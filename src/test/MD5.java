package test;

import java.security.MessageDigest;

//MD5+salt算法
public class MD5 {
    private static String compute(String inStr) {
        if (inStr == null)
            inStr = "";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        char[] charArray = inStr.toCharArray();

        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuilder hexValue = new StringBuilder();

        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();

    }

    private static String MD5Encode(String inStr, String salt) {
        String pwd = MD5.compute(inStr);
        pwd = pwd + salt;
        return MD5.compute(pwd);
    }

    public static void main(String[] args) {

        String password = "xiaoliouc";
        String username = "xiaoli";
        String pwd = MD5.MD5Encode(password, username);
        System.out.println(pwd);
        // 打印结果：5945710d891497c76f3353b8abadfc9d
    }

}