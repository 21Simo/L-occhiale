<%@page import="java.sql.Date"%>
<%@page import="model.ColoreDAO"%>
<%@page import="org.json.JSONArray"%>
<%@page import="model.Prodotto"%>
<%@page import="model.DettagliOrdine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	JSONObject dettaglioOrdineJson=(JSONObject) request.getAttribute("dettaglioOrdineJson");
%>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="./css/dettaglioProdotti.css">
    <link rel="stylesheet" href="./css/dettaglioOrdine.css">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    
    <!-- Montserrat Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="./css/dashboard.css">
	<title>Dettaglio ordine</title>
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %> 
	</nav>
	<div class="grid-container">
		<%@ include file="MenùDashboard.jsp" %>
		<main class="main-container">
        	<div class="main-title">
          		<p class="font-weight-bold">Dettaglio ordine</p>
        	</div>
        	
        	<%
			DettagliOrdine dettaglioOrdine=(DettagliOrdine) dettaglioOrdineJson.get("dettagliOrdine");
			ColoreDAO coloreDAO= new ColoreDAO();
			String importo= coloreDAO.prezzo(Double.toString(dettaglioOrdine.getImporto()));
			String indirizzo=(String) dettaglioOrdineJson.get("indirizzo");
			JSONArray prodotti=(JSONArray) dettaglioOrdineJson.get("prodotti");
			JSONArray ordine=(JSONArray) dettaglioOrdineJson.get("ordine");
			%>
			
			<div class="top">
				<p class="coloreTesto" id="numeroOrdine">Numero ordine: <%=dettaglioOrdine.getId() %></p>
				<p class="coloreTesto">Stato: <%=dettaglioOrdine.getStato() %></p>
			</div>
			<p id="dataOrdine" class="padding coloreTesto">Data: <%=dettaglioOrdine.visualizzaData(dettaglioOrdine.getData())%></p>
			<div class="top">
				<p id="nomecognome" class="padding coloreTesto">Nome e cognome: <%=utente.getNome() %> <%=utente.getCognome() %></p>
				<p id="indirizzo" class="padding coloreTesto">Indirizzo: <%=indirizzo %></p>
			</div>
			<p id="telefono" class="padding coloreTesto">Telefono: <%=utente.getTelefono() %></p>
			
			<table id="dettagliOrdini">
				<tr>
					<th></th>
					<th>Nome</th>
					<th>Colore</th>
					<th>Gradazione</th>
					<th>Prezzo</th>
					<th>Quantità</th>
					<th></th>
				</tr>
					<%
					for(int j=0; j<ordine.length(); j++)
					{
						JSONObject ordineJson=(JSONObject) ordine.get(j);
					%>
					<tr>
						<td>
							<img alt="immagineProdotto" class="immagine" name="./img/prodotti/<%=ordineJson.get("immagineProdotto")%>" src="./img/prodotti/<%=ordineJson.get("immagineProdotto")%>">
						</td>
						<td><%=ordineJson.get("nomeProdotto") %></td>
						<td><%=ordineJson.get("coloreProdotto") %></td>
					<%
						String graduati;
						if(ordineJson.names().toString().contains("file")==true)
						{
							graduati="Graduati";
					%>
					<td><%=graduati %></td>
					<% 
						}
						else
						{
							graduati="Non graduati";
					%>
					<td><%=graduati %></td>
					<%
						}
					%>
					<td><%=coloreDAO.prezzo(ordineJson.get("prezzo").toString()) %> &euro;</td>
					<td><%=ordineJson.get("quantitàProdotto") %></td>
					<%
					}
					%>
				</tr>
			</table>
			<p id="totale" class="padding coloreTesto">Totale: <%=importo %> &euro;</p>
			<button class="padding fattura" onclick="generaPDF()">Scarica la fattura</button>
        	
        </main>
	</div>	

	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>

	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
    <script src="./script/dashboard.js"></script>
    <script src="https://unpkg.com/jspdf-invoice-template@latest/dist/index.js" integrity="sha384-LUj5IQIFOuPHPTD9sjLomE0ogaJ/1qnn/uu1forYO0VZgs05t9nClPq/wmDncYR8" crossorigin="anonymous"></script>
    <script src="./script/fattura.js" charset="UTF-8"></script>
</body>
</html>