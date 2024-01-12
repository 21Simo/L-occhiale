<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="./css/dashboard.css">
    <link rel="stylesheet" href="./css/dettaglioProdottiAdmin.css">
    <link rel="stylesheet" href="./css/registrazione.css">
     
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

    <title>Inserisci prodotto</title>
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

			<div class="container">
				<form id="formInserisci" enctype="multipart/form-data" method="post" action="InserisciProdotto" onsubmit="return validazioneInserisci()">
					<div class="form first">
						<div id="colori" class="details personal">
							<span class="title">Inserisci prodotto</span>
							<div class="fields">
                        		<!--  
                        		<div class="input-field">
                        			<input type="text" id="idProdotto" name="idProdotto" value="" hidden="true">
                        			<label>Immagine: </label>
                        			<input type="text" id="immagineProdotto" name="immagineProdotto" value="" hidden="true">
                            		<label for="sceltaFile" class="caricamentoFile" id="etichettaSceltaFile">
                            			Inserisci l'immagine
									</label>
                            		<input name="uploadDocument" type="file" id="sceltaFile" accept="image/*" class="elementoNascosto" value=""/>
                            		<span id="erroreFile"></span> 
                        		</div>
                        		-->
                        		<div class="input-field">
									<label>Nome: </label>
									<input type="text" id="nomeProdotto" name="nomeProdotto" value="" placeholder="Inserisci il nome del prodotto">
									<p id="erroreNome"></p>
								</div>
                        		<div class="input-field">
									<label>Marca: </label>
									<input type="text" id="marcaProdotto" name="marcaProdotto" value="" placeholder="Inserisci la marca del prodotto">
									<p id="erroreMarca"></p>
								</div>
								<div class="input-field">
									<input type="text" id="genere" name="genere" value="" hidden="true">
									<label>Genere: </label>
									<select id="genereProdotto" name="genereProdotto">
										<option selected="selected">Inserisci il genere del prodotto</option>
										<option>Uomo</option>
										<option>Donna</option>
									</select>
									<p id="erroreGenere"></p>
								</div>
								<div id="colorii" class="input-field">
									<label>Colore: </label>
									<input type="number" id="idColore" name="idColore" value="" placeholder="Inserisci quanti colori vuoi aggiungere">
									<p id="erroreColore"></p>
								</div>
								<!--  
								<div class="input-field">
									<label>Prezzo: </label>
									<input type="text" id="prezzoProdotto" name="prezzoProdotto" value="" placeholder="Inserisci il prezzo">
									<p id="errorePrezzo"></p>
								</div>
								<div class="input-field">
									<label>Nome colore: </label>
									<input type="text" id="nomeColoreProdotto" name="nomeColoreProdotto" value="" placeholder="Inserisci il colore">
									<p id="erroreColore"></p>
								</div>
								<div class="input-field">
									<label for="quantità" class="labelQuantita"> Quantità: </label> 
									<input type="text" id="quantitàProdotto" name="quantitaProdotto" value="" placeholder="Inserisci la quantità">
									<p id="erroreQuantità"></p>
								</div>
								<div class="input-field">
									<label>Codice prodotto: </label>
									<input type="text" id="codiceProdotto" name="codiceProdotto" value="" placeholder="Inserisci il codice del prodotto">
									<p id="erroreCodice"></p>
								</div>
								-->
                        	</div>
                        	<div>
                        		<!-- 
                        		<button type="submit" class="bottoneDettagli" name="bottone" value="update" id="myBtn" onclick="abilitaCampi()">Modifica prodotto</button>
                        		 -->
								<button type="button" class="bottoneDettagli" name="bottoneCarello" value="" id="bottoneColore" onclick="aggiungiCampi()">Aggiungi campi dei colori</button>
							</div>
						</div>						
					</div>
				</form>
			</div>

		</section>
	</div>
	
	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>	
	
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/dettaglioProdottoAdmin.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/inserisciProdotto.js"></script>
</body>
</html>