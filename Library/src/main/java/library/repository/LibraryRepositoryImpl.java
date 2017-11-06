package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.Library;
@Repository
public interface LibraryRepositoryImpl extends JpaRepository<Library, Integer>,LibraryRepository {
	@Override
	List<Library> findAll() throws DataAccessException;
	@Override
	Optional<Library> findById(int id) throws DataAccessException;

}
