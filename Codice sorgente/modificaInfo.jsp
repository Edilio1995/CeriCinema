<!DOCTYPE html>
<html>
	<head>
	  <meta charset="UTF-8">
	  <style>
	    @import url(style.css);
	  </style>
	  <%@  page import="Utils.userBeans"%>
	  <title>Modifica Info Cinema</title>
	</head>
	<body>
	 <header>
	     <a href="visualizzaProdottiAdmin.jsp">
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
				<h4 align="center">Applica Modifiche</h4>								
					<center>
						<input type="submit" form="modifica" value="Applica">
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
		<form id='modifica' method='get' action='ModificaInfo' enctype='multipart/form-data'>"
			<table id="prodotti">
				<tr>
					<td>
						<h4> Sede </h4>
					</td> 
					<td>
						<input form="modifica" name="via" id="via" type="text" placeholder="Via"> 
					</td>
				</tr>
				<tr>
					<td>
						<h4> Telefono </h4>
					</td>
					<td rowspan='2'>
						<input form="modifica" type="text" placeholder="Telefono" name = "telefono" id="telefono">
					</td>
				</tr>
				<tr>
					<td>
						<h4> Fax </h4>
					</td>
				</tr>
				<tr>
					<td>
						<h4>Email</h4>
					</td>
					<td>
						<input form="modifica" type="text" id="email" placeholder="Email" name="email">
					</td>
				</tr>
			</table>
		</form>
		
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
	</body>
</html>
