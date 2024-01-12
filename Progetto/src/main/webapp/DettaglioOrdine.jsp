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
	System.out.println("DettaglioOrdine JSP: "+dettaglioOrdineJson);
%>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="./css/dashboard.css">
    <link rel="stylesheet" href="./css/dettaglioProdotti.css">
    <link rel="stylesheet" href="./css/dettaglioOrdine.css">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	<title>Dettaglio ordine</title>
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>

	<div class="contenitoreDashboard">
		<%@ include file="MenùDashboard.jsp" %>

		<section class="dashboard">
			<div class="dash-content">
				<div class="overview">
					<div class="title">
						<i class="uil uil-tachometer-fast-alt"></i> <span class="text">Dashboard</span>
					</div>

					<div class="boxes">
						<div class="box box1">
							<i class="uil uil-thumbs-up"></i> <span class="text">Total
								Likes</span> <span class="number">50,120</span>
						</div>
						<div class="box box2">
							<i class="uil uil-comments"></i> <span class="text">Comments</span>
							<span class="number">20,120</span>
						</div>
						<div class="box box3">
							<i class="uil uil-share"></i> <span class="text">Total
								Share</span> <span class="number">10,120</span>
						</div>
					</div>
				</div>

				
				<div class="activity padding">
					<div class="title">
						<i class="uil uil-clock-three"></i> 
						<span class="text">Ordini recenti</span>
					</div>
				</div>
			</div>
			
			<%
				DettagliOrdine dettaglioOrdine=(DettagliOrdine) dettaglioOrdineJson.get("dettagliOrdine");
				ColoreDAO coloreDAO= new ColoreDAO();
				String importo= coloreDAO.prezzo(Double.toString(dettaglioOrdine.getImporto()));
				System.out.println("DettaglioOrdine JSP: "+dettaglioOrdine.getId());
				System.out.println("DettaglioOrdine JSP importo: "+dettaglioOrdine.getImporto());
				String indirizzo=(String) dettaglioOrdineJson.get("indirizzo");
				JSONArray prodotti=(JSONArray) dettaglioOrdineJson.get("prodotti");
				//System.out.println("DettagliOrdine JSP: "+prodotti.get(0).getColore().getColore());
				System.out.println("DettaglioOrdine JSP: "+prodotti.length());
				JSONArray ordine=(JSONArray) dettaglioOrdineJson.get("ordine");
				System.out.println("DettaglioOrdine JSP: "+ordine);
				System.out.println("Utente: "+request.getSession().getAttribute("utente"));
			%>
			
			<div class="top">
				<p id="numeroOrdine">Numero ordine: <%=dettaglioOrdine.getId() %></p>
				<p>Stato: <%=dettaglioOrdine.getStato() %></p>
			</div>
			<p id="dataOrdine" class="padding">Data: <%=dettaglioOrdine.visualizzaData(dettaglioOrdine.getData())%></p>
			<div class="top">
				<p id="nomecognome" class="padding">Nome e cognome: <%=utente.getNome() %> <%=utente.getCognome() %></p>
				<p id="indirizzo" class="padding">Indirizzo: <%=indirizzo %></p>
			</div>
			<p id="telefono" class="padding">Telefono: <%=utente.getTelefono() %></p>
			
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
				for(int i=0; i<prodotti.length(); i++)
				{
					JSONObject prodotto=(JSONObject) prodotti.get(i);
					System.out.println("DettaglioOrdine JSP: "+prodotto);
					JSONObject colore=(JSONObject) prodotto.get("colore");
					System.out.println("DettaglioOrdine JSP: "+colore);
					System.out.println("DettaglioOrdine JSP: "+colore.get("immagine"));
				//}
			%>
				<tr>
					<td>
						<img alt="immagineProdotto" class="immagine" name="./img/prodotti/<%=colore.get("immagine")%>" src="./img/prodotti/<%=colore.get("immagine")%>">
					</td>
					<td><%=prodotto.get("nome") %></td>
					<td><%=colore.get("colore") %></td>
					<%
						for(int j=0; j<ordine.length(); j++)
						{
							JSONObject ordineJson=(JSONObject) ordine.get(j);
							System.out.println("DettaglioOrdine JSP: "+ordineJson);
							System.out.println("DettaglioOrdine JSP: "+ordineJson.get("idProdotto"));
							System.out.println("DettaglioOrdine JSP: "+prodotto.get("id"));
							if(ordineJson.get("idProdotto").equals(prodotto.get("id")))
							{
								System.out.println("Entro");
								System.out.println("DettaglioOrdine JSP: "+ ordineJson.names().toString().contains("file"));
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
								System.out.println(graduati);
					%>
					<td><%=colore.get("prezzo") %> &euro;</td>
					<td><%=ordineJson.get("quantitàProdotto") %></td>
					<%
							}
						}
					%>
				</tr>
			<%
				}
			%>
			</table>
			<p id="totale" class="padding">Totale: <%=importo %> &euro;</p>
			<button class="padding fattura" onclick="generaPDF()">Scarica la fattura</button>

		</section>

	</div>

	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>

	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
    <script src="./script/dashboard.js"></script>
    <script src="https://unpkg.com/jspdf-invoice-template@latest/dist/index.js"></script>
    <script src="./script/fattura.js" charset="UTF-8"></script>
</body>
</html>