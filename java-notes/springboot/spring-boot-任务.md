
### 1、异步处理任务

#####     @EnableAsync 用于配置文件，开启异步功能
#####     @Async       用于异步的方法上
    
    @EnableAsync	//开户异步注解功能
    @SpringBootApplication
    public class Springboot04TaskApplication {
    
    	public static void main(String[] args) {
    		SpringApplication.run(Springboot04TaskApplication.class, args);
    	}
    }
    
    @Service
    public class AsyncService {
    	@Async
    	public void hello() {
    		try {
    			Thread.sleep(3000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		System.out.println("处理数据中...");
    	}
    }
    
    @RestController
    public class AsyncController {
    	@Autowired
    	private AsyncService asyncService;
    
    	@RequestMapping("/hello")
    	public String hello() {
    		asyncService.hello();
    		return "success";
    	}
    }
    


### 2、定时任务

    秒（0~59）
    分钟（0~59）
    小时（0~23）
    天（月）（0~31，但是你需要考虑你月的天数）
    月（0~11）
    天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
    7.年份（1970－2099）
    
    【0 0/5 14,18 * * ?】每天14点整，和18点整，每个5分钟执行一次
    【0 15 10 ? * 1-6】每个月的周一至周六10:15分执行一次
    【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
    【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
    【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次
    
#####     @EnableScheduling :配置文件增加启动定时任务功能
#####     @Scheduled :用于方法上，标注访方法为定时任务方法
    
    
    @Service
    public class schedule {
    @Scheduled(cron = "0/4 * * * * MON-SAT")
    public void hello(){
        System.out.println("test scheduled ~ ");
    }
}

### 3、邮件发送任务

属性配置：

    spring.mail.username=196144190@qq.com
    spring.mail.password=dnyihozzssivbifj
    spring.mail.host=smtp.qq.com
    spring.mail.properties.mail.smtp.ssl.enable=true

java程序执行：

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class SendMailTest {
    
        @Autowired
        JavaMailSenderImpl javaMailSender;
    
        //普通文本发送
        @Test
        public void sendSimpleMailTest() {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("通知-今晚开会");
            simpleMailMessage.setText("今晚7:10开会");
            simpleMailMessage.setTo("1030868639@qq.com");
            simpleMailMessage.setFrom("196144190@qq.com");
            javaMailSender.send(simpleMailMessage);
        }
    
        //带附件的
        @Test
        public void mimeMessageTest() throws MessagingException {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
    
            mimeMessageHelper.setSubject("广播：好消息告知~");
            mimeMessageHelper.setText("<b style='color:red'>今天7:30开会");
            mimeMessageHelper.setTo("1030868639@qq.com");
            mimeMessageHelper.setFrom("196144190@qq.com");
    
            mimeMessageHelper.addAttachment("1.jpg", new File("D:\\PIC\\1-1406300Z543-50.jpg"));
            mimeMessageHelper.addAttachment("1.jpg", new File("D:\\PIC\\56652836b3b21.jpg"));
    
            javaMailSender.send(mimeMessage);
        }
    }

