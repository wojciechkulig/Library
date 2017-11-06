package library.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.exceptions.ResourceNotFoundException;
import library.model.Author;
import library.model.Book;
import library.model.Library;
import library.model.Reservation;
import library.model.Review;
import library.model.User;
import library.repository.AuthorsRepository;
import library.repository.BookRepository;
import library.repository.LibraryRepository;
import library.repository.ReservationRepository;
import library.repository.ReviewRepository;
import library.repository.UserRepository;
@Service
public class ApplicationServiceImpl implements ApplicationService {
	private AuthorsRepository authorsRepository;
	private BookRepository bookRepository;
	private LibraryRepository libraryRepository;
	private ReservationRepository reservationRepository;
	private ReviewRepository reviewRepository;
	private UserRepository userRepository;
	
	@Autowired
	public ApplicationServiceImpl(AuthorsRepository authorsRepository,BookRepository bookRepository,
			LibraryRepository libraryRepository, ReservationRepository reservationRepository,
			ReviewRepository reviewRepository,UserRepository userRepository) {
		this.authorsRepository= authorsRepository;
		this.bookRepository = bookRepository;
		this.libraryRepository = libraryRepository;
		this.reservationRepository = reservationRepository;
		this.reviewRepository = reviewRepository;
		this.userRepository = userRepository;
	}
	@Transactional
	public List<Author> findAuthors(){
		return authorsRepository.findAll();
	}
	@Transactional
	public Author findAuthorById(int id) {
		Author author =  authorsRepository.findById(id).get();
		if(author==null)
			throw new ResourceNotFoundException("id-"+id);
		return author;
	}
	@Transactional
	public List<Book> findBooks(){
		return bookRepository.findAll();
	}
	@Transactional
	public List<Book> findBooksByTitle(String title){
		List<Book> books = bookRepository.findByTitleIgnoreCaseContaining(title);
		if(books==null)
			throw new ResourceNotFoundException("title-"+title);
		return books;
	}
	@Transactional
	public Book findBookById(int id) {
		Book book =  bookRepository.findById(id).get();
		if(book==null)
			throw new ResourceNotFoundException("id-"+id);
		return book;
	}
	@Transactional
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	@Transactional
	public void deleteBook(int bookId) {
		Book book = this.findBookById(bookId);
		if(book==null)
			throw new ResourceNotFoundException("id-"+bookId);
		bookRepository.delete(book);
	}
	
	@Transactional
	public List<Library> findLibraries(){
		return libraryRepository.findAll();
	}
	@Transactional
	public Library findLibraryById(int id) {
		Library library =  libraryRepository.findById(id).get();
		if(library==null)
			throw new ResourceNotFoundException("id-"+id);
		return library;
	}
	@Transactional
	public List<Reservation> findReservationsByBookId(int bookId){
		List<Reservation> reservations = reservationRepository.findByBook_Id(bookId);
		if(reservations==null)
			throw new ResourceNotFoundException("id-"+bookId);
		return reservations;
	}	
	@Transactional
	public Reservation findReservationById(int reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).get();
		if(reservation==null)
			throw new ResourceNotFoundException("id-"+reservationId);
		return reservation;
	}
	@Transactional
	public void deleteReservation(int reservationId) {
		Reservation reservation = this.findReservationById(reservationId);
		if(reservation==null)
			throw new ResourceNotFoundException("id-"+reservationId);
		List<Reservation> reservations = this.findReservationsByBookId(reservation.getBook().getId());
		if(reservations !=null) {
			reservations.stream().filter(r->r.getQueueNumber()>reservation.getId()).forEach(r->r.setQueueNumber(r.getQueueNumber()-1));
			reservations.stream().forEach(r->this.saveReservation(r));
		}
	}
	
	@Transactional
	public Reservation saveReservation(Reservation reservation) {
		List<Reservation> reservations = this.findReservationsByBookId(reservation.getBook().getId());
		if(reservations==null|| reservations.size()==0) {
			reservation.setQueueNumber(1);
		}else {
			int lastNumberInQueue = reservations.stream().mapToInt(r->r.getQueueNumber()).min().getAsInt();
			reservation.setQueueNumber(lastNumberInQueue+1);
		}
		return reservationRepository.save(reservation);
	}
	@Transactional
	public void extendReservationDueBackDate(Reservation reservation) {
		reservation.setDateDueBack(Date.valueOf(LocalDate.now().plusMonths(1)));
		this.saveReservation(reservation);
	}
	@Transactional
	public List<Review> findReviewsByBookId(int bookId){
		List<Review> reviews = reviewRepository.findByBook_Id(bookId);
		if(reviews==null)
			throw new ResourceNotFoundException("id-"+bookId);
		return reviews;
	}
	@Transactional
	public Review findReviewById(int reviewId) {
		Review review = reviewRepository.findById(reviewId).get();
		if(review ==null)
			throw new ResourceNotFoundException("id-"+reviewId);
		return review;
	}
	@Transactional
	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}
	@Transactional
	public void deleteReview(int reviewId) {
		Review review = this.findReviewById(reviewId);
		if(review == null)
			throw new ResourceNotFoundException("id-"+reviewId);
		reviewRepository.delete(review);
	}
	@Transactional
	public List<User> findUsersByLastName(String lastName){
		List<User> users = userRepository.findByLastName(lastName);
		if(users==null)
			throw new ResourceNotFoundException("lastName-"+lastName);
		return users;
	}
	@Transactional
	public User findUserById(int userId) {
		User user = userRepository.findById(userId).get();
		if(user==null)
			throw new ResourceNotFoundException("id-"+userId);
		return user;
	}
	@Transactional
	public User findUserByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}

}
