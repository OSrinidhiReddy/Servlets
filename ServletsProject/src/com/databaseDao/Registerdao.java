package com.databaseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.model.Basic;

public class Registerdao 
{

	 String url = "jdbc:mysql://localhost:3306/****";
	 String uname = "***";
	 String pwd = "***";

	public int registration(Basic b) throws ClassNotFoundException {
		String sql = "insert into customers values(?,?,?,?,?,?,?)";
		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			Connection con = DriverManager.getConnection(url,uname,pwd);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, b.getFirstname());
			ps.setString(2, b.getLastname());
			ps.setString(3, b.getEmailid());
			ps.setString(4, b.getMobilenumber());
			ps.setString(5, b.getPassword());
			ps.setBlob(6,b.getPhoto());
			ps.setString(7, b.getPath());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

