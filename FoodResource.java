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

@Path("foods")
public class FoodResource {
FoodRepository repo = new FoodRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Food> getFoods() throws SQLException
	{
		System.out.println("Get alien called ...");
		
		return repo.getFoods();
	}
	
	@GET
	@Path("food/{id}")
	//	@Produces(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Food getFood(@PathParam("id") int id) throws SQLException
	{
		return repo.getFood(id);
	}
	
	@POST
	@Path("food")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Food createFood(Food a1) throws SQLException
	{
		repo.create(a1);
		return a1;
	}
	
	@PUT
	@Path("food")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Food updateFood(Food a1) throws SQLException
	{
		if(repo.getFood(a1.getId()).getId() == 0) {
			repo.create(a1);
		}
		else {
			repo.update(a1);
		}
		return a1;
	}
	
	@DELETE
	@Path("food/{id}")
	public Food killFood(@PathParam("id") int id) 
	{
		Food a = repo.getFood(id);
		
		if(a.getId()!=0) {
			repo.delete(id);
		}
		
		
		return a;
	}
}
