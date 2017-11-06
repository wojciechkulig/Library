package library.repository;

import java.util.List;
import java.util.Optional;

import library.model.User;

public interface UserRepository {
	List<User> findByLastName(String lastName);
	Optional<User> findById(int id);
	User findOneByUsername(String username);
	
}
