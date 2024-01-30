<%@page import="model.DettagliOrdine"%>
<%@page import="java.sql.Date"%>
<%@page import="model.ColoreDAO"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<title>Ordini</title>
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	<div class="grid-container">
		<%@ include file="MenùDashboardAdmin.jsp" %>
		<main class="main-container">
        	<div class="main-title">
          		<p class="font-weight-bold">Ordini</p>
        	</div>
        	<%
					JSONObject ordini=(JSONObject) request.getAttribute("ordiniJson");
					JSONArray listaOrdini=(JSONArray) ordini.get("ordini");
					System.out.println("Ordini JSP: "+listaOrdini);
					if(listaOrdini==null || listaOrdini.length()==0)
					{
				%>
				<p>Non ci sono ordini</p>
				<%			
					}
					else
					{
				%>
				<table id="ordini">
				<tr>
					<th>Numero d'ordine</th>
					<th>Importo</th>
					<th>Quantità</th>
					<th>Data</th>
					<th>Stato</th>
					<th></th>
				</tr>
				<%
						for(int i=0; i<listaOrdini.length(); i++)
						{
							JSONObject ordine=(JSONObject) listaOrdini.get(i);
							System.out.println(ordine);
							ColoreDAO coloreDAO= new ColoreDAO();
							String importo= coloreDAO.prezzo(ordine.get("importo").toString());
							Date data=Date.valueOf(ordine.get("data").toString());							
							DettagliOrdine dettagliOrdine=new DettagliOrdine();
				%>
				<tr>
					<td><%=ordine.get("id") %></td>
					<td><%=importo %> &euro;</td>
					<td><%=ordine.get("quantità") %></td>
					<td><%=dettagliOrdine.visualizzaData(data) %></td>
					<td><%=ordine.get("stato") %></td>
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