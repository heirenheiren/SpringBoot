package com.boot.SpringBoot.dao;

import org.springframework.data.repository.CrudRepository;

import com.boot.SpringBoot.domain.User;

public interface UserCrudRepository extends CrudRepository<User, Integer>
{


}
