import java.util.Scanner;
class Caesar{
    public static final String alph = "abcdefghijklmnopqrstuvwxyz";
    public static String encrypt (String pt,int sk){
        pt=pt.toLowerCase();
        String cipher_text="";
        for(int i=0;i<pt.length();i++){
            int map_set=alph.indexOf(pt.charAt(i));
            int enc_val=(sk+map_set)%26;
            char evalue=alph.charAt(enc_val);
            cipher_text=cipher_text+evalue;
        }
        return cipher_text;
    }
    public static String decrypt (String cipher_text,int sk){
        cipher_text=cipher_text.toLowerCase();
        String pt="";
        for(int i=0;i<cipher_text.length();i++){
            int map_set=alph.indexOf(cipher_text.charAt(i));
            int dec_val=(map_set-sk)%26;
            if(dec_val < 0){
                dec_val = alph.length()+dec_val;
            }
            char dvalue=alph.charAt(dec_val);
            pt=pt+dvalue;
         }
        return pt;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String message = new String();
        message=sc.next();
        String encrypted = encrypt(message,3);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted, 3);
        System.out.println(decrypted);
        sc.close();
    }
   }
