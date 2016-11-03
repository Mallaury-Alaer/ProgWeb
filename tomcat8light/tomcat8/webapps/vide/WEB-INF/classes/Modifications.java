import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Modifications")
public class Modifications extends HttpServlet{
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
		    String query = "select * from personne where login like '"+hidden+";'";
		    ResultSet rs = stmt.executeQuery(query);


		    out.println("<h1>Modifications</h1>");

		    String na = req.getParameter("n");
			String pr = req.getParameter("p");
			String ad = req.getParameter("a");
			String em = req.getParameter("e");
			String te = req.getParameter("t");
			String da = req.getParameter("d");

			if(na != ""){
				stmt.executeUpdate("update personne set nom = '"+na+"';");
			}if(pr != ""){
				stmt.executeUpdate("update personne set prenom = '"+pr+"';");
			}if(ad != ""){
				stmt.executeUpdate("update personne set adresse = '"+ad+"';");
			}if(em != ""){
				stmt.executeUpdate("update personne set email = '"+em+"';");
			}if(te != ""){
				stmt.executeUpdate("update personne set tel = '"+te+"';");
			}if(da != ""){
				stmt.executeUpdate("update personne set datenaiss = '"+da+"';");
			}

			res.sendRedirect("Modif");
		    

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