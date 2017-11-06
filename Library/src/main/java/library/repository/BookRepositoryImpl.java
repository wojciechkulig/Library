package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.Book;

@Repository
public interface BookRepositoryImpl extends JpaRepository<Book, Integer>, BookRepository {
	@Override
	List<Book> findAll() throws DataAccessException;
	@Override
	List<Book> findByTitleIgnoreCaseContaining(String title) throws DataAccessException;
	@SuppressWarnings("unchecked")
	@Override
	Book save(Book book) throws DataAccessException;
	@Override
	Optional<Book> findById(int id) throws DataAccessException;
	@Override
	void delete(Book book) throws DataAccessException;
	
}
