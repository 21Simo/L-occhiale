<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="no-js">
<head>
	<meta charset="utf-8">
	<title>Pagamento</title>
	<link rel="stylesheet" href="./css/pagamento.css">
</head>
<body>
	<div class="payment-loader" id="caricamento">
		<div class="pad">
			<div class="chip"></div>
			<div class="line line1"></div>
			<div class="line line2"></div>
		</div>
		<div class="loader-text">Stiamo effettuando il pagamento.</div>
	</div>
	<div class="principale" id="principale"> 
	</div>
	<script type="text/javascript" src="./script/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="./script/pagamento.js"></script>
</body>
</html>