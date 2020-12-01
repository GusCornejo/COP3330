import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactItemTest {

    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(InvalidContactException.class, () -> new ContactItem("","","",""));
    }

    @Test
    public void creationSucceedsWithBlankEmail() {
        assertDoesNotThrow(() -> new ContactItem("Jaqueline", "Colmenares", "40784572345", ""));
    }

    @Test
    public void creationSucceedsWithBlankFirstName() {
        assertDoesNotThrow(() -> new ContactItem("", "Lewis", "40784572346", "rlewis@gmail.com"));
    }

    @Test
    public void creationSucceedsWithBlankLastName() {
        assertDoesNotThrow(() -> new ContactItem("Ronald", "", "3284585925", "rtest@yahoo.com"));
    }

    @Test
    public void creationSucceedsWithBlankPhone() {
        assertDoesNotThrow(() -> new ContactItem("Anna", "Marie", "", "annamarie@icloud.com"));
    }

    @Test
    public void creationSucceedsWithNonBlankValues() {
        assertDoesNotThrow(() -> new ContactItem("Adriana", "Cornejo", "4076558585", "adrianacornejorealtor@gmail.com"));
    }

    @Test
    public void setContactMethodSucceedsWithBlankFirstName() {
        ContactItem contactItem = new ContactItem("Gus", "Cornejo", "4076248457", "gustavocornejo@gmail.com");
        assertDoesNotThrow(() -> contactItem.setContact("","Cornejo","4076248457","gustavocornejo@gmail.com"));
    }

    @Test
    public void setContactMethodSucceedsWithBlankLastName() {
        ContactItem contactItem = new ContactItem("Gus", "Cornejo", "4076248457", "gustavocornejo@gmail.com");
        assertDoesNotThrow(() -> contactItem.setContact("Gustavo","","4076248457","gustavocornejo@gmail.com"));
    }

    @Test
    public void setContactMethodSucceedsWithBlankPhoneNumber() {
        ContactItem contactItem = new ContactItem("Gus", "Cornejo", "4076248457", "gustavocornejo@gmail.com");
        assertDoesNotThrow(() -> contactItem.setContact("Gustavo","Cornejo","","gustavocornejo@gmail.com"));
    }

    @Test
    public void setContactMethodSucceedsWithBlankEmail() {
        ContactItem contactItem = new ContactItem("Gus", "Cornejo", "4076248457", "gustavocornejo@gmail.com");
        assertDoesNotThrow(() -> contactItem.setContact("Gus","Cornejo","4076248457",""));
    }

    @Test
    public void setContactMethodFailsWithAllBlankValues() {
        ContactItem contactItem = new ContactItem("Edit Test", "", "", "");
        assertThrows(InvalidContactException.class, () -> contactItem.setContact("","","",""));
    }
}