<!DOCTYPE html>
<html>
<head>
<%@  page import="Utils.userBeans"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="refresh" content="60" />
<script src="themes/1/js-image-slider.js" type="text/javascript"></script>
<style>
@import url(style.css);
@import
url(themes/1/js-image-slider.css)
</style>
<title>Cericinema</title>
</head>
<body onload="caricaFilm()">
	<header>
		<a href="reg_index.jsp">
			<center>
				<img width="200px" src="img/PC-HUNT.png">
			</center>
		</a>
	</header>
	<ul id="menu">
		<li><a class="menu" href="reg_index.jsp">Home</a></li>
		<li><a class="menu" href="reg_lista_film.jsp">Lista film</a></li>
		<li><a id="tendina" class="menu">Categoria</a>
			<ul>
				<li><a href="reg_filmAnimazione.jsp">Animazione</a></li>
				<li><a href="reg_filmAvventura.jsp">Avventura</a></li>
				<li><a href="reg_filmAzione.jsp">Azione</a></li>
				<li><a href="reg_filmCommedia.jsp">Commedia</a></li>
				<li><a href="reg_filmFantascienza.jsp">Fantascienza</a></li>
				<li><a href="reg_filmHorror.jsp">Horror</a></li>
				<li><a href="reg_filmThriller.jsp">Thriller</a></li>
			</ul></li>
		<li><a class="menu" href="reg_contatti.jsp">Contatti</a></li>
		<li><a class="menu" href="reg_ilmioaccount.jsp">Il mio
				Account</a></li>
		<li><a class="menu" href="reg_imieiacquisti.jsp">I miei
				Acquisti</a></li>
		<li><div id="car">
				<input type="button" value="Esci"
					onclick="document.location.href='index.jsp'"> <input
					placeholder="Cerca" id="ricerca" type="search" />
				<script>
		    			var txt = document.getElementById("ricerca");
		    			txt.onkeydown = function(e){
		    				if(e.keyCode==13){
		    					Ricerca();
		    				}
		    			}
		    		</script>
			</div></li>
	</ul>

	<%if(request.getAttribute("msg")!=null){%>
	<div id="avviso"><%=request.getAttribute("msg")%></div>
	<%}%>

	<div id="content">
		<table id="prodotti">
			<div id="sliderFrame">
				<div id="slider">
					<a href="reg_lista_film.jsp"><img src="img/slider_mammaopapa.png" /></a>
					<a href="reg_lista_film.jsp"><img src="img/slider_ora_legale.png" /></a>
					<a href="reg_lista_film.jsp"><img src="img/legobatman.png" /></a>
				</div>
				<div id="htmlcaption" style="display: none;">
					<em>HTML</em> caption. Link to <a href="http://www.google.com/">Google</a>.
				</div>
			</div> <br> <br>
		</table>
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
				xhttp.send("mex="+document.getElementById("admin").value);
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
