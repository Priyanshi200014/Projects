import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class ViewSubj extends HttpServlet
{
 
  public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
	   
        res.setContentType("text/html");	   
	    PrintWriter out = res.getWriter();
		String UN = req.getParameter("selected_name");
		
	    String CourseName="BVocWD";
		
		String FindSubj = req.getParameter("searchSubj");
		
		
		 
	try
	{
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		  
		  
		  
		  if(req.getParameter("findSubj")!=null)
		{
           PreparedStatement ps = c.prepareStatement("select * from subjectteacher where SubjectName = ?");
              ps.setString(1,FindSubj);
			   
  
											ResultSet rs=ps.executeQuery();  
                           
											 if (rs.next() == false)
											 {
												 out.println("<p class='errorSubj'>Subject does not exist</p>");
												 RequestDispatcher dispatcher = req.getRequestDispatcher("ViewTeacher.html");
													dispatcher.include(req,res);
											 }
										else
										{
											/* Printing column names */ 
											out.println("<p class='findSubj'>"+FindSubj+"</p>");
											out.print("<table width=80% border=1 class='ttableResult'>");  
											out.print("<caption>TimeTable:</caption>");											
											ResultSetMetaData rsmd=rs.getMetaData();  
												int total=rsmd.getColumnCount();  
												out.print("<tr>");  
												for(int i=1;i<=total;i++)  
												{  
													out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
												}  
  
												out.print("</tr>");  
              
												/* Printing result */  
  
												do
												{  
													out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>");  
                  
												}  while(rs.next());  
  
										out.print("</table>");  
			
			out.print("<p class='user_name'>");
			out.print(UN);
			out.print("</p>");
			 RequestDispatcher dispatcher = req.getRequestDispatcher("ViewSubject.html");
				   dispatcher.include(req,res);
										}
		}
		else  if(req.getParameter("addSubj")!=null)
		{
			out.print("<p class='user_name'>");
			out.print(UN);
			out.print("</p>");
			 RequestDispatcher dispatcher = req.getRequestDispatcher("AddSubject.html");
				   dispatcher.include(req,res);
		}
		
         	
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	   out.close();
	}	
	
	
}