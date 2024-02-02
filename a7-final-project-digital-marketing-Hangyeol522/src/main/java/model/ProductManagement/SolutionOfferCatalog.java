/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import java.util.Random;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {
    
    ArrayList<SolutionOffer> solutionoffers;
    
    public SolutionOfferCatalog() {
        solutionoffers = new ArrayList<SolutionOffer>();
    }

    public void addSolutionOffer(SolutionOffer so) {
        solutionoffers.add(so);
    }

    public SolutionOffer pickRandomSolutionOffer() {
        Random random = new Random();
        int randomIndex = random.nextInt(solutionoffers.size());
        return solutionoffers.get(randomIndex);
    }

    public ArrayList<SolutionOffer> getCustomizedSolutionOffers(CustomerProfile cp, Channel c) {
        ArrayList<SolutionOffer> customizedSolutionOfferList = new ArrayList<SolutionOffer>();
        ArrayList<Market> customerMarkets = cp.getMarkets();
        for (SolutionOffer so : solutionoffers) {
            if (customerMarkets.contains(so.getMarket()) && so.getChannel() == c) {
                customizedSolutionOfferList.add(so);
            }
        }
        return customizedSolutionOfferList;
    }

    public ArrayList<SolutionOffer> getSolutionOffers() {
        return solutionoffers;
    }

    public void printShortInfo() {
        System.out.println("Solution Offers List");
        for (SolutionOffer so : solutionoffers) {
            so.printShortInfo();
        }
    }
    //Market m1 = new Market("Test Market");
    //Channel c1 = new Channel("Test Channel");
    //MarketChannelAssignment mca = new MarketChannelAssignment(m1, c1);
    //SolutionOffer so = new SolutionOffer(mca);
    //Product product1 = new Product(5, 1, 10);
    //so.addProduct(product1);
    //so.setPrice(3);
}
