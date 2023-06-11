package org.acme.rest;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/Books")
public class BookResource {

    private Set<Book> Books = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public BookResource() {
        Books.add(new Book("Refactoring", "Martin Fowler"));
        Books.add(new Book("Head First Design Patterns", "Eric Freemon"));
    }

    @GET
    public Set<Book> list() {
        return Books;
    }

    @POST
    public Set<Book> add(Book Book) {
        Books.add(Book);
        return Books;
    }

    @DELETE
    public Set<Book> delete(Book Book) {
        Books.removeIf(existingBook -> existingBook.name.contentEquals(Book.name));
        return Books;
    }
}
