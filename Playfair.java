import java.util.Scanner;
public class Playfair {
public static void main(String[] args) {
    Scanner in=new Scanner(System.in);
    String key="MONARCHY";
    String message=in.nextLine();
    PFEncryption pfEncryption=new PFEncryption();
    pfEncryption.makeArray(key);
    message=pfEncryption.manageMessage(message);
    pfEncryption.doPlayFair(message, "Encrypt");
    String en_txt=pfEncryption.getEncrypted();
    System.out.println(en_txt.toLowerCase());
    pfEncryption.doPlayFair(en_txt, "Decrypt");
    System.out.print(pfEncryption.getDecrypted().toLowerCase());
}
}

class PFEncryption{
    private char [][] alpha= new char[5][5];
    private char[] unique_char= new char[26];
    private String ch="ABCDEFGHIKLMNOPQRSTUVWXYZ";
    private String enc="";
    private String dec="";
    void makeArray(String keyword){
        keyword=keyword.toUpperCase().replace("J","I");
        boolean present, terminate=false;
        int value=0;
        int uniq_length;
        for (int i=0; i<keyword.length(); i++){
            present=false;
            uniq_length=0;
            if (keyword.charAt(i)!= ' '){
                for (int k=0; k<unique_char.length; k++){
                    if (Character.toString(unique_char[k])==null){
                        break;
                    }
                    uniq_length++;
                }
                for (int j=0; j<unique_char.length; j++){
                    if (keyword.charAt(i)==unique_char[j]){
                        present=true;
                    }
                }
                if (!present){
                    unique_char[value]=keyword.charAt(i);
                    value++;
                }
            }
            ch=ch.replaceAll(Character.toString(keyword.charAt(i)), "");
        }
        for (int i=0; i<ch.length(); i++){
            unique_char[value]=ch.charAt(i);
            value++;
        }
        value=0;
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                alpha[i][j]=unique_char[value];
                value++;
                
            }
        }
        }
        String manageMessage(String message){
        int value=0;
        int len=message.length()-2;
        String newTxt="";
        String intermediate="";
        while (len>=0){
            intermediate=message.substring(value, value+2);
            if (intermediate.charAt(0)==intermediate.charAt(1)){
                newTxt=intermediate.charAt(0) + "x" + intermediate.charAt(1);
                message=message.replaceFirst(intermediate, newTxt);
                len++;
            }
            len-=2;
            value+=2;
        }
        if (message.length()%2!=0){
            message=message+'x';
            }
            return message.toUpperCase().replaceAll("J","I").replaceAll(" ","");
        }
    void doPlayFair(String message, String tag){
        int value=0;
        while (value<message.length()){
            searchAndEncryptOrDecrypt(message.substring(value, value + 2), tag);
            value+=2;
        }
    }
    void searchAndEncryptOrDecrypt(String doubblyCh, String tag){
        char ch1=doubblyCh.charAt(0);
        char ch2=doubblyCh.charAt(1);
        int row1=0, col1=0, row2=0, col2=0;
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if (alpha[i][j]==ch1){
                    row1=i;
                    col1=j;
                }else if (alpha[i][j]==ch2){
                    row2=i;
                    col2=j;
                }
            }
        }
        if (tag=="Encrypt")
            encrypt(row1, col1, row2, col2);
        else if(tag=="Decrypt")
            decrypt(row1, col1, row2, col2);
    }
    void encrypt(int row1, int col1, int row2, int col2){
        if (row1==row2){
            col1=col1+1;
            col2=col2+1;
            if (col1>4)
                col1=0;
            if (col2>4)
                col2=0;
    enc+=(Character.toString(alpha[row1][col1])+Character.toString(alpha[row1][col2]));
        }else if(col1==col2){
            row1=row1+1;
            row2=row2+1;
            if (row1>4)
                row1=0;
            if (row2>4)
                row2=0;
    enc+=(Character.toString(alpha[row1][col1])+Character.toString(alpha[row2][col1]));
        }else{
            enc+=(Character.toString(alpha[row1][col2])+Character.toString(alpha[row2][col1]));
        }
    }
    void decrypt(int row1, int col1, int row2, int col2){
        if (row1==row2){
            col1=col1-1;
            col2=col2-1;
            if (col1<0)
                col1=4;
            if (col2<0)
                col2=4;
            dec+=(Character.toString(alpha[row1][col1])+Character.toString(alpha[row1][col2]));
        }else if(col1==col2){
            row1=row1-1;
            row2=row2-1;
            if (row1<0)
                row1=4;
            if (row2<0)
                row2=4;
            dec+=(Character.toString(alpha[row1][col1])+Character.toString(alpha[row2][col1]));
        }else{
            dec+=(Character.toString(alpha[row1][col2])+Character.toString(alpha[row2][col1]));
        }
    }
    String getEncrypted(){
        return enc;
    }
    String getDecrypted(){
        return dec;
    }
}
