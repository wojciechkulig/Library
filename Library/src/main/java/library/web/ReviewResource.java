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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import library.model.Review;
import library.service.ApplicationService;

@RestController
public class ReviewResource {
	@Autowired
	private ApplicationService service;
	
	@GetMapping(value="/books/{bookId}/reviews")
	public List<Review> getAllReviews(@PathVariable("bookId")int id){
		return service.findReviewsByBookId(id);
	}
	@GetMapping(value="/books/{bookId}/reviews/{reviewId}")
	public Review getReview(@PathVariable("reviewId") int id) {
		return service.findReviewById(id);
	}
	@PostMapping(value="/books/{bookId}/reviews")
	public ResponseEntity<Object> addReview(@PathVariable("bookId")int id, Principal principal,@RequestBody @Valid Review review) {
		review.setBook(service.findBookById(id));
		review.setUser(service.findUserByUsername(principal.getName()));
		review = service.saveReview(review);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(review.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping(value="/books/{bookId}/reviews/{reviewId}")
	public void deleteReview(@PathVariable("reviewId")int reviewId) {
		service.deleteReview(reviewId);
	}

}
