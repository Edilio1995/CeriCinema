<!DOCTYPE html>
<html>
	<head>
	  <meta charset="UTF-8">
	  <%@  page import="Utils.userBeans"%>
	  <%@  page import="Utils.Proiezione"%>
	  <style>
	    @import url(style.css);
	  </style>
	  <title>Modifica Prodotto</title>
	</head>
	<body onload="carica()">
	    <a href="visualizzaProdottiAdmin.jsp">
	    <header>
	   <a href="visualizzaProdottiAdmin.jsp">
	   		<center>
	   			<img width="200px" src="img/PC-HUNT.png">
	   		</center>
	    </a>
	  </header>
	    <jsp:useBean id="userB" scope="session" class="Utils.userBeans"/>
	    <ul id="menu">
	      <li><a class="menu" href="visualizzaProdottiAdmin.jsp">Visualizza Prodotti</a></li>
		  <li><a class="menu" href="aggiungiProdotto.jsp">Aggiungi Prodotto</a></li>
		  <li><a class="menu" href="ImpExp.jsp">Import/Export dei dati</a></li>
		  <li><a class="menu" href="VisualizzaUtentiAdmin.jsp">Visualizza Utenti</a></li>
	   </ul>
	   <%Proiezione prod = (Proiezione) session.getAttribute("film"); %>
	   <div id="login" style="position: fixed;">
				<h3 align="center">Modifiche</h3>
				<form id="modifica" method="post" action="ApplicaModificheAdmin">	
					<input type="hidden" name="nome" value="<%=prod.getNome()%>">							
					&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input type="submit" value="Applica">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
					<input type="button" value="Annulla" onclick="document.location.href='visualizzaProdottiAdmin.jsp'">
				</form>
		</div>
		<table id="prodotti" width="50%">
			<tr>
		  		<td colspan="2">
		  			<h1>Modifica Proiezione</h1>
		  		</td>
		  	</tr>
		    <tr>
		      <td>
		        <img id="myImagee" name="myImage" src="<%=prod.getLocandina() %>" width="300" height="400" align="center">
		      </td>
		      <td>
		      	<table id="specifiche">
		      		<tr>
		      			<td>
		      				<strong>NOME</strong>
		      			</td>
		      			<td colspan="2">
		      				<b><%=prod.getNome() %></b>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>GENERE</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=prod.getGenere() %>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>DATA DI USCITA</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=prod.getData() %>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>DATA PROIEZIONE</strong>
		      			</td>
		      			<td>
		      				<%=prod.getDataProiezione() %>
		      			</td>
		      			<td>
		      				<input form="modifica" placeholder="inserisci nuovo valore" name="nuovocpu">
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>SALA</strong>
		      			</td>
		      			<td>
		      				A<%=prod.getSala() %>
		      			</td>
		      			<td>
		      				<select form="modifica" name="sale" id="sale">
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
		      				<%=prod.getPrezzo() %>
		      			</td>
		      			<td>
		      				<select form="modifica" name="prezzo" id="prezzo">
		      					<option value="3">3,00</option>
		      					<option value="4">4,00</option>
		      					<option value="7,5">7,50</option>
		      					<option value="10">10,00</option>
		      					<option value="12">12,00</option>
		      				</select>
		      				&#8364
		      			</td>
		      		</tr>
		      		
		      	</table>
		      </td>
			</tr>
		    <tr>
		      <td colspan="2">
		        <%=prod.getDescrizione()%>
		          <center>
		          	<p id="descrizione"> 
		          	 
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