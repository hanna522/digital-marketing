/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.Product;
import model.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class GeneralOrder {

    ArrayList<OrderItem> orderItems;
    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MarketChannelAssignment mca;
    String status;
    Channel channel;

    public GeneralOrder() {
    }

    public GeneralOrder(CustomerProfile cp, Channel c) {
        orderItems = new ArrayList<OrderItem>();
        customer = cp;
        customer.addCustomerOrder(this); // we link the order to the customer
        salesperson = null;
        status = "in process";
        channel = c;
    }

    public GeneralOrder(CustomerProfile cp, SalesPersonProfile ep, Channel c) {
        orderItems = new ArrayList<OrderItem>();
        customer = cp;
        salesperson = ep;
        customer.addCustomerOrder(this); // we link the order to the customer
        salesperson.addSalesOrder(this);
        channel = c;
    }

    public OrderItem newOrderItem(Product p, int actualPrice, int q) {
        OrderItem oi = new OrderItem(p, actualPrice, q);
        orderItems.add(oi);
        return oi;
    }

    // order total is the sumer of the order item totals
    public int getOrderTotal() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.getOrderItemTotal();
        }
        return sum;
    }

    // cacluate revenue
    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.calculatePricePerformance(); // positive and negative values
        }
        return sum;
    }

    public int getNumberOfOrderItemsAboveTarget() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            if (oi.isActualAboveTarget() == true) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    // sum all the item targets and compare to the total of the order
    public boolean isOrderAboveTotalTarget() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.getOrderItemTargetTotal(); // product targets are added
        }
        if (getOrderTotal() > sum) {
            return true;
        } else {
            return false;
        }

    }

    public void CancelOrder() {
        status = "Cancelled";
    }

    public void Submit() {
        status = "Submitted";
    }

    public int getNumberOfItems() {
        return orderItems.size();
    }

    public String getCustomerId() {
        return customer.getCustomerId();
    }

    public CustomerProfile getCustomer() {
        return customer;
    }

    public Channel getChannel() {
        return channel;
    }

    public ArrayList<OrderItem> getorderitems() {
        return orderItems;
    }
}
