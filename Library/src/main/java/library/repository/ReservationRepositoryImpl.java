package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.Reservation;
@Repository
public interface ReservationRepositoryImpl extends JpaRepository<Reservation, Integer>,ReservationRepository {
	@Override
	List<Reservation> findByBook_Id(int id) throws DataAccessException;
	@Override
	Optional<Reservation> findById(int id) throws DataAccessException;
	@SuppressWarnings("unchecked")
	@Override
	Reservation save(Reservation reservation) throws DataAccessException;
	@Override
	void delete(Reservation reservation) throws DataAccessException;
}
