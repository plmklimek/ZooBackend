package com.klimek.first;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("animals")
public class AnimalResource {
AnimalRepository repo = new AnimalRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Animal> getAnimals() throws SQLException
	{
		System.out.println("Get alien called ...");
		
		return repo.getAnimals();
	}
	
	@GET
	@Path("animal/{id}")
	//	@Produces(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Animal getAnimal(@PathParam("id") int id) throws SQLException
	{
		return repo.getAnimal(id);
	}
	
	@POST
	@Path("animal")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Animal createAnimal(Animal a1) throws SQLException
	{
		repo.create(a1);
		return a1;
	}
	
	@PUT
	@Path("animal")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Animal updateAnimal(Animal a1) throws SQLException
	{
		if(repo.getAnimal(a1.getId()).getId() == 0) {
			repo.create(a1);
		}
		else {
			repo.update(a1);
		}
		return a1;
	}
	
	@DELETE
	@Path("animal/{id}")
	public Animal killAnimal(@PathParam("id") int id) 
	{
		Animal a = repo.getAnimal(id);
		
		if(a.getId()!=0) {
			repo.delete(id);
		}
		
		
		return a;
	}
}
