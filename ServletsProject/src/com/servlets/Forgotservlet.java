package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Usermail;

/**
 * Servlet implementation class Forgotservlet
 */
@WebServlet("/Forgotservlet")
public class Forgotservlet extends HttpServlet {
	
	private static final long serialVersionUID=1L;
	
	public Forgotservlet()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("served at: ").append(request.getContextPath());
		
		RequestDispatcher rd=request.getRequestDispatcher("forgot.jsp");
		rd.forward(request,response);
	}
		
	
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter())
		{
			String emaill=request.getParameter("email");
			String submittype=request.getParameter("submit");
			
			SendEmail sm=new SendEmail();
			String code=sm.getRandom();
			
			Usermail user=new Usermail(emaill,code);
			boolean test=sm.sendEmail(user);
			
			if(test)
			{
				HttpSession session=request.getSession();
				session.setAttribute("authcode",user);
				request.getRequestDispatcher("changep.jsp").forward(request, response);
				out.println("hi bhanu");
			}
			else  
			{
				//request.setAttribute("message", "invalid mail");
				//request.getRequestDispatcher("forgot.jsp").forward(request, response);
				out.println("invalid email");
			}
			
		}
	}
	
   
 	
}
