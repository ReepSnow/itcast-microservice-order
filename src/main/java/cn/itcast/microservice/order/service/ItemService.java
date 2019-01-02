package cn.itcast.microservice.order.service;

import cn.itcast.microservice.order.entity.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangpengtao
 * @date 2019/1/1  22:20
 * @EMAIL wptxc@foxmail.com
 */

@Service
public class ItemService {

    /*// Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderProperties orderProperties;
    public Item queryItemById(Long id) {
        return this.restTemplate.getForObject(orderProperties.getItem().getUrl()
                + id, Item.class);
    }*/


    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${itcast.item.url}")
    private String itcastItemUrl;

   /* public Item queryItemById(Long id) {
        String serviceId = "itcast-microservice-item";
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if(instances.isEmpty()){
            return null;
        }
        // 为了演示，在这里只获取一个实例
        ServiceInstance serviceInstance = instances.get(0);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
    }*/
   //使用负载均衡ribbon的写法
   @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") // 进行容错处理
    public Item queryItemById(Long id) {
        String serviceId = "itcast-microservice-item";
        //因为item项目提供者提供了实例的名称，所以直接通过实例名称来作为请求地址
        return this.restTemplate.getForObject("http://" + serviceId + "/item/" + id, Item.class);
    }

    public Item queryItemByIdFallbackMethod(Long id){ // 请求失败执行的方法
        return new Item(id, "查询商品信息出错!", null, null, null);
    }


}

