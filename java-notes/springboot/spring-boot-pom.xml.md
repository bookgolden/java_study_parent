
#####     Setting.xml中配置JDK环境
    给maven 的settings.xml配置文件的profiles标签添加
    
    <profile>
        <id>jdk‐1.8</id>
        <activation>
	        <activeByDefault>true</activeByDefault>
	        <jdk>1.8</jdk>
        </activation>
        <properties>
	        <maven.compiler.source>1.8</maven.compiler.source>
	        <maven.compiler.target>1.8</maven.compiler.target>
	        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
        </properties>
    </profile>
    
#####     pom.xml中配置JDK环境

    <properties>
		<java.version>1.8</java.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
	</properties>
	
    
#####     导入配置文件处理器，配置文件进行绑定就会有提示

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-configuration-processor</artifactId>
		<optional>true</optional>
	</dependency>
		
    @ConfigurationProperties(prefix = "person")

    
#####     pom.xl

    <distributionManagement>
		<repository>
			<id>maven-releases</id>
			<name>maven-releases</name>
			<url>http://127.0.0.1:8088/repository/maven-releases/</url>
		</repository>
		<snapshotRepository>
			<id>maven-snapshots</id>
			<name>maven-snapshots</name>
			<url>http://127.0.0.1:8088/repository/maven-snapshots/</url>
		</snapshotRepository>
	</distributionManagement>
	
##### 	setting.xml
	<servers>
    	<server>
    		<id>maven-releases</id>
    		<username>admin</username>
    		<password>admin123</password>
    	</server>
    	<server>
    		<id>maven-snapshots</id>
    		<username>admin</username>
    		<password>admin123</password>
    	</server>
	</servers>
	
	<mirrors>
    	<mirror>
    	  <id>nexus-myself</id>
    	  <!--*指的是访问任何仓库都使用我们的私服-->
    	  <mirrorOf>*</mirrorOf>
    	  <name>Nexus myself</name>
    	  <url>http://127.0.0.1:8088/repository/maven-public/</url>
    	</mirror>
	</mirrors>
	
	<profiles>
    	<profile>
    		<id>mycof</id>
    		<repositories>
    			<!-- 私有库地址 -->
    			<repository>
    				<id>nexus</id>
    				<url>http://127.0.0.1:8088/repository/maven-public/</url>
    				<releases>
    					<enabled>true</enabled>
    				</releases>
    				<snapshots>
    					<enabled>true</enabled>
    				</snapshots>
    			</repository>
    		</repositories>
    	</profile>
    </profiles>
    
    <activeProfiles>
        <activeProfile>mycof</activeProfile>
    </activeProfiles>