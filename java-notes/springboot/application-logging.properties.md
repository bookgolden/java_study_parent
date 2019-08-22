
    debug=true
    
    server.port=80
    
    server.context-path=/boot02
    
    spring.http.encoding.enabled=true
    spring.http.encoding.charset=utf-8
    spring.http.encoding.force=true

    spring.mvc.view.prefix=/WEB-INF/
    spring.mvc.view.suffix=.jsp
    
    
    ------------------------
    #thymeleaf
    spring.thymeleaf.prefix=classpath:/templates/
    spring.thymeleaf.suffix=.html
    spring.thymeleaf.cache=false
    spring.thymeleaf.content-type=text/html
    spring.thymeleaf.enabled=true
    spring.thymeleaf.encoding=UTF-8
    spring.thymeleaf.mode=HTML5
    # mybatis
    mybatis.type-aliases-package=com.imooc.miaosha.domain
    mybatis.configuration.map-underscore-to-camel-case=true
    mybatis.configuration.default-fetch-size=100
    mybatis.configuration.default-statement-timeout=3000
    mybatis.mapperLocations = classpath:com/imooc/miaosha/dao/*.xml
    # druid
    spring.datasource.url=jdbc:mysql://10.106.154.105:3306/boot?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false
    spring.datasource.username=root
    spring.datasource.password=123456
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
    spring.datasource.filters=stat
    spring.datasource.maxActive=1000
    spring.datasource.initialSize=100
    spring.datasource.maxWait=60000
    spring.datasource.minIdle=500
    spring.datasource.timeBetweenEvictionRunsMillis=60000
    spring.datasource.minEvictableIdleTimeMillis=300000
    spring.datasource.validationQuery=select 'x'
    spring.datasource.testWhileIdle=true
    spring.datasource.testOnBorrow=false
    spring.datasource.testOnReturn=false
    spring.datasource.poolPreparedStatements=true
    spring.datasource.maxOpenPreparedStatements=20
    #redis
    redis.host=10.106.154.105
    redis.port=6379
    redis.timeout=10
    #redis.password=123456
    redis.poolMaxTotal=1000
    redis.poolMaxIdle=500
    redis.poolMaxWait=500
    

### 日志输出：
   
    logging.level.com.java.boot=info

    logging.path=/com/logs/                     结果：D:\com\logs\spring.log
    logging.path=D:\com\logs                    结果：D:\com\logs\spring.log
    #或
    #logging.file=D:\\com\\logs\\web-info.log   结果：D:\com\logs\web-info.log
    #logging.file=D:/com/logs/web-debug.log     结果：D:\com\logs\web-debug.log
    #logging.file=web-debug.log                 结果：默认在项目根目录下

    属性logging.path 与 logging.file 为互斥,
    logging.file优先级高于logging.path
    
#####     logback-spring中设置日志相关配置信息，参看logback-spring.xml.md文件中的笔记