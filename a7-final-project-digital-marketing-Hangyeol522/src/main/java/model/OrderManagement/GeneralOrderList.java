/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class GeneralOrderList {
    ArrayList<GeneralOrder> generalOrders;

    public GeneralOrderList() {
        generalOrders = new ArrayList<GeneralOrder>();
    }

    public GeneralOrder newOrder(CustomerProfile cp, Channel c) {
        GeneralOrder o = new GeneralOrder(cp, c);
        generalOrders.add(o);
        return o;

    }

    public GeneralOrder newOrder(CustomerProfile cp, SalesPersonProfile spp, Channel c) {
        GeneralOrder o = new GeneralOrder(cp, spp, c);
        generalOrders.add(o);

        return o;
    }

    public ArrayList<GeneralOrder> getGeneralOrders() {
        return generalOrders;
    }

    public int getSalesVolume() {

        int sum = 0;
        for (GeneralOrder order : generalOrders) {
            sum = sum + order.getOrderTotal();
        }
        return sum;
    }

    public void printShortInfo() {
        System.out.println("Checking what's inside the master order list.");
        System.out.println("There are " + generalOrders.size() + " order.");
    }

}
