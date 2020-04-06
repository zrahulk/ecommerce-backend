package com.ecommerce.ecommerce.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="addresses")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressId;
	private String address;
	private int pincode;
	private int country;
	private int state;
	
	public Address(int addressId) {
		super();
		this.addressId = addressId;
	}
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address( String address, int pincode, int country, int state) {
		super();
	
		this.address = address;
		this.pincode = pincode;
		this.country = country;
		this.state = state;
	}

	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
