package com.algorithm.$5_json;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 获取所有的非目录文件
 *
 * @author:v_fanhaibo on 2017/12/19.
 * @version:v1.0
 */

public class RecurseFile_NotDirectory {

    public static void main(String[] args) {

        getFiles(new File("C:\\shanlin\\Java8InAction\\src\\main\\java\\lambdasinaction\\RecurseFile_NotDirectory"));

    }
    //    获取所有的非目录文件
    public static List<File> getFiles(File file){
        List<File> recurseFiles = getRecurseFiles(file);
        List<File> collect = recurseFiles
                .stream()
                .flatMap(list -> Arrays.stream(list.listFiles()))
                .filter(File::isFile)
                .collect(toList());
        return collect;

    }

    //递归获取所有目录（File）
    public static List<File> getRecurseFiles(File file){
        File[] files = file.listFiles();
        List<File> list = new ArrayList<>();

        for (File file1 : files) {
            if (file1.isDirectory()){
                list.addAll(getRecurseFiles(file1));
            }else {
                list.add(file1);
            }
        }
        return list;
    }
}
