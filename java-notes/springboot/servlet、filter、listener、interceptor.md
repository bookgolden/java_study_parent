
#####     1、servlet注解方式
        @ServletComponentScan  
        @SpringBootApplication 
        
        @WebServlet(urlPatterns = "/hello")  
        public class MyServlet extends HttpServlet {}
        
        @WebFilter(urlPatterns = "/*")  
        public class MyFilter implements Filter {}
        
        @WebListener  
        public class MyListener implements ServletContextListener {}

#####     2、spring的注入方式:
    public class Config {
    	@Bean
    	public ServletRegistrationBean myServlet() {
    		// 配置servlet及其请求路径
    		return new ServletRegistrationBean(new MyServlet(), "/hello");
    	}
        
        @Bean
        public ServletRegistrationBean myServlet2(){
            ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
            registrationBean.setLoadOnStartup(1);
            return registrationBean;
        }
        
    	// 过滤器
    	@Bean
    	public FilterRegistrationBean myFilter() {
    		FilterRegistrationBean myFilter = new FilterRegistrationBean();
    		// 配置过滤器
    		myFilter.setFilter(new MyFilter());
    		// 配置过滤路径
    		myFilter.addUrlPatterns("/*");
    		return myFilter;
    	}
        
        @Bean
        public FilterRegistrationBean myFilter2(){
            FilterRegistrationBean registrationBean = new FilterRegistrationBean();
            registrationBean.setFilter(new MyFilter());
            registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
            return registrationBean;
        }
    
        	// 监听器
        	@Bean
        	public ServletListenerRegistrationBean<MyListener> myListener() {
        		return new ServletListenerRegistrationBean<MyListener>(new MyListener());
        	}
        
        	public static void main(String[] args) {
        		SpringApplication.run(Config.class, args);
        	}
        }
        
#####         3、spring-boot注入拦截器
    
    //使用WebMvcConfigurerAdapter来扩展SpringMVC的功能
    @Configuration
    public class MyMvcConfig extends WebMvcConfigurerAdapter {
    
    	@Override
    	public void addViewControllers(ViewControllerRegistry registry) {
    		// 浏览器发送 /atguigu 请求来到 success
    		registry.addViewController("/mvcViewer").setViewName("success");
    	}
    
    	@Override
    	public void addInterceptors(InterceptorRegistry registry) {
    		registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/", "/user/login");
    		super.addInterceptors(registry);
    	}
    	
    	//或
    	
    	@Bean
    	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
    		return new WebMvcConfigurerAdapter() {
    			@Override
    			public void addViewControllers(ViewControllerRegistry registry) {
    				registry.addViewController("/").setViewName("login");
    				registry.addViewController("/index.html").setViewName("login");
    			}
    			
    			@Override
    			public void addInterceptors(InterceptorRegistry registry) {
    				registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/", "/user/login");
    				super.addInterceptors(registry);
    			}
    		};
    	}
    }
    