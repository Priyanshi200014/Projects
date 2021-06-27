import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class NewSubject implements Servlet
{
  public void init(ServletConfig h)
  {
	  
  }
 
   public void service(ServletRequest req,ServletResponse res)throws ServletException, IOException
   {
      res.setContentType("text/html");	   
	   PrintWriter pw = res.getWriter();
	   
		String CourseName = req.getParameter("courseName");
		
		String NewSubjectName = req.getParameter("NewSubjectName");
		
		String teacherName = req.getParameter("teacherName");
		
	   		String UN = req.getParameter("selected_name");

	  try
	  {
     
	
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		  
	
		PreparedStatement ps = c.prepareStatement("insert into SubjectTeacher values(?,?)");
		
		ps.setString(1,NewSubjectName);
		ps.setString(2,teacherName);
		
		ps.executeUpdate();
		
		 ps = c.prepareStatement("create table "+NewSubjectName+"(StudentName varchar(30),RollNo varchar(20),DOB varchar(14))");
	     ps.executeUpdate();
		 
		 
            Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery("select Name,RollNo,DOB from Studentslist");
            while (rs.next()) 
			{
				
                String a = rs.getString(1);
				String b = rs.getString(2);
				String c1 = rs.getString(3);
						 
				PreparedStatement ps1 = c.prepareStatement("insert into "+NewSubjectName+" values (?,?,?)");
                ps1.setString(1,a);
                ps1.setString(2,b);
                ps1.setString(3,c1);
                
                ps1.executeUpdate();
            }
		
		

		         pw.print("<p class='user_name'>");
				   pw.print(UN);
				   pw.print("</p>");
         RequestDispatcher dispatcher = req.getRequestDispatcher("home.html");
         dispatcher.include(req,res);		
		   //pw.println("Hey  you are successfully registered!!!");	
	  
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