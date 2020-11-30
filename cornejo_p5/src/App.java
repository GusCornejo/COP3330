import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    protected static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        appMenuExecutor();
    }

    private static void appMenuExecutor() {
        while (true) {
            displayAppsMenu();
            switch (getIntegerChoiceFromUser()) {
                case 1 -> TaskApp.taskMain();
                case 2 -> ContactApp.contactMain();
                case 3 -> {
                    System.out.println("---Thanks for using the program!---");
                    System.exit(0);
                }
                default -> System.err.println("Warning: your choice must be a number between 1 and 3");
            }
        }
    }

    private static void displayAppsMenu() {
        System.out.println("Select an App");
        System.out.println("-------------");
        System.out.println("1) Tasks List App");
        System.out.println("2) Contacts List App");
        System.out.println("3) Shut Down");
    }

    protected static int getIntegerChoiceFromUser(){
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

    protected int getIndexFromUser(){
        System.out.println("Please select an item");
        return getIntegerChoiceFromUser() - 1;
    }

    protected static void displayListMenu(){
        System.out.println("-------------");
        System.out.println("App Main Menu");
        System.out.println("-------------");
        System.out.println("1. Crate a new list");
        System.out.println("2. Load an existing list");
        System.out.println("3. Go to apps menu");
        System.out.print("\n");
    }

    protected String getFileNameFromUser(){
        System.out.println("Enter the file name (please include filename extension)");
        String name = input.nextLine();
        if (name.length() == 0){
            throw new InputMismatchException("Given name is empty");
        }
        return name;
    }

}

