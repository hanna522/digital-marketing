
package model.OrderManagement;

import java.util.ArrayList;
import java.util.Random;

import model.ProductManagement.Product;
import model.ProductManagement.SolutionOffer;

public class SolutionOrderItem {
    SolutionOffer selectedSolutionOffer;
    ArrayList<OrderItem> orderitems;
    int actualPrice;
    int quantity;

    public SolutionOrderItem(SolutionOffer sof, int paidprice, int q) {
        selectedSolutionOffer = sof;
        sof.addSolutionOrderItem(this);
        orderitems = new ArrayList<OrderItem>();
        quantity = q;
        this.actualPrice = paidprice;
        // create an orderitem for each product in selectedSolutionOffer and connect it to the product
        for (Product p : selectedSolutionOffer.getProducts()) {
            OrderItem newOrderItem = new OrderItem(p, sof.getIndividualPrice(paidprice, q).get(p), q);
            orderitems.add(newOrderItem);
        }
    }

    public int getSolutionOrderItemTotal() {
        return actualPrice * quantity;
    }

    public int getPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.calculatePricePerformance();
        }
        return sum;
    }
    
    public SolutionOffer getSelectedSolutionOffer() {
        return selectedSolutionOffer;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderitems;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
