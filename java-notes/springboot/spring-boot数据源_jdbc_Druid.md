    
##### 1、JDBC方式：
    1-1、pom.xml文件引入依赖支持
    
    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-jdbc</artifactId>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
	</dependency>

	1-2、配置yml文件
	
    spring:
      datasource:
        username: mobilemoney
        password: devel@D3rjJpB6
        url: jdbc:mysql://10.141.8.141:3306/mobilemoney
        driver-class-name: com.mysql.jdbc.Driver
    
##### 2、Druid方式（数据源及监控配置）

    2-1、pom.xml文件引入依赖支持
    
    <dependencies>
    	<dependency>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-starter-jdbc</artifactId>
    	</dependency>
    	<dependency>
    		<groupId>mysql</groupId>
    		<artifactId>mysql-connector-java</artifactId>
    		<scope>runtime</scope>
    	</dependency>
    	<!--引入druid数据源 -->
    	<dependency>
    		<groupId>com.alibaba</groupId>
    		<artifactId>druid</artifactId>
    		<version>1.1.8</version>
    	</dependency>
    	<dependency>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-starter-web</artifactId>
    	</dependency>
    	<dependency>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-starter-test</artifactId>
    		<scope>test</scope>
    	</dependency>
    </dependencies>
	
    2-2、配置yml文件
    
    spring:
      datasource:
        username: mobilemoney
        password: devel@D3rjJpB6
        url: jdbc:mysql://10.141.8.141:3306/mobilemoney
        driver-class-name: com.mysql.jdbc.Driver
        type: com.alibaba.druid.pool.DruidDataSource  #指定数据源类型
    
        initialSize: 5
        minIdle: 5
        maxActive: 20
        maxWait: 60000
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
    #   配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        filters: stat,wall,log4j
        maxPoolPreparedStatementPerConnectionSize: 20
        useGlobalDataSourceStat: true
        connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
    #    schema:
    #      - classpath:department.sql
    
    
    2-3、Druid配置文件
    
    @Configuration
    public class DruidConfig {
        @ConfigurationProperties(prefix = "spring.datasource")
        @Bean
        public DataSource druid(){
           return  new DruidDataSource();
        }
    
        //配置Druid的监控
        //1、配置一个管理后台的Servlet
        @Bean
        public ServletRegistrationBean statViewServlet(){
            ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
            Map<String,String> initParams = new HashMap<>();
            initParams.put("loginUsername","admin");
            initParams.put("loginPassword","123456");
            initParams.put("allow","");//默认就是允许所有访问
            initParams.put("deny","192.168.15.21");
            bean.setInitParameters(initParams);
            return bean;
        }
    
        //2、配置一个web监控的filter
        @Bean
        public FilterRegistrationBean webStatFilter(){
            FilterRegistrationBean bean = new FilterRegistrationBean();
            bean.setFilter(new WebStatFilter());
    
            Map<String,String> initParams = new HashMap<>();
            initParams.put("exclusions","*.js,*.css,/druid/*");
            bean.setInitParameters(initParams);
            bean.setUrlPatterns(Arrays.asList("/*"));
            return  bean;
        }
    }

    2-4、监控地址
        http://localhost:8080/druid/login.html


###     测试数据库：

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class SpringBoot06DataJdbcApplicationTests {
    	@Autowired
    	DataSource dataSource;
    	@Test
    	public void contextLoads() throws SQLException {
    		//org.apache.tomcat.jdbc.pool.DataSource
    		System.out.println(dataSource.getClass());
    		Connection connection = dataSource.getConnection();
    		System.out.println(connection);
    		connection.close();
    	}
    }
    
    测试输出：
    1、
    class org.apache.tomcat.jdbc.pool.DataSource
    ProxyConnection[PooledConnection[com.mysql.jdbc.JDBC4Connection@7f7c420c]]
    2、
    class com.alibaba.druid.pool.DruidDataSource
    com.mysql.jdbc.JDBC4Connection@3762c4fc
    
###     程序执行SQL

    @Controller
    public class HelloController {
        @Autowired
        JdbcTemplate jdbcTemplate;
        @ResponseBody
        @GetMapping("/query")
        public Map<String,Object> map(){
            List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM t_notice");
            return list.get(0);
        }
    }