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
@Path("pavilons")
public class PavilonResource {
PavilonRepository repo = new PavilonRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Pavilon> getPavilons() throws SQLException
	{
		System.out.println("Get alien called ...");
		
		return repo.getPavilons();
	}
	
	@GET
	@Path("pavilon/{id}")
	//	@Produces(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Pavilon getPavilon(@PathParam("id") int id) throws SQLException
	{
		return repo.getPavilon(id);
	}
	
	@POST
	@Path("pavilon")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Pavilon createPavilon(Pavilon a1) throws SQLException
	{
		repo.create(a1);
		return a1;
	}
	
	@PUT
	@Path("pavilon")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Pavilon updatePavilon(Pavilon a1) throws SQLException
	{
		if(repo.getPavilon(a1.getId()).getId() == 0) {
			repo.create(a1);
		}
		else {
			repo.update(a1);
		}
		return a1;
	}
	
	@DELETE
	@Path("pavilon/{id}")
	public Pavilon killPavilon(@PathParam("id") int id) 
	{
		Pavilon a = repo.getPavilon(id);
		
		if(a.getId()!=0) {
			repo.delete(id);
		}
		
		
		return a;
	}
}