/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;
import java.util.Random;

import com.github.javafaker.Faker;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.CustomerType;
import model.CustomerManagement.CustomerTypeCatalog;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.MarketingManagement.AdvertisementCatalog;
import model.OrderManagement.GeneralOrderList;
import model.OrderManagement.SolutionOrderList;
import model.OrderManagement.GeneralOrder;
import model.OrderManagement.SolutionOrder;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.Personnel.Profile;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class ConfigureABusiness {

  static int upperPriceLimit = 50;
  static int lowerPriceLimit = 10;
  static int range = 5;
  static int productMaxQuantity = 5;

  public static Business createABusinessAndLoadALotOfData(String name, int supplierCount, int productCount,
      int customerCount, int orderCount, int itemCount, int solutionOrderCount, int solutionOrderItemCount) {
    
    Business business = new Business(name);
    
    loadCustomerTypeCatalog(business);

    loadMarkets(business);

    loadChannels(business);

    loadSuppliers(business, supplierCount);

    loadProducts(business, productCount);

    loadCustomers(business, customerCount);

    loadAdvertisment(business);

    loadSolutionOffers(business);

    loadGeneralOrders(business, orderCount, itemCount);

    loadSolutionOrders(business, solutionOrderCount, solutionOrderItemCount);

    loadAdminProfile(business);

    return business;
  }

  static void loadCustomerTypeCatalog(Business b) {
    CustomerTypeCatalog customerTypeCatalog = b.getCustomertypecatalog();
    customerTypeCatalog.addNewCustomerType("General");
    customerTypeCatalog.addNewCustomerType("Student");
    customerTypeCatalog.addNewCustomerType("Military");
    customerTypeCatalog.addNewCustomerType("Employee");
  }
  
  static void loadMarkets(Business b) {
    MarketCatalog marketCatalog = b.getMarketCatalog();

    Market region = new Market("State");
    Market regionsub1 = new Market("California");
    Market regionsub2 = new Market("Georgia");
    Market regionsub3 = new Market("Arizona");
    region.addSubmarket(regionsub1);
    region.addSubmarket(regionsub2);
    region.addSubmarket(regionsub3);

    Market gender = new Market("Gender");
    Market gendersub1 = new Market("Female");
    Market gendersub2 = new Market("Male");
    gender.addSubmarket(gendersub1);
    gender.addSubmarket(gendersub2);

    Market age = new Market("Age");
    Market agesub1 = new Market("18-24");
    Market agesub2 = new Market("25-34");
    Market agesub3 = new Market("35-44");
    Market agesub4 = new Market("45-54");
    Market agesub5 = new Market("64-");
    age.addSubmarket(agesub1);
    age.addSubmarket(agesub2);
    age.addSubmarket(agesub3);
    age.addSubmarket(agesub4);
    age.addSubmarket(agesub5);

    marketCatalog.addMarket(region);
    marketCatalog.addMarket(gender);
    marketCatalog.addMarket(age);
  }

  static void loadChannels(Business b) {
    ChannelCatalog channelCatalog = b.getChannelcatalog();

    Channel ch1 = new Channel("Homepage");
    Channel ch2 = new Channel("Facebook");
    Channel ch3 = new Channel("Instagram");
    Channel ch4 = new Channel("Store");
    Channel ch5 = new Channel("Direct");

    channelCatalog.addChannel(ch1);
    channelCatalog.addChannel(ch2);
    channelCatalog.addChannel(ch3);
    channelCatalog.addChannel(ch4);
    channelCatalog.addChannel(ch5);
  }

  static void loadAdvertisment(Business b) {
    AdvertisementCatalog advertismentCatalog = b.getAdvertisementCatalog();

    int advertisementBudgetTotal = 100;
    int advertisementNum = 10;

    for (int i = 0; i < advertisementNum; i++) {
      MarketChannelAssignment randomMca = new MarketChannelAssignment(b.getMarketCatalog().pickRandomSubMarket(), b.getChannelcatalog().pickRandomChannel());
      String randomName = "AD " + randomMca.getChannel().getName() + " " + randomMca.getMarket().getFirstCharacteristic();
      String randomConent = "(Ad) Find Best Product for " + randomMca.getMarket().getFirstCharacteristic();
      advertismentCatalog.addNewAdvertisement(randomMca, advertisementBudgetTotal / advertisementNum, randomName, randomConent);
    }
  }

  static void loadSolutionOffers(Business b) {
    SolutionOfferCatalog solutionOfferCatalog = b.getSolutionoffercatalog();

    Random random = new Random();

    for (int i = 0; i < 15; i++){
      MarketChannelAssignment randomMca = new MarketChannelAssignment(b.getMarketCatalog().pickRandomSubMarket(), b.getChannelcatalog().pickRandomChannel());
      SolutionOffer newSo = new SolutionOffer(randomMca);

      int randomBundleNum = random.nextInt(2,4);
      
      for (int j = 0; j < randomBundleNum; j++) {
        //should select from certain category later
        Product newProduct = b.getSupplierDirectory().pickRandomSupplier().getProductCatalog().pickRandomProduct();

        newSo.addProduct(newProduct);
      }
      
      int discount = getRandom(1, 3);
      int soPrice = newSo.getTargetPrice() - discount;

      newSo.setPrice((int) soPrice);

      solutionOfferCatalog.addSolutionOffer(newSo);
    }
    
  }

  static void loadSuppliers(Business b, int supplierCount) {
    Faker faker = new Faker();

    SupplierDirectory supplierDirectory = b.getSupplierDirectory();
    for (int index = 1; index <= supplierCount; index++) {
      supplierDirectory.newSupplier(faker.company().name());
    }
  }

  static void loadProducts(Business b, int productCount) {
    Faker faker = new Faker();

    SupplierDirectory supplierDirectory = b.getSupplierDirectory();

    for (Supplier supplier : supplierDirectory.getSupplierList()) {

      int randomProductNumber = getRandom(1, productCount);
      ProductCatalog productCatalog = supplier.getProductCatalog();

      for (int index = 1; index <= randomProductNumber; index++) {

        String productName = faker.commerce().productName();
        int randomFloor = getRandom(lowerPriceLimit, lowerPriceLimit + range);
        int randomCeiling = getRandom(upperPriceLimit - range, upperPriceLimit);
        int randomTarget = getRandom(randomFloor, randomCeiling);

        productCatalog.newProduct(productName, randomFloor, randomCeiling, randomTarget);
      }
    }
  }

  static int getRandom(int lower, int upper) {
    Random r = new Random();

    // nextInt(n) will return a number from zero to 'n'. Therefore e.g. if I want
    // numbers from 10 to 15
    // I will have result = 10 + nextInt(5)
    int randomInt = lower + r.nextInt(upper - lower);
    return randomInt;
  }

  static void loadCustomers(Business b, int customerCount) {
    CustomerDirectory customerDirectory = b.getCustomerDirectory();
    PersonDirectory personDirectory = b.getPersonDirectory();
    UserAccountDirectory userAccountDirectory = b.getUserAccountDirectory();
    MarketCatalog marketCatalog = b.getMarketCatalog();
    CustomerTypeCatalog ctc = b.getCustomertypecatalog();
    
    for (int index = 1; index <= customerCount; index++) {

      String newCustomerId = String.format("CU%04d", index);
      String newCustomerPw = "password1234";

      Person newPerson = personDirectory.newPerson(newCustomerId);
      CustomerType randomCustomerType = ctc.getRandomCustomerType();
      ArrayList<Market> randomMarkets = marketCatalog.pickRandomSubMarkets();

      CustomerProfile newCustomer = customerDirectory.newCustomerProfile(newPerson, randomCustomerType);
      newCustomer.addMarkets(randomMarkets);
      
      Profile newCustomerProfile = new Profile(newPerson, "Customer");
      userAccountDirectory.newUserAccount(newCustomerProfile, newCustomerId, newCustomerPw);
    }
  }

  static void loadGeneralOrders(Business b, int orderCount, int itemCount) {

    GeneralOrderList mol = b.getGeneralOrderList();
    CustomerDirectory cd = b.getCustomerDirectory();
    SupplierDirectory sd = b.getSupplierDirectory();

    for (int index = 0; index < orderCount; index++) {

      CustomerProfile randomCustomer = cd.pickRandomCustomer();
      if (randomCustomer == null) {
        System.out.println("Cannot generate orders. No customers in the customer directory.");
        return;
      }
      Channel randomChannel = b.getChannelcatalog().pickRandomChannel();
      GeneralOrder randomOrder = mol.newOrder(randomCustomer, randomChannel);

      int randomItemCount = getRandom(1, itemCount);
      for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {

        Supplier randomSupplier = sd.pickRandomSupplier();
        if (randomSupplier == null) {
          System.out.println("Cannot generate orders. No supplier in the supplier directory.");
          return;
        }
        ProductCatalog pc = randomSupplier.getProductCatalog();
        Product randomProduct = pc.pickRandomProduct();
        if (randomProduct == null) {
          System.out.println("Cannot generate orders. No products in the product catalog.");
          return;
        }

        int randomPrice = getRandom(randomProduct.getTargetPrice() - range, randomProduct.getCeilingPrice());
        int randomQuantity = getRandom(1, productMaxQuantity);

        randomOrder.newOrderItem(randomProduct, randomPrice, randomQuantity);
      }
    }
  }

  static void loadSolutionOrders(Business b, int solutionOrderCount, int solutionOrderitemCount) {
    SolutionOrderList msol = b.getSolutionOrderList();
    CustomerDirectory cd = b.getCustomerDirectory();
    SolutionOfferCatalog solutionOfferCatalog = b.getSolutionoffercatalog();

    for (int index = 0; index < solutionOrderCount; index++) {

      CustomerProfile randomCustomer = cd.pickRandomCustomer();
      if (randomCustomer == null) {
        System.out.println("Cannot generate solution orders. No customers in the customer directory.");
        return;
      }
      Channel randomChannel = b.getChannelcatalog().pickRandomChannel();
      SolutionOrder randomSolutionOrder = msol.newOrder(randomCustomer, randomChannel);

      int randomItemCount = getRandom(1, solutionOrderitemCount);
      for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {

        SolutionOffer randomSolutionOffer = solutionOfferCatalog.pickRandomSolutionOffer();
        if (randomSolutionOffer == null) {
          System.out.println("Cannot generate solution orders. No solution offers in the solution offer catalog.");
          return;
        }

        int randomQuantity = getRandom(1, productMaxQuantity);
        int randomPrice = getRandom(randomSolutionOffer.getTargetPrice() - 2, randomSolutionOffer.getCeilingPrice());
        randomSolutionOrder.newSolutionOrderItem(randomSolutionOffer, randomPrice, randomQuantity);
      }
    }
    
  }

  static void loadAdminProfile(Business b) {
    UserAccountDirectory userAccountDirectory = b.getUserAccountDirectory();

    String adminId = "AM0000";
    String adminPw = "password1234";

    Person newAdminPerson = new Person(adminId);

    Profile newAdminProfile = new Profile(newAdminPerson, "Admin");
    userAccountDirectory.newUserAccount(newAdminProfile, adminId, adminPw);
  }

}
