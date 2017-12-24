package com.algorithm.$09_zookeeper;

import com.algorithm.$8_annotation.Doc4Desc;

/**
 * @author fanhb on 2017/12/24
 * @desc xxxx
 */

@Doc4Desc("zk 伪集群")
public class Zookeeper_cluster {

    /**
     * 为集群
     *
     *
     1: myid
     2: dir
        dataDir=/opt/runtime/data/zookeeper3
        dataLogDir=/opt/runtime/log/zookeeper3
     3: port
        clientPort=2183
     4: 通信地址等
         server.1=127.0.0.1:2888:3888
         server.2=127.0.0.1:2889:3889
         server.3=127.0.0.1:2890:3890
         server.4=127.0.0.1:2890:3891
     5: 启动:
         ##!bin/bash
         /opt/sleeve/zookeeper1/bin/zkServer.sh start /opt/sleeve/zookeeper1/conf/zoo.cfg
         /opt/sleeve/zookeeper2/bin/zkServer.sh start /opt/sleeve/zookeeper2/conf/zoo.cfg
         /opt/sleeve/zookeeper3/bin/zkServer.sh start /opt/sleeve/zookeeper3/conf/zoo.cfg
         /opt/sleeve/zookeeper4/bin/zkServer.sh start /opt/sleeve/zookeeper4/conf/zoo.cfg
     6: 测试
        /opt/sleeve/zookeeper1/bin/zkCli.sh -server localhost:2181



     */

    /*
     * # The number of milliseconds of each tick
     tickTime=2000
     # The number of ticks that the initial
     # synchronization phase can take
     initLimit=10
     # The number of ticks that can pass between
     # sending a request and getting an acknowledgement
     syncLimit=5
     # the directory where the snapshot is stored.
     # do not use /tmp for storage, /tmp here is just
     # example sakes.

     dataDir=/opt/runtime/data/zookeeper3
     dataLogDir=/opt/runtime/log/zookeeper3
     #/opt/runtime/log/zookeeper
     # the port at which the clients will connect
     clientPort=2183
     # the maximum number of client connections.
     # increase this if you need to handle more clients
     #maxClientCnxns=60
     #
     # Be sure to read the maintenance section of the
     # administrator guide before turning on autopurge.
     #
     # http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
     #
     # The number of snapshots to retain in dataDir
     #autopurge.snapRetainCount=3
     # Purge task interval in hours
     # Set to "0" to disable auto purge feature
     #autopurge.purgeInterval=1




     server.1=127.0.0.1:2888:3888
     server.2=127.0.0.1:2889:3889
     server.3=127.0.0.1:2890:3890
     server.4=127.0.0.1:2890:3891
     */
}
