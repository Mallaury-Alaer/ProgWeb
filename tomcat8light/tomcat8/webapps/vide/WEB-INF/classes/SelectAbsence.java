import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/SelectAbsence")
public class SelectAbsence extends HttpServlet 
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
    	PrintWriter out = res.getWriter();
		res.setContentType("text/html");
	
		/* NE PAS MODIFIER (Sauf indication)*/
		out.println("<!DOCTYPE html><html lang='fr'>");
		out.println("<head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'>");
	
			/* Titre de la page HTML */
		out.println("<title>Menu</title>");
			/* **************** */
	
		out.println("<!-- Bootstrap core CSS --><link href='//getbootstrap.com/dist/css/bootstrap.min.css' rel='stylesheet'>");
	
		out.println("</head>");
		out.println("<body>");
	
		// authentifie ?
		HttpSession session = req.getSession(true);
		Personnep p = (Personnep)session.getAttribute("login");
		if (p==null) res.sendRedirect("Deconnect");

		Connection con = null;
		try{

			// enregistrement du driver
	    	Class.forName("org.postgresql.Driver");
	    
	    	// connexion a la base
	    	con = DriverManager.getConnection("jdbc:postgresql://psqlserv/n3p1","alaerm","moi");
	    
	    	// execution de la requete
	    	Statement stmt = con.createStatement();
	    	String absnj = "Select * from absences as a inner join justificatif as j on a.pno = j.pno where j.jno is null;";
	    	ResultSet rs = stmt.executeQuery(absnj);
	    	out.println("<center><H1>Absences non justifiées</H1>");
	    	out.println("<center><table border=1 class=\"table-centered table-hover table-condensed\">");
	 		out.println("<TR>");
	 		out.println("<TH>Date</TH><TH>Durée</TH></TR>");
	    	while(rs.next())
	    	{
	    		out.println("<TR><TD>"+rs.getString(4)+"</TD><TD>"+rs.getString(5)+"</TD></TR>");

	    	}
	    	out.println("</table>");

	    	String absj = "Select * from absences as a inner join justificatif as j on a.pno = j.pno where j.jno is not null;";
	    	rs = stmt.executeQuery(absj);
	    	out.println("<center><H1>Absences Justifiées</H1>");
	    	out.println("<center><table border=1 class=\"table-centered table-hover table-condensed\">");
	 		out.println("<TR>");
	 		out.println("<TH>Date</TH><TH>Durée</TH><TH>Raison</TH></TR>");
	    	while(rs.next())
	    	{
	    		out.println("<TR><TD>"+rs.getString(4)+"</TD><TD>"+rs.getString(5)+"</TD><TD>"+rs.getString(10)+"</TD></TR>");

	    	}
	    	out.println("</table>");


	    	String total = "Select count(*) from absences where pno="+p.num+";";
	    	rs=stmt.executeQuery(total);
	    	while(rs.next()){
	    		out.println("Vous avez "+rs.getInt()+" absences.");
	    	}
	    	
	    	out.println("</body>");
	    	out.println("</html>");

		}catch(Exception e){
			out.println("<div class='alert alert-warning' role='alert'>Erreur "+e.getClass()+" : "+e.getMessage()+"</div>");
		}
		finally
	    {
			try{con.close();} catch (Exception e){}
	    }
    }
}