import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/ListerCTP")
public class ListerCTP extends HttpServlet{
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
		out.println("<h1>Liste des salles:</h1>");
		
		Connection con = null;
		
		try{

			Class.forName("org.postgresql.Driver");
		    String url = "jdbc:postgresql://psqlserv/n3p1";
		    String nom = "alaerm";
		    String mdp = "moi";
		    con = DriverManager.getConnection(url,nom,mdp);
		    Statement stmt = con.createStatement(); 

		    String param=req.getParameter("ip");
		    String cond="";

		    if(param != null){
		    	cond = " where ip like '"+param+"';";
		    }

		    String query = "select * from salles"+cond;
		    ResultSet rs = stmt.executeQuery(query);

		    out.println("<TABLE BORDER=\"1\">");
			out.println("<TR><TH>Nom</TH><TH>Taille</TH><TH>Chaises</TH><TH>Portes</TH><TH>Fenetres</TH><TH>IP</TH><TH>Date</TH></TR>");

			while(rs.next()){
				String n = rs.getString(1);
				String t = rs.getString(2);
				String c = rs.getString(3);
				String p = rs.getString(4);
				String f = rs.getString(5);
				String i = rs.getString(6);
				String d = rs.getString(7);

				out.println("<TR><TD>"+n+"</TD><TD>"+t+"</TD><TD>"+c+"</TD><TD>"+p+"</TD><TD>"+f+"</TD><TD>"+i+"</TD><TD>"+d+"</TD></TR>");
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