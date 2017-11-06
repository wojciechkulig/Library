package library.service;

import java.util.List;

import library.model.Author;
import library.model.Book;
import library.model.Library;
import library.model.Reservation;
import library.model.Review;
import library.model.User;

public interface ApplicationService {
	
	public List<Author> findAuthors();
	public Author findAuthorById(int id);
	public List<Book> findBooks();
	public List<Book> findBooksByTitle(String title);
	public Book findBookById(int id);
	public Book saveBook(Book book);
	public void deleteBook(int bookId);
	public List<Library> findLibraries();
	public Library findLibraryById(int id);
	public List<Reservation> findReservationsByBookId(int bookId);
	public Reservation findReservationById(int reservationId);
	public void deleteReservation(int reservationId);
	public Reservation saveReservation(Reservation reservation);
	public List<Review> findReviewsByBookId(int bookId);
	public Review findReviewById(int reviewId);
	public Review saveReview(Review review);
	public void deleteReview(int reviewId);
	public List<User> findUsersByLastName(String lastName);
	public User findUserById(int userId);
	public void extendReservationDueBackDate(Reservation reservation);
	public User findUserByUsername(String username);
}
