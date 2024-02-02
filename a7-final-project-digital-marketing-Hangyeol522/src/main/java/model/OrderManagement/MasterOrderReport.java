package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerSummary;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketingManagement.Advertisement;
import model.MarketingManagement.AdvertisementCatalog;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;

public class MasterOrderReport {
    ArrayList<MasterOrderSummary> masterOrderSummarys;
    GeneralOrderList generalOrderList;
    SolutionOrderList solutionOrderList;

    public MasterOrderReport() {
        masterOrderSummarys = new ArrayList<MasterOrderSummary>();
    }

    public void generateOrderReport(GeneralOrderList gol, SolutionOrderList sol) {

        generalOrderList = gol;
        solutionOrderList = sol;

        MasterOrderSummary masterOrderSummary;

        // OrderSummaryComparator comparator = new OrderSummaryComparator();

        for (GeneralOrder generalOrder : generalOrderList.getGeneralOrders()) {
            masterOrderSummary = new MasterOrderSummary(generalOrder);
            masterOrderSummarys.add(masterOrderSummary);
        }

        for (SolutionOrder solutionOrder : solutionOrderList.getSolutionOrders()) {
            masterOrderSummary = new MasterOrderSummary(solutionOrder);
            masterOrderSummarys.add(masterOrderSummary);            
        }
        // Collections.sort(orderSummaryList, comparator);
    }

    public int getTotalProfit() {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            sum = sum + ms.getOrderProfit();
        }
        return sum;
    }

    public int getTotalRevenue() {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            sum = sum + ms.getOrderRevenue();
        }
        return sum;
    }

    public int getMarketProfit(Market m) {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkMarket(m)) {
                sum = sum + ms.getOrderProfit();
            }
        }
        return sum;
    }

    public int getMarketRevenue(Market m) {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkMarket(m)) {
                sum = sum + ms.getOrderRevenue();
            }
        }
        return sum;
    }

    public int getMarketPerformanceRate(Market m) {
        int aboveTarget = 0;
        int orderCount = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkMarket(m)) {
                orderCount++;
                if (ms.getAboveTargetOrderCount()) {
                    aboveTarget++;
                }
            }
        }
        return (int) ((orderCount > 0) ? ((double) aboveTarget / orderCount) * 100 : 0.0);
    }

    public int getChannelProfit(Channel c) {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkChannel(c)) {
                sum = sum + ms.getOrderProfit();
            }
        }
        return sum;
    }

    public int getChannelRevenue(Channel c) {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkChannel(c)) {
                sum = sum + ms.getOrderRevenue();
            }
        }
        return sum;
    }

    public int getChannelPerformanceRate(Channel c) {
        int aboveTarget = 0;
        int orderCount = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkChannel(c)) {
                orderCount++;
                if (ms.getAboveTargetOrderCount()) {
                    aboveTarget++;
                }
            }
        }
        return (int) ((orderCount > 0) ? ((double) aboveTarget / orderCount) * 100 : 0.0);
    }

    public int getAdProfit(Advertisement ad) {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkAd(ad)) {
                sum = sum + ms.getOrderProfit();
            }
        }
        return sum;
    }

    public int getAdRevenue(Advertisement ad) {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkAd(ad)) {
                sum = sum + ms.getOrderRevenue();
            }
        }
        return sum;
    }

    public int getAdPerformanceRate(Advertisement ad) {
        int aboveTarget = 0;
        int orderCount = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkAd(ad)) {
                orderCount++;
                if (ms.getAboveTargetOrderCount()) {
                    aboveTarget++;
                }
            }
        }
        return (int) ((orderCount > 0) ? ((double) aboveTarget / orderCount) * 100 : 0.0);
    }

    public int getBundleRevenue(SolutionOffer so) {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            sum = sum + ms.getSolutionOfferRevenue(so);
        }
        return sum;
    }
 
    public int getBundleProfit(SolutionOffer so) {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            sum = sum + ms.getSolutionOfferProfit(so);
        }
        return sum;
    }

    public int getBundlePerformanceRate(SolutionOffer so) {
        int aboveTarget = 0;
        int orderCount = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkBundle(so)) {
                orderCount++;
                if (ms.getAboveTargetOrderCount()) {
                    aboveTarget++;
                }
            }
        }
        return (int) ((orderCount > 0) ? ((double) aboveTarget / orderCount) * 100 : 0.0);
    } 

    public int getBundleTotalRevenue() {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkIfSolutionOffer()) {
                sum = sum + ms.getOrderRevenue();
            }
        }
        return sum;
    }

    public int getBundleTotalProfit() {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkIfSolutionOffer()) {
                sum = sum + ms.getOrderProfit();
            }
        }
        return sum;
    }    

    public int getBundleTotalPerformanceRate() {
        int aboveTarget = 0;
        int orderCount = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (ms.checkIfSolutionOffer()) {
            orderCount++;
                if (ms.getAboveTargetOrderCount()) {
                    aboveTarget++;
                }
            }
        }
        return (int) ((orderCount > 0) ? ((double) aboveTarget / orderCount) * 100 : 0.0);
    }    

    public int getNonBundleTotalRevenue() {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (!ms.checkIfSolutionOffer()) {
                sum = sum + ms.getOrderRevenue();
            }
        }
        return sum;
    }
    
    public int getNonBundleTotalProfit() {
        int sum = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (!ms.checkIfSolutionOffer()) {
                sum = sum + ms.getOrderProfit();
            }
        }
        return sum;
    }    

    public int getNonBundleTotalPerformanceRate() {
        int aboveTarget = 0;
        int orderCount = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            if (!ms.checkIfSolutionOffer()) {
            orderCount++;
                if (ms.getAboveTargetOrderCount()) {
                    aboveTarget++;
                }
            }
        }
        return (int) ((orderCount > 0) ? ((double) aboveTarget / orderCount) * 100 : 0.0);
    }    

    public MasterOrderSummary getTopProfitableOrder() { // most profitable order
        MasterOrderSummary currentTopOrder = null;

        for (MasterOrderSummary os : masterOrderSummarys) {
            if (currentTopOrder == null)
                currentTopOrder = os; // initial step
            else if (os.getOrderProfit() > currentTopOrder.getOrderProfit()) {
                currentTopOrder = os; // we have a new higher total
            }
        }
        return currentTopOrder;
    }    

    public int getTotalPerformanceRate() {
        int aboveTarget = 0;
        int orderCount = 0;
        for (MasterOrderSummary ms : masterOrderSummarys) {
            orderCount++;
            if (ms.getAboveTargetOrderCount()) {
                aboveTarget++;
            }
        }
        return (int) ((orderCount > 0) ? ((double) aboveTarget / orderCount) * 100 : 0.0);
    }


    public void printAllOrderSummary() {
        System.out.println("==================================================================== Order Summary List==========================================================");
        System.out.println(String.format("%10s | %10s | %10s | %10s | %5s | %10s | %5s | %30s | %30s", "B/S", "Customer", "O AP", "SOI AP", "SOI Q", "OI AP", "OI Q", "Product", "Supplier"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        for (MasterOrderSummary os : masterOrderSummarys) {
            os.printOrderSummary();
        }
    }

    public void printOrderAnalysisReport(MarketCatalog mcl, ChannelCatalog ccl, AdvertisementCatalog adc, SolutionOfferCatalog soc) {
        System.out.println("============================== Sales Performance Report ====================================");
        System.out.println("* Revenue : total sales");
        System.out.println("* Performance Rate : # of Above Target Order / # of Total Order");
        System.out.println("* Profit : sum of (acutal price - target price)");
        System.out.println("* Net Profit : Profit - Marketing Budget");
        System.out.println();
        
        System.out.println("[Sales Performance Report by Channel]");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%25s | %10s | %20s | %10s | %15s", "Channel Name", "Revenue($)", "Performance Rate(%)", "Profit($)", "Net Profit($)"));
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Channel c : ccl.getChannels()) {
            System.out.println(String.format("%25s | %10d | %20d | %10d | %15s", c.getName(), getChannelRevenue(c), getChannelPerformanceRate(c), getChannelProfit(c), getChannelProfit(c) - adc.getChannelMarketingBudget(c)));
        }
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%25s | %10d | %20d | %10d | %15d", "Total", getTotalRevenue(), getTotalPerformanceRate(), getTotalProfit(), getTotalProfit() - adc.getTotalMarketingBudget()));
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("[Sales Performance Report by Market]");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%25s | %10s | %20s | %10s | %15s", "Market Name", "Revenue($)", "Performance Rate(%)", "Profit($)", "Net Profit($)"));
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Market m : mcl.getAllSubmarkets()) {
            System.out.println(String.format("%25s | %10d | %20d | %10d | %15s", m.getFirstCharacteristic(), getMarketRevenue(m), getMarketPerformanceRate(m), getMarketProfit(m), getMarketProfit(m) - adc.getMarketMarketingBudget(m)));
        }
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("[Sales Performance Report by Advertisement]");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%25s | %10s | %20s | %10s | %15s", "Advertisement Name", "Revenue($)", "Performance Rate(%)", "Profit($)", "Net Profit($)"));
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Advertisement ad : adc.getAdvertisements()) {
            System.out.println(String.format("%25s | %10d | %20d | %10d | %15s", ad.getName(), getAdRevenue(ad), getAdPerformanceRate(ad), getAdProfit(ad), getAdProfit(ad) - ad.getBudget()));
        }
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("[Solution Offer Sales Performance Report by Bundle]");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%25s | %10s | %20s | %10s | %15s", "Bundle Name", "Revenue($)", "Performance Rate(%)", "Profit($)", "Net Profit($)"));
        System.out.println("--------------------------------------------------------------------------------------------");
        int totalBundleMarketingBudget = 0;
        ArrayList<String> tempSoName= new ArrayList();
        for (SolutionOffer so : soc.getSolutionOffers()) {
            String soName = "BD" + " " + so.getChannel().getName() + " " + so.getMarket().getFirstCharacteristic() + " " + String.format("%02d", 1);
            while (tempSoName.contains(soName)) {
                int index = Integer.parseInt(soName.substring(soName.length() - 2)) + 1;
                soName = "BD" + " " + so.getChannel().getName() + " " + so.getMarket().getFirstCharacteristic() + " " +
                        String.format("%02d", index);
            }
            tempSoName.add(soName);

            System.out.println(String.format("%25s | %10d | %20d | %10d | %15s", soName, getBundleRevenue(so), getBundlePerformanceRate(so), getBundleProfit(so), getBundleProfit(so) - adc.getMcaMarketingBudget(so.getMarketChannelAssignment())));
            totalBundleMarketingBudget += adc.getMcaMarketingBudget(so.getMarketChannelAssignment());
        }
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%25s | %10d | %20d | %10d | %15d", "Solution Offer Total", getBundleTotalRevenue(), getBundleTotalPerformanceRate(), getBundleTotalProfit(), getBundleTotalProfit() -totalBundleMarketingBudget));
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println();


        System.out.println("[Non-Solution Offer Sales Performance Report]");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%25s | %10s | %20s | %10s | %15s", "Non-Solution Offer", "Revenue($)", "Performance Rate(%)", "Profit($)", "Net Profit($)"));
        System.out.println("--------------------------------------------------------------------------------------------");
        int totalNonBundleMarketingBudget = adc.getTotalMarketingBudget();
        for (SolutionOffer so : soc.getSolutionOffers()) {    
            totalNonBundleMarketingBudget -= adc.getMcaMarketingBudget(so.getMarketChannelAssignment());
        }
        System.out.println(String.format("%25s | %10d | %20d | %10d | %15d", "Non-Solution Offer Total", getNonBundleTotalRevenue(), getNonBundleTotalPerformanceRate(), getNonBundleTotalProfit(), getNonBundleTotalProfit() -totalNonBundleMarketingBudget));
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println();
/*
        
        System.out.println("[Sales Performance Report by Bundle]");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%25s | %10s | %20s | %10s | %15s", "Category", "Revenue($)", "Performance Rate(%)", "Profit($)", "Net Profit($)"));
        System.out.println("--------------------------------------------------------------------------------------------");
        int totalBundleMarketingBudget = 0;
        ArrayList<String> tempSoName= new ArrayList();
        for (SolutionOffer so : soc.getSolutionOffers()) {
            String soName = "BD" + so.getChannel().getName().substring(0,2).toUpperCase() + so.getMarket().getFirstCharacteristic().substring(0,2).toUpperCase() + String.format("%03d", 1);
            while (tempSoName.contains(soName)) {
                int index = Integer.parseInt(soName.substring(soName.length() - 3)) + 1;
                soName = "BD" + so.getChannel().getName().substring(0, 2).toUpperCase() +
                        so.getMarket().getFirstCharacteristic().substring(0, 2).toUpperCase() +
                        String.format("%03d", index);
            }
            tempSoName.add(soName);

            if (soc.getSolutionOffers().indexOf(so) == 0) {
                System.out.println(String.format("%10s | %12s | %10d | %20d | %10d | %15s", "Bundle", soName, getBundleRevenue(so), getBundlePerformanceRate(so), getBundleProfit(so), getBundleProfit(so) - adc.getMcaMarketingBudget(so.getMarketChannelAssignment())));                
            }
            System.out.println(String.format("%10s | %12s | %10d | %20d | %10d | %15s", " ", soName, getBundleRevenue(so), getBundlePerformanceRate(so), getBundleProfit(so), getBundleProfit(so) - adc.getMcaMarketingBudget(so.getMarketChannelAssignment())));                
            
            totalBundleMarketingBudget += adc.getMcaMarketingBudget(so.getMarketChannelAssignment());
        }
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%25s | %10d | %20d | %10d | %15d", "Bundle Total", getBundleTotalRevenue(), getBundleTotalPerformanceRate(), getBundleTotalProfit(), getBundleTotalProfit() -totalBundleMarketingBudget));
        System.out.println("--------------------------------------------------------------------------------------------");

        int totalNonBundleMarketingBudget = adc.getTotalMarketingBudget();
        for (SolutionOffer so : soc.getSolutionOffers()) {    
            totalNonBundleMarketingBudget -= adc.getMcaMarketingBudget(so.getMarketChannelAssignment());
        }
        System.out.println(String.format("%25s | %10d | %20d | %10d | %15d", "Non-Bundle Total", getNonBundleTotalRevenue(), getNonBundleTotalPerformanceRate(), getNonBundleTotalProfit(), getNonBundleTotalProfit() -totalNonBundleMarketingBudget));
        System.out.println("--------------------------------------------------------------------------------------------");
    } */
    }
}
