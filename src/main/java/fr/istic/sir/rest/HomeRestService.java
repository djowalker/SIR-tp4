package fr.istic.sir.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.ElectronicDevice;
import domain.Friendship;
import domain.Heater;
import domain.Home;
import domain.IntelligentDevice;
import domain.Person;
import servlet.EntitySingleton;

@javax.inject.Singleton
@Path("Homes")
public class HomeRestService extends AbstractService<Home> {

	private EntityManager em;

	public HomeRestService() {
		super(Home.class);
	}	

	@PUT
	@Path("edit/{id}")
	@Consumes({ "application/xml", "application/json" })
	public Response edit(@PathParam("id") Integer id,String homeJson) {
		List<IntelligentDevice>devices = new ArrayList<IntelligentDevice>();
		
		
        JSONObject home = new JSONObject(homeJson);
        Home entity = new Home();
        entity.setAdresse(home.getString("adresse"));
        entity.setVille(home.getString("ville"));
        entity.setRooms(home.getInt("rooms"));
        entity.setSurface(home.getInt("surface"));
        
        JSONArray jsonDevices = home.getJSONArray("devices");
        for(int i = 0; i < jsonDevices.length(); i++){
           	IntelligentDevice dev = null;
        	String ref = jsonDevices.getJSONObject(i).getString("reference");
        	String type = jsonDevices.getJSONObject(i).getString("type");
        	int cons = jsonDevices.getJSONObject(i).getInt("consommationAvg");
        	if (type.equals("heater")){
        		String source = jsonDevices.getJSONObject(i).getString("source");
        		dev = new Heater(ref,entity,cons,source);
        		devices.add(dev);
        	}
        	else if (type.equals("elec")){
        		String name = jsonDevices.getJSONObject(i).getString("name");
        		dev = new ElectronicDevice(ref,entity,cons,name);
        		devices.add(dev);
        	}
        	else {
        	}
        }
        entity.setDevices(devices);
        Home entitybase = this.find(home.getInt("id"));
        super.remove(entitybase);
		return super.edit(entity);
	}
	
	@PUT
	@Path("create")
	@Consumes({ "application/xml", "application/json" })
	public Response create(String homeJson) {
		List<IntelligentDevice>devices = new ArrayList<IntelligentDevice>();
		
		
        JSONObject home = new JSONObject(homeJson);
        Home entity = new Home();
        entity.setAdresse(home.getString("adresse"));
        entity.setVille(home.getString("ville"));
        entity.setRooms(home.getInt("rooms"));
        entity.setSurface(home.getInt("surface"));
        
        JSONArray jsonDevices = home.getJSONArray("devices");
        for(int i = 0; i < jsonDevices.length(); i++){
        	IntelligentDevice dev = null;
        	String ref = jsonDevices.getJSONObject(i).getString("reference");
        	String type = jsonDevices.getJSONObject(i).getString("type");
        	int cons = jsonDevices.getJSONObject(i).getInt("consommationAvg");
        	if (type.equals("heater")){
        		String source = jsonDevices.getJSONObject(i).getString("source");
        		dev = new Heater(ref,entity,cons,source);
        		devices.add(dev);
        	}
        	else if (type.equals("elec")){
        		String name = jsonDevices.getJSONObject(i).getString("name");
        		dev = new ElectronicDevice(ref,entity,cons,name);
        		devices.add(dev);
        	}
        	else {
        	}
        }
        entity.setDevices(devices);
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
	public Home find(@PathParam("id") Integer id) {
		return (super.find(id));
	}

	@GET
	@Produces({ "application/json" })
	public List<Home> findAll() {
		return super.findAll();
	}


	@GET
	@Path("count")
	@Produces("text/plain")
	public String countREST() {
		return String.valueOf(super.count());
	}
	
	public Response edit(Home h){
		return super.edit(h);
	}
	
	protected EntityManager getEntityManager() {
		em = EntitySingleton.getManager();
		return em;
	}
}

