<!DOCTYPE html>
<html>
	<head>
	  <meta charset="UTF-8">
	 <%@  page import="Utils.userBeans"%>
	 <%@  page import="Utils.Proiezione"%>
	  <style>
	    @import url(style.css);
	  </style>
	  <title>Dettagli Prodotto</title>
	</head>
	<body onload="carica()">
	    <header>
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
	    <%Proiezione prod = (Proiezione) request.getAttribute("film"); %>
	   <div id="login" style="position: fixed; width: 220px;">
				<h4 align="center">Modifica / Elimina</h4>
				<form id="aggiungi" method="post" action="ModificaAdmin">									
					<button type="submit" value="Aggiungi"> 
						<img src="img/modifica.png" width="30px"/> 
					</button>&nbspModifica
					<input type="hidden" name="id" value="<%=prod.getNome()%>">
				</form>
				</br>
				<form id="elimina" method="post" action="EliminaAdmin">
					<button type="submit" value="Annulla" onclick=""> 
						<img src="img/vuota_car.png" width="30px"> 
						<input type="hidden" name="nome" value="<%=prod.getNome()%>">
					</button>&nbspElimina	
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
		        <img id="myImagee" name="myImage" src="<%=prod.getLocandina()%>" width="300" height="400" align="center">
		      </td>
		      <td>
		      	<table id="specifiche">
		      		<tr>
		      			<td>
		      				<b>Nome</b>
		      			</td>
		      			<td colspan="2">
		      				<%=prod.getNome()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<b>Genere</b>
		      			</td>
		      			<td colspan="2">
		      				<%=prod.getGenere()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>Data di uscita</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=prod.getData()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>Data Proiezione</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=prod.getDataProiezione()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>Sala</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=prod.getSala()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>Prezzo</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=prod.getPrezzo()%>&nbsp&#8364
		      			</td>
		      		</tr>
		      	</table>
		      </td>
			</tr>
		    <tr>
		      <td colspan="2">
		        <h3 id="intestazione"><%=prod.getNome()%></h3>
		          <center>
		          	<p id="descrizione"> 
		          	 <%=prod.getDescrizione() %>
		          	</p>
				  </center>
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
	</body>
</html>
