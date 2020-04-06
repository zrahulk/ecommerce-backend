package com.ecommerce.ecommerce.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;



@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userId;
	@NotBlank
	private String name;
	
	@Column(unique  = true)
	@NotBlank
	@Email
	@NaturalId
	private String email;
	private String password;
	@Column(unique = true, length=10)
	@Size(max= 10 ,min=10)
	private String phoneNumber;
	@OneToMany(cascade = CascadeType.ALL)
			@JoinColumn(name = "userId")
	
	private Set<Role> roles = new HashSet<Role>(); 
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private List<Address> addresses = new LinkedList<Address>();
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	private String areaCode;
	private Date createTs;
	public User(long userId) {
		super();
		this.userId = userId;
	}
	public User(@NotBlank @Email String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public User(@Size(max = 10, min = 10) String phoneNumber,String password, String areaCode) {
		super();
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.areaCode = areaCode;
		
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(@NotBlank String name, @NotBlank @Email String email, String password,
			@Size(max = 10, min = 10) String phoneNumber, Set<Role> roles, List<Address> addresses, String areaCode,
			Date createTs) {
		super();
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
		this.addresses = addresses;
		this.areaCode = areaCode;
		this.createTs = createTs;
	}

	
	
	
	public User(@NotBlank String name, @NotBlank @Email String email, String password,
			@Size(max = 10, min = 10) String phoneNumber, String areaCode, Date createTs) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.areaCode = areaCode;
		this.createTs = createTs;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Date getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", roles=" + roles + ", addresses=" + addresses + ", areaCode="
				+ areaCode + ", createTs=" + createTs + "]";
	}
	
	
	
	
	
	
}
