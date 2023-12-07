<%@page import="model.ProdottoCarello"%>
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
	<link rel="stylesheet" href="./css/modalAggiungiCarello.css">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp"%>
	</nav>
	
	<form enctype="multipart/form-data" method="post" action="AggiungiCarelloServlet" onsubmit="return validazioneDettagli()">
		<div class="row" id="card-1">
			<div class="column left" style="background-color: #aaa;">
				<input type="text" id="immagineProdotto" name="immagineProdotto" value="./img/prodotti/<%=colore.get("immagine") %>" hidden="true">
				<img src="./img/prodotti/<%=colore.get("immagine") %>" class="img-prodotto" id="img-1">
			</div>
			<div class="column right" style="background-color: #bbb;">
				<input type="text" id="marcaProdotto" name="marcaProdotto" value="<%=prodotto.get("marca") %>" hidden="true">
				<p><%=prodotto.get("marca") %></p>
				<input type="text" id="nomeProdotto" name="nomeProdotto" value="<%=prodotto.get("nome") %>" hidden="true">
				<h3 id="nome"><%=prodotto.get("nome") %></h3>
				<h6 id="prezzo"><%=colore.get("prezzo") %> &euro;</h6>
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
						<img title="<%=nome %>" name="<%=coloreJSON.get("id") %>" src="./img/prodotti/<%=coloreJSON.get("immagine") %>" class="color card-1 active-color" id="<%=coloreJSON.get("immagine") %>" onclick="cambiaImmagine(id, <%=coloreJSON.get("prezzo")%>, <%=coloreJSON.get("quantit�")%>)">
						<input type="text" id="coloreProdottoAttivo" name="coloreProdottoAttivo" value="<%=nome %>" hidden="true">
						<%
									}
									else
									{
						%>
						<img title="<%=nome %>" name="<%=coloreJSON.get("id") %>" src="./img/prodotti/<%=coloreJSON.get("immagine") %>" class="color card-1" id="<%=coloreJSON.get("immagine") %>" onclick="cambiaImmagine(id, <%=coloreJSON.get("prezzo")%>, <%=coloreJSON.get("quantit�")%>)">
						<%
									}
								}
							}
						%>
					</div>
				</div>
				<div class="contenitoreQuantita">
					<label for="quantit�" class="labelQuantita">
						Quantit�: 
					</label>
					<input type="text" id="quantit�Prodotto" name="quantitaProdotto" value="1" hidden="true">
					<select id="quantit�" class="menuQuantita" onchange="quantita()">
					<%
						Carello carelloSessione;
						if(utente!=null)
						{
							carelloSessione=(Carello) session.getAttribute("carelloUtente");
						}
						else
						{
							carelloSessione=(Carello) session.getAttribute("carello");
						}
						int quantit�Sospesa=0;
						if(carelloSessione!=null)
						{
							ArrayList<ProdottoCarello> listaCarello= carelloSessione.getListaProdotti();
							for(int i=1; i<listaCarello.size(); i++)
							{
								if(listaCarello.get(i).getColore().getId()== Integer.parseInt(colore.get("id").toString()))
								{
									quantit�Sospesa= listaCarello.get(i).getColore().getQuantit�();
								}
							}
						}
						
						int quantit�=Integer.parseInt(colore.get("quantit�").toString())-quantit�Sospesa;
						if(quantit�<=0)
						{
					%>
						<option value="0">0</option>
					<%		
						}
						for(int i=1; i<=quantit�; i++)
						{
					%>
						<option value="<%=i %>"><%=i %></option>
					<%
						}
					%>
					</select>
					<%
						if(quantit�<=0)
						{
					%>
					<span id="disponibilit�">Prodotto non disponibile</span>
					<%
						}
					%>
				</div>
				<%
					session.setAttribute("dettaglioProdotto", prodotto);
					session.setAttribute("coloreSelezionato", coloreSelezionato);
					if(quantit�<=0)
					{
				%>
				<button type="submit" class="bottoneDettagli" name="bottoneCarello" value="<%=prodotto.get("id")%>" id="myBtn" onclick="aggiungiCarello()" disabled="disabled">Aggiungi al carello</button>
				<%
					}
					else
					{
				%>
				<button type="submit" class="bottoneDettagli" name="bottoneCarello" value="<%=prodotto.get("id")%>" id="myBtn" onclick="aggiungiCarello()">Aggiungi al carello</button>
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
				<div class="title">Prodotto aggiunto nel carello</div>
				<span class="close" onmouseup="prova()">&times;</span>
			</div>
			<div class="contenuto">
				<div class="shopping-cart">
					<div class="item">
						<div class="image">
							<img src="./img/prodotti/<%=colore.get("immagine") %>" alt="" height="100%" width="100%" id="immagineModal" />
						</div>

						<div class="description">
							<span><%=prodotto.get("nome") %></span> <span id="colorazione"><%=colore.get("colore") %></span>
						</div>

						<div class="total-price" id="prezzoModal"><%=colore.get("prezzo") %> &euro;</div>
					</div>
				</div>
				<div class="infoCarello">
				<%
					Carello carelloDettagli;
					if(utente!=null)
					{
						carelloDettagli=(Carello) session.getAttribute("carelloUtente");
					}
					else
					{
						carelloDettagli=(Carello) session.getAttribute("carello");
					}
					if(carelloDettagli!=null)
					{
				%>
					<p id="totaleCarello">Totale costo: <%=carelloDettagli.getTotaleCosto() %> &euro;</p>
					<p id="quantit�Carello">Totale prodotti nel carello: <%=carelloDettagli.getQuantitaCarello() %></p>
				<%
					}
					else
					{
				%>
					<p id="totaleCarello">Totale costo: 0 &euro;</p>
					<p id="quantit�Carello">Totale prodotti nel carello: 0</p>
				<%
					}	
				%>
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
	<%
		}
	%>
	
	<footer>
		<%@ include file="Footer.jsp"%>
	</footer>
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/cambiaImmagineDettagli.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/modalAggiungiCarello.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/validazioneFileGradazioneDettagli.js"></script>
	
	<script type="text/javascript">	
		function aggiungiCarello() 
		{
			console.log("ooo");
			<%
				String id= prodotto.get("id").toString();
				System.out.println("Click X: "+id);
			%>
			var bottone= <%=id%>+"/colore0";
			console.log(bottone);
			var quantit�Selezionata=$('#quantit�Prodotto').val();
			console.log(quantit�Selezionata);
			var ultimaOption= $('#quantit� option:last-child').val();
			console.log(ultimaOption);
			for(var i=1; i==quantit�Selezionata; i++)
			{
				$('#quantit� option:last-child').remove();
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