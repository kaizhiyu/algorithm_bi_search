package com.algorithm.$_linux_cmd;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

/**
 * @author fanhb on 2018/1/25
 * @desc xxxx
 */

@SearchKeyWord("crontab linux定时任务")
public class Linux_cmd {

    //http://blog.csdn.net/lv_shijun/article/details/70263919

/**
 *   系统背景：cenos6.5
     操作工具：Xshell5

     情况一：正常情况（系统有service命令）
     重启服务命令：[root@centos6 /]# service crond restart

     启动服务命令：[root@centos6 /]# service crond start
     停止服务命令：[root@centos6 /]# service crond stop

     情况二：当linux发行的版本没有service这个命令时候，用如下命令进行停止启动：
     停止服务：[root@centos6 /]# /etc/init.d/cron空格stop
     启动服务：[root@centos6 /]# /etc/init.d/cron空格start
 */
}
