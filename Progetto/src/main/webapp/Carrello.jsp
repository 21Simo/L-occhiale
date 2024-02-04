<%@page import="model.ColoreDAO"%>
<%@page import="model.ProdottoCarrello"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Carrello</title>
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="./css/carrello.css" rel="stylesheet">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp"%>  
	</nav>
	
	<%
	Carrello carrelloSessione;
	if(utente!=null)
	{
		carrelloSessione=(Carrello) session.getAttribute("carrelloUtente");
	}
	else
	{
		carrelloSessione=(Carrello) session.getAttribute("carrello");
	}
	if(carrelloSessione==null)
	{
	%>
	
	<div class="carelloVuoto">
		<img src="./img/carrello vuoto.png" class="immagineCarelloVuoto">
		<p class="testoCarelloVuoto">Il carrello è vuoto</p>
	</div>
	
	<%
	}
	else
	{
		ArrayList<ProdottoCarrello> listaProdotti=carrelloSessione.getListaProdotti();
	%>

	<div class="wrapper">
		<h1>Carrello</h1>
		<div class="project">
			<div class="shop">
			<%
			for(int i=0; i<listaProdotti.size(); i++)
			{
			%>
				<div class="box">
					<img src="<%=listaProdotti.get(i).getColore().getImmagine() %>">
					<div class="content">
						<h3><%=listaProdotti.get(i).getNome() %>
							<p>Colore: <%=listaProdotti.get(i).getColore().getColore() %></p>
						</h3>
						<%
						Double prezzo=Double.parseDouble(listaProdotti.get(i).getColore().getPrezzo());
						int quantità=listaProdotti.get(i).getColore().getQuantità();
						Double totale=prezzo*quantità;
						ColoreDAO coloreDAO= new ColoreDAO();
						String totaleStringa=coloreDAO.prezzo(totale.toString());
						%>
						<h4>Prezzo: <%=totaleStringa %> &euro;</h4>
						<p>Gradazione: <%=listaProdotti.get(i).getGradazione() %></p>
						<div class="product-quantity counter">
							<form action="VisualizzazioneCarrelloServlet" method="post">
								<button name="quantitàBottone" value="meno-<%=i%>" class="bottoneNascosto">
									<span class="down" onClick='decreaseCount(event, this)'>-</span>
								</button>
							</form>
  							<input type="text" value="<%=listaProdotti.get(i).getColore().getQuantità() %>" class="quantitaProdottoInput" id="numeroQuantità" readonly="readonly">
  							<form action="VisualizzazioneCarrelloServlet" method="post">
  								<button name="quantitàBottone" value="più-<%=i%>" class="bottoneNascosto">
  									<span class="up" onClick='increaseCount(event, this)'>+</span>
  								</button>
  							</form>
						</div>
						<form action="VisualizzazioneCarrelloServlet" method="post">
							<button class="btn-area" name="prodotto" value="<%=i%>">
								<i aria-hidden="true" class="fa fa-trash"></i>
								Rimuovi
							</button>
						</form>
					</div>
				</div>
			<%
			}
			%>
			</div>
			<div class="right-bar">
			<%
			Double costoProdotti=0.0;
			Double prezzo,totaleProdotto;
			int quantità;
			for(int i=0; i<listaProdotti.size(); i++)
			{
				prezzo=Double.parseDouble(listaProdotti.get(i).getColore().getPrezzo());
				quantità=listaProdotti.get(i).getColore().getQuantità();
				totaleProdotto=prezzo*quantità;
				costoProdotti=costoProdotti+totaleProdotto;
			}
				ColoreDAO coloreDAO=new ColoreDAO();
				String costoProdottiString=coloreDAO.prezzo(costoProdotti.toString());
			%>
				<p><span>Subtotale</span> <span><%=costoProdottiString %> &euro;</span></p>
				<hr>
				<p><span>Spedizione</span> <span>Gratuita</span></p>
				<hr>
				<p><span>Totale</span> <span><%=costoProdottiString %> &euro;</span></p>
				<%
				if(utente==null)
				{
				%>
				<a href="Login.jsp"><i class="fa fa-shopping-cart"></i>Checkout</a>
				<%
				}
				else
				{
				%>
				<a href="Cassa.jsp"><i class="fa fa-shopping-cart"></i>Checkout</a>
				<%
				}
				%>
			</div>
		</div>
	</div>
	<%
		}
	%>
	
	<footer>
		<%@ include file="Footer.jsp"%>
	</footer>
</body>
</html>