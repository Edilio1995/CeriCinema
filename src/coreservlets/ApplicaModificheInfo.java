package coreservlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

import Utils.userBeans;

/**
 * Servlet implementation class ApplicaModificheInfo
 */
@WebServlet("/ApplicaModificheInfo")
public class ApplicaModificheInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicaModificheInfo() {
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
		userBeans utente = new userBeans();
		try 
		{
			while (rs.next())
			{
				if(rs.getString("username").equals(nomeUtente))
				{
					utente.setNome(rs.getString("nomeUtente"));
					utente.setCognome(rs.getString("cognomeUtente"));
					utente.setUsername(rs.getString("username"));
					utente.setCap(rs.getString("cap"));
					utente.setCodiceFiscale("codiceFiscale");
					utente.setCap(rs.getString("cap"));
					utente.setResidenza(rs.getString("residenza"));
					utente.setDataDiNascita(rs.getString("dataNascita"));
					utente.setEmail(rs.getString("email"));
				}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cont = 0;
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if(!(request.getParameter("nome").trim().length()==0))
		{
			utente.setNome(request.getParameter("nome"));
		}
		if(!(request.getParameter("cognome").trim().length()==0))
		{
			utente.setCognome(request.getParameter("cognome"));
		}
		if(!(request.getParameter("email").trim().length()==0) && ((request.getParameter("email").matches(emailPattern))))
		{
			utente.setEmail(request.getParameter("email"));
		}
		if(!(request.getParameter("username").trim().length()==0))
		{
			utente.setUsername(request.getParameter("username"));
			cont = 1;
		}
		if(!(request.getParameter("data").trim().length()==0))
		{
			utente.setDataDiNascita(request.getParameter("data"));
		}
		if(!(request.getParameter("residenza").trim().length()==0))
		{
			utente.setResidenza(request.getParameter("residenza"));
		}
		if(!(request.getParameter("cap").trim().length()==0))
		{
			utente.setCap(request.getParameter("cap"));
		}
		if(!(request.getParameter("cf").trim().length()==0))
		{
			utente.setCodiceFiscale(request.getParameter("cf"));
		}
		
		System.out.println(request.getParameter("residenza"));
		System.out.println(utente.getResidenza());
		query = "UPDATE `mydb`.`utenti` SET `username`='"+utente.getUsername()+"', `nomeUtente`='"+utente.getNome()+"', `email`='"+utente.getEmail()+"', `cognomeUtente`='"+utente.getCognome()+"', `residenza`='"+utente.getResidenza()+"', `cap`='"+utente.getCap()+"', `codiceFiscale`='"+utente.getCodiceFiscale()+"', `dataNascita`='"+utente.getDataDiNascita()+"' WHERE `username`='"+nomeUtente+"';";
		int rs2 = 0;
		try 
		{
			rs2 = stmt.executeUpdate(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(cont == 1)
		{
			FileOutputStream outFile = new FileOutputStream(file);
			ObjectOutputStream outStream = new ObjectOutputStream(outFile);
			outStream.writeObject(utente.getUsername());
			outStream.close();
			outFile.close();
		}
		response.sendRedirect("reg_ilmioaccount.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
