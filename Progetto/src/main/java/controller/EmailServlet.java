package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.UtenteDAO;

/**
 * Servlet che serve per vedere se un email è già presente nel database. 
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private UtenteDAO utenteDAO;
       
    
    public EmailServlet()
    {
        super();
        this.utenteDAO= new UtenteDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email= request.getParameter("email");
		JSONObject json= new JSONObject();
		try
		{
			json.put("presenza", utenteDAO.presenzaEmail(email));
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		response.setContentType("application/json");
		PrintWriter out= response.getWriter();
		out.print(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		doGet(request, response);
	}
}
