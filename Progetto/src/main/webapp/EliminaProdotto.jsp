<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	JSONObject prodotto= (JSONObject) request.getAttribute("prodotto");
	System.out.println(prodotto);
	String coloreSelezionato=request.getAttribute("coloreSelezionato").toString();
	JSONObject colore= (JSONObject) prodotto.get(coloreSelezionato);
	System.out.println(colore);
%>
    
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

    <title>Elimina prodotto</title>
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
						<span class="text">Ordini</span>
					</div>
				</div>
			</div>

			<div class="container">
				<form id="formElimina" method="post" action="EliminaProdottoServlet" onsubmit="return validazioneElimina()">
					<div class="form first">
						<div class="details personal">
							<span class="title">Elimina prodotto</span>
							<div class="fields">
                        		<div class="input-field">
                        			<input type="text" id="idProdotto" name="idProdotto" value="<%=prodotto.get("id")%>" hidden="true">
                        			<p>Immagine: <%=colore.get("immagine") %></p>     
                        		</div>
                        		<div class="input-field">									
									<p>Marca: <%=prodotto.get("marca")%></p>									
								</div>
								<div class="input-field">									
									<p>Nome: <%=prodotto.get("nome")%></p>									
								</div>
								<div class="input-field">									
									<%
									if(prodotto.get("sesso").equals("M"))
									{
									%>
									<p>Genere: Uomo</p>
									<%
									}
									else
									{
									%>
									<p>Genere: Donna</p>
									<%
									}
									%>								
								</div>
								<div class="input-field">									
									<p>Prezzo: <%=colore.get("prezzo")%> &euro;</p>									
								</div>								
								<div class="input-field">									
									<p>Nome colore: <%=colore.get("colore") %></p>									
								</div>
								<div class="input-field">									
									<p>Quantità: <%=colore.get("quantità") %></p>									
								</div>
								<div class="input-field">									
									<p>Codice prodotto: <%=colore.get("codiceProdotto") %></p>									
								</div>
								<div class="input-field">			
									<p>Scegli se vuoi eliminare solo un colore o tutto il prodotto: </p>
									<select id="coloreProdotto" name="coloreProdotto">
										<option selected="selected">Inserisci il colore da eliminare o tutto il prodotto</option>
									<%
									for (int i = 0; i < prodotto.names().length(); i++) 
									{
										if (prodotto.names().get(i).toString().contains("colore") == true) 
										{
											String nome = prodotto.names().get(i).toString();
											System.out.println("Colore: "+nome);
											JSONObject coloreJSON = (JSONObject) prodotto.get(nome);
											System.out.println(coloreJSON);				
									%>
										<option value="<%=coloreJSON.get("id")%>"><%=coloreJSON.get("colore") %></option>
									<%
										}
									}
									%>
										<option value="-1">Tutto il prodotto</option>
									</select>
									<p id="erroreColore"></p>
								</div>
                        	</div>
                        	<div>
                        		<button type="submit" class="bottoneDettagli" name="bottone" value="update" id="myBtn">Elimina</button>
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
	<script type="text/javascript" charset="UTF-8" src="./script/eliminaProdotto.js"></script>
</body>
</html>