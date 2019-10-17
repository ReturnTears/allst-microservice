# 微服务

## Maven分包分模块搭建
```
allst-microservice整体夫工程Project
父工程的Packageing是pom模式, 主要是定义POM文件, 后续将各个子模块共用的jar包等统一提取出来, 类似一个抽象的父类
dependencyManagement 是父类工程的管理机制

api子模块
新建allst-microservice-api Module工程, jar
修改POM文件
新建部门Entity且配合lombok使用
mvn clean install后给其他模块引用, 达到通用的目的
也即需要用到部门实体的话, 不用每个工程定义一份, 直接引用本模块即可


provider子模块
新建allst-microservice-provider Module工程, jar
修改POM文件
配置yml文件


编码中有一个约定俗成的关系:

约定 > 配置 > 编码



```