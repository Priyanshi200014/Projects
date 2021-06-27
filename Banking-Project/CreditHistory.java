import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class CreditHistory extends HttpServlet
{
   public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
        res.setContentType("text/html");	   
	    PrintWriter out = res.getWriter();
	
	try
	{
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
		
		PreparedStatement ps = c.prepareStatement("select * from credit");
		
		ResultSet rs = ps.executeQuery();
		
		out.println("<html><body>");
		out.println("<table border='2' class='ttable2'>");
		out.println("<tr><th>Serial No. </th><th>Name</th><th>Amount</th><th>Date</th></tr>");
		int ser=1;
		while(rs.next())
		{
			out.println("<tr><td>"+ser+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");	
				ser++;
			
		}
		out.println("</table></body></html>");
			RequestDispatcher dispatcher = req.getRequestDispatcher("Transactions.html");
				  dispatcher.include(req,res);
			
         	
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	}	
	
	


}
