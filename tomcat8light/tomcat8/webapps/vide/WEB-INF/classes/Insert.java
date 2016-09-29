import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Insert")
public class Insert extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		out.println("<head><title>Ajouter client</title></head><body><center> ");
		
		String name = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		int age = Integer.parseInt(req.getParameter("age"));
		
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:postgreSQL35W";
			String nom = "alaerm";
			String mdp = "moi";
			Connection con = DriverManager.getConnection(url,nom,mdp);
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("insert into CLIENTS values('"+name+"', '"+prenom+"',"+age+")" );
			
			con.close();
			
			//out.println("<p>Client ajoute avec succes!</p>");
			//out.println("<p> Pour voir la liste des clients actualise : <a href=http://localhost:8080/vide/servlet/Select>Cliquez ici</a> </p>");
			out.println("</body></html> ");
			res.sendRedirect("Select");
			
		}catch(Exception e){
			out.println(e.getMessage());
		}
	}
}