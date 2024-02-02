/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;
import java.util.Random;

import model.Business.Business;
import model.MarketModel.Channel;


/**
 *
 * @author kal bugrara
 */
public class ChannelCatalog {

    Business business;
    ArrayList<Channel> channels;

    public ChannelCatalog(Business b) {
        business = b;
        channels = new ArrayList<Channel>();
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void addChannel(Channel c) {
        channels.add(c);
    }

    public Channel pickRandomChannel() {
        if (channels.size() == 0) return null;
        Random random = new Random();
        int randomIndex = random.nextInt(channels.size());
        return channels.get(randomIndex);
    }

    public void printShortInfo() {
        System.out.println("Channel list for " + business.getName());
        for (Channel c : channels) {
            System.out.println(c.getName());
        }
    }
    
}
