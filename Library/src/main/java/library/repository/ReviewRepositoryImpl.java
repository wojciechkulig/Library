package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.Review;
@Repository
public interface ReviewRepositoryImpl extends JpaRepository<Review, Integer>, ReviewRepository {
	@Override
	List<Review> findByBook_Id(int id) throws DataAccessException;
	@Override
	Optional<Review> findById(int id) throws DataAccessException;
	@SuppressWarnings("unchecked")
	@Override
	Review save(Review review) throws DataAccessException;
	@Override
	void delete(Review review) throws DataAccessException;
	
}
