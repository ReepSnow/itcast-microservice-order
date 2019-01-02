package cn.itcast.microservice.order.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangpengtao
 * @date 2019/1/1  22:26
 * @EMAIL wptxc@foxmail.com
 */

@Component
@ConfigurationProperties(prefix="itcast") //以itcast开头的配置被匹配到
public class OrderProperties {

    private ItemProperties item = new ItemProperties();

    public ItemProperties getItem() {
        return item;
    }

    public void setItem(ItemProperties item) {
        this.item = item;
    }

}

