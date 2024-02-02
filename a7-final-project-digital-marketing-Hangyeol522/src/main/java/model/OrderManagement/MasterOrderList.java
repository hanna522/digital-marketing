package model.OrderManagement;

public class MasterOrderList {
    GeneralOrderList generalOrderList;
    SolutionOrderList solutionOrderList;
    MasterOrderReport masterOrderReport;

    public MasterOrderList(GeneralOrderList gol, SolutionOrderList sol) {
        generalOrderList = gol;
        solutionOrderList = sol;
    }

    public MasterOrderReport generateMasterOrderReport() {
        masterOrderReport = new MasterOrderReport();
        masterOrderReport.generateOrderReport(generalOrderList, solutionOrderList);

        return masterOrderReport;
    } 
}
