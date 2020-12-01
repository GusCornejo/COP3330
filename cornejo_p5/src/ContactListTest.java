import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactListTest {

    @Test
    public void newListIsEmpty() {
        ContactList contactList = new ContactList();
        assertTrue(contactList.isListEmpty());
    }

    @Test
    public void sizeMethodWorks() {
        ContactList contactList = new ContactList();
        ContactItem contactItem1 = new ContactItem("Size Test 1", "","","");
        ContactItem contactItem2 = new ContactItem("Size Test 2", "","","");
        contactList.add(contactItem1);
        assertEquals(1, contactList.size());
        contactList.add(contactItem2);
        assertEquals(2,contactList.size());
        assertNotEquals(3, contactList.size());
    }

    @Test
    public void addingContactIncreasesListSize() {
        ContactList contactList = new ContactList();
        assertEquals(0, contactList.size());
        ContactItem contactItem = new ContactItem("Size Test","","","sizetest@gmail.com");
        contactList.add(contactItem);
        assertEquals(1, contactList.size());
    }

    @Test
    public void editingContactFailsWithInvalidIndex() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Edit Test","","","edittest@gmail.com");
        contactList.add(contactItem);
        assertThrows(IndexOutOfBoundsException.class, () -> contactList.editContact(1, "New Edit Test","","", "edittest@gmail.com"));
    }

    @Test
    public void editingContactFailsWithBlankValues() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Edit Test 2","","","edittest@gmail.com");
        contactList.add(contactItem);
        assertThrows(InvalidContactException.class, () -> contactList.editContact(0, "","","", ""));
    }

    @Test
    public void editingAContactChangesItsInfo() {
        String fullFirstName = "Matthew";
        String lastName = "Harris";
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Matt","","525025223", "");
        contactList.add(contactItem);
        assertEquals("Matt", contactList.getContactFirstName(0));
        assertEquals("", contactList.getContactLastName(0));
        contactList.editContact(0, fullFirstName, lastName, "525025223", "");
        assertEquals("Matthew", contactList.getContactFirstName(0));
        assertEquals("Harris", contactList.getContactLastName(0));
    }

    @Test
    public void editSucceedsWithAtLeastOneNonBlankValue() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("FirstName", "LastName", "0000000000", "test@hotmail.com");
        contactList.add(contactItem);
        assertDoesNotThrow(() -> contactList.editContact(0,"","","","test@hotmail.com"));
    }

    @Test
    public void removingContactDecreasesListSize() {
        ContactList contactList = new ContactList();
        ContactItem contactItem1 = new ContactItem("Item 1","","","");
        ContactItem contactItem2 = new ContactItem("Item 2","","","");
        contactList.add(contactItem1);
        contactList.add(contactItem2);
        assertEquals(2, contactList.size());
        contactList.delete(1);
        assertEquals(1, contactList.size());
    }

    @Test
    public void deleteContactFailsWithInvalidIndex() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Delete 1","","","");
        contactList.add(contactItem);
        assertThrows(IndexOutOfBoundsException.class, () -> contactList.delete(1));
    }

    @Test
    public void deleteContactSucceedsWithValidIndex(){
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Delete 2","","","");
        contactList.add(contactItem);
        assertDoesNotThrow(() -> contactList.delete(0));
    }

    @Test
    public void isIndexValidMethodReturnsTrueWhenIndexIsValid() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Index Test1","", "", "");
        contactList.add(contactItem);
        assertTrue(contactList.isIndexValid(0));
    }

    @Test
    public void isIndexValidMethodReturnsFalseWhenIndexIsInvalid() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Index Test2","", "", "");
        contactList.add(contactItem);
        assertFalse(contactList.isIndexValid(5));
    }

    @Test
    public void writeAndLoadContactListFromFileSucceeds() {
        ContactList contactList1 = new ContactList();
        ContactItem contactItem = new ContactItem("File Test","", "48572580208", "");
        contactList1.add(contactItem);
        assertDoesNotThrow(() -> contactList1.write("JUnitTest2.txt"));
        ContactList contactList2 = new ContactList();
        assertDoesNotThrow(() -> contactList2.loadList("JUnitTest2.txt"));
    }
}