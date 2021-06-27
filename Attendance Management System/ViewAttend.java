import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
public class ViewAttend extends HttpServlet
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
		   //PreparedStatement ps = c.prepareStatement("select * from AttendanceSubject where SubjectName = ?");
             //ps.setString(1,FindSubj);
			  //int i = ps.executeUpdate(); 
			out.print("<p class='user_name'>");
			out.print(UN);
			out.println("</p>");
			out.print("<p class='findSubj'>");
			out.print(FindSubj);
			out.println("</p>");
					PreparedStatement ps1 = c.prepareStatement("Select * from "+FindSubj);
					out.println("<table class='ttableResult' >");
				    
					ps1.executeQuery();
					ResultSet rs = ps1.getResultSet();
					ResultSetMetaData rsmd = rs.getMetaData();  
												int total = rsmd.getColumnCount();  
												out.print("<tr>");  
												for(int j=1;j<=total;j++)  
												{  
													out.print("<th>"+rsmd.getColumnName(j)+"</th>");  
												}  
  
												out.print("</tr>");  
              
												/* Printing result */  
  
												while(rs.next())  
												{  
													out.print("<tr>");
													for(int j=1;j<=total;j++)
													{
														out.print("<td>"+rs.getString(j)+"</td>");
													}
													out.println("</tr>");
												}  
  
                    out.print("</table>");
				    
					RequestDispatcher dispatcher = req.getRequestDispatcher("ViewAttendance.html");
				   dispatcher.include(req,res);	
					
				   
					
		}
       		else  if(req.getParameter("addAtte")!=null)
		{
			out.print("<p class='user_name'>");
			out.print(UN);
			out.print("</p>");
			
			PreparedStatement ps = c.prepareStatement("select * from timetable");  
              
											out.print("<table width=80% border=1 class='ttable'>");  
											out.print("<caption>TimeTable:</caption>");  
  
											ResultSet rs=ps.executeQuery();  
              
										/* Printing column names */  
											ResultSetMetaData rsmd=rs.getMetaData();  
												int total=rsmd.getColumnCount();  
												out.print("<tr>");  
												for(int i=1;i<=total;i++)  
												{  
													out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
												}  
  
												out.print("</tr>");  
              
												/* Printing result */  
  
												while(rs.next())  
												{  
													out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td></tr>");  
                  
												}  
  
										out.print("</table>");  
    
			 RequestDispatcher dispatcher = req.getRequestDispatcher("MarkAttendance.html");
				   dispatcher.include(req,res);
		}
		else  if(req.getParameter("DownloadReport")!=null)
		{
		    PrintWriter pw = res.getWriter();
		   res.setContentType("application/vnd.ms-excel");
	res.setHeader("Content-Disposition","attachment; filename=Attendance.csv");
	
	pw.println("name \t hindi \t english \t math \t result");
	pw.println("himanshu \t 80 \t 80 \t 80 \t=sum(b2:d2)");
	
			 
		       
		/*PreparedStatement ps = c.prepareStatement("select * from "+FindSubj);  
              String colu = "";
											
										
												ResultSet rs=ps.executeQuery();  
              
											ResultSetMetaData rsmd=rs.getMetaData();  
												int total=rsmd.getColumnCount();  
												  
												pw.print(rsmd.getColumnName(1)+"\t");
												for(int i=2;i<total;i++)
												{
													pw.print(rsmd.getColumnName(i)+"\t");
												}
												pw.println(rsmd.getColumnName(total));
  
  
												while(rs.next())  
												{  
													for(int j=1;j<=total;j++)
													{
														if(j<total)
													  {
														if(j==2 || j==3)
														{
														  pw.print(rs.getString(j)+"\\");
													    }
														else
														{
														  pw.print(rs.getString(j)+"\\");

														}
													  }
													 else
														{
														  pw.print(rs.getString(j));

														}
													}
												}  
  */
			     RequestDispatcher dispatcher = req.getRequestDispatcher("home.html");
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