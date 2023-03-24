# coco-boot-mb-starter

基于MyBatis的拦截器实现的一些插件功能，适用于MyBatis以及MyBatisPlus  
**使用前提：持久化层使用 MyBatis/MyBatis-Plus，该stater不提供mb和mbp的stater请自行添加**

##  🍬 Feature1 字段加解密
使用姿势：
在配置累中注入如下Bean
并使用注解：
```java
coco:
  mb:
    privacy: true
```
并且在入库的 **实体类和字段（注意实体类和字段上都需要）** 使用注解 @Privacy ，即可在数据库隐私🔏字段。  
默认使用base64加密算法  
（如果你想修改🔐加密算法）可实现 EncryptionDecryption 接口 @Bean 进你的配置类，就可以重写加密🔐算法

⚠：如果使用的是MyBatisPlus的话 加密字段的修改如果使用Wrapper，则会失效！


##  🍬 Feature2  explain执行计划 每个查询类SQL均会打印执行计划
😜 使用姿势： 
```java
coco:
  mb:
    explain: true
```
**之后所有通过MyBatis/MyBatisPlus的查询SQL将会出现如下结果**
```text
SQL：SELECT id,num FROM number WHERE (id = 1)
执行计划如下：
｜ id=1 ｜ selectType='SIMPLE' ｜ table='number' ｜ partitions='null' ｜ type='ALL' ｜ possibleKeys='null' ｜ key='null' ｜ keyLen='null' ｜ ref='null' ｜ rows=1 ｜ filtered=100.0 ｜ extra='Using where' ｜
栈帧定位信息如下：
java.lang.Thread.getStackTrace(Thread.java:1564)
cn.cocowwy.common.util.PrintUtils.printStack(PrintUtils.java:21)
cn.cocowwy.cocobootmbstarter.interceptor.explain.ExplainInterceptor.preExplain(ExplainInterceptor.java:96)
cn.cocowwy.cocobootmbstarter.interceptor.explain.ExplainInterceptor.intercept(ExplainInterceptor.java:81)
org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:61)
com.sun.proxy.$Proxy98.query(Unknown Source)
org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:147)
org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:140)
org.apache.ibatis.session.defaults.DefaultSqlSession.selectOne(DefaultSqlSession.java:76)
sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
java.lang.reflect.Method.invoke(Method.java:498)
org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:426)
com.sun.proxy.$Proxy79.selectOne(Unknown Source)
org.mybatis.spring.SqlSessionTemplate.selectOne(SqlSessionTemplate.java:159)
com.baomidou.mybatisplus.core.override.MybatisMapperMethod.execute(MybatisMapperMethod.java:90)
com.baomidou.mybatisplus.core.override.MybatisMapperProxy$PlainMethodInvoker.invoke(MybatisMapperProxy.java:148)
com.baomidou.mybatisplus.core.override.MybatisMapperProxy.invoke(MybatisMapperProxy.java:89)
com.sun.proxy.$Proxy82.selectOne(Unknown Source)
cn.cocowwy.test.starterTests.AfterRunnerDoTests.test(AfterRunnerDoTests.java:46)
sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
java.lang.reflect.Method.invoke(Method.java:498)
cn.cocowwy.cocobootstarter.impl.AfterRunnerDoImpl.run(AfterRunnerDoImpl.java:46)
org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:783)
org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:773)
org.springframework.boot.SpringApplication.run(SpringApplication.java:319)
org.springframework.boot.SpringApplication.run(SpringApplication.java:1247)
org.springframework.boot.SpringApplication.run(SpringApplication.java:1236)
cn.cocowwy.test.TestApplication.main(TestApplication.java:25)
```
**参数说明：**  
- ⚡️ ️**printStack：是否打印堆栈日志，默认为true**  
- ⚡️ **subscribeType：订阅集合元素里面需要筛选的Type类型，默认为空（打印所有Type类型的查询语句）**

**🍓 注意：不建议在生产环境中开启, 请根据情况结合注解@Profile("dev")选择开启的环境**

# Feature3 SQL打印执行时长
开发中，有空再写...




