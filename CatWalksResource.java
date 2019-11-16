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
@Path("catwalks")
public class CatWalksResource {
CatWalksRepository repo = new CatWalksRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<CatWalks> getCatWalks() throws SQLException
	{
		System.out.println("Get alien called ...");
		
		return repo.getCatWalks();
	}
	
	@GET
	@Path("catwalk/{id}")
	//	@Produces(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public CatWalks getCatWalk(@PathParam("id") int id) throws SQLException
	{
		return repo.getCatWalk(id);
	}
	
	@POST
	@Path("catwalk")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public CatWalks createCatWalk(CatWalks a1) throws SQLException
	{
		repo.create(a1);
		return a1;
	}
	
	@PUT
	@Path("catwalk")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public CatWalks updateCatWalk(CatWalks a1) throws SQLException
	{
		if(repo.getCatWalk(a1.getId()).getId() == 0) {
			repo.create(a1);
		}
		else {
			repo.update(a1);
		}
		return a1;
	}
	
	@DELETE
	@Path("catwalk/{id}")
	public CatWalks killCatWalk(@PathParam("id") int id) 
	{
		CatWalks a = repo.getCatWalk(id);
		
		if(a.getId()!=0) {
			repo.delete(id);
		}
		
		
		return a;
	}
}

