import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangePassword extends HttpServlet
{
   public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
        res.setContentType("text/html");	   
	    PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		
		String pass1 = req.getParameter("passname1");
		String pass2 = req.getParameter("passname2");
		String passOld = req.getParameter("passnameold");
		
		
		
	try
	
	{
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
		
		
		
		if(pass1.equals(pass2))
		{
		PreparedStatement ps1 = c.prepareStatement("Update employee set password = ? where password = ?");
			ps1.setString(1,pass1);
			ps1.setString(2,passOld);
			ps1.executeUpdate();
			out.println("<p class='error'> Password Updated Please Login Again </p>");
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
				   dispatcher.include(req,res);
			
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Password does not Match","Alert Message",JOptionPane.WARNING_MESSAGE);
			
				RequestDispatcher dispatcher = req.getRequestDispatcher("ChangePassword.html");
				   dispatcher.forward(req,res);
			
		
		}
			
         
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	}	
	
	


}
