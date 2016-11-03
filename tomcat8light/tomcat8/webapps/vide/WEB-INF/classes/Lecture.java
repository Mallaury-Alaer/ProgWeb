import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Lecture")
public class Lecture extends HttpServlet{
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
	
		out.println("<title>Lecture</title></head><body><center> ");

		Connection con = null;

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