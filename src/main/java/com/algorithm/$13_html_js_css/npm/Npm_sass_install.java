package com.algorithm.$13_html_js_css.npm;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * npm install sass -g
 *
 * @author:v_fanhaibo on 2018/1/5.
 * @version:v1.0
 */

public class Npm_sass_install {

    /**
     * sass 安装命令
     *
     * npm install sass -g
     *
     * js执行命令
     * node xxx.js
     *
     *
     */
    //vue 启动命令：npm run dev
    public static void main(String[] args) throws IOException {

        byte[] bytes = "中国  ssss".getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String s = reader.readLine();
        reader.close();
        in.close();
        System.out.println(s);
        ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(bytes);


    }
}
