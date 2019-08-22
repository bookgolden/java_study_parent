
    
####     解决post请求乱码问题

    在web.xml中加入：
    
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
####     解决get请求中文参数出现乱码解决方法有两个
    
    1、修改tomcat配置文件添加编码与工程编码一致
    <Connector URIEncoding="utf-8" connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>
    
    2、另外一种方法对参数进行重新编码：
    String userName = new String(request.getParamter("userName").getBytes("ISO8859-1"),"utf-8");

    ISO8859-1是tomcat默认编码，需要将tomcat编码后的内容按utf-8编码
    
    