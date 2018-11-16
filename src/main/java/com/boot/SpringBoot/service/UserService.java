package com.boot.SpringBoot.service;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.SpringBoot.dao.UserRepository;
import com.boot.SpringBoot.domain.User;
import com.boot.SpringBoot.service.itf.UserInterface;

@Service
public class UserService implements UserInterface
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user)
	{
		User u = userRepository.save(user);
		return u;
	}
	
	@Override
	public User batchSaveUser()
	{
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
		
		Long begin = new Date().getTime();
		for (int j = 1; j <= 100; j++)
		{
			for (int i = 1; i <= 100; i++)
			{
				userRepository.save(new User(j*i+"", Math.random()+"",date));
			}
		}
		Long end = new Date().getTime();
		System.out.println("1000万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
		return userRepository.save(new User("cc", Math.random()+"",date));
	}

	@Override
	public User getUserById(Integer id)
	{
		return userRepository.findById(id).get();
	}
	
	@Override
	public User getUserByName(String name)
	{
		return userRepository.findByUname(name);
	}

}
