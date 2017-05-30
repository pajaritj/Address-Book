import java.util.Scanner;
import java.util.Calendar;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
	private final static int QUIT = 6;
	private final static int ADD = 1;
	private final static int DELETE = 2;
	private final static int EDIT = 3;
	private final static int LIST = 4;
	private final static int DISPLAY = 5;
	private final static String FILE_NAME = "Contact_List.txt";
	private final static String ASK_NAME = "Enter your Contact\'s name.";
	private final static String ERROR_ASK_NAME = "Please enter a valid first and last name.";
	private final static String ASK_DOB ="Enter their Date of Birth. (DD\\MM\\YY)";
	private final static String ERROR_ASK_DOB ="Please enter a valid Date of Birth. (DD\\MM\\YYYY)";
	private final static String ASK_PNUM ="Enter their phone number.";
	private final static String ERROR_ASK_PNUM ="Please enter a valid phone number.";
	private final static String ASK_MNUM ="Enter their mobile number.";
	private final static String ERROR_ASK_MNUM ="Please enter a valid mobile number.";
	private final static String ASK_ADDRESS = "Enter their Address. (Street Address,City/Town,County)";
	private final static String ERROR_ASK_ADDRESS ="Please enter a valid address.";
	private final static String REMOVE_ASK = "Enter the Contact\'s first or last name that you want to remove.";
	private final static String ERROR_REMOVE_ASK = "Please enter a valid first name or last name.";
	private final static String DISPLAY_ASK = "Display list by First or Last name ? (Type First or Last).";
	private final static String ERROR_DISPLAY_ASK = "Please enter First or Last.";
	private final static String DISPLAY_CON_ASK = "Enter your Contact\'s first or last name that you want to display.";
	private final static String ERROR_DISPLAY_CON_ASK = "Please enter a valid first name or last name.";
	private final static String EDIT_ASK ="Enter your Contact\'s name that you want to edit.";
	private final static String ERROR_EDIT_ASK = "Please enter a valid first name or last name.";
	private final static String EDIT_INSTRUCTIONS ="Type the new information of the fields that you want to edit.Leave fields empty to not change them.";
	private final static String EDIT_INSTRUCTIONS_TWO ="Seperated by commas ( Name,Address(Street Address\\County\\City ),Phone Number,Mobile Number,Date of Birth(DD\\MM\\YY) )";
	private final static String ERROR_EDIT_FIELDS ="Please enter the following fields in this format: Name,Address( Street Address\\County\\City ),Phone Number,Mobile Number,Date of Birth(DD\\MM\\YY)";
	private final static String ERROR_MENU ="Please select an option from the menu by typing the corresponding number.";
	private final static String MENU_ASK ="Enter your menu choice.";
	private static String First_Name,Last_Name,Address,City,County,Phone_Num,Mobile_Num,Birth_Date,print_text,name = " ";
	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		Address_Book contact_list = new Address_Book();
		try {
			// Initialise address book with pre-existing contacts from contact list text file
			Path filePath = Paths.get(System.getProperty("user.dir")+"\\src\\res\\" + FILE_NAME);
			List<String> lines = Files.readAllLines(filePath);
			initialiseAddressBook(contact_list,lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Show the menu in the command line
		menu();
		int choice;
		print_text= " ";
		//Keep asking the user to provide menu numbers within range
		do{
			choice =stdin.nextInt();
			stdin.nextLine();
			System.out.println(print_text);
			print_text=ERROR_MENU;
			// Show relevant questions to corresponding menu options
		while(choice!=QUIT){
			switch(choice){
				case ADD:
					// Asks the user questions to fill in the fields needed for a new contact
					fillFields(stdin);
				    //Create new contact, write it to file and add to address book
					Contact newContact= new Contact(First_Name,Last_Name,Address,City,County,Phone_Num,Mobile_Num,Birth_Date);
					String content = "\n"+First_Name+ "," +Last_Name+ "," +Address+ "," +City+ "," +County+ "," +Phone_Num+ "," +Mobile_Num+ "," +Birth_Date;
					try {
						Path filePath = Paths.get(System.getProperty("user.dir")+"\\src\\res\\" + FILE_NAME);
						Files.write(filePath, content.getBytes(), StandardOpenOption.APPEND);
					} catch (IOException e) {
						e.printStackTrace();
					}
					contact_list.addContact(newContact);
					break;
				case DELETE:
					print_text = REMOVE_ASK;
					// Keep asking the user to provide a valid name to remove contact
					do {
					    System.out.println(print_text);
					    name = stdin.nextLine();
					    print_text = ERROR_REMOVE_ASK;
					} while (name==" ");
					name = name.substring(0,1).toUpperCase() + name.substring(1,name.length());
					contact_list.removeContact(name);
					break;
				case EDIT:
					// Keep asking the user to provide a valid name to edit contact
					print_text = EDIT_ASK;
					do {
					    System.out.println(print_text);
					    name = stdin.nextLine();
					    print_text = ERROR_EDIT_ASK;
					} while (name==" ");
					name = name.substring(0,1).toUpperCase() + name.substring(1,name.length());
					contact_list.displayContact(name);
					System.out.println(EDIT_INSTRUCTIONS);
					print_text=EDIT_INSTRUCTIONS_TWO;
					// Keep asking the user to fill the fields to be changed properly
					String splited = " ";
					String [] split_string = null;
					do {
						System.out.println(print_text);
					    splited = stdin.nextLine();
					    split_string =splited.split(",");
					    print_text=ERROR_EDIT_FIELDS;
					} while (split_string.length<5);
					contact_list.editContact(name,split_string);
					break;
				case LIST:
					// Keep asking the user to type first or last to sort contacts
					String type = " ";
					print_text = DISPLAY_ASK;
					do {
					    System.out.println(print_text);
					    print_text = ERROR_DISPLAY_ASK;
					    type = stdin.nextLine();
					} while (!(type.equals("First") || type.equals("Last")));
					type = type.substring(0,1).toUpperCase() + type.substring(1,type.length());
					contact_list.displayBy(type);
					break;
				case DISPLAY:
					// Keep asking the user to provide a valid name to display contact
					name =" ";
					print_text = DISPLAY_CON_ASK;
					do {
					    System.out.println(print_text);
					    print_text = ERROR_DISPLAY_CON_ASK;
					    name = stdin.nextLine();
					} while(name==" ");
					name = name.substring(0,1).toUpperCase() + name.substring(1,name.length());
					contact_list.displayContact(name);
					break;
				case QUIT:
					break;
				default:
					System.out.println(print_text + "\n");
					print_text=ERROR_MENU;
					break;
			}
			menu();
	    	choice = Integer.parseInt(stdin.nextLine());
		}
		}while(choice<ADD && choice >QUIT);
		
	}
	
	public static boolean checkDOB(String [] string){
		if(string.length>=3){
			for(String str:string){
		    	if(str.matches("\\d+")==false){
		    		return false;
		    	}
		    }
			int day = Integer.parseInt(string[0]);
			int month =Integer.parseInt(string[1]);
			int year =Integer.parseInt(string[2]);
			if((day<= 0 && day>=31) || (month<= 0 && month>=12)|| (year> Calendar.getInstance().get(Calendar.YEAR) && year < 1920)){
				return false;
			}
			return true;
		}
		return false;
	}
	private static void initialiseAddressBook(Address_Book contact_list, List<String> lines) {
		for(String contacts: lines){
			String [] contact = contacts.split(",");
			contact_list.addContact(new Contact(contact[0],contact[1],contact[2],contact[3],contact[4],contact[5],contact[6],contact[7]));
		}
	}
	
	public static void fillFields(Scanner stdin){
		String splited = " ";
		String [] split_string= null;
		// Keep asking user until a proper full name is provided
		print_text = ASK_NAME;
		do {
		    System.out.println(print_text);
		    splited = stdin.nextLine();
		    split_string =splited.split(" ");
		    print_text = ERROR_ASK_NAME;
		} while (split_string.length<2);
	
		// Keep asking user until valid birth date is provided
		First_Name = split_string[0];
		Last_Name = split_string[1];
		print_text = ASK_DOB;
		do {
		    System.out.println(print_text);
		    Birth_Date = stdin.nextLine();
		    split_string =Birth_Date.split("\\\\");
		    print_text = ERROR_ASK_DOB;
		} while (!checkDOB(split_string));
		
		// Keep asking user until a valid phone number is provided
		print_text = ASK_PNUM;
		do {
		    System.out.println(print_text);
		    Phone_Num = stdin.nextLine();
		    print_text = ERROR_ASK_PNUM;
		} while (Integer.parseInt(Phone_Num)<100000000);
		Phone_Num = "+353" + Phone_Num.substring(1);
		
		// Keep asking user until a valid mobile number is provided
		print_text = ASK_MNUM;
		do {
		    System.out.println(print_text);
		    Mobile_Num = stdin.nextLine();
		    print_text = ERROR_ASK_MNUM;
		} while (Integer.parseInt(Mobile_Num)<800000000);
		
		// Keep asking user until a valid address is provided
		print_text = ASK_ADDRESS;
		do {
		    System.out.println(print_text);
		    Address = stdin.nextLine();
		    split_string =Address.split(",");
		    print_text = ERROR_ASK_ADDRESS;
		} while (split_string.length<2);
		
		Address = split_string[0];
	    City = split_string[1];
	    County = split_string[2];
	}

	public static void menu() {
		System.out.println(MENU_ASK);
		System.out.println("1.Add a new contact to your address book.");
		System.out.println("2.Delete a contact from your address book.");
		System.out.println("3.Edit a contact from your address book");
		System.out.println("4.List all contacts by last or first name");
		System.out.println("5.Display information of a specific contact");
		System.out.println("6.Quit.");
    }
}
