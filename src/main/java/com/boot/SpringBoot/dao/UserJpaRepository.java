package com.boot.SpringBoot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.SpringBoot.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Integer>
{

	User findByUserName(String name);
	
	List<User> findByUserNameLike(String name);
	
	List<User> findByUidIn(List<Integer> ids);
	
	List<User> findByUidNotIn(List<Integer> ids);

	User findByUserNameOrPassword(String name, String upass);

	//描述：自定义查询，当Spring Data JPA无法提供时，需要自定义接口，此时可以使用这种方式
	@Query("select max(uid) from user")
	int getMaxUid();
	
	/**
	 * 索引参数
	 * 描述：使用?占位符
	 */
	@Query("select u from user u where u.userName = ?1")// 1表示第一个参数
	User findUserByName(String name);
	
	/**
	 * 描述：可以通过@Modifying和@Query来实现更新
	 * 注意：Modifying queries的返回值只能为void或者是int/Integer
	 */
	@Modifying
	@Query("update user u set u.userName = :name where u.uid = :id")
	int updateUserById(@Param("name") String name, @Param("id") int id);
}
