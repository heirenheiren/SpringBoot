package com.boot.SpringBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.SpringBoot.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

	User findByUname(String name);

	User findByUnameOrUpass(String name, String upass);

}
