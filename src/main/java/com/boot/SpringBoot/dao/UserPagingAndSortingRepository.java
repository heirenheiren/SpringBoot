package com.boot.SpringBoot.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.boot.SpringBoot.domain.User;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, Integer>
{

}
