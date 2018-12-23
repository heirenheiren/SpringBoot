package com.boot.SpringBoot.service.itf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.boot.SpringBoot.domain.User;

public interface UserInterface
{
	public User saveUser(User user);
	public void batchSaveUser();
	public void jdbcSaveUser();
	public User getUserById(Integer id);
	public User getUserByName(String name);
	public Page<User> list(int page,int offset,String properties);
	void exportUser(HttpServletRequest request, HttpServletResponse response, int page, int offset, String properties);
}
