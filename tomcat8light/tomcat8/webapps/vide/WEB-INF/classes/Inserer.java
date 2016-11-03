import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/servlet/Inserer")
public class Inserer extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		
		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
		    String url = "jdbc:postgresql://psqlserv/n3p1";
		    String nom = "alaerm";
		    String mdp = "moi";
		    con = DriverManager.getConnection(url,nom,mdp);
		    Statement stmt = con.createStatement(); 

		    String name = req.getParameter("nom");
		    String size = req.getParameter("taille");
		    String chair = req.getParameter("chaises");
		    String door = req.getParameter("portes");
		    String window = req.getParameter("fenetres");
		    String ip = InetAddress.getLocalHost ().getHostAddress ();

		    String format = "yyyy-MM-dd hh:mm:ss:SS";
		    SimpleDateFormat fmt = new SimpleDateFormat(format);
		    Date date = new Date();


		    String query = "select * from salles";
		    ResultSet rs = stmt.executeQuery(query);
		    String insert = "insert into salles values ('"+name+"','"+size+"','"+chair+"','"+door+"','"+window+"','"+ip+"','"+fmt.format(date)+"')";

		    stmt.executeUpdate(insert);

		    res.sendRedirect("ListerCTP");

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
