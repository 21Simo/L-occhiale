<%@page import="org.json.JSONObject"%>
<%@page import="model.Prodotto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.Prodotto"%>
    
<%
	JSONObject json= (JSONObject) request.getAttribute("prodotti");
%>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Prodotti</title>
	<meta name="viewport" content="width-device-width, initial-scale-1.0">
	<link rel="stylesheet" href="./css/multiple.css">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	<div class="gallery">
		<%
			for(int i=0; i<json.length(); i++)
			{
				JSONObject prodotto=(JSONObject) json.get("prodotto"+i);
				JSONObject colore=(JSONObject) prodotto.get("colore0");
				//System.out.println("ProdottoJSP: "+colore.get("id"));
		%>
		<div class="content" id="card-<%=i %>">
			<form action="DettaglioProdottoServlet" method="post">
				<button type="submit" name="bottone" value="<%=prodotto.get("id")%>/colore0<%-- <%=colore.get("id") %>--%>" class="dettagli" id="bottone-<%=i %>">
					<%
						session.setAttribute("prodotto", json);
					%>
					<img src="./img/prodotti/<%=colore.get("immagine")%>" class="img-prodotto" id="img-<%=i %>">
					<h3><%=prodotto.get("nome") %></h3>
					<%
						/*
						System.out.println("ProdottiJSP: "+colore.get("prezzo"));
						String prezzo=colore.get("prezzo").toString();
						int punto=prezzo.indexOf(".");
						System.out.println(punto);
						String prezzoIntero=prezzo.substring(0, punto);
						System.out.println(prezzoIntero);
						String prezzoDopoPunto=prezzo.substring(punto+1);
						System.out.println(prezzoDopoPunto);
						String prezzoModificato="";
						if(prezzoDopoPunto.equals("0"))
						{
							prezzoModificato=prezzoIntero;
						}
						else
						{
							prezzoModificato=prezzo;
						}
						System.out.println("PrezzoModificato: "+prezzoModificato);
						*/
					%>
					<h6 id="prezzoProdotto-<%=i%>"> <%=colore.get("prezzo") %> &euro;</h6>
				</button>
			</form>
			<div class = "color-content">
              <div class = "color-groups">
              	<%
					for(int k=0; k<prodotto.names().length(); k++)
					{
						if(prodotto.names().get(k).toString().contains("colore")==true)
						{
							String nome=prodotto.names().get(k).toString();
							JSONObject coloreSelezionato=(JSONObject) prodotto.get(prodotto.names().get(k).toString());
							//System.out.println("JSP prodotto: "+coloreSelezionato);
							if(nome.equals("colore0"))
							{
			 	 %>
              	 
              	<img title="colore0" name="<%=coloreSelezionato.get("id") %>" src="./img/prodotti/<%=coloreSelezionato.get("immagine") %>" class="color card-<%=i %> active-color" id="<%=coloreSelezionato.get("immagine") %>" onclick="cambiaImmagine(id,<%=coloreSelezionato.get("prezzo")%>)">
              	
              	<%
							}
              				else
              				{            	  
              	%>
              	
              	<img title="<%=nome %>" name="<%=coloreSelezionato.get("id") %>" src="./img/prodotti/<%=coloreSelezionato.get("immagine") %>" class="color card-<%=i %>" id="<%=coloreSelezionato.get("immagine") %>" onclick="cambiaImmagine(id,<%=coloreSelezionato.get("prezzo")%>)">
                
                <%
              				}
						}
					}
                %>
              </div>
            </div>
		</div>
		<%
			}
		%>
	</div>
	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>
	<script type="text/javascript"charset="UTF-8" src="./script/cambiaImmagine.js"></script>
</body>
</html>