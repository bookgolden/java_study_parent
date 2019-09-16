###    centos7.6 + docker + redis 5.0.5
    
    1、
    cat /home/redis-cluster/redis-cluster.tmpl
        port ${PORT}
        protected-mode no
        cluster-enabled yes
        cluster-config-file nodes.conf
        cluster-node-timeout 5000
        cluster-announce-ip 192.168.1.7
        cluster-announce-port ${PORT}
        cluster-announce-bus-port 1${PORT}
        appendonly yes
    
    2、
    for port in `seq 7001 7006`; do \
      mkdir -p ./${port}/conf \
      && PORT=${port} envsubst < ./redis-cluster.tmpl > ./${port}/conf/redis.conf \
      && mkdir -p ./${port}/data; \
    done
    
    
    3、
    for port in `seq 7001 7006`; do \
      docker run -d -ti \
      -v /home/redis-cluster/${port}/conf/redis.conf:/usr/local/etc/redis/redis.conf \
      -v /home/redis-cluster/${port}/data:/data \
      --restart always --name redis-${port} --net host \
      --sysctl net.core.somaxconn=1024 redis redis-server /usr/local/etc/redis/redis.conf; \
    done
    
    4、docker exec -it redis-7001 bash
    
    5、
    /usr/local/bin/redis-cli --cluster create \
        192.168.1.7:7001 \
        192.168.1.7:7002 \
        192.168.1.7:7003 \
        192.168.1.7:7004 \
        192.168.1.7:7005 \
        192.168.1.7:7006 \
        --cluster-replicas 1
    
    6、redis-cli -c -p 7003




    