### Spring如何解析Dubbo标签

    1. 要了解Dubbo是如何解析标签的，首先要清楚一点就是Spring如何处理自定义标签的，因为Dubbo的标签可以算是Spring自定义标签的一种情况；
    
　　2. Spring通过两个接口来解析自定义的标签：NamespaceHandler和BeanDefinitionParser接口；NamespaceHandler负责namespace的处理，而BeanDefinitionParser负责Bean的解析；
    
　　3. 我们自定义标签的时候，可以实现NamespaceHandler接口，但更多的是继承NamespaceHandler的抽象类：NamespaceHandlerSupport，然后在classpath的META-INF目录下编写一个spring.handlers文件，在该文件中定义namespace的URL和namespace处理类的映射。
    比如Dubbo的spring.handlers文件内容(/是转义)：
    
    http\://code.alibabatech.com/schema/dubbo=com.alibaba.dubbo.config.spring.schema.DubboNamespaceHandler
　　
    4. spring框架初始化时会加载所有classpath的spring.handlers文件，把namespace的URL和namespace处理类映射到Map中，Spring在解析的时候，遇到一些自定义标签的时候，会在这个Map中查找namespace处理类，使用这个自定义的处理类来进行标签的解析工作；
    
　　5. 同样，Dubbo框架的namespace的处理类是DubboNamespaceHandler，也是实现了NamespaceHandlerSupport接口来处理命名空间，然后在这个类初始化的时候给所有标签都注册各自的解析器；而Dubbo的解析类DubboBeanDefinitionParser同样也是实现了BeanDefinitionParser接口；


    参考：https://www.cnblogs.com/xiaozhang2014/p/7821104.html#top