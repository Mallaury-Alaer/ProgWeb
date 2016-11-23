<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.io.* , java.sql.*, java.util.*"%>
<%@ page import="metier.Personne"%>
<%@ page import="metier.JeuPileOuFace"%>
<!DOCTYPE html>
<html>
	<head>
		<title> MaPage </title>
	</head>
		<body>
			<%
				JeuPileOuFace pa = (JeuPileOuFace)session.getAttribute("JeuPileOuFace");
				if (pa == null) {
					pa = new JeuPileOuFace();
					pa.init();
					session.setAttribute("JeuPileOuFace",pa);
				}
				String str = "lol";
				if(!pa.termine()){

							out.println("Vous jouez <a href=\"http://hevea01:8080/vide/pileouface.jsp?coup=P\">Pile</a> ou <a href=\"http://hevea01:8080/vide/pileouface.jsp?coup=F\">Face</a> ?");

						
						try{
							if (request.getParameter("coup") == null) {
								str = "A";
								pa.init();
							}else{
								str = request.getParameter("coup");
							}
						}catch(Exception e){

						}
						if (str.equals("P")) {
							pa.play(str.charAt(0));
						}else if(str.equals("F")){
							pa.play(str.charAt(0));
						}
				}
			
			out.println("<br/>Humain : "+pa.getPointsHumain());
			out.println("<br/>Ordi : "+pa.getPointsOrdi());
				if (pa.termine()) {
					if(pa.getPointsOrdi() >= pa.getPointsHumain()){
					out.println("<br/>Vous avez perdu !! <br/>");
					out.println("Appuyer sur Pile ou face pour rejouer");
					}else{
					out.println("<br/>Vous avez gagné !!  <br/>");
					out.println("Appuyer sur Pile ou face pour rejouer");
					}
					pa.init();
				}

			
			%>

		</body>
</html>