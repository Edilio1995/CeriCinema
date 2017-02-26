<!DOCTYPE html>
<html>
	<head>
	  <meta charset="UTF-8">
	  <%@  page import="Utils.Proiezione"%>
	  <%@  page import="Utils.userBeans"%>
	  <style>
	    @import url(style.css);
	  </style>
	  <title>Cericinema - Acquisto</title>
	</head>
	<body>
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
	   </li>
	 </ul>
	<%if(request.getAttribute("msg")!=null){%>
			<div id="avviso"><%=request.getAttribute("msg")%></div>
			<%}%>
				   	  
	   	<%	Proiezione film = (Proiezione) session.getAttribute("film"); %>
	   	<form action="AcquistaProiezione" method="post">
			<table id="prodotti" width="50%">
			    <tr>
			    	<td>
			    		Posto<br>
			    		<select id="posto" name="posto">
			    			<option value="A1">A1</option>
			    			<option value="A2">A2</option>
			    			<option value="A3">A3</option>
			    			<option value="A4">A4</option>
			    			<option value="A5">A5</option>
			    			<option value="A6">A6</option>
			    			<option value="A7">A7</option>
			    			<option value="A8">A8</option>
			    			<option value="A9">A9</option>
			    			<option value="A10">A10</option>
			    			<option value="A11">A11</option>
			    			<option value="A12">A12</option>
			    			<option value="A13">A13</option>
			    			<option value="A14">A14</option>
			    			<option value="A15">A15</option>
			    			<option value="A16">A16</option>
			    			<option value="B1">B1</option>
			    			<option value="B2">B2</option>
			    			<option value="B3">B3</option>
			    			<option value="B4">B4</option>
			    			<option value="B5">B5</option>
			    			<option value="B6">B6</option>
			    			<option value="B7">B7</option>
			    			<option value="B8">B8</option>
			    			<option value="B9">B9</option>
			    			<option value="B10">B10</option>
			    			<option value="B11">B11</option>
			    			<option value="B12">B12</option>
			    			<option value="B13">B13</option>
			    			<option value="B14">B14</option>
			    			<option value="B15">B15</option>
			    			<option value="B16">B16</option>
			    			<option value="B17">B17</option>
			    			<option value="B18">B18</option>
			    			<option value="C1">C1</option>
			    			<option value="C2">C2</option>
			    			<option value="C3">C3</option>
			    			<option value="C4">C4</option>
			    			<option value="C5">C5</option>
			    			<option value="C6">C6</option>
			    			<option value="C7">C7</option>
			    			<option value="C8">C8</option>
			    			<option value="C9">C9</option>
			    			<option value="C10">C10</option>
			    			<option value="C11">C11</option>
			    			<option value="C12">C12</option>
			    			<option value="C13">C13</option>
			    			<option value="C14">C14</option>
			    			<option value="C15">C15</option>
			    			<option value="C16">C16</option>
			    			<option value="C17">C17</option>
			    			<option value="C18">C18</option>
			    			<option value="C19">C19</option>
			    			<option value="C20">C20</option>
			    			
			    		</select>
			    	</td>
			    	<td rowspan="2">
			    		<img src="img/pianta.png" width="auto">
			    	</td>
			    </tr>
			    <tr>
			    	<td>
			    		<input type="submit" value="Compra">
			    		<input type="hidden" value="<%=request.getParameter("Indice") %>" name="Indice">
			    	</td>
			    </tr>
			</table>
		</form>
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
