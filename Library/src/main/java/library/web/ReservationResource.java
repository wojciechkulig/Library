package library.web;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import library.model.Reservation;
import library.service.ApplicationService;

@RestController
public class ReservationResource {
	@Autowired
	private ApplicationService service;
	
	@PostMapping(value="/books/{bookId}/reservations")
	public ResponseEntity<Object> saveReservation(@PathVariable("bookId") int id, Principal principal, @RequestBody @Valid Reservation reservation) {
		reservation.setUser(service.findUserByUsername(principal.getName()));
		reservation.setBook(service.findBookById(id));
		reservation = service.saveReservation(reservation);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reservation.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@GetMapping(value="/books/{bookId}/reservations")
	public List<Reservation> getReservations(@PathVariable("bookId") int id){ 
		return service.findReservationsByBookId(id);
	}
	@PutMapping(value="/books/{bookId}/reservations/{reservationId}")
	public void updateReservation(@PathVariable("reservationId") int reservationId) {
		Reservation reservation = service.findReservationById(reservationId);
		service.extendReservationDueBackDate(reservation);
		
	}
	@DeleteMapping(value="/books/{bookId}/reservations/{reservationId}")
	public void deleteReservation(@PathVariable("reservationId") int reservationId) {
		service.deleteReservation(reservationId);
	}

}
