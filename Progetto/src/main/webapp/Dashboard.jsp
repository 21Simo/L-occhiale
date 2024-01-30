<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.Date"%>
<%@page import="model.ColoreDAO"%>
<%@page import="model.DettagliOrdine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.DettagliOrdineDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	JSONObject dettagliOrdiniJson= (JSONObject) request.getAttribute("dettagliOrdini");
	System.out.println(dettagliOrdiniJson);
	JSONArray listaDettagliOrdini=(JSONArray) dettagliOrdiniJson.get("listaDettagliOrdini");
	System.out.println(listaDettagliOrdini.length());
%>
    
<!DOCTYPE html>
<html>
<head>	
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Dashboard</title>

    <!-- Montserrat Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="./css/dashboard.css">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	<div class="grid-container">
		<%@ include file="MenùDashboard.jsp" %>
      <main class="main-container">
        <div class="main-title">
          <p class="font-weight-bold">Ordini recenti</p>
        </div>
				<%
					System.out.println("Dashboard JSP: "+utente);
					if(utente==null)
					{
						response.sendRedirect("Login.jsp");
					}
					else
					{
						if(listaDettagliOrdini==null)
						{
				%>
				<p>Non ci sono ordini recenti. </p>
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
							ArrayList<DettagliOrdine> listaDettagliOrdiniOggetto= new ArrayList<DettagliOrdine>();
							for(int i=0; i<listaDettagliOrdini.length(); i++)
							{
								JSONObject dettagliOrdineJson=(JSONObject) listaDettagliOrdini.get(i);
								ColoreDAO coloreDAO= new ColoreDAO();
								String importo= coloreDAO.prezzo(dettagliOrdineJson.get("importo").toString());
								Date data= Date.valueOf(dettagliOrdineJson.get("data").toString());
								DettagliOrdine dettagliOrdine= new DettagliOrdine();
								dettagliOrdine.setId(Integer.parseInt(dettagliOrdineJson.get("id").toString()));
								dettagliOrdine.setImporto(Double.parseDouble(dettagliOrdineJson.get("importo").toString()));
								dettagliOrdine.setIva(Double.parseDouble(dettagliOrdineJson.get("iva").toString()));
								dettagliOrdine.setData(data);
								dettagliOrdine.setQuantità(Integer.parseInt(dettagliOrdineJson.get("quantità").toString()));
								dettagliOrdine.setIdUtente(Integer.parseInt(dettagliOrdineJson.get("idUtente").toString()));
								dettagliOrdine.setIdPagamento(Integer.parseInt(dettagliOrdineJson.get("idPagamento").toString()));
								dettagliOrdine.setStato(dettagliOrdineJson.get("stato").toString());
								listaDettagliOrdiniOggetto.add(dettagliOrdine);
				%>
				<tr> 
					<td><%=dettagliOrdineJson.get("id") %></td> 
					<td><%=importo %> &euro;</td>
					<td><%=dettagliOrdineJson.get("quantità") %></td>
					<td><%=dettagliOrdine.visualizzaData(data) %></td>
					<td>
						<form action="DettaglioOrdineServlet" method="post">
							<button name="dettaglioOrdine" class="noButton" value="<%=i %>">Dettaglio</button>
						</form>
					</td>
				</tr>
				<%
							}
							request.setAttribute("listaDettagliOrdini", listaDettagliOrdiniOggetto);
							session.setAttribute("listaDettagliOrdini", listaDettagliOrdiniOggetto);
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