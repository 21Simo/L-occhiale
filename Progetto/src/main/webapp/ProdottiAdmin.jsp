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
    
    <link rel="stylesheet" href="./css/dashboard.css">
    <link rel="stylesheet" href="./css/dettaglioProdotti.css">
     
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    
    <link rel="stylesheet" href="./css/multiple.css">
    
    <link rel="stylesheet" href="./css/prodottiAdmin.css">

    <title>Gestione prodotti</title>
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
			
			<%-- 
			<div class="gallery">
				
				<%
				for (int i = 0; i < json.length(); i++) {
					JSONObject prodotto = (JSONObject) json.get("prodotto" + i);
					JSONObject colore = (JSONObject) prodotto.get("colore0");
				%>
				
				<div class="contentAdmin" id="card-<%=i%>">
					<form action="DettaglioProdottoServlet" method="post">
						<input name="admin" type="text" hidden="true" value="true">
						<button type="submit" name="bottone" value="<%=prodotto.get("id")%>/colore0" class="dettagli" id="bottone-<%=i%>">
							<%
							session.setAttribute("prodotto", json);
							%>
							<img src="./img/prodotti/<%=colore.get("immagine")%>"
								class="img-prodotto" id="img-<%=i%>">
							<h3><%=prodotto.get("nome")%></h3>
							<h6 id="prezzoProdotto-<%=i%>">
								<%=colore.get("prezzo")%>
								&euro;
							</h6>
						</button>
					</form>
					<div class="">
						<div class="color-groups">
							<%
							for (int k = 0; k < prodotto.names().length(); k++) {
								if (prodotto.names().get(k).toString().contains("colore") == true) {
									String nome = prodotto.names().get(k).toString();
									JSONObject coloreSelezionato = (JSONObject) prodotto.get(prodotto.names().get(k).toString());
									//System.out.println("JSP prodotto: "+coloreSelezionato);
									if (nome.equals("colore0")) {
							%>

							<img title="colore0" name="<%=coloreSelezionato.get("id")%>"
								src="./img/prodotti/<%=coloreSelezionato.get("immagine")%>"
								class="color card-<%=i%> active-color"
								id="<%=coloreSelezionato.get("immagine")%>"
								onclick="cambiaImmagine(id,<%=coloreSelezionato.get("prezzo")%>)">

							<%
							} else {
							%>

							<img title="<%=nome%>" name="<%=coloreSelezionato.get("id")%>"
								src="./img/prodotti/<%=coloreSelezionato.get("immagine")%>"
								class="color card-<%=i%>"
								id="<%=coloreSelezionato.get("immagine")%>"
								onclick="cambiaImmagine(id,<%=coloreSelezionato.get("prezzo")%>)">

							<%
							}
							}
							}
							%>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
			--%>
		</section>

	</div>

	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>
	<script type="text/javascript" charset="UTF-8" src="./script/cambiaImmagine.js"></script>
</body>
</html>