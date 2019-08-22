

##### 处理器适配器
    
    org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter
    
    
##### 处理器映射器

    1、org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
    2、org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
    
##### 视图解析器

    org.springframework.web.servlet.view.InternalResourceViewResolver
    
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>
    
### 注解的处理器映射器和适配器

    在spring3.1之前使用org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping注解映射器。
    
    在spring3.1之后使用org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping注解映射器。
    
    在spring3.1之前使用org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter注解适配器。
    
    在spring3.1之后使用org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter注解适配器。

###     使用 mvc:annotation-driven代替上边注解映射器和注解适配器配置
	mvc:annotation-driven默认加载很多的参数绑定方法，
	比如json转换解析器就默认加载了，如果使用mvc:annotation-driven不用配置上边的RequestMappingHandlerMapping和RequestMappingHandlerAdapter
	实际开发时使用mvc:annotation-driven
    <mvc:annotation-driven></mvc:annotation-driven>

######     对于注解的Handler可以单个配置，实际开发中建议使用组件扫描
    <context:component-scan base-package="cn.itcast.ssm.controller"></context:component-scan>

------
###### 配置json转换器
###### springmvc中使用jackson进行json转换（@requestBody和@responseBody使用下边的包进行json转）

    <!--注解适配器 -->
	<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
		<property name="messageConverters">
		<list>
		    <bean class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"></bean>
		</list>
		</property>
	</bean>

    注意：如果使用<mvc:annotation-driven /> 则不用定义上边的内容


#### 类型格式转换：

    @Component
    public class EmployeeConverter implements Converter<String, Employee> {
    	@Override
    	public Employee convert(String source) {
    		if(source != null){
    			String [] vals = source.split("-");
    			//GG-gg@atguigu.com-0-105
    			if(vals != null && vals.length == 4){
    				String lastName = vals[0];
    				String email = vals[1];
    				Integer gender = Integer.parseInt(vals[2]);
    				Department department = new Department();
    				department.setId(Integer.parseInt(vals[3]));
    				
    				Employee employee = new Employee(null, lastName, email, gender, department);
    				System.out.println(source + "--convert--" + employee);
    				return employee;
    			}
    		}
    		return null;
    	}
    }

###### springmvc.xml
    
    <mvc:annotation-driven conversion-service="conversionService"></mvc:annotation-driven>	
	
	<!-- 配置 ConversionService -->
	<bean id="conversionService"
		class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
		<property name="converters">
			<set>
				<ref bean="employeeConverter"/>
			</set>
		</property>	
	</bean>

    @RequestMapping(value="/emp", method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
#### 拦截器定义

    定义拦截器，实现HandlerInterceptor接口。接口中提供三个方法。

    public class HandlerInterceptor1 implements HandlerInterceptor {
    	
    	//进入 Handler方法之前执行
    	//用于身份认证、身份授权
    	//比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
    	@Override
    	public boolean preHandle(HttpServletRequest request,
    			HttpServletResponse response, Object handler) throws Exception {
    		
    		//return false表示拦截，不向下执行
    		//return true表示放行
    		return false;
    	}
    
    	//进入Handler方法之后，返回modelAndView之前执行
    	//应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
    	@Override
    	public void postHandle(HttpServletRequest request,
    			HttpServletResponse response, Object handler,
    			ModelAndView modelAndView) throws Exception {
    	}
    
    	//执行Handler完成执行此方法
    	//应用场景：统一异常处理，统一日志处理
    	@Override
    	public void afterCompletion(HttpServletRequest request,
    			HttpServletResponse response, Object handler, Exception ex)
    			throws Exception {
    		
    	}
    }

###### 根据测试结果，拦截器最佳应用

    比如：统一日志处理拦截器，需要该 拦截器preHandle一定要放行，且将它放在拦截器链接中第一个位置。
    
    比如：登陆认证拦截器，放在拦截器链接中第一个位置。权限校验拦截器，放在登陆认证拦截器之后。（因为登陆通过后才校验权限）





