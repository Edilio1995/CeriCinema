<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<style>
			@import url(style.css);
		</style>
		<title>Cirecinema - Registrazione</title>
	</head>
	<body>
	  <header>
	    <a href="index.jsp">
	   		<center>
	   			<img width="200px" src="img/PC-HUNT.png">
	   		</center>
	    </a>
	  </header>
	  <jsp:useBean id="userB" scope="session" class="Utils.userBeans"/>
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
					<%	Object utente = session.getAttribute("user");
					if(utente == null)
					{%>
						<button type="submit" formaction="login.jsp" >Accedi</button>
						<%}
						else
						{%>
						<button type="button">Esci</button>
						<%} %>
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
   			<table id="prodotti">
   				<tr>
								<th colspan="2">
									Accedi subito per effettuare acquisti!
								</th>
							</tr>
							<tr>
								<th>
									<div style="text-align:left">Inserisci nei campi appositi il tuo username</br>
									e password per effettuare il login</div>
								</th>
								<th>
									<form action="Login">
										<div style="text-align:right">
										Nome utente:&nbsp<input id="username" name="username" type"text"></br>
										Password:&nbsp<input id = "password" name="password" type"text">
										</div>
								</th>
							</tr>
							<tr>
								<th colspan="2">
										<div style="text-align:right">
											<button type="submit">Invia</button>
										</div>
									</form>
								</th>
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