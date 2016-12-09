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
public class InfoDataServlet extends HttpServlet {
	private Service service = new Service();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String country = req.getParameter("country");
		String language = req.getParameter("language");
		String nLanguage = req.getParameter("newLanguage");
		String fail = "";
		
		if (country == ""){
			fail = "Error inserte un pais";
			req.setAttribute("fail",fail);
		}else if(nLanguage == ""){
			fail = "Se inserto con exito el nuevo pais";
			req.setAttribute("fail",fail);
			service.insertNewCountry(language, country);
		}else{
			fail = "Se inserto con exito el nuevo pais";
			req.setAttribute("fail",fail);
			service.insertNewLanguage(nLanguage, country);
		}

		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
