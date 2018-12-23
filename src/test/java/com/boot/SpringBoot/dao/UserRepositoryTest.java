package com.boot.SpringBoot.dao;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.boot.SpringBoot.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest
{
	@Autowired
	private UserJpaRepository userJpaRepository;
	@Test
	public void test()
	{
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
		
//		userJpaRepository.save(new User("cc", Math.random()+""));
		userJpaRepository.save(new User("bb", Math.random()+""));
//		List<User> listUser = userJpaRepository.findAll();
//		for (User user : listUser)
//		{
//			System.out.println(user.getUname());
//		}
//		Assert.assertEquals(userJpaRepository.count(), userRepository.findAll().size());
//		Assert.assertEquals("bb", userJpaRepository.findByUnameOrUpass("bb", "aa123456").getUname());
		//userJpaRepository.delete(userJpaRepository.findByUname("aa"));
		//userJpaRepository.delete(userJpaRepository.findByUname("bb"));
	}

}
