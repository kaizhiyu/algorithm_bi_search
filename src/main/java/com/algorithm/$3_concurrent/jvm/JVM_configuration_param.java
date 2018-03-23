package com.algorithm.$3_concurrent.jvm;

import com.algorithm.$8_annotation.single.ann.Doc4Desc;
import com.algorithm.$8_annotation.single.ann.Location;

/**
 * @auth v_fanhaibo on   2018/3/23
 */

@Location("JVM_param JVM")
@Doc4Desc("JVM 命令行工具")
public class JVM_configuration_param {

    /**
     * https://blog.csdn.net/u013063153/article/details/75729830

     ************************* JMAP -HEAP 命令详解  ***********************
     ***** https://blog.csdn.net/u014227228/article/details/41858031  *****
     **********************************************************************
     *
     Heap Configuration:
     MinHeapFreeRatio         = 40                                  :
     MaxHeapFreeRatio         = 70                                  :JVM堆最大空闲比率
     MaxHeapSize              = 104857600 (100.0MB)                 :设置JVM堆的最大大小
     NewSize                  = 34930688 (33.3125MB)                :设置JVM堆的‘新生代’的默认大小
     MaxNewSize               = 34930688 (33.3125MB)                :设置JVM堆的‘新生代’的最大大小
     OldSize                  = 69926912 (66.6875MB)                :设置JVM堆的‘老生代’的大小
     NewRatio                 = 2                                   :‘新生代’和‘老生代’的大小比率
     SurvivorRatio            = 8                                   :设置年轻代中Eden区与Survivor区的大小比值
     MetaspaceSize            = 21807104 (20.796875MB)              :
     CompressedClassSpaceSize = 1073741824 (1024.0MB)               :
     MaxMetaspaceSize         = 17592186044415 MB                   :
     G1HeapRegionSize         = 0 (0.0MB)                           :

     Heap Usage:                                    :
     New Generation (Eden + 1 Survivor Space):                      :
     capacity = 31457280 (30.0MB)                                   :
     used     = 3920152 (3.7385482788085938MB)                                  :
     free     = 27537128 (26.261451721191406MB)                                 :
     12.461827596028646% used                                   :

     Eden Space:                                    :
     capacity = 27983872 (26.6875MB)                                    :
     used     = 3920152 (3.7385482788085938MB)                                  :
     free     = 24063720 (22.948951721191406MB)                                 :
     14.008611817549765% used                                   :
     From Space:                                    :
     capacity = 3473408 (3.3125MB)                                  :
     used     = 0 (0.0MB)                                   :
     free     = 3473408 (3.3125MB)                                  :
     0.0% used                                  :
     To Space:                                  :
     capacity = 3473408 (3.3125MB)                                  :
     used     = 0 (0.0MB)                                   :
     free     = 3473408 (3.3125MB)                                  :
     0.0% used                                  :
     tenured generation:                                    :
     capacity = 69926912 (66.6875MB)                                    :
     used     = 0 (0.0MB)                                   :
     free     = 69926912 (66.6875MB)                                    :
     0.0% used                                  :
     :
     1836 interned Strings occupying 162888 bytes.                                  :

     */
}
