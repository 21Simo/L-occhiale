<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	JSONObject ultimiProdottiJson=(JSONObject) request.getAttribute("ultimiProdotti");
	JSONArray listaUltimiProdotti=(JSONArray) ultimiProdottiJson.get("listaUltimiProdotti");
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="./css/dettaglioProdotti.css">
     
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    
    <!-- Montserrat Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="./css/dashboard.css">

    <title>Dashboard</title>
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	<div class="grid-container">
		<%@ include file="MenùDashboardAdmin.jsp" %>
		<main class="main-container">
        	<div class="main-title">
          		<p class="font-weight-bold">Prodotti inseriti di recente</p>
        	</div>
        	<%
        	if(listaUltimiProdotti.length()==0)
        	{
        	%>
        	<p>Non ci sono prodotti inseriti di recente.</p>
        	<%
        	}
        	else
        	{
        	%>
        	<table id="ordini">
				<tr>
					<th>Id prodotto</th>
					<th>Nome prodotto</th>
					<th>Marca</th>
					<th>Sesso</th>
				</tr>
			<%
				for(int i=0; i<listaUltimiProdotti.length(); i++)
				{
					JSONObject prodottoJson=(JSONObject) listaUltimiProdotti.get(i);
			%>
				<tr>
					<td><%=prodottoJson.get("id") %></td>
					<td><%=prodottoJson.get("nome") %></td>
					<td><%=prodottoJson.get("marca") %></td>
					<td><%=prodottoJson.get("sesso") %></td>
				</tr>
			<%
				}
        	}
			%>
			</table>
        </main>
	</div>

	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>
	
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
    <script src="./script/dashboard.js"></script>
</body>
</html>