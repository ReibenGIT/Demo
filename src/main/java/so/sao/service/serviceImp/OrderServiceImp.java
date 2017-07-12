package so.sao.service.serviceImp;

import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.sao.common.Msg;
import so.sao.dao.OrderMapper;
import so.sao.domain.Order;
import so.sao.domain.OrderDetails;
import so.sao.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 订单业务层实现
 * Created by acer on 2017/7/10.
 */
@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    /**
     * 查询订单
     * @param stute 如果有此参数，可以根据订单状态查询订单
     * @param orderId 若有此参数，可以根据订单ID查询对应订单
     * @return List<Order>
     */
    @Override
    public List<Order> findOrders(String stute, String orderId ,int page) {
        PageHelper.startPage(page,  Msg.ROWS);
        return orderMapper.findOrders(stute,orderId);
    }
    /**
     * 根据订单ID查询对应状态的订单详情
     * @param stute
     * @param orderId
     * @return List<OrderDetails>
     */
    @Override
    public List<OrderDetails> findOrderDetails(String stute,String orderId) {
        List<Order> orders = orderMapper.findOrders(stute,orderId);
        List<OrderDetails> orderDetails = new ArrayList<>();
        if(orders!=null){
            for(Order order:orders){
                orderDetails=order.getOrderDetails();
            }
        }
        return orderDetails;
    }

    /**
     * 根据订单编号获取用订单列表
     *
     * @param orderId
     * @return Order
     */
    @Override
    public Order findOrder(String orderId) {
        return orderMapper.findOrder(orderId);
    }

    /**
     * 更改订单
     * @param order
     * @return String
     */
    @Override
    public String updateOrder(Order order) {
        Order order1 = orderMapper.findOrder(order.getId());//先查是否有这条记录
        int count;
        if(order1!=null){
            count= orderMapper.update(order);
            if(count>0){
                return Msg.SUCCESS_MSG;
            }
        }
        return Msg.FAIL_MSG;

    }

    /**
     * 当前订单状态下的商品导出
     * @param stute 订单状态
     * @return
     */
    public void export(HttpServletRequest request, HttpServletResponse response, String stute) {

        String[] excelHeader = {"订单编号", "订单状态","下单时间", "订单金额", "收货人", "联系电话","商品名称"};

        String fileName ="订单详情"+System.currentTimeMillis();
        List<Order> orderList = orderMapper.findOrders(stute,null);
        OutputStream ouputStream = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("order details");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }

        SimpleDateFormat sfp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < orderList.size(); i++) {
            row = sheet.createRow(i + 1);
            Order order = orderList.get(i);
            row.createCell(0).setCellValue(order.getId());
            row.createCell(1).setCellValue(order.getStute());
            row.createCell(2).setCellValue(sfp.format(order.getOrderTime()));
            row.createCell(3).setCellValue(order.getOrderPrice());
            row.createCell(4).setCellValue(order.getConsignee());
            row.createCell(5).setCellValue(order.getPhone());
            if(order.getOrderDetails().size()>1){//一个订单中有多个商品
                StringBuffer sb = new StringBuffer();
                for(int j = 0; j<order.getOrderDetails().size();j++){
                    sb.append(order.getOrderDetails().get(j).getCommodityName()+",");
                }
                sb.deleteCharAt(sb.length()-1);
                row.createCell(6).setCellValue(String.valueOf(sb));//将商品的名称拼接
            }else {
                row.createCell(6).setCellValue(order.getOrderDetails().get(0).getCommodityName());
            }

        }
        try {
            response.setHeader("Content-disposition", "attachment;filename="+new String(
                    fileName.getBytes("gbk"), "iso8859-1")+".xls");//解决中文乱码
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
