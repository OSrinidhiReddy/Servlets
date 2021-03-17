package com.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

import com.databaseDao.Registerdao;
import com.model.Basic;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/Registerservlet")

public class Registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Registerdao rdao = new Registerdao();

	public Registerservlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String foldername="Imagepath";
		String uploadpath=request.getServletContext().getRealPath("")+foldername;
		File ir=new File(uploadpath);
		
		if(!ir.exists()) {
			ir.mkdirs();
		}
		
		
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String emailid = request.getParameter("emailid");
		String mobilenumber = request.getParameter("mobilenumber");
		String password = request.getParameter("password");

		InputStream inputStream = null;

		Part filePart = request.getPart("imageupload");
		
		String filename=filePart.getSubmittedFileName();
		String path=foldername+File.separator+filename;
		 InputStream is=filePart.getInputStream();
		 Files.copy(is,Paths.get(uploadpath + File.separator + filename),StandardCopyOption.REPLACE_EXISTING);
		 
	
		if (filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			inputStream = filePart.getInputStream();
		}
		Basic b = new Basic();
		b.setFirstname(firstname);
		b.setLastname(lastname);
		b.setEmailid(emailid);
		b.setMobilenumber(mobilenumber);
		b.setPassword(password);
		b.setPhoto(inputStream);
		b.setPath(path);

		try 
		{
			rdao.registration(b);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
			
		}
		RequestDispatcher rd = request.getRequestDispatcher("sucess.jsp");
		rd.forward(request, response);

	}


}
