<%@page import="java.time.LocalDate"%>
<%@page import="model.RegioneDAO"%>
<%@page import="model.Regione"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./css/registrazione.css">
	<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	<title>Registrazione</title>
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>

	<div class="container">
        <header>Registrazione</header>

        <form action="RegistrazioneServlet" onsubmit="return validazione()" name="form" method="post">
            <div class="form first">
                <div class="details personal">
                    <span class="title">Informazioni personali</span>

                    <div class="fields">
                        <div class="input-field">
                            <label>Nome</label>
                            <input type="text" id="nome" name="nome" placeholder="Inserisci il tuo nome" required>
                            <p id="erroreNome" class="errore"></p> 
                        </div>
                        
                        <div class="input-field">
                            <label>Cognome</label>
                            <input type="text" id="cognome" name="cognome" placeholder="Inserisci il tuo cognome" required>
                            <p id="erroreCognome" class="errore"></p>
                        </div>

						<div class="input-field">
                            <label>Codice fiscale</label>
                            <input type="text" id="codiceFiscale" name="codiceFiscale" placeholder="Inserisci il tuo codice fiscale" required>
                            <p id="erroreCodiceFiscale" class="errore"></p>
                        </div>

                        <div class="input-field">
                            <label>Date di nascita</label>
                            <%
                            	int anno=LocalDate.now().getYear();
                            	anno=anno-16;                 
                            %>
                            <input type="date" id="dataNascita" name="dataNascita" placeholder="Inserisci la tua data di nascita" max="<%=anno %>-12-31" required>
                            <p id="erroreDataNascita" class="errore"></p>
                        </div>

                        <div class="input-field">
                            <label>Email</label>
                            <input type="email" id="email" name="email" placeholder="Inserisci la tua email" required>
                            <p id="erroreEmail" class="errore"></p>
                        </div>
                        
                        <div class="input-field">
                            <label>Password</label>
                            <input type="password" id="password" name="password" placeholder="Inserisci la tua password" required>
                            <p class="coloreTesto">
                            	La password deve avere minimo otto caratteri, almeno una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale.
                            </p>
                            <p id="errorePassword" class="errore"></p>
                        </div>

                        <div class="input-field">
                            <label>Numero di telefono</label>
                            <input type="tel" id="telefono" name="telefono" placeholder="Inserisci il tuo numero di telefono" required>
                            <small class="coloreTesto">Esempio: +39-0123456789</small>
                            <p id="erroreTelefono" class="errore"></p>
                        </div>

                        <div class="input-field">
                            <label>Sesso</label>
                            <select name="sesso" required>
                                <option disabled selected>Seleziona il tuo sesso</option>
                                <option>Maschio</option>
                                <option>Femmina</option>
                                <option>Altro</option>
                            </select>
                        </div>
         
                    </div>
                </div>

                <div class="details ID">
                    <span class="title">Indirizzo (spedizioni solo in Italia)</span>

                    <div class="fields">
                    	<div class="input-field">
                            <label>Stato</label>
                            <input type="text" value="Italia" placeholder="Inserisci lo stato" readonly required>
                        </div>
                        
                        <div class="input-field">
                            <label>Regione</label>
                            <select name="regione" id="regione" onchange="regioni()" required>
                            	<option disabled selected>Seleziona la regione</option>
                        
                        <%
                        	RegioneDAO regioneDAO= new RegioneDAO();
                        	ArrayList<Regione> listaRegioni= regioneDAO.regioni();
                        	int j=0;
                        	for(int i=0; i<listaRegioni.size(); i++)
                        	{
                        		j++;
                        %>
                        
                        		<option value="<%=j%>/<%=listaRegioni.get(i).getNome()%>"><%=listaRegioni.get(i).getNome() %></option>
                        <%
                        	}
                        %>
                        	</select>
                        </div>
                        
                        <div class="input-field">
                        	<label>Provincia</label>
                        	<select name="province" id="province" onchange="provincia()" required>
                        		<option disabled selected>Seleziona la provincia</option>
                        	</select>
                        </div>
                        
                        <div class="input-field">
                        	<label>Comune</label>
                        	<select name="comune" id="comune" onchange="" required>
                        		<option disabled selected>Seleziona il comune</option>
                        	</select>
                        </div>
                        
                        <div class="input-field">
                            <label>Indirizzo</label>
                            <input type="text" id="indirizzo" name="indirizzo" placeholder="Inserisci l'indirizzo" required>
                            <p id="erroreIndirizzo" class="errore"></p>
                        </div>
                        
                    </div>

                    <button class="nextBtn">
                        <span class="btnText">Registrati</span>
                        <i class="uil uil-navigator"></i>
                    </button>
                    
                </div> 
            </div>
        </form>
    </div>
    
    <footer>
		<%@ include file="Footer.jsp" %>
	</footer>

	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="./script/registrazione.js"></script>
</body>
</html>