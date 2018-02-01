package com.algorithm.$_java_tools_cmd.$tc4_java.cmd;

import java.io.*;
import java.util.Arrays;

/**
 * @auth v_fanhaibo on   2018/1/31
 */
public class $2018_01_31_java_cmd {

    /** 1: jcmd  ===jps -ml */

    /** 2: jdeps --jdkinternals     [运行该命令jdeps -jdkinternals以确定您的代码是否使用内部JDK API。] */

    /** 3: jdk9doc
     *  //https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-5B808B2F-E891-43CD-BF6E-78787E547071
     *
     * */

    /**
     * process handle
     * 4:descendants	英[dɪ'sendənts] 美[dɪ'sendənts] n.	后裔; 后代( descendant的名词复数 ); （由过去类似物发展来的） 派生物; 弟子;
     */


    public static void main(String[] args) {

        File file = new File(pathName);

        recurseUpdateFile(file);
//        recurseFile(file);
//        System.out.println(" /** ".replaceAll("/\\*\\*", "<\\\\b>"));
//        System.out.println(" */ ".replaceAll("\\*/", "<\\\\b>"));
//        System.out.println("*".replaceAll("\\*", "<\\\\b>"));

//        File file = new File("C:\\opt\\logs\\src\\launcher\\my.html");
//        randomAccessFile(file.getAbsolutePath(),"rw");
//        File file = new File("C:\\opt\\logs\\src\\launcher\\my.java");
//        String absolutePath = file.getAbsolutePath();
//        String newPath = absolutePath.replaceAll("\\.java", "\\.html");
//        file.renameTo(new File(newPath));
//
//        System.out.println(file.getAbsolutePath());

    }

    private static void recurseFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            Arrays.stream(files).parallel().forEach($2018_01_31_java_cmd::recurseFile);
        } else {

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String s = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            String absolutePath = file.getAbsolutePath();
//            String newPath = absolutePath.replaceAll("\\.java", "\\.html");
//            System.out.println(newPath);
//            file.renameTo(new File(newPath));

        }
    }

    /**
     * 修改文件的**
     *
     * @param file
     */
    public static void recurseUpdateFile(File file) {

        if (file.isDirectory()) {
            Arrays.stream(file.listFiles()).parallel().forEach($2018_01_31_java_cmd::recurseUpdateFile);
        } else {
            randomAccessFileHead(file.getAbsolutePath(), "rw");
        }


    }
    static String pathName = "C:\\opt\\logs\\src\\com\\sun\\corba\\se\\impl\\corba\\ExceptionListImpl.java";

    public static void randomAccessFile(String path, String rwMode) {
        StringBuilder htmlHeader = new StringBuilder("<!DOCTYPE html><html><head>    <meta charset=\"UTF-8\">    <title>text</title>    <style>            </style></head><body>");

        StringBuilder htmlFoot = new StringBuilder("</body></html>");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(path, rwMode)) {
            String line = null;
            long lastPointer = 0;
//            randomAccessFile.readUTF()
            while ((line = randomAccessFile.readLine()) != null) {
                final long filePointer = randomAccessFile.getFilePointer();

                String replaceAll = line
//                        .replaceAll("/\\*\\*", "<\\\\b>");
//                        .replaceAll("\\*/", "<\\\\b>")
                        .replaceAll(" /* ", " <\\\\b> ");

                if (lastPointer == 0) {
                    replaceAll = htmlHeader.append(replaceAll).toString();
                }
                randomAccessFile.seek(lastPointer);
                randomAccessFile.writeBytes(replaceAll);
                lastPointer = filePointer;
                if (lastPointer == 0) {
                    lastPointer = randomAccessFile.getFilePointer();
                }
            }
            randomAccessFile.seek(lastPointer);
            randomAccessFile.writeBytes(htmlFoot.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void randomAccessFileHead(String path, String rwMode) {
        StringBuilder htmlHeader = new StringBuilder("<!DOCTYPE html><html><head>    <meta charset=\"UTF-8\">    <title>text</title>    <style>            </style></head><body>");

        StringBuilder htmlFoot = new StringBuilder("</body></html>");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(path, rwMode)) {

            long filePointer = randomAccessFile.getFilePointer();
            randomAccessFile.seek(filePointer);
            randomAccessFile.writeBytes(htmlHeader.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
