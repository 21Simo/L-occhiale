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
      
    <link rel="stylesheet" href="./css/registrazione.css">
    <link rel="stylesheet" href="./css/ordiniAdmin.css">   
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    
    <!-- Montserrat Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="./css/dashboard.css">
    
	<title>Ordini</title>
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
          		<p class="font-weight-bold">Ordini</p>
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
                            		<p id="erroreOpzione" class="errore"></p>
                        		</div>
                        	</div>
                        	<div id="data" class="nascondi">
                        		<div class="input-field">
                            		<label>Dalla data</label>
                            		<input type="date" id="data1" name="data1" placeholder="Inserisci la tua data di nascita">
                            		<p id="erroreData1" class="errore"></p>
                        		</div>	
                        		<div class="input-field">
                            		<label>Alla data</label>
                            		<input type="date" id="data2" name="data2" placeholder="Inserisci la tua data di nascita">
                            		<p id="erroreData2" class="errore"></p>
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
                                		for(int i=0; i<listaUtente.size(); i++)
                                		{
                                		%>
                                		<option value="<%=listaUtente.get(i).getId() %>"><%=listaUtente.get(i).getEmail() %></option>
                                		<%
                                		}
                                		%>                                		                                
                            		</select>
                            		<p id="erroreUtente" class="errore"></p>
                        		</div>              					
                        	</div>
                        	<div id="bottone" class="nascondi">
                        		<button type="submit" class="bottoneDettagli" name="bottone" value="update" id="myBtn">Visualizza</button>
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
	<script type="text/javascript" charset="UTF-8" src="./script/ordiniAdmin.js"></script>
    <script src="./script/dashboard.js"></script>
</body>
</html>