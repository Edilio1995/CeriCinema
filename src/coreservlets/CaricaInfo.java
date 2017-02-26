package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.Film;

/**
 * Servlet implementation class CaricaInfo
 */
@WebServlet("/CaricaInfo")
public class CaricaInfo extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaInfo()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		String telefono = null, email = null, via = null;
		
		PrintWriter out = response.getWriter();
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "lamianascita";
		System.out.println("Connecting database...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Connection connection;
		try {
			connection = (Connection) DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		
		Statement stmt = null;
		try {
			stmt = (Statement) connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "SELECT * FROM mydb.infocinema;" ;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()){
			     via = rs.getString(1);
			     telefono = rs.getString(2);
			     email = rs.getString(3);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DA QUI ABBIAMO L'ELENCO
		String table = "";
			table += "<tr><td><h4> Sede </h4></td> <td>"+ 
					via +
					"</td></tr><tr><td><h4> Telefono </h4></td><td>" +
					telefono +
					"</td></tr><tr><td><h4> Fax </h4></td><td>" +
					telefono +
				    "</td></tr><tr><td><h4>Email</h4></td><td>"+
				    email +
					"</td></tr>";
			
		out.println(table);
		}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}