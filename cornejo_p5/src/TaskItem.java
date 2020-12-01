import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class TaskItem {

    private String title;
    private String description;
    private String dueDate;
    private boolean isCompleted;
    private final char checkMark = '√';


    protected TaskItem(String title, String description, String dueDate) {

        if (isTitleValid(title)) {
            this.title = title;
        } else {
            throw new InvalidTitleException("Invalid title: title must be at least one character long");
        }
        this.description = description;
        if (isDateValid(dueDate)) {
            this.dueDate = convertDateToString(convertStringToDate(dueDate));
        }
    }

    private boolean isDateValid(String date) {
        if (!isDueDateGiven(date)){
            throw new InputMismatchException("No date was given");
        }
        if (convertStringToDate(date) == null) {
            throw new InvalidDateException("Invalid date pattern");
        }
        return true;
    }

    private Date convertStringToDate(String stringDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(stringDate);
        } catch (ParseException e) {
            System.err.println("Invalid Pattern");
        }
        return null;
    }

    private String convertDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    private boolean isTitleValid(String title) {
        return title.length() > 0;
    }

    private boolean isDueDateGiven(String date){
        return date.length() >= 6 && date.length() <= 10;
    }

    protected String getTaskTitle() {
        return title;
    }

    protected String getTaskDescription() {
        return description;
    }

    protected String getTaskDate() {
        return dueDate;
    }

    protected boolean getCompletionStatus() {
        return isCompleted;
    }

    protected void placeCheckMark(){
        if (description.length() == 0 || description.charAt(description.length() - 1) != checkMark) {
            description = description + " " + checkMark;
            isCompleted = true;
        } else {
            System.err.println("This task was already checked completed");
        }
    }

    protected void removeCheckMark(){
        try {
            description = description.replace(checkMark,' ');
            isCompleted = false;
        } catch (NoSuchElementException e) {
            System.err.println("The task selected was not checked completed");
        }
    }

    protected void setTitle(String title) {
        if (isTitleValid(title)) {
            this.title = title;
        } else {
            throw new InvalidTitleException("INVALID TITLE: title must be at least one character long");
        }
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setDueDate(String dueDate) {

        if (isDateValid(dueDate)){
            this.dueDate = convertDateToString(convertStringToDate(dueDate));
        }
    }
}

class InvalidTitleException extends IllegalArgumentException {
    public InvalidTitleException(String msg) {
        super(msg);
    }
}
class InvalidDateException extends IllegalArgumentException {
    public InvalidDateException(String msg) {
        super(msg);
    }
}