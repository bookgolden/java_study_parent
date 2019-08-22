### 1、原始的JDBC方式
  	@Autowired
	JdbcTemplate jdbcTemplate;

	@ResponseBody
	@RequestMapping("/getNoticeById/{id}")
	public Map<String, Object> map(@PathVariable("id") Integer id) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_notice WHERE id=" + id);
		return list.get(0);
	}

	@ResponseBody
	@RequestMapping("/insertEntity/{id}")
	public Map<String, Object> insertEntity(@PathVariable("id") Integer id) {
		jdbcTemplate.execute("UPDATE t_notice set title='bob' WHERE id=" + id);
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_notice where id=" + id);
		return list.get(0);
	}
    
### 2、mybatis Mapper注解方式
    pom.xml jar包支持
        <dependency>
    		<groupId>org.mybatis.spring.boot</groupId>
    		<artifactId>mybatis-spring-boot-starter</artifactId>
    		<version>1.3.1</version>
    	</dependency>
	
##### 	开启驼峰命名规则（属性文件中配置）
	mybatis.configuration.map-underscore-to-camel-case=true
	
	
	logging.level.com.atguigu.cache.mapper=debug
    debug=true
	
####     2-1、注解方式
    @Mapper  //会被容器自己动加载
    public interface TblUserMapper {
    	@Select("select * from tbl_user where id=#{id}")
    	public TblUser getUser(Integer id);
    }
    
    @RestController
    public class TblUserController {
    	@Autowired
    	private TblUserMapper tblUserMapper;
    	
    	@RequestMapping("/getUserById/{id}")
    	public TblUser getUserById(@PathVariable("id") Integer id) {
    		return tblUserMapper.getUser(id);
    	}
    }
    //纯pojo类即可
    @Data
    public class TblUser {
    	private Integer id;
    	private String email;
    	private String lastName;
	}

#####     驼峰转换配置（java配置方式中 解决驼峰命名问题）
    @Configuration
    public class MyMvcConfig {
    	@Bean
    	public ConfigurationCustomizer configurationCustomizer() {
    		return new ConfigurationCustomizer() {
    			@Override
    			public void customize(org.apache.ibatis.session.Configuration configuration) {
    				configuration.setMapUnderscoreToCamelCase(true);
    			}
    		};
    	}
    	
    	//嵌入式servlet容器配置
    	@Bean
    	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
    		return new EmbeddedServletContainerCustomizer() {
    			@Override
    			public void customize(ConfigurableEmbeddedServletContainer container) {
    				container.setPort(8083);
    			}
    		};
    	}
    }
    
#####         全局扫描Mapper替代在每个接口中加@Mapper注解
        @MapperScan(value = "com.java.boot.mapper")
        @SpringBootApplication
        public class HelloMainApplication {
        	public static void main(String[] args) {
        		SpringApplication.run(HelloMainApplication.class, args);
        	}
        }

####     2-2、xml配置文件方式
####         application.yml
        mybatis:
          # 指定全局配置文件位置
          config-location: classpath:mybatis/mybatis-config.xml
          # 指定sql映射文件位置
          mapper-locations: classpath:mybatis/mapper/*.xml
        
#####         /cn-boot/src/main/resources/mybatis/mapper/AppAdUserMapper.xml
           <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
            <mapper namespace="com.java.boot.mapper.AppAdUserMapper">
                <select id="getAPPAdUserById" resultType="com.java.boot.bean.AppAdUser">
                    SELECT * FROM t_app_ad_user WHERE id=#{id}
                </select>
            </mapper>
            
#####         /cn-boot/src/main/resources/mybatis/mybatis-config.xml
           <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
            <configuration>
                <settings>
                    <setting name="mapUnderscoreToCamelCase" value="true"/>  //xml配置方式中 解决驼峰命名问题
                </settings>
            </configuration>
        
#####         接口定义
        public interface AppAdUserMapper {
        	public AppAdUser getAPPAdUserById(Integer id);
        }
        
#####         @RestController
        public class APPAdUserController {
        	@Autowired
        	private AppAdUserMapper appAdUserMapper;
        	
        	@RequestMapping("/getAppAdUser/{id}")
        	public AppAdUser getAppAdUser(@PathVariable("id") Integer id) {
        		return appAdUserMapper.getAPPAdUserById(id);
        	}
        }
    
### 3、JPA方式(实现Repository接口方式)
#####     pom.xml jar包支持
        <dependency>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-starter-data-jpa</artifactId>
    	</dependency>
		
#####     application.yml
        spring:
            jpa:
                show-sql: true      //打印执行SQL
                hibernate:
                  ddl-auto: update  //如果表不存在则自动创建，存在则更新
    
#####     继承JpaRepository来完成对数据库的操作
    public interface NoticeRepository extends JpaRepository<NoticeBean, Integer> {
    }
    
    @Entity
    @Table(name = "t_notice")
    public class NoticeBean {
    	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
    	private Integer id;
    	@Column //不指定name，默认为属性名
    	private String title;
    	@Column
    	private String content;
    	@Column
    	private String link;
    	@Column(name = "start_time")
    	private Date startTime;
    	@Column(name = "end_time")
    	private Date endTime;
    	@Column(name = "button_text")
    	private String buttonText;
    	@Column
    	private int status;
    	@Column
    	private int type;
	}
	
    @RestController
    public class NoticeController {    
        @Autowired
    	private NoticeRepository noticeRepository;
    	
    	@RequestMapping("/getOne/{id}")
    	public NoticeBean getOne(@PathVariable("id") Integer id) {
    		return noticeRepository.findOne(id);
    	}
    	
    	@RequestMapping(value = "/insertNoticeBean")
    	public NoticeBean insertNoticeBean(@ModelAttribute NoticeBean bean) {
    		bean = new NoticeBean();
    		bean.setButtonText("Exit");
    		bean.setContent("你好");
    		bean.setEndTime(new Date());
    		bean.setStartTime(new Date());
    		bean.setStatus(0);
    		bean.setTitle("hello");
    		bean.setLink("http://www.baidu.com");
    		return noticeRepository.save(bean);
    	}
    
    	@ResponseBody
    	@RequestMapping("/deleteEntity/{id}")
    	public Map<String, Object> deleteEntity(@PathVariable("id") Integer id) {
    		noticeRepository.delete(id);
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("status", "0");
    		map.put("msg", "success");
    		return map;
    	}
    }