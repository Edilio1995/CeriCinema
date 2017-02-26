<!DOCTYPE html>
<html>
	<head>
		<%@  page import="Utils.userBeans"%>
	  	<meta charset="UTF-8">
	  	<style>
	    	@import url(style.css);
	  	</style>
	  <title>Cericinema - Riepilogo Dati</title>
	</head>
	<body>
		<header>
	    <a href="index.jsp">
	   		<center>
	   			<img width="200px" src="img/PC-HUNT.png">
	   		</center>
	    </a>
	  </header>
		<ul id="menu">
		    <li><a class="menu" href="index.jsp">Home</a></li>
		    <li><a class="menu" href="lista_film.jsp">Lista film</a></li>
				<li> <a id = "tendina" class="menu">Categoria</a>
					<ul>
						<li><a href="filmAnimazione.jsp">Animazione</a></li>
						<li><a href="filmAvventura.jsp">Avventura</a></li>
						<li><a href="filmAzione.jsp">Azione</a></li>
						<li><a href="filmCommedia.jsp">Commedia</a></li>
						<li><a href="filmFantascienza.jsp">Fantascienza</a></li>
						<li><a href="filmHorror.jsp">Horror</a></li>
						<li><a href="filmThriller.jsp">Thriller</a></li>
					</ul>
				</li>
		    <li><a class="menu" href="contatti.jsp">Contatti</a></li>
		    <li><a class="menu" href="registrazione.jsp">Registrazione</a></li>
		    <li><div id="car">
		    	<form>
						<button type="submit" formaction="login.jsp" >Accedi</button>
	    		</form>
	    		<script>
	    			var txt = document.getElementById("ricerca");
	    			txt.onkeydown = function(e){
	    				if(e.keyCode==13){
	    					Ricerca();
	    				}
	    			}
	    		</script>
	    	</div>
	    	</form>
	   </li>
	 </ul>
		<div id="riepilogo"  style="width: 300px; text-align: left">
			<b>Nome: </b><span id="nom"><%=request.getParameter("nome")%></span><br>
			<b>Cognome: </b><span id="cognom"><%=request.getParameter("cognome")%></span><br>
			<b>Email:</b><span id = "email"><%=request.getParameter("email")%></span><br>
			<b>Data di nascita: </b><span id="data"><%=request.getParameter("dataDiNascita")%></span><br>
			<b>Residenza: </b><span id="resid"><%=request.getParameter("residenza")%></span><br>
			<b>Cap: </b><span id="cap"><%=request.getParameter("cap")%></span><br>
			<b>Codice fiscale: </b><span id="fiscale"><%=request.getParameter("codiceFiscale")%></span><br>
			<b>Username: </b><span id="user"><%=request.getParameter("username")%></span><br>
			<b>Password: </b><span id="pass"><%=request.getParameter("password")%></span><br>
		</div>
		<footer>
		  <div id="copyright">
		    <p>Design Copyright © Ildo Tiberio. All rights reserved.</p>
		  </div>
		  <div id="svg">
		  	<svg height="10" width="0">
		    	<img src="img/PC-HUNT_logo.svg" height="50" width="80">
		  	</svg>
		  </div>
		</footer>
		<script>
			function Ricerca() {
				  var xhttp = new XMLHttpRequest();
				  xhttp.onreadystatechange = function() {
					  if (xhttp.readyState == 4 && xhttp.status == 200) {
					    document.getElementById("riepilogo").innerHTML = xhttp.responseText;
					  }
					};
					xhttp.open("POST", "Ricerca", true);
					xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					xhttp.send("mex="+document.getElementById("ricerca").value);
			}
		</script>
	</body>
</html>
