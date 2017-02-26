package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.Film;
import Utils.userBeans;

/**
 * Servlet implementation class caricaSelectFilm
 */
@WebServlet("/CaricaSelectFilm")
public class CaricaSelectFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaSelectFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//connessione al db
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
				try 
				{
					while (rs.next())
					{
					     table+="<option value='"+rs.getInt(1)+"'>"+rs.getString(2)+"</option>";
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
