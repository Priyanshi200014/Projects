import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class SubmitAttendance extends HttpServlet
{
 
  public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
        res.setContentType("text/html");	   
	    PrintWriter out = res.getWriter();
		String UN = req.getParameter("selected_name");
		
	    String CourseName="BVocWD";
		
		String SubjN = req.getParameter("selected_subject");
		
		 String newColumn=req.getParameter("newCol");
		
		
		 
	try
	{
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		 
		  PreparedStatement ps=c.prepareStatement("Alter table "+SubjN+" add `"+newColumn+"` varchar(30)");
			int i=ps.executeUpdate();
			
			int rows = Integer.parseInt(req.getParameter("total_rows"));
			
			PreparedStatement ps1;
			String mark="";
			String ro="";
			
			for(int j=1;j<=rows;j++)
			{
				ps1 = c.prepareStatement("Update "+SubjN+" set `"+newColumn+"`= ? where RollNo= ?");
				mark = req.getParameter("PA"+j);
				ro = req.getParameter("RN"+j);
				ps1.setString(1,mark);
				ps1.setString(2,ro);
				ps1.executeUpdate();
				
			}
			
			
			
			out.print("<p class='user_name'>");
			out.print(UN);
			out.print("</p>");
			 RequestDispatcher dispatcher = req.getRequestDispatcher("home.html");
				   dispatcher.include(req,res);
			
         	
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	   out.close();
	}	
	
	
}