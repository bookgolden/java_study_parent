###     @Transactional
    
    
    
    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-jdbc</artifactId>
	</dependency>

当引入jdbc依赖之后，Spring Boot会自动默认分别注入DataSourceTransactionManager或JpaTransactionManager，所以我们不需要任何额外配置就可以用@Transactional注解进行事务的使用

	@Transactional
	public boolean tx() {
		User u1= new User();
		u1.setId(2);
		u1.setName("2222");
		userDao.insert(u1);
		
		User u2= new User();
		u2.setId(1);
		u2.setName("11111");
		userDao.insert(u2);
		
		return true;
	}

    @Transactional不仅可以注解在方法上，也可以注解在类上，当注解在类上的时候意味着此类的所有public方法都是开启事务的，如果类级别和方法级别同时使用了@Transactional注解，则使用在类级别的注解会重载方法级别的注解