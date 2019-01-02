package cn.itcast.microservice.order.controller;

import cn.itcast.microservice.order.entity.Order;
import cn.itcast.microservice.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpengtao
 * @date 2019/1/1  22:21
 * @EMAIL wptxc@foxmail.com
 */

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "order/{orderId}")
    public Order queryOrderById(@PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderById(orderId);
    }

}

