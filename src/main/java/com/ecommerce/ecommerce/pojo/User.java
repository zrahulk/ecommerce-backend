package com.ecommerce.ecommerce.pojo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ecommerce.ecommerce.customAnnotaions.Unique;
import org.hibernate.annotations.NaturalId;



@Entity
@Table(name="users")

public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userId")
	private long userId;
	@NotBlank(message = "Full Name should not be blank")
	private String name;
	
	@NotBlank(message = "Email should not be blank")
	@Email(message = "It should be email")
	@Unique(message = "EmailId exist")
	@NaturalId
	private String email;
	@NotBlank(message = "Password should not be black")
	private String password;
	@Column(length=10, name="phone_number")
	@Size(max= 10 ,min=10, message = "Phone number should be 10 digit long")
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
	@Column(name="area_code")
	private String areaCode;
	@Column(name="createTs")
	private Long createTs;
	public User(long userId) {
		super();
		this.userId = userId;
	}
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public User(String phoneNumber,
			String password, String areaCode) {
		super();
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.areaCode = areaCode;
		
	}

	public User(String name,
				String email,
				String password,
				String phoneNumber,
				String areaCode) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.areaCode = areaCode;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User( String name, String email, String password,
			String phoneNumber, Set<Role> roles, List<Address> addresses, String areaCode,
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
	
	
	public User(String name, String email, String password,
			 String phoneNumber, String areaCode, Long createTs) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.areaCode = areaCode;
		this.active = 1;
		this.createTs = createTs;
	}
	
	
	
	public User( String name,
				 String email,
				 String password,
				 String phoneNumber,
				 Set<Role> roles,
				 String areaCode,
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
