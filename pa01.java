import java.io.*;
import java.util.ArrayList;


/*=============================================================================
|   To Compile:  javac pa01.java
|   To Execute:  java -> java pa01 kX.txt pX.txt
|   Assignment:  pa01 - Encrypting a plaintext file using the Vigenere cipher
|   Author:      Emin Mammadzada
|   Language:    Java
|   Instructor:  McAlpin
|     Due Date:  October 27, 2021
|
+=============================================================================*/

public class pa01 {

    public static void main(String[] args) throws IOException {

        //arrays to store the data from input files
        ArrayList<Character> key_arr = new ArrayList<>();
        ArrayList<Character> padded_key_arr = new ArrayList<>();
        ArrayList<Character> plaintext_arr = new ArrayList<>();
        ArrayList<Character> cipher_arr;

        // create necessary objects for reading the key file
        BufferedReader key_br = make_reader(new File(args[0]));
        BufferedReader p_key_br = make_reader(new File(args[0]));

        // create necessary objects for reading the plaintext file
        BufferedReader plaintext_br = make_reader(new File(args[1]));


        //read the files into corresponding arraylists
        read_file(key_arr, key_br);
        read_file(padded_key_arr, p_key_br);
        read_file(plaintext_arr, plaintext_br);

        //add additional padding to plaintext
        while (plaintext_arr.size() != 512){
            plaintext_arr.add('x');
        }

        //generate a key to be the same size as plaintext
        generate_key(padded_key_arr, plaintext_arr);

        //create cipher text
        cipher_arr = create_cipher_text(padded_key_arr, plaintext_arr);

        //print the output to the console
        print_output(key_arr, plaintext_arr, cipher_arr);

    }

    //print final output
    public static void print_output(ArrayList<Character> key_arr, ArrayList<Character> plaintext_arr, ArrayList<Character> cipher_arr){
        System.out.println("Vigenere Key: \n");
        print_arr(key_arr);

        System.out.println("Plaintext: \n");
        print_arr(plaintext_arr);

        System.out.println("Cipher text: \n");
        print_arr(cipher_arr);
    }

    //method to formatted printing
    public static void print_arr(ArrayList<Character> arr){
        int i = 0;
        for (Character character : arr) {
            if (i > 79){
                i = 0;
                System.out.print("\n");
            }
            System.out.print(character);
            i += 1;
        }
        System.out.print("\n\n");
    }

    //pad the key until it has the same length as plaintext
    public static void generate_key(ArrayList<Character> key_arr, ArrayList<Character> plaintext_arr){

        int size = plaintext_arr.size();

        for (int i=0; ;i++){
            if (size == i)
                i = 0;
            if (key_arr.size() == plaintext_arr.size())
                break;
            key_arr.add(key_arr.get(i));
        }
    }

    //generate the cipher text
    public static ArrayList<Character> create_cipher_text(ArrayList<Character> key_arr, ArrayList<Character> plaintext_arr){
        ArrayList <Character> cipher_arr = new ArrayList<>();
        for (int i=0; i<plaintext_arr.size(); i++) {
            int ch = ( (key_arr.get(i)-97) + (plaintext_arr.get(i) -97)) % 26 + 97;
            cipher_arr.add( (char) ch);
        }
        return cipher_arr;
    }


    //create buffered readers for files
    public static BufferedReader make_reader(File file) throws FileNotFoundException {
        FileReader file_fr = new FileReader(file);
        return new BufferedReader(file_fr);
    }

    public static void read_file(ArrayList<Character> arr, BufferedReader br) throws IOException {
        int c;
        while((c = br.read()) != -1){
            char character = (char) c;

            if (Character.isLetter(character)) {
                arr.add(Character.toLowerCase(character));
            }
        }
    }

}


/*=============================================================================
      | I, Emin Mammadzada, 5087769 affirm that this program is
      | entirely my own work and that I have neither developed my code together with
      | any another person, nor copied any code from any other person, nor permitted
      | my code to be copied  or otherwise used by any other person, nor have I
      | copied, modified, or otherwise used programs created by others. I acknowledge
      | that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/

