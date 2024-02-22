package com.greencoffee.common.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producers")
public class Producer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 128, nullable = false, unique = true)
	private String email;

	@Column(length = 64, nullable = false)
	private String password;

	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;

	@Column(name = "phone_number", length = 45, nullable = false)
	private String phoneNumber;

	@Column(name = "geolocation", length = 45, nullable = false)
	private String geolocation;

	@Column(name = "address", length = 45, nullable = false)
	private String address;

	@Column(name = "altitude", length = 45, nullable = false)
	private String altitude;

	@Column(length = 64)
	private String photos;

	private boolean enabled;

	@ManyToMany
	@JoinTable(name = "producers_roles", joinColumns = @JoinColumn(name = "producer_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public Producer() {

	}

	public Producer(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(String geolocation) {
		this.geolocation = geolocation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	  @ManyToMany
	  
	  @JoinTable( name = "producer_roles", joinColumns = @JoinColumn(name =
	  "producer_id"), inverseJoinColumns = @JoinColumn(name = "role_id") )
	  
	  public Set<Role> getRoles() { return roles; }
	  
	  public void setRoles(Set<Role> roles) { this.roles = roles; }
	 

	@Override
	public String toString() {
		return "Producer [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", geolocation=" + geolocation
				+ ", address=" + address + ", altitude=" + altitude + ", photos=" + photos + ", enabled=" + enabled
				+ ", roles=" + roles + "]";
	}

	/*
	 * @Override public String toString() { return "User [id=" + id + ", email=" +
	 * email + ", password=" + password + ", firstName=" + firstName + ", lastName="
	 * + lastName + "]"; }
	 */

}
