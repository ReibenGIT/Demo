package so.sao.service;

import org.springframework.stereotype.Service;
import so.sao.domain.Order;
import so.sao.domain.OrderDetails;

import java.util.List;

/**
 * 订单业务层接口
 * Created by acer on 2017/7/10.
 */
@Service
public interface OrderService {
    /**
     * 查询订单
     * @param stute 如果有此参数，可以根据订单状态查询订单
     * @param orderId 若有此参数，可以根据订单ID查询对应订单
     * @param page 分页
     * @return List<Order>
     */
    List<Order> findOrders(String stute,String orderId,int page);

    /**
     * 更改订单
     * @param order
     * @return String
     */
    String updateOrder(Order order);

    /**
     * 根据订单ID查询对应状态的订单详情
     * @param stute
     * @param orderId
     * @return List<OrderDetails>
     */
    List<OrderDetails> findOrderDetails(String stute,String orderId);

    /**
     * 根据订单编号获取用订单列表
     * @param orderId
     * @return Order
     */
    Order findOrder(String orderId);
}
