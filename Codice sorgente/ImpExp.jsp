<!DOCTYPE html>
<html>
	<head>
	  <meta charset="UTF-8">
	  <style>
	    @import url(style.css);
	  </style>
	  <%@  page import="Utils.userBeans"%>
	  <title>Info Cinema</title>
	</head>
	<body onload="caricaInfo()">
	 <header>
	    <a href="index.jsp">
	   		<center>
	   			<img width="200px" src="img/PC-HUNT.png">
	   		</center>
	    </a>
	  </header>
		<ul id="menu">
		    <li><a class="menu" href="visualizzaProdottiAdmin.jsp">Elenco Proiezioni</a></li>
		    <li><a class="menu" href="aggiungiProdotto.jsp">Aggiungi proiezione</a></li>
		    <li><a class="menu" href="ImpExp.jsp">Vedi info</a></li>
		    <li><a class="menu" href="VisualizzaUtentiAdmin.jsp">Visualizza Utenti</a></li>
	   </li>
	   </ul>
	   <div id="login" style="position: fixed; width: 220px;">
				<h4 align="center">Modifica Info</h4>								
					<center>
						<a href="modificaInfo.jsp">
							<img src="img/modifica.png" width="30px"/> 
						</a>
					</center>
				</form>
		</div>
	   <div id="logout">
			    <span id="benvenuto">Benvenuto Admin </span>
			    <form method="post" action="Logout">
			    	<input type="submit" name="Logout" value="Logout">
			    	<input type="hidden" name="admin" value="admin">
			    </form>
			</div>
	<div id="content">
		<table id="prodotti">
		
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
	function caricaInfo() {
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
			  if (xhttp.readyState == 4 && xhttp.status == 200) {
			    document.getElementById("prodotti").innerHTML = xhttp.responseText;
			  }
			};
			xhttp.open("POST", "CaricaInfo", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("mex="+"mostra");
	}
	</script>
	</body>
</html>
