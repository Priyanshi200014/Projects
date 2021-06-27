import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Excelrun extends HttpServlet
{
  public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
  {
	  PrintWriter pw = res.getWriter();
	res.setContentType("application/vnd.ms-excel");
	res.setHeader("Content-Disposition","attachment; filename=demo.csv");
	
	pw.print("name");
	pw.println("himanshu");
	pw.print("class");
	pw.println("second");
	
	
  }
}