# spring-cloud-test

2022.09.29

## 前言

本项目是一个 Spring Cloud 练习项目。

## 开发环境

- Java: 11
- Spring Boot: 2.6.3
- Spring Cloud: 2021.0.1
- Spring Cloud Alibaba: 2021.0.1.0
- Nacos: 1.4.4

## 项目结构

1. gateway：网关模块
2. nacos-client：nacos 客户端模块
3. user-service：业务测试模块

## 项目演示

### 配置和运行

1. Alibaba Nacos 下载安装运行
2. 在 Nacos UI 上新建命名空间 `spring-cloud-test`：

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8871132284.png" alt="image-20220929下午71132284" style="zoom:33%;" />

3. 复制命名空间 ID，粘贴到各个子模块的 `application.yml` 中：

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8872103388.png" alt="image-20220929下午72103388" style="zoom:33%;" />

4. 在 Nacos UI 的 配置管理-配置列表 的 `spring-cloud-test` 命名空间下新增配置：
   1. Data ID: `gateway-router`
   2. Group: `test`
5. 在配置中添加 YAML 格式配置并发布：

```yml
- id: user-service
  uri: http://localhost:7000
  predicates:
    - Path=/user/**
  filters:
    - PrefixPath=/user-service-test
- id: nacos-client
  uri: http://localhost:8000
  predicates:
    - Path=/nacos-client/**
  filters:
    - PrefixPath=/nacos-client-test
```

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8872557594.png" alt="image-20220929下午72557594" style="zoom: 33%;" />

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8872644938.png" alt="image-20220929下午72644938" style="zoom: 30%;" />

6. 启动三个 Application：

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8873213575.png" alt="image-20220929下午73213575" style="zoom: 33%;" />

### Nacos 服务发现

7. 可以在 Nacos UI 中看到三个服务：

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8873258798.png" alt="image-20220929下午73258798" style="zoom: 30%;" />

### Gateway 动态监听路由配置

8. 在 `GatewayApplication` 的日志中可以看到 Nacos 上的动态路由配置已经被读取：

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8873745557.png" alt="image-20220929下午73745557" style="zoom: 25%;" />

9. 尝试在 Nacos 中更改配置并发布：

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8873918853.png" alt="image-20220929下午73918853" style="zoom: 33%;" />

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8874011279.png" alt="image-20220929下午74011279" style="zoom: 33%;" />

10. 更新后的路由配置可以被网关服务监听到，并被采用：

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8874417188.png" alt="image-20220929下午74417188" style="zoom: 33%;" />

<img src="media/README.assets/image-20220929%E4%B8%8B%E5%8D%8874502762.png" alt="image-20220929下午74502762" style="zoom:33%;" />

11. 将 Nacos 中路由配置复原并发布，否则后续会出错

### Gateway 网关功能

#### 通过网关访问 Nacos 客户端接口

![image-20220929下午75507394](media/README.assets/image-20220929%E4%B8%8B%E5%8D%8875507394.png)

#### 通过网关访问业务测试模块接口

![image-20220929下午75835972](media/README.assets/image-20220929%E4%B8%8B%E5%8D%8875835972.png)

## 参考资料

1. [Spring Cloud / Alibaba 微服务架构实战](https://coding.imooc.com/class/522.html#Anchor)
2. [SpringCloud学习教程目录](https://www.macrozheng.com/cloud/cloud_catalog.html#gateway-%E7%AE%80%E4%BB%8B)

## 更新记录

- [x] 2022.09.29：整合完 Nacos 和 Gateway







