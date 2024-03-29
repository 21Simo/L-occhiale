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

import model.Province;
import model.ProvinceDAO;

/**
 * Servlet che serve per ottenere le provincie dato una regione. 
 */
@WebServlet("/ProvinceServlet")
public class ProvinceServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger= Logger.getLogger(ProvinceServlet.class.getName());
	
	private ProvinceDAO provinceDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProvinceServlet() 
    {
        super();
        this.provinceDAO= new ProvinceDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int regione= Integer.parseInt(request.getParameter("regione"));
		JSONObject jsonProvince= new JSONObject();
		JSONArray jsonArray= new JSONArray();
		JSONArray jsonId= new JSONArray();
		try
		{
			ArrayList<Province> listaProvince= provinceDAO.province(regione);
			for(int i=0; i<listaProvince.size(); i++)
			{
				jsonArray.put(listaProvince.get(i).getNome());
				jsonId.put(listaProvince.get(i).getId());
			}
			jsonProvince.put("province", jsonArray);
			jsonProvince.put("id", jsonId);
			
			response.setContentType("application/json");
			PrintWriter out= response.getWriter();
			out.print(jsonProvince);
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
