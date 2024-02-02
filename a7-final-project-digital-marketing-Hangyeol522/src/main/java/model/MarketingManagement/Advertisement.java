package model.MarketingManagement;

import model.MarketModel.MarketChannelAssignment;

public class Advertisement {

    MarketChannelAssignment selectedMarketChannelAssignment;
    int budget;
    String name;
    String content;

    public Advertisement(MarketChannelAssignment mca, int b, String n, String c) {
        selectedMarketChannelAssignment = mca;
        mca.setadvertisementBudget(b);
        budget = b;
        name = n;
        content = c;
    }

    public MarketChannelAssignment getSelectedMarketChannelAssignment() {
        return selectedMarketChannelAssignment;
    }
    
    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public int getBudget() {
        return budget;
    }

}
