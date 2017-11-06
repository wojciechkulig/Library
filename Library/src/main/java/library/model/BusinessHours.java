package library.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="businessHours")
public class BusinessHours {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="businessHoursId")
	@JsonIgnore
	private int id;
	@Min(value=1)
	@Max(value=7)
	@Column(name="day")
	private int day;
	@Column(name="openTime")
	@NotNull
	private Time openTime;
	@Column(name="closeTime")
	@NotNull
	private Time closeTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public Time getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}
	public Time getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}
	
	
	
}
