package com.algorithm.$22_encode;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import java.security.MessageDigest;

/**
 * @auth v_fanhaibo on   2018/1/17
 */
public class Md5Util {

    @SearchKeyWord("转16进制字符串，前面补零")
    public final static String MD5(String toEncode) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd',
                'e', 'f'};
        try {
            byte[] strTemp = toEncode.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            StringBuilder builder = new StringBuilder("");
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
//                System.out.println(md[i] & 0xff);
                builder.append(String.format("%2s",Integer.toHexString(md[i] & 0xff)).replaceAll(" ","0"));
            }
            System.out.println(builder.toString());//there is a problem which not UTF-16
            System.out.println(new String(str));
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        MD5("a_123456");
//        String str = "000AB";
//        Integer in = Integer.valueOf(str,16);
//        System.out.println(in);
//        String st = Integer.toHexString(in).toUpperCase();
//        st = String.format("%2s",st);
//        st= st.replaceAll(" ","0");
//        System.out.println(st);
    }
}
