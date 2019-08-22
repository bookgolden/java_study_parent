
    @ImportResource({"classpath:spring1.xml" , "classpath:spring2.xml"})
    
---

######     ConfigurationProperties
    
    springboot 1.5版本以及之前采用:
    @ConfigurationProperties(prefix="user",locations={"classpath:user.propeties"})
    public class User {
        private String username;
        private Integer age;
    }
    
    springboot 1.5版本以后采用如下: 
    @PropertySource(value ="classpath:user.properties")
    @ConfigurationProperties(prefix = "user")
    @Component
    public class User {
        private String username;
        private Integer age;
    }
    
    @EnableConfigurationProperties 以上配置需要在main启动函数类文件上激活配置: 新版本中默认开启.
    
    @Value("${name}")   加载单个属性
    private String name;.
    
    
    ViewResolver（ContentNegotiatingViewResolver）        视图解析器

### @EnableWebMvc全面接管SpringMVC

### 激活指定profile
    1、在配置文件中指定 spring.profiles.active=dev
    2、命令行：
    java -jar spring-boot-02-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev；
    可以直接在测试的时候，配置传入命令行参数
    3、虚拟机参数；
    -Dspring.profiles.active=dev
    
    
###     @AutoConfigureAfter
    
    @Configuration
    @AutoConfigureAfter(MyBatisConfig.class) //保证在MyBatisConfig实例化之后再实例化该类
    public class MapperScannerConfig {
        // mapper接口的扫描器
        @Bean
        public MapperScannerConfigurer mapperScannerConfigurer() {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setBasePackage("com.taotao.cart.mapper");
            return mapperScannerConfigurer;
        }
    }
    

    
### 配置文件加载位置    
    springboot 启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文
    件
    –file:./config/
    –file:./
    –classpath:/config/
    –classpath:/
    优先级由高到底，高优先级的配置会覆盖低优先级的配置；
    server:
    port: 8081
    spring:
    profiles:
    active: prod
    ‐‐‐
    server:
    port: 8083
    spring:
    profiles: dev
    ‐‐‐
    server:
    port: 8084
    spring:
    profiles: prod #指定属于哪个环境
    SpringBoot会从这四个位置全部加载主配置文件；互补配置；
    我们还可以通过spring.config.location来改变默认的配置文件位置
    项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置；指定配置文件和默
    认加载的这些配置文件共同起作用形成互补配置；
    java -jar spring-boot-02-config-02-0.0.1-SNAPSHOT.jar --spring.config.location=G:/application.properties
    
    java -jar spring-boot-02-config-02-0.0.1-SNAPSHOT.jar --server.port=8087 --spring.config.location=G:/application.properties --server.context-path=/abc