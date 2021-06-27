import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class AdminCheckData extends HttpServlet
{
  public void init(ServletConfig h)
  {
	  
  }
 
  public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
      res.setContentType("text/html");	   
	   PrintWriter out = res.getWriter();
	    String CourseName="BVocWD";
		String UN = req.getParameter("adminName");
		
	   
	try
	{
		int flag=0;
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		  		
		if(req.getParameter("ChangeValues")!=null)
			{
					String Table1 = req.getParameter("seltable");
					String Field1 = req.getParameter("SelectedField");
					String OValue1 = req.getParameter("SelectedFieldoldValue");
					String NValue1 = req.getParameter("SelectedFieldnewValue");
					
					
					
			PreparedStatement ps2 = c.prepareStatement("update "+Table1+" set "+Field1+" = '"+NValue1+"' where "+Field1+" = '"+OValue1+"' ");
			      
           
		         int k = ps2.executeUpdate();
				 					out.println("<p class='error'>Data Updated!!!</p>");

				   out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
					out.println("<p class='selectedtable'>"+Table1+"</p>");
				 RequestDispatcher dispatcher = req.getRequestDispatcher("AdminInfo.html");
				  dispatcher.include(req,res);	
					
				
			}
			else if(req.getParameter("DropField")!=null)
			{
					String Table1 = req.getParameter("seltable");
					String Field1 = req.getParameter("SelectedField1");
					
				
					
			PreparedStatement ps3 = c.prepareStatement("Alter table "+Table1+" drop "+Field1);
			      
           
		         int k = ps3.executeUpdate();
				   out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
					
					out.println("<p class='error'>Data Updated!!!</p>");
					out.println("<p class='selectedtable'>"+Table1+"</p>");
				 RequestDispatcher dispatcher = req.getRequestDispatcher("AdminInfo.html");
				  dispatcher.include(req,res);	
					
				
			}
			else if(req.getParameter("selTable")!=null)
			{
		   
				 String Table = req.getParameter("selectedTable");
	    
	             PreparedStatement ps1 = c.prepareStatement("select * from "+Table);
			      
				  ResultSet rs1 = ps1.executeQuery();
				  out.print("<table width=80% border=1 class='ttable'>");  
											
              
										/* Printing column names */  
											ResultSetMetaData rsmd1=rs1.getMetaData();  
												int total=rsmd1.getColumnCount();  
												out.print("<tr>");  
												for(int i=1;i<=total;i++)  
												{  
													out.print("<th>"+rsmd1.getColumnName(i)+"</th>");  
												}  
  
												out.print("</tr>");  
              
												/* Printing result */  
  
												while(rs1.next())  
												{  
													out.print("<tr>"); 
                                                      for(int j=1;j<=total;j++)
													  {
													    out.print("<td>"+rs1.getString(j)+"</td>");
													  }														  
											        out.println("</tr>");
												}  
  
										out.println("</table>"); 
										out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
										out.println("<p class='selectedtable'>"+Table+"</p>");
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