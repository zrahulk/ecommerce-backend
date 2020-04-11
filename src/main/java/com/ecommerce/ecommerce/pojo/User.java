package com.ecommerce.ecommerce.pojo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	@Column(name="userId")
	private long userId;
	@NotBlank
	private String name;
	
	@Column(unique  = true)
	@NotBlank
	@Email
	@NaturalId
	private String email;
	private String password;
	@Column(unique = true, length=10, name="phoneNumber")
	@Size(max= 10 ,min=10)
	private String phoneNumber;
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name ="user_role", joinColumns = @JoinColumn(name = "userId"),
	inverseJoinColumns =@JoinColumn(name= "roleId") )
	
	private Set<Role> roles = new HashSet<Role>(); 
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name ="user_address", joinColumns =  @JoinColumn(name = "userId"), 
	inverseJoinColumns = @JoinColumn(name="addressId"))
	private List<Address> addresses = new LinkedList<Address>();
	
	public List<Address> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	
	}
	@Column(name="areaCode")
	private String areaCode;
	@Column(name="createTs")
	private Long createTs;
	public User(long userId) {
		super();
		this.userId = userId;
	}
	public User(@NotBlank @Email String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public User(@Size(max = 10, min = 10) String phoneNumber,
			String password, String areaCode) {
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
			Long createTs) {
		super();
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
		this.addresses = addresses;
		this.areaCode = areaCode;
		this.createTs = createTs;
		this.active = 1;
	}

	
	public User(User user) {
		this.active  =user.getActive();
		this.name = user.getName();
		this.areaCode  = user.getAreaCode();
		this.roles = user.getRoles();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.createTs = user.getCreateTs();
		this.userId = user.getUserId();
		
	}
	
	
	public User(@NotBlank String name, @NotBlank @Email String email, String password,
			@Size(max = 10, min = 10) String phoneNumber, String areaCode, Long createTs) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.areaCode = areaCode;
		this.active = 1;
		this.createTs = createTs;
	}
	
	
	
	public User( @NotBlank String name, 
			@NotBlank @Email String email, String password,
			@Size(max = 10, min = 10) String phoneNumber,
			Set<Role> roles, String areaCode, 
			Long createTs) {
		super();
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.active = 1;
		this.roles = roles;
		this.areaCode = areaCode;
		this.createTs = createTs;
	}
	@Column(name="userId")
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
	
	@Column(name="phoneNumber")
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
	
	@Column(name="areaCode")
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Column(name="createTs")
	public Long getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Long createTs) {
		this.createTs = createTs;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", roles=" + roles + ", addresses=" + addresses + ", areaCode="
				+ areaCode + ", createTs=" + createTs + "]";
	}
	
	
	
	
	
	
}
