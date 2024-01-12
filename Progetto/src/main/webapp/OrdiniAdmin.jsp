<%@page import="java.util.ArrayList"%>
<%@page import="model.UtenteDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="./css/dashboard.css">  
    <link rel="stylesheet" href="./css/registrazione.css">
    <link rel="stylesheet" href="./css/ordiniAdmin.css">   
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    
	<title>Ordini</title>
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
				<form id="formOrdini" method="post" action="OrdiniAdminServlet" onsubmit="return validazioneOrdini()">
					<div class="form first">
						<div class="details personal">
							<span class="title">Ordini</span>
							<div class="fields">                   
                        		<div class="input-field">
                            		<select id="opzione" name="opzione" onchange="opzioni()" required>
                                		<option selected>Seleziona se vuoi visualizzare gli ordini per data o per utente</option>
                               			<option>Data</option>
                               	 		<option>Utente</option>                                
                            		</select>
                            		<p id="erroreOpzione"></p>
                        		</div>
                        	</div>
                        	<div id="data" class="nascondi">
                        		<div class="input-field">
                            		<label>Dalla data</label>
                            		<input type="date" id="data1" name="data1" placeholder="Inserisci la tua data di nascita">
                            		<p id="erroreData1"></p>
                        		</div>	
                        		<div class="input-field">
                            		<label>Alla data</label>
                            		<input type="date" id="data2" name="data2" placeholder="Inserisci la tua data di nascita">
                            		<p id="erroreData2"></p>
                        		</div>					
                        	</div>
                        	<div id="utente" class="nascondi">
                        		<div class="input-field">
                            		<label>Utente</label>
                            		<select id="utenteOption" name="utenteOption" required>
                                		<option selected>Seleziona l'utente</option>
                                		<%
                                		UtenteDAO utenteDAO= new UtenteDAO();
                                		ArrayList<Utente> listaUtente= utenteDAO.utenti();
                                		System.out.println(listaUtente);
                                		for(int i=0; i<listaUtente.size(); i++)
                                		{
                                		%>
                                		<option value="<%=listaUtente.get(i).getId() %>"><%=listaUtente.get(i).getEmail() %></option>
                                		<%
                                		}
                                		%>                                		                                
                            		</select>
                            		<p id="erroreUtente"></p>
                        		</div>              					
                        	</div>
                        	<div id="bottone" class="nascondi">
                        		<button type="submit" class="bottoneDettagli" name="bottone" value="update" id="myBtn">Visualizza</button>
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
	<script type="text/javascript" charset="UTF-8" src="./script/ordiniAdmin.js"></script>
</body>
</html>