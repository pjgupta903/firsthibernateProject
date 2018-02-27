package org.javabrains.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name="user_details")
public class UserDetails{

	@Id
	@GenericGenerator(name="CustomIdGenerator",strategy="org.javabrains.util.CustomIdGenerator",parameters={
			@Parameter(name="sequence_name", value = "user_seq"),@Parameter(name="sequence_per_entity_suffix", value = "user_seq")})
	@GeneratedValue(generator="CustomIdGenerator")
	@Column(name="user_id")
	private String userId;
	
	
	// auto- generated primary key value of type Long
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;*/
	
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="join_date")
	private Date joinDate;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="user_phone", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="phone_id"))
	private List<Phone> phones = new ArrayList<Phone>();
	
	/*@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="address1",column=@Column(name="home_address1")),
		@AttributeOverride(name="city",column=@Column(name="home_city")),
		@AttributeOverride(name="state",column=@Column(name="home_state")),
		@AttributeOverride(name="address2",column=@Column(name="home_address2")),
	})
	private Address homeAddress;*/
	
	
	// code to use hibernate strategies for generating primary key value
	@ElementCollection
	@JoinTable(name="user_address" ,joinColumns=@JoinColumn(name="user_id"))
	@GenericGenerator(name="hilo-gen",strategy="hilo")
	@CollectionId(columns=@Column(name="address_id"), generator="hilo-gen", type=@Type(type="long"))
	private Collection<Address> officeAddresses = new HashSet<Address>();
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;


	/*public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}*/


	public Vehicle getVehicle() {
		return vehicle;
	}


	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public Collection<Address> getOfficeAddresses() {
		return officeAddresses;
	}


	public void setOfficeAddresses(Collection<Address> officeAddresses) {
		this.officeAddresses = officeAddresses;
	}


	@Temporal(TemporalType.TIME)
	public Date getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	
}
