import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class TeacherRegister implements Servlet
{
  public void init(ServletConfig h)
  {
	  
  }
 
   public void service(ServletRequest req,ServletResponse res)throws ServletException, IOException
   {
      res.setContentType("text/html");	   
	   PrintWriter pw = res.getWriter();
	    
		String Name = req.getParameter("teacherName");
	    String SubjectName = req.getParameter("Subjects_Values");
	    String CourseName = req.getParameter("courseName");
	    String teacherPhone = req.getParameter("teacherPhone");
	    String teacherGender = req.getParameter("teacherGender");
	       String UN = req.getParameter("selected_name");
	
	try
	{
		
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		  
		/*Statement s = c.createStatement();
		s.executeUpdate("create table TeachersList(Name varchar(40),SubjectName varchar(20),PhoneNo varchar(15),Gender varchar(12))");
		s.close();
		*/
		PreparedStatement ps = c.prepareStatement("insert into TeachersList values(?,?,?,?)");
		ps.setString(1,Name);
		ps.setString(2,SubjectName);
		ps.setString(3,teacherPhone);
		ps.setString(4,teacherGender);
		
		int i = ps.executeUpdate();
		  
		  ps=c.prepareStatement("Update subjectteacher set TeacherName = ? where SubjectName=?");
		  ps.setString(1,Name);
		  ps.setString(2,SubjectName);
		  ps.executeUpdate();
		  
		   	pw.print("<p class='user_name'>");
			pw.print(UN);
			pw.print("</p>");
		   
         RequestDispatcher dispatcher = req.getRequestDispatcher("home.html");
         dispatcher.include(req,res);		
		   pw.println("Hey  you are successfully registered!!!");
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	   pw.close();
	}	
	
	public void destroy()
	  {
		  
	  }
	  public ServletConfig getServletConfig()
	  {
		return(null);  
	  }
	  public String getServletInfo()
	  {
		 return(null);  
	  }

}