import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ContactList {

    List<ContactItem> contactList;

    protected ContactList() {

        contactList = new ArrayList<>();
    }

    protected void editContact(int index, String newFirstName, String newLastName, String newPhoneNumber, String newEmailAddress) {
        contactList.get(index).setContact(newFirstName, newLastName, newPhoneNumber, newEmailAddress);
    }

    protected void delete(int index) {
        contactList.remove(index);
    }

    protected String getContactEmailAddress(int index) {
        return contactList.get(index).getEmailAddress();
    }

    protected String getPhoneNumber(int index) {
        return contactList.get(index).getPhoneNumber();
    }

    protected String getContactLastName(int index) {
        return contactList.get(index).getLastName();
    }

    protected String getContactFirstName(int index) {
        return contactList.get(index).getFirstName();
    }

    protected void printContactList(){
        if (isListEmpty()) {
            System.out.println("List is empty. Please add a contact first");
        } else {
            System.out.println("List of Contacts");
            System.out.println("----------------");
            for (int i = 0; i < contactList.size(); i++) {
                System.out.printf("%d)\n", i + 1);
                System.out.printf("Name: %s %s \n", getContactFirstName(i), getContactLastName(i));
                System.out.printf("Phone number: %s\n", getPhoneNumber(i));
                System.out.printf("Email address: %s\n", getContactEmailAddress(i));
            }
        }
    }

    protected void displayContactListMenu(){
        System.out.println("----------------------------");
        System.out.println("Contacts List Operation Menu");
        System.out.println("----------------------------");
        System.out.println("1. Display the contact list");
        System.out.println("2. Add a contact");
        System.out.println("3. Edit an contact");
        System.out.println("4. Remove a contact");
        System.out.println("5. Save the current contact list");
        System.out.println("6. Quit");
        System.out.print("\n");
    }

    protected int size() {
        return contactList.size();
    }

    protected boolean isListEmpty() {
        return contactList.size() == 0;
    }

    protected void add(ContactItem contactItem) {
        contactList.add(contactItem);
    }

    public void write(String fileName) {
        while (true) {
            try (Formatter output = new Formatter(fileName)) {
                for (ContactItem contactItem : contactList) {
                    output.format("%s | %s | %s | %s\n", contactItem.getFirstName(), contactItem.getLastName(), contactItem.getPhoneNumber(), contactItem.getEmailAddress());
                }
                System.out.println("List was successfully saved");
                break;
            } catch (InputMismatchException e){
                System.err.println("Warning: no file name was given. Please try again");
            } catch (FileNotFoundException e) {
                System.err.println("Warning: unable to find the file. Please choose a different name");
            }
            fileName = getFileName();
        }
    }

    public void loadList(String fileName) {

        while (true) {
            try {
                File input = new File(fileName);
                Scanner reader = new Scanner(input);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] elements = line.split("\\|");
                    ContactItem contactItem = new ContactItem(elements[0], elements[1], elements[2], elements[3]);
                    add(contactItem);
                }
                System.out.println("List was successfully loaded");
                break;
            } catch (FileNotFoundException e) {
                System.err.println("Warning: file was not found. Try typing it again");
            } catch (InputMismatchException e) {
                System.err.println("Warning: no file name was given. Please try again");
            }
            fileName = getFileName();
        }
    }

    protected boolean isIndexValid(int index) {
        if (index < contactList.size()) {
            return true;
        } else {
            System.err.println("The contact selected does not exists");
            return false;
        }
    }

    private static String getFileName() {
        App app = new App();
        return app.getFileNameFromUser();
    }
}
