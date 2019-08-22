
### @Conditional派生注解（Spring注解版原生的@Conditional作用）
    作用：必须是@Conditional指定的条件成立，才给容器中添加组件，配置配里面的所有内容才生效

    @Conditional                    扩展注解作用（判断是否满足当前指定条件）
    @ConditionalOnJava              系统的java版本是否符合要求
    @ConditionalOnBean              容器中存在指定Bean；
    @ConditionalOnMissingBean       容器中不存在指定Bean；
    @ConditionalOnExpression        满足SpEL表达式指定
    @ConditionalOnClass             系统中有指定的类
    @ConditionalOnMissingClass      系统中没有指定的类
    @ConditionalOnSingleCandidate   容器中只有一个指定的Bean，或者这个Bean是首选Bean
    @ConditionalOnProperty          系统中指定的属性是否有指定的值
    @ConditionalOnResource          类路径下是否存在指定资源文件
    @ConditionalOnWebApplication    当前是web环境
    @ConditionalOnNotWebApplication 当前不是web环境
    @ConditionalOnJndi              JNDI存在指定项

    自动配置类必须在一定的条件下才能生效；
    我们怎么知道哪些自动配置类生效；
    我们可以通过启用 debug=true属性；来让控制台打印自动配置报告，这样我们就可以很方便的知道哪些自动配置类生效

