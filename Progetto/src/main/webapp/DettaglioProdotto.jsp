<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	JSONObject prodotto= (JSONObject) request.getAttribute("prodotto");
	//System.out.println("DettaglioProdottoJSP: "+prodotto);
	String coloreSelezionato=request.getAttribute("coloreSelezionato").toString();
	//System.out.println("DettaglioProdottoJSP: "+coloreSelezionato);
	JSONObject colore= (JSONObject) prodotto.get(coloreSelezionato);
	//System.out.println("DettaglioProdottoJSP: "+colore);
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dettaglio Prodotto</title>
	<link rel="stylesheet" href="./css/dettaglioProdotti.css">
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
			<h3><%=prodotto.get("nome") %></h3>
			<h6 id="prezzo"><%=colore.get("prezzo") %> &euro;</h6>
  			<div class="containerToogle" id="toogle">
				<label class="switch switchToogle etichettaToogle">
		        	<input type="checkbox" name="modificaLenti" id="modificaLenti" value="1">
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
								//System.out.println(nome);
								JSONObject coloreJSON=(JSONObject) prodotto.get(nome);
								System.out.println("DettaglioProdottoJSP colori: "+coloreJSON);
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
			<button class="bottone">Aggiungi al carello</button>
		</div>
	</div>
	
	<footer>
		<%@ include file="Footer.jsp"%>
	</footer>
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/cambiaImmagineDettagli.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#choose-file').change(function () {
				var i = $(this).prev('label').clone();
				var file = $('#choose-file')[0].files[0].name;
				$(this).prev('label').text(file);
			}); 
	 	});
	</script>
</body>
</html>