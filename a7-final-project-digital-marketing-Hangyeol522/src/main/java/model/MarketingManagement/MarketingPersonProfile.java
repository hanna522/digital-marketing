/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketingManagement;

import java.util.ArrayList;

import model.OrderManagement.GeneralOrder;
import model.Personnel.Person;
import model.Personnel.Profile;

/**
 *
 * @author kal bugrara
 */
public class MarketingPersonProfile extends Profile {
    ArrayList<GeneralOrder> salesOrders;

    public MarketingPersonProfile(Person p) {

        super(p, "Marketing");
        salesOrders = new ArrayList<GeneralOrder>();

    }

    public void addSalesOrder(GeneralOrder o) {
        salesOrders.add(o);
    }

    @Override
    public String getRole() {
        return "Marketing";
    }

}
