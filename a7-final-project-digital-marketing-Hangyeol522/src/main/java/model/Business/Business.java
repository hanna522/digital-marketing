/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerTypeCatalog;
import model.CustomerManagement.MarketCatalog;
import model.MarketingManagement.MarketingPersonDirectory;
import model.OrderManagement.GeneralOrderList;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.SolutionOrderList;
import model.Personnel.EmployeeDirectory;
import model.Personnel.PersonDirectory;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOfferCatalog;
import model.SalesManagement.SalesPersonDirectory;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccountDirectory;
import model.MarketingManagement.AdvertisementCatalog;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory;
    GeneralOrderList generalorderlist;
    SolutionOrderList solutionorderlist;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog;
    ChannelCatalog channelcatalog;
    SolutionOfferCatalog solutionoffercatalog;
    CustomerDirectory customerdirectory;
    EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;
    CustomerTypeCatalog customertypecatalog;
    AdvertisementCatalog advertisementCatalog;
    MasterOrderList masterorderlist;

    public Business(String n) {
        name = n;
        generalorderlist = new GeneralOrderList();
        suppliers = new SupplierDirectory();
        solutionoffercatalog = new SolutionOfferCatalog();
        persondirectory = new PersonDirectory();
        customerdirectory = new CustomerDirectory(this);
        salespersondirectory = new SalesPersonDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        marketingpersondirectory = new MarketingPersonDirectory(this);
        employeedirectory = new EmployeeDirectory(this);
        marketcatalog = new MarketCatalog(this);
        customertypecatalog = new CustomerTypeCatalog(this);
        channelcatalog = new ChannelCatalog(this);
        solutionorderlist = new SolutionOrderList();
        advertisementCatalog = new AdvertisementCatalog(this);
        masterorderlist = new MasterOrderList(generalorderlist, solutionorderlist);
    }

    public String getName() {
        return name;
    }

    public int getSalesVolume() {
        return generalorderlist.getSalesVolume() + solutionorderlist.getSalesVolume();
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }
    public MarketingPersonDirectory getMarketingPersonDirectory() {
        return marketingpersondirectory;
    }

    public SupplierDirectory getSupplierDirectory() {
        return suppliers;
    }

    public ProductsReport getSupplierPerformanceReport(String n) {
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();
    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String n) {

        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();
    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); //return size of the arraylist
        return i;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerdirectory;
    }

    public SalesPersonDirectory getSalesPersonDirectory() {
        return salespersondirectory;
    }

    public GeneralOrderList getGeneralOrderList() {
        return generalorderlist;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    public MarketCatalog getMarketCatalog() {
        return marketcatalog;
    }

    public ChannelCatalog getChannelcatalog() {
        return channelcatalog;
    }

    public CustomerTypeCatalog getCustomertypecatalog() {
        return customertypecatalog;
    }

    public SolutionOfferCatalog getSolutionoffercatalog() {
        return solutionoffercatalog;
    }

    public SolutionOrderList getSolutionOrderList() {
        return solutionorderlist;
    }

    public AdvertisementCatalog getAdvertisementCatalog() {
        return advertisementCatalog;
    }

    public MasterOrderList getMasterOrderList() {
        return masterorderlist;
    }

    public void printShortInfo(){
        System.out.println("Checking what's inside the business hierarchy.");
        suppliers.printShortInfo();
        customerdirectory.printShortInfo();
        generalorderlist.printShortInfo();
    }

}
