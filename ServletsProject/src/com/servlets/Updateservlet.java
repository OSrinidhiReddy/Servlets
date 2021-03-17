package com.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.databaseDao.Editdb;
import com.model.Basic;

@WebServlet("/Updateservlet")
@MultipartConfig(maxFileSize = 16177215)
public class Updateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Updateservlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String emailid = request.getParameter("emailid");
		String mobilenumber = request.getParameter("mobilenumber");
		String submittype = request.getParameter("submit");
		InputStream inputStream = null;		Part filePart = request.getPart("photo");

		if (filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			inputStream = filePart.getInputStream();
		}

		Basic r= new Basic();
		Editdb edit = new Editdb();
		try {

			if (submittype.equals("update")) {
				r.setFirstname(firstname);
				r.setLastname(lastname);
				r.setEmailid(emailid);
				r.setMobilenumber(mobilenumber);
				r.setPhoto(inputStream);
				edit.edit1(r);
				
				request.getRequestDispatcher("sucess.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}