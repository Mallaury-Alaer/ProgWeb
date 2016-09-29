import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Ascii")
public class Ascii extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
    
		res.setContentType( "text/html" );
		String param=req.getParameter("nbCol");
		int nbcol;
		if(param==null){
			nbcol=1;
		}else{
			nbcol = Integer.parseInt(param);
		}

		out.println("<!doctype html>");
		out.println("<head><title>Table Ascii</title></head><body><center> ");
		out.println("<h1>Table Ascii</h1>");
		
		out.println("<TABLE BORDER=\"1\">");
		
		int i = 32;
		while(i<=255){
			int j=1;
			out.println("<TR>");
			while(j<=nbcol && i<=255){
				out.print("<TD>"+i+"</TD><TD>"+(char)i+"</TD> \n");
				i++;
				j++;
				
			}
			out.println("</TR>");
		}
		out.println("</TABLE>");
		
		
		out.println("</body></html> ");
	}
	
}