# mamba-forever-lakers

　湖人总冠军🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆



## 注意

如果项目启动读取 redis 的配置老是 localhost:6379 ，但是我们确实是配置了集群。
那么只能说明是IDEA 工程有问题!!!

- 解决办法
>  重新导入项目，或者提交代码之后，重新clone 项目。之前尝试过 gradle build 不起作用.

## 如果这个项目出现问题了。
执行
> gradle clean build -x test

不要问我为什么... 就是项目需要重新构建


## 启动项目
> gradle clean source:jar package -P prod


## 遇到异常

### SLF4J: Class path contains multiple SLF4J bindings.

JAR 冲突,因为我项目添加zookeeper，需要移除冲突JAR

<https://stackoverflow.com/questions/18952479/how-to-exclude-multiple-slf4j-bindings-to-log4j>

### Failed to load class "org.slf4j.impl.StaticLoggerBinder


### Gradle build fails on Lombok annotated classes
IDEA 2019.2 GRADLE 5.X LOMBOK 1.18.8  JDK12
<https://stackoverflow.com/questions/35236104/gradle-build-fails-on-lombok-annotated-classes>

```groovy
dependencies{

compileOnly 'org.projectlombok:lombok:1.18.8'
annotationProcessor 'org.projectlombok:lombok:1.18.8'

}
```

## [Springboot 2.0选择HikariCP作为默认数据库连接池的五大理由](http://blog.didispace.com/Springboot-2-0-HikariCP-default-reason/)