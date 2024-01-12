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
	<meta charset="ISO-8859-1">
	<title>Account</title>
	<link rel="stylesheet" href="./css/dashboard.css">
	<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	<link rel="stylesheet" href="./css/registrazione.css">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	
	<%
		System.out.println("Account JSP: "+utente);
		if(utente==null)
		{
			response.sendRedirect("Login.jsp");
		}
	%>

	<div class="contenitoreDashboard">
		<%@ include file="MenùDashboard.jsp" %>

		<section class="dashboard">
			<div class="dash-content">
				<div class="overview">
					<div class="title">
						<i class="uil uil-tachometer-fast-alt"></i> <span class="text">Dashboard</span>
					</div>
				</div>
			</div>
		
			<div class="container">
				<header>Modifica i dati</header>

				<form id="formModifica" action="ModificaAccountServlet" onsubmit="return noValidazione()" name="form" method="post">
					<div class="form first">
						<div class="details personal">
							<span class="title">Personal Details</span>

							<div class="fields">
								<div class="input-field">
									<label>Nome</label> 
									<input type="text" id="nome" value="<%=utente.getNome() %>" name="nome" placeholder="Inserisci il tuo nome" required disabled="disabled">
									<p id="erroreNome"></p>
								</div>

								<div class="input-field">
									<label>Cognome</label> 
									<input type="text" id="cognome" value="<%=utente.getCognome() %>" name="cognome" placeholder="Inserisci il tuo cognome" required disabled="disabled">
									<p id="erroreCognome"></p>
								</div>

								<div class="input-field">
									<label>Codice fiscale</label> 
									<input type="text" id="codiceFiscale" value="<%=utente.getCodiceFiscale() %>" name="codiceFiscale" placeholder="Inserisci il tuo codice fiscale" required disabled="disabled">
									<p id="erroreCodiceFiscale"></p>
								</div>

								<div class="input-field">
									<label>Date di nascita</label> 
									<input type="date" id="dataNascita" value="<%=utente.getDataNascita() %>" name="dataNascita" placeholder="Inserisci la tua data di nascita" required disabled="disabled">
									<p id="erroreDataNascita"></p>
								</div>

								<div class="input-field">
									<label>Email</label> 
									<input type="email" id="email" value="<%=utente.getEmail() %>" name="email" placeholder="Inserisci la tua email" required disabled="disabled">
									<p id="erroreEmail"></p>
								</div>

								<div class="input-field">
									<label>Password</label> 
									<input type="password" id="password" value="<%=utente.getPassword() %>" name="password" placeholder="Inserisci la tua password" required disabled="disabled">
									<p>
										La password deve avere minimo otto caratteri, almeno una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale.
									</p>
									<p id="errorePassword"></p>
								</div>

								<div class="input-field">
									<label>Numero di telefono</label> 
									<input type="tel" id="telefono" value="<%=utente.getTelefono() %>" name="telefono" placeholder="Inserisci il tuo numero di telefono" required disabled="disabled"> 
									<small>
										Esempio: +39-0123456789
									</small>
									<p id="erroreTelefono"></p>
								</div>

								<div class="input-field">
									<label>Sesso</label> 
									<select name="sesso" required disabled="disabled">
										<%
										switch (utente.getSesso())
										{
										//if(utente.getSesso().equals("Maschio"))
										//{
											case "Maschio":
										%>
										<option disabled="disabled" selected="selected">Maschio</option>
										<option>Femmina</option>
										<option>Altro</option>
										<%
												break;
											case "Femmina":
										//else if(utente.getSesso().equals("Femmina"))
										//{
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
											System.out.println("Account jsp id provincie: "+listaProvince.get(k).getId());
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
										System.out.println("Account jsp provincia: "+provincia);
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
									<p id="erroreIndirizzo"></p>
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

		</section>
	</div>

	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>
	
	<script type="text/javascript" charset="UTF-8" src="./script/registrazione.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./script/account.js"></script>
</body>
</html>