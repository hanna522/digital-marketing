package model.OrderManagement;

import java.util.Comparator;

public class OrderSummaryComparator implements Comparator<MasterOrderSummary> {

    @Override
    public int compare(MasterOrderSummary o1, MasterOrderSummary o2) {
        
        if (Integer.compare(o2.numberOfItems, o1.numberOfItems) != 0) return Integer.compare(o2.numberOfItems, o1.numberOfItems);

        return Integer.compare(o2.salesvolume, o1.salesvolume);
    }
    
}
