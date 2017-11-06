package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import library.model.Reservation;

public interface ReservationRepository {
	List<Reservation> findByBook_Id(int id) throws DataAccessException;
	Optional<Reservation> findById(int id) throws DataAccessException;
	Reservation save(Reservation reservation) throws DataAccessException;
	void delete(Reservation reservation) throws DataAccessException;
}
