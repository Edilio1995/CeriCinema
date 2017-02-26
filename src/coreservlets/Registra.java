package coreservlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

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
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Utils.userBeans;

/**
 * Servlet implementation class Registra
 */
@WebServlet("/Registra")
public class Registra extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registra()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		//controllo dati
		if(request.getParameter("nome").trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?Nome non inserito");
		}
		else if(request.getParameter("cognome").trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?Cognome non inserito");
		}
		else if(request.getParameter("dataDiNascita").trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?Data di nascita non inserita");
		}
		else if((request.getParameter("dataDiNascita").charAt(2) != '/')||(request.getParameter("dataDiNascita").charAt(5) != '/'))
		{
			response.sendRedirect("errore.jsp?Data di nascita non corretta(gg/mm/aaaa)");
		}
		else if(request.getParameter("email").trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?Email non inserita");
		}
		else if(!(request.getParameter("email").matches(emailPattern)))
		{
			response.sendRedirect("errore.jsp?Formato email non valido");
		}
		else if(request.getParameter("residenza").trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?Residenza non inserita");
		}
		else if(request.getParameter("codiceFiscale").trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?CF non inserito");
		}
		else if(request.getParameter("codiceFiscale").length() < 16)
		{
			response.sendRedirect("errore.jsp?Formato CF non corretto");
		}
		else if(request.getParameter("username").trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?Username non inserito");
		}
		else if(request.getParameter("password").trim().length() == 0)
		{
			response.sendRedirect("errore.jsp?Password non inserita");
		}
		else if(request.getParameter("password").trim().length() < 5)
		{
			response.sendRedirect("errore.jsp?Password troppo corta (min 5 caratteri)");
		}
		else if(!(request.getParameter("password").equals(request.getParameter("password2"))))
		{
			response.sendRedirect("errore.jsp?Le password non corrispondono");
		}
		else
		{
			//controllo dati
			userBeans nuovo = new userBeans();
			nuovo.setUsername(request.getParameter("username"));
			nuovo.setNome(request.getParameter("nome"));
			nuovo.setCognome(request.getParameter("cognome"));
			nuovo.setPassword(request.getParameter("password"));
			nuovo.setResidenza(request.getParameter("residenza"));
			nuovo.setCodiceFiscale(request.getParameter("codiceFiscale"));
			nuovo.setDataDiNascita(request.getParameter("dataDiNascita"));
			nuovo.setCap(request.getParameter("cap"));
			nuovo.setEmail(request.getParameter("email"));
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
			String sql="INSERT INTO `mydb`.`utenti` (`username`, `password`, `nomeUtente`, `cognomeUtente`, `residenza`, `cap`, `codiceFiscale`, `dataNascita`, `email`) VALUES ('"+nuovo.getUsername()+"','"+nuovo.getPassword()+"','"+nuovo.getNome()+"', '"+ nuovo.getCognome()+"','"+nuovo.getResidenza()+"','"+nuovo.getCap()+"','"+nuovo.getCodiceFiscale()+"','"+nuovo.getDataDiNascita()+"','"+nuovo.getEmail()+"');";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				preparedStatement.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			} 
			HttpSession session = request.getSession();
			session.setAttribute("user", nuovo);
			request.setAttribute("user", nuovo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("riepilogo.jsp");
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
