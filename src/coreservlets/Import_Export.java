package coreservlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Servlet implementation class Import_Export
 */
@WebServlet("/Import_Export")
@MultipartConfig
public class Import_Export extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private Boolean flagVal = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Import_Export() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		flagVal = false;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String scelta=request.getParameter("scelta");
		if(scelta.equals("importa"))
		{
			//File da importare
			FileOutputStream outinit;
			ServletContext contextinit = request.getServletContext();
			String pathinit = contextinit.getRealPath("/PC-HUNT");
			
			Part filepart = request.getPart("myfile");
			
			InputStream in = filepart.getInputStream();
			File fileinit = new File(pathinit + filepart.getName()+ ".xml");
			
			outinit = new FileOutputStream(fileinit);
			for(int i = 0; i < filepart.getSize(); i++)
			{
				outinit.write(in.read());
			}
			
			ServletContext context2 = request.getServletContext();
			String path2 = context2.getRealPath("/" + fileinit.getName());
			SAXBuilder builder = new SAXBuilder();
			File file = new File(path2);
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
			
			//CONTROLLO XML
			class MyErrorHandler implements ErrorHandler 
			{
			      public void warning(SAXParseException e) throws SAXException 
			      {
			         System.out.println("Warning: "); 
			         try 
			         {
						printInfo(e);
			         }
			         catch (ServletException e1) 
			         {
						e1.printStackTrace();
			         } 
			         catch (IOException e1) 
			         {
						e1.printStackTrace();
			         }
			      }
			      public void error(SAXParseException e) throws SAXException 
			      {
			         System.out.println("Error: "); 
			         try 
			         {
						printInfo(e);
			         }
			         catch (ServletException e1) 
			         {
						e1.printStackTrace();
			         }
			         catch (IOException e1) 
			         {
						e1.printStackTrace();
					}
			      }
			      public void fatalError(SAXParseException e) throws SAXException
			      {
			         System.out.println("Fattal error: "); 
			         try 
			         {
						printInfo(e);
			         }
			         catch (ServletException e1) 
			         {
						e1.printStackTrace();
			         }
			         catch (IOException e1) 
			         {
						e1.printStackTrace();
					}
			      }
			      
			      private void printInfo(SAXParseException e) throws ServletException, IOException 
			      {
			         System.out.println("   Riga: "+e.getLineNumber());
			         System.out.println("   Colonna: "+e.getColumnNumber());
			         System.out.println("   Messaggio: "+e.getMessage());
			 		 flagVal = true;
			      }
		      }
			
		      try 
		      {
		          File x = new File(path2);
		          DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		          f.setValidating(true); // Default is false
		          DocumentBuilder b = f.newDocumentBuilder();
		          // ErrorHandler h = new DefaultHandler();
		          ErrorHandler h = new MyErrorHandler();
		          b.setErrorHandler(h);
		          org.w3c.dom.Document d = b.parse(x);
		      }
		      catch (ParserConfigurationException e) 
		      {
		          System.out.println(e.toString());      
		      }
		      catch (SAXException e) 
		      {
		          System.out.println(e.toString());      
		      }
		      catch (IOException e) 
		      {
		          System.out.println(e.toString());      
		      }

			//File originale
			 if(flagVal==false)
			 {
				ServletContext context = request.getServletContext();
				String path = context.getRealPath("/pc_catalog.xml");
				SAXBuilder builder2 = new SAXBuilder();
				File file2 = new File(path);
				Document documento2 = null;
				
				try 
				{
					 documento2 = builder2.build(file2);
				} 
				catch (JDOMException e)
				{
					e.printStackTrace();
				}
				Element radice2 = documento2.getRootElement();
				for(int i = 0 ; i <radice.getChildren().size(); i++)
				{
					int j = 0;
					int cont = 0;
					boolean flag = false;
					while(j < radice2.getChildren().size() && flag == false)
					{
						if(radice.getChildren().get(i).getChildText("ID").equals(radice2.getChildren().get(j).getChildText("ID")))
						{
							radice2.getChildren().get(j).getChild("NUMERO").setText(""+(Integer.parseInt(radice.getChildren().get(i).getChildText("NUMERO"))+Integer.parseInt(radice2.getChildren().get(j).getChildText("NUMERO"))));
							flag=true;
						}
						else
						{
							cont++;
						}
						j++;
					}
					if(cont == radice2.getChildren().size())
					{
						Element pc=new Element("PC");
						Element figlio = new Element("PIC");
						figlio.setText(radice.getChildren().get(i).getChildText("PIC"));
						pc.addContent(figlio);
						
						figlio = new Element("PIC1");
						figlio.setText(radice.getChildren().get(i).getChildText("PIC1"));
						pc.addContent(figlio);
						
						figlio = new Element("PIC2");
						figlio.setText(radice.getChildren().get(i).getChildText("PIC2"));
						pc.addContent(figlio);
						
						figlio = new Element("PIC3");
						figlio.setText(radice.getChildren().get(i).getChildText("PIC3"));
						pc.addContent(figlio);
						
						figlio = new Element("PIC4");
						figlio.setText(radice.getChildren().get(i).getChildText("PIC4"));
						pc.addContent(figlio);
						
						figlio = new Element("NAME");
						figlio.setText(radice.getChildren().get(i).getChildText("NAME"));
						pc.addContent(figlio);
						
						figlio = new Element("CPU");
						figlio.setText(radice.getChildren().get(i).getChildText("CPU"));
						pc.addContent(figlio);
						
						figlio = new Element("RAM");
						figlio.setText(radice.getChildren().get(i).getChildText("RAM"));
						pc.addContent(figlio);
						
						figlio = new Element("GPU");
						figlio.setText(radice.getChildren().get(i).getChildText("GPU"));
						pc.addContent(figlio);
						
						figlio = new Element("HDD");
						figlio.setText(radice.getChildren().get(i).getChildText("HDD"));
						pc.addContent(figlio);
						
						figlio = new Element("PREZZO");
						figlio.setText(radice.getChildren().get(i).getChildText("PREZZO"));
						pc.addContent(figlio);
						
						figlio = new Element("ID");
						figlio.setText(radice.getChildren().get(i).getChildText("ID"));
						pc.addContent(figlio);
						
						figlio = new Element("NUMERO");
						figlio.setText(radice.getChildren().get(i).getChildText("NUMERO"));
						pc.addContent(figlio);
						
						figlio = new Element("DESCRIZIONE");
						figlio.setText(radice.getChildren().get(i).getChildText("DESCRIZIONE"));
						pc.addContent(figlio);
						
						radice2.addContent(pc);
					}
				}
				
				XMLOutputter outputter = new XMLOutputter(); 
			    //Imposto il formato dell'outputter come "bel formato" 
			    outputter.setFormat(Format.getPrettyFormat()); 
			    //Produco l'output sul file pc_catalog.xml 
			    outputter.output(documento2, new FileOutputStream(file2));
			    
			    outinit.close();
				if(fileinit.delete())
				{
					System.out.println("eliminato");
				}
				request.setAttribute("msg", "DataBase aggiornato!");
			}
			 else if(flagVal == true)
			 {
				 request.setAttribute("msg", "Errore XML non valido, si assicuri che l'xml inserito rispetti il DTD");
			 }
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("ImpExp.jsp");
		dispatcher.forward(request, response);			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
