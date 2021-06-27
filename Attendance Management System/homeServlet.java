import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class homeServlet extends HttpServlet
{
 
  public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
        res.setContentType("text/html");	   
	    PrintWriter out = res.getWriter();
	    String CourseName="BVocWD";
		String UN = req.getParameter("selected_name");
	   		
	 
	try
	{
		int flag=0;
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		 
				if(req.getParameter("StudRegistration")!=null)
				{
					out.print("<p class='user_name'>");
				   out.print(UN);
				   out.print("</p>");
				   RequestDispatcher dispatcher = req.getRequestDispatcher("StudentRegistration.html");
				   dispatcher.include(req,res);
				   flag=1;
				
				}
			    else if(req.getParameter("teacherRegistration")!=null)
				{
					out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
				   //PreparedStatement ps=c.prepareStatement("Select Count(SubjectName) from subjectteacher");  
				   
					//int total =ps.executeQuery();  
                     //int i=1;
					 out.println("<select class='Subjects' >");

					PreparedStatement ps = c.prepareStatement("Select SubjectName from subjectteacher");
				    
					ps.executeQuery();
					ResultSet rs=ps.getResultSet();
						while(rs.next())  
						{  
							out.print("<option>"+rs.getString(1)+"</option>"); 							
						}  
                    out.print("</select>");
				   				   				   
				   RequestDispatcher dispatcher = req.getRequestDispatcher("TeacherRegistration.html");
				   dispatcher.include(req,res);
				   flag=1;
				
				}
				else if(req.getParameter("SubRegistration")!=null)
				{
				   out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
				   RequestDispatcher dispatcher = req.getRequestDispatcher("AddSubject.html");
				   dispatcher.include(req,res);
				   flag=1;
				
				}
				else if(req.getParameter("ttRegistration")!=null)
				{
					out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
				   
				   out.println("<select class='Subjects' >");

					PreparedStatement ps = c.prepareStatement("Select SubjectName from subjectteacher");
				    
					ps.executeQuery();
					ResultSet rs=ps.getResultSet();
						while(rs.next())  
						{  
							out.print("<option>"+rs.getString(1)+"</option>"); 							
						}  
                    out.print("</select>");
					
					 out.println("<select class='Subjects_teachers' >");

					PreparedStatement ps1 = c.prepareStatement("Select * from subjectteacher");
				    
					ps1.executeQuery();
					ResultSet rs1=ps1.getResultSet();
						while(rs1.next())  
						{  
							out.print("<option>"+rs1.getString(1)+" , "+rs1.getString(2)+"</option>"); 							
						}  
                    out.print("</select>");
					
					
					
				   RequestDispatcher dispatcher = req.getRequestDispatcher("tt.html");
				   dispatcher.include(req,res);
				   flag=1;
				
				}
			else if(req.getParameter("markAttendance")!=null)
				{
					out.print("<p class='user_name' >");
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
				   flag=1;
				
				}
				else if(req.getParameter("viewStudent")!=null)
				{
					out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
				   
					  RequestDispatcher dispatcher = req.getRequestDispatcher("ViewStudent.html");
				   dispatcher.include(req,res);
				}
				else if(req.getParameter("viewTeacher")!=null)
				{
					out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
				   
					  RequestDispatcher dispatcher = req.getRequestDispatcher("ViewTeacher.html");
				   dispatcher.include(req,res);
				}
				
				else if(req.getParameter("viewSubject")!=null)
				{
					out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
					  RequestDispatcher dispatcher = req.getRequestDispatcher("ViewSubject.html");
				   dispatcher.include(req,res);
				}
				else if(req.getParameter("viewAttend")!=null)
				{
					out.print("<p class='user_name' >");
				   out.print(UN);
				   out.print("</p>");
					  RequestDispatcher dispatcher = req.getRequestDispatcher("ViewAttendance.html");
				   dispatcher.include(req,res);
				}
				
			else if(req.getParameter("logout")!=null)
				{
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
	
	
}