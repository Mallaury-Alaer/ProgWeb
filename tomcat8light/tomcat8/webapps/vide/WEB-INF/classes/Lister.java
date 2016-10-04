import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Lister")
public class Lister extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		Connection con=null;
		
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		out.println("<head><title>Table resultats</title></head><body><center> ");
		out.println("<h1>Liste des resultats:</h1>");
		
		try{
			String url = "jdbc:odbc:tp5";
			con = DriverManager.getConnection(url, "alaerm", "moi");
			Statement stmt;
			stmt = con.createStatement();
			
			
			String query = "select * from resultats";
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			out.println("<TABLE BORDER=\"1\">");
			out.println("<TR><TH>Nom</TH><TH>Annee</TH><TH>Nationalite</TH><TH>Categorie</TH><TH>Club</TH><TH>Temps</TH></TR>");
			
			while(rs.next()){
				String nom = rs.getString(1);
				int annee = rs.getInt(2);
				String nat = rs.getString(3);
				String cat = rs.getString(4);
				String club = rs.getString(5);
				int tps = rs.getInt(6);
				
				out.println("<TR><TD>"+nom+"</TD><TD>"+annee+"</TD><TD>"+nat+"</TD><TD>"+cat+"</TD><TD>"+club+"</TD><TD>"+tps+"</TD></TR>");
			}
			con.close();
			out.println("</TABLE>");
			out.println("</body></html>");
		}catch(Exception e){
			out.println(e.getMessage());
		}
	}
}