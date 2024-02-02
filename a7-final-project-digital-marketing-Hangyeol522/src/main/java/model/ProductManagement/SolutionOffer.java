/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.CustomerManagement.CustomerType;
import model.MarketModel.Market;
import model.MarketModel.Channel;
import model.MarketModel.MarketChannelAssignment;
import model.OrderManagement.OrderItem;
import model.OrderManagement.SolutionOrderItem;

/**
 *
 * @author kal bugrara
 */
public class SolutionOffer {
    ArrayList<Product> products;
    int price;// floor, ceiling, and target ideas
    MarketChannelAssignment marketChannelComb;
    CustomerType customerType;
    ArrayList<SolutionOrderItem> solutionOrderItems;

    public SolutionOffer(MarketChannelAssignment m) {
        marketChannelComb = m;
        products = new ArrayList<Product>();
        solutionOrderItems = new ArrayList<SolutionOrderItem>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void setPrice(int p) {
        price = p;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Market getMarket() {
        return marketChannelComb.getMarket();
    }

    public Channel getChannel() {
        return marketChannelComb.getChannel();
    }

    public void addSolutionOrderItem(SolutionOrderItem oi) {
        solutionOrderItems.add(oi);
    }

    public int getSellingPrice() {
        return price;
    }

    public int getTargetPrice() {
        int sum = 0;
        for (Product p : products) {
            sum += p.getTargetPrice();
        }
        return sum;
    }

    public int getCeilingPrice() {
        int sum = 0;
        for (Product p : products) {
            sum += p.getCeilingPrice();
        }
        return sum;
    }

    public int getDiscountAmount() {
        int originPrice = 0;
        for (Product p : getProducts()) {
            originPrice = originPrice + p.getTargetPrice();
        }
        return originPrice - price;
    }

    public MarketChannelAssignment getMarketChannelAssignment() {
        return marketChannelComb;
    }

    public HashMap<Product, Integer> getIndividualPrice(int p, int q) {
        HashMap<Product, Integer> priceMatch = new HashMap<Product, Integer>();

        int totalTargetPrice = 0;
        for (Product product : products) {
            totalTargetPrice = totalTargetPrice + product.getTargetPrice();
        }

        int beforeAdjustTotalPrice = 0;
        for (Product product : products) {
            double percentage = (double) product.getTargetPrice() / totalTargetPrice;
            int individualPrice = (int) Math.round(p * percentage);
            priceMatch.put(product, individualPrice);
            beforeAdjustTotalPrice = beforeAdjustTotalPrice + individualPrice;
        }

        int redundant = p - beforeAdjustTotalPrice;

        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        Product randomProduct = products.get(randomIndex);
        int adjustedIndividualPrice = priceMatch.get(randomProduct) + redundant;
        priceMatch.put(randomProduct, adjustedIndividualPrice);
        
    return priceMatch;
    }

    public void printTestInfo() {
        System.out.println("  Bundle Products : ");
        System.out.println("   (Market : " + marketChannelComb.getMarket().getFirstCharacteristic() + " / Channel : " + marketChannelComb.getChannel().getName() + ")");

        for (Product p : products) {
            System.out.println("   " + p.toString());            
        }
        System.out.println("  Price : " + price);
    }

    public void printShortInfo() {
        System.out.print(String.format(" | $%4s (Save $%2d)", price, getDiscountAmount()));

        for (Product p : products) {
            if (products.indexOf(p) == 0) {
            System.out.println(String.format(" | %34s", p.toString()));
            } else {
                System.out.println(String.format("%3s | %16s | + %32s ", " ", " ", p.toString()));
            }
        }
        System.out.println("-----------------------------------------------------------");
    }

}