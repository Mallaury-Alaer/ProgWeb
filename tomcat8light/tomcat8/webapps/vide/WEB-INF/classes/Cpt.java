import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Cpt")
public class Cpt extends HttpServlet{

	int nbVisites = 0;

	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
    
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		nbVisites++;

		HttpSession session = req.getSession( true );
		Integer cpt = (Integer)session.getAttribute( "compteur" );
		cpt = new Integer( cpt == null ? 1 : cpt.intValue() + 1 );
		session.setAttribute( "compteur", cpt );

		out.println("<!doctype html>");
		out.println("<head><title>Compteur</title></head><body><center> ");
		out.println("<h1>Compteur</h1>");
		out.println("Vous avez acceder "+cpt+" fois a cette page sur les "+nbVisites+" acces au total.");
		out.println("</body>");
		out.println("</html>");


	}
}