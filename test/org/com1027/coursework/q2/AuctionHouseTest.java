
package org.com1027.coursework.q2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

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
   // System.out.println("display unsold auction with no bids and purchases \n");
    //System.out.println(ahouse1.displayUnsoldProducts());
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
   // System.out.println(ahouse1.displayUnsoldProducts());
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
    //System.out.println("display sold auction with bids meeting reserve \n");
    //System.out.println(ahouse1.displaySoldProducts());
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
}
