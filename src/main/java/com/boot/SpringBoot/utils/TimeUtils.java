package com.boot.SpringBoot.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils
{

	public static Date StringToDate(String dateStr)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try
		{
			date = sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}

	public static Date getCurrentTime()
	{
		return new Date();
	}
}
