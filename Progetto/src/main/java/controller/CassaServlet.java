package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.ColoreDAO;
import model.DettagliOrdine;
import model.DettagliOrdineDAO;
import model.Ordine;
import model.OrdineDAO;
import model.Pagamento;
import model.PagamentoDAO;
import model.ProdottoCarrello;
import model.Utente;

/**
 * Servlet che serve per la gestione della cassa.
 */
@WebServlet("/CassaServlet")
@MultipartConfig
public class CassaServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static java.util.logging.Logger logger= java.util.logging.Logger.getLogger(CassaServlet.class.getName());
	
	private PagamentoDAO pagamentoDAO;
	private DettagliOrdineDAO dettagliOrdineDAO;
	private OrdineDAO ordineDAO;
	private ColoreDAO coloreDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CassaServlet()
    {
        super();
        this.pagamentoDAO= new PagamentoDAO();
        this.dettagliOrdineDAO= new DettagliOrdineDAO();
        this.ordineDAO= new OrdineDAO();
        this.coloreDAO= new ColoreDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String nomeCliente=request.getParameter("nome");
		String numeroCarta=request.getParameter("numeroCarta");
		String scadenza=request.getParameter("scadenza");
		String cvv=request.getParameter("cvv");
		Pagamento pagamento= new Pagamento();
		pagamento.setNomeCliente(nomeCliente);
		pagamento.setNumeroCarta(numeroCarta);
		pagamento.setScadenza(scadenza);
		pagamento.setCvv(cvv);
		try
		{
			pagamentoDAO.inserisciPagamento(pagamento);
			Carrello carrello=(Carrello) request.getSession().getAttribute("carrelloUtente");
			Double importo= Double.parseDouble(carrello.getTotaleCosto());
			Date data= new Date(System.currentTimeMillis());
			int quantità= carrello.getQuantitaCarrello();
			Utente utente=(Utente) request.getSession().getAttribute("utente");
			DettagliOrdine dettagliOrdine= new DettagliOrdine();
			dettagliOrdine.setImporto(importo);
			dettagliOrdine.setIva(22.00);
			dettagliOrdine.setData(data);
			dettagliOrdine.setQuantità(quantità);
			dettagliOrdine.setIdUtente(utente.getId());
			dettagliOrdine.setIdPagamento(pagamentoDAO.ultimoId());
			dettagliOrdine.setStato("In elaborazione");
			ArrayList<ProdottoCarrello> listaProdotti= carrello.getListaProdotti();
			dettagliOrdineDAO.inserisciDettagliOrdine(dettagliOrdine);
			Ordine ordine= new Ordine();
			ordine.setIdOrdine(dettagliOrdineDAO.ultimoId());
			for(int i=0; i<listaProdotti.size(); i++)
			{
				coloreDAO.aggiornaQuantità(listaProdotti.get(i).getId(), listaProdotti.get(i).getColore().getId(), listaProdotti.get(i).getColore().getQuantità());
				if(listaProdotti.get(i).getGradazione().equals("Graduati"))
				{
					String file= listaProdotti.get(i).getPath();
					String pathTemp="D:\\Università\\2 anno\\2 semestre\\TSW\\RepoGithub\\Progetto\\src\\main\\webapp\\tmp file\\";
					String destinazione="D:\\Università\\2 anno\\2 semestre\\TSW\\RepoGithub\\Progetto\\src\\main\\webapp\\file\\";
					Files.move(Paths.get(pathTemp+file.substring(file.lastIndexOf("\\")+1)), Paths.get(destinazione+file.substring(file.lastIndexOf("\\")+1)));
					ordine.setFile(file);
				}
				ordine.setIdProdotto(listaProdotti.get(i).getId());
				ordine.setIdColore(listaProdotti.get(i).getColore().getId());
				ordine.setQuantitàProdotto(listaProdotti.get(i).getColore().getQuantità());
				ordine.setPrezzo(listaProdotti.get(i).getColore().getPrezzo());
				String immagineProdotto= listaProdotti.get(i).getColore().getImmagine();
				int indice= immagineProdotto.lastIndexOf("/");
				immagineProdotto= immagineProdotto.substring(indice+1);
				ordine.setImmagineProdotto(immagineProdotto);
				ordine.setNomeProdotto(listaProdotti.get(i).getNome());
				ordine.setColoreProdotto(listaProdotti.get(i).getColore().getColore());
				ordineDAO.inserisciOrdine(ordine);
				carrello= null;
				request.getSession().setAttribute("carrelloUtente", carrello);
				request.getSession().setAttribute("carrello", carrello); 
			}
			RequestDispatcher dispatcher= request.getRequestDispatcher("Pagamento.jsp");
			dispatcher.forward(request, response);
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
