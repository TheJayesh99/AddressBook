package com.bridgelabz.addressbook;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


public class AddressBookService
{
	//address books hash map
	HashMap<String,LinkedList<Contact>> addressBooks = new HashMap<>();
	Scanner scanner = new Scanner(System.in);
	public static final String FILE_PATH="c://Users//malij//OneDrive//Desktop";
	public static final String TEXT_FILE="/addressBook.txt";
	public static final String CSV_FILE="/addressBook.csv";
	//method to add contacts
	public Contact addContact()
	{
		Contact contact = new Contact();
		LinkedList<Contact> allContacts = new LinkedList<Contact>();
		System.out.println("Enter First Name");
		contact.setFirstName(scanner.next());
		System.out.println("Enter last Name");
		contact.setLastname(scanner.next());
		System.out.println("Enter address");
		contact.setAddress(scanner.next());
		System.out.println("Enter City");
		contact.setCity(scanner.next());
		System.out.println("Enter State");
		contact.setState(scanner.next());
		System.out.println("Enter Pincode");
		contact.setZip(scanner.next());
		System.out.println("Enter Phone Number");
		contact.setPhoneNumber(scanner.nextInt());
		System.out.println("Enter Email");
		contact.setEmail(scanner.next());
		System.out.println("Enter Book name to which you have to add contact");
		String bookName  = scanner.next();

		//checking book already exist
		if (addressBooks.containsKey(bookName))
		{
			//if exist then add contact to list
			LinkedList<Contact> contactList  =  addressBooks.get(bookName);				
			addContactToExsistingBook(contact, bookName, contactList);
		}
		else
		{	
			//creating a new book and list
			allContacts.add(contact);
			addressBooks.put(bookName,allContacts);
			System.out.println("New book created and Contact Added Sucessfully");
		}

		return contact;
	}

	//method to delete contact
	public boolean deleteContact(int phoneNumberToDelete,String bookName) 
	{
		if (addressBooks.containsKey(bookName))
		{
			LinkedList<Contact> contactList  =  addressBooks.get(bookName);
			for (Contact contact : contactList)
			{	
				if (contact.getPhoneNumber() == phoneNumberToDelete)
				{
					contactList.remove(contact);
					return operationStatus(true);
				}
			}
		}
		return operationStatus(false);
	}

	//method to edit contact
	public boolean editContact(int phoneNumber,String bookName)
	{
		if (addressBooks.containsKey(bookName))
		{
			LinkedList<Contact> contactList  =  addressBooks.get(bookName);
			for (Contact contact : contactList)
			{	
				if (contact.getPhoneNumber() == phoneNumber)
				{
					System.out.println("Enter First Name");
					String firstName = scanner.next();
					System.out.println("Enter last Name");
					String lastName = scanner.next();
					System.out.println("Enter address");
					String address = scanner.next();
					System.out.println("Enter City");
					String city = scanner.next();
					System.out.println("Enter State");
					String state = scanner.next();
					System.out.println("Enter zip");
					String zip = scanner.next();
					contact.setFirstName(firstName);
					contact.setLastname(lastName);
					contact.setAddress(address);
					contact.setCity(city);
					contact.setState(state);
					contact.setState(zip);
					return operationStatus(true);
				}
			}
		}
		return operationStatus(false);
	}

	public void displayContact() 
	{
		addressBooks.entrySet().stream()
		.map(books->books.getKey())
		.map(bookNames->{
			System.out.println(bookNames); 
			return addressBooks.get(bookNames); 
		})
		.forEach(contactInBook->System.out.println(contactInBook));
	}

	//method to search multiple person in city and state
	public int searchPerson(String searchKey)
	{
		int count = 0;
		for (String bookName : addressBooks.keySet())
		{
			LinkedList<Contact> contactList  =  addressBooks.get(bookName);
			contactList.stream()
			.filter(n->n.getState()==searchKey || n.getCity() == searchKey)
			.forEach(n->System.out.println(n.getFirstName()+" "+n.getLastname()));
		}
		return count;
	}



	//method to get operation status 
	private static boolean operationStatus(boolean status) 
	{
		if(status)
		{
			System.out.println("Operation  Successfully");
		}
		else
		{
			System.out.println("Contact not found");
		}
		return status;
	}

	//check Duplicate using name
	private void addContactToExsistingBook(Contact contact, String bookName, LinkedList<Contact> contactList)
	{
		boolean isAlreadyExsist = contactList.stream()
				.anyMatch(contactsInlist->contactsInlist.getFirstName()==contact.getFirstName());
		if( !(isAlreadyExsist) )
		{
			contactList.add(contact);				
			addressBooks.put(bookName, contactList);
			System.out.println("New Contact Added Sucessfully");
		}
		else
		{
			System.out.println("Contact already exsist");
		}
	}

	//method to view person based on state or city
	public void viewPerson(String viewKey) 
	{		
		for (String bookName : addressBooks.keySet())
		{
			LinkedList<Contact> contactList  =  addressBooks.get(bookName);
			contactList.stream()
			.filter(contact->contact.getState()==viewKey || contact.getCity() == viewKey)
			.forEach(contact->System.out.println(contact));
		}

	}

	//method to sort contacts based on person name
	public void sortContacts()
	{
		for (String bookName : addressBooks.keySet())
		{
			LinkedList<Contact> contatct = addressBooks.get(bookName);
			contatct.stream().sorted(Comparator.comparing(Contact::getFirstName)).forEach(n->System.out.println(n));
		}
	}

	//method to sort by city state or zip
	public void sortBY(int sortByWhich)
	{
		switch (sortByWhich)
		{
		case 1:
			for (String bookName : addressBooks.keySet())
			{
				LinkedList<Contact> contatct = addressBooks.get(bookName);
				contatct.stream().sorted(Comparator.comparing(Contact::getCity)).forEach(n->System.out.println(n));
			}
			break;
		case 2:
			for (String bookName : addressBooks.keySet())
			{
				LinkedList<Contact> contatct = addressBooks.get(bookName);
				contatct.stream().sorted(Comparator.comparing(Contact::getState)).forEach(n->System.out.println(n));
			}
			break;
		case 3:				
			for (String bookName : addressBooks.keySet())
			{
				LinkedList<Contact> contatct = addressBooks.get(bookName);
				contatct.stream().sorted(Comparator.comparing(Contact::getZip)).forEach(n->System.out.println(n));
			}
			break;
		default:
			System.out.println("Invalid Inout");
			break;
		}

	}

	//method to store all contacts to file
	public void writingToFile() 
	{
		//checking file is already there
		checkFile();

		StringBuffer addressBookBuffer = new StringBuffer();
		addressBooks.entrySet().stream()
		.map(books->books.getKey())
		.map(bookNames->{
			addressBookBuffer.append(bookNames+"\n");
			return addressBooks.get(bookNames); 
		})
		.forEach(contactInBook->addressBookBuffer.append(contactInBook+"\n"));

		//Writing data into file
		try 
		{
			Files.write(Paths.get(FILE_PATH+TEXT_FILE), addressBookBuffer.toString().getBytes());
			System.out.println("Written in the file \n\n");
		}
		catch (IOException e) 
		{	
			System.err.println("Problem encountered while writing into file");
		}
	}

	//method to read data from file
	public void readFile() 
	{
		try
		{
			//reading data from file 
			String contentOfFile = Files.readString(Paths.get(FILE_PATH+TEXT_FILE));
			//printing data in console
			System.out.println(contentOfFile);
		}
		catch (IOException e) 
		{
			System.err.println("Faced some problem while reading the file ");
		}
	}
	//method to create file if file doesn't exist
	private void checkFile() 
	{
		File file = new File(FILE_PATH+TEXT_FILE);
		try 
		{
			//checking file already exists
			if (!file.exists()) 
			{
				//if not creating a new file
				file.createNewFile();
				System.out.println("Created a file at "+FILE_PATH+TEXT_FILE);
			} 		
		}
		catch (IOException e1) 
		{			
			System.err.println("Problem encountered while creating a file");
		}
	}

	//method to write data into csv file
	public void writeToCsv() 
	{
		try
		{
			Writer writer = Files.newBufferedWriter(Paths.get(FILE_PATH+CSV_FILE)); //creating a file writer
			StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.build();
			List<Contact> ContactList = new ArrayList<>(); //init empty contact list 
			addressBooks.entrySet().stream()
			.map(books->books.getKey())
			.map(bookNames->{ 
				return addressBooks.get(bookNames); 
			}).forEach(contacts ->{
				ContactList.addAll(contacts);
			});
			beanToCsv.write(ContactList);  //writing all the contact which is in contact list
			writer.close(); 
		}
		catch (CsvDataTypeMismatchException e) 
		{
			e.printStackTrace();
		} 
		catch (CsvRequiredFieldEmptyException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	//reading data from csv file
	public void readFromCsvFile() 
	{
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get(FILE_PATH+CSV_FILE));   //reader to read contacts
			CsvToBean<Contact> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Contact.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
			List<Contact> contacts = csvToBean.parse();   //Converting them to list

			for(Contact contact: contacts) {
			    System.out.println("Name : " + contact.getFirstName()+" "+contact.getLastname());
			    System.out.println("Email : " + contact.getEmail());
			    System.out.println("PhoneNo : " + contact.getPhoneNumber());
			    System.out.println("Address : " + contact.getAddress());
			    System.out.println("State : " + contact.getState());			    
			    System.out.println("City : " + contact.getCity());
			    System.out.println("Zip : " + contact.getZip());
			    System.out.println("==========================");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}