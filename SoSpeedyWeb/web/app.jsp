<%@ page errorPage="../errors/failure.jsp"%>


<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>

<html>
	<head>
    	<title>SoSpeedy</title>
    	<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link type="text/css" href="styles/myCSS.css" rel="stylesheet"></link>

		<!-- inserisci script dove hai definito le varie funzioni ajax e javascript -->
		<script type="text/javascript" src="scripts/ajax.js"></script>

	 </head>
	 
	 <body>
	 	
	 	
	 	<div class="container">
	 	
		 	<div id="logo">
	        	<img src="images/logo.jpeg" alt="SOSPEEDY Logo">
	    	</div>
	    	
		 	<h1>SOSPEEDY</h1>
	        <h2>Visualizza la tua attesa</h2>
	 	
		 	<form action="TempoAttesa" method="post">
	      		<p>Codice identificativo paziente:</p>
	      		<input type="text" name="codiceIdentificativo" size="5"/><br>
	      		<input type="submit" value="Conferma"/>
	      	</form>
	      	
	      	<%
	      		String risposta=(String)request.getAttribute("risposta");
	      		if(risposta!=null){
	      	%>
	      		<p><%=risposta %></p>
	      	<%
	      		}
	      	%>
	 	
	 	</div>
	 

 	</body>
</html>