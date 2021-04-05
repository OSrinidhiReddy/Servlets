package com.servlets;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.model.Usermail;

public class SendEmail 
{
	public String getRandom()
	{
		java.util.Random rmd=new java.util.Random();
		int num=rmd.nextInt(999999);

		return String.format("%06d", num);

	}

	public boolean sendEmail(Usermail user)
	{
		boolean test=false;

		 String toEmail=user.getEmail();
		 String fromEmail="**************";
		 String password="*************";


		try 
		{
			Properties properties=new Properties();

			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.port","587");
			properties.put("mail.smtp.auth" ,"true");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.debug","true");
			properties.put("mail.smtp.socketFactory.port","587");
			properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	   	 //get session
			Session session=Session.getInstance(properties,new Authenticator()
			{
				@Override 
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail,password);
				}
			});
			Message mes=new MimeMessage(session);

			mes.setFrom(new InternetAddress(fromEmail));
			mes.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));

			mes.setSubject("user email verified");
			mes.setText("successfull.... your verification otp is : "+user.getCode());

			javax.mail.Transport.send(mes);

			test=true;

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return test;
	}

}



