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
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.Film;
import Utils.userBeans;

/**
 * Servlet implementation class CaricaFilmIndex
 */
@WebServlet("/CaricaFilmIndex")
public class CaricaFilmIndex extends HttpServlet 
{
	private ArrayList<Film> elenco;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaFilmIndex() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		elenco = new ArrayList<>();
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
		try 
		{
			while (rs.next())
			{
			     Film a = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			     elenco.add(a);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DA QUI ABBIAMO L'ELENCO
		String choise = request.getParameter("mex");
		String table = "<tr>";
		int j = 0;
		if(choise.equals("admin"))
		{
			System.out.println("sisi");
			for(int i=0; i < elenco.size(); i++)
			{
				table += "<td> "
						+ "<form method='post' action='DettagliProdottoAdmin'>"
						+ "<button class='foto_prodotto' id='btn_index' type='submit'> "
						+ "<img src='" + elenco.get(i).getLocandina()+ "' width='200' height='300' align='center'>" 
						+ "</button>"
						+ "<input type='text' name='id' hidden value='" + elenco.get(i).getId() + "'><br>"
						+ "</td>"
						+ "</form>";
				if(i+1==j+4)
				{
					j+=4;
					table += "</tr><tr>";
				}
			}
		}
		else
		{
			if(request.getParameter("mex").equals("reg"))
			{
				for(int i=0; i < elenco.size(); i++)
				{
					table += "<td> "
							+ "<form method='post' action='DettagliProdotto'>"
							+ "<button class='foto_prodotto' id='btn_index' type='submit'> "
							+ "<img src='" + elenco.get(i).getLocandina()+ "' width='200' height='300' align='center'>" 
							+ "</button>"
							+ "<input type='text' name='id' hidden value='" + elenco.get(i).getId() + "'><br>"
							+ "<input type='text' name='reg' hidden value='true'><br>"
							+ "</td>"
							+ "</form>";
					if(i+1==j+5)
					{
						j+=5;
						table += "</tr><tr>";
					}
				}
			}
			else
			{
				for(int i=0; i < elenco.size(); i++)
				{
					table += "<td> "
							+ "<form method='post' action='DettagliProdotto'>"
							+ "<button class='foto_prodotto' id='btn_index' type='submit'> "
							+ "<img src='" + elenco.get(i).getLocandina()+ "' width='200' height='300' align='center'>" 
							+ "</button>"
							+ "<input type='text' name='id' hidden value='" + elenco.get(i).getId() + "'><br>"
							+ "</td>"
							+ "</form>";
					if(i+1==j+5)
					{
						j+=5;
						table += "</tr><tr>";
					}
				}
			}
			
		}
		
		
		table += "</tr>";
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
