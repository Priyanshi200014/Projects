import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.Format;

public class MarkAttend extends HttpServlet
{
 
  public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
   {
        res.setContentType("text/html");	   
	    PrintWriter out = res.getWriter();
		String UN = req.getParameter("selected_name");
	    String CourseName="BVocWD";
		String subject = req.getParameter("selected_subject");
		String day1 = req.getParameter("selected_subject_day");
		
		String period = req.getParameter("selected_subject_period");
		
		
	   	 Date d = new Date();
			int day = d.getDate();
			int month = (d.getMonth()+1);
			int year =  (d.getYear() % 100)+2000;
           SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); 
        String currentday = simpleDateformat.format(d);	
		
		System.out.println("Current day = "+currentday+"  Selected day = "+day1);
	      String date = day+"-"+month+"-"+year;
		 
	try
	{
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		 
		 
		 if(currentday.equals(day1))
		 {
		  PreparedStatement ps=c.prepareStatement("Select TeacherName from subjectteacher where SubjectName = ?");
		  ps.setString(1,subject);
		  ResultSet rs1=ps.executeQuery();
		  out.print("<p class='teacherName'>");
		   while(rs1.next())
		  {
			  out.print(rs1.getString(1));
			  
		  }
          out.println("</p>");
				   
			       out.print("<p class='subj'>");
				   out.print(subject);
				   out.println("</p>");
				   
				     out.print("<br><p class='user_name'>");
				   out.print(UN);
				   out.println("</p>");
				   
				    out.print("<p class='day'>");
				   out.print(day1);
				   out.println("</p>"); 
				   
				   out.print("<p class='per'>");
				   out.print(period);
				   out.println("</p>");
				   
				
				    
				   out.print("<br><p class='cur_date'>");
				   out.print(date);
				   out.println("</p>");
				   
				 
				 
				   
				   out.println("<table class='Stud_List' >");

					 ps = c.prepareStatement("Select Name,RollNo,DOB from studentslist");
				    
					ps.executeQuery();
					ResultSet rs=ps.getResultSet();
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
													out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");  
                  
												}  
  
                    out.print("</table>");
				   
				   
				   RequestDispatcher dispatcher = req.getRequestDispatcher("MarkAttendanceNext.html");
				   dispatcher.include(req,res);	
					
		 }
		 else
		 {
			  out.print("<br><p class='error'>");
				   out.print("Today is not "+day1+" you can submit attendance record on the same day only.");
				   out.println("</p>");
				   
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
			 
		 }
         	
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	   out.close();
	}	
	
	
}