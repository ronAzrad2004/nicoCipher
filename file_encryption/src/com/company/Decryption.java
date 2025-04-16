package com.company;

import java.util.Arrays;

public class Decryption {
    // Decrypts a string encoded using the Nico Cipher and a given key
    public static String nicoCipher_Decryption(String encode, String key) {
        // Convert key into character array and sort it alphabetically
        char[] new_keys = key.toCharArray();
        Arrays.sort(new_keys); // Used to determine the column order in the cipher grid

        String[] str_arr = new String[key.length()]; // Temporary array to hold encoded columns
        String decode = ""; // Final decoded message

        reset(str_arr); // Initialize all elements to empty strings

        int length = encode.length() / key.length(); // Number of rows in the transposition grid

        // Step 1: Rebuild columns from encoded message using fixed-length stepping
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0, k = i; j < length; j++, k += key.length()) {
                if (k < encode.length())
                    str_arr[i] += encode.charAt(k); // Fill each column
            }
        }

        String[] dec_str = new String[key.length()]; // Reordered columns based on original key
        reset(dec_str); // Initialize all elements to empty strings

        // Step 2: Rearrange the columns into their original order using the key
        for (int i = 0; i < key.length(); i++) {
            // Find the position of the current character in the sorted key
            int temp_index = new String(new_keys).indexOf(key.charAt(i));
            new_keys[temp_index] = '~'; // Mark this character as used
            dec_str[i] = str_arr[temp_index]; // Place the correct column in order
        }

        // Step 3: Read the characters row by row to reconstruct the original message
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < dec_str.length; j++) {
                decode += dec_str[j].charAt(i); // Concatenate row-wise
            }
        }

        return decode.trim(); // Return the decoded message without extra whitespace
    }

    // Helper method to initialize all elements in a string array to empty strings
    private static void reset(String[] correct_arr) {
        for (int i = 0; i < correct_arr.length; i++)
            correct_arr[i] = "";
    }
}
