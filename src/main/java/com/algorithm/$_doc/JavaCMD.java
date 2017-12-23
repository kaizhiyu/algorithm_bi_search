package com.algorithm.$_doc;

import com.algorithm.$8_annotation.Doc4Desc;

/**
 * @author fanhb on 2017/12/24
 * @desc xxxx
 */

@Doc4Desc("java命令 jar命令说明")
public class JavaCMD {

    //first method
    /**
     *
     * 1: 打包jar文件
     *      jar cvf  main.jar Test4Java.class
     * 2: 解压jar文件
     *      jar xf main.jar
     *
     * 3: 更新Main-Class: Test4Java.class
     *      jar    umf    MANIFEST.MF    app.jar 　(应该是hello.jar吧)　
     * 4: exec
     *      java jar main.jar
     *
     * https://www.cnblogs.com/mq0036/p/3885399.html
     *
     */

    //sec method
    /**
     * http://blog.csdn.net/xyw591238/article/details/51914803
     *
     *
     *
     */


    // third method
    // jar -cef
    /**
     *
     * JAR 命令说明
     * jar cmd
     -c  创建新档案
     -t  列出档案目录
     -x  从档案中提取指定的 (或所有) 文件
     -u  更新现有档案
     -v  在标准输出中生成详细输出
     -f  指定档案文件名
     -m  包含指定清单文件中的清单信息
     -n  创建新档案后执行 Pack200 规范化

     -e  为捆绑到可执行 jar 文件的独立应用程序 指定应用程序入口点
     -0  仅存储; 不使用任何 ZIP 压缩
     -P  保留文件名中的前导 '/' (绝对路径) 和 ".." (父目录) 组件
     -M  不创建条目的清单文件
     -i  为指定的 jar 文件生成索引信息
     -C  更改为指定的目录并包含以下文件
     如果任何文件为目录, 则对其进行递归处理。
     清单文件名, 档案文件名和入口点名称的指定顺序
     与 'm', 'f' 和 'e' 标记的指定顺序相同。

     示例 1: 将两个类文件归档到一个名为 classes.jar 的档案中:
     jar cvf classes.jar Foo.class Bar.class
     示例 2: 使用现有的清单文件 'mymanifest' 并
     将 foo/ 目录中的所有文件归档到 'classes.jar' 中:
     jar cvfm classes.jar mymanifest -C foo/ .

     *
     * http://blog.csdn.net/whatday/article/details/54767187
     * http://blog.csdn.net/hudashi/article/details/7069416
     *
     *
     * 1. jar -cef test.CardLayoutDemo CardLayoutDemo.jar test
     * 2. 以上命令及参数的含义如下：
     * 3. jar命令为java自带的专用打包工具；
     * 4. c代表生成新的jar包；
     * 5. e代表可执行的类，亦即main方法所在的类。书写时要加上包名，在本例中是后面的test.CardLayoutDemo；
     * 6. f代表生成的jar包的名称，在本例中是CardLayoutDemo.jar。此包名可以随意命名，没有规定；
     * 7. test最后面的这个参数表示将test目录下的所有文件都打包放到新的jar包中。
     *
     * 8. 打包class文件：
     * 主类                          jar包名称                       被打包的目标（class字节码）
     * 9. jar -cef CardLayoutDemo CardLayoutDemo.jar test
     *
     * 10. 执行/运行jar包：java -jar main2.jar
     * 11. 编译到指定目录： javac Test2.java -d /opt/
     */

}
