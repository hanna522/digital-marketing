/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

/**
 *
 * @author kal bugrara
 */
public class MarketChannelAssignment {
     
    Market market;
    Channel channel;
    int advertisementBudget;
    
    public MarketChannelAssignment(Market m, Channel c){
        market = m;
        channel = c; 
        advertisementBudget = 0;
    }

    public Market getMarket() {
        return market;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setadvertisementBudget(int b) {
        advertisementBudget = b;
    }

    public int getadvertisementBudget() {
        return advertisementBudget;
    }
}
