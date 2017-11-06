package library.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import library.model.Library;
import library.service.ApplicationService;

@RestController
public class LibraryResource {
	@Autowired
	private ApplicationService service;
	
	@GetMapping(value="/libraries")
	public List<Library> getAllLibraries(){
		return service.findLibraries();
	}
	@GetMapping(value="/libraries/{id}")
	public Library getLibrary(@PathVariable("id") int id) {
		Library library = service.findLibraryById(id);
		return library;
	}

}
