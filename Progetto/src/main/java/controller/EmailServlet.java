package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	static Logger logger= Logger.getLogger(EmailServlet.class.getName());
	
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
			response.setContentType("application/json");
			PrintWriter out= response.getWriter();
			out.print(json);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.log(Level.INFO, "Exception", e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		doGet(request, response);
	}
}
