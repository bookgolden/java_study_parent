

## dubbo-provider

#### application.properties

    dubbo.application.name=dubbo-provider
    dubbo.registry.address=zookeeper://10.106.154.105:2181
    dubbo.scan.base-packages=com.java.boot.service

####     TicketService.java
    package com.java.boot.service;
    public interface TicketService {
    	public String getTicket();
    }
    
####     TicketServiceImpl.java
    
    package com.java.boot.service.impl;

    import org.springframework.stereotype.Component;
    import com.alibaba.dubbo.config.annotation.Service;
    import com.java.boot.service.TicketService;
    
    @Component
    @Service // 将dubbo服务发布出去
    public class TicketServiceImpl implements TicketService {
    	@Override
    	public String getTicket() {
    		return "厉害了我的国";
    	}
    }

####    ProviderTicketApplication.java
    package com.java.boot;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    
    /**
     * 1、将服务提供者注册到注册中心
     * 1-1、引入dubbo和zkclient相关依赖
     * 1-2、配置dubbo的扫描包和注册中心地址
     * 1-3、使用@Service发布服务
     * @author 1800101645
     */
    @SpringBootApplication
    public class ProviderTicketApplication {
    	public static void main(String[] args) {
    		SpringApplication.run(ProviderTicketApplication.class, args);
    	}
    }
    
    
## dubbo-consumer

#### application.properties
    dubbo.application.name=dubbo-consumer
    dubbo.registry.address=zookeeper://10.106.154.105:2181
    server.port=80
    
    
####     ConsumerService.java
    
    package com.java.boot.service;

    import org.springframework.stereotype.Service;
    import com.alibaba.dubbo.config.annotation.Reference;
    
    @Service
    public class ConsumerService {
    	@Reference
    	TicketService ticketService;
    	public String hello() {
    		String msg = ticketService.getTicket();
    		System.out.println("买到票了，" + msg);
    		return msg;
    	}
    }
    
####     ConsumerController.java
    
    package com.java.boot.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.ResponseBody;
    
    import com.java.boot.service.ConsumerService;
    
    @Controller
    public class ConsumerController {
    	@Autowired
    	ConsumerService userService;
    	@ResponseBody
    	@RequestMapping(value="/hell")
    	public String hello() {
    		String msg = userService.hello();
    		System.out.println("买到票了，" + msg);
    		return msg;
    	}
    }
    
####     pom.xml
    
    <dependencies>
        <dependency>
			<groupId>com.java.boot</groupId>
			<artifactId>dubbo-provider</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>com.alibaba.boot</groupId>
			<artifactId>dubbo-spring-boot-starter</artifactId>
			<version>0.1.0</version>
		</dependency>

		<!--引入zookeeper的客户端工具 -->
		<dependency>
			<groupId>com.github.sgroschupf</groupId>
			<artifactId>zkclient</artifactId>
			<version>0.1</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
