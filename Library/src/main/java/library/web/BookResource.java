package library.web;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import library.model.Book;
import library.service.ApplicationService;

@RestController
public class BookResource {
	@Autowired
	private ApplicationService service;
	
	@GetMapping(value="/books")
	public List<Book> getAllBooks(){
		return service.findBooks();
	}
	
	@GetMapping(value="/books", params= "title")
	public List<Book> getBooksWithGivenTitle(@RequestParam("title") String title){
		List<Book> books = service.findBooksByTitle(title);
		return books;
	}
	
	@GetMapping(value="/books/{id}")
	public Book getBook(@PathVariable("id") int id) {
		return service.findBookById(id);
	}
	@PostMapping(value="/books")
	public ResponseEntity<Object> addBook(@Valid @RequestBody Book book) {
		Book savedBook = service.saveBook(book);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping(value="/books/{id}")
	public void deleteBook(@PathVariable("id") int id) {
		service.deleteBook(id);
	}

}
