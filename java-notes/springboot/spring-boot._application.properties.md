

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