package coreservlets;

import java.io.File;
import java.io.FileOutputStream;
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
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.ProdottoDaVisualizzare;

/**
 * Servlet implementation class EliminaAdmin
 */
@WebServlet("/EliminaAdmin")
public class EliminaAdmin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaAdmin() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String id = request.getParameter("nome");
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
		String query = "DELETE FROM `mydb`.`film` WHERE `nomeFilm`='"+id+"';" ;
		int rs = 0;
		try 
		{
			rs = stmt.executeUpdate(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		response.sendRedirect("visualizzaProdottiAdmin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
