<!DOCTYPE html>
<html>
	<head>
	<%@  page import="Utils.userBeans"%>
		<meta charset="UTF-8">
		<style>
			@import url(style.css);
		</style>
		<title>Cericinema - Registrazione</title>
	</head>
	<body>
	  <header>
	    <a href="index.jsp">
	   		<center><img width="200px" src="img/PC-HUNT.png"></center>
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
	    	</form>
	 </ul>
   			<table id="prodotti">
   			<tr>
   				<th colspan="3">Registrazione</th>
   			</tr>
   			<td id="prodottireg">
					<form action="Registra" method="post" name="form">
							<tr>
								<td colsplan="2">
									<div id="reg1">
										Nome:<br> <input type="text" id="nome" name="nome" maxlength="25" value="" onblur="nomefocus()"><br>
										Cognome:<br> <input type="text" id="cognome" name="cognome"value="" maxlength="30" onblur="cognomefocus()"><br> 
										Email: <br> <input type="text" id="email" name="email" maxlength="50" onblur="emailfocus()"><br>
										Data di Nascita:<br> <input type="text" id="dataDiNascita" name="dataDiNascita" maxlength="10" value="" onblur="datafocus()"><br>
										Residenza:<br> <input type="text" id="residenza" name="residenza" value="" onblur="residenzafocus()"><br> 
										CAP:<br> <input type="text" id="cap" name="cap" value="" maxlength="5" onblur="capfocus()"><br>
										Codice Fiscale:<br> <input type="text" id="codiceFiscale" name="codiceFiscale" maxlength="16" value="" onblur="cffocus()"><br>
										Username:<br> <input type="text" id="username" name="username" maxlength="15" value="" onblur="userfocus()"><br> 
										Password:<br> <input type="password" id="password" name="password" maxlength="30" value="" onblur="passfocus()"><br>
										Ripeti password:<br> <input type="password" id="password2" name="password2" value="" onblur="pass2focus()"><br>
									</div>
								</td>
								<td>
									<div id="reg2">
									<br> <input type="submit" name="Invia" value="   Invia   "onclick="registra()">
									</div>
								</td>
							</tr>
					</form>
			</td>
			</tr>
			</table>
			<footer>
			  <div id="copyright">
			    <p>Design Copyright � Ildo Tiberio. All rights reserved.</p>
			  </div>
			  <div id="svg">
			  	<svg height="10" width="0">
			    	<img src="img/PC-HUNT_logo.svg" height="50" width="80">
			  	</svg>
			  </div>
			</footer>
			<script>
				  function visible(id){
				    if (id==1) {
				      document.getElementById("numeroCarta").style.visibility="hidden";
				      document.getElementById("scadenzaCarta").style.visibility="hidden";
				      document.getElementById("codiceIBAN").style.visibility="visible";
				      document.getElementById("codiceCVV").style.visibility="hidden";
				    }
				    else if (id==2) {
				      document.getElementById("codiceIBAN").style.visibility="hidden";
				      document.getElementById("numeroCarta").style.visibility="visible";
				      document.getElementById("codiceCVV").style.visibility="visible";
				      document.getElementById("scadenzaCarta").style.visibility="visible";
				    }
				  }
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
				  
				  function nomefocus()
				  {
					  if (document.getElementById("nome").value == "")
						{
							document.getElementById("nome").style.borderColor = "red";
							document.getElementById("nome").value = "";
						}
					  else
						  {
						 	 document.getElementById("nome").style.borderColor = none;
						  }
				  }
				  

				  function emailfocus()
				  {
					  if (document.getElementById("email").value == "")
						{
							document.getElementById("email").style.borderColor = "red";
							document.getElementById("email").value = "";
						}
					  else
						  {
						 	 document.getElementById("email").style.borderColor = none;
						  }
				  }
				  
					  //cognome
					 function cognomefocus()
					  {
						  if (document.getElementById("cognome").value == "")
						  {
							document.getElementById("cognome").style.borderColor = "red";
							document.getElementById("cognome").value = "";
						  }
						  else
						  {
						 	 document.getElementById("cognome").style.borderColor = none;
						  }
					  }
					  
					  //residenza
					  function residenzafocus()
					  {
						  if (document.getElementById("residenza").value == "")
						  {
							document.getElementById("residenza").style.borderColor = "red";
							document.getElementById("residenza").value = "";
						  }
						  else
						  {
						 	 document.getElementById("residenza").style.borderColor = none;
						  }
					  }
					  
					//cap
					function capfocus()
					{
						  if (document.getElementById("cap").value == "")
						  {
							document.getElementById("cap").style.borderColor = "red";
							document.getElementById("cap").value = "";
						  }
						  else
						  {
						 	 document.getElementById("cap").style.borderColor = none;
						  }
					}
					
					//codiceFiscale
					function cffocus()
					{
						  if (document.getElementById("codiceFiscale").value == "")
						  {
							document.getElementById("codiceFiscale").style.borderColor = "red";
							document.getElementById("codiceFiscale").value = "";
						  }
						  else
						  {
						 	 document.getElementById("codiceFiscale").style.borderColor = none;
						  }
					}
					
					//username
					function userfocus()
					{
						  if (document.getElementById("username").value == "")
						  {
							document.getElementById("username").style.borderColor = "red";
							document.getElementById("username").value = "";
						  }
						  else
						  {
						 	 document.getElementById("username").style.borderColor = none;
						  }
					}
					
					//password
					function passfocus()
					{
						  if((document.getElementById("password").value == "") && (document.getElementById("password").lenght < 5))
						  {
							document.getElementById("password").style.borderColor = "red";
							document.getElementById("password").value = "";
						  }
						  else
						  {
						 	 document.getElementById("password").style.borderColor = none;
						  }
					}
					
					//password2
					function pass2focus()
					{
						  if(document.getElementById("password2").value == "")
						  {
							document.getElementById("password2").style.borderColor = "red";
							document.getElementById("password2").value = "";
						  }
						  else
						  {
						 	 document.getElementById("password2").style.borderColor = none;
						  }
					}
					
					//codiceIBAN
					function ibanfocus()
					{
						  if(document.getElementById("codiceIBAN").value == "")
							  {
								document.getElementById("codiceIBAN").style.borderColor = "red";
								document.getElementById("codiceIBAN").value = "";
							  }
						  else
						  {
						 	 document.getElementById("codiceIBAN").style.borderColor = none;
						  }
					}
					
					function numfocus()
					{
						if(document.getElementById("numeroCarta").value == "")
						  {
							document.getElementById("numeroCarta").style.borderColor = "red";
							document.getElementById("numeroCarta").value = "";
						  }
						  else
						  {
						 	 document.getElementById("numeroCarta").style.borderColor = none;
						  }
					}
					
					function codicefocus()
					{
						if(document.getElementById("codiceCVV").value == "")
						  {
							document.getElementById("codiceCVV").style.borderColor = "red";
							document.getElementById("codiceCVV").value = "";
						  }
						  else
						  {
						 	 document.getElementById("codiceCVV").style.borderColor = none;
						  }
					}
					
					function scadenzafocus()
					{
						 if(document.getElementById("scadenzaCarta").value == "")
						  {
							document.getElementById("scadenzaCarta").style.borderColor = "red";
							document.getElementById("scadenzaCarta").value = "";
						  }
						  else
						  {
						 	 document.getElementById("scadenzaCarta").style.borderColor = none;
						  }
					}
				  
		/*		  function datafocus()
				  {
					  if (document.form.dataDiNascita.value.substring(2,3) != "/" || document.form.dataDiNascita.value.substring(5,6) != "/" || isNaN(document.form.dataDiNascita.value.substring(0,2)) || isNaN(document.form.dataDiNascita.value.substring(3,5)) || isNaN(document.form.dataDiNascita.value.substring(6,10))) 
					  {
							    alert("Inserire nascita in formato gg/mm/aaaa");
							    document.form.dataDiNascita.value = "";
							    document.form.dataDiNascita.focus();
							    return false;
					  } 
					  else if (document.form.dataDiNascita.value.substring(0,2) > 31) 
					  {
						   alert("Impossibile utilizzare un valore superiore a 31 per i giorni");
						   document.form.dataDiNascita.select();
						   return false;	    
					  }
					  else if (document.form.dataDiNascita.value.substring(3,5) > 12) 
					  {							 
							   alert("Impossibile utilizzare un valore superiore a 12 per i mesi");
							   document.form.dataDiNascita.value = "";
							   document.form.dataDiNascita.focus();
							   return false;
							    
					  } 
					  else if (document.form.dataDiNascita.value.substring(6,10) < 1900) 
					  {
							   alert("Impossibile utilizzare un valore inferiore a 1900 per l'anno");
							   document.form.dataDiNascita.value = "";
							   document.form.dataDiNascita.focus();
							   return false;
					  }
				  }			*/
				  
		</script>
	</body>
</html>
