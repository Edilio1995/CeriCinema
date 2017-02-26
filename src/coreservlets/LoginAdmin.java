package coreservlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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

import Utils.userBeans;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginAdmin")
public class LoginAdmin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdmin() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
				int cont = 0;
				if("rei".equals(request.getParameter("username")))
				{
					cont = 1;
					if("rei".equals(request.getParameter("password")))
					{
						cont = 2;
					}
				
				}
				else
				{
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE html>" +
								"<html>" +
									"<head>" +
									  "<meta charset='UTF-8'>" +
									  "<style>" +
									   " @import url(styleadmin.css);" +
									  "</style>" +
									  "<title>PC-HUNT/Admin</title>" +
									"</head>" +
									"<body>" +
										"<div id = 'login'>" +
											"<form method='post' name='login' action='LoginAdmin'>" +
												"<h2> Benvenuto!</h2> <br>" +
												"Username: &nbsp <input id='errore' name='username' type='text' placeholder='username'> <br> <br>" +
												"Password: &nbsp <input name='password' type='password' placeholder='password'> <br><br>" +
												"<input type='submit' value='Invia'>" +
											"</form>" +
										"</div>" +
									"</body>" +
								"</html>");
				}
				if(cont == 2)
				{
					userBeans userAdmin = new userBeans();
					HttpSession session = request.getSession();
					request.setAttribute("admin", userAdmin);
					session.setAttribute("admin", userAdmin);
					response.sendRedirect("visualizzaProdottiAdmin.jsp");
				}
				else if(cont == 1)
				{
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE html>" +
								"<html>" +
									"<head>" +
									  "<meta charset='UTF-8'>" +
									  "<style>" +
									   " @import url(styleadmin.css);" +
									  "</style>" +
									  "<title>PC-HUNT/Admin</title>" +
									"</head>" +
									"<body>" +
										"<div id = 'login'>" +
											"<form method='post' name='login' action='LoginAdmin'>" +
												"<h2>Benvenuto!</h2> <br>" +
												"Username: &nbsp <input name='username' type='text' placeholder='username'> <br> <br>" +
												"Password: &nbsp <input id='errore' name='password' type='password' placeholder='password'> <br><br>" +
												"<input type='submit' value='Invia'>" +
											"</form>" +
										"</div>" +
									"</body>" +
								"</html>");
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
