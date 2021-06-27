import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class StudentLogin extends HttpServlet
{
  public void init(ServletConfig h)
  {
	  
  }
 
  public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
      res.setContentType("text/html");	   
	   PrintWriter out = res.getWriter();
	    String CourseName="BVocWD";
				
	    String Email = req.getParameter("Email");
	    String Password = req.getParameter("password");
	    
	    HttpSession session = req.getSession();
	    session.setAttribute("user",Email);
	
	try
	{
		int flag=0;
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		  		
		PreparedStatement ps = c.prepareStatement("select Name,Email,Password from Users");		
		ResultSet rs=ps.executeQuery();
               while(rs.next())
			{
				if(Email.equals(rs.getString(2)) && Password.equals(rs.getString(3)))
				{
					out.print("<p class='user_name'>");
				   out.print(rs.getString(1));
				   out.print("</p>");
				   RequestDispatcher dispatcher = req.getRequestDispatcher("home.html");
				   dispatcher.include(req,res);
				   flag=1;
				   break;
				
				}
			}
			
			if(flag==0)
			{
				JOptionPane.showMessageDialog(null,"Invalid Username Or Password!!!","Alert Message",JOptionPane.WARNING_MESSAGE);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("Login.html");
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