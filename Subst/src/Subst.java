/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subst {
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
        
        String message = "";
        List<Character> alphabet_cipher;
           
        // Count the number of appearance of each characters in the message
//        for(char a: alphabet)
//        {
//            int count = 0;
//            for(char o : message.toCharArray())
//            {
//                if(o == a)
//                {
//                    count++;
//                    
//                }
//                
//            }
//            System.out.println(a + ": " + count);
//        }
        
        /* Encryption mode */
        if(mode == 'e'){ 
            String file_path = args[1];
            String key_file_path = args[2];
            message = getMsgFromFile(file_path, alphabet);
            alphabet_cipher = getKeyFromFile(key_file_path);
            String encrypted_message = "";
            for(char c : message.toCharArray())
            {
                for(char a : alphabet)
                {
                    if(c == a)
                    {
                        encrypted_message += alphabet_cipher.get(alphabet.indexOf(a));
                    }
                }
            }
   
            
            //System.out.println("Ciphertext:");
            System.out.print(encrypted_message);
        }
        
        /* Decryption mode */
        else if(mode == 'd'){  
            String file_path = args[1];
            String key_file_path = args[2];
            message = getMsgFromFile(file_path, alphabet);
            alphabet_cipher = getKeyFromFile(key_file_path);
            String original_message = "";
            for(char c : message.toCharArray())
            {
                for(char a : alphabet_cipher)
                {
                    if(c == a)
                    {
                        original_message += alphabet.get(alphabet_cipher.indexOf(a));
                    }
                }
            }
           
            
            //System.out.println("Original message:");
            System.out.print(original_message);                
        }
        
        else if(mode == 'g')
        {
            String key_generate_file = args[1];
            List<Character> key_generate_list = alphabet;
            Collections.shuffle(key_generate_list);
            
            
            
            File file = new File(key_generate_file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);          
            //System.out.print(key_generate_list);
            for(char c: key_generate_list)
            {    
                System.out.print(c);
                bw.write(c);
            }
            
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
    
    public static List<Character> getKeyFromFile(String key_file_path) throws FileNotFoundException, IOException
    {       
        /* Read key from text file and store the key */
        File key_file = new File(key_file_path);
        BufferedReader br1 = new BufferedReader(new FileReader(key_file));
        int e;
        String key = "";
        while ((e = br1.read()) != -1) {           
            key += (char) e;
        }
        br1.close();
        // System.out.println(key);
        
        // key = "(YUCPE.L6_3AF2H5Q; 0):J_?Z\nK___________NX9________";
        /* Make a new alphabet from the key */
        List<Character> alphabet_cipher = new ArrayList<>();
        for (int j = 0; j < key.length(); j++){
           // System.out.println(alphabet_s.charAt(j));
           alphabet_cipher.add(key.charAt(j));
        }
        return alphabet_cipher;
        // System.out.println(alphabet_cipher);
        // System.out.println(alphabet_cipher.size());
    }
    
}
