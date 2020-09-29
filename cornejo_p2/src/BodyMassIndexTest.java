import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BodyMassIndexTest {

    @Test
    public void testConstructor(){
        BodyMassIndex constructTest1 = new BodyMassIndex(60, 100);
        BodyMassIndex constructTest2 = new BodyMassIndex(73, 170);
        assertNotSame(constructTest1.height, constructTest2.height);
        assertNotSame(constructTest1.weight, constructTest2.weight);
    }
    @Test
    public void testGetBmiMethod(){
        BodyMassIndex BmiTest = new BodyMassIndex(50.0, 137.0);
        assertEquals(38.5244, BmiTest.getBmi());//I calculated 38.5244 with a calculator
    }
    @Test
    public void testBmiCatMethod(){
        BodyMassIndex catMethodTest = new BodyMassIndex(10000, 10000);
        assertNotEquals("BMI category could not be found", catMethodTest.bmiCat());
        //bmiCat method should always print a category if integers are positive, which is the case
    }

    @Test
    public void testCatObesity(){
        BodyMassIndex BmiCatTest1 = new BodyMassIndex(45.0, 140.0); //BMI: 61.5
        assertEquals("Obesity", BmiCatTest1.bmiCat());
    }
    @Test
    public void testCatOverweight(){
        BodyMassIndex BmiCatTest2 = new BodyMassIndex(65.0, 155.0); //BMI: 25.8
        assertEquals("Overweight", BmiCatTest2.bmiCat());
    }
    @Test
    public void testCatNormalWeight(){
        BodyMassIndex BmiCatTest3 = new BodyMassIndex(68.0, 141.0); //BMI: 21.4
        assertEquals("Normal weight", BmiCatTest3.bmiCat());
    }
    @Test
    public void testCatUnderweight(){
        BodyMassIndex BmiCatTest4 = new BodyMassIndex(69.0, 115.0); //BMI: 17.0
        assertEquals("Underweight", BmiCatTest4.bmiCat());
    }

}