import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Caesar {
    public static void main (String[] args) throws IOException{    
        /* Receive input arguments */
        char mode = args[0].charAt(0);
        String file_path = args[1];
        int key = Integer.parseInt(args[2]); // negative number = left shift, positive number = right shift
        // System.out.println("Arguments: " + mode + " " + file_path + " " + key);
        
        /* The original alphabet */
        String alphabet_s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
        List<Character> alphabet = new ArrayList<>();
        
        /* Store string alphabet_s to list */
        for (int j = 0; j < alphabet_s.length(); j++){
           // System.out.println(alphabet_s.charAt(j));
           alphabet.add(alphabet_s.charAt(j));
        }               
        // System.out.println(alphabet);         
        
        /* Make a new alphabet from the key */
        List<Character> alphabet_cipher = new ArrayList<>();
        for(int i = 0; i < alphabet.size(); i++){
            int n = i + key;
            if(n >= alphabet.size()){
                n = n - alphabet.size();
            }
            alphabet_cipher.add(alphabet.get(n));            
        }
        // System.out.println(alphabet_cipher);
        
        /* Read text file and store the message to string */
        File file = new File(file_path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        int d;
        String message = "";
        while ((d = br.read()) != -1) {
            message += (char)d;            
        }
        br.close();
        message = message.toUpperCase();
        // System.out.println(message);          
        
        /* Delete characters that is not in the alphabet */
        for(char c : message.toCharArray()){
            if(!alphabet.contains(c)){
                // System.out.println(c);
                message = message.replace(Character.toString(c), "");
            }
        }
        // System.out.println(message);               
        
        /* Encryption mode */
        if(mode == 'e'){
            String encrypted_message = "";
            for(int z = 0; z < message.length(); z++){
                int index = alphabet.indexOf(message.charAt(z));
                encrypted_message += alphabet_cipher.get(index);
            }
            //System.out.println("Ciphertext:");
            System.out.print(encrypted_message);
        }
        
        /* Decryption mode */
        else if(mode == 'd'){                       
            String original_message = "";
            for(int z = 0; z < message.length(); z++){
                int index = alphabet_cipher.indexOf(message.charAt(z));
                original_message += alphabet.get(index);
            }
            //System.out.println("Original message:");
            System.out.print(original_message);                
        }
                
        else{
            System.out.println("Wrong arguments given. Terminating program!!!");
        }
    }
}
