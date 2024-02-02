package model.OrderManagement;

import java.util.ArrayList;

import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.MarketingManagement.Advertisement;
import model.ProductManagement.SolutionOffer;

public class MasterOrderSummary {
    GeneralOrder generalOrder;
    SolutionOrder solutionOrder;
    String orderType;
    int salesvolume;
    int numberOfItems;
    boolean totalabovetarget;
    int orderpriceperformance;
    int numberofOrderitemsabovetarget;
    String customerId;
    int ordertotal;
    int numberOfSolutionOffers;
    
    public MasterOrderSummary(GeneralOrder o){
        generalOrder = o;
        solutionOrder = null;
        salesvolume = o.getOrderTotal();
        numberOfItems = o.getNumberOfItems();
        totalabovetarget = o.isOrderAboveTotalTarget();
        orderpriceperformance = o.getOrderPricePerformance();
        numberofOrderitemsabovetarget = o.getNumberOfOrderItemsAboveTarget();
        customerId = o.getCustomerId();
        ordertotal = o.getOrderTotal();
        numberOfSolutionOffers = 0;
    }

    public MasterOrderSummary(SolutionOrder so){
        generalOrder = null;
        solutionOrder = so;
        salesvolume = so.getOrderTotal();
        numberOfItems = so.getNumberOfItems(); // the number of Products (orderitem)
        totalabovetarget = so.isOrderAboveTotalTarget();
        orderpriceperformance = so.getOrderPricePerformance();
        numberofOrderitemsabovetarget = so.getNumberOfOrderItemsAboveTarget();
        customerId = so.getCustomerId();
        ordertotal = so.getOrderTotal();
        numberOfSolutionOffers = so.getNumberOfSolutionOrderItems(); // the number of Bundle (Solution Offers)
    }

    public int getOrderProfit(){
        return orderpriceperformance;
    }

    public int getOrderRevenue() {
        return ordertotal;
    }

    public boolean getAboveTargetOrderCount() {
        return totalabovetarget;
    }

    public boolean checkMarket(Market m) {
        ArrayList<Market> checkMarket = new ArrayList<Market>();
        if (solutionOrder == null) {
            checkMarket = generalOrder.getCustomer().getMarkets();
            if (checkMarket.contains(m)) {
                return true;
            }
        } 
        if (generalOrder == null) {
            checkMarket = solutionOrder.getCustomer().getMarkets();
            if (checkMarket.contains(m)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkChannel(Channel c) {
        Channel checkChannel = null;
        if (solutionOrder == null) {
            checkChannel = generalOrder.getChannel();
            if (checkChannel == c) {
                return true;
            }
        }
        if (generalOrder == null) {
            checkChannel = solutionOrder.getChannel();
            if (checkChannel == c) {
                return true;
            }            
        }
        return false;
    }

    public boolean checkAd(Advertisement ad) { // check the order's mca has advertisment
        ArrayList<Market> checkMarket = new ArrayList<Market>();
        Channel checkChannel = null;
        if (solutionOrder == null) {
            checkMarket = generalOrder.getCustomer().getMarkets();
            checkChannel = generalOrder.getChannel();
            if (checkMarket.contains(ad.getSelectedMarketChannelAssignment().getMarket()) && checkChannel == ad.getSelectedMarketChannelAssignment().getChannel()) {
                return true;
            }
        }
        if (generalOrder == null) {
            checkMarket = solutionOrder.getCustomer().getMarkets();
            checkChannel = solutionOrder.getChannel();
            if (checkMarket.contains(ad.getSelectedMarketChannelAssignment().getMarket()) && checkChannel == ad.getSelectedMarketChannelAssignment().getChannel()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBundle(SolutionOffer so) {
        if (generalOrder == null) {
            for (SolutionOrderItem soi : solutionOrder.getSolutionOrderItems()) {
                if(soi.getSelectedSolutionOffer() == so) {
                    return true;
                }
            } 
        }
        return false;
    }

    public SolutionOrder getSolutionOrder() {
        return solutionOrder;
    }

    public GeneralOrder getGeneralOrder() {
        return generalOrder;
    }

    public int getSolutionOfferRevenue(SolutionOffer so) {
        int sum = 0;
        if (generalOrder == null) {
            for (SolutionOrderItem soi : solutionOrder.getSolutionOrderItems()) {
                if (soi.getSelectedSolutionOffer() == so) {
                    sum += soi.getActualPrice() * soi.getQuantity();
                }
            }
        }
        return sum;
    }

    public int getSolutionOfferProfit(SolutionOffer so) {
        int sum = 0;
        if (generalOrder == null) {
            for (SolutionOrderItem soi : solutionOrder.getSolutionOrderItems()) {
                if (soi.getSelectedSolutionOffer() == so) {
                    sum += soi.getPricePerformance();
                }
            }
        }
        return sum;
    }

    public boolean checkIfSolutionOffer() {
        return (generalOrder == null);
    }

    public int getAdMarketingBudget(Advertisement ad) {
        return ad.getBudget();
    }


    public void printOrderSummary(){
        if (generalOrder == null) {
            System.out.println(String.format("%10s | %10s | %10s | %10s | %5s | %10s | %5s | %30s | %30s", "Bundle", customerId, salesvolume, " ", " ", " ", " ", " ", " "));
            for (SolutionOrderItem soi : solutionOrder.getSolutionOrderItems() ) {
                System.out.println(String.format("%10s | %10s | %10s | %10s | %5s | %10s | %5s | %30s | %30s", " ", " ", " ", soi.getActualPrice(), soi.getQuantity(), " ", " ", " ", " "));
                for (OrderItem oi : soi.getOrderItems()) {
                    System.out.println(String.format("%10s | %10s | %10s | %10s | %5s | %10s | %5s | %30s | %30s", " ", " ", " ", " ", " ", oi.getActualPrice(), oi.getQuantity(), oi.getSelectedProduct().toString(), "Supplier?"));
                }
//            System.out.println("           |            |            |-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -");
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        if (solutionOrder == null) {
            System.out.println(String.format("%10s | %10s | %10s | %10s | %5s | %10s | %5s | %30s | %30s", "Single", customerId, salesvolume, " ", " ", " ", " ", " ", " "));
            for (OrderItem oi : generalOrder.getorderitems()) {
                    System.out.println(String.format("%10s | %10s | %10s | %10s | %5s | %10s | %5s | %30s | %30s", " ", " ", " ", " ", " ", oi.getActualPrice(), oi.getQuantity(), oi.getSelectedProduct().toString(), "Supplier?"));
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

}
