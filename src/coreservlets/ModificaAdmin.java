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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.Film;
import Utils.Proiezione;

/**
 * Servlet implementation class ModificaAdmin
 */
@WebServlet("/ModificaAdmin")
public class ModificaAdmin extends HttpServlet 
{
	private ArrayList<Film> elenco;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaAdmin() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//immagine importata
				elenco = new ArrayList<>();
			/*	String pathinit = contextinit.getRealPath("/img");
				
				Part filepart = request.getPart("nuovonome");
				
				//lettura prima foto e creazione cartella	
				InputStream in = filepart.getInputStream();
				String nome = request.getParameter("nome").replace(" ", "_").replace("\"", "");
				boolean success = new File(pathinit + "/" + nome).mkdirs();
				if(!success)
				{
					System.out.println("Nuoooooo");
				}
				File fotoprincipale = new File(pathinit + "/" + nome + ".jpg");
				
				outinit = new FileOutputStream(fotoprincipale);
				for(int i = 0; i < filepart.getSize(); i++)
				{
					outinit.write(in.read());
				}*/
				
				//connessione al db
				
				String id = request.getParameter("id");
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
				int cont = 0;
				try 
				{
					while (rs.next())
					{
					     Proiezione a = new Proiezione(rs.getString("nomeFilm"), rs.getString("dataUscita"), rs.getString("genere"), rs.getString("descrizione"), rs.getString("locandina"), rs.getString("sala"), rs.getString("prezzo"), rs.getString("dataProiezione"));
					     if(id.equals(rs.getString("nomeFilm")))
							{
					    	 	cont = 1;
								System.out.println("sono dentro");
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
			
				System.out.println(film.getNome() + " " + film.getLocandina());
				HttpSession session = request.getSession();
				session.setAttribute("film", film);
				request.setAttribute("film", film);
				RequestDispatcher requestDispatcher; 
				requestDispatcher = request.getRequestDispatcher("modificaAdmin.jsp");
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
