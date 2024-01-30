package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Colore;
import model.ColoreDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet che serve per l'eliminazione del prodotto. 
 */
@WebServlet("/EliminaProdottoServlet")
public class EliminaProdottoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private ProdottoDAO prodottoDAO;
	
	private ColoreDAO coloreDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaProdottoServlet() 
    {
        super();     
        this.prodottoDAO= new ProdottoDAO();
        this.coloreDAO= new ColoreDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int idProdotto= Integer.parseInt(request.getParameter("idProdotto"));
		int idColore= Integer.parseInt(request.getParameter("coloreProdotto"));
		System.out.println(idProdotto);
		System.out.println(idColore);
		ArrayList<Colore> listaColore= new ArrayList<Colore>();
		
		try
		{
			if(idColore==-1)
			{
				listaColore= coloreDAO.colorePerId(idProdotto);
				System.out.println(listaColore.size());
				for(int i=0; i<listaColore.size(); i++)
				{
					System.out.println("ID: "+listaColore.get(i).getId());
					System.out.println("ID colore: "+listaColore.get(i).getIdProdotto());
					Colore colore= new Colore();
					colore.setId(listaColore.get(i).getId());
					colore.setIdProdotto(listaColore.get(i).getIdProdotto());
					coloreDAO.eliminaColore(colore);
				}
				Prodotto prodotto= new Prodotto();
				prodotto.setId(idProdotto);
				prodottoDAO.eliminaProdotto(prodotto);
			}
			else
			{
				Colore colore= new Colore();
				colore.setId(idColore);
				colore.setIdProdotto(idProdotto);				
				coloreDAO.eliminaColore(colore);
				listaColore= coloreDAO.colorePerId(idProdotto); 
				if(listaColore.size()==0)
				{
					Prodotto prodotto= new Prodotto();
					prodotto.setId(idProdotto);
					prodottoDAO.eliminaProdotto(prodotto);
				}				
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("DashboardAdminServlet"); 
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
