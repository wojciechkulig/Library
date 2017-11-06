package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import library.model.Library;

public interface LibraryRepository {
	List<Library> findAll() throws DataAccessException;
	Optional<Library> findById(int id) throws DataAccessException;
}
