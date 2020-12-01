public class ContactApp extends App {

    private ContactList contactList;

     private void contactMenuExecutor() {
         boolean exitContactsApp = false;
         while (!exitContactsApp) {
             displayListMenu();
             switch (getIntegerChoiceFromUser()){
                 case 1 -> {
                     createNewContactList();
                     contactListOperationMenuExecutor();
                 }
                 case 2 -> {
                     createNewContactList();
                     contactList.loadList(getFileNameFromUser());
                     contactListOperationMenuExecutor();
                 }
                 case 3 -> exitContactsApp = true;
                 default -> System.err.println("Please enter a number from 1 to 3");
             }
         }
     }

    private void contactListOperationMenuExecutor() {
        boolean exitOperationMenu = false;
        while (!exitOperationMenu) {
            contactList.displayContactListMenu();
            switch (getIntegerChoiceFromUser()) {
                case 1 -> contactList.printContactList();
                case 2 -> addContact();
                case 3 -> editContact();
                case 4 -> deleteContact();
                case 5 -> saveContactList();
                case 6 -> exitOperationMenu = true;
                default -> System.err.println("Please enter a number from 1 to 6");
            }
        }
    }

    private void saveContactList() {
         if (!contactList.isListEmpty()) {
             contactList.write(getFileNameFromUser());
         } else {
             System.err.println("List is empty. Please add a contact first");
         }
    }

    private void deleteContact() {
         while (true) {
             if (contactList.isListEmpty()) {
                 System.err.println("Warning: cannot edit anything because list is empty");
                 break;
             }
             contactList.printContactList();
             int index = getIndexFromUser();
             if (contactList.isIndexValid(index)) {
                 contactList.delete(index);
                 System.out.println("Contact was successfully deleted");
                 break;
             }
         }
    }

    private void editContact() {
        if (contactList.isListEmpty()) {
            System.err.println("Warning: cannot edit anything because list is empty");
        } else {
            while (true) {
                contactList.printContactList();
                int index = getIndexFromUser();
                if (contactList.isIndexValid(index)) {
                    try {
                        String newFirstName = getFirstNameFromUser();
                        String newLastName = getLastNameFromUser();
                        String newPhoneNumber = getPhoneNumberFromUser();
                        String newEmailAddress = getEmailAddressFromUser();
                        contactList.editContact(index, newFirstName, newLastName, newPhoneNumber, newEmailAddress);
                        break;
                    } catch (InvalidContactException e) {
                        System.err.println("Warning: At least one piece of information must be provided");
                    }
                }
            }
        }
    }

    private void addContact() {
         ContactItem contactItem = getContactItem();
         contactList.add(contactItem);
    }

    private ContactItem getContactItem() {
         ContactItem contactItem;
         while (true) {
             try {
                 String firstName = getFirstNameFromUser();
                 String lastName = getLastNameFromUser();
                 String phoneNumber = getPhoneNumberFromUser();
                 String emailAddress = getEmailAddressFromUser();
                 contactItem = new ContactItem(firstName, lastName, phoneNumber, emailAddress);
                 break;
             } catch (InvalidContactException e) {
                 System.err.println("Warning: At least one piece of information must be provided");
             }
         }
         return contactItem;
    }

    private static String getFirstNameFromUser() {
         System.out.println("Please enter the first name");
         return input.nextLine();
     }

     private static String getLastNameFromUser() {
         System.out.println("Please enter the last name");
         return input.nextLine();
     }

     private static String getPhoneNumberFromUser() {
         System.out.println("Please enter the phone number");
         return input.nextLine();
     }

     private static String getEmailAddressFromUser() {
         System.out.println("Please enter the email address");
         return input.nextLine();
     }

    private void createNewContactList() {
         contactList = new ContactList();
         System.out.println("A new contact list has been created");
    }

    protected static void contactMain() {
        ContactApp contactApp = new ContactApp();
        contactApp.contactMenuExecutor();
    }

}