package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import library.model.Book;

public interface BookRepository {
	List<Book> findAll() throws DataAccessException;
	List<Book> findByTitleIgnoreCaseContaining(String title) throws DataAccessException;
	Book save(Book book) throws DataAccessException;
	Optional<Book> findById(int id) throws DataAccessException;
	void delete(Book book) throws DataAccessException;
}
