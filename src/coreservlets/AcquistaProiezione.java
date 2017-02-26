package coreservlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.userBeans;

/**
 * Servlet implementation class AcquistaProiezione
 */
@WebServlet("/AcquistaProiezione")
public class AcquistaProiezione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcquistaProiezione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
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
		
		//prendo nome e id del film
		String nomeFilm = request.getParameter("Indice");
		int idProiezioni = 0;
		
		//prendo il nome dell'utente
		int idUtente = 0;
		String nomeUtente = "";
		File file = new File("reg_utente.txt");		
		FileInputStream inFile = new FileInputStream(file);
		ObjectInputStream inStream = new ObjectInputStream(inFile);
		try {
			nomeUtente=(String)inStream.readObject();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query = "SELECT * FROM mydb.film f JOIN mydb.proiezioni p ON f.idFilm = p.idFilm;" ;
		String sala = "";
		ResultSet rs = null;
		try 
		{
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try 
		{
			while (rs.next())
			{
				if(rs.getString("nomeFilm").equals(nomeFilm))
				{
					idProiezioni = rs.getInt("idProiezioni");
					sala = rs.getString("sala");
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		query = "SELECT * FROM mydb.utenti;" ;
		try 
		{
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try 
		{
			while (rs.next())
			{
				if(rs.getString("username").equals(nomeUtente))
				{
					idUtente = rs.getInt("idUtenti");
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		GregorianCalendar gc = new GregorianCalendar();
		String data = gc.get(Calendar.DAY_OF_MONTH) + "/" + (gc.get(Calendar.MONTH)+1) + "/" + gc.get(Calendar.YEAR);
		String posto = request.getParameter("posto");
		
		query = "SELECT * FROM mydb.prenotazioni;" ;
		try 
		{
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		int cont = 0;
		try 
		{
			while (rs.next())
			{
				if((rs.getString("sala").equals(sala))&&(rs.getString("posto").equals(posto)))
				{
					cont = 1;
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		if(cont == 0)
		{
			query = "INSERT INTO `mydb`.`prenotazioni` (`idProiezioni`, `idUtenti`, `data`, `posto`, `sala`) VALUES ('"+idProiezioni+"', '"+idUtente+"', '"+data+"', '"+posto+"','"+sala+"');";
			int rs2 = 0;
			try 
			{
				rs2 = stmt.executeUpdate(query);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			response.sendRedirect("acquistoEffettuato.jsp");
		}
		else
		{
			request.setAttribute("msg", "Posto già prenotato!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("acquistaProiezione.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
