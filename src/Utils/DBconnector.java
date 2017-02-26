package Utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdom2.output.support.SAXTarget;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBconnector 
{
	public DBconnector()
	{
		
	}
	
	public void connettiDb()
	{
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
	}
	
	public ResultSet getUtenti(Statement stmt)
	{
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
		return rs;
	}
	
	public ResultSet getFilm(Statement stmt)
	{
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
		return rs;
	}
	
	public ResultSet getProiezioni(Statement stmt)
	{
		String query = "SELECT * FROM mydb.proiezioni;" ;
		ResultSet rs = null;
		try 
		{
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getPrenotazioni(Statement stmt)
	{
		String query = "SELECT * FROM mydb.prenotazioni;" ;
		ResultSet rs = null;
		try 
		{
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getInfoCinema(Statement stmt)
	{
		String query = "SELECT * FROM mydb.infocinema;" ;
		ResultSet rs = null;
		try 
		{
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
}


