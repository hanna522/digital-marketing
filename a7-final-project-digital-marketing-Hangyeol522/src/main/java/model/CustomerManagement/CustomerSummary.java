/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import model.OrderManagement.GeneralOrder;

/**
 *
 * @author kal bugrara
 */
public class CustomerSummary {
    CustomerProfile selectedCustomerProfile;
    int ordertotal;
    public CustomerSummary(CustomerProfile cp){
        selectedCustomerProfile = cp;
    }

    public boolean checkType(CustomerType ct) {
        return selectedCustomerProfile.getCustomerType() == ct;
    }

    public int getRevenue() {
        return selectedCustomerProfile.getOrderTotal();
    }

    public int getProfit() {
        return selectedCustomerProfile.getTotalPricePerformance();
    }

    public int getAboveTargetOrderCount() {
        return selectedCustomerProfile.getNumberOfOrdersAboveTotalTarget();
    }

    public int getOrderCount() {
        return selectedCustomerProfile.getOrderCount();
    }
    
    public void printCustomerSummary() {
        System.out.println(String.format("%25s | %10s | %10s", "Total", "#of Customer", "Revenue($)"));       
    }
    
}
