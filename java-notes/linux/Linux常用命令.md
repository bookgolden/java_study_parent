
#####     硬件资源查看命令
    
    top、 free、 iostat、 vmstat
    
    
    查看线程数
    ulimit -u
    
    
###     linux下检测远程端口是否打开
    
    方法一. 常用telnet 118.10.6.128 88方式测试远程主机端口是否打开。

    
    方法二. nmap ip -p port 测试端口
    
    nmap ip 显示全部打开的端口
    
    根据显示close/open确定端口是否打开。
     
    
    方法三. nc -v host port
    
    端口未打开返回状态为非0
    
    
    
    tasklist|findstr "8080"
    netstat -ano |findstr 2612
    tasklist|findstr  "1008"