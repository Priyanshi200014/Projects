import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class AdminLogin extends HttpServlet
{
  public void init(ServletConfig h)
  {
	  
  }
 
  public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
      res.setContentType("text/html");	   
	   PrintWriter out = res.getWriter();
	    String CourseName="BVocWD";
				
	    String Uname = req.getParameter("UName");
	    String Password = req.getParameter("password");
	    
	  
	
	try
	{
		int flag=0;
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		  		
		PreparedStatement ps = c.prepareStatement("select * from Admin");		
		ResultSet rs=ps.executeQuery();
               while(rs.next())
			{
				if(Uname.equals(rs.getString(1)) && Password.equals(rs.getString(2)))
				{
						
				   flag=1;
				   break;
				
				}
			}
			
			if(flag==0)
			{
				JOptionPane.showMessageDialog(null,"Invalid Username Or Password!!!","Alert Message",JOptionPane.WARNING_MESSAGE);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("Admin.html");
				dispatcher.include(req,res);
				
			}
			else
			{
				out.print("<p class='user_name'>");
					   out.print(Uname);
					   out.print("</p>");
				   
				   
										PreparedStatement ps1 = c.prepareStatement("show tables");  
              
											out.println("<select class='Tables1' >");

											
											ps1.executeQuery();
											ResultSet rs1=ps1.getResultSet();
											while(rs1.next())  
												{  
													out.print("<option>"+rs1.getString(1)+"</option>"); 							
												}  
											out.print("</select>");
					
											
  
				   
				   RequestDispatcher dispatcher = req.getRequestDispatcher("AdminInfo.html");
				   dispatcher.include(req,res);
				
			
			}
	
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