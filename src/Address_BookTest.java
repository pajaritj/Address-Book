import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class Address_BookTest {

	 @Test
	    public void testConstructor()
	    {
	      new Address_Book();
	    }

	    //~ Public Methods ........................................................

	    // ----------------------------------------------------------
	    /**
	     * Check that the methods work for empty address books
	     */
	    @Test
	    public void testEmpty()
	    {
	    	Address_Book test = new Address_Book();
	    	String [] fields = {" ","Walsh"," "," "," "," "," "," "};
	    	assertFalse("removeContact from empty Address Book",test.removeContact("Christy"));
	    	assertFalse("editContact from empty Address Book",test.editContact("Christy",fields));
	    	assertFalse("displayContact from empty Address Book",test.displayContact("Christy"));
	    	assertFalse("displayBy(First) from empty Address Book",test.displayBy("First"));
	    	assertFalse("displayBy(Last) from empty Address Book",test.displayBy("Last"));
	        assertTrue("addContact to empty Address Book",test.addContact(new Contact("Christy","Murphy","51 Ashling hgts","Blanchardstown","Dublin","+353018214993","0869174454","4/15/1986")));
	        
	    }

	    // ----------------------------------------------------------
	    /**
	     * Check that the methods work for initialized address books
	     */
	    @Test
	    public void testInitialised()
	    {
	    	Address_Book test = new Address_Book();
	    	
	    	String [] fields = {"Christy Walsh"," "," "," "," "};
	    	assertTrue("Failed to addContact to initialized Address Book",test.addContact(new Contact("Christy","Murphy","51 Ashling hgts","Blanchardstown","Dublin","+353018214993","0869174454","4/15/1986")));
	        assertTrue("Failed to displayBy(First) from initialized Address Book",test.displayBy("First"));
	    	assertTrue("Failed to displayBy(Last) from initialized Address Book",test.displayBy("Last"));
	    	assertTrue("Failed to displayContact from initialized Address Book",test.displayContact("Christy"));
	    	assertTrue("Failed to editContact from initialized Address Book",test.editContact("Christy",fields));
	    	assertTrue("Failed to removeContact from initialized Address Book",test.removeContact("Christy"));
	    }
	    
	 // ----------------------------------------------------------
	    /**
	     * Checks behaviors for addContact()
	     */
	    @Test
	    public void testaddContact()
	    {
	    	Address_Book test = new Address_Book();
	    	assertFalse("Added a null contact",test.addContact(null));
	    	assertTrue("Failed to addContact to initialized Address Book",test.addContact(new Contact("Christy","Murphy","51 Ashling hgts","Blanchardstown","Dublin","+353018214993","0869174454","4/15/1986")));
	    	
	    }
	    
	 // ----------------------------------------------------------
	    /**
	     * Checks behaviors for removeContact()
	     */
	    @Test
	    public void testremoveContact()
	    {
	    	Address_Book test = new Address_Book();
	    	test.addContact(new Contact("Christy","Murphy","51 Ashling hgts","Blanchardstown","Dublin","+353018214993","0869174454","4/15/1986"));
	    	assertFalse("Removed a contact with no name",test.removeContact(null));
	    	assertTrue("Failed to removeContact from initialized Address Book",test.removeContact("Christy"));
	    	
	    }
	    
	 // ----------------------------------------------------------
	    /**
	     * Checks behaviors for editContact()
	     */
	    @Test
	    public void testeditContact()
	    {
	    	Address_Book test = new Address_Book();
	    	
	    	String [] fields = {"Christy Walsh"," "," "," "," "};
	    	test.addContact(new Contact("Christy","Murphy","51 Ashling hgts","Blanchardstown","Dublin","+353018214993","0869174454","4/15/1986"));
	    	assertTrue("Failed to editContact from initialized Address Book",test.editContact("Christy",fields));
	    	assertFalse("Edited contact with no name",test.editContact(null,fields));
	    	assertFalse("Edited contact with no fields",test.editContact("Christy",null));
	    	assertFalse("Edited contact with no fields and no name",test.editContact(null,null));
	    }
	    
	 // ----------------------------------------------------------
	    /**
	     * Check the methods for displaying contacts
	     */
	    @Test
	    public void testdisplayContact()
	    {
	    	Address_Book test = new Address_Book();
	    	Path filePath = Paths.get(System.getProperty("user.dir")+"\\src\\res\\Contact_List.txt");
			try {
				List<String> lines = Files.readAllLines(filePath);
				for(String contacts: lines){
					String [] contact = contacts.split(",");
					test.addContact(new Contact(contact[0],contact[1],contact[2],contact[3],contact[4],contact[5],contact[6],contact[7]));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	    	assertTrue("Failed to displayBy(First) from initialized Address Book",test.displayBy("First"));
	    	assertTrue("Failed to displayBy(Last) from initialized Address Book",test.displayBy("Last"));
	    	assertFalse("Listed contacts with no sort type",test.displayBy(null));
	    	assertTrue("Did not display contact in Address Book",test.displayContact("Christy"));
	    	test.removeContact("Christy");
	    	assertFalse("Displayed contact not in Address Book",test.displayContact("Christy"));
	    	assertFalse("Displayed contact with no name",test.displayContact(null));
	    }
	    

}
