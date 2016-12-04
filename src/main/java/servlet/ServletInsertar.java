package servlet;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.*;
import service.*;

/**
 * Servlet implementation class servlet
 */
public class ServletInsertar extends HttpServlet {
	private Service service = new Service();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String country = req.getParameter("country");
		String language = req.getParameter("language");
		String nLanguage = req.getParameter("newLanguage");
		
		service.crearTablas();
		
		if(nLanguage == ""){
			service.insertarPais(language, country);
		}
		else{
			service.insertarNuevoIdioma(nLanguage, country);
		}

		redirect(resp);
	}
	
	private void redirect(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("index.jsp");
	}
}
