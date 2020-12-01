import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    public void newTaskListIsEmpty() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isListEmpty());
    }

    @Test
    public void sizeMethodWorks() {
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("Size1","","2020-3-4");
        TaskItem taskItem2 = new TaskItem("Size2","","2020-3-4");
        taskList.add(taskItem1);
        assertEquals(1,taskList.size());
        taskList.add(taskItem2);
        assertEquals(2, taskList.size());
        assertNotEquals(3, taskList.size());
    }

    @Test
    public void  addingTaskItemsToTaskListIncreasesItsSize() {
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("Test1", "", "2020-11-14");
        TaskItem taskItem2 = new TaskItem("Test2", "", "2020-11-14");
        taskList.add(taskItem1);
        taskList.add(taskItem2);
        assertEquals(2, taskList.size());
    }


    @Test
    public void editingTaskItemsChangesValues(){
        String newTitle = "New";
        String newDate = "2020-02-17";
        TaskList taskList = new TaskList();
        TaskItem taskItem = new TaskItem("Old", "", "2016-10-11");
        taskList.add(taskItem);
        taskList.editTask(0, newTitle, "No empty this time", newDate);

        assertEquals("New", taskItem.getTaskTitle());
        assertEquals("2020-02-17", taskItem.getTaskDate());
        assertNotEquals("", taskItem.getTaskDescription());
    }

    @Test
    public void editingTaskItemsChangesDate(){
        String oldDate = "2020-3-2";
        String newDate = "2022-03-17";
        TaskList taskList = new TaskList();
        TaskItem taskItem = new TaskItem("Title", "", oldDate);
        taskList.add(taskItem);

        taskList.editTask(0, "Title", "No empty this time", newDate);

        assertEquals(newDate, taskItem.getTaskDate());
        assertEquals("2022-03-17", taskItem.getTaskDate());
        assertNotEquals(oldDate, taskItem.getTaskDate());
    }

    @Test
    public void editingTaskItemsChangesTitle(){
        String oldTask = "Clean the house";
        String newTask = "Sell the house";
        TaskList taskList = new TaskList();
        TaskItem taskItem = new TaskItem(oldTask, "", "2020-2-12");
        taskList.add(taskItem);

        taskList.editTask(0, newTask, "No empty this time", "2020-2-3");

        assertEquals(newTask, taskItem.getTaskTitle());
        assertEquals("Sell the house", taskItem.getTaskTitle());
        assertNotEquals(oldTask, taskItem.getTaskTitle());
    }

    @Test
    public void markingATaskChangesItsStatus(){
        TaskList taskList = new TaskList();
        TaskItem taskItem = new TaskItem("StatusTest", "", "2020-3-4");
        taskList.add(taskItem);
        taskList.insertMark(0);
        assertTrue(taskItem.getCompletionStatus());
    }

    @Test
    public void unmarkingATaskChangesItsStatus(){
        TaskList taskList = new TaskList();
        TaskItem taskItem = new TaskItem("StatusTest2", "", "2020-3-9");
        taskList.add(taskItem);
        taskList.insertMark(0);
        assertTrue(taskItem.getCompletionStatus());
        taskList.removeMark(0);
        assertFalse(taskItem.getCompletionStatus());
    }

    @Test
    public void markCompletedMethodFailsWithInvalidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem = new TaskItem("StatusTest3", "", "2020-3-9");
        taskList.add(taskItem);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.insertMark(5));
    }

    @Test
    public void removeMarkMethodFailsWithInvalidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem = new TaskItem("StatusTest4", "", "2020-3-9");
        taskList.add(taskItem);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.removeMark(10));
    }

    @Test
    public void editingTaskFailsWithInvalidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem = new TaskItem("IndexTest", "", "2018-10-11");
        taskList.add(taskItem);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.editTask(5, "IndexTest", "", "2018-12-11"));
    }

    @Test
    public void isIndexValidMethodReturnsFalseWhenIndexIsOutOfBounds(){
        TaskList taskList = new TaskList();
        TaskItem taskItem = new TaskItem("IndexTest2", "", "2020-08-11");
        taskList.add(taskItem);
        assertFalse(taskList.isIndexValid(2));
    }
    @Test
    public void isIndexValidMethodReturnsTrueWhenIndexIsValid(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("IndexTest3", "", "2020-08-11");
        TaskItem taskItem2 = new TaskItem("IndexTest4", "", "2020-09-11");
        taskList.add(taskItem1);
        taskList.add(taskItem2);
        assertTrue(taskList.isIndexValid(1));
    }

    @Test
    public void deletingTaskDecreasesListSize(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("SizeTest1", "", "2020-08-11");
        TaskItem taskItem2 = new TaskItem("SizeTest2", "", "2020-09-11");
        taskList.add(taskItem1);
        taskList.add(taskItem2);
        assertEquals(2, taskList.size());
        taskList.delete(0);
        assertEquals(1, taskList.size());
    }

    @Test
    public void deleteTaskSucceedsWithValidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("DeleteTest4", "", "2020-08-11");
        taskList.add(taskItem1);
        assertDoesNotThrow(() -> taskList.delete(0));
    }

    @Test
    public void deleteTaskFailsWithInvalidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("DeleteTest1", "", "2020-08-11");
        taskList.add(taskItem1);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.delete(1));
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("GetSucceeds1", "", "2020-08-11");
        taskList.add(taskItem1);
        assertDoesNotThrow(() -> taskList.getTaskTitle(0));
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("GetSucceeds2", "", "2020-08-11");
        TaskItem taskItem2 = new TaskItem("GetSucceeds3", "", "2020-08-11");
        TaskItem taskItem3 = new TaskItem("GetSucceeds4", "", "2020-08-11");
        taskList.add(taskItem1);
        taskList.add(taskItem2);
        taskList.add(taskItem3);
        assertDoesNotThrow(() -> taskList.getTaskDescription(2));
    }

    @Test
    public void gettingTaskItemDateSucceedsWithValidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("GetSucceeds5", "", "2020-08-11");
        TaskItem taskItem2 = new TaskItem("GetSucceeds6", "", "2020-08-11");
        taskList.add(taskItem1);
        taskList.add(taskItem2);
        assertDoesNotThrow(() -> taskList.getTaskDescription(1));
    }


    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("GetFails1", "", "2020-08-11");
        taskList.add(taskItem1);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTaskTitle(5));
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("GetFails2", "description", "2020-08-11");
        TaskItem taskItem2 = new TaskItem("GetFails3", "description", "2020-08-11");
        taskList.add(taskItem1);
        taskList.add(taskItem2);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTaskDescription(2));
    }

    @Test
    public void gettingTaskItemDateFailsWithInvalidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("GetFails4", "description", "2021-8-11");
        taskList.add(taskItem1);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTaskDate(3));
    }

    @Test
    public void gettingTaskItemStatusFailsWithInvalidIndex(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("GetFails5", "description", "2020-08-11");
        taskList.add(taskItem1);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTaskStatus(1));
    }

    @Test
    public void writeAndLoadTaskListFromFileSucceeds(){
        TaskList taskList1 = new TaskList();
        TaskItem taskItem = new TaskItem("GetFails5", "description", "2020-08-11");
        taskList1.add(taskItem);
        assertDoesNotThrow(() -> taskList1.write("JUnitTest.txt"));
        TaskList taskList2 = new TaskList();
        assertDoesNotThrow(() -> taskList2.loadList("JUnitTest.txt"));
    }

    @Test
    public void isListEmptyReturnsTrueIfListIsEmpty(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("EmptyPasses", "description", "2020-08-11");
        taskList.add(taskItem1);
        taskList.delete(0);
        assertTrue(taskList::isListEmpty);
    }

    @Test
    public void isListEmptyReturnsFalseIfListIsNotEmpty(){
        TaskList taskList = new TaskList();
        TaskItem taskItem1 = new TaskItem("EmptyFails", "description", "2020-08-11");
        taskList.add(taskItem1);
        assertFalse(taskList::isListEmpty);
    }

}