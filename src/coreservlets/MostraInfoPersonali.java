package coreservlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class MostraInfoPersonali
 */
@WebServlet("/MostraInfoPersonali")
public class MostraInfoPersonali extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostraInfoPersonali() {
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
		String nomeUtente="";
		
		File file = new File("reg_utente.txt");		
		FileInputStream inFile = new FileInputStream(file);
		ObjectInputStream inStream = new ObjectInputStream(inFile);
		try {
			nomeUtente=(String)inStream.readObject();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
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
		String table="";
		try 
		{
			while (rs.next())
			{
				if(rs.getString("username").equals(nomeUtente))
			     table+="<tr>"
			     		+ "<td>"
			     		+ "<b>Nome</b></td><td>"
			     		+ rs.getString("nomeUtente")
						+ "</td> </tr>"
						+ "<tr> <td>"
						+ "<b>Cognome</b> </td>"
						+ "<td>"
						+ rs.getString("cognomeUtente")
						+ "</td></tr><tr><td>"
						+ "<b>Email</b></td>"
						+ "<td>"
						+ rs.getString("email")
						+ "</td></tr><tr><td>"
						+ "<b>Data di Nascita</b></td>"
						+ "<td>"
						+ rs.getString("dataNascita")
						+ "</td></tr><tr><td>"
						+ "<b>Username</b></td><td>"
						+ rs.getString("username")
						+ "</td></tr><tr><td>"
						+"<b>Residenza</b></td><td>"
						+ rs.getString("residenza")
						+ "</td></tr><tr><td>"
						+ "<b>CAP</b></td>"
						+ "<td>"
						+ rs.getString("cap")
						+ "</td></tr><tr><td>"
						+ "<b>CF</b></td>"
						+ "<td>"
						+ rs.getString("codiceFiscale")
						+ "</td></tr>";
						
						
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(table);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
