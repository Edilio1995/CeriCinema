package coreservlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet implementation class VisualizzaAcquistiAdmin
 */
@WebServlet("/VisualizzaAcquistiAdmin")
public class VisualizzaAcquistiAdmin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaAcquistiAdmin() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/storico_acquisti.xml");
		
		//accesso al db.xml		
		SAXBuilder builder = new SAXBuilder();
		File file = new File(path);
		Document documento = null;
		try 
		{
			 documento = builder.build(file);
		} 
		catch (JDOMException e)
		{
			e.printStackTrace();
		}
		Element radice = documento.getRootElement();
		
		PrintWriter out = response.getWriter();
		String username = request.getParameter("mex");
		int cont = 0;
		String table = "<tr><th></th><th>NOME</th><th>PREZZO D'ACQUISTO</th><th>DATA</th><th>QUANTITA</th></tr>";
		int i = 0;
		while(i < radice.getChildren().size())
		{
			if(username.equals(radice.getChildren().get(i).getName()))
			{
				for(int j = 0; j < radice.getChildren().get(i).getChildren().size(); j++)
				{
					cont = 1;
					table += "<tr><td> <form method='post' action='DettagliProdottoAdmin'>"+ 
						"<button id='btn_index' type='submit'>" +
						"<img src='" + radice.getChildren().get(i).getChildren().get(j).getChildText("FOTO") + "' width='50' height='50' align='center'>"+
						"</button>" +
						"<input type='text' name='id' hidden value='" + radice.getChildren().get(i).getChildren().get(j).getChildText("ID") + "'> </input></form>" +
						"</td><td>" +
						radice.getChildren().get(i).getChildren().get(j).getChildText("NOME") +
					    "</td><td>"+
					    radice.getChildren().get(i).getChildren().get(j).getChildText("PREZZO") +" &#8364"+
					    "</td><td>" +
					    radice.getChildren().get(i).getChildren().get(j).getChildText("DATA") +
						"</td><td>" +
						radice.getChildren().get(i).getChildren().get(j).getChildText("QUANTITA") +
					    "</td></tr>";
				}
			}
				i++;
		}
		if(cont == 1)
		{
			out.println(table);
		}
		else
		{
			out.println("<tr><td>Ancora nessun acquisto effettuato</td></tr>");
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
