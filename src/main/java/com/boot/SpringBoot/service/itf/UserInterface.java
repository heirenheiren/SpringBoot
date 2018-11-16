package com.boot.SpringBoot.service.itf;

import com.boot.SpringBoot.domain.User;

public interface UserInterface
{
	public User saveUser(User user);
	public User batchSaveUser();
	public User getUserById(Integer id);
	public User getUserByName(String name);
}
