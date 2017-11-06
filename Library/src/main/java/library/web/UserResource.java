package library.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.exceptions.CustomUnauthorizedUserException;
import library.model.User;
import library.service.ApplicationService;

@RestController
public class UserResource {
	@Autowired
	private ApplicationService service;

	@GetMapping(value="/users", params= "lastName")
	public List<User> getUsersWithGivenLastName(@RequestParam("lastName") String lastName){
		return service.findUsersByLastName(lastName);
	}
	@GetMapping(value="/users/{id}")
	public User getUser(@PathVariable("id")int id, Principal principal) {
		User user = service.findUserByUsername(principal.getName());
		if(hasPermissionToGetUser(id, user))
			return service.findUserById(id);
		throw new CustomUnauthorizedUserException("Doesn't have ROLE_USER or principal id-"+id+" doesn't match requested user id");
	}
	private boolean hasPermissionToGetUser(int id, User user) {
		return user.getId() ==id || user.getAuthorities().stream().anyMatch(authority-> authority.equals(new SimpleGrantedAuthority("ROLE_USER")));
	}
}
