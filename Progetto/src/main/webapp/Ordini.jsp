<%@page import="java.util.ArrayList"%>
<%@page import="model.DettagliOrdine"%>
<%@page import="java.sql.Date"%>
<%@page import="model.ColoreDAO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
	
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
		<%@ include file="MenùDashboard.jsp" %>
		<main class="main-container">
        	<div class="main-title">
         		<p class="font-weight-bold">Ordini</p>
        	</div>      
        
				<%
					JSONObject ordini=(JSONObject) request.getAttribute("ordini");
					JSONArray listaOrdini=(JSONArray) ordini.get("listaOrdini");
					if(listaOrdini.length()==0)
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
						ArrayList<DettagliOrdine> listaDettagliOrdini= new ArrayList<DettagliOrdine>();
						for(int i=0; i<listaOrdini.length(); i++)
						{
							JSONObject ordine=(JSONObject) listaOrdini.get(i);
							ColoreDAO coloreDAO= new ColoreDAO();
							String importo= coloreDAO.prezzo(ordine.get("importo").toString());
							Date data=Date.valueOf(ordine.get("data").toString());
							DettagliOrdine dettagliOrdine=new DettagliOrdine();
							dettagliOrdine.setId(Integer.parseInt(ordine.get("id").toString()));
							dettagliOrdine.setImporto(Double.parseDouble(ordine.get("importo").toString()));
							dettagliOrdine.setIva(Double.parseDouble(ordine.get("iva").toString()));
							dettagliOrdine.setData(data);
							dettagliOrdine.setQuantità(Integer.parseInt(ordine.get("quantità").toString()));
							dettagliOrdine.setIdUtente(Integer.parseInt(ordine.get("idUtente").toString()));
							dettagliOrdine.setIdPagamento(Integer.parseInt(ordine.get("idPagamento").toString()));
							dettagliOrdine.setStato(ordine.get("stato").toString());
							listaDettagliOrdini.add(dettagliOrdine);
				%>
				<tr>
					<td><%=dettagliOrdine.getId() %></td>
					<td><%=coloreDAO.prezzo(Double.toString(dettagliOrdine.getImporto())) %> &euro;</td>
					<td><%=dettagliOrdine.getQuantità() %></td>
					<td><%=dettagliOrdine.visualizzaData(dettagliOrdine.getData()) %></td>
					<td><%=dettagliOrdine.getStato() %></td>
					<td>
						<form action="DettaglioOrdineServlet" method="post">
							<button name="dettaglioOrdine" class="noButton" value="<%=i%>">Dettaglio</button>
						</form>
					</td>
				</tr>
				<%			
						}
						request.setAttribute("listaDettagliOrdini", listaDettagliOrdini);
						session.setAttribute("listaDettagliOrdini", listaDettagliOrdini);
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