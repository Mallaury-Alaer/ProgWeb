import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Synthese")
public class Synthese extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );

		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"../style.css\" />");
		out.println("<a href=ListerCTP>Lister</a>");
		out.println("<a href=../saisie.html>Inserer</a>");
		out.println("<a href=Synthese>Synthese</a>");
		out.println("<title>Table salles</title>");
		out.println("</head><body><center> ");
		out.println("<h1>Synthese:</h1>");
		
		Connection con = null;

		try{
			Class.forName("org.postgresql.Driver");
		    String url = "jdbc:postgresql://psqlserv/n3p1";
		    String nom = "alaerm";
		    String mdp = "moi";
		    con = DriverManager.getConnection(url,nom,mdp);
		    Statement stmt = con.createStatement();

		    String query = "select count(*),ip, min(dat), max(dat) from salles group by ip";
		    ResultSet rs = stmt.executeQuery(query);

		    out.println("<TABLE BORDER=\"1\">");
			out.println("<TR><TH>IP</TH><TH>Nb saisies</TH><TH>Date Min</TH><TH>Date Max</TH></TR>");

			while(rs.next()){
				String i = rs.getString(2);
				int n = rs.getInt(1);
				String mind = rs.getString(3);
				String maxd = rs.getString(4);

				out.println("<TR><TD><a href=ListerCTP?ip="+i+">"+i+"</a></TD><TD>"+n+"</TD><TD>"+mind+"</TD><TD>"+maxd+"</TD></TR>");
			}

			out.println("</TABLE>");
			out.println("</body></html>");

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