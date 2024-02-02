/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Supplier;

import java.util.ArrayList;
import java.util.Random;

import model.OrderManagement.MasterOrderReport;
import model.ProductManagement.Product;
import model.ProductManagement.ProductsReport;

/**
 *
 * @author kal bugrara
 */
public class SupplierDirectory {
    ArrayList<Supplier> suppliers;

    public SupplierDirectory() {
        suppliers = new ArrayList<Supplier>();
    }

    public Supplier newSupplier(String n) {
        Supplier supplier = new Supplier(n);
        suppliers.add(supplier);
        return supplier;

    }

    public Supplier findSupplier(String id) {

        for (Supplier supplier : suppliers) {

            if (supplier.toString().equals(id))
                return supplier;
        }
        return null;
    }

    public ArrayList<Supplier> getSupplierList() {
        return suppliers;
    }

    public Supplier pickRandomSupplier() {
        if (suppliers.size() == 0)
            return null;
        Random r = new Random();
        int randomIndex = r.nextInt(suppliers.size());
        return suppliers.get(randomIndex);
    }

    public void printShortInfo() {
        System.out.println("Checking what's inside the supplier directory.");
        System.out.println("There are " + suppliers.size() + " suppliers.");
        for (Supplier s : suppliers) {
            s.printShortInfo();
        }
    }

    public ArrayList<Product> getEveryProduct() {
        ArrayList<Product> everyProduct = new ArrayList<Product>();
        for (Supplier s : suppliers) {
            for (Product p : s.getProductCatalog().getProductList()) {
                everyProduct.add(p);
            }
        }
        return everyProduct;
    }

    public ArrayList<Product> getEveryProductAlwaysAboveTarget() {
        ArrayList<Product> everyProductAboveTarget = new ArrayList<>();
        for (Product p : getEveryProduct()) {
            if (p.isProductAlwaysAboveTarget()) {
                everyProductAboveTarget.add(p);
            }
        }
        return everyProductAboveTarget;
    }

    public void printEveryProduct() {
        System.out.println("======================= All Product =======================");
        System.out.println(String.format("%3s | %15s | %35s", "#", "Price", "Product Name"));
        System.out.println("-----------------------------------------------------------");

        int count = 0;
        for (Supplier s : suppliers) {
            for (Product p : s.getProductCatalog().getProductList()) {
                count++;
                System.out.println(String.format("%3d | %10s $%3d | %35s", count, " ", p.getTargetPrice(), p.toString()));
            }
        }
        System.out.println("-----------------------------------------------------------");
    }

    public void printEveryProductWithDiscount(double d) {
        System.out.println("======================= All Product =======================");
        int discountRate = (int) (d*100);
        System.out.println(String.format("%3s | %15s | %35s", "#", String.format("Price (%2d", (100-discountRate)) + "% DC)", "Product Name"));
        System.out.println("-----------------------------------------------------------");

        int count = 0;
        for (Supplier s : suppliers) {
            for (Product p : s.getProductCatalog().getProductList()) {
                count++;
                int discountPrice = (int) Math.floor(p.getTargetPrice() * d);
                System.out.println(String.format("%3d | %10s $%3d | %35s", count, " ", discountPrice, p.toString()));
            }
        }
        System.out.println("-----------------------------------------------------------");
    }

    public void printProductPerformanceReport() {
        System.out.println("===================================== Supplier Performance Report ====================================");
        System.out.println("* Revenue : total sales");
        System.out.println("* Performance Rate : # of Above Target Order / # of Total Order");
        System.out.println("* Profit : sum of (acutal price - target price)");
        System.out.println();
        System.out.println("[Supplier Performance Report by supplier]");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%35s | %15s | %10s | %20s | %10s", "Supplier Name", "# of Products", "Revenue($)", "Performance Rate(%)", "Profit($)"));
        System.out.println("------------------------------------------------------------------------------------------------------");

        for (Supplier s : suppliers) {
            System.out.print(String.format("%35s | ", s.toString()));
            s.getProductCatalog().generateProductPerformanceReport("Name").printProductReport();
        }

        int totalProductNum = 0;
        for (Supplier s : suppliers) {
            totalProductNum += s.getProductCatalog().generateProductPerformanceReport("Name").getProductSummaryListSize();
        }
        
        int totalRevenue = 0;
        for (Supplier s : suppliers) {
            totalRevenue = totalRevenue + s.getProductCatalog().generateProductPerformanceReport("Name").getRevenue();
        }
        int totalProfit = 0;
        for (Supplier s : suppliers) {
            totalProfit = totalProfit + s.getProductCatalog().generateProductPerformanceReport("Name").getProfit();
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%35s | %15d | %10d | %20s | %10d", "Total", getEveryProduct().size(), totalRevenue, "Performance Rate(%)", totalProfit));
        System.out.println("------------------------------------------------------------------------------------------------------");    
    }
}