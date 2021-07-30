package com.bridgelabz.addressbook;

import com.opencsv.bean.CsvBindByPosition;

public class Contact {
	private int id;
	@CsvBindByPosition(position = 0)
	 private String firstName;
	@CsvBindByPosition(position = 1)
	 private String lastname;
	@CsvBindByPosition(position = 2)
	 private String address;
	@CsvBindByPosition(position = 3)
	 private String city;
	@CsvBindByPosition(position = 4)
	 private String state;
	@CsvBindByPosition(position = 5)
	 private String zip;
	@CsvBindByPosition(position = 6)
	 private int phoneNumber;
	@CsvBindByPosition(position = 7)
	 private String email;
	 
	public Contact() {
		super();
	
	}

	public Contact(int id, String firstName, String lastname, String address, String city, String state, String zip,
			int phoneNumber, String email)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	@Override
	public String toString() {
		return "firstName=" + firstName + ", lastname=" + lastname + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", phoneNumber=" + phoneNumber + ", email=" + email ;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	 
}
