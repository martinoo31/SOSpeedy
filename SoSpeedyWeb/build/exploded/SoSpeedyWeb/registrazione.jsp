<%@ page session="true"%>
<html>
   <head>
      <title>Registrazione</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
      <p>
      	Inserisci i tuoi dati per registrarti &nbsp;
      </p>
      <div>
      	<form action="LogIn" method="get">
      		<p>User:</p>
      		<input type="text" name="username" size="25"/><br>
      		<p>Password:</p>
      		<input type="password" name="pwd" size="25"/><br>
      		<p>Group:</p>
      		<input type="text" name="group" size="25"/><br><br>
      		<input type="submit" value="Registrati"/>
      	</form>
      </div>
   </body>
</html>