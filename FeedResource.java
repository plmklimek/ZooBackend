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
@Path("feed")
public class FeedResource {
FeedRepository repo = new FeedRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Feed> getFeeds() throws SQLException
	{
		System.out.println("Get alien called ...");
		
		return repo.getFeeds();
	}
	
	@GET
	@Path("feed/{id}")
	//	@Produces(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Feed getFeed(@PathParam("id") int id) throws SQLException
	{
		return repo.getFeed(id);
	}
	
	@POST
	@Path("feed")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Feed createFeed(Feed a1) throws SQLException
	{
		repo.create(a1);
		return a1;
	}
	
	@PUT
	@Path("feed")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Feed updateFeed(Feed a1) throws SQLException
	{
		if(repo.getFeed(a1.getId()).getId() == 0) {
			repo.create(a1);
		}
		else {
			repo.update(a1);
		}
		return a1;
	}
	
	@DELETE
	@Path("feed/{id}")
	public Feed killFeed(@PathParam("id") int id) 
	{
		Feed a = repo.getFeed(id);
		
		if(a.getId()!=0) {
			repo.delete(id);
		}
		
		
		return a;
	}
}