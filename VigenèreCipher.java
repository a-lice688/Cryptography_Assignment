import java.util.*; 
import java.io.*;

public class VigenèreCipher {

    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);

        while (true) {
            
            int choice = -1;
            
            while (true) {
                System.out.print("Choose 1 to encode, 2 to decode, 3 to see letter distribution, or 999 to quit: ");
                
                try {
                    choice = s.nextInt();
                    s.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    s.nextLine();
                }
            }
            
            if (choice == 999) {
                System.out.println("Bye, loser!");
                break;
            }

            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            System.out.print("Enter the message: ");
            String inputString = s.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter the keyword: ");
                String keyword = s.nextLine();
                String encoded = encode(inputString, keyword);
                System.out.println("Encoded message: " + encoded);
            } else if (choice == 2) {
                System.out.print("Enter the keyword: ");
                String keyword = s.nextLine();
                String decoded = decode(inputString, keyword);
                System.out.println("Decoded message: " + decoded);
            } else {
                String decoded = frequencyAnalysis(inputString);
                System.out.println("Decoded message: " + decoded);
            }
            
        
        }

    }
    
    public static String encode(String plaintext, String keyword) {
        
        String result = "";
        keyword = keyword.toUpperCase();
        int index = 0;
    
        for (int i = 0; i < plaintext.length(); i++) {
            
            char originalChar = plaintext.charAt(i);
    
            if (Character.isLetter(originalChar)) {
                
                char base;
                if (Character.isUpperCase(originalChar)) base = 'A';
                else base = 'a';
                
                int shift = keyword.charAt(index % keyword.length()) - 'A';
                
                char encrypted = (char)(((originalChar - base + shift) % 26) + base);
                
                result += encrypted;
                
                index++;
                
            } else result += originalChar;
            
        }
    
        return result;
    }
    
        public static String decode(String ciphertext, String keyword) {
            
        String result = "";
        keyword = keyword.toUpperCase();
        int index = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char originalChar = ciphertext.charAt(i);

            if (Character.isLetter(originalChar)) {
                
                char base;
                if (Character.isUpperCase(originalChar)) base = 'A';
                else base = 'a';
                
                int shift = keyword.charAt(index % keyword.length()) - 'A';
                
                char decrypted = (char)(((originalChar - base - shift + 26) % 26) + base);
                
                result += decrypted;
                
                index++;
                
            } else result += originalChar;
        
        }

        return result;
    }
    
    public static String frequencyAnalysis (String cipherText) {
        
        String result = "";
        int[] histogram = new int[26]; 
        String englishFreq = "etaoinshrdlucmfwygpbvkxqjz";
        int totalLetters = 0;
        
        for (int i = 0; i < cipherText.length(); i++) {
            
            char ch = Character.toLowerCase(cipherText.charAt(i)); 
            
            if (ch >= 'a' && ch <= 'z') {
                
                histogram[ch - 'a']++;
                
                totalLetters++;
            
            }   
            
        }
        
        char[] letters = new char[26];
        int[] frequencies = new int[26];

        for (int i = 0; i < 26; i++) {
            
            letters[i] = (char) ('a' + i);
            
            frequencies[i] = histogram[i];
            
        }
        
        //selection sort 
        for (int i = 0; i < 25; i++) { 
            
            int maxIndex = i;
            
            for (int j = i + 1; j < 26; j++) {
                
                if (frequencies[j] > frequencies[maxIndex]) {
                    maxIndex = j;
                }
            }
        
            int tempFreq = frequencies[i];
            frequencies[i] = frequencies[maxIndex];
            frequencies[maxIndex] = tempFreq;
            
            char tempLetter = letters[i];
            letters[i] = letters[maxIndex];
            letters[maxIndex] = tempLetter;
        }
        
        for (int i = 0; i < 26; i++) {
            double percent;
            if (totalLetters == 0) {
                percent = 0.0;
            } else {
                percent = (frequencies[i] * 100.0) / totalLetters;
            }
        
            result += letters[i] + ": " + frequencies[i] + " (" + String.format("%.2f", percent) + "%)\n";
        }
        
        
        return result;
        
    }
}
