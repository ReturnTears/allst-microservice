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

Eureka的集群搭建
allst-mircoservice-eureka是master
allst-mircoservice-eureka-slave1是slave1节点
allst-mircoservice-eureka-slave2是slave2节点
Slave的POM配置和master一致
添加服务启动类
修改本地的hosts域名映射
修改三台eureka服务的yml配置
将eurekaslave相关的两台服务配置到provider服务中


```

**Ribbon**
```text
SpringCloud Ribbon是基于Netflix Ribbon实现的一套客户端 负载均衡的工具
简单来说, Ribbon是Netflix发布的开源项目, 主要功能是提供客户端软件负载均衡算法, 将Netflix的中间服务连接在一起,
Ribbon客户端组件提供一系列完善的配置项: 如超时链接, 重试, 简单来说, 就是在配置文件中列出loadbalancer(简称LB)后面所有的机器,
Ribbon会自动地帮助你基于某种规则(轮询, 随机连接)去连接这些机器, 我们很容易使用Ribbon实现自定义地负载均衡算法.

LB: LoadBalance, 在微服务或分布式集群中经常用地一种应用
负载均衡简单来说就是将用户地请求平摊地分配到多个服务上, 从而达到系统地HA(High Availability)
常见地负载均衡软件有: Nginx, LVS, 硬件F5等
相应地在中间件: 例如: dubbo和SpringCloud中均给我们提供了负载均衡, SpringCloud地负载均衡算法可以自定义
集中式LB: 偏硬件, 即在服务地消费方和提供方之间使用独立地LB设施(可以是硬件, 如F5, 也可以是软件, 如Nginx), 由该设施负责把访问请求通过某种策略转发至服务地提供方
进程内LB: 偏软件, 即将LB逻辑集成到消费方, 消费方从服务注册中获知哪有地址可用,  然后自己再从这些地址中选择出一个合适地服务器
                Ribbon就属于进程内LB, 他只是一个类库, 集成于消费方进程, 消费方通过他来获取到服务提供方地址(例: 到超市购物结算的时候, 我们一般会选择排队人少的队列去结算, (超市相当于服务方, 我们则是消费方))


Ribbon在工作时分成两步:
1,先选择EurekaServer, 他优先选择在同一区域内负载较少的server
2,再根据用户指定的策略,从server取到的服务注册表中选择一个地址
其中Ribbon提供了多种策略,如: 轮询, 随机和根据响应时间加权

Ribbon的核心组件IRule: 根据特定算法中从服务列表中选取一个要访问的服务
特定算法有:
1),RoundRobinRule 轮询
2),RandomRule 随机
3),AvailabilityFilteringRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务,还有并发的连接数量超过阈值的服务, 然后对剩余的服务列表按照轮询策略进行访问
4),WeightedResponseTimeRule 根据平均响应时间计算所有服务的权重, 响应时间越快服务权重越大被选中的概率越大, 刚启动时如果统计信息不足, 则使用轮询策略, 等统计信息足够时会切换到当前算法
5),RetryRule 先按照轮询的策略获取服务, 如果获取服务失败则在指定时间内重新进行重试, 获取可用的服务
6),BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务, 然后选择一个并发量最小的服务
7),ZoneAvoidanceRule 默认规则, 复合判断server所在区域的性能和server的可用性选择服务器

Ribbon & Feign & Nginx的区别 
```

**Feign**
```
Feigin是一个声明式的Web服务客户端, 使得编写Web服务端变得非常容易,只需要创建一个接口, 然后在接口添加注解即可
https://github.com/OpenFeign/feign

Feign能干什么
Feign旨在使编写Java Http客户端变得更容易
在使用Ribbon+RestTemplate时, 利用RestTemplate对HTTP请求的封装处理, 形成了一套模板化的调用方法, 但是在实际开发中, 由于对服务依赖的调用可能不止一处,
往往一个接口会被多处调用, 所以通常都会针对每一个微服务封装一些客户端类来包装这些依赖服务的调用. 所以, feign在此注解上做了进一步封装, 由他来帮我们定义和实现依赖服务接口的定义, 在feign的实现下,
我们只需要创建一个接口并使用注解的方式来配置它(以前是Dao接口上面标注Mapper注解, 现在是一个微服务接口上面标注一个Feign注解即可),即完成对服务提供方的接口绑定,
简化了使用SpringCloud Ribbon时,自动封装服务调用客户端的开发量

Feign集成了Ribbon
利用Ribbon维护了MicroServiceCLoud-Dept的服务列表信息, 并且通过轮询实现了客户端的负载均衡, 而与ribbon不同的是,
通过feign只需要定义服务绑定接口且以声明式的方法, 优雅而简单的实现了服务调用

```
**Hystrix**
```
Hystrix断路器

1,在分布式系统中面临的问题:
复杂分布式体现结构中的应用程序有数十个依赖关系, 每个依赖关系有些时候不可避免地失败

服务雪崩:
多个微服务之间调用的时候, 假设微服务1调用微服务2和微服务3, 微服务2和微服务3又调用其他的微服务, 这就是所谓的"扇出".
如果扇出的链路上某个微服务的调用响应时间过长或者不可用, 对微服务1的调用就会占用越来越多的资源,进而引起系统雪崩, 所谓的"雪崩效应"

对于高流量的应用来说, 单一的后端依赖可能会导致所有服务器上的所有资源都在几秒中内饱和. 比失败更糟糕的是,
这些应用程序还可能导致服务之间的延迟增加, 备份队列, 线程和其他系统资源紧张, 导致整个系统发生更多的练级故障, 这些都表示需要对故障和延迟进行隔离和管理,
以便单个依赖关系的失败, 不能取消整个应用程序或系统

2,是什么? 是一种保护机制
https:
Hystrix是一个用于处理分布式系统的延迟和容错的开源库, 在分布式系统里, 许多依赖不可避免地会调用失败, 比如超时, 异常等,
Hystrix能够保证在一个依赖出现问题地情况下,不会导致整体服务失败, 避免级联故障,以提高分布式系统地弹性.

"断路器"本身是一种开关装置, 当某个服务单元发生故障之后, 通过断路器地故障监控(类似熔断保险丝), 向调用方返回一个符合预期地, 可处理地备选响应(FallBack),
而不是长时间的等待或者抛出调用方无法处理的异常, 这样就保证了服务调用方的线程不会被长时间, 不必要的占用, 从而避免了故障在分布式系统中的蔓延, 乃至雪崩

3, 服务熔断
熔断机制是对应雪崩效应的一种微服务链路保护机制
当扇出链路的某个微服务不可用或者响应时间太长时, 会进行服务的降级, 进而熔断该节点微服务的调用, 快速返回"错误"的响应信息.
当检测到该节点微服务调用相应正常后恢复调用链路.在SpringCLoud框架里熔断机制通过Hystrix实现, Hystrix会监控微服务调用的状况, 当失败的调用到一定阈值,
缺省时5s内到20s调用失败就会启动熔断机制, 熔断机制的注解是@HystrixCommand


什么是服务降级?
当整体服务资源不够的情况下, 先关掉一些服务, 待服务资源充足的情况下,再开启回来,
服务降级处理是在客户端实现完成的,与服务器没有关系

Hystrix Dashboard
除了隔离依赖服务的调用以外, Hystrix还提供了准实时的调用监控(Hystrix Dashboard), Hystrix会持续地记录所有通过Hystrix发起的请求的执行信息
并以统计报表和图形的形式展示给用户, 包括每秒执行多少请求, 多少失败等,
netflix通过hystrix-metrics-event-stream项目实现了对以上指标的监控, springcloud也提供了Hystrix Dashboard的整合, 对监控内容转化成可视化界面



```
**zuul路由网关**
```text
Zuul包含了对请求的路由和过滤两个最主要的功能:
其中路由功能负责将外部请求转发到具体的微服务实例上, 是实现外部访问统一入口的基础而过滤器功能则负责对请求的处理过程进行干预
是实现请求校验, 服务聚合等功能的基础, Zuul和Eureka进行整合, 将Zuul自身注册为Eureka服务治理下的应用, 同时从Eureka中获得其他微服务得消息,
也即以后得访问服务都通过Zuul跳转后获得

Zuul服务最终还是会注册进Eureka

提供 = 代理 + 路由 + 过滤 三大功能



```


**CAP**
```text

RDBMS (mysql/oracle/sqlserver) 遵循 ACID
A atomicity原子性
C consistency一致性
I isolation独立性
D durability持久性

NOSQL (redis/mongdb) 遵循 CAP
C consistency 强一致性
A availability 可用性
P partition tolerance 分区容错性

任何一个分布式系统目前只能较好的满足其中的两个特性
CAP理论的核心是: 一个分布式系统不可能同时满足一致性,可用性和分区容错性这三个需求, 
根据CAP原理将NoSQL数据库分成了满足CA原则, CP原则, AP原则三类:
CA: 单点集群, 满足一致性, 可用性的系统, 通常在扩展性上不太强大
CP: 满足一致性, 分区容错性, 通常性能不是特别高
AP: 满足可用性, 分区容错性, 通常对一致性要求较低

zookeeper保证CP
eureka保证AP
```
CAP:
![Image](https://github.com/ReturnTears/allst-microservice/blob/master/cimg/cap1.png)
![Image](https://github.com/ReturnTears/allst-microservice/blob/master/cimg/cap2.png)



Eureka服务治理:
![Image](https://github.com/ReturnTears/allst-microservice/blob/master/cimg/eureka.png)
Dubbo服务治理:
![Image](https://github.com/ReturnTears/allst-microservice/blob/master/cimg/dubbo.png)
