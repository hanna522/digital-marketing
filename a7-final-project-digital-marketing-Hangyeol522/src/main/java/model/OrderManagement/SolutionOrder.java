package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.Product;
import model.ProductManagement.SolutionOffer;
import model.SalesManagement.SalesPersonProfile;

public class SolutionOrder {
    ArrayList<SolutionOrderItem> solutionOrderItems;
    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MarketChannelAssignment mca;
    String status;
    Channel channel;

    public SolutionOrder() {
    }

    public SolutionOrder(CustomerProfile cp, Channel c) {
        solutionOrderItems = new ArrayList<SolutionOrderItem>();
        customer = cp;
        customer.addCustomerSolutionOrder(this);
        salesperson = null;
        status = "in process";
        channel = c;
    }

    public SolutionOrder(CustomerProfile cp, SalesPersonProfile ep, Channel c) {
        solutionOrderItems = new ArrayList<SolutionOrderItem>();
        customer = cp;
        salesperson = ep;
        customer.addCustomerSolutionOrder(this); // we link the order to the customer
        salesperson.addSalesSolutionOrder(this);
        channel = c;
    }

    public SolutionOrderItem newSolutionOrderItem(SolutionOffer sof, int actualPrice, int q) {
        SolutionOrderItem soi = new SolutionOrderItem(sof, actualPrice, q);
        solutionOrderItems.add(soi);
        return soi;
    }

    // order total is the sumer of the order item totals
    public int getOrderTotal() {
        int sum = 0;
        for (SolutionOrderItem soi : solutionOrderItems) {
            sum = sum + soi.getSolutionOrderItemTotal();
        }
        return sum;
    }

    // caculate revenue
    public int getOrderPricePerformance() {
        int sum = 0;
        for (SolutionOrderItem soi : solutionOrderItems) {
            sum = sum + soi.getPricePerformance();
        }
        return sum;
    }

    public int getNumberOfOrderItemsAboveTarget() {
        int sum = 0;
        for (SolutionOrderItem soi : solutionOrderItems) {
            for (OrderItem oi : soi.getOrderItems()) {
                if (oi.isActualAboveTarget() == true) {
                    sum = sum + 1;
                }
            }
        }
        return sum;
    }
    
    public boolean isOrderAboveTotalTarget() {
        int sum = 0;
        for (SolutionOrderItem soi : solutionOrderItems) {
            for (OrderItem oi : soi.getOrderItems()) {
                sum = sum + oi.getOrderItemTargetTotal();
            }
        }
        if (getOrderTotal() > sum) {
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfSolutionOrderItems() {
        return solutionOrderItems.size();
    }

    public int getNumberOfItems() {
        int itemsTotal = 0;
        for (SolutionOrderItem soi : solutionOrderItems) {
            itemsTotal = itemsTotal + soi.getSelectedSolutionOffer().getProducts().size();
        }
        return itemsTotal;
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

    public ArrayList<SolutionOrderItem> getSolutionOrderItems() {
        return solutionOrderItems;
    }

}
