<%@page import="model.Utente"%>
<%@page import="model.Carello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<link rel="stylesheet" href="./css/header.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<title>Flex</title>
	</head>
	<body>
		<header>
			<a href="Index.jsp" class="menu">
				<img alt="Logo" src="img/LogoAdobe.png" width="180" class="logo">
			</a>
			<nav>
				<ul class="menu">
					<li>
						<!--  <a href="Prodotto" class="menu">Tutti i prodotti</a>-->
						<form action="Prodotto" method="post">
							<button name="prodotti" class="noButton menu" value="header">Tutti i prodotti</button>							
						</form>
					</li>
					<li><a href="Uomo.jsp" class="menu">Uomo</a></li>
					<li><a href="Donna.jsp" class="menu">Donna</a></li>
				</ul>
			</nav>
			<div>
				<a href="ListaDesideri.jsp" class="menu">
					<img alt="Lista desideri" src="img/icone/heart.svg" class="icone">
				</a>
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
				<a href="Dashboard.jsp" class="menu">
					<img alt="Account" src="img/icone/person.svg" class="icone">
				</a>
				<%
						}
						else if(tipo.equals("Amministratore"))
						{
				%>
				<a href="DashboardAdmin.jsp" class="menu">
					<img alt="Account" src="img/icone/person.svg" class="icone">
				</a>
				<%
						}
					}
					else
					{
						carello=(Carello) session.getAttribute("carello");
				%>
				<a href="Login.jsp" class="menu">
					<img alt="Account" src="img/icone/person.svg" class="icone">
				</a>
				<%
					}
				%>
				<%
					System.out.println("Header carello: "+carello);
				%>
				<a href="Carello.jsp" class="menu" id="carello">
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
		</header>
		<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function () 
			{
				var quantitàCarello=$('#contatoreCarello').html();
				if(quantitàCarello>0)
				{
					$('#contatoreCarello').addClass("opacita");
				}
	 		});
	</script>
	</body>
</html>