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
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="./css/dashboard.css">
	<link rel="stylesheet" href="./css/dettaglioProdotti.css">
	<link rel="stylesheet" href="./css/dettaglioOrdine.css">
	<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	<title>Ordini</title>
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	
	<div class="contenitoreDashboard">
		<%@ include file="MenùDashboardAdmin.jsp" %>

		<section class="dashboard">
			<div class="dash-content">				
				<div class="activity padding">
					<div class="title">
						<i class="uil uil-clock-three"></i> 
						<span class="text">Ordini recenti</span>
					</div>
				</div>
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

		</section>

	</div>
	
	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>
</body>
</html>