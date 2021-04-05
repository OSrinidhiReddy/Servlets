package com.databaseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.model.Basic;

public class Changepss
{
	public int changeb(Basic r) {
		int result = 0;
		String url = "jdbc:mysql://localhost:3306/sri";
		String uname = "***";
		String pass = "***";
		String db = "com.mysql.cj.jdbc.Driver";
		
		String sql = "update customers set password=? where password =?";
		try {
			Class.forName(db);
			Connection con = DriverManager.getConnection(url, uname, pass);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, r.getNewpassword());
			ps.setString(2, r.getPassword());
			System.out.println(ps);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
