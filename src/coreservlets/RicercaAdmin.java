package coreservlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Ricerca
 */
@WebServlet("/RicercaAdmin")
public class RicercaAdmin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaAdmin() 
    {
        super();
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
		String query = "SELECT * FROM mydb.film;" ;
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
		int cont = 0, j = 0, i = 0;
		try 
		{
			while (rs.next())
			{
				if(rs.getString("nomeFilm").toLowerCase().contains(request.getParameter("mex"))||rs.getString("nomeFilm").toUpperCase().contains(request.getParameter("mex")))
				{
				     cont = 1;
				     table += "<td> "
								+ "<form method='post' action='DettagliProdottoAdmin'>"
								+ "<button class='foto_prodotto' id='btn_index' type='submit'> "
								+ "<img src='" + rs.getString("locandina")+"'+ width='200' height='300' align='center'>" 
								+ "</button>"
								+ "<input type='text' name='id' hidden value='" + rs.getInt(1) + "'><br>"
								+ "</td>"
								+ "</form>";
						if(i+1==j+4)
						{
							j+=4;
							table += "</tr><tr>";
						}
						i++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cont == 1)
		{
			out.println(table);
		}
		else
		{
			out.println("<tr><th>Nessun prodotto corrisponde alla descrizione</th></tr>");
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
