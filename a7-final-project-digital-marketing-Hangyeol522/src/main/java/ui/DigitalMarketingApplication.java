/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.CustomerTypeCatalog;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketingManagement.Advertisement;
import model.MarketingManagement.AdvertisementCatalog;
import model.OrderManagement.GeneralOrderList;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.MasterOrderReport;
import model.OrderManagement.SolutionOrderList;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccount;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class DigitalMarketingApplication {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here

    // 1. Populate the model +

    Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("KMart", 50, 10, 30, 50, 10, 100, 3);

    SupplierDirectory sd = business.getSupplierDirectory();
    CustomerDirectory cd = business.getCustomerDirectory();
    UserAccountDirectory uad = business.getUserAccountDirectory();
    SolutionOfferCatalog soc = business.getSolutionoffercatalog();
    ChannelCatalog ccl = business.getChannelcatalog();
    MasterOrderList mol = business.getMasterOrderList();
    MasterOrderReport mor = mol.generateMasterOrderReport();
    MarketCatalog mcl = business.getMarketCatalog();
    AdvertisementCatalog adc = business.getAdvertisementCatalog();
    CustomerTypeCatalog ctc = business.getCustomertypecatalog();

    Channel accessChannel = ccl.pickRandomChannel(); // set random current access channel

    boolean exitCode = false;
    Scanner sc = new Scanner(new BufferedInputStream(System.in));
    Stack<String> menuStack = new Stack<>();
    menuStack.push("FirstPage");

    while (!exitCode) {
          
      String currentMenu = menuStack.peek();

        System.out.println("Welcome to " + business.getName() + ".");
        System.out.println("1. Log-in");
        System.out.println("2. Admin");
        System.out.println("3. Exit");

        String input1 = sc.next();

        if (input1.equals("1")) {

          menuStack.push("CustomerLogin");
          currentMenu = menuStack.peek();
          
          System.out.println("Enter your ID:");

          String inputCustomerid = sc.next();

            if (!uad.checkUserAccount(inputCustomerid)) { // when ID is wrong
              System.out.println("ID does not exist. Please check ID again");
              menuStack.pop(); // go back to previous menu
            }
            
            if (uad.checkUserAccount(inputCustomerid)) {
            System.out.println("Enter Password:");

            String inputCustomerpw = sc.next();

            if (uad.AuthenticateUser(inputCustomerid, inputCustomerpw) == null) { // when PW is wrong
              System.out.println("Password incorrect. Please check password again");
              menuStack.pop(); // go back to previous menu
            }

            if (uad.AuthenticateUser(inputCustomerid, inputCustomerpw) != null) {
              
              menuStack.push("CustomerPage");
              currentMenu = menuStack.peek();

              UserAccount ua = uad.AuthenticateUser(inputCustomerid, inputCustomerpw);
              CustomerProfile loggedinCustomer = cd.findCustomer(ua.getPersonId());

              Advertisement customizedAd = adc.getCustomizedAd(loggedinCustomer, accessChannel);
              if (customizedAd != null) {
                System.out.println(customizedAd.getContent()); // show customized add
              }

              while (currentMenu.equals("CustomerPage")) {
                System.out.println("1. Go Shopping");
                System.out.println("2. View My Profile");
                System.out.println("3. Log-out");

                String inputCustomer1 = sc.next();

                if (inputCustomer1.equals("1")) {

                  menuStack.push("ShoppingPage");
                  currentMenu = menuStack.peek();

                  while (currentMenu.equals("ShoppingPage")) {
                    if (cd.getCustomerType(loggedinCustomer).toString() == "General") {
                      System.out.println("Enjoy a variety of products.");

                      ArrayList<SolutionOffer> customizedSolutionOfferList = soc.getCustomizedSolutionOffers(loggedinCustomer, accessChannel);
                      System.out.println("");
                      // show customized bundles for the customer
                      System.out.println("========================= All Bundles =====================");      
                      System.out.println(String.format("%3s | %16s | %34s", "#", "price", "Included Products"));
                      System.out.println("-----------------------------------------------------------");
                      for (SolutionOffer so : customizedSolutionOfferList) {
                        System.out.print(String.format("%3d", customizedSolutionOfferList.indexOf(so) + 1));
                        so.printShortInfo();
                      }
                      System.out.println("");
                      // show single products for the customer
                      sd.printEveryProduct(); // show no discount price for general customer
                    }

                    else if (cd.getCustomerType(loggedinCustomer).toString() == "Student") {
                      System.out.println("Special benefits await your academic journey. Select an option ");

                      ArrayList<SolutionOffer> customizedSolutionOfferList = soc.getCustomizedSolutionOffers(loggedinCustomer, accessChannel);
                      System.out.println("");
                      // show customized bundles for the customer
                      System.out.println("========================= All Bundles =====================");      
                      System.out.println(String.format("%3s | %16s | %34s", "#", "price", "Included Products"));
                      System.out.println("-----------------------------------------------------------");
                      for (SolutionOffer so : customizedSolutionOfferList) {
                        System.out.print(String.format("%3d", customizedSolutionOfferList.indexOf(so) + 1));
                        so.printShortInfo();
                      }
                      System.out.println("");
                      // show single products for the customer
                      sd.printEveryProductWithDiscount(0.7); // show 30% discount price for student
                    }

                    else if (cd.getCustomerType(loggedinCustomer).toString() == "Military") {
                      System.out.println("Special benefits to express our gratitude. Please select a product:");

                      ArrayList<SolutionOffer> customizedSolutionOfferList = soc.getCustomizedSolutionOffers(loggedinCustomer, accessChannel);
                      System.out.println("");
                      // show customized bundles for the customer
                      System.out.println("========================= All Bundles =====================");      
                      System.out.println(String.format("%3s | %16s | %34s", "#", "price", "Included Products"));
                      System.out.println("-----------------------------------------------------------");
                      for (SolutionOffer so : customizedSolutionOfferList) {
                        System.out.print(String.format("%3d", customizedSolutionOfferList.indexOf(so) + 1));
                        so.printShortInfo();
                      }
                      System.out.println("");
                      // show single products for the customer
                      sd.printEveryProductWithDiscount(0.8); // show 20% discount price for military
                    }

                    else if (cd.getCustomerType(loggedinCustomer).toString() == "Employee") {
                      System.out.println("Exclusive benefits are here for employees. Please select a product:");

                      ArrayList<SolutionOffer> customizedSolutionOfferList = soc.getCustomizedSolutionOffers(loggedinCustomer, accessChannel);
                      System.out.println("");
                      // show customized bundels for the customer
                      System.out.println("========================= All Bundles =====================");      
                      System.out.println(String.format("%3s | %16s | %34s", "#", "price", "Included Products"));
                      System.out.println("-----------------------------------------------------------");
                      for (SolutionOffer so : customizedSolutionOfferList) {
                        System.out.print(String.format("%3d", customizedSolutionOfferList.indexOf(so) + 1));
                        so.printShortInfo();
                      }
                      System.out.println("");
                      // show single products for the customer
                      sd.printEveryProductWithDiscount(0.9); // show 10% discount price for employee
                    }

                    System.out.println("0. Go back to menu");
                    String inputBack = sc.next();

                    if (inputBack.equals("0")) {
                      menuStack.pop();
                      currentMenu = menuStack.peek();
                    }
                  }
                }

                if (inputCustomer1.equals("2")) { // view Customer Profile
                  menuStack.push("CustomerProfilePage");
                  currentMenu = menuStack.peek();
                  
                  while (currentMenu.equals("CustomerProfilePage")) {
                    loggedinCustomer.printShortInfo();
                    
                    System.out.println("0. Go back to menu");
                    String inputBack = sc.next();

                    if (inputBack.equals("0")) {
                      menuStack.pop();
                      currentMenu = menuStack.peek();
                    }
                  }

                }

                if (inputCustomer1.equals("3")) { // Go back to FirstPage
                  menuStack.pop();
                  menuStack.pop();
                  currentMenu = menuStack.peek();
                }
              }
            }
          }
        }

        if (input1.equals("2")) {

          menuStack.push("AdminLogin"); // Admin Id login (Admin ID : AM0000 / pw : password1234)
          currentMenu = menuStack.peek();

          System.out.println("Enter admin ID:");

          String inputAdminId = sc.next();

          if (uad.findUserAccount(inputAdminId) == null) {
            System.out.println("Admin ID incorrect. Please check ID again.");
            menuStack.pop();
          } else if (uad.findUserAccount(inputAdminId).getRole().equals("Admin")) {
            System.out.println("Enter admin password:");
            
            String inputAdminPw = sc.next();

            if (uad.AuthenticateUser(inputAdminId, inputAdminPw) == null) {
              System.out.println("Password incorrect. Please check password again.");
              menuStack.pop();
            }

            if (uad.AuthenticateUser(inputAdminId, inputAdminPw) != null) {
              UserAccount loggedinAdmin = uad.AuthenticateUser(inputAdminId, inputAdminPw);

              menuStack.push("AdminMenu");
              currentMenu = menuStack.peek();

              while (currentMenu.equals("AdminMenu")) {
                System.out.println("Welcome to admin page. Please select an option");
                System.out.println("1. View Sales Performance Report");
                System.out.println("2. View Customer Performance Report");
                System.out.println("3. Log-out");

                String inputAdmin1 = sc.next();

                if (inputAdmin1.equals("1")) {
                  menuStack.push("SalesReport");
                  currentMenu = menuStack.peek();

                  while (currentMenu.equals("SalesReport")) {
                    mor.printOrderAnalysisReport(mcl, ccl, adc, soc); // print Sales Perforance Report

                    System.out.println("0. Go back to menu");
                    String inputBack = sc.next();
                    
                    if (inputBack.equals("0")) {
                      menuStack.pop();
                      currentMenu = menuStack.peek();
                    }
                  }
                }

                if (inputAdmin1.equals("2")) {
                  menuStack.push("CustomerReport");
                  currentMenu = menuStack.peek();

                  while (currentMenu.equals("CustomerReport")) {
                    cd.printCustomerReport(ctc, mcl); // print Sales Perforance Report

                    System.out.println("0. Go back to menu");
                    String inputBack = sc.next();
                    
                    if (inputBack.equals("0")) {
                      menuStack.pop();
                      currentMenu = menuStack.peek();
                    }
                  }
                }

                if (inputAdmin1.equals("3")) {
                  menuStack.pop();
                  menuStack.pop();
                  currentMenu = menuStack.peek();
                }
              }
            }
          } else {
            System.out.println("Admin ID incorrect. Please check ID again.");
            menuStack.pop();
          }
          
        }
        if (input1.equals("3")) {
            exitCode = true;
        }
    }
    System.out.println("Thank you, have a nice day.");
    sc.close();

  }
}
