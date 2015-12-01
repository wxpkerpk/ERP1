package com.guotion.material.service.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wx on 15/11/22.
 */
@Entity(name = "t_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
 class Order {
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(targetEntity = User.class)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getHaveElevator() {
        return haveElevator;
    }

    public void setHaveElevator(int haveElevator) {
        this.haveElevator = haveElevator;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getTrafficTel() {
        return trafficTel;
    }

    public void setTrafficTel(String trafficTel) {
        this.trafficTel = trafficTel;
    }

    public String getTrafficCategoryString() {
        return trafficCategoryString;
    }

    public void setTrafficCategoryString(String trafficCategoryString) {
        this.trafficCategoryString = trafficCategoryString;
    }

    public float getItemriginalCost() {
        return itemriginalCost;
    }

    public void setItemriginalCost(float itemriginalCost) {
        this.itemriginalCost = itemriginalCost;
    }

    public float getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(float orderCost) {
        this.orderCost = orderCost;
    }

    public int getHavePay() {
        return havePay;
    }

    public void setHavePay(int havePay) {
        this.havePay = havePay;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getTrafficerAddress() {
        return trafficerAddress;
    }

    public void setTrafficerAddress(String trafficerAddress) {
        this.trafficerAddress = trafficerAddress;
    }

    @OneToMany(targetEntity = OrderLog.class,cascade = CascadeType.ALL)
    public Set<OrderLog> getOrderLogSet() {
        return orderLogSet;
    }

    public void setOrderLogSet(Set<OrderLog> orderLogSet) {
        this.orderLogSet = orderLogSet;
    }
    String id;
    User owner;
    String customerName;
    int haveElevator;//0没有,1
    String itemCategory;
    String trafficTel;
    String trafficCategoryString;
    float itemriginalCost;
    float orderCost;
    int havePay;//0没有,1有
    int orderState;//0在厂家仓库1收货点仓库2发往目的地仓库3到达目的地仓库4市内配送中5到达客户家6完成
    String descr;
    String trafficerAddress;
    Set<OrderLog> orderLogSet;



    public Order() {
        orderLogSet=new HashSet<>();

    }
}
