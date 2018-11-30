
package org.com1027.coursework.q2optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * Tests the AuctionHouse class.
 * 
 * @author Stella Kazamia
 */
public class AuctionHouseTest {

  
  /**
   * Creating an Auction object and check maps created.
   * Check methods return correct values when nothing stored in maps. 
   */
  @Test
  public void testAuctionHouseCreation() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product = new BiddableProduct(1,"teddy",10.00);
    User user = new User("Stella");
    assertEquals(false,ahouse1.checkExistence(product));
    assertEquals("",ahouse1.displaySoldProducts());
    assertEquals(false,ahouse1.placeBid(product, user, 10.00));       
 }  



  /**
   * Creating an Auction object and check error is thrown
   * in case of invalid product
   */
  @Test(expected=IllegalArgumentException.class)
  public void testInvalidRegister1() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product = new BiddableProduct(1,"teddy",10.00);
    User user = new User("Stella");
    assertEquals(true,ahouse1.register(null, user));  
 } 
  /**
   * Creating an Auction object and check error is thrown
   * in case of invalid user
   */
  @Test(expected=IllegalArgumentException.class)
  public void testInvalidRegister2() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product = new BiddableProduct(1,"teddy",10.00);
    User user = new User("Stella");
    assertEquals(true,ahouse1.register(product, null));  
 }  
  
  /**
   * Creating and registering a valid Auction object and check error is thrown
   * in case of placing a bid with invalid product
   */
  @Test(expected=IllegalArgumentException.class)
  public void testInvalidBid1() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product = new BiddableProduct(1,"teddy",10.00);
    User user = new User("Stella");
    assertEquals(true,ahouse1.register(product, user));
    assertEquals(true,ahouse1.placeBid(null, user, 10.00));   
    
 }
  
  /**
   * Creating and registering a valid Auction object and check error is thrown
   * in case of placing a bid with invalid user
   */
  @Test(expected=IllegalArgumentException.class)
  public void testInvalidBid2() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product = new BiddableProduct(1,"teddy",10.00);
    User user = new User("Stella");
    assertEquals(true,ahouse1.register(product, user));
    assertEquals(true,ahouse1.placeBid(product, null, 10.00));   
    
 }   
  
 

  /**
   * Creating and registering a valid Auction object and check error is thrown
   * in case of placing a bid with an invalid bid value not greater than 0
   */
  @Test(expected=IllegalArgumentException.class)
  public void testInvalidBid3() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product = new BiddableProduct(1,"teddy",10.00);
    User user = new User("Stella");
    assertEquals(true,ahouse1.register(product, user));
    assertEquals(true,ahouse1.placeBid(product, user, 0.00));   
    
 } 
  
  /**
   * Creating an Auction object, registering and placing bids,
   * checking that a lower bid cannot replace a higher bid already made
   */
  @Test
  public void testRegisterandPlaceBid() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product = new BiddableProduct(1,"teddy",10.00);
    User user = new User("Stella");
    assertEquals(false,ahouse1.checkExistence(product));
    assertEquals(true,ahouse1.register(product, user));  
    //can't register twice
    assertEquals(false,ahouse1.register(product, user));  
    //check place bid
    assertEquals(true,ahouse1.placeBid(product, user, 10.00));   
    assertEquals(false,ahouse1.placeBid(product, user, 5.00));   
    assertEquals(true,ahouse1.placeBid(product, user, 20.00));     
 } 
  /**
   * Creating a valid auction and registering different product. 
   * Don't need to test for registering in this (already done). 
   * Check that all unsold products stored and displayed correctly.
   * Because Maps don't preserve order we need to be able to check that 
   * the output of the map is preserved in any order, see
   * https://examples.javacodegeeks.com/core-java/junit/junit-assertthat-example/
   */
  @Test
  public void testEndAuctionWithNoBidsOrPurchases() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product1 = new BiddableProduct(1,"teddy",10.00);
    BuyNowProduct product2 = new BuyNowProduct(2,"doll",20.00,5);
    User user = new User("Stella");
    ahouse1.register(product1, user);  
    ahouse1.register(product2, user);  
    ahouse1.endAuction(product1);
    ahouse1.endAuction(product2);  
    System.out.println("display unsold auction with no bids and purchases \n");
    System.out.println(ahouse1.displayUnsoldProducts());
    assertThat(ahouse1.displayUnsoldProducts(), 
       anyOf(is("1 - teddy"+"\n"+"2 - doll"+"\n"), is("2 - doll"+"\n"+"1 - teddy"+"\n")));
 } 
 
  /**
   * Creating a valid auction and registering biddable product. 
   * Don't need to test for registering and bidding in this (already done). 
   * Check that all unsold products stored and displayed correctly.
   * Scenario includes making bids that do not meet the reserve prices.
   * Because Maps don't preserve order we need to be able to check that 
   * the output of the map is preserved in any order, see
   * https://examples.javacodegeeks.com/core-java/junit/junit-assertthat-example/
   */
  @Test
  public void testEndAuctionWithBidsNotMeetingReserve() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product1 = new BiddableProduct(1,"teddy",10.00);
    BiddableProduct product2 = new BiddableProduct(2,"doll",20.00);
    User user = new User("Stella");
    ahouse1.register(product1, user);  
    ahouse1.register(product2, user); 
    ahouse1.placeBid(product1, user, 8.00);
    ahouse1.placeBid(product2, user, 18.00);
    ahouse1.endAuction(product1);
    //System.out.println("display unsold auction with no bids meeting reserve \n");
    //System.out.println(ahouse1.displayUnsoldProducts());
    ahouse1.endAuction(product2); 
    System.out.println(ahouse1.displayUnsoldProducts());
    //order of map not guaranteed so for simple two product case
    //so the assert needs to be more complex than just assertEquals
    assertThat(ahouse1.displayUnsoldProducts(), 
         anyOf(is("1 - teddy"+"\n"+"2 - doll"+"\n"), is("2 - doll"+"\n"+"1 - teddy"+"\n")));
    
 } 
  /**
   * Creating a valid auction and registering biddable product. 
   * Don't need to test for registering and bidding in this (already done). 
   * Check that all sold products stored and displayed correctly.
   * Scenario includes making bids that meet the reserve prices.
   * Because Maps don't preserve order we need to be able to check that 
   * the output of the map is preserved in any order, see
   * https://examples.javacodegeeks.com/core-java/junit/junit-assertthat-example/
   */
  @Test
  public void testEndAuctionWithBidsMeetingReserve() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct product1 = new BiddableProduct(1,"teddy",10.00);
    BiddableProduct product2 = new BiddableProduct(2,"doll",20.00);
    User user = new User("Stella");
    ahouse1.register(product1, user);  
    ahouse1.register(product2, user); 
    ahouse1.placeBid(product1, user, 12.00);
    ahouse1.placeBid(product2, user, 22.00);
    ahouse1.endAuction(product1);
    ahouse1.endAuction(product2);  
    System.out.println("display unsold auction with bids meeting reserve \n");
    System.out.println(ahouse1.displaySoldProducts());
    assertThat(ahouse1.displaySoldProducts(), 
        anyOf(is("1 - S***a bid £12.0"+"\n"+"2 - S***a bid £22.0"+"\n"), is("2 - S***a bid £22.0"+"\n"+"1 - S***a bid £12.0"+"\n")));
 } 
  

/* new tests for Q2 because additional buy now */
  
  /**
   * Creating an Auction object, registering and buying now some objects,
   * checking that a buy now can't happen if tries to buy too many of the product
   */
  @Test
  public void testRegisterandBuyNow() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BuyNowProduct product = new BuyNowProduct(1,"teddy",10.00,9);
 
    User user = new User("Stella");
    assertEquals(false,ahouse1.checkExistence(product));
    assertEquals(true,ahouse1.register(product, user)); 
    
    //can't register twice
    assertEquals(false,ahouse1.register(product, user));  
    //check place bid
    assertEquals(true,ahouse1.buyNow(product, user, 5));   
    assertEquals(true,ahouse1.buyNow(product, user, 2));   
    assertEquals(false,ahouse1.buyNow(product, user, 5));     

 }
  
  /**
   * Creating an Auction object, registering and buying now some products,
   * checking that if not all the quantities of the items sold
   * when auction finishes that product is viewed as unsold. Model is coarse
   * doesn't differentiate some of the buy not product items being sold.
   * It is considering the product as a whole and not the individual items of a product.
   */
  @Test
  public void testEndAuctionWithBuyNowLeft() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BuyNowProduct product1 = new BuyNowProduct(1,"teddy",10.00,9);
    BuyNowProduct product2 = new BuyNowProduct(2,"doll",20.00,5);
    User user = new User("Stella");
    ahouse1.register(product1, user);  
    ahouse1.register(product2, user);  
    ahouse1.buyNow(product1, user, 5);
    ahouse1.endAuction(product1);
    ahouse1.endAuction(product2);
    //System.out.println("display unsold auction with outstanding teddies \n");
    //System.out.println(ahouse1.displayUnsoldProducts());
    //order of map not guaranteed so for simple two product case
    //so the assert needs to be more complex than just assertEquals
    assertThat(ahouse1.displayUnsoldProducts(), 
         anyOf(is("1 - teddy"+"\n"+"2 - doll"+"\n"), is("2 - doll"+"\n"+"1 - teddy"+"\n")));
    
 } 
  
  /**
   * Creating an Auction object, registering and buying now some products,
   * checking that if all the quantities of a product is sold
   * when auction finishes that product goes into sold. Model is coarse
   * doesn't differentiate some of the buy not product items being sold.
   * It is considering the product as a whole and not the individual items.
   */
  @Test
  public void testEndAuctionWithBuyNowSold() {
    AuctionHouse ahouse1 = new AuctionHouse();
    BuyNowProduct product1 = new BuyNowProduct(1,"teddy",10.00,9);
    BuyNowProduct product2 = new BuyNowProduct(2,"doll",20.00,5);
    User user = new User("Stella");
    ahouse1.register(product1, user);  
    ahouse1.register(product2, user);  
    ahouse1.buyNow(product1, user, 9);
    ahouse1.buyNow(product2, user, 5);
    ahouse1.endAuction(product1);
    ahouse1.endAuction(product2);
    //System.out.println("display sold auction with no teddies and doll remaining \n");
    //System.out.println(ahouse1.displayUnsoldProducts());
    assertThat(ahouse1.displaySoldProducts(), 
        anyOf(is("1 - S***a bought 9\n"+"2 - S***a bought 5\n"), is("2 - S***a bought 5\n"+"1 - S***a bought 9\n")));
   
 } 
  
  // Students will be expected to produce one test case for this optional question
  
  /* Scenario to test 4 products and the optional display method.
   * First create a biddable object with product name of teddy for £5. 
   * Second create a biddable object with product name of truck for £10.
   * Third create a buy now object with product name of doll for £20 and quantity of 5
   * Fourth create a buy now object with product name of ball for £10 and quantity of 10
   * Create two users with names Stella and Helen
   * Register all the projects
   * Stella bids £2 for first product
   * Stella bids £10 for second product
   * Helen bids £15 for second product
   * Stella bids £20 for second product
   * Stella buys 5 of the third product
   * Stella buys 7 of the fourth product
   * The auction for all products end
   * Assert that the product with the highest value which have been sold are 
   * products identified with product ids 2 and 3
   */
  
  @Test
  public void testHighestSoldProducts() {
    // creating the needed classes
    AuctionHouse ahouse1 = new AuctionHouse();
    BiddableProduct biddableProduct1 = new BiddableProduct(1,"teddy",5.00);
    BiddableProduct biddableProduct2 = new BiddableProduct(2,"truck",10.00);
    BuyNowProduct buyNowProduct1 = new BuyNowProduct(3,"doll",20.00,5);
    BuyNowProduct buyNowProduct2 = new BuyNowProduct(4,"ball",10.00,10);
    User user1 = new User("Stella");
    User user2 = new User("Helen");
    
    // registering of the products
    ahouse1.register(biddableProduct1, user1);
    ahouse1.register(biddableProduct2, user1);  
    ahouse1.register(buyNowProduct1, user2);
    ahouse1.register(buyNowProduct2, user2);
    
    // bids and purchases
    ahouse1.placeBid(biddableProduct1, user1, 2.00);
    ahouse1.placeBid(biddableProduct2, user1, 10.00);
    ahouse1.placeBid(biddableProduct2, user2, 15.00);
    ahouse1.placeBid(biddableProduct2, user1, 20.00);
    ahouse1.buyNow(buyNowProduct1, user1, 5);
    ahouse1.buyNow(buyNowProduct2, user1, 7);
    
    // end auction for all products
    ahouse1.endAuction(biddableProduct1);
    ahouse1.endAuction(biddableProduct2);
    ahouse1.endAuction(buyNowProduct1);
    ahouse1.endAuction(buyNowProduct2);
    
    // creating and initialising auxiliary variables for the asserting
    List<String> highestSoldProducts = new ArrayList<String>();
    for (Product product : ahouse1.getHighestValuedSoldProducts()) {
      highestSoldProducts.add(product.toString() + "\n");
    }
    System.out.println("This row\n" + highestSoldProducts);
    assertThat(String.join("", highestSoldProducts),
        anyOf(is("2 - truck\n3 - doll\n"), is("3 - doll\n2 - truck\n")));
    
  } 
}
