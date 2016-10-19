import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Insert")
public class Insert extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!doctype html>");
		out.println("<head><title>Ajouter client</title></head><body><center> ");

		/*
		String name = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		int age = Integer.parseInt(req.getParameter("age"));
		*/
		
		String hidden = req.getParameter("tableR");
		
		try{
		    Class.forName("org.postgresql.Driver");
		    String url = "jdbc:postgresql://psqlserv/n3p1";
		    String nom = "alaerm";
		    String mdp = "moi";
		    Connection con = DriverManager.getConnection(url,nom,mdp);
		    
		    Statement stmt = con.createStatement();
		    String query = "select * from "+hidden;
		    ResultSet rs = stmt.executeQuery(query);
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int nbCols = rsmd.getColumnCount();
		    String inser = "insert into "+hidden+" values (";

		    for(int i=1; i<=nbCols; i++){
			if(i!=nbCols){
			    if(rsmd.getColumnName(i).equals("age") || rsmd.getColumnName(i).equals("id")){
				inser+="'"+Integer.parseInt(req.getParameter(rsmd.getColumnName(i)))+"',";
			    }else{
				inser+="'"+req.getParameter(rsmd.getColumnName(i))+"',";
			    }
			}else{
			     if(rsmd.getColumnName(i).equals("age") || rsmd.getColumnName(i).equals("id")){
				inser+="'"+Integer.parseInt(req.getParameter(rsmd.getColumnName(i)))+"')";
			    }else{
				 inser+="'"+req.getParameter(rsmd.getColumnName(i))+"')";
			    }
			}
		    }

		    stmt.executeUpdate(inser);
			
			
			//stmt.executeUpdate("insert into "+hidden+" values('"+name+"', '"+prenom+"',"+age+")" );
		    
		    con.close();
		    
		    //out.println("<p>Client ajoute avec succes!</p>");
		    //out.println("<p> Pour voir la liste des clients actualise : <a href=http://localhost:8080/vide/servlet/Select>Cliquez ici</a> </p>");
		    out.println("</body></html> ");
		    res.sendRedirect("Select?table="+hidden);
		    
		}catch(Exception e){
		    out.println(e.getMessage());
		}
	}
}
