# CocoBoot
🍓 封装的一些starter和utils，感兴趣的可以按模块自取
🍓 你可以基于需要构建的模块来替代Spring的Starter启动，如需要

| 模块                          | 说明                                    | 传送门                                                                               |
|-----------------------------|---------------------------------------|-----------------------------------------------------------------------------------|
| CocoBoot                    | 聚合工程，boot-starter版本基于2.3.10.RELEASE构建 | [🚪](https://github.com/Cocowwy/CocoBoot)                                         |
| coco-boot-common            | 公共包，使用不需引入                            | 🈚️                                                                               |
| coco-boot-starter           | starter模块，引入开启SpringBootStarter，并增强功能 | [🚪](https://github.com/Cocowwy/CocoBoot/tree/master/coco-boot-starter)           |
| coco-boot-web-starter       | web模块，引入开启SpringBootWeb，并增强功能         | [🚪](https://github.com/Cocowwy/CocoBoot/tree/master/coco-boot-web-starter)       |
| coco-boot-openfeign-starter | openfeign模块，引入开启openfeign，并增强功能       | [🚪](https://github.com/Cocowwy/CocoBoot/tree/master/coco-boot-openfeign-starter) |

稳定版本使用：
```maven
还没有打包，先用下面的快照版～
```

快照版本使用：
```maven
<dependency>
    <groupId>cn.cocowwy</groupId>
    <artifactId>模块名</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
<repositories>
    <repository>
        <id>snapshots</id>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```
