/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;
import java.util.Random;

import model.Business.Business;
import model.MarketModel.Market;
import model.Personnel.Person;

/**
 *
 * @author kal bugrara
 */
public class CustomerDirectory {

    Business business;
    ArrayList<CustomerProfile> customerlist;

    public CustomerDirectory(Business d) {

        business = d;
        customerlist = new ArrayList<CustomerProfile>();

    }

    public CustomerProfile newCustomerProfile(Person p, CustomerType ct) {

        CustomerProfile sp = new CustomerProfile(p, ct);
        customerlist.add(sp);
        return sp;
    }

    public CustomerProfile findCustomer(String id) {

        for (CustomerProfile sp : customerlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null; //not found after going through the whole list
    }
        public CustomersReport generatCustomerPerformanceReport(){
        CustomersReport customersreport = new CustomersReport();
    
        for(CustomerProfile cp: customerlist){
            customersreport.addCustomerSummary(cp.generateCustomerSummary());
        }
        return customersreport; 
    }

    public CustomerProfile pickRandomCustomer(){
        if (customerlist.size() == 0) return null;
        Random r = new Random();
        int randomIndex = r.nextInt(customerlist.size());
        return customerlist.get(randomIndex);
    }

    public ArrayList<CustomerProfile> getCustomerList() {
        return customerlist;
    }

    public CustomerType getCustomerType(CustomerProfile cp) {
        return cp.getCustomerType();
    }
    public void printShortInfo() {
        System.out.println("There are " + customerlist.size() + "customers.");
    }
    public void printCustomerReport(CustomerTypeCatalog ctc, MarketCatalog mcl){
        CustomersReport customersReport = generatCustomerPerformanceReport();
        customersReport.printCustomerReport(ctc, mcl);
    }
}
