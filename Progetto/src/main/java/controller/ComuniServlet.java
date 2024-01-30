package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Comune;
import model.ComuneDAO;
import model.Province;

/**
 * Servlet che serve per ottenere i comuni selezionata una provincia. 
 */
@WebServlet("/ComuniServlet")
public class ComuniServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
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
		JSONArray prova= new JSONArray();
		try
		{
			ArrayList<Comune> listaComuni= comuneDAO.comuni(provincia);
			for(int i=0; i<listaComuni.size(); i++)
			{
				jsonArray.put(listaComuni.get(i).getNome());
			}
			jsonComuni.put("comuni", jsonArray);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		response.setContentType("application/json");
		PrintWriter out= response.getWriter();
		out.print(jsonComuni);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
