import java.util.InputMismatchException;

public class TaskApp extends App {

    private TaskList taskList;

    private void tasksMenuExecutor() {
        boolean exitTasksApp = false;
        while (!exitTasksApp) {
            displayListMenu();
            switch (getIntegerChoiceFromUser()){
                case 1 -> {
                    createNewTaskList();
                    taskListOperationMenuExecutor();
                }
                case 2 -> {
                    createNewTaskList();
                    taskList.loadList(getFileNameFromUser());
                    taskListOperationMenuExecutor();
                }
                case 3 -> exitTasksApp = true;
                default -> System.err.println("Please enter a number from 1 to 3");
            }
        }
    }

    private void taskListOperationMenuExecutor() {
        boolean exitOperationMenu = false;
        while (!exitOperationMenu) {
            taskList.displayTaskListMenu();
            switch (getIntegerChoiceFromUser()) {
                case 1 -> taskList.printTaskList();
                case 2 -> addTask();
                case 3 -> editTask();
                case 4 -> deleteTask();
                case 5 -> markCompletedOperation();
                case 6 -> removeMarkOperation();
                case 7 -> saveTaskList();
                case 8 -> exitOperationMenu = true;
                default -> System.err.println("Please enter a number from 1 to 8");
            }
        }
    }

    private void saveTaskList() {
        if (!taskList.isListEmpty()) {
            taskList.write(getFileNameFromUser());
        } else {
            System.err.println("List is empty. Please add a task first");
        }
    }

    protected void markCompletedOperation() {
        if (taskList.isListEmpty()) {
            System.err.println("Warning: cannot edit anything because list is empty");
        } else {
            while (true) {
                taskList.printUncompletedTaskList();
                int index = getIndexFromUser();
                if (taskList.isIndexValid(index)) {
                    taskList.insertMark(index);
                    break;
                }
            }
        }
    }

    protected void removeMarkOperation() {
        if (taskList.isListEmpty()) {
            System.out.println("List is empty");
        } else {
            while (true) {
                taskList.printCompletedTaskList();
                int index = getIndexFromUser();
                if (taskList.isIndexValid(index)) {
                    taskList.removeMark(index);
                    break;
                }
            }
        }
    }

    private void createNewTaskList() {
        taskList = new TaskList();
        System.out.println("A new task list has been created");
    }

    private void deleteTask() {
        if (taskList.isListEmpty()){
            System.err.println("Warning: cannot delete anything because list is empty");
        } else {
            while (true) {
                taskList.printTaskList();
                int index = getIndexFromUser();
                if (taskList.isIndexValid(index)) {
                    taskList.delete(index);
                    System.out.println("Task was successfully deleted");
                    break;
                }
            }
            taskList.printTaskList();
        }
    }

    private void addTask() {
        TaskItem taskItem = getTaskItem();
        taskList.add(taskItem);
    }

    private void editTask() {
        if (taskList.isListEmpty()){
            System.err.println("Warning: cannot edit anything because list is empty");
        } else {
            while (true) {
                taskList.printTaskList();
                int index = getIndexFromUser();
                if (taskList.isIndexValid(index)) {
                    try {
                        String newTitle = getTitleFromUser();
                        String newDescription = getDescriptionFromUser();
                        String newDate = getDateFromUser();
                        taskList.editTask(index, newTitle, newDescription, newDate);
                        break;
                    } catch (InvalidTitleException e) {
                        System.err.println("Warning: a title must be provided. Please enter the task again.");
                    } catch (InvalidDateException e) {
                        System.err.println("Warning: use YYYY-MM-DD for date. Please enter the task again.");
                    } catch (InputMismatchException e) {
                        System.err.println("Warning: due date must be provided. Please enter the task again.");
                    }
                }
            }
        }
    }

    protected static TaskItem getTaskItem() {
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

    protected static void taskMain() {
        TaskApp taskApp = new TaskApp();
        taskApp.tasksMenuExecutor();
    }
}