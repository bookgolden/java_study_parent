mysql安装
    
    [root@storm4 ~]# docker run -p 3306:3306 --name mysql -v /sda3/mysql/conf:/etc/mysql/conf.d -v /sda3/mysql/logs:/logs -v /sda3/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
    deeacfd13c071fed158348628dd5af4cefeecd94fb9971b2e4f479dcd9e4bd23
    
    [root@storm4 ~]# docker ps
    CONTAINER ID        IMAGE               COMMAND                CREATED             STATUS              PORTS                               NAMES
    deeacfd13c07        mysql               "docker-entrypoint.s   30 seconds ago      Up 28 seconds       0.0.0.0:3306->3306/tcp, 33060/tcp   mysql               
    
    [root@storm4 ~]# docker exec -it deeacfd13c07 /bin/bash
    
    root@deeacfd13c07:/# mysql -uroot -p123456
    mysql: [Warning] Using a password on the command line interface can be insecure.
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    Your MySQL connection id is 8
    Server version: 8.0.17 MySQL Community Server - GPL
    
    Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.
    
    Oracle is a registered trademark of Oracle Corporation and/or its
    affiliates. Other names may be trademarks of their respective
    owners.
    
    Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
    mysql> 
    
    mysql> select host,user,plugin,authentication_string from mysql.user;
    +-----------+------------------+-----------------------+------------------------------------------------------------------------+
    | host      | user             | plugin                | authentication_string                                                  |
    +-----------+------------------+-----------------------+------------------------------------------------------------------------+
    | %         | root             | mysql_native_password | *6BB4837EB74329105EE4568DDA7DC67ED2CA2AD9                              |
    | localhost | mysql.infoschema | caching_sha2_password | $A$005$THISISACOMBINATIONOFINVALIDSALTANDPASSWORDTHATMUSTNEVERBRBEUSED |
    | localhost | mysql.session    | caching_sha2_password | $A$005$THISISACOMBINATIONOFINVALIDSALTANDPASSWORDTHATMUSTNEVERBRBEUSED |
    | localhost | mysql.sys        | caching_sha2_password | $A$005$THISISACOMBINATIONOFINVALIDSALTANDPASSWORDTHATMUSTNEVERBRBEUSED |
    | localhost | root             | mysql_native_password | *6BB4837EB74329105EE4568DDA7DC67ED2CA2AD9                              |
    +-----------+------------------+-----------------------+------------------------------------------------------------------------+
    5 rows in set (0.00 sec)
    
    mysql> select version();
    +-----------+
    | version() |
    +-----------+
    | 8.0.17    |
    +-----------+
    1 row in set (0.00 sec)

    mysql> 
    