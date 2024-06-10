<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="Beans.Utente"%>
<%@ page import="Beans.GruppoUtenti"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>

<html>
	<head>
	
		<title>Password</title>
		
	</head>
	<body>
	
	<% 
 	Utente logged=(Utente)session.getAttribute("currentUser");
	
	if(logged==null || !(logged.getUserName().equals("admin") && logged.getPwd().equals("admin")) ) { 
		%>
		<p>
		Non puoi accedere a questa pagina senza aver fatto l'accesso come amministratore
		</p>
		<%
	}
	else{ 
		%>
		<p>
			Tabella degli utenti gia registrati con relativo gruppo
		</p>
		<table>
		<tr>
			<th style="width: 33%">Group id</th>
			<th style="width: 33%">Name</th>
			<th style="width: 33%">Password</th>
		</tr>
		
		<%
		Map<String, GruppoUtenti> gruppi = (Map<String, GruppoUtenti>)this.getServletContext().getAttribute("gruppi");
		
		for(String idGruppo : gruppi.keySet()){
			
			GruppoUtenti utenti = gruppi.get(idGruppo);
			
			for(Utente u: utenti.getUtenti()){
		%>
				<tr>		
					<td><%= idGruppo %></td>
					<td><%= u.getUserName() %> </td>
					<td><%= u.getPwd() %></td>
				</tr>
		<%
			}
		}
		%>
		</table>
	<%	
	} 
	%>
	</body>
</html>