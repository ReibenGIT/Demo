package so.sao.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * 订单实体
 * Created by acer on 2017/7/10.
 */

public class Order implements Serializable{
    /**
     * 订单编号
     */
    private String id;
    /**
     * 订单状态
     */
    private String stute;
    /**
     * 下单时间
     */
    private BigInteger orderTime;
    /**
     *订单金额
     */
    private double orderPrice;
    /**
     *收货人
     */
    private String consignee;
    /**
     *收货人电话
     */
    private String phone;
    /**
     *订单详情
     */
    private List<OrderDetails> orderDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStute() {
        return stute;
    }

    public void setStute(String stute) {
        this.stute = stute;
    }

    public BigInteger getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(BigInteger orderTime) {
        this.orderTime = orderTime;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
