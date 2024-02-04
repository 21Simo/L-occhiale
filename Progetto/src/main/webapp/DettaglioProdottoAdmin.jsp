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
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="./css/dettaglioProdottiAdmin.css">
    <link rel="stylesheet" href="./css/registrazione.css">
     
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    
     <!-- Montserrat Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="./css/dashboard.css">

    <title>Dettaglio prodotti</title>
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	<%
	if(utente==null)
	{
		response.sendRedirect("Login.jsp");
	}
	else
	{
	%>
	<div class="grid-container">
		<%@ include file="MenùDashboardAdmin.jsp" %>
		<main class="main-container">
        	<div class="main-title">
          		<p class="font-weight-bold">Modifica prodotto</p>
        	</div>
        	<div class="container">
				<form id="formDettagli" enctype="multipart/form-data" method="post" action="DettaglioProdottoAdminServlet" onsubmit="return noValidazione()">
					<div class="form first">
						<div class="details personal">
							<span class="title"></span>
							<div class="fields">
                        		<div class="input-field">
                        			<input type="text" id="idProdotto" name="idProdotto" value="<%=prodotto.get("id")%>" hidden="true">
                        			<label>Immagine: </label>
                        			<input type="text" id="immagineProdotto" name="immagineProdotto" value="<%=colore.get("immagine") %>" hidden="true">
                            		<label for="sceltaFile" class="caricamentoFile" id="etichettaSceltaFile">
   										<%=colore.get("immagine") %>
									</label>
                            		<input name="uploadDocument" type="file" id="sceltaFile" accept="image/*" class="elementoNascosto" value="./img/prodotti/<%=colore.get("immagine") %>" disabled="disabled"/>
                            		<span id="erroreFile" class="errore"></span> 
                        		</div>
                        		<div class="input-field">
									<label>Marca: </label>
									<input type="text" id="marcaProdotto" name="marcaProdotto" value="<%=prodotto.get("marca")%>" disabled="disabled">
									<p id="erroreMarca" class="errore"></p>
								</div>
								<div class="input-field">
									<label>Nome: </label>
									<input type="text" id="nomeProdotto" name="nomeProdotto" value="<%=prodotto.get("nome")%>" disabled="disabled">
									<p id="erroreNome" class="errore"></p>
								</div>
								<div class="input-field">
									<input type="text" id="genere" name="genere" value="<%=prodotto.get("sesso")%>" hidden="true">
									<label>Genere: </label>
									<select name="genereProdotto" disabled="disabled">
									<%
										if(prodotto.get("sesso").equals("M"))
										{
									%>
										<option selected="selected">Uomo</option>
										<option>Donna</option>
									<%
										}
										else
										{
									%>
										<option>Uomo</option>
										<option selected="selected">Donna</option>
									<%
										}
									%>
									</select>
								</div>
								<div class="input-field">
									<label>Prezzo: </label>
									<input type="text" id="prezzoProdotto" name="prezzoProdotto" value="<%=colore.get("prezzo")%>" placeholder="Inserisci il prezzo" disabled="disabled">
									<p id="errorePrezzo" class="errore"></p>
								</div>
								<div class="input-field">
									<input type="text" id="idColore" name="idColore" value="<%=colore.get("id")%>" hidden="true">
									<label>Colore: </label>
									<select id="coloreProdotto" name="coloreProdotto" onchange="colore()" disabled="disabled">
									<%
									for (int i = 0; i < prodotto.names().length(); i++) 
									{
										if (prodotto.names().get(i).toString().contains("colore") == true) 
										{
											String nome = prodotto.names().get(i).toString();
											JSONObject coloreJSON = (JSONObject) prodotto.get(nome);				
									%>
										<option value="<%=coloreJSON.get("immagine")%>,<%=coloreJSON.get("prezzo")%>,<%=coloreJSON.get("colore")%>,<%=coloreJSON.get("quantità")%>,<%=coloreJSON.get("codiceProdotto")%>"><%=nome %></option>
									<%
										}
									}
									%>
									</select>
								</div>
								<div class="input-field">
									<label>Nome colore: </label>
									<input type="text" id="nomeColoreProdotto" name="nomeColoreProdotto" value="<%=colore.get("colore") %>" placeholder="Inserisci il colore" disabled="disabled">
									<p id="erroreColore" class="errore"></p>
								</div>
								<div class="input-field">
									<label for="quantità" class="labelQuantita"> Quantità: </label> 
									<input type="text" id="quantitàProdotto" name="quantitaProdotto" value="<%=colore.get("quantità") %>" placeholder="Inserisci la quantità" disabled="disabled">
									<p id="erroreQuantità" class="errore"></p>
								</div>
								<div class="input-field">
									<label>Codice prodotto: </label>
									<input type="text" id="codiceProdotto" name="codiceProdotto" value="<%=colore.get("codiceProdotto") %>" placeholder="Inserisci il codice del prodotto" disabled="disabled">
									<p id="erroreCodice" class="errore"></p>
								</div>
                        	</div>
                        	<div class="bottoniModifica">
                        		<button type="submit" class="bottoneDettagli spazioBottone" name="bottone" value="update" id="myBtn" onclick="abilitaCampi()">Modifica prodotto</button>
								<button type="button" class="bottoneDettagli" name="bottoneCarello" value="<%=prodotto.get("id")%>" id="bottoneColore" onclick="aggiungiColore()">Aggiungi colore</button>
							</div>
						</div>
					</div>
				</form>
			</div>
        </main>
	</div>
	<%
	}
	%>
	
	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>	
	
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/dettaglioProdottoAdmin.js"></script>
    <script src="./script/dashboard.js"></script>
</body>
</html>