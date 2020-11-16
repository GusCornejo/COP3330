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
                    createNewList();
                    taskList.loadList(getFileName());
                    ListOperationMenuExecutor();
                }
                case 3 -> {
                    System.out.println("---Thanks for using the program---");
                    System.exit(0);
                }
                default -> System.err.println("Please enter a number from 1 to 3");
            }
        }
    }

    private void ListOperationMenuExecutor() {
        boolean exitOperationMenu = false;
        while (!exitOperationMenu) {
            taskList.displayTaskListMenu();
            switch (getChoice()) {
                case 1 -> taskList.printList();
                case 2 -> addTask();
                case 3 -> editTask();
                case 4 -> deleteTask();
                case 5 -> markTask();
                case 6 -> unmarkTask();
                case 7 -> taskList.write(getFileName());
                case 8 -> exitOperationMenu = true;
                default -> System.err.println("Please enter a number from 1 to 8");
            }
        }
    }

    private void createNewList() {
        taskList = new TaskList();
        System.out.println("A new list has been created");
    }

    private void markTask() {
        while (true) {
            taskList.printUncompletedTaskList();
            int index = getIndex();
            if (taskList.isIndexValid(index)) {
                taskList.markCompleted(index);
                break;
            }
        }
        taskList.printList();
    }

    private void unmarkTask() {
        while (true) {
            taskList.printCompletedTaskList();
            int index = getIndex();
            if (taskList.isIndexValid(index)) {
                taskList.removeMark(index);
                break;
            }
        }
        taskList.printList();
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
        taskList.printList();
    }

    private void addTask() {
        TaskItem taskItem = getTaskItem();
        taskList.add(taskItem);
    }

    private void editTask() {
        while (true) {
            taskList.printList();
            int index = getIndex();
            if (taskList.isIndexValid(index)) {
                try {
                    String newTitle = getTitleFromUser();
                    String newDescription = getDescriptionFromUser();
                    String newDate = getDateFromUser();
                    taskList.edit(index, newTitle, newDescription, newDate);
                    break;
                } catch (InvalidTitleException e){
                    System.err.println("Warning: a title must be provided. Please enter the task again.");
                } catch (InvalidDateException e){
                    System.err.println("Warning: use YYYY-MM-DD for date. Please enter the task again.");
                } catch (InputMismatchException e) {
                    System.err.println("Warning: due date must be provided. Please enter the task again.");
                }
            }
        }
    }

    private static int getIndex(){
        System.out.println("Please select a task");
        return getChoice() - 1;
    }

    private static TaskItem getTaskItem() {
        TaskItem taskItem;
        while (true){
            try {
                String title = getTitleFromUser();
                String description = getDescriptionFromUser();
                String date = getDateFromUser();
                taskItem = new TaskItem(title, description, date);
                break;
            } catch (InvalidTitleException e) {
                System.err.println("Warning: a title must be provided. Please enter the task again.");
            } catch (InvalidDateException e) {
                System.err.println("Warning: use YYYY-MM-DD for date. Please enter the task again.");
            } catch (InputMismatchException e) {
                System.err.println("Warning: due date must be provided. Please enter the task again.");
            }
        }
        return taskItem;
    }

    private static String getDateFromUser() {
        System.out.println("Please enter a date (use format YYYY-MM-DD)");
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
    public String getFileName(){
        System.out.println("Enter the file name (please include filename extension)");
        String name = input.nextLine();
        if (name.length() == 0){
            throw new InputMismatchException("Given name is empty");
        }
        return name;
    }

    public static void main(String[] args) {
        App m = new App();
        m.mainMenuExecutor();
    }
}
