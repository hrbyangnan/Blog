package Security;

import java.util.Random;

public class Security {
    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    //todo get salt code
    public static String getSaltCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(hex[random.nextInt(16)]);
        }
        return sb.toString();
    }

    //todo get hash+slat code
    public static String getHashAndSaltCode(String salt, String password) {
        String saltAndHash=salt+password;
        int arraySize = 11113;
        int hashAndSalt = 0;
        for (int i = 0; i < saltAndHash.length(); i++) {
            int letterValue = saltAndHash.charAt(i) - 96;

            hashAndSalt = ((hashAndSalt << 5) + letterValue) % arraySize;
        }

        return hashAndSalt+"";
    }

//    public static void main(String[] args) {
//        String temp=getHashAndSaltCode(getSaltCode(),"123456");
//        System.out.println(temp);
//    }




}
