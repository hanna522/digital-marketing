/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;
import java.util.Random;

import model.ProductManagement.SolutionOffer;

/**
 *
 * @author kal bugrara
 */
public class Market {
    ArrayList<SolutionOffer> so;
    ArrayList<MarketChannelAssignment> channels;
    ArrayList<String> characteristics;
    ArrayList<Market> submarkets;
    int size;

    public Market(String s) {
        characteristics = new ArrayList<String>();
        characteristics.add(s);
        submarkets = new ArrayList<Market>();
        so = new ArrayList<SolutionOffer>();
        channels = new ArrayList<MarketChannelAssignment>();
    }

    public ArrayList<SolutionOffer> getSolutionOffer() {
        return so;
    }

    public ArrayList<MarketChannelAssignment> getChannels() {
        return channels;
    }

    public ArrayList<String> getCharacteristics() {
        return characteristics;
    }

    public ArrayList<Market> getSubmarkets() {
        return submarkets;
    }

    public int getSize() {
        return size;
    }

    public void addSubmarket(Market sm) {
        submarkets.add(sm);
    }

    public String getFirstCharacteristic() {
        return characteristics.get(0);
    }

    public Market pickRandomSubMarket() {
        if (submarkets.size() == 0) return null;
        Random random = new Random();
        int randomIndex = random.nextInt(submarkets.size());
        return submarkets.get(randomIndex);
    }
    
    public void printShortInfo() {
        for (String characteristic : characteristics) {
            System.out.print(characteristic + " ");
        }
        System.out.println();
        for (Market submarket : submarkets) {
            for (String characteristic : submarket.getCharacteristics()) {
                System.out.print(characteristic + " ");
            }
        }
    }
    
}
