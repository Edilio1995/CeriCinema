package coreservlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

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

import Utils.DBconnector;
import Utils.Film;
import Utils.userBeans;

/**
 * Servlet implementation class MostraDati
 */
@WebServlet("/MostraDati")
public class MostraDati extends HttpServlet 
{
	
	private ArrayList<Film> elenco;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostraDati()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		elenco = new ArrayList<>();
		HttpSession session = request.getSession();
		userBeans users = ((userBeans) session.getAttribute("user"));
		
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
		String query = "SELECT * FROM mydb.film;" ;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()){
			     Film a = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			     elenco.add(a);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DA QUI ABBIAMO L'ELENCO
		String choise = request.getParameter("mex");
		String table = "<tr><th></th><th width='100px'>NOME</th><th width='100px'>DATA USCITA</th><th>DESCRIZIONE</th></tr>";
		if(request.getParameter("mex2") != null)
		{
			for(int i=0; i < elenco.size(); i++){
				if(elenco.get(i).getGenere().equalsIgnoreCase(choise))
				{
					table += "<tr><td> <form method='post' action='DettagliProdotto'>"+ 
							"<button id='btn_index' type='submit'>" +
							"<img src='" + elenco.get(i).getLocandina() + "' width='100' height='140' align='center'>"+
							"</button>"+
							"<input type='text' name='id' hidden value='" + elenco.get(i).getId() + "'> </input>" +
							"<input type='text' name='reg' hidden value='true'><br></form>"+
							"</td><td>" +
							elenco.get(i).getNome() +
						    "</td><td>"+
						    elenco.get(i).getData() +
						    "</td><td style='text-align:justify'>" +
						    elenco.get(i).getDescrizione() +
							"</td></tr>";
				}
			}
		}
		else
		{
			for(int i=0; i < elenco.size(); i++){
				if(elenco.get(i).getGenere().equalsIgnoreCase(choise))
				{
						
					table += "<tr><td> <form method='post' action='DettagliProdotto'>"+ 
							"<button id='btn_index' type='submit'>" +
							"<img src='" + elenco.get(i).getLocandina() + "' width='100' height='140' align='center'>"+
							"</button>"+ "<input type='text' name='id' hidden value='" + elenco.get(i).getId() + "'> </input></form>" +
							"</td><td>" +
							elenco.get(i).getNome() +
						    "</td><td>"+
						    elenco.get(i).getData() +
						    "</td><td style='text-align:justify'>" +
						    elenco.get(i).getDescrizione() +
							"</td></tr>";
				}
			}
		}
		
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
