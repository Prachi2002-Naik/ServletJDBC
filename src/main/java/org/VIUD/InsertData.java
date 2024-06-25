package org.VIUD;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Insert")
public class InsertData extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String btn=request.getParameter("s");
		if(btn!=null)
		{
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		int age=Integer.parseInt(request.getParameter("age"));
		try {
			com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(d);
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/prachii","root","root");
			if(conn!=null)
			{
				System.out.println("connected");
				PreparedStatement pstmt=conn.prepareStatement("insert into register value('0',?,?,?)");
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				pstmt.setInt(3, age);
				int value=pstmt.executeUpdate();
				if(value>0)
				{
					RequestDispatcher r=request.getRequestDispatcher("RegistrationForm.html");
					r.forward(request, response);
					out.println("<h2>Data inserted</h2>");
				}
				else
				{
					out.println("<h2>Data not inserted</h2>");
				}
			}
			else
			{
			      out.println("<h2>Not connected<h2>");
			}
		}
		catch(Exception ex)
		{
			out.println("Exception is"+ex);
			
		}
	 }
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
