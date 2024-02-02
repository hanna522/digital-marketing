package model.MarketingManagement;

import java.util.ArrayList;

import model.Business.Business;
import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;

public class AdvertisementCatalog {
    Business business;
    ArrayList<Advertisement> advertisements;

    public AdvertisementCatalog(Business b) {
        business = b;
        advertisements = new ArrayList<Advertisement>();
    }

    public void addAdvertisement(Advertisement ad) {
        advertisements.add(ad);
    }

    public void addNewAdvertisement(MarketChannelAssignment mca, int b, String n, String c) {
        Advertisement newAd = new Advertisement(mca, b, n, c);
        advertisements.add(newAd);
    }

    public ArrayList<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public Advertisement getCustomizedAd(CustomerProfile cp, Channel c) {
        for (Advertisement ad : advertisements) {
            if (ad.getSelectedMarketChannelAssignment().getChannel() == c && cp.getMarkets().contains(ad.getSelectedMarketChannelAssignment().getMarket())) {
                return ad;
            }
        }
        return null;
    }

    public int getTotalMarketingBudget() {
        int sum = 0;
        for (Advertisement ad : advertisements) {
            sum += ad.getBudget();
        }
        return sum;
    }

    public int getChannelMarketingBudget(Channel c) {
        int sum = 0;
        for (Advertisement ad : advertisements) {
            if (ad.getSelectedMarketChannelAssignment().getChannel() == c) {
                sum += ad.getBudget();
            }
        }
        return sum;
    }

    public int getMarketMarketingBudget(Market m) {
        int sum = 0;
        for (Advertisement ad : advertisements) {
            if (ad.getSelectedMarketChannelAssignment().getMarket() == m) {
                sum += ad.getBudget();
            }
        }
        return sum;
    }

    public int getMcaMarketingBudget(MarketChannelAssignment mca) {
        for (Advertisement ad : advertisements) {
            if (ad.getSelectedMarketChannelAssignment().getMarket() == mca.getMarket() && ad.getSelectedMarketChannelAssignment().getChannel() == mca.getChannel()) {
                return ad.getBudget();
            }
        }
        return 0;
    }

    public ArrayList<MarketChannelAssignment> selectedMarketChannelAssignments() {
        ArrayList<MarketChannelAssignment> mcaList = new ArrayList<MarketChannelAssignment>();
        for (Advertisement ad : advertisements) {
            mcaList.add(ad.getSelectedMarketChannelAssignment());
        }
        return mcaList;
    }
}
