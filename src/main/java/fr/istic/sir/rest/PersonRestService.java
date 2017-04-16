package fr.istic.sir.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.Friendship;
import domain.Home;
import domain.Person;
import servlet.EntitySingleton;

@javax.inject.Singleton
@Path("Person")
public class PersonRestService extends AbstractService<Person> {

	private EntityManager em;

	public PersonRestService() {
		super(Person.class);
	}	

	@PUT
	@Path("edit/{id}")
	@Consumes({ "application/xml", "application/json" })
	public Response edit(String personJson) {
		
		List<Friendship> friends = new ArrayList<Friendship>();
		List<Home> homes = new ArrayList<Home>();
		
		
        JSONObject person = new JSONObject(personJson);
        Person entity = new Person();
        entity.setName(person.getString("name"));
        entity.setSurname(person.getString("surname"));
        entity.setMail(person.getString("email"));
        
        JSONArray jsonFriendship = person.getJSONArray("friends");
        for(int i = 0; i < jsonFriendship.length(); i++){
        	Friendship f = new Friendship(jsonFriendship.getJSONObject(i).getInt("idMe"),jsonFriendship.getJSONObject(i).getInt("idFriend"));
            friends.add(f);
        }
        entity.setFriends(friends);
        JSONArray jsonHomes = person.getJSONArray("homes");
        for(int i = 0; i < jsonHomes.length(); i++){
        	String adresse = jsonHomes.getJSONObject(i).getString("adresse");
        	System.out.println("adresse : "+ adresse);
        	QueryService qs = new QueryService(getEntityManager());
        	Home h = qs.getHomeByAdd(adresse);  
        	System.out.println(h.toString());
        	homes.add(h);
            h.addOwner(entity);
            HomeRestService hrs = new HomeRestService();
            hrs.edit(h);
        }
        
        entity.setHomes(homes);
        Person entitybase = this.find(person.getInt("id"));
        super.remove(entitybase);
        
		return super.edit(entity);
	}
	
	@PUT
	@Path("create")
	@Consumes({ "application/xml", "application/json" })
	public Response create(String personJson) {
		
		List<Friendship> friends = new ArrayList<Friendship>();
		List<Home> homes = new ArrayList<Home>();

        JSONObject person = new JSONObject(personJson);
        Person entity = new Person();
        entity.setName(person.getString("name"));
        entity.setSurname(person.getString("surname"));
        entity.setMail(person.getString("email"));
        
        JSONArray jsonFriendship = person.getJSONArray("friends");
        for(int i = 0; i < jsonFriendship.length(); i++){
        	Friendship f = new Friendship(jsonFriendship.getJSONObject(i).getInt("idMe"),jsonFriendship.getJSONObject(i).getInt("idFriend"));
            friends.add(f);
        }
        entity.setFriends(friends);
        
        JSONArray jsonHomes = person.getJSONArray("homes");
        for(int i = 0; i < jsonHomes.length(); i++){
        	String adresse = jsonHomes.getJSONObject(i).getString("adresse");
        	System.out.println("adresse : "+ adresse);
        	QueryService qs = new QueryService(getEntityManager());
        	Home h = qs.getHomeByAdd(adresse);  
        	System.out.println(h.toString());
        	homes.add(h);
            h.addOwner(entity);
            HomeRestService hrs = new HomeRestService();
            hrs.edit(h);
        }
        
        entity.setHomes(homes);
		return super.create(entity);
	}

	@DELETE
	@Path("remove/{id}")
	public Response remove(@PathParam("id") Integer id) {
		return super.remove(super.find(id));
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Person find(@PathParam("id") Integer id) {
		return (super.find(id));
	}

	@GET
	@Produces({ "application/json" })
	public List<Person> findAll() {
		return super.findAll();
	}


	@GET
	@Path("count")
	@Produces("text/plain")
	public String countREST() {
		return String.valueOf(super.count());
	}
	
	protected EntityManager getEntityManager() {
		em = EntitySingleton.getManager();
		return em;
	}
}
