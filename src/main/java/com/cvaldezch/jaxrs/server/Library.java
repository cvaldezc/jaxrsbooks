package com.cvaldezch.jaxrs.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/library")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Library {

	private static final Logger log = Logger.getLogger(Library.class.getName());
	
	private static Map<String, Book> books = new HashMap<String, Book>();
	static {
		Book[] bookarr = new Book[]{
				new Book("001", "The Judgment"),
				new Book("002", "The Stoker"),
				new Book("003", "Jackals and Arabs"),
				new Book("004", "The Refusal")
		};
		for (Book book : bookarr){
			books.put(book.getIsbn(), book);
		}
	}

	@GET
	@Path("/books")
	public Collection<Book> getBooks(){
		Collection<Book> result = books.values();
		log.info("getbooks: " + result);
		return result;
	}

	@GET
	@Path("/books/{isbn}")
	public Book getBook(@PathParam("isbn") String id){
		Book book = books.get(id);
		log.info("getBook: " + book);
		return book;
	}

	@PUT
	@Path("/books/{isbn}")
	public Book addBook(@PathParam("isbn") String id, @QueryParam("title") String title){
		Book book = new Book(id, title);
		log.info("add Book: " + book);
		books.put(id, book);
		return book;
	}

	@POST
	@Path("/books/{isbn}")
	public Book updateBook(@PathParam("isbn") String id, String title){
		Book book = books.get(id);
		if (book != null) {
			book.setTitle(title);
		}
		log.info("update Book: " + book);
		return book;
	}

	@DELETE
	@Path("/books/{isbn}")
	public Book removeBook(@PathParam("isbn") String id){
		Book book = books.remove(id);
		log.info("remove Book: " + book);
		return book;
	}

}
