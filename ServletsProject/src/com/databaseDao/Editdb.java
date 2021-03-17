package com.databaseDao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.model.Basic;

public class Editdb {
	public int edit1(Basic r) {
		int result = 0;
		String url = "jdbc:mysql://localhost:3306/bhanu";
		String uname = "root";
		String pass = "Reset@123";
		String db = "com.mysql.cj.jdbc.Driver";
		
		String sql = "update customers set lastname=?,emailid=?,mobilenumber=?,photo=? where firstname =?";
		try {
			Class.forName(db);
			Connection con = DriverManager.getConnection(url, uname, pass);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, r.getLastname());
			ps.setString(2, r.getEmailid());
			ps.setString(3, r.getMobilenumber());
			ps.setBlob(4, r.getPhoto());
			ps.setString(5, r.getFirstname());
			//System.out.println(ps);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}