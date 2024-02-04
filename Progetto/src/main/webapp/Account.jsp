<%@page import="model.Comune"%>
<%@page import="model.ComuneDAO"%>
<%@page import="model.Province"%>
<%@page import="model.ProvinceDAO"%>
<%@page import="model.Regione"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.RegioneDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
	<title>Account</title>
	<link rel="stylesheet" href="./css/dashboard.css">
	<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	<link rel="stylesheet" href="./css/registrazione.css">
	
	<!-- Montserrat Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
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
		<%@ include file="MenùDashboard.jsp" %>
		
		<main class="main-container">
        	<div class="main-title">
          		<p class="font-weight-bold">Modifica i dati</p>
        	</div>
        	
        	<div class="container">
        	<form id="formModifica" action="ModificaAccountServlet" onsubmit="return noValidazione()" name="form" method="post">
					<div class="form first">
						<div class="details personal">
							<span class="title">Informazioni personali</span>

							<div class="fields">
								<div class="input-field">
									<label>Nome</label> 
									<input type="text" id="nome" value="<%=utente.getNome() %>" name="nome" placeholder="Inserisci il tuo nome" required disabled="disabled">
									<p id="erroreNome" class="errore"></p>
								</div>

								<div class="input-field">
									<label>Cognome</label> 
									<input type="text" id="cognome" value="<%=utente.getCognome() %>" name="cognome" placeholder="Inserisci il tuo cognome" required disabled="disabled">
									<p id="erroreCognome" class="errore"></p>
								</div>

								<div class="input-field">
									<label>Codice fiscale</label> 
									<input type="text" id="codiceFiscale" value="<%=utente.getCodiceFiscale() %>" name="codiceFiscale" placeholder="Inserisci il tuo codice fiscale" required disabled="disabled">
									<p id="erroreCodiceFiscale" class="errore"></p>
								</div>

								<div class="input-field">
									<label>Date di nascita</label> 
									<input type="date" id="dataNascita" value="<%=utente.getDataNascita() %>" name="dataNascita" placeholder="Inserisci la tua data di nascita" required disabled="disabled">
									<p id="erroreDataNascita" class="errore"></p>
								</div>

								<div class="input-field">
									<label>Email</label> 
									<input type="email" id="email" value="<%=utente.getEmail() %>" name="email" placeholder="Inserisci la tua email" required disabled="disabled">
									<p id="erroreEmail" class="errore"></p>
								</div>

								<div class="input-field">
									<label>Password</label> 
									<input type="password" id="password" value="<%=utente.getPassword() %>" name="password" placeholder="Inserisci la tua password" required disabled="disabled">
									<p class="coloreTesto">
										La password deve avere minimo otto caratteri, almeno una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale.
									</p>
									<p id="errorePassword" class="errore"></p>
								</div>

								<div class="input-field">
									<label>Numero di telefono</label> 
									<input type="tel" id="telefono" value="<%=utente.getTelefono() %>" name="telefono" placeholder="Inserisci il tuo numero di telefono" required disabled="disabled"> 
									<small class="coloreTesto">
										Esempio: +39-0123456789
									</small>
									<p id="erroreTelefono" class="errore"></p>
								</div>

								<div class="input-field">
									<label>Sesso</label> 
									<select name="sesso" required disabled="disabled">
										<%
										switch (utente.getSesso())
										{
											case "Maschio":
										%>
										<option disabled="disabled" selected="selected">Maschio</option>
										<option>Femmina</option>
										<option>Altro</option>
										<%
												break;
											case "Femmina":
										%>
										<option>Maschio</option>
										<option disabled="disabled" selected="selected">Femmina</option>
										<option>Altro</option>
										<%
												break;
											case "Altro":
										%>
										<option>Maschio</option>
										<option>Femmina</option>
										<option disabled="disabled" selected="selected">Altro</option>
										<%
												break;
										}
										%>
									</select>
								</div>

							</div>
						</div>

						<div class="details ID">
							<span class="title">Indirizzo (spedizioni solo in Italia)</span>

							<div class="fields">
								<div class="input-field">
									<label>Stato</label> 
									<input type="text" value="Italia" placeholder="Inserisci lo stato" readonly required disabled="disabled">
								</div>

								<div class="input-field">
									<label>Regione</label> 
									<select name="regione" id="regione" onchange="regioni()" required disabled="disabled">
										

										<%
                        					RegioneDAO regioneDAO= new RegioneDAO();
                        					ArrayList<Regione> listaRegioni= regioneDAO.regioni();
                        					int j=0;
                        					int regione=0;
                        					for(int i=0; i<listaRegioni.size(); i++)
                        					{
                        						j++;
                        						if(listaRegioni.get(i).getNome().equals(utente.getRegione()))
                        						{
                        							regione= j;
                        				%>
                        				<option disabled="disabled" selected="selected" value="<%=j%>/<%=listaRegioni.get(i).getNome()%>"><%=listaRegioni.get(i).getNome() %></option>
										<%
                        						}
                        						else
                        						{
                        				%>
										<option value="<%=j%>/<%=listaRegioni.get(i).getNome()%>"><%=listaRegioni.get(i).getNome() %></option>
										<%
                        						}
                        					}
                        				%>
									</select>
								</div>

								<div class="input-field">
									<label>Provincia</label> 
									<select name="province" id="province" onchange="provincia()" required disabled="disabled">
									<%
										ProvinceDAO provinceDAO= new ProvinceDAO();
										ArrayList<Province> listaProvince= provinceDAO.province(regione);
										int provincia=0;
										for(int k=0; k<listaProvince.size(); k++)
										{
											if(listaProvince.get(k).getNome().equals(utente.getProvincia()))
											{
												provincia= listaProvince.get(k).getId();
									%>
									<option disabled="disabled" selected="selected" value="<%=listaProvince.get(k).getId()%>/<%=listaProvince.get(k).getNome()%>"><%=listaProvince.get(k).getNome() %></option>
									<%
											}
											else
											{
									%>
									<option value="<%=listaProvince.get(k).getId()%>/<%=listaProvince.get(k).getNome()%>"><%=listaProvince.get(k).getNome() %></option>
									<%
											}
										}
									%>
									</select>
								</div>

								<div class="input-field">
									<label>Comune</label> 
									<select name="comune" id="comune" onchange="" required disabled="disabled">
									<%
										ComuneDAO comuneDAO= new ComuneDAO();
										ArrayList<Comune> listaComuni= comuneDAO.comuni(provincia);
										int h=0; 
										int comune=0;
										for(int z=0; z<listaComuni.size(); z++)
										{
											h++;
											if(listaComuni.get(z).getNome().equals(utente.getComune()))
											{
												comune= h;
									%>
									<option disabled="disabled" selected="selected"><%=listaComuni.get(z).getNome() %></option>
									<%
											}
											else
											{
									%>
									<option><%=listaComuni.get(z).getNome() %></option>
									<%
											}
										}
									%>
									</select>
								</div>

								<div class="input-field">
									<label>Indirizzo</label> 
									<input type="text" id="indirizzo" value="<%=utente.getIndirizzo() %>" name="indirizzo" placeholder="Inserisci l'indirizzo" required disabled="disabled">
									<p id="erroreIndirizzo" class="errore"></p>
								</div>

							</div>

							<button class="nextBtn" onclick="abilitaCampi()">
								<span class="btnText">Modifica</span> 
								<i class="uil uil-navigator"></i>
							</button>

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
	<script type="text/javascript" charset="UTF-8" src="./script/registrazione.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/account.js"></script>
    <script src="./script/dashboard.js"></script>
</body>
</html>