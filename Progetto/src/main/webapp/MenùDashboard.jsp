<%@page import="model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Menù Dashboard</title>
</head>
<body>
	<!-- Header -->
      <header class="header">
        <div class="menu-icon" onclick="openSidebar()">
          <span class="material-icons-outlined coloreMenu">menu</span>
        </div>
      </header>
      <!-- End Header -->
      
      <%
      Utente utenteMenù=(Utente) request.getSession().getAttribute("utente");
      %>

      <!-- Sidebar -->
      <aside id="sidebar">
        <div class="sidebar-title">
          <div class="sidebar-brand">
            Ciao <%=utenteMenù.getNome() %>
          </div>
          <span class="material-icons-outlined" onclick="closeSidebar()">close</span>
        </div>

        <ul class="sidebar-list">
          <li class="sidebar-list-item">
            <a href="DashboardUtenteServlet">
              Dashboard
            </a>
          </li>
          <li class="sidebar-list-item">
            <a href="Account.jsp">
              Account
            </a>
          </li>
          <li class="sidebar-list-item">
            <a href="OrdiniServlet">
              Ordini
            </a>
          </li>
          <li class="sidebar-list-item">
            <a href="LogoutServlet">
              Logout
            </a>
          </li>
        </ul>
      </aside>
      <!-- End Sidebar -->
</body>
</html>