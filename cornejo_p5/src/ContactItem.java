public class ContactItem {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    protected ContactItem(String firstName, String lastName, String phoneNumber, String emailAddress) {

        if (isInputValid(firstName, lastName, phoneNumber, emailAddress)) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = formatPhoneNumber(phoneNumber);
            this.emailAddress = emailAddress;
        } else {
            throw new InvalidContactException("Invalid contact: none contact info was provided");
        }
    }

    protected boolean isInputValid(String firstName, String lastName, String phoneNumber, String emailAddress) {
        return  firstName.length() > 0 || lastName.length() > 0 || phoneNumber.length() > 0 || emailAddress.length() > 0;
    }

    private String formatPhoneNumber(String phoneNumber){
        return phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
    }

    protected String getEmailAddress() {
        return this.emailAddress;
    }

    protected String getPhoneNumber() {
        return this.phoneNumber;
    }

    protected String getLastName() {
        return this.lastName;
    }

    protected String getFirstName() {
        return this.firstName;
    }

    protected void setContact(String newFirstName, String newLastName, String newPhoneNumber, String newEmailAddress){
        if (isInputValid(newFirstName, newLastName, newPhoneNumber, newEmailAddress)) {
            this.firstName = newFirstName;
            this.lastName = newLastName;
            this.phoneNumber = newPhoneNumber;
            this.emailAddress = newEmailAddress;
        } else {
            throw new InvalidContactException("Invalid contact: none contact info was provided");
        }
    }
}

class InvalidContactException extends IllegalArgumentException {
    public InvalidContactException(String msg) {
        super(msg);
    }
}
