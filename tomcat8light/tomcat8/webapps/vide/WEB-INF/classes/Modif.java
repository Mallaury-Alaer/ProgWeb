import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Modif")
public class Modif extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		out.println("<head><title>Modification</title></head><body><center> ");

		Connection con = null;

		String hidden = req.getParameter("log");

		try{

			
			Class.forName("org.postgresql.Driver");
		    String url = "jdbc:postgresql://psqlserv/n3p1";
		    String nom = "alaerm";
		    String mdp = "moi";
		    con = DriverManager.getConnection(url,nom,mdp);
		    Statement stmt = con.createStatement(); 

		    HttpSession session = req.getSession( true );
		    String login = (String)session.getAttribute("login");

		    out.println("<H1>"+login+"</H1>");

		    String query = "select * from personne where login like '"+login+"';";
		    ResultSet rs = stmt.executeQuery(query);

		    out.println("<table border=1 class=\"table-centered table-hover table-condensed\">");
		    out.println("<TR><TH>Nom</TH><TH>Prenom</TH><TH>Adresse</TH><TH>Email</TH><TH>Telephone</TH><TH>Date de naissance</TH></TR>");

		    while(rs.next()){
		    	String name = rs.getString(3);
		    	String pre = rs.getString(4);
		    	String adr = rs.getString(5);
		    	String email = rs.getString(6);
		    	String tel = rs.getString(7);
		    	String ddn = rs.getString(8);

		    	out.println("<TR><TD>"+name+"</TD><TD>"+pre+"</TD><TD>"+adr+"</TD><TD>"+email+"</TD><TD>"+tel+"</TD><TD>"+ddn+"</TD></TR>");
		    }

		    out.println("<TR>");
		    out.println("<FORM Method=\"POST\" Action=\"Modifications\">");
		    out.println("<TD><INPUT type=\"text\" size=20 name=\"n\"</TD>");
		    out.println("<TD><INPUT type=\"text\" size=20 name=\"p\"</TD>");
		    out.println("<TD><INPUT type=\"text\" size=20 name=\"a\"</TD>");
		    out.println("<TD><INPUT type=\"text\" size=20 name=\"e\"</TD>");
		    out.println("<TD><INPUT type=\"text\" size=20 name=\"t\"</TD>");
		    out.println("<TD><INPUT type=\"text\" size=20 name=\"d\"</TD>");
		    out.println("</TR>");
		    out.println("</TABLE");
		    out.println("<INPUT type=\"hidden\" size=20 name=\"log\" value=\""+login+"\">");
		    out.println("<BR><INPUT type=\"submit\" value=\"Valider\"></BR>");
	   		out.println("</form>");

		    

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