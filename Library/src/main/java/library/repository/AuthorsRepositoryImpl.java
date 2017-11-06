package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.Author;
@Repository
public interface AuthorsRepositoryImpl extends JpaRepository<Author, Integer>,AuthorsRepository {
	@Override
	List<Author> findAll() throws DataAccessException;
	@Override
	Optional<Author> findById(int id) throws DataAccessException;
}
