<%@page import="model.ColoreDAO"%>
<%@page import="model.ProdottoCarello"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Cassa</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="./css/cassa.css">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>

	<div class="row">
		<div class="col-75">
			<div class="container">
				<form name="form" action="CassaServlet" onsubmit="return validazionePagamento()" method="post">

					<div class="row">
						<div class="col-50">
							<h3>Pagamento</h3>
							<label for="fname" class="etichettaCassa">Carte accettate</label>
							<div class="icon-container">
								<img alt="" src="img/icone/icons8-visa.svg">
								<img alt="" src="img/icone/icons8-mastercard.svg">
								<img alt="" src="img/icone/icons8-maestro.svg">
								<img alt="" src="img/icone/icons8-paypal.svg">
							</div>
							<div>
								<label for="cname" class="etichettaCassa">Nome sulla carta</label> 
								<input type="text" id="nome" name="nome" placeholder="Inserisci il nome e il cognome presente sulla tua carta" class="inputText" required>
								<p id="erroreNome" class="errore"></p>
							</div>
							<div>
								<label for="ccnum" class="etichettaCassa">Numero carta di credito</label> 
								<input type="text" id="numeroCarta" name="numeroCarta" placeholder="Inserisci il numero della tua carta" class="inputText" required>
								<p id="erroreNumeroCarta"></p>
							</div>
							<div>
								<label for="expmonth" class="etichettaCassa">Scadenza (mese anno)</label> 
								<input type="month" id="scadenza" name="scadenza" class="inputText">
								<p id="erroreScadenza"></p>
							</div>
							<div class="row">
								<div class="col-50">
									<label for="cvv" class="etichettaCassa">CVV</label> 
									<input type="text" id="cvv" name="cvv" placeholder="Inserisci il CVV della tua carta" class="inputText">
									<p id="erroreCVV"></p>
								</div>
							</div>
						</div>

					</div>
					<input type="submit" value="Vai al pagamento" class="btn">
				</form>
			</div>
		</div>
		<div class="col-25">
			<div class="container">
			<%
				Carello carelloSessione=null;
				if(utente!=null)
				{
					carelloSessione=(Carello) session.getAttribute("carelloUtente");
				}
				else
				{
					RequestDispatcher dispatcher=request.getRequestDispatcher("/Login.jsp");
					dispatcher.forward(request, response);
				}
				System.out.println("Cassa: "+carelloSessione);
				if(carelloSessione==null)
				{
					RequestDispatcher dispatcher=request.getRequestDispatcher("/Carello.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
			%>
				<h4>
					Carello
					<span class="price" style="color: black">
						<i class="fa fa-shopping-cart"></i> 
						<b><%=carelloSessione.getQuantitaCarello() %></b>
					</span>
				</h4>
				<%
					ArrayList<ProdottoCarello> prodottiCarello=carelloSessione.getListaProdotti();
					Double totale=0.0;
					for(int i=0; i<prodottiCarello.size(); i++)
					{
				%>
				<p>
					<span class="linkCassa"><%=prodottiCarello.get(i).getNome() %></span> 
					<%
						Double prezzo=Double.parseDouble(prodottiCarello.get(i).getColore().getPrezzo());
						int quantità=prodottiCarello.get(i).getColore().getQuantità();
						totale=totale+(prezzo*quantità);
						System.out.println("Cassa JSP totale: "+totale);
						ColoreDAO coloreDAO= new ColoreDAO();
						String totaleStringa=coloreDAO.prezzo(totale.toString());
						System.out.println("Cassa JSP totale ehh: "+totaleStringa);
						carelloSessione.setTotaleCosto(totaleStringa);
					%>
					<span class="price"><%=totaleStringa %> &euro;</span>
				</p>
				<%
					}
				%>
				<hr>
				<%
					Double costoProdotti=0.0;
					Double prezzo,totaleProdotto;
					int quantità;
					for(int i=0; i<prodottiCarello.size(); i++)
					{
						prezzo=Double.parseDouble(prodottiCarello.get(i).getColore().getPrezzo());
						quantità=prodottiCarello.get(i).getColore().getQuantità();
						totaleProdotto=prezzo*quantità;
						costoProdotti=costoProdotti+totaleProdotto;
					}
					ColoreDAO coloreDAO=new ColoreDAO();
					String costoProdottiString=coloreDAO.prezzo(costoProdotti.toString());
				%>
				<p>
					Total 
					<span class="price" style="color: black">
						<b><%=costoProdottiString %> &euro;</b>
					</span>
				</p>
			<%
				}
			%>
			</div>
		</div>
	</div>
	
	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>
	
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="./script/cassa.js"></script>
</body>
</html>