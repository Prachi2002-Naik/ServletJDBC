package org.VIUD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/View")
public class ViewData extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     response.setContentType("text/html");
	     PrintWriter out=response.getWriter();
	     
	     out.println("<html>");
	     out.println("<body>");
	     out.println("<h2><center>View Data</center></h2>");
	     out.println("</body>");
	     out.println("</html>");
	     
	     try {
	    	 com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
	    	 DriverManager.registerDriver(d);
	    	 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/prachii","root","root");
	    	 if(conn!=null)
	    	 {
	    		 //out.println("connected"); 
	    		 PreparedStatement pstmt=conn.prepareStatement("select*from register");
	    		 ResultSet rs=pstmt.executeQuery();
	    		 out.println("<table border='1' align='center' width='50%'>");
	    		 out.println("<tr>");
	    		 out.println("<th>Id</th>");
	    		 out.println("<th>Name</th>");
	    		 out.println("<th>Email</th>");
	    		 out.println("<th>Age</th>");
	    		 out.println("<th>Update</th>");
	    		 out.println("<th>Delete</th>");
	    		 out.println("</tr>");
	    		 while(rs.next())
	    		 {
	    			 out.println("<tr>");
	    			 out.println("<td>"+rs.getInt(1)+"</td>");
	    			 out.println("<td>"+rs.getString(2)+"</td>");
	    			 out.println("<td>"+rs.getString(3)+"</td>");
	    			 out.println("<td>"+rs.getInt(4)+"</td>");
	    			 out.println("<td><a href='Update?ename="+rs.getString("name")+ "&eemail="+rs.getString("email")+"&eage="+rs.getInt("age")+"&eid="+rs.getInt("id")+"'>Update</a></td>");
	    			 out.println("<td><a href='Delete?userid="+rs.getInt("id")+"'>Delete</a></td>");
	    			 out.println("</tr>");
	    		 }
	    	 }
	    	 else
	    	 {
	    		 out.print("not connected");
	    	 }
	    	 out.println("<h3><a href='RegistrationForm.html'>Registration Page</a><h3>");
	     }
	     catch(Exception ex)
	     {
	    	 out.println("Exception is "+ex);
	     }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
