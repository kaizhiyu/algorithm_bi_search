package com.algorithm.$2_java8;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/29.
 * @version:v1.0
 */

public class User {

    private Byte code;

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public static void main(String[] args) {
        User user = new User();
//        user.setCode(new Byte(1));
        Byte.valueOf(1);
        Byte aByte = new Byte((byte) 1);
    }
}
