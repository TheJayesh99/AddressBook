package com.bridgelabz.addressbooktest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void givenUpadteEmployeeData_WhenUpdated_shouldSyncWithDatabase()
	{
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readFromDataBase();
		addressBookService.updateContactInDataBase("virat",4321);
		assertTrue(addressBookService.checkSyncWithDB("virat"));
	}
}
