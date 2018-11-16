package com.boot.SpringBoot.domain.enumer;

public enum Gender
{
	MALE("male",1),FEMALE("female",2),NEUTER("neuter",3);

	//成员变量  
    private String name;  
    private int ordinal;
	Gender(String name, int ordinal)
	{
		this.name = name;  
        this.ordinal = ordinal; 
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getOrdinal()
	{
		return ordinal;
	}
	public void setOrdinal(int ordinal)
	{
		this.ordinal = ordinal;
	}
	
}
