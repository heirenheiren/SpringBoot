package com.boot.SpringBoot.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.boot.SpringBoot.dao.UserJpaRepository;
import com.boot.SpringBoot.dao.UserJpaSpecificationExecutor;
import com.boot.SpringBoot.dao.UserPagingAndSortingRepository;
import com.boot.SpringBoot.domain.User;
import com.boot.SpringBoot.domain.enumer.Gender;
import com.boot.SpringBoot.importcsv.CsvUtil;
import com.boot.SpringBoot.service.itf.UserInterface;
import com.boot.SpringBoot.utils.TimeUtils;

@Service
public class UserService implements UserInterface
{
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	private UserPagingAndSortingRepository userPagingAndSortingRepository;
	
	@Autowired
	private UserJpaSpecificationExecutor userJpaSpecificationExecutor;

	@Override
	public User saveUser(User user)
	{
		User u = userJpaRepository.save(user);
		return u;
	}

	@Override
	public void batchSaveUser()
	{
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		logger.debug(formattedDate);
		
		User user = null;
		List<User> entities = null;
		for (int j = 1; j <= 10000; j++)
		{
			Long begin = new Date().getTime();
			entities = new LinkedList<User>();
			for (int i = 1; i <= 1000; i++)
			{
				user = new User();
				user.setUserName(UUID.randomUUID().toString());
				user.setPassword(UUID.randomUUID().toString().replace("-", "").toLowerCase());
				user.setBirthDay(TimeUtils.StringToDate("1988-03-27"));
				user.setAddress("中国广东省深圳市福田区福华路5002号平安金融中心21楼");
				user.setGender(Gender.MALE.getValue());
				user.setTel("13632878637");
				user.setCreateBy("SYSTEM");
				user.setUpdateBy("SYSTEM");
				Date dt = new Date();
				user.setCreateDate(dt);
				user.setUpdateDate(dt);
				entities.add(user);
				// userRepository.save(user);
			}
			System.out.println("begin");
			userJpaRepository.saveAll(entities);
			entities.clear();
			Long end = new Date().getTime();
			System.out.println("1000条数据插入花费时间 : " + (end - begin) / 1000 + " s");
		}

	}

	@Override
	public User getUserById(Integer id)
	{
		return userJpaRepository.findById(id).get();
	}

	@Override
	public User getUserByName(String name)
	{
		return userJpaRepository.findByUserName(name);
	}

	@Override
	public void jdbcSaveUser()
	{
		int begin = userJpaRepository.getMaxUid()+1;// 起始id
		long end = begin + 100000;// 每次循环插入的数据量
		String url = "jdbc:mysql://localhost:3306/test?useServerPrepStmts=false&rewriteBatchedStatements=true&useUnicode=true&amp;characterEncoding=UTF-8";
		String user = "root";
		String password = "123456";

		// 定义连接、statement对象
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try 
		{
            //加载jdbc驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //编写sql
            String sql = "insert into user (uid,uname,upass,birthday,address,gender,tel,createby,updateby,createdate,updatedate) values (?, ?, ?, ?, ?, ?, ?, 'SYSTEM', 'SYSTEM', now(), now())";
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();

            //循环10次，每次十万数据，一共1000万
            for(int i=0;i<10;i++) 
            {
            	//将自动提交关闭
                conn.setAutoCommit(false);
                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) 
                {
                    //赋值
                    pstm.setInt(1, begin);
                    pstm.setString(2, UUID.randomUUID().toString());
                    pstm.setString(3, UUID.randomUUID().toString().replace("-", "").toLowerCase());
                    pstm.setDate(4, new java.sql.Date(TimeUtils.StringToDate("1988-03-27").getTime()));
                    pstm.setString(5, "中国广东省深圳市福田区福华路5002号平安金融中心21楼");
                    pstm.setInt(6, Gender.MALE.getValue());
                    pstm.setString(7, "13632878637");
                    //添加到同一个批处理中
                    pstm.addBatch();
                    begin++;
                }
                System.out.println("begin");
                //执行批处理
                pstm.executeBatch();
                //提交事务
                System.out.println("end");
                conn.commit();
                //边界值自增10W
                end += 100000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时："+(eTime-bTime)/1000);
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时："+(eTime1-bTime1)/1000);
        } 
		catch (SQLException e) 
		{
            e.printStackTrace();
        } 
		catch (ClassNotFoundException e1) 
		{
            e1.printStackTrace();
        }
	}

	@Override
	public Page<User> list(int page, int offset, String properties)
	{
		Sort sort = new Sort(Direction.DESC, properties);
		Pageable pageable = PageRequest.of(page, offset, sort);
		return userPagingAndSortingRepository.findAll(pageable);
	}

	@Override
	public void exportUser(HttpServletRequest request, HttpServletResponse response, int page, int offset, String properties)
	{
		Sort sort = new Sort(Direction.DESC, properties);
		Pageable pageable = PageRequest.of(page, offset, sort);
		Page<User> pageUser= userPagingAndSortingRepository.findAll(pageable);
		
		List<User> users = pageUser.getContent();
		String fileName = "user.csv";//压缩包里面的文件
		String[] header = {"序号", "名称", "地址", "电话", "生日"};
		CsvUtil.exportData(request,response,users,fileName,header);
	}

}
