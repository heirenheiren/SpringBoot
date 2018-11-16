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
	private UserRepository userRepository;
	@Test
	public void test()
	{
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
		
//		userRepository.save(new User("cc", Math.random()+""));
		userRepository.save(new User("bb", Math.random()+""));
//		List<User> listUser = userRepository.findAll();
//		for (User user : listUser)
//		{
//			System.out.println(user.getUname());
//		}
//		Assert.assertEquals(userRepository.count(), userRepository.findAll().size());
//		Assert.assertEquals("bb", userRepository.findByUnameOrUpass("bb", "aa123456").getUname());
		//userRepository.delete(userRepository.findByUname("aa"));
		//userRepository.delete(userRepository.findByUname("bb"));
	}

}
