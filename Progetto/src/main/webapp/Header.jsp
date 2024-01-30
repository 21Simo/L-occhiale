<%@page import="model.Utente"%>
<%@page import="model.Carello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>  	
  	<meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Header</title>
    <link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
	<div class="page-header">
      <div class="logo">
      	<a href="IndexServlet" class="menu">
        	<img alt="Logo" src="img/LogoAdobe1.png" width="60" class="logo">
        </a>
      </div>
      <a id="menu-icon" class="menu-icon" onclick="onMenuClick()">
        <i class="fa fa-bars"></i>
      </a>

      <div id="navigation-bar" class="nav-bar">
      	<form action="Prodotto" method="post">
			<button name="prodotti" class="noButton" value="header">Tutti i prodotti</button>							
		</form>
		<form action="GenereProdottoServlet" method="post">							
			<button name="genere" class="noButton" value="Uomo">Uomo</button>
			<button name="genere" class="noButton" value="Donna">Donna</button>
		</form>
      </div>

      <div class="header-right">
      	<%
		Utente utente=(Utente) request.getSession().getAttribute("utente");
		System.out.println("Header utente: "+utente);
		Carello carello;
		if(utente!=null)
		{
			carello=(Carello) session.getAttribute("carelloUtente");
			String tipo= utente.getTipo();
			if(tipo.equals("Utente"))
			{
		%>
		<a href="DashboardUtenteServlet" class="menu noLink">
			<img alt="Account" src="img/icone/person.svg" class="icone">
		</a>
		<%
			}
			else if(tipo.equals("Amministratore"))
			{
		%>
		<a href="DashboardAdminServlet" class="menu noLink">
			<img alt="Account" src="img/icone/person.svg" class="icone">
		</a>
		<%
			}
			else if(tipo.equals("errore"))
			{
		%>
		<a href="Login.jsp" class="menu noLink">
			<img alt="Account" src="img/icone/person.svg" class="icone">
		</a>
		<%
			}
		}
		else
		{
			carello=(Carello) session.getAttribute("carello");
		%>
		<a href="Login.jsp" class="menu noLink">
			<img alt="Account" src="img/icone/person.svg" class="icone">
		</a>
		<%
		}
		System.out.println("Header carello: "+carello);
		%>
		<a href="Carrello.jsp" class="menu noLink" id="carello">
			<img alt="Carello" src="img/icone/cart4.svg" class="icone">
			<%
			if(carello!=null)
			{
			%>
			<span class='cart-counter' id="contatoreCarello"><%=carello.getQuantitaCarello() %></span>
			<%
			}
			%>
			<span class='cart-counter' id="contatoreCarello"> </span>
		</a>
      </div>
    </div>
    <script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
    <script src="./script/header.js" charset="UTF-8"></script>
</body>
</html>