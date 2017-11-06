package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import library.model.Author;

public interface AuthorsRepository {
	List<Author> findAll() throws DataAccessException;
	Optional<Author> findById(int id) throws DataAccessException;
}
