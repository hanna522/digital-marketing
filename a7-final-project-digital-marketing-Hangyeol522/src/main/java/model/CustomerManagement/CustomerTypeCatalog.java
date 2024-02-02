package model.CustomerManagement;

import java.util.ArrayList;
import java.util.Random;

import model.Business.Business;

public class CustomerTypeCatalog {
    
    Business business;
    ArrayList<CustomerType> customerTypeList;

    public CustomerTypeCatalog(Business b) {
        customerTypeList = new ArrayList<CustomerType>();
    }

    public void addCustomerType(CustomerType ct) {
        customerTypeList.add(ct);
    }

    public void addNewCustomerType(String s) {
        CustomerType customertype = new CustomerType(s);
        customerTypeList.add(customertype);
    }

    public CustomerType findCustomerType(String s) {
        CustomerType out = null;
        for (CustomerType ct : customerTypeList) {
            if (ct.toString() == s) {
                out = ct;
            }
        }
        return out;
    }

    public ArrayList<CustomerType> getCustomerTypeList() {
        return customerTypeList;
    }

    public CustomerType getRandomCustomerType() {

        Random random = new Random();
        
        int randomIndex = random.nextInt(customerTypeList.size());
        return customerTypeList.get(randomIndex);
    }
}
