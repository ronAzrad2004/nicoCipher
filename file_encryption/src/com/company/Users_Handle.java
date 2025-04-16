package com.company;


import java.io.*;
import java.util.Scanner;

public class Users_Handle {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in); // Create a scanner object for user input

        encrypted_file(scanner); // Call the encryption method
        //C:\Users\ron12\Documents\aa.txt
        //C:\Users\ron12\Desktop\encoded.txt
        decrypted_file(scanner); // Call the decryption method
    }

    // Method to encrypt a file and save the result
    public static void encrypted_file(Scanner scanner) throws IOException {
        // Prompt user for the path of the file to encrypt
        System.out.print("Enter the path of the text file you want to encrypt: ");
        String path = scanner.nextLine();
        path = path.replaceAll("\\\\", "/"); // Replace backslashes with slashes for compatibility

        // Create the file where encrypted content will be saved
        File f = new File("C:/Users/ron12/Desktop/encoded.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        BufferedReader file = null;

        try {
            file = new BufferedReader(new FileReader(path)); // Try to open the input file
        } catch (Exception exception) {
            System.out.println(exception); // Handle file not found or access issues
            return;
        }

        // Prompt user for the encryption key
        System.out.print("Enter the key you want to encrypt the file with: ");
        String str_to_encode = "";
        String key = scanner.nextLine();

        if (file != null) {
            String l;
            // Read each line of the file and append to string
            while ((l = file.readLine()) != null) {
                str_to_encode += l + "\n";
            }
            str_to_encode = str_to_encode.trim(); // Remove last unnecessary newline

            // Encrypt the gathered text using the provided key
            String encoded_msg = Encryption.nicoCipher(str_to_encode, key);

            // Write the encrypted text to the new file
            writer.write(encoded_msg);
            System.out.println("The encoded text is in the file you wanted");

            // Close writer and reader to release system resources
            writer.close();
            file.close();
        }
    }

    // Method to decrypt a file and save the result
    public static void decrypted_file(Scanner scanner) throws IOException {
        // Prompt user for the path of the file to decrypt
        System.out.print("Enter the path of the text file you want to decrypt: ");
        String path = scanner.nextLine();
        path = path.replaceAll("\\\\", "/"); // Replace backslashes with slashes for compatibility

        // Create the file where decrypted content will be saved
        File f = new File("C:/Users/ron12/Desktop/decoded.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        BufferedReader file = null;

        try {
            file = new BufferedReader(new FileReader(path)); // Try to open the input file
        } catch (Exception exception) {
            System.out.println(exception); // Handle file not found or access issues
            return;
        }

        // Prompt user for the decryption key
        System.out.print("Enter the key you want to decrypt the file with: ");
        String str_to_decode = "";
        String key = scanner.nextLine();

        if (file != null) {
            String l;
            // Read each line of the file and append to string
            while ((l = file.readLine()) != null) {
                str_to_decode += l + "\n";
            }
            str_to_decode = str_to_decode.trim(); // Remove last unnecessary newline

            // Decrypt the text using the provided key
            String decoded_msg = Decryption.nicoCipher_Decryption(str_to_decode, key);

            // Write the decrypted text to the new file
            writer.write(decoded_msg);
            System.out.println("The decoded text is in the file you wanted");

            // Close writer and reader to release system resources
            writer.close();
            file.close();
        }
    }
}
