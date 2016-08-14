import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ColTrans {
    public static void main (String[] args) throws IOException{    
        /* Receive input arguments */
        char mode = args[0].charAt(0);
        String file_path = args[1];
        double key = Integer.parseInt(args[2]); // number of columns
        
        /* The original alphabet */
        String alphabet_s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
        List<Character> alphabet = new ArrayList<>();
        
        /* Store string alphabet_s to list */
        for (int j = 0; j < alphabet_s.length(); j++){
           // System.out.println(alphabet_s.charAt(j));
           alphabet.add(alphabet_s.charAt(j));
        }               
        // System.out.println(alphabet);         
        
        /* Read text file and store the message to string */
        File file = new File(file_path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        int d;
        String message = "";
        while ((d = br.read()) != -1) {
            message += (char) d;            
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
        
        /* Calculate number of rows and create a double dimensional array */
        double num_row = Math.ceil(message.length() / key);
        char[][] message_da = new char[(int)num_row][(int)key];
        // System.out.println(num_row);
        //System.out.println("Number of characters: " + message.length());
        
        /* Encryption mode */
        if (mode == 'e') {           
            /* Insert the original message to the table */
            for (int j = 0; j < message_da.length; j++) { // number of rows
                for (int i = 0; i < message_da[j].length; i++) { // number of columns
                    int position = (int) (i + j * key);
                    if (position >= message.length()) {
                        message_da[j][i] = ' ';
                    } else {
                        // System.out.println(position);
                        message_da[j][i] = message.charAt(position);
                    }
                }
            }

            /* Create an encrypted version */
            String encrypted_message = "";
            for (int i = 0; i < message_da[0].length; i++) { // number of columns
                for (int j = 0; j < message_da.length; j++) { // number of rows
                    encrypted_message += message_da[j][i];
                }
            }
            //System.out.println("Ciphertext:");
            System.out.print(encrypted_message);
        }
        
        /* Decryption mode */
        else if(mode == 'd'){                       
            /* Insert the encrypted message to the table */
            int position = 0;
            for (int i = 0; i < message_da[0].length; i++) { // number of columns
                for (int j = 0; j < message_da.length; j++) { // number of rows
                    if (position >= message.length()) {
                        message_da[j][i] = ' ';
                    }
                    else{
                        message_da[j][i] = message.charAt(position);
                    }
                    position++;
                }
            }
            
            /* Construct original message */ 
            String original_message = "";
            for (int j = 0; j < message_da.length; j++) { // number of rows
                for (int i = 0; i < message_da[j].length; i++) { // number of columns
                    original_message += message_da[j][i];
                }                
            }
            //System.out.println("Original message:");
            System.out.print(original_message);
        }
                
        else{
            System.out.println("Wrong arguments given. Terminating program!!!");
        }
    }
}
