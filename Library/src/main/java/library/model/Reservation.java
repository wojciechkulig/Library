package library.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationId")
	private int id;
	@Column(name = "dateBorrowed")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateBorrowed;
	@Column(name = "dateDueBack")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateDueBack;
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User user;
	@Column(name = "queueNumber")
	private int queueNumber;
	@ManyToOne
	@JoinColumn(name="BookId")
	private Book book;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Date getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(Date dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public Date getDateDueBack() {
		return dateDueBack;
	}

	public void setDateDueBack(Date dateDueBack) {
		this.dateDueBack = dateDueBack;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQueueNumber() {
		return queueNumber;
	}

	public void setQueueNumber(int queueNumber) {
		this.queueNumber = queueNumber;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	

}
