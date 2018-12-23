package com.boot.SpringBoot.importcsv;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boot.SpringBoot.domain.User;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvUtil
{
	// 定义一个CSV路径
	static String csvFilePath = "D://StemQ.csv";

	// 编码类型
	public static final Charset CHARSET = Charset.forName("UTF-8");

	// 分隔符
	public static final char DELIMITER = ',';

	// 文件后缀
	public static final String SUFFIX = ".csv";

	public static void main(String[] args)
	{
		// readCSV();
	}

	public static void readCSV()
	{
		try
		{
			// 用来保存数据
			ArrayList<String[]> csvFileList = new ArrayList<String[]>();
			// 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
			CsvReader reader = new CsvReader(csvFilePath, DELIMITER, CHARSET);
			// 跳过表头 如果需要表头的话，这句可以忽略
			reader.readHeaders();
			// 逐行读入除表头的数据
			while (reader.readRecord())
			{
				System.out.println(reader.getRawRecord());
				csvFileList.add(reader.getValues());
			}
			reader.close();

			// 遍历读取的CSV文件
			for (int row = 0; row < csvFileList.size(); row++)
			{
				// 取得第row行第0列的数据
				String cell = csvFileList.get(row)[0];
				System.out.println("------------>" + cell);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void writeCsv(String zipFilePath, String csvName, String[] header, ResultSet rs)
			throws IOException, SQLException
	{
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ZipOutputStream zos = null;
		try
		{
			fos = new FileOutputStream(zipFilePath);
			bos = new BufferedOutputStream(fos);
			zos = new ZipOutputStream(bos);
			zos.putNextEntry(new ZipEntry(csvName));
			writeCsv(zos, header, rs);
		}
		finally
		{
			flush(zos);
			close(zos);
			// flush(bos);
			close(bos);
			// flush(fos);
			close(fos);
		}
	}

	public static void writeCsv(String csvFilePath, String[] header, ResultSet rs) throws IOException, SQLException
	{
		writeCsv(new File(csvFilePath), header, rs);
	}

	private static void writeCsv(File file, String[] header, ResultSet rs) throws IOException, SQLException
	{
		BufferedOutputStream out = null;
		FileOutputStream fileOutputStream = null;
		try
		{
			fileOutputStream = new FileOutputStream(file);
			out = new BufferedOutputStream(fileOutputStream);
			writeCsv(out, header, rs);
		}
		finally
		{
			if (out != null)
			{
				out.flush();
				out.close();
			}
			if (fileOutputStream != null)
			{
				fileOutputStream.close();
			}
		}
	}

	private static void writeCsv(OutputStream out, String[] header, ResultSet rs) throws IOException, SQLException
	{
		CsvWriter writer = null;
		try
		{
			// 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
			writer = new CsvWriter(out, DELIMITER, CHARSET);
			writeCsv(writer, header, rs);
		}
		finally
		{
			if (writer != null)
				writer.close();
		}
	}

	private static void writeCsv(CsvWriter writer, String[] header, ResultSet rs) throws SQLException, IOException
	{
		// 写表头
		if (header != null)
			writer.writeRecord(header);

		ResultSetMetaData md = rs.getMetaData();

		// method1
		int columnCount = md.getColumnCount();
		while (rs.next())
		{
			for (int i = 1; i <= columnCount; i++)
			{
				writer.write(rs.getString(i));
			}
			writer.endRecord();
		}

		// method2
		while (rs.next())
		{
			String[] csvContent = new String[columnCount];
			for (int i = 1; i <= columnCount; i++)
			{
				csvContent[i] = rs.getString(i);
			}
			writer.writeRecord(csvContent);
		}
		writer.close();
		System.out.println("--------CSV文件已经写入--------");
	}

	private static void flush(Flushable flushable)
	{
		if (flushable != null)
		{
			try
			{
				flushable.flush();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private static void close(Closeable closeable)
	{
		if (closeable != null)
		{
			try
			{
				closeable.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void exportData(HttpServletRequest request, HttpServletResponse response, List<User> users, String fileName, String[] header)
	{
		try
		{
			// 为了解决中文名称乱码问题
			//fileName = URLEncoder.encode(fileName, "UTF-8");
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			CsvWriter writer = new CsvWriter(new BufferedOutputStream(new FileOutputStream(new File("D://"+fileName))), DELIMITER, CHARSET);
			// 写表头
			if (header != null)
				writer.writeRecord(header);
			
			for (User user : users)
			{
				String[] content = {user.getUid()+"",user.getUserName(),user.getAddress(),user.getTel(),user.getBirthDay().toString()};
				writer.writeRecord(content);
			}
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			OutputStream out = response.getOutputStream();
			writer.flush();
			out.close();
			writer.close();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
