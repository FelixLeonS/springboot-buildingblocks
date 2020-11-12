package com.stacksimplify.restservices.springbootbuildingblocks.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"firstname","lastname"})
public class User extends RepresentationModel<User> {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "USER_NAME",length = 50, nullable = false, unique = true)
	@NotEmpty(message = "Username is a mandatory field, please provide a username")
	private String username;
	@Size(min = 2, message ="Minimin size for first name is 2 characters")
	@Column(name = "FIRST_NAME",length = 50, nullable = false)
	private String firstname;
	@Column(name = "LAST_NAME",length = 50, nullable = false)
	private String lastname;
	@Column(name = "EMAIL_ADDRESS",length = 50, nullable = false)
	private String email;
	@Column(name = "ROLE",length = 50, nullable = false)
	private String role;
	@JsonIgnore
	@Column(name = "SSN",length = 50, nullable = false, unique = true)
	private String ssn;
	
	
	@OneToMany(mappedBy="user")
	private List<Order> oders;
	
	// No argument constructor
	public User() {
		
	}
	// Fields constructor
	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}
	
	// Getter and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public List<Order> getOders() {
		return oders;
	}
	public void setOders(List<Order> oders) {
		this.oders = oders;
	}
	// To String (Optional for bean logging)
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
	
	
	
	
}
