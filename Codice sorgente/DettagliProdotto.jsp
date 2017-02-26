<!DOCTYPE html>
<html>
	<head>
	  <meta charset="UTF-8">
	  <%@  page import="Utils.Proiezione"%>
	  <%@  page import="Utils.userBeans"%>
	  <style>
	    @import url(style.css);
	  </style>
	  <title>Cericinema - Dettagli Prodotto</title>
	</head>
	<body>
	    <header>
	    <a href="index.jsp">
	   		<center>
	   			<img width="200px" src="img/PC-HUNT.png">
	   		</center>
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
	   </li>
	 </ul>
		  
	   	  
	   	  <%	Proiezione film = (Proiezione) session.getAttribute("film"); %>
		  <table id="prodotti" width="50%">
		    <tr>
		      <td>
		        <img id="myImagee" name="myImage" src="<%=film.getLocandina()%>" width="300" height="400" align="center">
		      </td>
		      <td>
		      	<table id="specifiche">
		      		<tr>
		      			<td>
		      				<b>Nome</b>
		      			</td>
		      			<td colspan="2">
		      				<%=film.getNome()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<b>Genere</b>
		      			</td>
		      			<td colspan="2">
		      				<%=film.getGenere()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>Data di uscita</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=film.getData()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>Data Proiezione</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=film.getDataProiezione()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>Sala</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=film.getSala()%>
		      			</td>
		      		</tr>
		      		<tr>
		      			<td>
		      				<strong>Prezzo</strong>
		      			</td>
		      			<td colspan="2">
		      				<%=film.getPrezzo()%>&nbsp&#8364
		      			</td>
		      		</tr>
		      	</table>
		      </td>
			</tr>
			<tr>
		        <td id="aggCarrello" colspan="2">
		          <b>Acquista</b><br/><br />
		          <button id="butCarr" type="button" value="Esci" onclick="document.location.href='login.jsp'"><img src="img/carrello.png" width="30" height="30"></button>
			</tr>
		    <tr>
		      <td colspan="2">
		        <h3 id="intestazione"><%=film.getNome()%></h3>
		          <center>
		          	<p id="descrizione"> 
		          	 <%=film.getDescrizione() %>
		          	</p>
				  </center>
		      </td>
			</tr>
		</table>
		
		%>
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
			function Ricerca() 
			{
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
