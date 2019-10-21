# 微服务

## 微服务
```
微服务的核心就是将传统的一站式应用, 根据业务拆分成一个一个的服务, 彻底去耦合, 每个微服务提供单个业务功能的服务, 一个服务做一件事,从技术角度看,就是一
种小而独立的处理过程, 类似进程的概念, 能够自行单独启动或销毁, 拥有自己独立的数据库


```

## Maven分包分模块搭建
```text
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


RestTemplate提供了多种便捷访问远程Http服务的方法
是一种简单便捷的访问restful服务模板类, 是Spring提供的用于访问Rest服务的客户端模板工具类

使用RestTemplate访问restful接口非常的简单, (url, requestMap, responseBean这三个参数分别代表REST请求地址, 请求参数,HTTP响应转换被转换成的对象类型)
```

### Eureka
```text
Netflix在设计Eureka时遵守的是AP原则
Eureka是Netflix的一个子模块, 也是核心模块之一, Eureka是一个基于REST的服务, 用于定位服务, 以实现云端中间层服务发现和故障转移. 服务注册与发现对于微服务
架构来说是非常重要的,有了服务发现与注册, 只需要使用服务的标识符, 就可以访问到服务,而不需要修改服务调用的配置文件了.
功能类似于dubbo的注册中心, 比如zookeeper

Eureka采用了CS的设计架构, Eureka Server作为服务注册功能的服务器, 它是服务注册中心

而系统中的其他服务, 使用Eureka的客户端连接到Eureka Server并维持心跳链接.
这样系统的维护人员就可以通过Eureka Server来监控系统中各个微服务是否正常运行. 
Spring Cloud的一些其他模块(比如Zuul)就可以通过Eureka Server来发现系统中的其他微服务, 并执行相关的逻辑

Eureka于dubbo的对比:
https://www.jianshu.com/p/e47c027a9aeb

Eureka包含两个组件:Eureka Server和Eureka Client
Eureka Server提供服务注册服务
各个节点启动后, 会在Eureka Server中进行注册,这样Eureka Server中的服务注册表将会存储所有可用服务节点的信息, 服务节点的信息可用在界面中直观的看到
Eureka Client是一个Java客户端,用于简化Eureka Server的交互, 客户端同时具备一个内置的,使用轮询(round-robin)负载算法的负载均衡器
在应用启动后,将会向Eureka Server发送心跳(默认周期为30秒), 如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳,Eureka Server
将会从服务注册表中把这个服务节点溢出(默认90秒)

Eureka Server提供服务注册和发现
Service Provider服务提供方将自身服务注册到Eureka, 从而使服务消费方可以找到
Service Consumer服务消费方从Eureka获取注册服务列表, 从而能够消费服务


eureka的自我保护机制:
某一时刻某个微服务不可用了, eureka不会立刻清理, 依旧会对该微服务的信息进行保存
默认情况下, 如果EurekaServer在一定时间内没有接收到某个微服务实例的心跳, EurekaServer将会注销该实例(默认90秒)
但是当网络分区故障发生时, 微服务与EurekaServer之间无法通信, 以上行为可能变得非常危险了--因为微服务本身其实是健康的, 此时本不应该注销该微服务.
Eureka通过"自我保护模式"来解决这个问题--当EurekaServer节点在短时间内丢失过多客户端时(可能发生了网络分区故障), 那么这个节点就会进入自我保护模式. 一旦进入该模式,
Eureka就会保护服务注册表中的信息, 不再删除服务注册表中的数据(也就是不会注销任何微服务).当网络故障回复后, 该Eureka Server节点会自动退出自我保护模式.

在自我保护模式下, Eureka Server会保护注册表中的信息, 不再注销任何服务实例, 当它收到的心跳数重新恢复到阈值以上时,
该Eureka server节点就会自动退出自我保护模式, 他的设计哲学就是宁可保留错误的服务注册信息, 也不盲目注销任何可能健康的服务实例

在Spring cloud中, 可以使用eureka.server.enable-self-preservation=false禁用自我保护模式












```
Eureka服务治理:
![Image](https://github.com/ReturnTears/allst-microservice/blob/master/cimg/eureka.png)
Dubbo服务治理:
![Image](https://github.com/ReturnTears/allst-microservice/blob/master/cimg/dubbo.png)
