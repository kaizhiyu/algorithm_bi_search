package com.algorithm.$_linux_cmd;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

/**
 * @author fanhb on 2018/1/25
 * @desc xxxx
 */

@SearchKeyWord("springboot linux中的启动方式")
//https://www.cnblogs.com/woms/p/6145688.html
public class Linux_cmd_vm {

    //修改linux root密码 虚拟机 centos7
    //http://www.linuxidc.com/Linux/2016-08/134034.htm

    /**

     https://www.cnblogs.com/silent2012/archive/2015/07/28/4682770.html

     1:查看ip地址 
     ip addr        (ifconfig 被取消)

     2：主机名
     hostnamectl set-hostname xxx   (vi /etc/sysconfig/network)
     3: 防火墙
     a:  firewall-cmd --state (查看防火墙状态)
     b:  systemctl stop/start firewalld.service (开关防火墙)

     4：查看Linux版本
     lsb_release -a
     cat /proc/version
     cat /etc/issue
     cat /etc/redhat-release
     uname -a
     5:cpu
     cat /proc/cpuinfo
     6:机器位数
     getconf LONG_BIT
     7:远程拷贝命令
     scp -r /jdk/ root@ip:/opt/


     systemctl stop firewalld.service #停止firewall
     systemctl disable firewalld.service #禁止firewall开机启动
     yum install xinetd telnet telnet-server -y
     systemctl enable telnet.socket

     yum install -y nc
     http://blog.csdn.net/paicmis/article/details/53431163

     */



}
