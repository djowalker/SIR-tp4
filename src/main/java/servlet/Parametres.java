package servlet;

import java.io.*;

import javax.servlet.*;

import javax.servlet.http.*;

import java.util.*; 
public class Parametres extends HttpServlet {
  public void doGet(HttpServletRequest request,
					HttpServletResponse response)
	  throws ServletException, IOException {
	response.setContentType("text/html");

	PrintWriter out = response.getWriter();

	out.println("<html><body>\n" +
				"<h1>Tableau des paramètres</h1>\n" +
				"<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\n" +
				"<tr>\n" +
				"<th>Nom</th><th>Valeur(s)</th>");

	Enumeration NomsParam = request.getParameterNames();

	while(NomsParam.hasMoreElements()) {
	  String NomParam = (String)NomsParam.nextElement();

	  out.println("<tr><td>" + NomParam + "</td></tr>\n");

	  String[] ValeursParam = request.getParameterValues(NomParam);

	  if (ValeursParam.length() == 1) {		
	String ValeurParam = ValeursParam[0];

		if (ValeurParam.length() == 0)		  
		out.println("<td><b>Aucune valeur</i></td>");

		else		  out.println(ValeurParam);	  
	  } 
	  else {
		out.println("<td><ul>");		
	for(int i=0; i < ValeursParam.length(); i++) {
		  out.println("<li>" + ValeursParam[i] + "</li>");		
	}
		out.println("</ul></td></tr>");	  
	  }	
	}
	out.println("</table>\n</body></html>");  
  }
  public void doPost(HttpServletRequest request,
					 HttpServletResponse response)
	  throws ServletException, IOException {	
	doGet(request, response);  
  }
}