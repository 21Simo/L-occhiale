<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
	String login=(String) request.getAttribute("login");
	System.out.println("JSP: "+login);
%>
	
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
	<meta charset="utf-8">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="./css/login.css">
</head>
<body>
	<nav>
		<%@ include file="Header.jsp" %>
	</nav>
	<div class="login">
		<div class="container" id="container">
			<!--  
			<div class="form-container sign-up-container">
				<form action="#" class="formLogin">
					<h1 class="grassetto">Create Account</h1>
					<div class="social-container">
						<a href="#" class="social linkLogin"><i class="fab fa-facebook-f"></i></a> 
						<a href="#" class="social linkLogin"><i class="fab fa-google-plus-g"></i></a> 
						<a href="#" class="social linkLogin"><i class="fab fa-linkedin-in"></i></a>
					</div>
					<span class="etichettaLogin">or use your email for registration</span> 
					<input type="text" placeholder="Name" class="inputLogin" /> <input type="email" placeholder="Email" />
					<input type="password" placeholder="Password" class="inputLogin" />
					<button class="bottoneLogin">Sign Up</button>
				</form>
			</div>-->
			<div class="form-container sign-in-container">
				<form action="LoginServlet" class="formLogin" method="post">
					<h1 class="grassetto">Login</h1>
					<input name="email" type="email" placeholder="Email" class="inputLogin" /> 
					<input name="password" type="password" placeholder="Password" class="inputLogin" /> 
					<!--  <a href="#" class="linkLogin">Forgot your password?</a>-->
					<% 
						if(login!=null)
						{
							if(login.equals("errore"))
							{
					%>
					<p class="erroreLogin">Errore: email o password errati.</p>
					<%
							}
						}
					%>
					<button class="bottoneLogin">Login</button>
				</form>
			</div>
			<div class="overlay-container">
				<div class="overlay">
					<!--  
					<div class="overlay-panel overlay-left">
						<h1 class="grassetto">Welcome Back!</h1>
						<p class="testoLogin">To keep connected with us please login with your personal info</p>
						<button class="ghost bottoneLogin" id="signIn">Sign In</button>
					</div>-->
					<div class="overlay-panel overlay-right">
						<h1 class="grassetto">Registrati</h1>
						<p class="testoLogin">Inserisci i tuoi dati e unisciti a noi.</p>
						<a href="Registrazione.jsp"><button class="ghost bottoneLogin" id="signUp">Registrati</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<%@ include file="Footer.jsp" %>
	</footer>
</body>
</html>