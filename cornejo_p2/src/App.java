import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static final DecimalFormat numberFormat = new DecimalFormat("#.00");

    //main method
    public static void main(String[] args){

        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }
        displayBmiStatistics(bmiData);
    }

    private static void greeter(){
        System.out.println("Welcome to the BMI Calculator");
        System.out.println("If you'd like to calculate a BMI, type 'Y'. Otherwise, type 'N'");
    }

    public static String choiceInput(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static boolean moreInput(){
        greeter();
        String choice = choiceInput();
        if (choice.equals("Y") || choice.equals("y")){
            return true;
        } else if (choice.equals("N") || choice.equals("n")){
            return false;
        } else {
            System.out.println("Please type either 'Y' or 'N'");
        }   return moreInput();
    }

    public static double doubleInput(){
        Scanner scan = new Scanner(System.in);
        return scan.nextDouble();
    }

    public static double getUserHeight(){
        System.out.println("Please enter the height (in inches)");
        double height = doubleInput();
        if (height < 0){
            System.out.println("Please enter a positive value for height");
            return getUserHeight();
        }
        return height;
    }

    public static double getUserWeight(){
        System.out.println("Please enter the weight (in pounds)");
        double weight = doubleInput();
        if (weight < 0){
            System.out.println("Please enter a positive value for weight");
            return getUserWeight();
        }
        return weight;
    }

    public static void displayBmiInfo(BodyMassIndex bmi){
        System.out.println("BMI: " + numberFormat.format(bmi.getBmi()));
        System.out.println("Category: " + bmi.bmiCat());
        System.out.print("\n");
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        double bmiSum = 0;
        for (BodyMassIndex bmiDatum : bmiData) {
            bmiSum += bmiDatum.getBmi();
        }
        System.out.println("The BMI average is: " + numberFormat.format(bmiSum / bmiData.size()));
        System.out.println("Thanks for using this calculator!");
    }
}
