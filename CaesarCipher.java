import java.util.*;

public class CaesarCipher 
{
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);

        while (true) {
            
            int choice = -1;
            
            while (true) {
                System.out.print("Choose 1 to encode, 2 to decode, or 999 to quit: ");
                
                try {
                    choice = s.nextInt();
                    s.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    s.nextLine();
                }

            if (choice == 999) {
                System.out.println("Bye, loser!");
                break;
            }

            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            System.out.print("Enter the message: ");
            String inputString = s.nextLine();

            System.out.print("Enter the key: ");
            int inputKey = s.nextInt();
            s.nextLine(); 

            if (choice == 1) {
                String encoded = encode(inputString, inputKey);
                System.out.println("Encoded message: " + encoded);
            } else {
                String decoded = decode(inputString, inputKey);
                System.out.println("Decoded message: " + decoded);
            }
            
        }
    }

    }


    public static String encode(String inputString, int key) {
        
        String result = "";
        
        for (int i = 0; i < inputString.length(); i++) {
            
            char originalChar = inputString.charAt(i);

            if (Character.isLetter(originalChar)) {
                
                char base;
                if (Character.isUpperCase(originalChar)) base = 'A';
                else base = 'a';
                
                char newChar = (char)(((originalChar - base + key) % 26 + 26) % 26 + base); // guards for neg key
            
                result += newChar;
                
            } else if (Character.isDigit(originalChar)) {
                
                char newDigit = (char)(((originalChar - '0' + key) % 10 + 10) % 10 + '0');
            
                result += newDigit;
                
            }
            
            else result += originalChar;
            
        }
        
        return result;
    }

    public static String decode(String cipherText, int key) {
        
        String result = "";

        for (int i = 0; i < cipherText.length(); i++) {

            char originalChar = cipherText.charAt(i);

            if (Character.isLetter(originalChar)) {

                char base;
                if (Character.isUpperCase(originalChar)) base = 'A';
                else base = 'a';

                char newChar = (char)(((originalChar - base - key) % 26 + 26) % 26 + base);

                result += newChar;

            } else if (Character.isDigit(originalChar)) {

                char newDigit = (char)(((originalChar - '0' - key) % 10 + 10) % 10 + '0');

                result += newDigit;

            } else result += originalChar;
            
        }

        return result;
    
    }


}
