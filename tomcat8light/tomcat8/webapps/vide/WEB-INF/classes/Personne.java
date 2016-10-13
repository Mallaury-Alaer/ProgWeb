import java.sql.*;
import java.util.Properties;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Personne")
public class Personne extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">");
		out.println("<title>Personnes</title>");
		out.println("</head><body><center> ");
		out.println("<h1>Personnes:</h1>");
		
		try{
			Connection con=null;
			Statement stmt;
		
			// enregistrement du driver
			Class.forName("org.postgresql.Driver");
		
			// connexion à la base
			String url = "jdbc:postgresql://psqlserv/n3p1";
			String nom = "alaerm";
			String mdp = "moi";
			con = DriverManager.getConnection(url,nom,mdp);
		
			stmt = con.createStatement();
			String query = "select ID,NOM,PRENOM from PERSONNE";
			ResultSet rs = stmt.executeQuery(query);
		
			out.println("<table border=1 class=\"table-centered table-hover table-condensed\">");
			out.println("<TR><TH>ID</TH><TH>Nom</TH><TH>Prenom</TH></TR>");
		
			while(rs.next()){
				out.println("<TR>");
				out.println("<TD>"+rs.getInt("id")+"</TD><TD>"+rs.getString("nom")+"</TD><TD>"+rs.getString("prenom")+"</TD>");
				out.println("</TR>");
			}
			
			 con.close();
			
			out.println("</TABLE>");
			out.println("</body></html> ");
		
		}catch(Exception e){
			out.println(e.getMessage());
		}
		
	}
}
