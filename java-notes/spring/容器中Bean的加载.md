
1、配置类：

    @Import({User.class, Yellow.class, MyImportSelector.class, MyBeanDefinitionRegistrar.class })
    @Configuration
    public class MainConfig {
    	@Bean
    	public User getUser() {
    		User user = new User();
    		user.setName("bob");
    		user.setAge(10);
    		return user;
    	}
    }

1.1、@Component
    
    @Component
    public class Car {
    	public Car(){
    		System.out.println("car constructor...");
    	}
    }
    
1.2、import

    public class User {
    	public User() {
    		System.out.println("User constructor...");
    	}
    	private String name;
    	private int age;
    }

1.3、ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware

    public class Yellow implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
        private ApplicationContext applicationContext;
    	public Yellow() {
    		System.out.println("Yellow constructor...");
    	}
    
    	@Override
    	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    		System.out.println("ApplicationContextAware.setApplicationContext... \n" + applicationContext);
    		this.applicationContext = applicationContext;
    	}
    
    	@Override
    	public void setBeanName(String name) {
    		System.out.println("ApplicationContextAware.setBeanName... \n" + name);
    	}
    
    	@Override
    	public void setEmbeddedValueResolver(StringValueResolver resolver) {
    		String resolveStringValue = resolver.resolveStringValue(" 操作系统=${os.name} , 算术结果=#{20*18}");
    		System.out.println("ApplicationContextAware.setEmbeddedValueResolver... " + resolveStringValue);
    	}
    }
    
    
1.4、ImportSelector

    public class MyImportSelector implements ImportSelector {
    	public MyImportSelector() {
    		System.out.println("MyImportSelector constructor...");
    	}
    	@Override
    	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    		return new String[] {"com.java.boot.bean.Blue"};
    	}
    }
    
1.5、ImportBeanDefinitionRegistrar

    public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    	@Override
    	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    		boolean d1 = registry.containsBeanDefinition("com.java.boot.bean.Blue");
    		boolean d2 = registry.containsBeanDefinition("com.java.boot.bean.Blue");
    		boolean dd =registry.isBeanNameInUse("com.java.boot.bean.Blue");
    		System.out.println(dd);
    		if(d1 && d2) {
    			RootBeanDefinition rainRow = new RootBeanDefinition(RainBow.class);
    			registry.registerBeanDefinition("rainBow", rainRow);
    		}
    	}
    }

测试：

    public class TestAnn {
    	@Test
    	public void test() {
    		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
    		//String[] beanNames = applicationContext.getBeanNamesForType(User.class);
    		String[] beanNames = applicationContext.getBeanDefinitionNames();
    		for(String vs : beanNames) {
    			System.out.println(vs);
    		}
    	}
    }    