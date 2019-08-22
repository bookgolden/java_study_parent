

    ps -ef | grep redis| grep -v root | awk '{print $2}' | xargs kill -9  
    
    ./src/redis-cli --cluster create 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006 --cluster-replicas 1
    
    ./src/redis-cli -c -p 7001
    
    /workspace/redis-5.0.5/utils/create-cluster/create-cluster stop
    /workspace/redis-5.0.5/utils/create-cluster/create-cluster start
    
    127.0.0.1:7001> cluster info
    127.0.0.1:7001> cluster nodes










    port 7001  #端口
    cluster-enabled yes #启用集群模式
    cluster-config-file nodes.conf
    cluster-node-timeout 5000 #超时时间
    appendonly yes
    daemonize yes #后台运行
    protected-mode no #非保护模式
    pidfile  /var/run/redis_7001.pid

    /workspace/redis-5.0.5/src/redis-server /workspace/cluster_redis/7001/redis.conf
    /workspace/redis-5.0.5/src/redis-server /workspace/cluster_redis/7002/redis.conf
    /workspace/redis-5.0.5/src/redis-server /workspace/cluster_redis/7003/redis.conf
    /workspace/redis-5.0.5/src/redis-server /workspace/cluster_redis/7004/redis.conf
    /workspace/redis-5.0.5/src/redis-server /workspace/cluster_redis/7005/redis.conf
    /workspace/redis-5.0.5/src/redis-server /workspace/cluster_redis/7006/redis.conf


    ./src/redis-cli --cluster create 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 12

###      redis5 提供了关闭集群的工具，在如下目录：
     /workspace/redis-5.0.5/utils/create-cluster
     
    [root@nginx create-cluster]# vim create-cluster 
    #!/bin/bash
    
    # Settings
    PORT=7000
    TIMEOUT=2000
    NODES=6
    REPLICAS=1
    
    
    127.0.0.1:7001> cluster info
    cluster_state:ok
    cluster_slots_assigned:16384
    cluster_slots_ok:16384
    cluster_slots_pfail:0
    cluster_slots_fail:0
    cluster_known_nodes:6
    cluster_size:3
    cluster_current_epoch:6
    cluster_my_epoch:1
    cluster_stats_messages_ping_sent:2686
    cluster_stats_messages_pong_sent:2487
    cluster_stats_messages_sent:5173
    cluster_stats_messages_ping_received:2487
    cluster_stats_messages_pong_received:2681
    cluster_stats_messages_received:5168
    127.0.0.1:7001> cluster nodes
    78f367593e3da9c8350d2d393c273231d65e0873 127.0.0.1:7006@17006 slave 60da82ee057570d7ebb4e5e082199c330801acdd 0 1556204005976 6 connected
    60da82ee057570d7ebb4e5e082199c330801acdd 127.0.0.1:7002@17002 master - 0 1556204005000 2 connected 5461-10922
    a5fdcdcca96adfe21a78f604a5fc19b2a4b1f866 127.0.0.1:7003@17003 master - 0 1556204007996 3 connected 10923-16383
    5ced1d25f5bb7e4875b1c5d2d14a83952c916910 127.0.0.1:7005@17005 slave 1a00025d1ecd5f9d6b00e6d33b27b295b967e18d 0 1556204007000 5 connected
    1a00025d1ecd5f9d6b00e6d33b27b295b967e18d 127.0.0.1:7001@17001 myself,master - 0 1556204002000 1 connected 0-5460
    56d42a4e152174c5f665c74002d7b4dc6b78cdd7 127.0.0.1:7004@17004 slave a5fdcdcca96adfe21a78f604a5fc19b2a4b1f866 0 1556204005000 4 connected
    127.0.0.1:7001> 