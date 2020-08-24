import java.io.*;
import java.lang.*;
import java.util.Scanner;
public class Vigenere
{    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
            String key = new String();
        System.out.println("Enter the key:");

            key = scan.next();
            key = key.toUpperCase();
        System.out.println("Enter the plain text:");
        String pt = new String();

        pt = scan.next();
            
            pt = pt.toUpperCase();
      
        String enc_t = encrypt(pt, key);
        System.out.println(enc_t);
        System.out.println(decrypt(enc_t, key));
    }
 
    static String encrypt(String txt, final String key) {
        String cipher = "";
        txt = txt.toUpperCase();
        for (int i = 0, j = 0; i < txt.length(); i++) {
            char character = txt.charAt(i);
            if (character < 'A' || character > 'Z') continue;
            cipher += (char)((character + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return cipher;
    }
 
    static String decrypt(String txt, final String key) {
        String cipher = "";
        txt = txt.toUpperCase();
        for (int i = 0, j = 0; i < txt.length(); i++) {
            char character = txt.charAt(i);
            if (character < 'A' || character > 'Z') continue;
            cipher += (char)((character - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return cipher;
    }
}
