package coreservlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

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
 * Servlet implementation class Filtra
 */
@WebServlet("/Filtra")
public class Filtra extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filtra() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/pc_catalog.xml");
		
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
		int cont = 0;
		String table = "<tr><th></th><th>NOME</th><th>CPU</th><th>RAM</th><th>GPU</th><th>HDD</th><th>PREZZO</th><th>COMPRA</th></tr>";
		for(int i = 0; i < radice.getChildren().size(); i++)
		{
			if(Integer.parseInt(radice.getChildren().get(i).getChildText("PREZZO")) <= Integer.parseInt(request.getParameter("mex")))
			{
				cont = 1;
				table += "<tr><td> <form method='post' action='DettagliProdotto'>"+ 
					"<button id='btn_index' type='submit'>" +
					"<img src='" + radice.getChildren().get(i).getChildText("PIC") + "' width='50' height='50' align='center'>"+
					"</button>" +
					"<input type='text' name='id' hidden value='" + radice.getChildren().get(i).getChildText("ID") + "'> </input></form>" +
					"</td><td>" +
					radice.getChildren().get(i).getChildText("NAME") +
				    "</td><td>"+
				    radice.getChildren().get(i).getChildText("CPU") +
				    "</td><td>"+
				    radice.getChildren().get(i).getChildText("RAM") +
				    "</td><td>"+
				    radice.getChildren().get(i).getChildText("GPU") +
				    "</td><td>"+
				    radice.getChildren().get(i).getChildText("HDD") +
				    "</td><td>"+
				    radice.getChildren().get(i).getChildText("PREZZO") +" &#8364"+
				    "</td><td><form method='post' action='AddCarrello'><input id ='numprod' name='numprod' type='number' min ='1' max ='" + radice.getChildren().get(i).getChildText("NUMERO") + "' value='1' style='width: 30px;'> &nbsp&nbsp <button type='submit' action='aggiuntoProdotto()'><img style='float: left;' src='img/carrello.png' width='30' height='30'></button><input type='text' name='Indice' hidden value="+radice.getChildren().get(i).getChildText("ID")+"></form></td></tr>";
			}
		}
		if(cont ==1)
		{
			out.println(table);
		}
		else
		{
			out.println("<tr><th>Nessun prodotto corrisponde alla descrizione</th></tr>");
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
