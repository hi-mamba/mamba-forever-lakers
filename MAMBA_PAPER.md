
# MAMBA_PAPER


## springboot 中接口驼峰自动转下划线以及跨域代码块

[Spring MVC 响应json修改命名方式（驼峰修改为下划线）](https://segmentfault.com/a/1190000018635039)

- 方法一：在config里设置
 在spring config 中新建一个Jackson2ObjectMapperBuilderCustomizer 的bean 
 可以参考springboot中接口驼峰自动转下划线以及跨域代码块
```java
 @Bean
 public Jackson2ObjectMapperBuilderCustomizer customJackson() {
     return new Jackson2ObjectMapperBuilderCustomizer() {
         @Override
         public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
             //驼峰转换为下划线
             jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
         }
 
 };
```

- 方法二：在application.properties里设置
 在中application.properties设置一下属性，可以参考Spring Jackson property naming strategy

设置返回对象使用下划线 - (驼峰修改为下划线）
[Spring Jackson property naming strategy](https://javadeveloperzone.com/spring/spring-jackson-property-naming-strategy/)

```properties
 #命名规则下划线式
 spring.jackson.propertyNamingStrategy=SnakeCaseStrategy
 ```
- 方法三

参数返回转换实现：

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)：在实体类上使用，整个实体返回的json字段格式转换为下划线格式
或者：@JsonProperty：在字段名上使用，返回的json字段格式转换为下划线格式

例一：
```java

/**
 * @author pankui
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class UserInfo {
    private String userName;
}
```

例二： 
```java
@JsonProperty("pay_account")
private String payAccount;
```





