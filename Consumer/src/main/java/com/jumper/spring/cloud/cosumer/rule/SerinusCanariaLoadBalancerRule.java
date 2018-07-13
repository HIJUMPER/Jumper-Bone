package com.jumper.spring.cloud.cosumer.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 灰度发布
 */
public class SerinusCanariaLoadBalancerRule extends RandomRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerinusCanariaLoadBalancerRule.class);

    private Random random = new Random();

    //TODO 把灰度规则放入配置文件
    private static HashMap<String, Integer> rules = new HashMap<>();

    static {
        rules.put("1.0.1", 10);
        rules.put("1.0.2", 20);
        rules.put("1.0.3", 20);
    }

    /**
     * 规整灰度版本信息
     * 决定此次请求需要调用的版本
     * 获取相应版本的Server，如果没有获得的到随机一个版本，并循环调用一个Server
     */
    @Override
    public Server choose(Object key) {

        Map<String, List<Server>> makeUpService = makeUpServiceVersion();
        String vs = getVersionV2(makeUpService);
        LOGGER.info("Choose version:{}", vs);
        LoadBalancerStats loadBalancerStats = ((BaseLoadBalancer) getLoadBalancer()).getLoadBalancerStats();
        int defaultzone = loadBalancerStats.getActiveRequestsCount("defaultzone");
        LOGGER.info("ActiveRequestsCount:{}", defaultzone);
        if (makeUpService.containsKey(vs)) {
            List<Server> servers = makeUpService.get(vs);
            return servers.size() == 0 ? null : servers.get(random.nextInt(servers.size()));
        } else {
            //TODO 如果选取的VS正好不在可用SERVER中，可用SERVER剩余都未灰度版本，则可能导致无SERVER可用，此处暂用随机SERVER，但损失类概率
            return super.choose(key);
        }
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    private Map<String, List<Server>> makeUpServiceVersion() {
        List<Server> reachableServers = getLoadBalancer().getAllServers();
        Map<String, List<Server>> makedServers = new HashMap<>();
        reachableServers.forEach(reachableServer -> {
            String version = getVersionFromServer(reachableServer);

            List<Server> servers = makedServers.getOrDefault(version, new LinkedList<Server>());
            servers.add(reachableServer);
            makedServers.put(version, servers);
        });
        return makedServers;
    }

    private String getVersionFromServer(Server reachableServer) {
        String version = StringUtils.substringAfterLast(reachableServer.getMetaInfo().getInstanceId(), ":");
        version = StringUtils.isNotEmpty(version) ? version : "OTHER";
        return version;
    }

    /**
     * 此处未判断是否大于
     *
     * @return
     */
    private String getVersionV1() {
        //TODO 多线程转换，使用缓存
        Map<Integer, String> temp = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Integer boundary = 0;
        for (Map.Entry<String, Integer> entry : rules.entrySet()) {
            boundary = boundary + entry.getValue();
            temp.put(boundary, entry.getKey());
        }
        Integer rd = random.nextInt(boundary);
        for (Map.Entry<Integer, String> entry : temp.entrySet()) {
            if (entry.getKey() > rd) {
                return entry.getValue();
            }
        }
        return null;
    }


    private String getVersionV2(Map<String, List<Server>> makeUpService) {
        if (rules == null || rules.size() ==0){
            return null;
        }
        BaseLoadBalancer loadBalancer = (BaseLoadBalancer) getLoadBalancer();
        Map<Server, ServerStats> serverStats = loadBalancer.getLoadBalancerStats().getServerStats();
        Map<String, Long> requestCountPerVersion = new HashMap<>();
        Long totalRequestCount = 0L;
       for(Map.Entry<String,List<Server>> entry : makeUpService.entrySet()){
            Long totalRequestForThisVersion = 0L;
            for (Server server : entry.getValue()){
                totalRequestForThisVersion += serverStats.get(server).getTotalRequestsCount();
            }
            totalRequestCount+=totalRequestForThisVersion;
            requestCountPerVersion.put(entry.getKey(),totalRequestForThisVersion);
        };
        Map<String,Double> ratePerVersion = new HashMap<>();
        for (Map.Entry<String,Long> entry : requestCountPerVersion.entrySet()){
            ratePerVersion.put(entry.getKey(),  (totalRequestCount == 0 ? 0D:(double)entry.getValue()/(double)totalRequestCount)*100);
        };

        TreeMap<Double, String> weight = new TreeMap<>();
        rules.forEach((version,rate)->{
            if (ratePerVersion.containsKey(version)){
                weight.put(ratePerVersion.get(version)-rate,version);
            }
        });
        return weight.get(weight.firstKey());
    }

}
