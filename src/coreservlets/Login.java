package coreservlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
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
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Utils.Carrello;
import Utils.Film;
import Utils.userBeans;

/**
 * Servlet implementation class Login
 */

@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private ArrayList<userBeans> elenco;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nome = null;
		String cognome = null;
		String dataDiNascita= null;
		String residenza= null;
		String cap= null;
		String codiceFiscale= null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String iban= null;
		String numeroCarta= null;
		String cvv= null;
		String scadenzaCarta= null;
		ArrayList<userBeans> elenco = new ArrayList<>();
		
		
		if(username.trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?Username non inserito");
		}
		else if(password.trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?Password non inserita");
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
		try 
		{
			while (rs.next())
			{
			     userBeans a = new userBeans();
			     a.setNome(rs.getString("nomeUtente"));
			     a.setCognome(rs.getString("cognomeUtente"));
			     a.setDataDiNascita(rs.getString("dataNascita"));
			     a.setResidenza(rs.getString("residenza"));
			     a.setCap(rs.getString("cap"));
			     a.setCodiceFiscale(rs.getString("codiceFiscale"));
			     a.setUsername(rs.getString("username"));
			     a.setPassword(rs.getString("password"));
			     a.setIban(rs.getString("iban"));
			     a.setNumeroCarta(rs.getString("numeroCarta"));
			     a.setCvv(rs.getString("cvv"));
			     a.setScadenzaCarta(rs.getString("scadenzaCarta"));
			     elenco.add(a);
			 }
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		
		int i = 0, cont = 0;
		while(i < elenco.size() && (cont == 0))
		{
			if((username.equals(elenco.get(i).getUsername())))
			{
				cont = 1;
				if((password.equals(elenco.get(i).getPassword())))
				{
					cont = 2;
					nome = elenco.get(i).getNome();
					cognome = elenco.get(i).getCognome();
					dataDiNascita = elenco.get(i).getDataDiNascita();
					residenza = elenco.get(i).getResidenza();
					cap = elenco.get(i).getCap();
					codiceFiscale = elenco.get(i).getCodiceFiscale();
					iban = elenco.get(i).getIban();
					numeroCarta = elenco.get(i).getNumeroCarta();
					cvv = elenco.get(i).getCvv();
					scadenzaCarta = elenco.get(i).getScadenzaCarta();
				}
			}
			i++;
		}		
		if(cont == 2)
		{
			userBeans userB = new userBeans();
			userB.setNome(nome);
			userB.setCognome(cognome);
			userB.setDataDiNascita(dataDiNascita);
			userB.setResidenza(residenza);
			userB.setCap(cap);
			userB.setCodiceFiscale(codiceFiscale);
			userB.setUsername(username);
			userB.setPassword(password);
			userB.setIban(iban);
			userB.setNumeroCarta(numeroCarta);
			userB.setCvv(cvv);
			userB.setScadenzaCarta(scadenzaCarta);
			File file = new File("reg_utente.txt");	
			FileOutputStream outFile = new FileOutputStream(file);
			ObjectOutputStream outStream = new ObjectOutputStream(outFile);
			outStream.writeObject(userB.getUsername());
			outStream.close();
			outFile.close();
			response.sendRedirect("reg_index.jsp");
		}
		else if (cont == 1)
		{
			response.sendRedirect("errore.jsp?Username o password errata");
		}
		else
		{
			response.sendRedirect("errore.jsp?Account non registrato");
		}
		cont = 0;
	}
	
		/*** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			doGet(request, response);
		}

}