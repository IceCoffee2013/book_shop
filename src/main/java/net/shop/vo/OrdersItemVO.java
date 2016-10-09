package net.shop.vo;

import java.io.Serializable;

/**
 * Created by Langley on 9/15/16.
 */
public class OrdersItemVO implements Serializable {

    private static final long serialVersionUID = 905987690676181624L;
    private int number;
    private int orderNumber;
    private int goodNumber;
    private int amount;
    private int price;

    public OrdersItemVO() {
    }

    public OrdersItemVO(int goodNumber, int amount, int price) {
        this.goodNumber = goodNumber;
        this.amount = amount;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        this.goodNumber = goodNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
