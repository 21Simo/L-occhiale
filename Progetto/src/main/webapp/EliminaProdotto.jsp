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

    <title>Elimina prodotto</title>
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
          		<p class="font-weight-bold">Elimina prodotto</p>
        	</div>
        	<div class="container">
				<form id="formElimina" method="post" action="EliminaProdottoServlet" onsubmit="return validazioneElimina()">
					<div class="form first">
						<div class="details personal">
							<span class="title"></span>
							<div class="fields">
                        		<div class="input-field">
                        			<input type="text" id="idProdotto" name="idProdotto" value="<%=prodotto.get("id")%>" hidden="true">
                        			<p id="immagine" class="coloreTesto">Immagine: <%=colore.get("immagine") %></p>     
                        		</div>
                        		<div class="input-field">									
									<p class="coloreTesto">Marca: <%=prodotto.get("marca")%></p>									
								</div>
								<div class="input-field">									
									<p class="coloreTesto">Nome: <%=prodotto.get("nome")%></p>									
								</div>
								<div class="input-field">									
									<%
									if(prodotto.get("sesso").equals("M"))
									{
									%>
									<p class="coloreTesto">Genere: Uomo</p>
									<%
									}
									else
									{
									%>
									<p class="coloreTesto">Genere: Donna</p>
									<%
									}
									%>								
								</div>
								<div class="input-field">									
									<p id="prezzo" class="coloreTesto">Prezzo: <%=colore.get("prezzo")%> &euro;</p>									
								</div>								
								<div class="input-field">									
									<p id="nomeColore" class="coloreTesto">Nome colore: <%=colore.get("colore") %></p>									
								</div>
								<div class="input-field">									
									<p id="quantità" class="coloreTesto">Quantità: <%=colore.get("quantità") %></p>									
								</div>
								<div class="input-field">									
									<p id="codiceProdotto" class="coloreTesto">Codice prodotto: <%=colore.get("codiceProdotto") %></p>									
								</div>
								<div class="input-field">			
									<p class="coloreTesto">Scegli se vuoi eliminare solo un colore o tutto il prodotto: </p>
									<select id="coloreProdotto" name="coloreProdotto" onchange="aggiorna(this)">
										<option selected="selected">Inserisci il colore da eliminare o tutto il prodotto</option>
									<%
									for (int i = 0; i < prodotto.names().length(); i++) 
									{
										if (prodotto.names().get(i).toString().contains("colore") == true) 
										{
											String nome = prodotto.names().get(i).toString();
											JSONObject coloreJSON = (JSONObject) prodotto.get(nome);				
									%>
										<option value="<%=coloreJSON.get("id")%>"><%=coloreJSON.get("colore") %></option>
									<%
										}
									}
									%>
										<option value="-1">Tutto il prodotto</option>
									</select>
									<%
									int k=0;
									String variId="";
									for (int j = 0; j < prodotto.names().length(); j++) 
									{
										if (prodotto.names().get(j).toString().contains("colore") == true) 
										{
											String nome = prodotto.names().get(j).toString();
											JSONObject coloreJSON = (JSONObject) prodotto.get(nome);												
											k++;
											variId+=coloreJSON.get("id")+"/";
									%>
									<input id="colore<%=coloreJSON.get("id")%>" value="<%=coloreJSON.get("immagine") %>,<%=coloreJSON.get("prezzo") %>,<%=coloreJSON.get("colore") %>,<%=coloreJSON.get("quantità") %>,<%=coloreJSON.get("codiceProdotto") %>" hidden="true">
									<%
										}
									}									
									%>
									<input id="colore-1" value="<%=k%>/<%=variId %>" hidden="true">
									<p class="errore" id="erroreColore"></p>
								</div>
                        	</div>
                        	<div>
                        		<button type="submit" class="bottoneDettagli" name="bottone" value="update" id="myBtn">Elimina</button>
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
	<script type="text/javascript" charset="UTF-8" src="./script/eliminaProdotto.js"></script>
    <script src="./script/dashboard.js"></script>
</body>
</html>