package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import domain.Person;
import fr.istic.sir.rest.PersonRestService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "personadd", urlPatterns = { "/PersonAdd" })
public class PersonAdd extends HttpServlet {
	
	private EntityManager em;
	private PersonRestService qs ;

	public void init() throws ServletException {
		// EntityManager manager = new EntityManager();
		em = EntitySingleton.getManager();
		qs = new PersonRestService();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<HTML>\n<BODY>\n" + "<H1>Recapitulatif des informations ajoutées</H1>\n" + "<UL>\n"
				+ " <LI>idPerson: " + request.getParameter("prenomPerson") + "\n" + " <LI>nomPerson: "
				+ request.getParameter("nomPerson") + "\n" + " <LI>mail: " + request.getParameter("mail") + "\n"
				+ " <LI>ami: " + request.getParameter("ami") + "\n" + "</UL>\n" +
				"</BODY></HTML>");

		EntityTransaction tx = EntitySingleton.getManager().getTransaction();
		tx.begin();
		try {
			Person added = new Person(request.getParameter("nomPerson"),request.getParameter("prenomPerson"),
					request.getParameter("mail"));
			qs.create(added);

		} catch (Exception e) {
			e.printStackTrace();
		}

		tx.commit();

	}
}