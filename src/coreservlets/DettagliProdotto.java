package coreservlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.Film;
import Utils.Film_Da_Visualizzare;
import Utils.ProdottoDaVisualizzare;
import Utils.Proiezione;

/**
 * Servlet implementation class DettagliProdotto
 */
@WebServlet("/DettagliProdotto")
public class DettagliProdotto extends HttpServlet 
{
	private ArrayList<Proiezione> elenco;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliProdotto()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		elenco = new ArrayList<>();
		String id = request.getParameter("id");
		ProdottoDaVisualizzare prod = null;
		
		PrintWriter out = response.getWriter();
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "lamianascita";
		System.out.println("Connecting database...");
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e2) 
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Connection connection;
		try 
		{
			connection = (Connection) DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		}
		catch (SQLException e) 
		{
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		
		Statement stmt = null;
		try
		{
			stmt = (Statement) connection.createStatement();
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		String query = "SELECT * FROM mydb.film f JOIN mydb.proiezioni p ON f.idFilm = p.idFilm;" ;
		ResultSet rs = null;
		try 
		{
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		int idProiezioni = 0;
		Proiezione film = new Proiezione();
		try 
		{
			while (rs.next())
			{
				 Proiezione a = new Proiezione(rs.getString("nomeFilm"), rs.getString("dataUscita"), rs.getString("genere"), rs.getString("descrizione"), rs.getString("locandina"), rs.getString("sala"), rs.getString("prezzo"), rs.getString("dataProiezione"));
			     idProiezioni = rs.getInt("idProiezioni");
				 elenco.add(a);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cont = 0;
		for(int i=0; i < elenco.size(); i++)
		{
			if(id.equals(""+idProiezioni))
			{
				cont = 1;
				film.setNome(elenco.get(i).getNome());
				film.setLocandina((elenco.get(i).getLocandina()));
				film.setDescrizione((elenco.get(i).getDescrizione()));
				film.setData((elenco.get(i).getData()));
				film.setGenere(elenco.get(i).getGenere());
				film.setDataProiezione(elenco.get(i).getDataProiezione());
				film.setPrezzo(elenco.get(i).getPrezzo());
				film.setSala(elenco.get(i).getSala());
				break;
			}
		}
		if(cont == 1)
		{
			System.out.println(film.getNome() + " " + film.getLocandina());
			HttpSession session = request.getSession();
			session.setAttribute("film", film);
			request.setAttribute("film", film);
			RequestDispatcher requestDispatcher; 
			String reg = request.getParameter("reg");
			System.out.println("reg=" + reg);
			if(reg != null)
			{
				requestDispatcher = request.getRequestDispatcher("reg_DettagliProdotto.jsp");
			}
			else
			{
				requestDispatcher = request.getRequestDispatcher("DettagliProdotto.jsp");
			}
			requestDispatcher.forward(request, response);
		}
		else
		{
			response.sendRedirect("proiezioneNonPresenteUtente.jsp");
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
