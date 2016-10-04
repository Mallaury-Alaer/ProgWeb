import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/ListerA")
public class ListerA extends HttpServlet{
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
			
			
			String query = "select * from resultats ";
			String param=req.getParameter("tri");
			if(param!=null){
				query=query+"order by \""+param+"\"";
			}
			
			String sens = req.getParameter("sens");
			String inverse="asc";
			if(sens != null){
				if(sens.equals("asc")){
					inverse="desc";
				}else if(sens.equals("desc")){
					inverse="asc";
				}
				query=query+" "+inverse;
			}
			
			
			
			ResultSet rs = stmt.executeQuery(query);
			
			out.println("<TABLE BORDER=\"1\">");
			out.println("<TR><TH><a href= http://localhost:8080/vide/servlet/ListerA?tri=nom&sens="+inverse+"> Nom </a></TH>");
			out.println("<TH><a href= http://localhost:8080/vide/servlet/ListerA?tri=annee&sens="+inverse+"> Annee </a></TH>");
			out.println("<TH><a href= http://localhost:8080/vide/servlet/ListerA?tri=nationalite&sens="+inverse+"> Nationalite </a></TH>");
			out.println("<TH><a href= http://localhost:8080/vide/servlet/ListerA?tri=categ&sens="+inverse+"> Categorie </a></TH>");
			out.println("<TH><a href= http://localhost:8080/vide/servlet/ListerA?tri=club&sens="+inverse+"> Club </a></TH>");
			out.println("<TH><a href= http://localhost:8080/vide/servlet/ListerA?tri=temps&sens="+inverse+"> Temps </a></TH></TR>");
			
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