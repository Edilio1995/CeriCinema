<!DOCTYPE html>
<%@page import="java.net.URLDecoder"%>
<html>
	<head>
		  <meta charset="UTF-8">
		  <style>
		  	@import url(style.css);
		  </style>
		  <title>Riepilogo Dati</title>
	</head>
	<body>
		<header>
	    <a href="reg_index.jsp">
	   		<center><img width="200px" src="img/PC-HUNT.png"></center>
	    </a>
	  </header>
		<ul id="menu">
		    <li><a class="menu" href="reg_index.jsp">Home</a></li>
		    <li><a class="menu" href="reg_lista_film.jsp">Lista film</a></li>
				<li> <a id = "tendina" class="menu">Categoria</a>
					<ul>
						<li><a href="reg_filmAnimazione.jsp">Animazione</a></li>
						<li><a href="reg_filmAvventura.jsp">Avventura</a></li>
						<li><a href="reg_filmAzione.jsp">Azione</a></li>
						<li><a href="reg_filmCommedia.jsp">Commedia</a></li>
						<li><a href="reg_filmFantascienza.jsp">Fantascienza</a></li>
						<li><a href="reg_filmHorror.jsp">Horror</a></li>
						<li><a href="reg_filmThriller.jsp">Thriller</a></li>
					</ul>
				</li>
		    <li><a class="menu" href="reg_contatti.jsp">Contatti</a></li>
		    <li><a class="menu" href="reg_ilmioaccount.jsp">Il mio Account</a></li>
		    <li><a class="menu" href="reg_imieiacquisti.jsp">I miei Acquisti</a></li>
		    <li><div id="car">
			<input type="button" value="Esci" onclick="document.location.href='index.jsp'">
	    	<input placeholder="Cerca" id="ricerca" type="search"/>
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
		<div id="riepilogo" style="width: auto; text-align: center">
			<%=URLDecoder.decode(request.getQueryString())%>
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
