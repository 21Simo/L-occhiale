<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<nav class="navDashboard">
		<div class="menu-items">
			<ul class="nav-links">
				<li>
					<a href="DashboardAdmin.jsp"> 
						<i class="uil uil-estate"></i>
						<span class="link-name">Dahsboard</span>
					</a>
				</li>
				<li>
					<form action="Prodotto" method="post">
						<button name="prodotti" class="noButton menu" value="dashboard">
							<i class="uil uil-files-landscapes"></i> 
							<span class="link-name">Catalogo</span>
						</button>							
					</form>
				</li>
				<li>
					<a href="OrdiniAdmin.jsp"> 
						<i class="uil uil-files-landscapes"></i> 
						<span class="link-name">Ordini</span>
					</a>
				</li>
			</ul>

			<ul class="logout-mode nav-links">
				<li><a href="LogoutServlet"> <i class="uil uil-signout"></i> <span
						class="link-name">Logout</span>
				</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>