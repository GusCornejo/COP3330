import org.junit.jupiter.api.Test;
import java.util.InputMismatchException;
import static org.junit.jupiter.api.Assertions.*;

public class TaskItemTest {

    @Test
    public void creatingTaskFailsWithInvalidDueDatePattern() {
        assertThrows(InvalidDateException.class, () -> new TaskItem("Pay bills", "", "2020.12.23"));
    }

    @Test
    public void creatingTaskFailsWhenNoDueDateIsGiven(){
        assertThrows(InputMismatchException.class, () -> new TaskItem("DateTest2", "", ""));
    }

    @Test
    public void creatingTaskFailsWithInvalidTitle(){
        assertThrows(InvalidTitleException.class, () -> new TaskItem("", "", "2020-12-30"));
    }

    @Test
    public void creatingTaskAcceptsDatesWithInvalidMonthsAndModifiesThem(){
        TaskItem task = new TaskItem("Weird month test", "", "2020-13-20");
        assertEquals("2021-01-20", task.getTaskDate());
    }

    @Test
    public void creatingTaskAcceptsDatesWithInvalidDaysAndModifiesThem(){
        TaskItem task = new TaskItem("Weird day test", "", "2020-08-32");
        assertEquals("2020-09-01", task.getTaskDate());
    }

    @Test
    public void creatingTaskSucceedsWithValidTitleAndDueDate(){
        assertDoesNotThrow(() -> new TaskItem("Take out trash", "Do it after 8pm", "2020-11-20"));
    }

    @Test
    public void setTitleSucceedsWithValidTitle(){
        TaskItem task = new TaskItem("Old title", "", "2020-11-20");
        assertDoesNotThrow(() -> task.setTitle("New title"));
    }

    @Test
    public void setTitleFailsWithInvalidTitle(){
        TaskItem task = new TaskItem("valid", "", "2022-11-04");
        assertThrows(InvalidTitleException.class, () -> task.setTitle(""));
    }

    @Test
    public void setDueDateSucceedsWithValidDueDate(){
        TaskItem task = new TaskItem("SetDateValid", "", "2021-03-21");
        assertDoesNotThrow(() -> task.setDueDate("2020-12-10"));
    }

    @Test
    public void setDueDateFailsWithInvalidDueDatePattern(){
        TaskItem task = new TaskItem("SetDateInvalid", "", "2020-03-21");
        assertThrows(InvalidDateException.class, () -> task.setDueDate("2021.02.13"));
    }

    @Test
    public void setDueDateFailsWhenNoDateIsGiven(){
        TaskItem task = new TaskItem("setDateInvalid2", "", "2020-12-31");
        assertThrows(InputMismatchException.class, () -> task.setDueDate(""));
    }
}