/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.MarketModel.Market;
import model.OrderManagement.GeneralOrder;
import model.OrderManagement.SolutionOrder;
import model.Personnel.Person;

/**
 *
 * @author kal bugrara
 */
public class CustomerProfile {
    ArrayList<GeneralOrder> orders;
    ArrayList<SolutionOrder> solutionOrders;
    ArrayList<Market> markets;
    Person person;
    CustomerType customerType;
    CustomerSummary customersummary;

    public CustomerProfile(Person p, CustomerType ct) {
        person = p;
        orders = new ArrayList<GeneralOrder>();
        solutionOrders = new ArrayList<SolutionOrder>();
        markets = new ArrayList<Market>();
        customerType = ct;
    }

    public int getTotalPricePerformance() {
        int sum = 0;
        for (GeneralOrder order : orders) {
            sum = sum + order.getOrderPricePerformance();
        }
        for (SolutionOrder solutionOrder : solutionOrders) {
            sum = sum + solutionOrder.getOrderPricePerformance();
        }
        return sum;
    }

    public int getOrderTotal() {
        int sum = 0;
        for (GeneralOrder order : orders) {
            sum = sum + order.getOrderTotal();
        }
        for (SolutionOrder solutionOrder : solutionOrders) {
            sum = sum + solutionOrder.getOrderTotal();
        }
        return sum;
    }

    public int getNumberOfOrdersAboveTotalTarget() {
        // for each order in the customer order list
        // calculate if order is positive (actual order total is greater than sum of
        // item targets
        // if yes then add 1 to total
        int sum = 0;
        for (GeneralOrder o : orders) {
            if (o.isOrderAboveTotalTarget() == true)
                sum = sum + 1;
        }
        for (SolutionOrder so : solutionOrders) {
            if (so.isOrderAboveTotalTarget() == true)
                sum = sum + 1;
        }

        return sum;
    }

    public int getNumberOfOrdersBelowTotalTarget() {
        return 0;
    }
    // for each order in the customer order list
    // calculate if order is negative
    // if yes then add 1 to total

    public boolean isMatch(String id) {
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    public void addCustomerOrder(GeneralOrder o) {
        orders.add(o);
    }

    public void addCustomerSolutionOrder(SolutionOrder so) {
        solutionOrders.add(so);
    }

    public void addMarket(Market m) {
        markets.add(m);
    }

    public void addMarkets(ArrayList<Market> ms) {
        for (Market m : ms) {
            markets.add(m);
        }
    }

    @Override
    public String toString() {
        return person.getPersonId();
    }

    public String getCustomerId() {
        return person.getPersonId();
    }

    public Person getPerson() {
        return person;
    }

    public ArrayList<Market> getMarkets() {
        return markets;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public int getOrderCount() {
        return orders.size();
    }

    public CustomerSummary generateCustomerSummary() {
        customersummary = new CustomerSummary(this);
        return customersummary;
    }

    public void printShortInfo() {
        System.out.println("=========== My Profile ==========");
        System.out.println();
        System.out.println("[Account Information]");
        System.out.println("---------------------------------");
        System.out.println(String.format("%15s | %15s", "ID", getCustomerId()));
        System.out.println(String.format("%15s | %15s", "Type", customerType.toString()));
        for (Market m : markets) {
            int index = markets.indexOf(m) + 1;
            System.out.println(String.format("%15s | %15s", "Extra Info " + index, m.getFirstCharacteristic()));
        }
        System.out.println("---------------------------------");
        System.out.println();

        System.out.println("[Order Information]");
        System.out.println("---------------------------------");
        System.out.println(String.format("%15s | %15s", "Amount", "$ " + getOrderTotal()));
        int totalOrderNum = orders.size() + solutionOrders.size();
        System.out.println(String.format("%15s | %15s", "Times", totalOrderNum + " times"));
        System.out.println("---------------------------------");
    }

}
