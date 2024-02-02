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

/**
 *
 * @author kal bugrara
 */
public class MarketCatalog {

    Business business;
    ArrayList<Market> markets;

    public MarketCatalog(Business b) {
        business = b;
        markets = new ArrayList<Market>();
    }

    public ArrayList<Market> getMarkets() {
        return markets;
    }

    public void addMarket(Market m) {
        markets.add(m);
    }

    public void addNewMarket(String s) {
        Market m = new Market(s);
        markets.add(m);
    }

    public Market pickRandomMarket() {
        if (markets.size() == 0) return null;
        Random random = new Random();
        int randomIndex = random.nextInt(markets.size());
        return markets.get(randomIndex);
    }

    public Market pickRandomSubMarket() {
        Market randomMarket = pickRandomMarket();
        ArrayList<Market> submarkets = randomMarket.getSubmarkets();
        if (submarkets.size() == 0) return null;
        Random random = new Random();
        int randomIndex = random.nextInt(submarkets.size());
        return submarkets.get(randomIndex);
    }

    public ArrayList<Market> pickRandomMarkets() {
        if (markets.size() == 0) return null;
        Random random = new Random();

        ArrayList<Market> randomMarkets = new ArrayList<Market>();
        int randomMarketNum = random.nextInt(1, markets.size()+1);
        for (int i = 0; i < randomMarketNum; i++) {
            int randomIndex = random.nextInt(markets.size());
            while (randomMarkets.contains(markets.get(randomIndex))) {
                randomIndex = random.nextInt(markets.size());
            }
            randomMarkets.add(markets.get(randomIndex));
        }
        return randomMarkets;
    }
    
    public ArrayList<Market> pickRandomSubMarkets() {
        ArrayList<Market> randomSubMarkets = new ArrayList<Market>();
        ArrayList<Market> randomMarkets = pickRandomMarkets();
    
        if (randomMarkets.size() == 0) {
            return null;
        }
    
        for (Market market : randomMarkets) {
            if (market.getSubmarkets().size() != 0) {
                Random random = new Random();
                int randomIndex = random.nextInt(market.getSubmarkets().size());
                randomSubMarkets.add(market.getSubmarkets().get(randomIndex));
            }
        }
        return randomSubMarkets;
 
    }

    public ArrayList<Market> getAllSubmarkets() {
        ArrayList<Market> subMarkets = new ArrayList<Market>();

        for (Market market : markets) {
            for (Market submarket : market.getSubmarkets()) {
                subMarkets.add(submarket);
            }
        }
        return subMarkets;
    }
}
