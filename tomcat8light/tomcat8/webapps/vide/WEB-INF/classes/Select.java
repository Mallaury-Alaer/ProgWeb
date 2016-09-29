import java.sql.*;
import java.util.Properties;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Select")
public class Select extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		out.println("<head><title>Table clients</title></head><body><center> ");
		out.println("<h1>Liste des clients:</h1>");
		
		try{
			Connection con=null;
			Statement stmt;
		
			// enregistrement du driver
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
			// connexion à la base
			String url = "jdbc:odbc:postgreSQL35W";
			String nom = "alaerm";
			String mdp = "moi";
			con = DriverManager.getConnection(url,nom,mdp);
		
			stmt = con.createStatement();
			String query = "select NOM,PRENOM,AGE from CLIENTS";
			ResultSet rs = stmt.executeQuery(query);
		
			out.println("<TABLE BORDER=\"1\">");
			out.println("<TR><TH>Nom</TH><TH>Prenom</TH><TH>Age</TH></TR>");
		
			while(rs.next()){
				out.println("<TR>");
				out.println("<TD>"+rs.getString("nom")+"</TD><TD>"+rs.getString("prenom")+"</TD><TD>"+rs.getInt("age")+"</TD>");
				out.println("</TR>");
			}
			
			con.close();
			out.println("<TR>");
			out.println("<FORM Method=\"POST\" Action=\"http://localhost:8080/vide/servlet/Insert\">");
			out.println("<TD><INPUT type=\"text\" size=20 name=\"nom\"></TD>");
			out.println("<TD><INPUT type=\"text\" size=20 name=\"prenom\"></TD>");
			out.println("<TD><INPUT type=\"text\" size=2 name=\"age\"></TD>");
			
			out.println("<TR>");
			
			out.println("</TABLE>");
			out.println("<BR><INPUT type=\"submit\" value=\"Valider\">");
			out.println("</form>");
			out.println("</body></html> ");
		
		}catch(Exception e){
			out.println(e.getMessage());
		}
		
	}
}