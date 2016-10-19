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
		String param = req.getParameter("table");
		String table;
		if(param == null){
		    table = "clients";
		}else{
		    table = param;
		}
		
		out.println("<!doctype html>");
		out.println("<head><title>Table "+ table +"</title></head><body><center> ");
		out.println("<h1>Contenu de la table : "+ table +"</h1>");
		
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
			String query = "select * from "+ table;
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int nbCols = rsmd.getColumnCount();
			
			out.println("<TABLE BORDER=\"1\">");

			out.println("<TR>");
			for(int i=1; i<=nbCols; i++){
			    out.println("<TH>"+rsmd.getColumnName(i)+"</TH>");
			}
			out.println("</TR");
			
			while(rs.next()){
				out.println("<TR>");
				for(int i=1; i<=nbCols; i++){
				    out.println("<TD>"+rs.getString(rsmd.getColumnName(i))+"</TD>");
				}
				//out.println("<TD>"+rs.getString("nom")+"</TD><TD>"+rs.getString("prenom")+"</TD><TD>"+rs.getInt("age")+"</TD>");
				out.println("</TR>");
			}
			
			con.close();
			/* FORMULAIRE PIED DE TABLEAU */
			out.println("<TR>");
			out.println("<FORM Method=\"POST\" Action=\"http://localhost:8080/vide/servlet/Insert\">");

			out.println("<TR>");
			
			for(int i=1; i<=nbCols; i++){
			    out.println("<TD><INPUT type=\"text\" size=20 name=\""+rsmd.getColumnName(i)+"\"></TD>");
			}
			
			out.println("</TR>");
			
			
			
			out.println("</TABLE>");
			
			out.println("<BR><INPUT type=\"submit\" value=\"Valider\"></BR>");
			out.println("<INPUT type=\"hidden\" size=20 name=\"tableR\" value=\""+table+"\">");
			out.println("</form>");
			
			out.println("</body></html> ");
		
		}catch(Exception e){
			out.println(e.getMessage());
		}
		
	}
}
