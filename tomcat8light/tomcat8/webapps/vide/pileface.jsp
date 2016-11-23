<!DOCTYPE HTLM>
<HTML>
	<%@page pageEncoding="UTF-8"%>
	<%@page import="metier.JeuPileOuFace"%> 
	<HEAD>
		<META http-equiv="Content-Type" content="text/html">
			<TITLE>Pile ou Face</TITLE>
	</HEAD>
	<BODY>
		<CENTER>
			<H1> Pile ou Face</H1>
			<%
				JeuPileOuFace jeu = (JeuPileOuFace)session.getAttribute("JeuPileOuFace");
				if (jeu == null) {
					jeu = new JeuPileOuFace();
					jeu.init();
					session.setAttribute("JeuPileOuFace",jeu);
				}
				String str = "vide";
				if(!jeu.termine()){
			%>
			<p> Vous jouez <a href="http://hevea03:8080/vide/pileface.jsp?coup=P">Pile</a> ou <a href="http://hevea03:8080/vide/pileface.jsp?coup=F">Face</a>
			</p>

			<%
				try{
					if (request.getParameter("coup") == null) {
						str = "A";
						jeu.init();
					}else{
						str = request.getParameter("coup");
					}
				}catch(Exception e){

				}
				if (str.equals("P")) {
					jeu.play(str.charAt(0));
				}else if(str.equals("F")){
					jeu.play(str.charAt(0));
				}
			}
			%>
			<br>Scores : <br> Vous : <%=jeu.getPointsHumain()%> | Ordi : <%=jeu.getPointsOrdi()%>
			<%
				if (jeu.termine()) {
					if(jeu.getPointsOrdi() >= jeu.getPointsHumain()){
			%>
						<br>Vous avez perdu !!
						<br>Appuyer <a href="http://hevea03:8080/vide/pileface.jsp">ici</a> pour rejouer
			<%
					}else{
			%>
						<br>Vous avez gagné !!  
						<br>Appuyer <a href="http://hevea03:8080/vide/pileface.jsp">ici</a> pour rejouer
			<%
					}
					jeu.init();
				}
			%>
		</CENTER>
	</BODY>
</HTML>