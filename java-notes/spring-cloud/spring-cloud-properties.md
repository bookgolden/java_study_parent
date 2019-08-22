server.port

spring.application.name

    eureka.client.serviceUrl.defaultZone 
    
    eureka.instance.hostname=localhost
    
    eureka.client.register-with-eureka=false
    
    eureka.client.fetch-registry=false
    
    @EnableDiscoveryClient 在工程的启动类中,向服务中心注册
    
    eureka.client.registerWithEureka=false #不把自己注册到eureka上
    
    eureka.client.fetchRegistry=false #不从eureka上来获取服务的注册信息
    
    @LoadBalanced注解表明这个restRemplate开启负载均衡的功能

    
    Feign的起步依赖spring-cloud-starter-feign
    Eureka的起步依赖spring-cloud-starter-eureka
    Web的起步依赖spring-boot-starter-web

    断路器 应用于调用端
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-hystrix</artifactId>
    </dependency>
    @EnableHystrix注解开启Hystrix
    
    @HystrixCommand(fallbackMethod = "hiError")
	@Override
	public String getMsg() {
		return restTemplate.getForObject("http://EUREKA-PROVIDER/getMsg?name=bob", String.class);
	}

	public String hiError(){
		return "your request is erorr~";
	}