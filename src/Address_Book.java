import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Address_Book {
	private ArrayList<Contact> contacts;
	
	//Address Book Constructor
	public Address_Book(){
		contacts = new ArrayList<Contact>();
		// Create file of list of users and load to array
	}
	// Used to add contacts to address book, Has a single Contact argument to be added to the address book 
	public boolean addContact(Contact target_contact){
		if(contacts!=null && target_contact!=null){
			if(!contacts.contains(target_contact)){
				contacts.add(target_contact);
				return true;
			}
		}
		return false;
	}
	// Used to remove contacts from address book, Has a single string argument that is used to look for the contact in the address book
	public boolean removeContact(String name){
		if(!contacts.isEmpty() && name!=null){
			Contact contact =hasContact(name);
			if(contact!=null){
					System.out.println("Removed Contact "+ contact.getFirst_Name() + " " + contact.getLast_Name());
					contacts.remove(contact);
					return true;
			}
		}	
		return false;
	}
	// Used to edit contacts from address book, Has a string argument and a string array argument that is used to change contact details
	public boolean editContact(String name,String [] fields){
		if(!contacts.isEmpty() && name !=null && fields!=null){
			Contact contact =hasContact(name);
			if(contact!=null && fields!=null){
				String[] splited = fields[0].split(" ");
				if(!fields[0].equals(" ")){
					contact.setFirst_Name(splited[0]);
					contact.setLast_Name(splited[1]);
				}
				if(!fields[1].equals(" ")){
					String [] addsplited =fields[1].split("\\\\");
					contact.setAddress(addsplited[0]);
					contact.setCity(addsplited[1]);
					contact.setCounty(addsplited[2]);
				}
				if(!fields[2].equals(" ")){
					contact.setPhone_Num(fields[2]);
				}
				if(!fields[3].equals(" ")){
					contact.setMobile_Num(fields[3]);
				}
				if(!fields[4].equals(" ")){
					contact.setBirth_Date(fields[4]);
				}
				System.out.println("Edited Contact: ");
				displayContact(contact.getFirst_Name());
				return true;
			}
		}
		return false;
	}
	// Displays a contact given a String name argument
	public boolean displayContact(String name){
		if(!contacts.isEmpty() && name !=null){
		Contact contact =hasContact(name);
			if(contact!=null){
					contact.printContact();
					return true;
				}
		}
		return false;
	}
	// Displays the whole list of contacts sorted by type, Has a single argument that defines what type of sorting to be applied 
	public boolean displayBy(String sortType){
		if(!contacts.isEmpty() && sortType !=null){
			if(sortType.equals("First")){
				Collections.sort(contacts, new ContactFirstComparator());
				for(Contact contact : contacts){
					System.out.println("Name: "+ contact.getFirst_Name() + " " + contact.getLast_Name());
				}
			}
			else{
				Collections.sort(contacts, new ContactLastComparator());
				for(Contact contact : contacts){
					System.out.println("Name: "+ contact.getLast_Name() + " " + contact.getFirst_Name() );
				}
			}
			return true;
		}
		return false;
	}
	// Checks if a contact is in the address book
	private Contact hasContact(String name){
		if(!contacts.isEmpty() && name !=null){
			for(Contact contact : contacts){
				if(contact.getFirst_Name().equals(name) || contact.getLast_Name().equals(name)
					|| (contact.getFirst_Name()+contact.getLast_Name()).equals(name)){
					return contact;
				}
			}
		}
		return null;
	}
	
	// Used for comparison of contacts inside the address book, Helps with the sorting
	private class ContactFirstComparator implements Comparator<Contact> {
	    @Override
	    public int compare(Contact o1, Contact o2) {
	        return o1.getFirst_Name().compareTo(o2.getFirst_Name());
	    }
	}
	private class ContactLastComparator implements Comparator<Contact> {
	    @Override
	    public int compare(Contact o1, Contact o2) {
	        return o1.getLast_Name().compareTo(o2.getLast_Name());
	    }
	}
}
