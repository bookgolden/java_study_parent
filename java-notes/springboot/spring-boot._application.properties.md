

    server.port= 8082
    server.context-path=/ll
    spring.profiles.active=prod
    
    server:
      port: 8084
    spring:
      profiles: prod
      
    # 我们能配置的属性都是来源于这个功能的properties类
    spring.http.encoding.enabled=true
    spring.http.encoding.charset=utf-8
    spring.http.encoding.force=true


###     pom.xml
    <properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

    @ImportResource(locations = {"classpath:beans.xml"})
    
    
---
    https://www.bbsmax.com/A/o75NXo9MzW/
    https://www.bbsmax.com/A/q4zVQa4bdK/
    
### spring boot maven打包问题：

    执行mvn clean package，报错如下（说点不相关的，使用install同理。因为spring-boot:repackage目标（goal）（下文会说）被绑定在package构建阶段（phases），
    而package阶段在install阶段之前，指定构建阶段之前的阶段都会执行。详细参见：Introduction to the Build Lifecycle）
    　　
    [ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) on project webapps-api-bid: 
    Execution default of goal org.springframework.boot:spring-boot-maven-plugin:1.5.3.RELEASE:repackage failed: 
    Unable to find a single main class from the following candidates [com.xx.api.main.ApiBidMain, com.xx.webapps.api.main.WebappsApiBidMain]
    
    执行mvn clean package spring-boot:repackage，报错如下，不如上面日志详细
    [ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) on project webapps-api-bid: 
    Execution default of goal org.springframework.boot:spring-boot-maven-plugin:1.5.3.RELEASE:repackage failed: Unable to find main class
    
####  解决方法一、

    [推荐] 通用解决方法：<configuration>下配置mainClass，指定程序入口。
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>1.5.3.RELEASE</version>
        <configuration>
            <mainClass>com.xx.webapps.api.main.WebappsApiBidMain</mainClass>
        </configuration>
        <executions>
            <execution>
                <goals>
                    <goal>repackage</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    
    
    
####  解决方法二、
    如果你的pom继承自spring-boot-starter-parent（注意此前提），也可以直接在<properties>配置<start-class>（其实这里的start-class直接对应清单文件里的Start-Class）：

    <properties>
        <start-class>com.xx.webapps.api.main.WebappsApiBidMain</start-class>
    </properties>
    
    
    简单点说，这货重新打包个可执行的jar/war，可以在命令行使用-jar执行。如果指定layout为NONE那就没有主类只是打个普通的jar（不可执行），一般不会这么做。
    一般情况，这个目标会打一个新的jar/war，并把maven默认打的jar/war添加.original后缀，在target目录下可以看到：