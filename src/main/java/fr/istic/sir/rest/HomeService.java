package fr.istic.sir.rest;

import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import domain.Home;
import domain.Person;
import servlet.EntitySingleton;

@Path("/home")
public class HomeService {

	@POST
	@Path("/add")
	public Response addHome(@FormParam("address") String address, @FormParam("town") String town,
			@FormParam("rooms") int rooms, @FormParam("surface") int surface) {
		EntitySingleton.getInstance();
		EntityTransaction tx = EntitySingleton.getManager().getTransaction();
		Home addedHome = new Home(address, town, rooms, surface);
		tx.begin();
		EntitySingleton.getManager().persist(addedHome);
		tx.commit();
		EntitySingleton.getManager().close();
		return Response.status(200).entity("addHome is called, the data here has been added to the database address : "
				+ address + ", ville : " + town + ", pieces : " + rooms + ", surface : " + surface).build();
	}

	 @GET
	 @Path("/list")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Home> getHomes() {
	        
		 EntitySingleton.getInstance();
		 EntityTransaction tx = EntitySingleton.getManager().getTransaction();
	
		tx.begin();
			
		List<Home> resultList = EntitySingleton.getManager().createQuery("Select a From Home a", Home.class).getResultList();
		System.out.println(resultList.size());
		tx.commit();
					
		EntitySingleton.getManager().close();
		return resultList;
			
	 }

}
