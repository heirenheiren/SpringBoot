package com.boot.SpringBoot.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.boot.SpringBoot.domain.User;

public interface UserJpaSpecificationExecutor extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User>
{

}
