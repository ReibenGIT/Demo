package so.sao.domain;

import java.io.Serializable;

/**
 *
 * 定案详情实体
 * Created by acer on 2017/7/10.
 */
public class OrderDetails implements Serializable {
    /**
     *订单详情标号
     */
    private String orderDetailid;
    /**
     *商品编号
     */
    private String commodityId;
    /**
     *商品名称
     */
    private String commodityName;
    /**
     *商品价格
     */
    private double commodityPrice;
    /**
     *商品数量
     */
    private int orderNumber;
    /**
     *订单编号
     */
    private String orderId;

    public String getOrderDetailid() {
        return orderDetailid;
    }

    public void setOrderDetailid(String orderDetailid) {
        this.orderDetailid = orderDetailid;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
