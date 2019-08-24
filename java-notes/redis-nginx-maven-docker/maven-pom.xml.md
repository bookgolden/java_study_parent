
   
######    警告：
   Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
    
######    解决方法
    
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>
      
### mvn打包命令：

    mvn clean package -Ponline -DskipTests=true
    mvn install -DskipTests 或 
    mvn install -Dmaven.test.skip=true
    java -jar --server.port=8088
    java -jar -Dserver.port=8088
    
###      这个插件，可以将应用打包成一个可执行的jar包

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    
###     生成jar包、sources.jar、javaadoc.jar文件

    <build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>${java.version}</source>
					<target>${java.version}</target>
					<testSource>${java.version}</testSource>
					<testTarget>${java.version}</testTarget>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<fork>true</fork>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-javadoc-plugin</artifactId>
				<configuration>
					<encoding>UTF-8</encoding>
					<aggregate>true</aggregate>
					<docencoding>UTF-8</docencoding>
				</configuration>
				<executions>
					<execution>
						<id>attach-javadocs</id>
						<goals>
							<goal>jar</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-source-plugin</artifactId>
				<executions>
					<execution>
						<id>attach-sources</id>
						<goals>
							<goal>jar</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>