import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a 4-digit integer to encrypt");
        String input1 = scan.nextLine();
        System.out.println("Enter a 4-digit integer to decrypt");
        String input2 = scan.nextLine();

        Encrypter encrypted1 = new Encrypter(input1);
        System.out.println(encrypted1);
        Encrypter encrypted2 = new Encrypter(input2);
        System.out.println(encrypted2);


        Decrypter decrypted1 = new Decrypter(input1);
        System.out.println(decrypted1);
        Decrypter decrypted2 = new Decrypter(input2);
        System.out.println(decrypted2);
    }


}
