#### 几个重要的事件回调机制

    配置在META-INF/spring.factories
    ApplicationContextInitializer
    SpringApplicationRunListener
    
    只需要放在ioc容器中
    ApplicationRunner
    CommandLineRunner
    
######     META-INF/spring.factories

    org.springframework.context.ApplicationContextInitializer=\
    com.atguigu.springboot.listener.HelloApplicationContextInitializer
    
    org.springframework.boot.SpringApplicationRunListener=\
    com.atguigu.springboot.listener.HelloSpringApplicationRunListener

######     SpringApplicationRunListener

    public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

        //必须有的构造器
        public HelloSpringApplicationRunListener(SpringApplication application, String[] args){
    
        }
    
        @Override
        public void starting() {
            System.out.println("SpringApplicationRunListener...starting...");
        }
    
        @Override
        public void environmentPrepared(ConfigurableEnvironment environment) {
            Object o = environment.getSystemProperties().get("os.name");
            System.out.println("SpringApplicationRunListener...environmentPrepared.."+o);
        }
    
        @Override
        public void contextPrepared(ConfigurableApplicationContext context) {
            System.out.println("SpringApplicationRunListener...contextPrepared...");
        }
    
        @Override
        public void contextLoaded(ConfigurableApplicationContext context) {
            System.out.println("SpringApplicationRunListener...contextLoaded...");
        }
    
        @Override
        public void finished(ConfigurableApplicationContext context, Throwable exception) {
            System.out.println("SpringApplicationRunListener...finished...");
        }
    }
        
######     ApplicationContextInitializer

    public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            System.out.println("ApplicationContextInitializer...initialize..."+applicationContext);
        }
    }
    
######     ApplicationRunner

    @Component
    public class HelloCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            System.out.println("CommandLineRunner...run..."+ Arrays.asList(args));
        }
    }
    
######     CommandLineRunner

    @Component
    public class HelloApplicationRunner implements ApplicationRunner {
        @Override
        public void run(ApplicationArguments args) throws Exception {
            System.out.println("ApplicationRunner...run....");
        }
    }
    
---
######     执行顺序结果
    
    SpringApplicationRunListener...starting...
    SpringApplicationRunListener...environmentPrepared..Windows 7
    
    ApplicationContextInitializer...initialize...
    
    SpringApplicationRunListener...contextPrepared...
    
    ApplicationRunner...run....
    
    CommandLineRunner...run...[]
    
    SpringApplicationRunListener...finished...
    
    
    
    
    
    
    