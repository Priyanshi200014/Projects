import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class TimeTable implements Servlet
{
  public void init(ServletConfig h)
  {
	  
  }
 
   public void service(ServletRequest req,ServletResponse res)throws ServletException, IOException
   {
      res.setContentType("text/html");	   
	   PrintWriter pw = res.getWriter();
	   
		//String CourseName = "BVocWD";
		
		String CourseName = req.getParameter("course_name");
		/*if(CourseName.equals("B.Voc(Web Designing)"))
   {
     CourseName="BVocWD";
   }
   else if(CourseName.equals("B.Voc(Printing Technology)"))
   {
	   
     CourseName="BVocPT";
   }
   else if(CourseName.equals("B.Sc(H) Chemistry"))
   {
     CourseName="BScChem";
   }
   else if(CourseName.equals("B.Sc(H) Physics"))
   {
     CourseName="BScPhy";
   }
   else if(CourseName.equals("B.Sc(H) Botany"))
   {
     CourseName="BScBot";
   }
   else if(CourseName.equals("B.Sc(H) Computer Science"))
   {
     CourseName="BScCS";
   }
   else if(CourseName.equals("B.A. (Hons.) Political Science"))
   {
     CourseName="BaPS";
   }
   else if(CourseName.equals("B.Com"))
   {
     CourseName="BCom";
   }
   else if(CourseName.equals("B.A. (Hons.) Geography"))
   {
     CourseName="BaGeo";
   }
   else if(CourseName.equals("B.A. (Hons.) History"))
   {
     CourseName="BaHistory";
   }
   else if(CourseName.equals("B.A. (Hons.) Hindi"))
   {
     CourseName="BaHindi";
   }
   else if(CourseName.equals("B.A. (Hons.) English"))
   {
     CourseName="BaEng";
   }
   else if(CourseName.equals("B.A. (Hons.) Journalism"))
   {
     CourseName="BaJour";
   }
   else if(CourseName.equals("B.A. (Hons.) Sanskrit"))
   {
     CourseName="BaSans";
   }*/
   
		String day1 = "Monday";
		String day2 = "Tuesday";
		String day3 = "Wednesday";
		String day4 = "Thursday";
		String day5 = "Friday";
		String day6 = "Saturday";
		
			
		
		String subjectSlotI1 = req.getParameter("slotI1_subject");
		String subjectSlotII1 = req.getParameter("slotII1_subject");
		String subjectSlotIII1 = req.getParameter("slotIII1_subject");
		String subjectSlotIV1 = req.getParameter("slotIV1_subject");
		String subjectSlotV1 = req.getParameter("slotV1_subject");;
		String subjectSlotVI1 = req.getParameter("slotVI1_subject");
		String subjectSlotVII1 = req.getParameter("slotVII1_subject");
		String subjectSlotVIII1 = req.getParameter("slotVIII1_subject");
		
		String subjectSlotI2 = req.getParameter("slotI2_subject");
		String subjectSlotII2 = req.getParameter("slotII2_subject");
		String subjectSlotIII2 = req.getParameter("slotIII2_subject");
		String subjectSlotIV2 = req.getParameter("slotIV2_subject");
		String subjectSlotV2 = req.getParameter("slotV2_subject");
		String subjectSlotVI2 = req.getParameter("slotVI2_subject");
		String subjectSlotVII2 = req.getParameter("slotVII2_subject");
		String subjectSlotVIII2 = req.getParameter("slotVIII2_subject");
		
		String subjectSlotI3 = req.getParameter("slotI3_subject");
		String subjectSlotII3 = req.getParameter("slotII3_subject");
		String subjectSlotIII3 = req.getParameter("slotIII3_subject");
		String subjectSlotIV3 = req.getParameter("slotIV3_subject");
		String subjectSlotV3 = req.getParameter("slotV3_subject");
		String subjectSlotVI3 = req.getParameter("slotVI3_subject");
		String subjectSlotVII3 = req.getParameter("slotVII3_subject");
		String subjectSlotVIII3 = req.getParameter("slotVIII3_subject");
		
		String subjectSlotI4 = req.getParameter("slotI4_subject");
		String subjectSlotII4 = req.getParameter("slotII4_subject");
		String subjectSlotIII4 = req.getParameter("slotIII4_subject");
		String subjectSlotIV4 = req.getParameter("slotIV4_subject");
		String subjectSlotV4 = req.getParameter("slotV4_subject");
		String subjectSlotVI4 = req.getParameter("slotVI4_subject");
		String subjectSlotVII4 = req.getParameter("slotVII4_subject");
		String subjectSlotVIII4 = req.getParameter("slotVIII4_subject");
		
		String subjectSlotI5 = req.getParameter("slotI5_subject");
		String subjectSlotII5 = req.getParameter("slotII5_subject");
		String subjectSlotIII5 = req.getParameter("slotIII5_subject");
		String subjectSlotIV5 = req.getParameter("slotIV5_subject");
		String subjectSlotV5 = req.getParameter("slotV5_subject");
		String subjectSlotVI5 = req.getParameter("slotVI5_subject");
		String subjectSlotVII5 = req.getParameter("slotVII5_subject");
		String subjectSlotVIII5 = req.getParameter("slotVIII5_subject");
		
		String subjectSlotI6 = req.getParameter("slotI6_subject");
		String subjectSlotII6 = req.getParameter("slotII6_subject");
		String subjectSlotIII6 = req.getParameter("slotIII6_subject");
		String subjectSlotIV6 = req.getParameter("slotIV6_subject");
		String subjectSlotV6 = req.getParameter("slotV6_subject");
		String subjectSlotVI6 = req.getParameter("slotVI6_subject");
		String subjectSlotVII6 = req.getParameter("slotVII6_subject");
		String subjectSlotVIII6 = req.getParameter("slotVIII6_subject");
	   
	  try
	  {
     
	
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection c = DriverManager.getConnection("jdbc:mysql://localhost/"+CourseName,"Priyanshi","root");
		  
		
		Statement s = c.createStatement();
		s.executeUpdate("create table TimeTable(DaysName varchar(35),I varchar(20),II varchar(20),III varchar(20),IV varchar(20),V varchar(20),VI varchar(20),VII varchar(20),VIII varchar(20))");
		s.close();
	
		PreparedStatement ps = c.prepareStatement("insert into TimeTable values(?,?,?,?,?,?,?,?,?)");
		
		ps.setString(1,day1);
		ps.setString(2,subjectSlotI1);
		ps.setString(3,subjectSlotII1);
		ps.setString(4,subjectSlotIII1);
		ps.setString(5,subjectSlotIV1);
		ps.setString(6,subjectSlotV1);
		ps.setString(7,subjectSlotVI1);
		ps.setString(8,subjectSlotVII1);
		ps.setString(9,subjectSlotVIII1);
		ps.executeUpdate();
	
		
		ps.setString(1,day2);
		ps.setString(2,subjectSlotI2);
		ps.setString(3,subjectSlotII2);
		ps.setString(4,subjectSlotIII2);
		ps.setString(5,subjectSlotIV2);
		ps.setString(6,subjectSlotV2);
		ps.setString(7,subjectSlotVI2);
		ps.setString(8,subjectSlotVII2);
		ps.setString(9,subjectSlotVIII2);
		ps.executeUpdate();
		  
		  
		
		ps.setString(1,day3);
		ps.setString(2,subjectSlotI3);
		ps.setString(3,subjectSlotII3);
		ps.setString(4,subjectSlotIII3);
		ps.setString(5,subjectSlotIV3);
		ps.setString(6,subjectSlotV3);
		ps.setString(7,subjectSlotVI3);
		ps.setString(8,subjectSlotVII3);
		ps.setString(9,subjectSlotVIII3);
		ps.executeUpdate();
		  
		 
		ps.setString(1,day4);
		ps.setString(2,subjectSlotI4);
		ps.setString(3,subjectSlotII4);
		ps.setString(4,subjectSlotIII4);
		ps.setString(5,subjectSlotIV4);
		ps.setString(6,subjectSlotV4);
		ps.setString(7,subjectSlotVI4);
		ps.setString(8,subjectSlotVII4);
		ps.setString(9,subjectSlotVIII4);
		ps.executeUpdate();
		  
	
		ps.setString(1,day5);
		ps.setString(2,subjectSlotI5);
		ps.setString(3,subjectSlotII5);
		ps.setString(4,subjectSlotIII5);
		ps.setString(5,subjectSlotIV5);
		ps.setString(6,subjectSlotV5);
		ps.setString(7,subjectSlotVI5);
		ps.setString(8,subjectSlotVII5);
		ps.setString(9,subjectSlotVIII5);
		ps.executeUpdate();
		 
		 
		ps.setString(1,day6);
		ps.setString(2,subjectSlotI6);
		ps.setString(3,subjectSlotII6);
		ps.setString(4,subjectSlotIII6);
		ps.setString(5,subjectSlotIV6);
		ps.setString(6,subjectSlotV6);
		ps.setString(7,subjectSlotVI6);
		ps.setString(8,subjectSlotVII6);
		ps.setString(9,subjectSlotVIII6);
		ps.executeUpdate();
		
		 ps=c.prepareStatement("Select * from TimeTable where DaysName = ?");
		  ps.setString(1,day6);
		  ResultSet rs=ps.executeQuery();
		  
		   while(rs.next())
		  {
			  pw.println("I = "+rs.getString(2)+"  II = "+rs.getString(3)+"  III = "+rs.getString(4)+"  IV = "+rs.getString(5)+"  V = "+rs.getString(6)+"  VI = "+rs.getString(7)+"  VII = "+rs.getString(8)+"  VIII = "+rs.getString(9));
			  
		  }
		   
         RequestDispatcher dispatcher = req.getRequestDispatcher("home.html");
         dispatcher.include(req,res);		
		   pw.println("Hey  you are successfully registered!!!");	
	  
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