package library.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="libraries")
public class Library {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="libraryId")
	private int id;
	@Column(name="address")
	@Size(min=5)
	private String address;
	@Column(name="contactNumber")
	@Size(min=9,max=9)
	private String contactNumber;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="libraryId")
	private List<BusinessHours> businessHours;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public List<BusinessHours> getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(List<BusinessHours> businessHours) {
		this.businessHours = businessHours;
	}
	
	

}
