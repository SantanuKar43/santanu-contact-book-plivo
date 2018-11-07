package com.santanu.santanucontactbook;

import com.santanu.santanucontactbook.model.Contact;
import com.santanu.santanucontactbook.service.ContactService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ContactServiceTest {

	@Autowired
	private ContactService contactService;

	private String name;
	private String email;
	private Long phone;

	@Before
	public void init() {
		name = UUID.randomUUID().toString();
		email = UUID.randomUUID().toString();
		phone = (long) (Math.random() * 100000);
	}

	@Test
	public void testFindContactByEmail() {
		Contact contact = new Contact(name, email, phone);
		contactService.saveContact(contact);
		Contact contact1 = contactService.findContactByEmail(email);
		Assert.assertEquals(contact, contact1);
	}

	@Test
	public void testFindContactByName() {
		String email1 = UUID.randomUUID().toString();
		String email2 = UUID.randomUUID().toString();
		Contact contact = new Contact(name, email, phone);
		Contact contact1 = new Contact(name, email1, phone);
		Contact contact2 = new Contact(name, email2, phone);
		List<Contact> contactList = Arrays.asList(contact, contact1, contact2);
		contactService.saveContact(contact);
		contactService.saveContact(contact1);
		contactService.saveContact(contact2);
		List<Contact> contactList1 = contactService.findContactByName(name, 0, 10);
		Assert.assertEquals(contactList, contactList1);
	}

	@Test
	public void testDeleteContactById() {
		Contact contact = new Contact(name, email, phone);
		contactService.saveContact(contact);
		Contact contact1 = contactService.findContactByEmail(email);
		Boolean result = contactService.deleteContactById(contact1.getId());
		Assert.assertTrue(result);
		Assert.assertNull(contactService.findContactByEmail(email));
	}

	@Test
	public void testDeleteContactByEmail() {
		Contact contact = new Contact(name, email, phone);
		contactService.saveContact(contact);
		Boolean result = contactService.deleteContactByEmail(email);
		Assert.assertTrue(result);
		Contact contact1 = contactService.findContactByEmail(email);
		Assert.assertNull(contact1);
	}

	@Test
	public void testUpdateContactByEmail() {
		Contact contact = new Contact(name, email, phone);
		contactService.saveContact(contact);
		Contact contact1 = contactService.findContactByEmail(email);
		Assert.assertEquals(name, contact1.getName());
		String name1 = UUID.randomUUID().toString();
		Boolean result = contactService.updateContactByEmail(email, null, name1);
		Assert.assertTrue(result);
		Contact contact2 = contactService.findContactByEmail(email);
		Assert.assertEquals(name1, contact2.getName());
	}

	@Test
	public void testUpdateContactById() {
		Contact contact = new Contact(name, email, phone);
		contactService.saveContact(contact);
		Contact contact1 = contactService.findContactByEmail(email);
		Assert.assertEquals(name, contact1.getName());
		String email1 = UUID.randomUUID().toString();
		Boolean result = contactService.updateContactById(contact1.getId(), email1, null, "");
		Assert.assertTrue(result);
		Contact contact2 = contactService.findContactByEmail(email1);
		Assert.assertEquals(email1, contact2.getEmail());
	}

	@Test
	public void testSaveContact() {
		Contact contact = new Contact(name, email, phone);
		Boolean result = contactService.saveContact(contact);
		Assert.assertTrue(result);
		Contact contact1 = contactService.findContactByEmail(email);
		Assert.assertEquals(contact, contact1);
	}

}
