package so.sao.dao;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import so.sao.domain.Order;

import java.util.List;

/**
 * 订单的映射文件
 * Created by acer on 2017/7/10.
 */
@Mapper
public interface OrderMapper {
    /**
     * 查询订单
     * @param stute 如果有此参数，可以根据订单状态查询订单
     * @param stute,orderId  如有此俩个参数，可以根据订单ID查询对应状态的订单详情
     * @return List<Order>
     */
    List<Order> findOrders(@Param("stute")String stute,@Param("orderId")String orderId);

    /**
     * 更改订单
     * @param order
     * @return int
     */
    int update(Order order);

    /**
     * 根据订单编号获取用订单列表
     * @param orderId
     * @return Order
     */
    Order findOrder(String orderId);
}

