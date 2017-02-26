package coreservlets;

import java.io.File;
import java.io.FileOutputStream;
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
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.Film;
import Utils.ProdottoDaVisualizzare;
import Utils.Proiezione;
import Utils.userBeans;

/**
 * Servlet implementation class ApplicaModificheAdmin
 */
@WebServlet("/ApplicaModificheAdmin")
public class ApplicaModificheAdmin extends HttpServlet 
{
	private ArrayList<Film> elenco;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplicaModificheAdmin() 
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//connessione al db
		String id = request.getParameter("nome");
		System.out.println("----->" + id);
		
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
		Proiezione film = new Proiezione();
		int idProiezione = 0;
		try 
		{
			while (rs.next())
			{
			     Proiezione a = new Proiezione(rs.getString("nomeFilm"), rs.getString("dataUscita"), rs.getString("genere"), rs.getString("descrizione"), rs.getString("locandina"), rs.getString("sala"), rs.getString("prezzo"), rs.getString("dataProiezione"));
			     if(id.equals(rs.getString("nomeFilm")))
					{
			    	 	idProiezione = rs.getInt("idProiezioni");
						film.setNome(rs.getString("nomeFilm"));
						film.setLocandina(rs.getString("locandina"));
						film.setDescrizione(rs.getString("descrizione"));
						film.setData(rs.getString("dataUscita"));
						film.setGenere(rs.getString("genere"));
						film.setDataProiezione(rs.getString("dataProiezione"));
						film.setPrezzo(rs.getString("prezzo"));
						film.setSala(rs.getString("sala"));
						break;
					}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DA QUI ABBIAMO L'ELENCO
		Proiezione prod2 = new Proiezione();

		if(!(request.getParameter("nuovocpu").trim().equals("")))
		{
			film.setDataProiezione(request.getParameter("nuovocpu"));
		}
		if(!(request.getParameter("sale").trim().equals("")))
		{
			film.setSala(request.getParameter("sale"));
		}
		if(!(request.getParameter("prezzo").trim().equals("")))
		{
			film.setPrezzo(request.getParameter("prezzo"));
		}
		
		
		query = "UPDATE `mydb`.`proiezioni` SET `dataProiezione`='"+film.getDataProiezione()+"', `sala`='"+film.getSala()+"', `prezzo`='"+film.getPrezzo()+"' WHERE `idProiezioni`='"+idProiezione+"';";
		int rs2 = 0;
		try 
		{
			rs2 = stmt.executeUpdate(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("film", film);
		RequestDispatcher requestDispatcher; 
		requestDispatcher = request.getRequestDispatcher("DettagliProdottoAdmin.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
