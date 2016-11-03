import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Authent")
public class Authent extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		out.println("<head><title>Authentification</title></head><body><center> ");

		Connection con = null;

		try{

			Class.forName("org.postgresql.Driver");
		    String url = "jdbc:postgresql://psqlserv/n3p1";
		    String nom = "alaerm";
		    String mdp = "moi";
		    con = DriverManager.getConnection(url,nom,mdp);
		    Statement stmt = con.createStatement(); 

		    String login = req.getParameter("log");
			String password = req.getParameter("pw");

			String query = "select * from personne where login like '"+login+"' and mdp like '"+password+"';";
		    ResultSet rs = stmt.executeQuery(query);

		    if(rs.next()){
		    	//out.println("Utilisateur connu du SGBD, mot de passe correct");

		    	HttpSession session = req.getSession( true );
		    	session.setAttribute("login", login);
		    	res.sendRedirect("../menu.html");

		    	/* Verification du mdp
		    	if(password.equals(rs.getString(2))){
		    		out.println("Utilisateur connu du SGBD, mot de passe correct");	
		    	}else{
		    		out.println("Mot de passe incorrect");
		    		out.println("<a href=menu>Retour a la page login</a>");
		    	}
		    	*/
		    	
		    }else{
		    	out.println("Utilisateur inconnu du SGBD, ");
		    	out.println("<a href=../login.html>Retour a la page login</a>");
		    }

		    out.println("</body></center>");

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