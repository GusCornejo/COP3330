public class Decrypter{

    String decrypt;
    String result1;
    String result2;
    String result3;
    String result4;

    public Decrypter(String decrypt){

        this.decrypt = decryptFunction(decrypt);

    }

    public String decryptFunction(String decrypt){

        int[] stringToArray = new int[decrypt.length()];

        for (int i = 0; i < decrypt.length(); i++){
            stringToArray[i] = Character.digit(decrypt.charAt(i), 10);
        }

        int temp1;
        int temp2;

        stringToArray[0] = ( 10 + stringToArray[0]) - 7;
        stringToArray[1] = ( 10 + stringToArray[1]) - 7;
        stringToArray[2] = ( 10 + stringToArray[2]) - 7;
        stringToArray[3] = ( 10 + stringToArray[3]) - 7;

        for (int j = 0; j < 4; j++){
            if (stringToArray[j] > 9){
                stringToArray[j] -= 10;
            }
        }

        temp1 = stringToArray[0];
        temp2 = stringToArray[1];
        stringToArray[0] = stringToArray[2];
        stringToArray[1] = stringToArray[3];
        stringToArray[2] = temp1;
        stringToArray[3] = temp2;

        this.result1 = Integer.toString(stringToArray[0]);
        this.result2 = Integer.toString(stringToArray[1]);
        this.result3 = Integer.toString(stringToArray[2]);
        this.result4 = Integer.toString(stringToArray[3]);

        return toString();
    }

    public String toString(){

        return result1 + result2 + result3 + result4;

    }

}
