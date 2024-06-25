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

@WebServlet("/Delete")
public class DeleteData extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("userid"));
		try {
			com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(d);
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/prachii","root","root");
			if(conn!=null)
			{
				PreparedStatement pstmt=conn.prepareStatement("delete from register where id="+id);
			    int value=pstmt.executeUpdate();
				if(value>0)
				{
				   out.println("<h2>Record Deleted</h2>");
				   RequestDispatcher r=request.getRequestDispatcher("View");
				   r.forward(request, response);
				}
				else
				{
				  out.println("<h2>Record not deleted</h2>");
				}
			}
			else
			{
				out.println("<h2>Not connected<h2>");
			}
		}
		catch(Exception ex)
		{
			out.println("exception ex "+ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
