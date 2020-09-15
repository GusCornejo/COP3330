public class Encrypter{

    String encrypt;
    String result1;
    String result2;
    String result3;
    String result4;

    public Encrypter(String encrypt){

        this.encrypt = encryptFunction(encrypt);

    }

    public String encryptFunction(String encrypt){

        int[] stringToArray = new int[encrypt.length()];

        for (int i = 0; i < encrypt.length(); i++){
            stringToArray[i] = Character.digit(encrypt.charAt(i), 10);
        }

        int temp1;
        int temp2;

        stringToArray[0] = ( stringToArray[0] + 7) % 10;
        stringToArray[1] = ( stringToArray[1] + 7) % 10;
        stringToArray[2] = ( stringToArray[2] + 7) % 10;
        stringToArray[3] = ( stringToArray[3] + 7) % 10;

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

