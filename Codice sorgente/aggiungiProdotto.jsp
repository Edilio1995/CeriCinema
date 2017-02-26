<!DOCTYPE html>
<html>
	<head>
	  <meta charset="UTF-8">
	 <%@  page import="Utils.userBeans"%>
	  <style>
	    @import url(style.css);
	  </style>
	  <title>Aggiungi Prodotto</title>
	</head>
	<body onload="caricaFilm()">
	     <a href="visualizzaProdottiAdmin.jsp">
	   		<center>
	   			<img width="200px" src="img/PC-HUNT.png">
	   		</center>
	    </a>
	  </header>
	    <jsp:useBean id="userB" scope="session" class="Utils.userBeans"/>
	    <ul id="menu">
	      <li><a class="menu" href="visualizzaProdottiAdmin.jsp">Elenco Film</a></li>
		    <li><a class="menu" href="aggiungiProdotto.jsp">Aggiungi proiezione</a></li>
		    <li><a class="menu" href="ImpExp.jsp">Vedi info</a></li>
		    <li><a class="menu" href="VisualizzaUtentiAdmin.jsp">Visualizza Utenti</a></li>
	   </ul>
	   <div id="login" style="position: fixed; width: 220px;">
				<h3 align="center">Aggiungi Proiezione</h3>
				<form id="aggiungi" method="post" action="AggiungiProdottoAdmin" enctype="multipart/form-data">									
					&nbsp &nbsp &nbsp&nbsp<input type="submit" value="Aggiungi">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
					<input type="button" value="Annulla" onclick="document.location.href='visualizzaProdottiAdmin.jsp'">
				</form>
	   </div>
	   <div id="logout">
			    <span id="benvenuto">Benvenuto Admin </span>
			    <form method="post" action="Logout">
			    	<input type="submit" name="Logout" value="Logout">
			    	<input type="hidden" name="admin" value="admin">
			    </form>
			</div>
		  <table id="prodotti" width="50%">
		    <tr>
		      <td>
		      	<table id="specifiche">
		      		<tr>
		      			<td>
		      				<b>TITOLO</b>
		      			</td>
		      			<td>
		      				<input type="text" form="aggiungi" placeholder="Titolo film" id="nome" name="nome" onblur="nomefocus()">
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>FILM</strong>
		      			</td>
		      			<td>
		      				<select form="aggiungi" name="cpu" id="selectFilm">
		      				
		      				</select>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>SALA</strong>
		      			</td>
		      			<td>
		      				<select form="aggiungi" name="sale" id="sale">
		      					<option value="1">A1</option>
		      					<option value="2">A2</option>
		      					<option value="3">A3</option>
		      					<option value="4">A4</option>
		      				</select>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>PREZZO</strong>
		      			</td>
		      			<td>
		      				<select form="aggiungi" name="prezzo" id="prezzo">
		      					<option value="3">3,00</option>
		      					<option value="4">4,00</option>
		      					<option value="7,5">7,50</option>
		      					<option value="10">10,00</option>
		      					<option value="12">12,00</option>
		      				</select>
		      				&#8364
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>DATA PROIEZIONE</strong>
		      			</td>
		      			<td>
		      				<input type="text" form="aggiungi" placeholder="gg/mm/aaaa" id="gpu" name="gpu" onblur="gpufocus()">
		      			</td>
		      		</tr>
		      	</table>
		      </td>
			</tr>
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
					  if (xhttp.readyState == 4 && xhttp.status == 200) 
					  {
					    document.getElementById("selectFilm").innerHTML = xhttp.responseText;
					  }
					};
					xhttp.open("GET", "CaricaSelectFilm", true);
					xhttp.send();
			}
		    
		    //nome
			 function nomefocus()
			  {
				  if (document.getElementById("nome").value == "")
				  {
					document.getElementById("nome").style.borderColor = "red";
					document.getElementById("nome").value = "";
					document.getElementById("nome").focus();
				  }
				  else
				  {
				 	 document.getElementById("nome").style.borderColor = none;
				  }
			  }
		    
			//cpu
			 function cpufocus()
			  {
				  if (document.getElementById("cpu").value == "")
				  {
					document.getElementById("cpu").style.borderColor = "red";
					document.getElementById("cpu").value = "";
					document.getElementById("cpu").focus();
				  }
				  else
				  {
				 	 document.getElementById("cpu").style.borderColor = none;
				  }
			  }
		    
			//gpu
			 function gpufocus()
			  {
				  if (document.getElementById("gpu").value == "")
				  {
					document.getElementById("gpu").style.borderColor = "red";
					document.getElementById("gpu").value = "";
					document.getElementById("gpu").focus();
				  }
				  else
				  {
				 	 document.getElementById("gpu").style.borderColor = none;
				  }
			  }
		    
			//prezzo
			 function prezzofocus()
			  {
				  if (document.getElementById("prezzo").value == "")
				  {
					document.getElementById("prezzo").style.borderColor = "red";
					document.getElementById("prezzo").value = "";
					document.getElementById("prezzo").focus();
				  }
				  else
				  {
				 	 document.getElementById("prezzo").style.borderColor = none;
				  }
			  }	
			
			//quantita
			 function quantitafocus()
			  {
				  if (document.getElementById("quan").value == "")
				  {
					document.getElementById("quan").style.borderColor = "red";
					document.getElementById("quan").value = "";
					document.getElementById("quan").focus();
				  }
				  else
				  {
				 	 document.getElementById("quan").style.borderColor = none;
				  }
			  }	
			
			//descrizione
			 function descrfocus()
			  {
				  if (document.getElementById("descr").value == "")
				  {
					document.getElementById("descr").style.borderColor = "red";
					document.getElementById("descr").value = "";
					document.getElementById("descr").focus();
				  }
				  else
				  {
				 	 document.getElementById("descr").style.borderColor = none;
				  }
			  }	
	    </script>
	</body>
</html>
