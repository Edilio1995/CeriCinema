<!DOCTYPE html>
<html>
<head>
<%@  page import="Utils.userBeans"%>
  <meta charset="UTF-8">
  <style>
    @import url(style.css);
  </style>
  <title>Cericinema - Lista film</title>
</head>
<body onload="caricaFilm()">
	  <header>
	    <a href="index.jsp">
	   		<center><img width="200px" src="img/PC-HUNT.png"></center>
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
					<%	
					userBeans utente = (userBeans) session.getAttribute("user");
					if(utente==null)
					{%>
						<button type="submit" formaction="login.jsp" >Accedi</button>
					<%}
					else
					{%>
						<button type="submit" formaction="Logout" >Esci</button>
					<%}%>
	    			<input placeholder="Cerca" id="ricerca" type="search"/>
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
	 </ul>  	
  		<%!int i; int n=0;%>
  		
  		<%if(request.getAttribute("msg")!=null){%>
			<div id="avviso"><%=request.getAttribute("msg")%></div>
			<%}%>
  		
  		<table id="prodotti">
  		
 		</table>
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
  		function caricaFilm() 
		{
			  var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
				  if (xhttp.readyState == 4 && xhttp.status == 200) {
				    document.getElementById("prodotti").innerHTML = xhttp.responseText;
				  }
				};
				xhttp.open("POST", "CaricaFilmIndex", true);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send("mex="+document.getElementById("ricerca").value);
		}
  		function Ricerca() {
					  var xhttp = new XMLHttpRequest();
					  xhttp.onreadystatechange = function() {
						  if (xhttp.readyState == 4 && xhttp.status == 200) {
						    document.getElementById("prodotti").innerHTML = xhttp.responseText;
						  }
						};
						xhttp.open("POST", "Ricerca", true);
						xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						xhttp.send("mex="+document.getElementById("ricerca").value);
				}
  		</script>
	</body>
</html>
