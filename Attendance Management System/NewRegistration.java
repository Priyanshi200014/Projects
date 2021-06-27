import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class NewRegistration extends HttpServlet
{
  public void init(ServletConfig h)
  {
	  
  }
 
  public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
      res.setContentType("text/html");	   
	   PrintWriter out = res.getWriter();
	    String CourseName="BVocWD";
		String Name = req.getParameter("NewName");
		String Phone = req.getParameter("NewPhone");		
	    String Email = req.getParameter("NewEmail");
	    String Password = req.getParameter("Newpassword");
	    
	
	try
	{
		int flag=0;
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		  
		/*Statement s = c.createStatement();
		s.executeUpdate("create table Users(Name varchar(40),PhoneNo varchar(14) ,Email varchar(30),Password varchar(15) Primary Key)");
		s.close();
		*/
		
		PreparedStatement ps = c.prepareStatement("select Password from Users");		
		ResultSet rs=ps.executeQuery();
               while(rs.next())
			{
				if(Password.equals(rs.getString(1)))
				{
				   JOptionPane.showMessageDialog(null,"Password already exists!!!","Alert Message",JOptionPane.WARNING_MESSAGE);
				
				     RequestDispatcher dispatcher = req.getRequestDispatcher("RegistrationForm.html");
				     dispatcher.include(req,res);
				     flag=1;
				     break;
				
				}
			}
			
			if(flag!=1)
			{
				 ps = c.prepareStatement("insert into Users values(?,?,?,?)");
				 ps.setString(1,Name);
				 ps.setString(2,Phone);		
				 ps.setString(3,Email);
				 ps.setString(4,Password);
		
				int i = ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Registration Successfull!!!","Alert Message",JOptionPane.WARNING_MESSAGE);
				
				     RequestDispatcher dispatcher = req.getRequestDispatcher("Login.html");
				     dispatcher.include(req,res);
			}
			
		
		/* ps = c.prepareStatement("select Name from Users where PhoneNo = ?");
		
		ps.setString(1,Phone);
		
	 
		ResultSet rs=ps.executeQuery();  
 
	    out.println("<p class='user_name'>");
		
		out.println("<table width=50% border=2>");
		out.println("<caption>Result : </caption>");
		
		ResultSetMetaData rsmd=rs.getMetaData();  
			int total=rsmd.getColumnCount();  
			out.print("<tr>");  
			for(int i=1;i<=total;i++)  
			{  
				out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
			}  
		    
             out.print("</tr>");  
		
		
		while(rs.next())  
		{  
			out.print(rs.getString(1));  
                  
		}  
  
			out.print("</p>");  
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("Login.html");
         dispatcher.include(req,res);	
		*/
         	
		   //out.println("Hey  you are successfully registered!!!");
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	   out.close();
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