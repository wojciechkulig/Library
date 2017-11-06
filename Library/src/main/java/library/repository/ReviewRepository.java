package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import library.model.Review;

public interface ReviewRepository {
	List<Review> findByBook_Id(int id) throws DataAccessException;
	Optional<Review> findById(int id) throws DataAccessException;
	Review save(Review review) throws DataAccessException;
	void delete(Review review) throws DataAccessException;
}
