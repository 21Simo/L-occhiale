package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Comune;
import model.ComuneDAO;

/**
 * Servlet che serve per ottenere i comuni selezionata una provincia. 
 */
@WebServlet("/ComuniServlet")
public class ComuniServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(ComuniServlet.class.getName());
	
	private ComuneDAO comuneDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComuniServlet() 
    {
        super();
        this.comuneDAO= new ComuneDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int provincia= Integer.parseInt(request.getParameter("provincia"));
		JSONObject jsonComuni= new JSONObject();
		JSONArray jsonArray= new JSONArray();
		try
		{
			ArrayList<Comune> listaComuni= comuneDAO.comuni(provincia);
			for(int i=0; i<listaComuni.size(); i++)
			{
				jsonArray.put(listaComuni.get(i).getNome());
			}
			jsonComuni.put("comuni", jsonArray);
			response.setContentType("application/json");
			PrintWriter out= response.getWriter();
			out.print(jsonComuni);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.log(Level.INFO, "Exception", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
