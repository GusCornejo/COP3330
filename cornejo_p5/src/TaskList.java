import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TaskList {

    List<TaskItem> taskList;

    protected TaskList() {
        taskList = new ArrayList<>();
    }

    protected void add(TaskItem taskitem) {
        taskList.add(taskitem);
    }

    protected void editTask(int index, String newTitle, String newDescription, String newDate) {

        taskList.get(index).setTitle(newTitle);
        taskList.get(index).setDescription(newDescription);
        taskList.get(index).setDueDate(newDate);
    }

    protected String getTaskTitle(int index) {
        return taskList.get(index).getTaskTitle();
    }

    protected String getTaskDescription(int index) {
        return taskList.get(index).getTaskDescription();
    }

    protected String getTaskDate(int index) {
        return taskList.get(index).getTaskDate();
    }

    protected Boolean getTaskStatus(int index) {
        return taskList.get(index).getCompletionStatus();
    }

    protected void delete(int index) {
        taskList.remove(index);
    }

    protected void insertMark(int index) {
        taskList.get(index).placeCheckMark();
    }

    protected void removeMark(int index){
        taskList.get(index).removeCheckMark();
    }

    protected int size() {
        return taskList.size();
    }

    protected boolean isListEmpty() {
        return taskList.size() == 0;
    }

    protected void printTaskList(){
        if (isListEmpty()) {
            System.out.println("List is empty. Please add a task first");
        } else {
            System.out.println("Current tasks");
            System.out.println("-------------");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d) %s: %s - %s\n", i + 1, getTaskDate(i), getTaskTitle(i), getTaskDescription(i));
            }
        }
    }

    protected void printCompletedTaskList() {
        System.out.println("Completed tasks:");
        for (int i = 0; i < size(); i++){
            if (getTaskStatus(i)) {
                System.out.printf("%d) %s: %s - %s\n", i + 1, getTaskDate(i), getTaskTitle(i), getTaskDescription(i));
            }
        }
    }

    protected void printUncompletedTaskList(){
        System.out.println("Uncompleted tasks:");
        for (int i = 0; i < size(); i++){
            if (!getTaskStatus(i)) {
                System.out.printf("%d) %s: %s - %s\n", i + 1, getTaskDate(i), getTaskTitle(i), getTaskDescription(i));
            }
        }
    }

    protected void write(String fileName) {
        while (true) {
            try (Formatter output = new Formatter(fileName)) {
                for (TaskItem taskItem : taskList) {
                    output.format("%s | %s | %s\n", taskItem.getTaskDate(), taskItem.getTaskTitle(), taskItem.getTaskDescription());
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

    protected void loadList(String fileName) {
        while (true) {
            try {
                File input = new File(fileName);
                Scanner reader = new Scanner(input);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] elements = line.split(" \\| ");
                    if (elements.length == 3) {
                        TaskItem taskItem = new TaskItem(elements[1], elements[2], elements[0]);
                        add(taskItem);
                    } else if (elements.length == 2) {
                        TaskItem taskItem = new TaskItem(elements[1], "", elements[0]);
                        add(taskItem);
                    }
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

    protected void displayTaskListMenu(){
        System.out.println("-------------------------");
        System.out.println("Tasks List Operation Menu");
        System.out.println("-------------------------");
        System.out.println("1. Display the list");
        System.out.println("2. Add an item");
        System.out.println("3. Edit an item");
        System.out.println("4. Remove an item");
        System.out.println("5. Mark an item as completed");
        System.out.println("6. Unmark an item as completed");
        System.out.println("7. Save the current list");
        System.out.println("8. Quit");
        System.out.print("\n");
    }

    protected boolean isIndexValid(int index) {
        if (index < taskList.size()){
            return true;
        } else {
            System.err.println("The task selected does not exists");
            return false;
        }
    }

    private static String getFileName() {
        App app = new App();
        return app.getFileNameFromUser();
    }
}