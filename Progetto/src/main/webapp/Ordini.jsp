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
		<%@ include file="MenùDashboard.jsp" %>

		<section class="dashboard">
			<div class="dash-content">				
				<div class="activity padding">
					<div class="title">
						<i class="uil uil-clock-three"></i> 
						<span class="text">Ordini recenti</span>
					</div>
				</div>
			</div>
			
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
					JSONObject ordini=(JSONObject) request.getAttribute("ordini");
					JSONArray listaOrdini=(JSONArray) ordini.get("listaOrdini");
					System.out.println("Ordini JSP: "+listaOrdini);
					if(listaOrdini==null)
					{
				%>
				<p>Non ci sono ordini</p>
				<%
					}
					else
					{
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

		</section>

	</div>
	
	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>
</body>
</html>