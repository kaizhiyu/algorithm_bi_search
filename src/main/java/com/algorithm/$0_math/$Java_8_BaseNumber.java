package com.algorithm.$0_math;

import com.algorithm.$8_annotation.Doc4Desc;

/**
 * java8中基本数据类型
 * Integer 、Long、Short、Byte、Character、Double、Float、Boolean、BigInteger、BigDecmail
 * 其中BigInteger、BigDecimal没有相对应的基本类型，主要应用于高精度的运算，BigInteger 支持任意精度的整数，
 * BigDecimal支持任意精度带小数点的运算。
 *
 * @author:v_fanhaibo on 2018/1/4.
 * @version:v1.0
 */

@Doc4Desc("基本数据类型")
public class $Java_8_BaseNumber {

    /**
     * java八大基本类型：
     * 整型：（8）
     *      byte：
     *      byte数据类型是8位、有符号的，以二进制补码表示的整数；
     *      最小值是-128（-2^7）；
     *      最大值是127（2^7-1）；
     *      默认值是0；
     *      byte类型用在大型数组中节约空间，主要代替整数，因为byte变量占用的空间只有int类型的四分之一；
     *      例子：byte a = 100，byte b = -50。
     * short：（16）
     *      short数据类型是16位、有符号的以二进制补码表示的整数
     *      最小值是-32768（-2^15）；
     *      最大值是32767（2^15 - 1）；
     *      Short数据类型也可以像byte那样节省空间。一个short变量是int型变量所占空间的二分之一；
     *      默认值是0；
     *      例子：short s = 1000，short r = -20000。
     * int：（32）
     *      int数据类型是32位、有符号的以二进制补码表示的整数；
     *      最小值是-2,147,483,648（-2^31）；
     *      最大值是2,147,483,647（2^31 - 1）；
     *      一般地整型变量默认为int类型；
     *      默认值是0；
     *      例子：int a = 100000, int b = -200000。
     * long：（64）
     *      long数据类型是64位、有符号的以二进制补码表示的整数；
     *      最小值是-9,223,372,036,854,775,808（-2^63）；
     *      最大值是9,223,372,036,854,775,807（2^63 -1）；
     *      这种类型主要使用在需要比较大整数的系统上；
     *      默认值是0L；
     *      后面加L或者l,就表示是long长整型；
     *      例子： long a = 100000L，Long b = -200000L。
     * 浮点型：
     * float：（32）
     *      float数据类型是单精度、32位、符合IEEE 754标准的浮点数；
     *      float在储存大型浮点数组的时候可节省内存空间；
     *      默认值是0.0f；
     *      后面加F或者f,表示float类型；
     *      浮点数不能用来表示精确的值，如货币；
     *      例子：float f1 = 234.5f。
     * double：（64）
     *      double数据类型是双精度、64位、符合IEEE 754标准的浮点数；
     *      浮点数的默认类型为double类型；
     *      double类型同样不能表示精确的值，如货币；
     *      默认值是0.0d；
     *      后面加D或者d,表示double类型；
     *      例子：double d1 = 123.4。
     * 布尔型：
     * boolean：(1位)
     *      boolean数据类型表示一位的信息；
     *      只有两个取值：true和false；
     *      这种类型只作为一种标志来记录true/false情况；
     *      默认值是false；
     *      例子：boolean one = true。
     *      字符型：
     * char：（16）
     *      char类型是一个单一的16位Unicode字符；
     *      最小值是’\u0000’（即为0）；
     *      最大值是’\uffff’（即为65,535）；
     *      char数据类型可以储存任何字符；
     *      例子：char letter = ‘A’。
     *      注意：String不是基本类型。
     *
     */
}
