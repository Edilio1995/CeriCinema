package coreservlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.Film;
import Utils.Film_Da_Visualizzare;
import Utils.ProdottoDaVisualizzare;
import Utils.userBeans;

/**
 * Servlet implementation class AggiungiProdottoAdmin
 */
@WebServlet("/AggiungiProdottoAdmin")
@MultipartConfig
public class AggiungiProdottoAdmin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiProdottoAdmin() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		//connessione al db
		HttpSession session = request.getSession();
		userBeans users = ((userBeans) session.getAttribute("user"));
		
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
		
		//connesione stabilita ed esecuzione della query
		
		String titolo = request.getParameter("nome");
		String idFilm = request.getParameter("cpu");
		String sale = request.getParameter("sale");
		String prezzo = request.getParameter("prezzo");
		String dataProiezione = request.getParameter("gpu");
		
		//controllo dati
		if(request.getParameter("nome").trim().length() == 0)
		{
			response.sendRedirect("admin_errore.jsp?Nome non inserito");
		}
		else if((request.getParameter("gpu").charAt(2) != '/')||(request.getParameter("gpu").charAt(5) != '/'))
		{
			response.sendRedirect("admin_errore.jsp?Data non corretta(gg/mm/aaaa)");
		}
		else
		{
			String query = "INSERT INTO `mydb`.`proiezioni` (`nomeProiezione`, `sala`, `dataProiezione`, `prezzo`, `idFilm`) VALUES ('" + titolo + "', '" + sale + "', '" + dataProiezione +"', '" + prezzo + "', '" + idFilm + "');" ;
			try 
			{
				stmt.executeUpdate(query);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			response.sendRedirect("Inserimento_completato.jsp");
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
