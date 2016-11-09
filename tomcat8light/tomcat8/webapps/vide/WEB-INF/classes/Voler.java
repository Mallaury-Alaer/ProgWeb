import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Voler")
public class Voler extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		
		String p = req.getParameter("p");
		String inser = "";

		Connection con = null;

		try{

			Class.forName("org.postgresql.Driver");
		    con = DriverManager.getConnection("jdbc:postgresql://psqlserv/n3p1","alaerm","moi");
		    Statement stmt = con.createStatement();

		    inser = "insert into numsession values ('"+p+"');";
		    stmt.executeUpdate(inser);

		    res.sendRedirect("http://localhost:8080/chauny02/servlet/Menu");

		}catch(Exception e){
			out.println(e.getMessage());
		}finally{
			try{
				con.close();
			}catch(Exception e){
				out.println(e.getMessage());
			}
		}
	}
}