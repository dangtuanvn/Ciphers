


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Vernam {
    public static void main (String[] args) throws IOException{    
        /* Receive input arguments */
        char mode = args[0].charAt(0);
         
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
        
       
        
       /* Encryption mode */
        if (mode == 'e') {

            String file_path = args[1];
            String key_file_path = args[2];
            int n = Integer.parseInt(args[3]);
            String message = getMsgFromFile(file_path, alphabet);
            String key = getMsgFromFile(key_file_path, alphabet);
            String encrypted_message = "";

            if (message.length() + n> key.length()) {// End of key
                System.out.println("Not Enough Key");
            } else {

                for (int j = 0; j < message.length(); j++) {
                    /* Combine the message and key */
                    int char_value = alphabet.indexOf(message.charAt(j));

                    int key_value = alphabet.indexOf(key.charAt(j + n));

                    int combined_value = char_value + key_value;

                    /* Convert the combined value to character */
                    if (combined_value >= alphabet.size()) {
                        combined_value -= alphabet.size();
                    }

                    encrypted_message += alphabet.get(combined_value);

                }
                //System.out.println("Ciphertext:");
                System.out.print(encrypted_message);
            }

        }
        
        /* Decryption mode */
        else if (mode == 'd') {
            String file_path = args[1];
            String key_file_path = args[2];
            int n = Integer.parseInt(args[3]);
            String message = getMsgFromFile(file_path, alphabet);
            String key = getMsgFromFile(key_file_path, alphabet);
            String original_message = "";

            if (message.length() + n > key.length()) {// End of key
                System.out.println("Not Enough Key");

            } else {
                for (int j = 0; j < message.length(); j++) {
                    /* Combine the message and key */
                    int char_value = alphabet.indexOf(message.charAt(j));

                    int key_value = alphabet.indexOf(key.charAt(j + n));

                    int combined_value = char_value - key_value;

                    /* Convert the combined value to character */
                    if (combined_value < 0) {
                        combined_value += alphabet.size();
                        //System.out.println(combined_value);
                    }

                    original_message += alphabet.get(combined_value);
                }
                //System.out.println("Original message:");
                System.out.print(original_message);
            }
        }
        
        else if (mode == 'g')// generate key mode
        {
            String key_generate_file = args[1];
            int key_length = Integer.parseInt(args[2]);
            String key_generate = "";
            for(int i = 0; i < key_length; i++)
            {
                key_generate += alphabet.get(new Random().nextInt(50));// generate random character in Alphabet
            }
           
            File file = new File(key_generate_file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);          
            System.out.print(key_generate);
            bw.write(key_generate);          
            bw.close();
        }
                
        else{
            System.out.println("Wrong arguments given. Terminating program!!!");
        }
        
    }
    
    public static String getMsgFromFile(String file_path, List<Character> alphabet) throws FileNotFoundException, IOException
    {
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
      
        return message;
        // System.out.println(message); 
    }
        
}