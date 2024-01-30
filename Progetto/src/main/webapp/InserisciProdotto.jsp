<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

    <title>Inserisci prodotto</title>
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	<div class="grid-container">
		<%@ include file="MenùDashboardAdmin.jsp" %>
		<main class="main-container">
        	<div class="main-title">
          		<p class="font-weight-bold">Ordini recenti</p>
        	</div>
        	<div class="container">
				<form id="formInserisci" enctype="multipart/form-data" method="post" action="InserisciProdotto" onsubmit="return validazioneInserisci()">
					<div class="form first">
						<div id="colori" class="details personal">
							<span class="title">Inserisci prodotto</span>
							<div class="fields">
                        		<div class="input-field">
									<label>Nome: </label>
									<input type="text" id="nomeProdotto" name="nomeProdotto" value="" placeholder="Inserisci il nome del prodotto">
									<p id="erroreNome" class="errore"></p>
								</div>
                        		<div class="input-field">
									<label>Marca: </label>
									<input type="text" id="marcaProdotto" name="marcaProdotto" value="" placeholder="Inserisci la marca del prodotto">
									<p id="erroreMarca" class="errore"></p>
								</div>
								<div class="input-field">
									<input type="text" id="genere" name="genere" value="" hidden="true">
									<label>Genere: </label>
									<select id="genereProdotto" name="genereProdotto">
										<option selected="selected">Inserisci il genere del prodotto</option>
										<option>Uomo</option>
										<option>Donna</option>
									</select>
									<p id="erroreGenere" class="errore"></p>
								</div>
								<div id="colorii" class="input-field">
									<label>Colore: </label>
									<input type="number" id="idColore" name="idColore" value="" placeholder="Inserisci quanti colori vuoi aggiungere">
									<p id="erroreColore" class="errore"></p>
								</div>								
                        	</div>
                        	<div>                        		
								<button type="button" class="bottoneDettagli" name="bottoneCarello" value="" id="bottoneColore" onclick="aggiungiCampi()">Aggiungi campi dei colori</button>
							</div>
						</div>						
					</div>
				</form>
			</div>
        </main>
	</div>
	
	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>	
	
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/dettaglioProdottoAdmin.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/inserisciProdotto.js"></script>
    <script src="./script/dashboard.js"></script>
</body>
</html>