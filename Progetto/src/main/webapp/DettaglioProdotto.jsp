<%@page import="model.ColoreDAO"%>
<%@page import="model.ProdottoCarrello"%>
<%@page import="java.util.ArrayList"%>
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
	<link rel="stylesheet" href="./css/modalAggiungiCarrello.css">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp"%>  
	</nav>
	
	<form enctype="multipart/form-data" method="post" action="AggiungiCarrelloServlet" onsubmit="return validazioneDettagli()">
		<div class="row" id="card-1">
			<div class="column left">
				<input type="text" id="immagineProdotto" name="immagineProdotto" value="./img/prodotti/<%=colore.get("immagine") %>" hidden="true">
				<img src="./img/prodotti/<%=colore.get("immagine") %>" class="img-prodotto" id="img-1">
			</div>
			<div class="column right">
				<input type="text" id="marcaProdotto" name="marcaProdotto" value="<%=prodotto.get("marca") %>" hidden="true">
				<p class="coloreTesto"><%=prodotto.get("marca") %></p>
				<input type="text" id="nomeProdotto" name="nomeProdotto" value="<%=prodotto.get("nome") %>" hidden="true">
				<h3 class="coloreTesto" id="nome"><%=prodotto.get("nome") %></h3>
				<h6 class="coloreTesto" id="prezzo"><%=colore.get("prezzo") %> &euro;</h6>
				<input type="text" id="prezzoProdotto" name="prezzoProdotto" value="<%=colore.get("prezzo") %>" hidden="true">
				<div class="containerToogle" id="toogle">
					<label class="switch switchToogle etichettaToogle">
		        		<input type="checkbox" name="modificaLenti" id="modificaLenti" value="Non graduati">
		        		<label for="modificaLenti" data-on="Graduati" data-off="Non graduati" class="switchToogleInterno etichettaToogle"></label>
		    		</label>
		    		<input type="text" id="gradazione" name="gradazione" value="Non graduati" hidden="true">
				</div>
				<label for="sceltaFile" class="caricamentoFile elementoNascosto" id="etichettaSceltaFile">
   					Carica la prescrizione medica
				</label>
				<span id="erroreFile"></span>
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
						<input type="text" id="coloreProdottoAttivo" name="coloreProdottoAttivo" value="<%=nome %>" hidden="true">
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
				<div class="contenitoreQuantita">
					<label for="quantità" class="labelQuantita coloreTesto">
						Quantità: 
					</label>
					<input type="text" id="quantitàProdotto" name="quantitaProdotto" value="1" hidden="true">
					<select id="quantità" class="menuQuantita" onchange="quantita()">
					<%
					Carrello carrelloSessione;
					if(utente!=null)
					{
						carrelloSessione=(Carrello) session.getAttribute("carrelloUtente");
					}
					else
					{
						carrelloSessione=(Carrello) session.getAttribute("carrello");
					}
					int quantitàSospesa=0;
					if(carrelloSessione!=null)
					{
						ArrayList<ProdottoCarrello> listaCarrello= carrelloSessione.getListaProdotti();
						for(int i=1; i<listaCarrello.size(); i++)
						{
							if(listaCarrello.get(i).getColore().getId()== Integer.parseInt(colore.get("id").toString()))
							{
								quantitàSospesa= listaCarrello.get(i).getColore().getQuantità();
							}
						}
					}
					int quantità=Integer.parseInt(colore.get("quantità").toString())-quantitàSospesa;
					if(quantità<=0)
					{
					%>
						<option value="0">0</option>
					<%
					}
					for(int i=1; i<=quantità; i++)
					{
					%>
						<option value="<%=i%>"><%=i%></option>
					<%
					}
					%>
					</select>
					<%
					if(quantità<=0)
					{
					%>
					<span id="disponibilità">Prodotto non disponibile</span>
					<%
					}
					%>
				</div>
				<%
				session.setAttribute("dettaglioProdotto", prodotto);
				session.setAttribute("coloreSelezionato", coloreSelezionato);
				if(quantità<=0)
				{
				%>
				<button type="submit" class="bottoneDettagli" name="bottoneCarello" value="<%=prodotto.get("id")%>" id="myBtn" onclick="aggiungiCarrello()" disabled="disabled">Aggiungi al carrello</button>
				<%
				}
				else
				{
				%>
				<button type="submit" class="bottoneDettagli" name="bottoneCarello" value="<%=prodotto.get("id")%>" id="myBtn" onclick="aggiungiCarrello()">Aggiungi al carrello</button>
				<%
				}
				%>
			</div>
		</div>
	</form>
	
	<%
	boolean modal=false;
	if(request.getAttribute("modal")!=null)
	{
		boolean modalPar=(Boolean) request.getAttribute("modal");
		modal=true;
	}
	if(modal==true)
	{
	%>
	<div id="myModal" class="modal">
		<div class="modal-content">
			<div class="chiudi">
				<div class="title">Prodotto aggiunto nel carrello</div>
				<span class="close" onmouseup="prova()">&times;</span>
			</div>
			<div class="contenuto">
				<div class="shopping-cart">
					<div class="item">
						<div class="image">
							<img src="./img/prodotti/<%=colore.get("immagine")%>" alt="" height="100%" width="100%" id="immagineModal" />
						</div>

						<div class="description">
							<span><%=prodotto.get("nome")%></span> <span id="colorazione"><%=colore.get("colore")%></span>
						</div>

						<div class="total-price" id="prezzoModal"><%=colore.get("prezzo")%> &euro;</div>
					</div>
				</div>
				<div class="infoCarello">
				<%
				Carrello carrelloDettagli;
				if(utente!=null)
				{
					carrelloDettagli=(Carrello) session.getAttribute("carrelloUtente");
				}
				else
				{
					carrelloDettagli=(Carrello) session.getAttribute("carrello");
				}
				if(carrelloDettagli!=null)
				{
					ColoreDAO coloreDAO= new ColoreDAO();
					String totale= coloreDAO.prezzo(carrelloDettagli.getTotaleCosto());
				%>
					<p id="totaleCarello">Totale costo: <%=totale %> &euro;</p>
					<p id="quantitàCarello">Totale prodotti nel carello: <%=carrelloDettagli.getQuantitaCarrello() %></p>
				<%
				}
				else
				{
				%>
					<p id="totaleCarello">Totale costo: 0 &euro;</p>
					<p id="quantitàCarello">Totale prodotti nel carello: 0</p>
				<%
				}	
				%>
				</div>
				<div class="infoCarelloBottoni">
					<form action="Prodotto" method="post" class="spazioBottoni">
						<button name="prodotti" class="bottoneModal" value="header">Continua lo shopping</button>
					</form>
					<a href="Carrello.jsp">
						<button class="bottoneModal">Vai al carrello</button>
					</a>
				</div>
			</div>
		</div>
	</div>
	<%
	}
	%>
	
	<footer>
		<%@ include file="Footer.jsp"%>
	</footer>
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/cambiaImmagineDettagli.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/modalAggiungiCarrello.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/validazioneFileGradazioneDettagli.js"></script>
	
	<script type="text/javascript">	
		function aggiungiCarrello() 
		{
			<%
			String id= prodotto.get("id").toString();
			%>
			var bottone= <%=id%>+"/colore0";
			var quantitàSelezionata=$('#quantitàProdotto').val();
			var ultimaOption= $('#quantità option:last-child').val();
			for(var i=1; i==quantitàSelezionata; i++)
			{
				$('#quantità option:last-child').remove();
			}
			$.ajax
			({
				data: {bottone: bottone},
				url: 'DettaglioProdottoServlet',
				method: 'POST',
				success: function (risultati)
				{
					
				}
			})
		}
	</script>
</body>
</html>