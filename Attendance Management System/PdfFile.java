import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

public class PdfFile extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)

               throws IOException {

              PrintWriter out = response.getWriter();

              response.setContentType("application/pdf");

              String filepath = "C:\\Attendance Management System\\10.pdf";

              response.setHeader("Content-Disposition", "inline; filename=’jsp.pdf'");

              FileOutputStream fileout = new FileOutputStream(filepath);

              fileout.close();

              out.close();

    }

 
}
