import java.lang.Math;
public class BodyMassIndex {

    double height;
    double weight;

    //Constructor
    public BodyMassIndex(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public double getBmi(){
        return (703 * weight) / Math.pow(this.height, 2);
    }

    public String bmiCat(){
        if (getBmi() < 18.5 && getBmi() > 0){
            return "Underweight";
        }
        if (getBmi() >= 18.5 && getBmi() <= 24.9){
            return "Normal weight";
        }
        if (getBmi() > 24.9 && getBmi() <= 29.9) {
            return "Overweight";
        }
        if (getBmi() >= 30.0){
            return "Obesity";
        } else {
            return "BMI category could not be found";
        }
    }

}
