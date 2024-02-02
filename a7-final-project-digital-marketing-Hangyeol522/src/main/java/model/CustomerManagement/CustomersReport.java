/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.MarketModel.Market;

/**
 *
 * @author kal bugrara
 */
public class CustomersReport {
    ArrayList<CustomerSummary> customerlist;

    public CustomersReport() {
        customerlist = new ArrayList();
    }

    public void addCustomerSummary(CustomerSummary cs) {
        customerlist.add(cs);
    }

    public ArrayList<CustomerSummary> getcustomerlist() {
        return customerlist;
    }

    public int getTotalRevenue() {
        int sum = 0;
        for (CustomerSummary cs : customerlist) {
            sum = sum + cs.getRevenue();
        }
        return sum;
    }

    public int getTotalProfit() {
        int sum = 0;
        for (CustomerSummary cs : customerlist) {
            sum = sum + cs.getProfit();
        }
        return sum;
    }

    public int getAboveTargetRate() {
        int aboveTarget = 0;
        int orderCount = 0;
        for (CustomerSummary cs : customerlist) {
            aboveTarget = aboveTarget + cs.getAboveTargetOrderCount();
            orderCount = orderCount + cs.getOrderCount();
        }
        return (int) ((orderCount > 0) ? ((double) aboveTarget / orderCount) * 100 : 0.0);

    }

    public int getCustomerTypeCount(CustomerType ct) {
        int customerCount = 0;
        for (CustomerSummary cs : customerlist) {
            if (cs.checkType(ct)) {
                customerCount++;
            }
        }
        return customerCount;
    }

    public int getCustomerTypeRevenue(CustomerType ct) {
        int sum = 0;
        for (CustomerSummary cs : customerlist) {
            if (cs.checkType(ct)) {
                sum = sum + cs.getRevenue();
            }
        }
        return sum;
    }

    public int getCustomerTypeProfit(CustomerType ct) {
        int sum = 0;
        for (CustomerSummary cs : customerlist) {
            if (cs.checkType(ct)) {
                sum = sum + cs.getProfit();
            }
        }
        return sum;
    }

    public int getCustomerTypeAboveTargetOrderCount(CustomerType ct) {
        int aboveTarget = 0;
        int orderCount = 0;
        for (CustomerSummary cs : customerlist) {
            if (cs.checkType(ct)) {
                aboveTarget = aboveTarget + cs.getAboveTargetOrderCount();
                orderCount = orderCount + cs.getOrderCount();
            }
        }
        return (int) ((orderCount > 0) ? ((double) aboveTarget / orderCount) * 100 : 0.0);
    }

    public void printCustomerReport(CustomerTypeCatalog ctc, MarketCatalog mcl) {
        System.out.println("========================= Customer Performance Report ==========================");
        System.out.println("* Revenue : total sales");
        System.out.println("* Performance Rate : # of Above Target Order / # of Total Order");
        System.out.println("* Profit : sum of (acutal price - target price)");
        System.out.println();
        System.out.println("[Customer Performance Report by Customer Type]");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(String.format("%15s | %13s | %10s | %20s | %10s", "Customer Type", "# of Customer", "Revenue($)", "Performance Rate(%)", "Profit($)"));
        System.out.println("--------------------------------------------------------------------------------");

        for (CustomerType ct : ctc.getCustomerTypeList()) {
            System.out.println(String.format("%15s | %13d | %10d | %20d | %10d", ct.toString(), getCustomerTypeCount(ct), getCustomerTypeRevenue(ct), getCustomerTypeAboveTargetOrderCount(ct), getCustomerTypeProfit(ct)));
            }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(String.format("%15s | %13d | %10d | %20d | %10d", "Total", customerlist.size(), getTotalRevenue(), getAboveTargetRate(), getTotalProfit()));
        System.out.println("--------------------------------------------------------------------------------");

    }
    
}
