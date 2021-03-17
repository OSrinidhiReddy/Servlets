
package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Loginservlet")
@MultipartConfig(maxFileSize = 16177215)
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public Loginservlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=null;
		try {
			String username1 =request.getParameter("firstname");
			String password1 =request.getParameter("password");
			String dbname=null;
			String path=null;
			String dbpassword=null;
			String sql="select * from customers where firstname=? and password=? ";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","root","Reset@123");
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, username1);
			ps.setString(2, password1);
			ResultSet rs=ps.executeQuery();
			PrintWriter out=response.getWriter();
			HttpSession session =request.getSession();
			
			
				while(rs.next()) {
				dbname =rs.getString(1);
				dbpassword=rs.getString(5);
				email=rs.getString(3);
				path=rs.getString(7);
				
				}
			
			if(username1.equals(dbname) && password1.equals(dbpassword)) {
				session.setAttribute("name", dbname);
				session.setAttribute("email", email);
				session.setAttribute("path", path);
				RequestDispatcher dd=request.getRequestDispatcher("preview.jsp");
				dd.forward(request, response);
			}else {
				RequestDispatcher rd= request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();}
		
		}

}

