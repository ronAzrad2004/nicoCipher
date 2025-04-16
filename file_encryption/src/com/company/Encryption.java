package com.company;

public class Encryption {

    // Encrypts a message using the Nico Cipher and a keyword
    public static String nicoCipher(String message, String key) {
        int[] arr = new int[key.length()]; // Array to store ASCII values of key characters
        String[] msg_arr; // Holds each column (characters belonging to a key letter)
        String[] correct_arr; // Final rearranged rows to build the encoded message
        String encoded_msg = ""; // Final output message

        // Pad message with spaces so its length is a multiple of the key length
        while (message.length() % key.length() != 0)
            message += " ";

        int length = message.length() / key.length(); // Number of rows
        correct_arr = new String[length]; // One string for each row
        msg_arr = new String[key.length()]; // One string for each column (key character)

        reset(correct_arr); // Initialize arrays with empty strings
        reset(msg_arr);

        // Step 1: Store ASCII values of key characters
        for (int i = 0; i < key.length(); i++) {
            arr[i] = (int) (key.charAt(i));
        }

        // Step 2: Divide message into columns according to the key
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0, k = i; j < length; j++, k += key.length()) {
                if (k < message.length())
                    msg_arr[i] += message.charAt(k);
                else
                    msg_arr[i] += ' '; // Pad with space if necessary
            }
        }

        // Step 3: Reorder the columns based on alphabetical order of key characters
        for (int i = 0; i < arr.length; i++) {
            int temp_index = findMin(arr); // Find the smallest unused key char (alphabetically first)
            arr[temp_index] = Integer.MAX_VALUE; // Mark it as used
            get_Values(msg_arr, temp_index, correct_arr); // Fill rows from the selected column
        }

        // Step 4: Concatenate all the rows into one string
        for (String str : correct_arr)
            encoded_msg += str;

        return encoded_msg;
    }

    // Adds characters from the selected column (temp_index) into each row of correct_arr
    private static void get_Values(String[] msg_arr, int temp_index, String[] correct_arr) {
        for (int i = 0; i < correct_arr.length; i++)
            correct_arr[i] += msg_arr[temp_index].charAt(i);
    }

    // Helper method to reset all strings in an array
    private static void reset(String[] correct_arr) {
        for (int i = 0; i < correct_arr.length; i++)
            correct_arr[i] = "";
    }

    // Finds the index of the smallest (alphabetically first) character in the array
    private static int findMin(int[] arr) {
        int index = 0;
        int num = arr[index];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < num) {
                num = arr[i];
                index = i;
            }
        }
        return index;
    }
}
