<!DOCTYPE HTLM>
<HTML>
	<%@page import="java.sql.*"%>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<TITLE>Test Select</TITLE>
		</HEAD>
		<BODY>
			<CENTER>
				
				<% 	
				Class.forName("org.postgresql.Driver");
				Connection con=DriverManager.getConnection("jdbc:postgresql://psqlserv/n3p1","alaerm","moi");
				Statement stmt = con.createStatement();
				String table = "clients";
				String query = "select * from "+table;
				ResultSet rs = stmt.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				int nbCols = rsmd.getColumnCount();
				%>

				<H1>Table <%= table%></H1>


				<TABLE border=1>
					<TR>
						<%
						for(int i=1; i<=nbCols; i++){
						%>
						<TH>
							<% out.print(rsmd.getColumnName(i)); %>
						</TH>
						<% } %>
					</TR>

					<% while(rs.next()){ %>
					<TR>
						<% for(int i=1; i<=nbCols; i++){ %>
						<TD>
							<% out.print(rs.getString(i)); %>	
						</TD>
						<% } %>
					</TR>
					<% } %>

				</TABLE>

			</CENTER>

		</BODY>
	</HTML>
