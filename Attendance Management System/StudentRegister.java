import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class StudentRegister implements Servlet
{
  public void init(ServletConfig h)
  {
	  
  }
 
   public void service(ServletRequest req,ServletResponse res)throws ServletException, IOException
   {
      res.setContentType("text/html");	   
	   PrintWriter pw = res.getWriter();
	    String UN = req.getParameter("userName");
		String studName = req.getParameter("studName");
	    String CourseName = req.getParameter("studCourse");
	    String studAddress = req.getParameter("studAddress");
	    String studPhone = req.getParameter("studPhone");
	    String studEmailId = req.getParameter("studEmailId");
	    String studBatchCode = req.getParameter("studBatchCode");
	    String studGender = req.getParameter("studGender");
	    String studRollNo = req.getParameter("studRollNo");
	    String studDOB = req.getParameter("studDOB");

	
	try
	{
		
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		  
		/*Statement s = c.createStatement();
		s.executeUpdate("create table StudentsList(Name varchar(30),RollNo varchar(20) Primary Key,Address varchar(40),Phone varchar(12),EmailId varchar(30),BatchCode varchar(15),Gender varchar(10),DOB varchar(20))");
		s.close();
		*/
		
		
		
		PreparedStatement ps = c.prepareStatement("insert into StudentsList values(?,?,?,?,?,?,?,?)");
		ps.setString(1,studName);
		ps.setString(2,studRollNo);
		ps.setString(3,studAddress);
		ps.setString(4,studPhone);
		ps.setString(5,studEmailId);
		ps.setString(6,studBatchCode);
		ps.setString(7,studGender);
		ps.setString(8,studDOB);
		

        int i = ps.executeUpdate();
		
		PreparedStatement ps1 = c.prepareStatement("SELECT SubjectName FROM subjectteacher");
		

		 ResultSet rs = ps1.executeQuery();
		  PreparedStatement pstmt;
		  String su;
            while (rs.next()) 
			{
				
                su = rs.getString(1);
                pstmt = c.prepareStatement("INSERT INTO "+su+"(StudentName,RollNo,DOB) VALUES(?,?,?)");
				
                pstmt.setString(1,studName);
                pstmt.setString(2,studRollNo);
                pstmt.setString(3,studDOB);
                
                pstmt.executeUpdate();
            }
		
		
			
		
		
		    pw.print("<p class='user_name'>");
			pw.print(UN);
			pw.print("</p>");
         RequestDispatcher dispatcher = req.getRequestDispatcher("home.html");
         dispatcher.include(req,res);		
		  // pw.println("Hey  you are successfully registered!!!");
		  

	 
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