package com.algorithm.$19_todo.list.ytd;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.Arrays;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */

@SearchKeyWord(" jdk1.7 forkJoinPool ")
@SearchKeyWord(" 结论,理由,事实; 分类 ")

public class $2018_01_19_ForkJoinPool {

    //command + shift + G
    //https:    //book.douban.com/subject/1231584/
    //Linux内核源代码情景分析（上册）

    /**
     * 2: essentially	英[ɪˈsenʃəli] 美[ɪˈsenʃəli] adv.	本质上，根本上; 本来; “essential”的派生;
     *
     */

    /**
     * // http://blog.sina.com.cn/s/blog_c038e9930102v1ox.html
     * // http://blog.csdn.net/u010571316/article/details/64906481
     * 3: as-if-serial语义的意思指：
     * 不管怎么重排序（编译器和处理器为了提高并行度），
     * （单线程）程序的执行结果不能被改变。编译器，runtime 和处理器都必须遵守as-if-serial语义。
     * 为了遵守as-if-serial语义，编译器和处理器不会对存在数据依赖关系的操作做重排序，
     * 因为这种重排序会改变执行结果。但是，如果操作之间不存在数据依赖关系，
     * 这些操作可能被编译器和处理器重排序。为了具体说明，请看下面计算圆面积的代码示例：
     * <p>
     * 4: 重排序, 编译器和处理器为了提高并行度
     * <p>
     * 5: 内存屏障
     * 6: command + 7 : structure  , 结构
     *
     */
    /**
     * 7: 算法复杂度分析中的符号（Θ、Ο、ο、Ω、ω）简介  //http://blog.csdn.net/liyuming0000/article/details/46929493
     * <p>
     * <p>
     * 原创 2015年07月17日 16:42:49 标签：算法复杂度分析 5064
     * Θ，读音：theta、西塔；既是上界也是下界(tight)，等于的意思。
     * Ο，读音：big-oh、欧米可荣（大写）；表示上界(tightness unknown)，小于等于的意思。
     * ο，读音：small-oh、欧米可荣（小写）；表示上界(not tight)，小于的意思。
     * Ω，读音：big omega、欧米伽（大写）；表示下界(tightness unknown)，大于等于的意思。
     * ω，读音：small omega、欧米伽（小写）；表示下界(not tight)，大于的意思。
     */

    public static void main(String[] args) {

//        insertSort();
        StringBuilder he = new StringBuilder("he");
    }
    public static void printBuild(StringBuilder stringBuilder, User user) {
        stringBuilder = new StringBuilder(" 222");

        stringBuilder.append("world!");

        System.out.println(stringBuilder);

        user = new User("method", "11");
        System.out.println(user);

    }

    static class User {
        /**  */
        private String name;
        /**  */
        private String age;

//        @Override
//        public String toString() {
//            return "User{" +
//                    "name='" + name + '\'' +
//                    ", age='" + age + '\'' +
//                    '}';
//        }

        public User(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }
}
