package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.databaseDao.Changepss;
import com.model.Basic;

@WebServlet("/Changepassword")
public class Changepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Changepassword() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("preview.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String password = request.getParameter("pass");
		String npassword = request.getParameter("npass");
		String url = "jdbc:mysql://localhost:3306/bhanu";
		String uname = "root";
		String pass = "Reset@123";
		String db = "com.mysql.cj.jdbc.Driver";
		String sql = "SELECT * from customers where password =?";
		String pass1=null;
		Basic r=new Basic();
		Changepss cp=new Changepss();
		
		try {
			Class.forName(db);
			Connection con = DriverManager.getConnection(url, uname, pass);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,r.getPassword());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				pass1=rs.getString(5);
			}
			
			
				r.setNewpassword(npassword);
				r.setPassword(password);
				cp.changeb(r);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
