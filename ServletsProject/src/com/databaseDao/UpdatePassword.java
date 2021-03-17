package com.databaseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.model.OutDetails;

public class UpdatePassword 
{

	public static int  updateDetails(OutDetails outdetails) throws ClassNotFoundException
	{

		String url = "jdbc:mysql://localhost:3306/bhanu";
		String uname = "root";
		String pwd = "Reset@123";
		int i=0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try
		{
			Connection con = DriverManager.getConnection(url,uname,pwd);
			PreparedStatement ps = con.prepareStatement("update customers set password=? where emailid=?");
			ps.setString(1, outdetails.getNewPassword());
			ps.setString(2, outdetails.getOutemail());

			if(i>0)
			{
				System.out.println("update succefull");
			}
			else
			{
				System.out.println("not-update succefull");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return i;
	}
}


