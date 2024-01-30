<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	JSONObject json= (JSONObject) request.getAttribute("prodotti");
	session.setAttribute("prodottiAdmin", json);
%>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="./css/dettaglioProdotti.css">
     
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    
    <link rel="stylesheet" href="./css/multiple.css">
    
    <link rel="stylesheet" href="./css/prodottiAdmin.css">
    
    <!-- Montserrat Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="./css/dashboard.css">

    <title>Gestione prodotti</title>
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	<div class="grid-container">
		<%@ include file="MenùDashboardAdmin.jsp" %>
		<main class="main-container">
        	<div class="main-title">
          		<p class="font-weight-bold coloreTesto">Gestisci i prodotti</p>
        	</div>
        	
        	<div class="contenitoreBottoneInserisci">
				<a href="InserisciProdotto.jsp">
					<button class="bottoneInserisci">Inserisci un prodotto</button>
				</a>
			</div>
			<table id="ordini">
				<tr>
					<th>Nome prodotto</th>
					<th>Marca</th>
					<th>Genere</th>
					<th></th>
					<th></th>
				</tr>
				<%
				for (int i = 0; i < json.length(); i++) 
				{
					JSONObject prodotto = (JSONObject) json.get("prodotto" + i);
					JSONObject colore = (JSONObject) prodotto.get("colore0");
					System.out.println(prodotto);
					String genere;
					if(prodotto.get("sesso").equals("M"))
					{
						genere="Uomo";
					}
					else
					{
						genere="Donna";
					}
				%>
				<tr>
					<td><%=prodotto.get("nome") %></td>
					<td><%=prodotto.get("marca") %></td>
					<td><%=genere %></td>
					<td>
						<form action="DettaglioProdottoServlet" method="post">
							<input name="admin" type="text" hidden="true" value="true">
							<input name="azione" type="text" hidden="true" value="modifica">
							<button type="submit" name="bottone" class="noButton" value="<%=prodotto.get("id")%>/colore0" id="bottone-<%=i%>">Modifica</button>
						</form>
					</td>
					<td>
						<form action="DettaglioProdottoServlet" method="post">
							<input name="admin" type="text" hidden="true" value="true">
							<input name="azione" type="text" hidden="true" value="elimina">
							<button type="submit" name="bottone" class="noButton" value="<%=prodotto.get("id")%>/colore0" id="bottone-<%=i%>">Elimina</button>
						</form>
					</td>
				</tr>
				<%
				}
				%>
			</table>
        	
        </main>
	</div>

	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>
	<script type="text/javascript" charset="UTF-8" src="./script/cambiaImmagine.js"></script>
	<!-- Scripts -->
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
    <!-- ApexCharts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.35.3/apexcharts.min.js"></script>
    <!-- Custom JS -->
    <script src="./script/dashboard.js"></script>
</body>
</html>