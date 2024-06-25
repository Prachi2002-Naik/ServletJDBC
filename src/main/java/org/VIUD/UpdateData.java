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

@WebServlet("/Update")
public class UpdateData extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out=response.getWriter();
          
     
      String name=request.getParameter("ename");
      String email=request.getParameter("eemail");
      int age=Integer.parseInt(request.getParameter("eage"));
      
       out.println("<html>");
       out.println("<body>");
       out.println("<form name='frm' action='' method='post' style='border:1px solid black; width:400px; height:350px; margin-left:40%; margin-top:40px;'>");
       out.println("<center>");
       out.println("<h2>Updated Page</h2>");
	   out.println("<input style='width:200px; height:40px;' type='text' name='name' value='"+name+"' class='control'>");
	   out.println("<br><br>");
	   out.println("<input style='width:200px; height:40px;' type='text' name='email' value='"+email+"' class='control'>");
	   out.println("<br><br>");
	   out.println("<input style='width:200px; height:40px;' type='text' name='age' value='"+age+"' class='control'>");
	   out.println("<br><br>");
	   out.println("<input style='width:200px; height:40px;' type='submit' name='s' value='Submit' class='control'>");
	   out.println("</center>");
	   out.println("</form>");
	   
	   String button=request.getParameter("s");
	   
      if(button!=null)
      {
    	  name=request.getParameter("name");
    	  email=request.getParameter("email");
    	  age=Integer.parseInt(request.getParameter("age"));
          int uid=Integer.parseInt(request.getParameter("eid"));
      try {
    	  com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
    	  DriverManager.registerDriver(d);
    	  Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/prachii","root","root");
    	  if(conn!=null)
    	  {
    		   PreparedStatement pstmt=conn.prepareStatement("update register set name=?,email=?,age=? where id=?");
    		   pstmt.setString(1, name);
    		   pstmt.setString(2,email);
    		   pstmt.setInt(3, age);
    		   pstmt.setInt(4, uid);
    		  
    		   int value=pstmt.executeUpdate();
    		   if(value>0)
    		   {
    			   RequestDispatcher r=request.getRequestDispatcher("View");
    	           r.forward(request, response);
    			   out.println("<h2>Data Updated Success<h2>");
    		   }
    		   else
    		   {
    			   out.println("<h2>Data not updated<h2>");
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
      else
      {
    	  
      }
      out.println("</body>");
	   out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
