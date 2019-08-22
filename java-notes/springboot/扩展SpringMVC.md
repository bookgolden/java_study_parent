
编写一个配置类（@Configuration），是WebMvcConfigurerAdapter类型；不能标注@EnableWebMvc;
既保留了所有的自动配置，也能用我们扩展的配置

    //使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
    @Configuration
    public class MyMvcConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            // super.addViewControllers(registry);
            //浏览器发送 /atguigu 请求来到 success
            registry.addViewController("/atguigu").setViewName("success");
        }
    }
    
    原理：
    1）、WebMvcAutoConfiguration是SpringMVC的自动配置类
    2）、在做其他自动配置时会导入；@Import(EnableWebMvcConfiguration.class)