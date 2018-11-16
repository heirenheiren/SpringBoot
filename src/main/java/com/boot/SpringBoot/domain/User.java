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
@Entity
@ApiModel(value = "用户信息")
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@ApiModelProperty(value = "用户id", required = true) 
	public Integer uid=1;
	
	@Column(nullable = false, unique = true)
	@ApiModelProperty(value = "昵称", required = true)
	public String uname;
	
	@Column(nullable = false, unique = false)
	public String upass;
	
	//@JSONField(format="YYYY-MM-dd HH:mm:ss:SS")
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date birthday;
	
	private String address;
	
	private String tel;
	
	private Gender gender;
	
	public User()
	{
		super();
	}
	public User(String uname, String upass)
	{
		super();
		this.uname = uname;
		this.upass = upass;
	}
	public User(Integer uid, String uname, String upass)
	{
		super();
		this.uid = uid;
		this.uname = uname;
		this.upass = upass;
	}
	
	public User(String uname, String upass, Date birthday)
	{
		super();
		this.uname = uname;
		this.upass = upass;
		this.birthday = birthday;
	}
	public int getUid()
	{
		return uid;
	}
	public void setUid(Integer uid)
	{
		this.uid = uid;
	}
	public String getUname()
	{
		return uname;
	}
	public void setUname(String uname)
	{
		this.uname = uname;
	}
	public String getUpass()
	{
		return upass;
	}
	public void setUpass(String upass)
	{
		this.upass = upass;
	}
	public Date getBirthday()
	{
		return birthday;
	}
	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public Gender getGender()
	{
		return gender;
	}
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	@Override
	public String toString()
	{
		return "User [uid=" + uid + ", uname=" + uname + ", upass=" + upass + ", birthday=" + birthday + ", address="
				+ address + ", tel=" + tel + ", gender=" + gender + "]";
	}
	
}
