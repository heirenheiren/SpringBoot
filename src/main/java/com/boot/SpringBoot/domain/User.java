package com.boot.SpringBoot.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.boot.SpringBoot.domain.enumer.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@Entity(name="user")
@ApiModel(value = "用户信息")
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="uid",nullable = false,unique = true, length=11)
	@ApiModelProperty(value = "用户id", required = true) 
	public Integer uid=1;
	
	@Column(name="uname",nullable = false,unique = true, length=36)
	@ApiModelProperty(value = "昵称", required = true)
	public String userName;
	
	@Column(name="upass",nullable = false,unique = true, length=32)
	public String password;
	
	//@JSONField(format="YYYY-MM-dd HH:mm:ss:SS")
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(name="birthday")
	private Date birthDay;
	
	@Column(name="address", length=100)
	private String address;
	
	@Column(name="gender", length=1)
	private int gender;
	
	@Column(name="tel",length=11)
	private String tel;
	
	@Column(name="createby",nullable = false,length=6)
	private String createBy;
	
	@Column(name="updateby",nullable = false,length=6)
	private String updateBy;
	
	@Column(name="createdate",nullable = false)
	private Date createDate;
	
	@Column(name="updatedate",nullable = false)
	private Date updateDate;
	
	public User()
	{
		super();
	}

	public User(Integer uid, String userName, String password)
	{
		super();
		this.uid = uid;
		this.userName = userName;
		this.password = password;
	}

	public User(String userName, String password)
	{
		super();
		this.userName = userName;
		this.password = password;
	}
	
}
