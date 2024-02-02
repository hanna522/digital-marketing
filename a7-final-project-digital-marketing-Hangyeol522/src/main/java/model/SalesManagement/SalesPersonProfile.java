/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.SalesManagement;

import java.util.ArrayList;

import model.OrderManagement.GeneralOrder;
import model.OrderManagement.SolutionOrder;
import model.Personnel.Person;
import model.Personnel.Profile;

/**
 *
 * @author kal bugrara
 */
public class SalesPersonProfile extends Profile {
    ArrayList<GeneralOrder> salesOrders;
    ArrayList<SolutionOrder> salesSolutionOrders;

    public SalesPersonProfile(Person p) {

        super(p, "Sales");
        salesOrders = new ArrayList<GeneralOrder>();

    }

    public void addSalesOrder(GeneralOrder o) {
        salesOrders.add(o);
    }

    public void addSalesSolutionOrder(SolutionOrder so) {
        salesSolutionOrders.add(so);
    }

    @Override
    public String getRole() {
        return "Sales";
    }

}
