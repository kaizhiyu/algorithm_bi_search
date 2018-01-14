package com.algorithm.$19_todo.list.ytd;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */

@SearchKeyWord("this关键字 ")
public class $2018_01_14_the_fourth {

    /**
     *如果只有一个eat方法，它如何知道是被a1还是被a2所调用的呢？
     为了能用简便，面向对象的语法来编写代码----即“发送消息给对象“，编译器做了一些幕后工作。它把”所操作对象的引用"
     作为第一个参数传递给了eat()。所以上述俩个方法的调用等价于下:
     Apple.eat(a1,1);
     Apple.eat(a2,2);
     当然这是内部的表示形式，告诉我们实际发生的事情，但是我们并不能这样书写代码。
     假设你希望在方法的内部获得对当前对象的引用。由于这个引用是有编译器“偷偷”传入的，所以没有标识符可用“，为此，
     我们引入关键字：this，this关键字只能在方法内部使用，表示对”调用方法的那个对象"的引用。
     例如，当需要返回对当前对象的引用时，在return语句中这样写:
     */


}
