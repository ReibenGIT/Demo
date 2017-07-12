package so.sao.web;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import so.sao.domain.Order;
import so.sao.domain.OrderDetails;
import so.sao.service.serviceImp.OrderServiceImp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单管理模块的Action层
 * Created by acer on 2017/7/10.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    //@Autowired
    @Resource
    private OrderServiceImp orderService;

    @ApiOperation(value="获取订单", notes="根据订单状态获取用订单列表(没有参数时表示查询所有)")
    @RequestMapping(value="/searchOrdersWithStu", method= RequestMethod.GET)
    public PageInfo<Order> findOrders(String stute, String orderId, int page) {
        List<Order> orders = orderService.findOrders(stute,orderId,page);
        return new PageInfo<Order>(orders);
    }

    @ApiOperation(value="获取订单", notes="根据订单编号获取用订单列表")
    @RequestMapping(value="/searchOrdersWithId", method= RequestMethod.GET)
    public Order findOrder(String orderId) {
        Order order = orderService.findOrder(orderId);
        return order;
    }

    @ApiOperation(value="查询订单详情", notes="查询订单详情")
    @RequestMapping(value="/searchOrderDetails", method= RequestMethod.GET)
    public List<OrderDetails> findOrderDetails(String stute,String orderId){
        return orderService.findOrderDetails(stute,orderId);
    }

    @ApiOperation(value="更改订单", notes="更改订单状态")
    @RequestMapping(value="/updateOrder", method= RequestMethod.POST)
    public String update(Order order) {
        return orderService.updateOrder(order);
    }

    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ApiOperation(value = "导出商品", notes = "导出当前状态下的商品")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,String stute) {
        orderService.export(request,response,stute);
    }

}
