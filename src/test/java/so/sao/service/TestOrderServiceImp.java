package so.sao.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import so.sao.common.Msg;
import so.sao.domain.Order;
import so.sao.domain.OrderDetails;
import so.sao.service.serviceImp.OrderServiceImp;

import java.util.List;

/**
 * 测试OrderService
 * Created by acer on 2017/7/12.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOrderServiceImp {

    @Autowired
    private OrderServiceImp orderService;

    @Test
    public void findOrders(){

        /**
         * 根据订单的状态查询订单
         */
        List<Order> orderByStu = orderService.findOrders("3",null,1);
        Assert.assertNotNull(orderByStu);
        Assert.assertEquals(5,orderByStu.size());

    }

    /**
     * 根据订单ID查询对应状态的订单详情
     */
    @Test
    public void findOrderDetails(){
        List<OrderDetails> orderDetails= orderService.findOrderDetails("2","2");
        Assert.assertNotNull(orderDetails);
        Assert.assertEquals(2,orderDetails.size());
    }

    /**
     * 更改订单状态
     */
    @Test
    public void updateOrder(){
        Order order = new Order();
        order.setId("3");
        order.setStute("3");
        Order order1=orderService.findOrder("3");
        Assert.assertNotNull(order1);
        String foo = orderService.updateOrder(order);
        Assert.assertEquals(Msg.SUCCESS_MSG,foo);
    }

    /**
     * 根据订单ID查询订单列表
     */
    @Test
    public void findOrder(){
        Order order=orderService.findOrder("3");
        Assert.assertNotNull(order);
        Assert.assertNotNull(order.getStute());


    }

}
