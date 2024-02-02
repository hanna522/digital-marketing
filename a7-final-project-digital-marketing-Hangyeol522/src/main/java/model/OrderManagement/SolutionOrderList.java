package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.SalesManagement.SalesPersonProfile;

public class SolutionOrderList {
    ArrayList<SolutionOrder> solutionOrders;

    public SolutionOrderList() {
        solutionOrders = new ArrayList<SolutionOrder>();
    }

    public SolutionOrder newOrder(CustomerProfile cp, Channel c) {
        SolutionOrder so = new SolutionOrder(cp, c);
        solutionOrders.add(so);
        return so;

    }

    public SolutionOrder newOrder(CustomerProfile cp, SalesPersonProfile spp, Channel c) {
        SolutionOrder so = new SolutionOrder(cp, spp, c);
        solutionOrders.add(so);

        return so;
    }

    public ArrayList<SolutionOrder> getSolutionOrders() {
        return solutionOrders;
    }

    public int getSalesVolume() {
        int sum = 0;
        for (SolutionOrder so : solutionOrders) {
            sum = sum + so.getOrderTotal();
        }
        return sum;
    }

    public void printShortInfo() {
        System.out.println("Checking what's inside the master solution order list.");
        System.out.println("There are " + solutionOrders.size() + " order.");
    }
}
