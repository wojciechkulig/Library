package library.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import library.model.Author;
import library.service.ApplicationService;

@RestController
public class AuthorsResource {
	@Autowired
	private ApplicationService service;
	
	@GetMapping(value="/authors")
	public List<Author> getAuthors(@PathVariable("id") int id) {
		return service.findAuthors();
	}
	
	@GetMapping(value="/authors/{id}")
	public Author getAuthor(@PathVariable("id") int id) {
		Author author = service.findAuthorById(id);
		return author;
	}

}
