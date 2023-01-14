# coco-boot-openfeign-starter

## 🍬 Feature1
**新增路由规则** ```DevRouteRule```

**当你使用的是OpenFeign来进行微服务的调用，那么这是一个适用于本地开发使用的负载均衡策略**  
**遵循如下规则：**  

⚡️ 如果请求的是server1，则会优先路由到 127.0.0.1:8081  
⚡️ 如果请求的是server3，会检查**本地是否有启动server3（前提是能被服务发现）**，如果有，则路由到本地的server3服务上   
⚡️ 如果请求的是server4，则按照Ribbon的轮询算法路由  
🌸 即：**优先配置，其次本地，最后轮询**
```yaml
coco:
  openfeign:
    dev:
      enable: true
      route:
        server1: 127.0.0.1:8081
        server2: 127.0.0.1:8082
```
**注意，该路由规则默认不开启，如需使用，请将 enable 打开**