<!DOCTYPE html>
<html>
	<head>
	  <%@  page import="Utils.Carrello"%>
	  <%@  page import="Utils.userBeans"%>
	  <meta charset="UTF-8">
	  <style>
	    @import url(style.css);
	  </style>
	  <title>Cericinema - Modifica il mio Account</title>
	</head>
	<body onload="">
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
	<div id="login" style="position: fixed; width: 220px;">
				<h3 align="center">Modifica Info Personali</h3>
				<form id="modifica" method="get" action="ApplicaModificheInfo" enctype="multipart/form-data">									
					&nbsp &nbsp &nbsp&nbsp<input type="submit" value="Applica">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
					<input type="button" value="Annulla" onclick="document.location.href='reg_ilmioaccount.jsp'">
				</form>
	 </div>
	<div id="content">
		<table id="prodotti">
			<tr>
				<td>
					<b>Nome</b>
				</td>
				<td>
					<input form="modifica" type="text" name="nome" maxlength="20" placeholder="nuovo nome">
				</td>
			</tr>
			<tr>
				<td>
					<b>Cognome</b>
				</td>
				<td>
					<input form="modifica" type="text" name="cognome" maxlength="30" placeholder="nuovo cognome">
				</td>
			</tr>
			<tr>
				<td>
					<b>Email</b>
				</td>
				<td>
					<input form="modifica" type="text" name="email" maxlength="50" placeholder="nuova email">
				</td>
			</tr>
			<tr>
				<td>
					<b>Data di Nascita</b>
				</td>
				<td>
					<input form="modifica" type="text" name="data" maxlength="10" placeholder="nuova data di nascita">
				</td>
			</tr>
			<tr>
				<td>
					<b>Username</b>
				</td>
				<td>
					<input form="modifica" type="text" name="username" placeholder="nuovo username">
				</td>
			</tr>
			<tr>
				<td>
					<b>Residenza</b>
				</td>
				<td>
					<input form="modifica" type="text" name="residenza" placeholder = "nuova residenza">
				</td>
			</tr>
			<tr>
				<td>
					<b>CAP</b>
				</td>
				<td>
					<input form="modifica" type="text" name="cap" maxlength="5" placeholder = "nuovo cap">
				</td>
			</tr>
			<tr>
				<td>
					<b>CF</b>
				</td>
				<td>
					<input form="modifica" type="text" name="cf" maxlength="16" placeholder="nuovo cf">
				</td>
			</tr>
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