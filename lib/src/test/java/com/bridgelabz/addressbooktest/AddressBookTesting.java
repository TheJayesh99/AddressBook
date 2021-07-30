package com.bridgelabz.addressbooktest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.bridgelabz.addressbook.AddressBookService;
import com.bridgelabz.addressbook.Contact;

public class AddressBookTesting 
{
	@Test
	public void givenReadFromDB_ShouldReturnListOfContacts() 
	{
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList  = addressBookService.readFromDataBase();
		assertEquals(4, contactList.size());
	}
}
