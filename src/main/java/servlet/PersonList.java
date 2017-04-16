package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import domain.ElectronicDevice;
import domain.Home;
import domain.Person;
import fr.istic.sir.rest.PersonRestService;
import jpa.JpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "personlist", urlPatterns = { "/PersonList" })
public class PersonList extends HttpServlet {

//	private EntityManagerFactory factory;
//	private EntityManager manager;
	private EntityManager em;
	private PersonRestService qs ;

	@Override
	public void init() throws ServletException {
//		System.out.println("init list");
//		factory = Persistence.createEntityManagerFactory("example");
//		manager = factory.createEntityManager();
		em = EntitySingleton.getManager();
		qs = new PersonRestService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		EntityTransaction tx = EntitySingleton.getManager().getTransaction();

		tx.begin();
		try {
			PrintWriter out = response.getWriter();
			int numOfPerson = qs.count();
			if(numOfPerson != 0){
			List<Person> resultList = qs.findAll();
			
			System.out.println("num of person : " + numOfPerson);
			out.println("<HTML>\n<BODY>\n" + 
					"<H1>Recapitulatif des personnes dans la base</H1>\n");
			for (Person pers : resultList) {		
				System.out.println("next Person : " + pers.getName() + " " + pers.getSurname());
				out.println("<UL>\n" + 
							" <LI>Nom: " + pers.getName() + "\n" 
							+ " <LI>Prenom: " + pers.getSurname() + "\n" 
							+ " <LI>Mail: " + pers.getMail() + "\n" 
						+ "</UL>\n");
			}
			out.println("</BODY></HTML>");
			}
			else{
				out.println("<HTML>\n<BODY>\n" + 
						"<H1>Il n'y a pas de personnes dans la BDD !</H1>\n"
						+"</BODY></HTML>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
	}

	@Override
	public void destroy() {
		System.out.println("destroy list");
		super.destroy();
		EntitySingleton.getManager().close();
	}
}
