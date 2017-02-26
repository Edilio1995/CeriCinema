<!DOCTYPE html>
<html>
	<head>
	  <%@  page import="Utils.Carrello"%>
	  <%@  page import="Utils.userBeans"%>
	  <meta charset="UTF-8">
	  <style>
	    @import url(style.css);
	  </style>
	  <title>Admin</title>
	</head>
	<body onload="caricaFilmAdmin()">
	<header>
	  <a href="visualizzaProdottiAdmin.jsp">
	   		<center>
	   			<img width="200px" src="img/PC-HUNT.png">
	   		</center>
	    </a>
	  </header>
		<ul id="menu">
		    <li><a class="menu" href="visualizzaProdottiAdmin.jsp">Elenco Film</a></li>
		    <li><a class="menu" href="aggiungiProdotto.jsp">Aggiungi proiezione</a></li>
		    <li><a class="menu" href="ImpExp.jsp">Vedi info</a></li>
		    <li><a class="menu" href="VisualizzaUtentiAdmin.jsp">Visualizza Utenti</a></li>
		    <li><div id="car">
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
	   </li>
	   </ul>
	   		<div id="logout">
			    <span id="benvenuto">Benvenuto Admin </span>
			    <form method="post" action="Logout">
			    	<input type="submit" name="Logout" value="Logout">
			    	<input type="hidden" name="admin" value="admin">
			    </form>
			</div>
			<div id="filtri">
				<h3>Filtra per genere</h3>
				</br><select placeholder="Inserire prezzo" onchange="filtrami()" type="text" id="filtroPrezzo">
						<option value="---">---</option>
					 	<option value="Commedia">Commedia</option>
		      			<option value="Avventura">Avventura</option>
		      			<option value="Animazione">Animazione</option>
		      			<option value="Horror">Horror</option>
		      			<option value="Azione">Azione</option>
		      			<option value="Thriller">Thriller</option>
		      			<option value="Fantascienza">Fantascienza</option>
					 </select>
			</div>
	<div id="content">
		<table id="prodotti"> </table>
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
		function caricaFilmAdmin() 
		{
			  var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
				  if (xhttp.readyState == 4 && xhttp.status == 200) {
				    document.getElementById("prodotti").innerHTML = xhttp.responseText;
				  }
				};
				xhttp.open("POST", "CaricaFilmIndex", true);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send("mex="+"admin");
		}
				function Ricerca() {
					  var xhttp = new XMLHttpRequest();
					  xhttp.onreadystatechange = function() {
						  if (xhttp.readyState == 4 && xhttp.status == 200) {
						    document.getElementById("prodotti").innerHTML = xhttp.responseText;
						  }
						};
						xhttp.open("POST", "RicercaAdmin", true);
						xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						xhttp.send("mex="+document.getElementById("ricerca").value);
				}
				function filtrami() {
					  var xhttp = new XMLHttpRequest();
					  xhttp.onreadystatechange = function() {
						  if (xhttp.readyState == 4 && xhttp.status == 200) {
						    document.getElementById("prodotti").innerHTML = xhttp.responseText;
						  }
						};
						xhttp.open("POST", "FiltraAdmin", true);
						xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						xhttp.send("mex="+document.getElementById("filtroPrezzo").value);
				}
		</script>
	</body>
</html>