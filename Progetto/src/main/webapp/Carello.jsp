<%@page import="model.ColoreDAO"%>
<%@page import="model.ProdottoCarello"%>
<%@page import="model.Prodotto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Carello</title>
	<link rel="stylesheet" href="./css/carello.css">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp"%>
	</nav>

	<h1>Carello</h1>
	
	<%
		Carello carelloSessione=(Carello) session.getAttribute("carello");
	%>
	
	<%
		if(carelloSessione==null)
		{
	%>
	
	<div class="carelloVuoto">
		<img src="./img/carello vuoto.png" class="immagineCarelloVuoto">
		<p class="testoCarelloVuoto">Il carello è vuoto</p>
	</div>
	
	<%
		}
		else
		{
			ArrayList<ProdottoCarello> listaProdotti=carello.getListaProdotti();
	%>

	<div class="shopping-cart group">

		<div class="group">
			<label class="product-image etichettaColonna immagineColonna">Immagine</label> 
			<label class="product-details etichettaColonna dettagliColonna">Prodotto</label> 
			<label class="product-price etichettaColonna">Prezzo</label>
			<label class="product-quantity etichettaColonna">Quantità</label> 
			<label class="product-removal etichettaColonna rimuoviProdottoColonna">Rimuovi</label> 
			<label class="product-line-price etichettaColonna">Totale</label>
		</div>
		
		<%
			for(int i=0; i<listaProdotti.size(); i++)
			{
		%>

		<div class="product group prodotto">
			<div class="product-image immagineProdotto">  
				<img src="<%=listaProdotti.get(i).getColore().getImmagine() %>" class="immagine">
			</div>
			<div class="product-details">
				<div class="dettagliProdottoTitolo"><%=listaProdotti.get(i).getNome() %></div>
				<p class="dettagliProdottoDescrizione">
					Colore: <%=listaProdotti.get(i).getColore().getColore() %>
				</p>
				<p class="dettagliProdottoDescrizione">
					<%=listaProdotti.get(i).getGradazione() %>
				</p>
				<%
					if(listaProdotti.get(i).getGradazione().equals("Graduati")==true)
					{
				%>
				<p class="dettagliProdottoDescrizione">
					Prescrizione medica: <%=listaProdotti.get(i).getFile() %>
				</p>
				<%
					}
				%>
			</div>
			<div class="product-price"> <%=listaProdotti.get(i).getColore().getPrezzo() %></div>
			<div class="product-quantity counter">
				<form action="VisualizzazioneCarelloServlet" method="post">
					<button name="quantitàBottone" value="meno-<%=i%>" class="bottoneNascosto">
						<span class="down" onClick='decreaseCount(event, this)'>-</span>
					</button>
				</form>
  				<input type="text" value="<%=listaProdotti.get(i).getColore().getQuantità() %>" class="quantitaProdottoInput" id="numeroQuantità">
  				<form action="VisualizzazioneCarelloServlet" method="post">
  					<button name="quantitàBottone" value="più-<%=i%>" class="bottoneNascosto">
  						<span class="up" onClick='increaseCount(event, this)'>+</span>
  					</button>
  				</form>
			</div>
			
			<div class="product-removal">
				<form action="VisualizzazioneCarelloServlet" method="post">
					<button class="rimuoviProdotto" name="prodotto" value="<%=i%>">Rimuovi</button>
				</form>
			</div>
			<%
				Double prezzo=Double.parseDouble(listaProdotti.get(i).getColore().getPrezzo());
				int quantità=listaProdotti.get(i).getColore().getQuantità();
				Double totale=prezzo*quantità;
				ColoreDAO coloreDAO= new ColoreDAO();
				String totaleStringa=coloreDAO.prezzo(totale.toString());
			%>
			<div class="product-line-price"> <%=totaleStringa %></div>
		</div>
		
		<%
			}
		%>

		<div>
			<div class="group totaliElementi">
				<label class="etichettaTotaleElemento">Costo prodotti</label>
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
				<div class="totals-value totaleValoriElemento" id="cart-subtotal">
					<%=costoProdottiString %>
				</div>
			</div>
			<div class="group totaliElementi">
				<label class="etichettaTotaleElemento">Tax (5%)</label>
				<div class="totals-value totaleValoriElemento" id="cart-tax">3.60</div>
			</div>
			<div class="group totaliElementi">
				<label class="etichettaTotaleElemento">Spedizione</label>
				<div class="totals-value totaleValoriElemento" id="cart-shipping">15.00</div>
			</div>
			<div class="group totals-item-total totaliElementi totaleCarello">
				<label class="etichettaTotaleElemento">Totale</label>
				<div class="totals-value totaleValoriElemento" id="cart-total">90.57</div>
			</div>
		</div>

		<button class="checkout">Vai alla cassa</button>

	</div>
	
	<%
		}
	%>

	<footer>
		<%@ include file="Footer.jsp"%>
	</footer>
	<script type="text/javascript" charset="UTF-8" src="./script/carello.js"></script>
</body>
</html>