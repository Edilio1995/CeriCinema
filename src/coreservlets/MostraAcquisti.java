package coreservlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

import Utils.userBeans;

/**
 * Servlet implementation class MostraAcquisti
 */
@WebServlet("/MostraAcquisti")
public class MostraAcquisti extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostraAcquisti() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String pass = "lamianascita";
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
			connection = (Connection) DriverManager.getConnection(url, user, pass);
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
		String query = "SELECT * FROM mydb.utenti;" ;
		ResultSet rs = null;
		try 
		{
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		int idUtente = 0;
		try 
		{
			while (rs.next())
			{
			     if(rs.getString("nomeUtente").equals(nomeUtente))
			     {
			    	 idUtente = rs.getInt("idUtenti");
			     }
			 }
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		
		query = "SELECT * FROM ((mydb.prenotazioni p JOIN mydb.proiezioni pr ON p.idProiezioni = pr.idProiezioni)JOIN mydb.film f ON pr.idFilm = f.idFilm)JOIN mydb.utenti ON p.idUtenti = utenti.idUtenti;";
		rs = null;
		int cont = 0;
		String table = "<tr><th>Nome Film</th><th>Data</th></tr>";
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
			    	 cont = 1;
			    	 table+= "<tr><td>" + rs.getString("nomeFilm") + "</td><td>" + rs.getString("data") +"</td></tr>";
			     }
			 }
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if(cont == 1)
		{
			out.println(table);
		}
		else
		{
			out.println("<tr><th>Ancora nessun prodotto acquistato</th></tr>");
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
