package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.databaseDao.UpdatePassword;
import com.model.OutDetails;
import com.model.Usermail;

/**
 * Servlet implementation class VerifyCode
 */
@WebServlet("/VerifyCode")
public class VerifyCode extends HttpServlet
{
	private final long serialVersionUID = 1L;

	public VerifyCode() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("served at: ").append(request.getContextPath());
		//response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher ds=request.getRequestDispatcher("changep.jsp");
		ds.forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			 
			String newpass = request.getParameter("newpass");
			Usermail user = (Usermail) session.getAttribute("authcode");

			String code = request.getParameter("authcode");

			if (code.equals(user.getCode())) {
				OutDetails outdetails = new OutDetails();
				outdetails.setOutemail(user.getEmail());
				outdetails.setNewpassword(newpass);
				UpdatePassword.updateDetails(outdetails);
				request.setAttribute("successsmessage", "Login With New Password!!!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.setAttribute("mmessage", "incorrect verification code");
				request.getRequestDispatcher("verify.jsp").forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/bhanu";
		String uname = "root";
		String pass = "Reset@123";
		String db = "com.mysql.cj.jdbc.Driver";
		String passs = "";
		String firstname = "";
		PrintWriter out = response.getWriter();
		Connection con = null;
		

		try
		{
			Class.forName(db);
			con = DriverManager.getConnection(url, uname, pass);
			HttpSession session=request.getSession();
			Usermail user=(Usermail)session.getAttribute("authcode");

			String code = request.getParameter("authcode");
			String password= request.getParameter("password");
			String cpassword = request.getParameter("cpassword");
			String mail = user.getEmail();
			if(code.equals(user.getCode()))
			{
				if (password.equals(cpassword)) 
				{

					PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from customers where emailid=?");
					ps.setString(1,mail);
					ResultSet rs = ps.executeQuery();
				
					while (rs.next()) 
					{
						firstname = rs.getString(3);
						passs= rs.getString(5);
						if (firstname.equals(mail)) 
						{
							
						    Statement st1 = con.createStatement();
							int i = st1.executeUpdate("update customers set password='" + password + "' where emailid='" + firstname + "'");
							if (i > 0)
							{
								out.println("Password changed successfully");
								//response.sendRedirect("login.jsp");
								st1.close();
								con.close();
							}
							else 
							{
								out.println("password update failed");
							} 

						}
						else
						{
							out.println("Incorrect Password");
						}
					}
				}
				else
				{
					out.println("In valid otp");
				}
			}
			else
				{
					out.println("In valid otp");
				}
			
			

		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}




