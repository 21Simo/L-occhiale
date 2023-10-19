<%@page import="org.w3c.dom.Element"%>
<%@page import="org.w3c.dom.html.HTMLDocument"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	JSONObject prodotto= (JSONObject) request.getAttribute("prodotto");
	String coloreSelezionato=request.getAttribute("coloreSelezionato").toString();
	JSONObject colore= (JSONObject) prodotto.get(coloreSelezionato);
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dettaglio Prodotto</title>
	<link rel="stylesheet" href="./css/dettaglioProdotti.css">
	<link rel="stylesheet" href="./css/modalAggiungiCarello.css">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp"%>
	</nav>
	
	<div class="row" id="card-1">
		<div class="column left" style="background-color: #aaa;">
			<img src="./img/prodotti/<%=colore.get("immagine") %>" class="img-prodotto" id="img-1">
		</div>
		<div class="column right" style="background-color: #bbb;">
			<p><%=prodotto.get("marca") %></p>
			<h3 id="nome"><%=prodotto.get("nome") %></h3>
			<h6 id="prezzo"><%=colore.get("prezzo") %> &euro;</h6>
  			<div class="containerToogle" id="toogle">
				<label class="switch switchToogle etichettaToogle">
		        	<input type="checkbox" name="modificaLenti" id="modificaLenti" value="Non graduati">
		        	<label for="modificaLenti" data-on="Graduati" data-off="Non graduati" class="switchToogleInterno etichettaToogle"></label>
		    	</label>
			</div>
			
			<label for="sceltaFile" class="caricamentoFile elementoNascosto" id="etichettaSceltaFile">
   				Carica la prescrizione medica
			</label>
			<input name="uploadDocument" type="file" id="sceltaFile" accept=".jpg,.jpeg,.pdf,doc,docx,application/msword,.png" class="elementoNascosto"/>
			<div class="color-content">
				<div class="color-groups">					
					<%
						for(int i=0; i<prodotto.names().length(); i++)
						{
							if(prodotto.names().get(i).toString().contains("colore")==true)
							{
								String nome=prodotto.names().get(i).toString();
								JSONObject coloreJSON=(JSONObject) prodotto.get(nome);
								if(nome.equals(coloreSelezionato))
								{
					%>
									<img title="<%=nome %>" name="<%=coloreJSON.get("id") %>" src="./img/prodotti/<%=coloreJSON.get("immagine") %>" class="color card-1 active-color" id="<%=coloreJSON.get("immagine") %>" onclick="cambiaImmagine(id, <%=coloreJSON.get("prezzo")%>, <%=coloreJSON.get("quantità")%>)">
					<%
								}
								else
								{
					%>				
									<img title="<%=nome %>" name="<%=coloreJSON.get("id") %>" src="./img/prodotti/<%=coloreJSON.get("immagine") %>" class="color card-1" id="<%=coloreJSON.get("immagine") %>" onclick="cambiaImmagine(id, <%=coloreJSON.get("prezzo")%>, <%=coloreJSON.get("quantità")%>)">
					<%
								}
							}
						}
					%>
				</div>
			</div>
			<%-- <p class="quantita">Quantità: <%=colore.get("quantità") %></p>--%>
			<div class="contenitoreQuantita">
				<label for="quantità" class="labelQuantita">
					Quantità: 
				</label>
				<select id="quantità" class="menuQuantita">
				<%
					int quantità=Integer.parseInt(colore.get("quantità").toString());
					for(int i=1; i<=quantità; i++)
					{
				%>
					<option value="<%=i %>"><%=i %></option>
				<%
					}
				%>
				</select>
			</div>
			<%
				session.setAttribute("dettaglioProdotto", prodotto);
			%>
			<button type="submit" class="bottoneDettagli" name="bottoneCarello" value="<%=prodotto.get("id")%>" id="myBtn" onclick="servlet()">Aggiungi al carello</button>
		</div>
	</div>
	
	<!-- Modal -->
	<div id="myModal" class="modal">
		<div class="modal-content">
			<div class="chiudi">
				<div class="title">Prodotto aggiunto nel carello</div>
				<span class="close">&times;</span>
			</div>
			<div class="contenuto">
			<div class="shopping-cart">
				<div class="item">
					<div class="image">
						<img src="./img/prodotti/<%=colore.get("immagine") %>" alt="" height="100%" width="100%" id="immagineModal" />
					</div>

					<div class="description">
						<span><%=prodotto.get("nome") %></span> <span id="colorazione">White</span>
					</div>

					<div class="total-price" id="prezzoModal">549 &euro;</div>
				</div>
			</div>
			
			<div class="infoCarello">
				<p id="totaleCarello">Totale costo: 500 &euro;</p>
				<p id="quantitàCarello">Totale prodotti nel carello: 5</p>
			</div>
			<div class="infoCarelloBottoni">
				<form action="Prodotto" method="post">
					<button class="bottoneModal">Continua lo shopping</button>
				</form>
				<a href="Carello.jsp">
					<button class="bottoneModal">Vai al carello</button>
				</a>
			</div>
		</div>
	</div>
	</div>
	
	<footer>
		<%@ include file="Footer.jsp"%>
	</footer>
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/cambiaImmagineDettagli.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/modalAggiungiCarello.js"></script>
</body>
</html>