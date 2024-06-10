<%@ page session="true"%>
<html>
   <head>
      <title>Accesso</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
      <p>
      	Fai l'accesso per entrare nell'applicazione &nbsp;
      </p>
      <div>
      	<form action="LogIn" method="post">
      		<p>User:</p>
      		<input type="text" name="userName" size="30"/><br>
      		<p>Password:</p>
      		<input type="password" name="pwd" size="30"/><br>
      		<br>
      		<input type="submit" value="Accedi"/>
      	</form>
      </div>
      <div>
      Oppure clicca <a href="registrazione.jsp"> qui </a> per registrarti
      </div>

   </body>
</html>