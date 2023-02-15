package cn.cocowwy.cocobootopenfeginstarter.autoconfigure.ribbon;

import cn.cocowwy.cocobootopenfeginstarter.prop.DevOpenFeignProperties;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.PredicateBasedRule;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * DevRouteRule 路由规则:
 * {@link DevOpenFeignProperties}
 * route配置了服务名与IP的映射，则优先
 * 如果没有配置，则从注册中心上优先本地服务并路由
 * 如果均未配置则按照ribbon的默认策略轮询注册中心的服务列表
 *
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
public class DevRouteRule extends PredicateBasedRule {

    @Autowired
    private InetUtils inetUtils;

    private final DevOpenFeignProperties devOpenFeignProperties;

    public DevRouteRule(DevOpenFeignProperties devOpenFeignProperties) {
        this.devOpenFeignProperties = devOpenFeignProperties;
    }

    @Override
    public AbstractServerPredicate getPredicate() {
        return AbstractServerPredicate.alwaysTrue();
    }


    @Override
    public Server choose(Object key) {

        BaseLoadBalancer lb = (BaseLoadBalancer) getLoadBalancer();

        List<Server> allServers = lb.getAllServers();

        List<Server> servers = filterServers(lb.getName(), allServers);

        // 过滤服务列表
        return getPredicate().chooseRoundRobinAfterFiltering(servers, key).orNull();
    }

    private List<Server> filterServers(String name, List<Server> servers) {
        Map<String, String> routeMap = devOpenFeignProperties.getRoute();

        // 优先route配置
        if (routeMap.containsKey(name)) {
            Server server = new Server(routeMap.get(name));
            return Collections.singletonList(server);
        }

        // 其次优先本地服务
        String hostIp = this.inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();

        for (Server server : servers) {
            String host = server.getHost();
            // 优先本地ip的服务 某些服务使用主机名注册为host
            if (host.equals(hostIp)) {
                return Collections.singletonList(server);
            }
        }

        // 返回原server列表
        return servers;
    }
}
