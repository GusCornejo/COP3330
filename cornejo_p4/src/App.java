import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static final Scanner input = new Scanner(System.in);
    private TaskList taskList;

    private void mainMenuExecutor() {
        while (true) {
            displayMainMenu();
            switch (getChoice()){
                case 1 -> {
                    createNewList();
                    ListOperationMenuExecutor();
                }
                case 2 -> {
                    getTaskItemsFromFile();
                    ListOperationMenuExecutor();
                }
                case 3 -> System.exit(0);
                default -> System.out.println("Please enter a number from 1 to 3");
            }
        }
    }

    private void getTaskItemsFromFile() {
        createNewList();
        while (true) {
            try {
                String fileName = getFileName();
                File input = new File(fileName);
                Scanner reader = new Scanner(input);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] elements = line.split("\\|");
                    if (elements.length == 3) {
                        TaskItem taskItem = new TaskItem(elements[1], elements[2], elements[0]);
                        storeTaskItem(taskItem);
                    } else if (elements.length == 2){
                        TaskItem taskItem = new TaskItem(elements[1], "", elements[0]);
                        storeTaskItem(taskItem);
                    }
                }
                break;
            } catch (FileNotFoundException e) {
                System.err.println("Warning: file was not found");
            }
        }
    }

    private void ListOperationMenuExecutor() {
        boolean exitOperationMenu = false;
        while (!exitOperationMenu) {
            displayListOperationMenu();
            switch (getChoice()) {
                case 1 -> displayTasksList();
                case 2 -> addTask();
                case 3 -> editTask();
                case 4 -> deleteTask();
                case 5 -> markTask();
                case 6 -> unmarkTask();
                case 7 -> writeTasks();
                case 8 -> exitOperationMenu = true;
                default -> System.out.println("Please enter a number from 1 to 8");
            }
        }
    }

    private void createNewList() {
        taskList = new TaskList();
        System.out.println("New task list has been created");
    }

    private void writeTasks() {
        String fileName = getFileName();
        taskList.write(fileName);
    }

    private String getFileName() {
        System.out.println("Enter the file name (please include filename extension)");
        return input.nextLine();
    }

    private void markTask() {
        while (true) {
            displayUncompletedTasksList();
            int index = getIndex();
            if (taskList.isIndexValid(index)) {
                taskList.markCompleted(index);
                break;
            }
        }
        displayTasksList();
    }

    private void displayUncompletedTasksList() {
        System.out.println("Uncompleted tasks:");
        taskList.printUncompletedTaskList();
    }

    private void unmarkTask() {
        displayCompletedTasksList();
        while (true) {
            int index = getIndex();
            if (taskList.isIndexValid(index)) {
                taskList.removeMark(index);
                break;
            }
        }
        displayTasksList();
    }

    private void displayCompletedTasksList() {
        System.out.println("Completed tasks:");
        taskList.printCompletedTaskList();
    }

    private void deleteTask() {
        while (true) {
            int index = getIndex();
            if (taskList.isIndexValid(index)) {
                taskList.delete(index);
                System.out.println("Task was successfully deleted");
                break;
            }
        }
        displayTasksList();
    }

    private void displayTasksList() {
        System.out.println("Current tasks:");
        System.out.println("--------------");
        taskList.printList();
    }

    private void addTask() {
        TaskItem taskItem = getTaskItem();
        storeTaskItem(taskItem);
    }

    private void storeTaskItem(TaskItem taskItem) {
        taskList.add(taskItem);
    }

    private void editTask() {

        while (true) {
            int index = getIndex();
            if (taskList.isIndexValid(index)) {
                try {
                    String newTitle = getTitleFromUser();
                    String newDescription = getDescriptionFromUser();
                    String newDueDate = getDateFromUser();
                    taskList.edit(index, newTitle, newDescription, newDueDate);
                    break;
                } catch (InvalidTitleException e){
                    System.err.println("Warning: title is invalid. Please enter it again.");
                } catch (InvalidDateException e){
                    System.err.println("Warning: due date cannot be in the past. Please enter it again.");
                } catch (InputMismatchException e) {
                    System.err.println("Warning: due date must be provided");
                }
            }
        }
    }

    private static int getIndex(){
        System.out.println("Enter a task number");
        return getChoice() - 1;
    }

    private TaskItem getTaskItem() {
        TaskItem taskItem;
        while (true){
            try {
                String tittle = getTitleFromUser();
                String description = getDescriptionFromUser();
                String dueDate = getDateFromUser();
                taskItem = new TaskItem(tittle, description, dueDate);
                break;
            } catch (InvalidTitleException e) {
                System.err.println("Warning: title is invalid. Please enter the task again.");
            } catch (InvalidDateException e) {
                System.err.println("Warning: use YYYY-MM-DD for date. Please enter the task again.");
            } catch (InputMismatchException e) {
                System.err.println("Warning: due date must be provided. Please enter the task again.");
            }
        }
        return taskItem;
    }

    private static String getDateFromUser() {
        System.out.println("Please enter a date (YYYY-MM-DD)");
        return input.nextLine();
    }

    private static String getDescriptionFromUser() {
        System.out.println("Please enter a description");
        return input.nextLine();
    }

    private static String getTitleFromUser() {
        System.out.println("Please enter a title");
        return input.nextLine();
    }

    private static void displayMainMenu(){
        System.out.println("---------");
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1. Crate a new list");
        System.out.println("2. Load an existing list");
        System.out.println("3. Quit");
        System.out.print("\n");
    }

    private static void displayListOperationMenu() {
        System.out.println("-------------------");
        System.out.println("List Operation Menu");
        System.out.println("-------------------");
        System.out.println("1. Display the list");
        System.out.println("2. Add an item");
        System.out.println("3. Edit an item");
        System.out.println("4. Remove an item");
        System.out.println("5. Mark an item as completed");
        System.out.println("6. Unmark an item as completed");
        System.out.println("7. Save the current list");
        System.out.println("8. Quit the main menu");
        System.out.print("\n");
    }

    private static int getChoice(){
        int result = 9999999;
        try {
            result = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("Warning: please enter integers only");
            input.nextLine();
        }
        return result;
    }

    public static void main(String[] args) {
        App m = new App();
        m.mainMenuExecutor();
    }

}
