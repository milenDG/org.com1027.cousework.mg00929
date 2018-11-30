package org.com1027.coursework.q1;

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
   * Creating an Auction object and check maps created. Check methods return correct values when nothing stored in maps.
   */
  @Test
  public void testAuctionHouseCreation() {
    AuctionHouse ahouse1 = new AuctionHouse();
    Product product = new Product(1, "teddy", 10.00);
    User user = new User("Stella");
    assertEquals(false, ahouse1.checkExistence(product));
    assertEquals("", ahouse1.displaySoldProducts());
    assertEquals(false, ahouse1.placeBid(product, user, 10.00));
  }

  /**
   * Creating an Auction object and check error is thrown in case of invalid product
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRegister1() {
    AuctionHouse ahouse1 = new AuctionHouse();
    User user = new User("Stella");
    assertEquals(true, ahouse1.register(null, user));
  }

  /**
   * Creating an Auction object and check error is thrown in case of invalid user
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRegister2() {
    AuctionHouse ahouse1 = new AuctionHouse();
    Product product = new Product(1, "teddy", 10.00);
    assertEquals(true, ahouse1.register(product, null));
  }

  /**
   * Creating and registering a valid Auction object and check error is thrown in case of placing a bid with invalid product
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBid1() {
    AuctionHouse ahouse1 = new AuctionHouse();
    Product product = new Product(1, "teddy", 10.00);
    User user = new User("Stella");
    assertEquals(true, ahouse1.register(product, user));
    assertEquals(true, ahouse1.placeBid(null, user, 10.00));

  }

  /**
   * Creating and registering a valid Auction object and check error is thrown in case of placing a bid with invalid user
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBid2() {
    AuctionHouse ahouse1 = new AuctionHouse();
    Product product = new Product(1, "teddy", 10.00);
    User user = new User("Stella");
    assertEquals(true, ahouse1.register(product, user));
    assertEquals(true, ahouse1.placeBid(product, null, 10.00));

  }

  /**
   * Creating and registering a valid Auction object and check error is thrown in case of placing a bid with an invalid bid value
   * not greater than 0
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBid3() {
    AuctionHouse ahouse1 = new AuctionHouse();
    Product product = new Product(1, "teddy", 10.00);
    User user = new User("Stella");
    assertEquals(true, ahouse1.register(product, user));
    assertEquals(true, ahouse1.placeBid(product, user, 0.00));

  }

  /**
   * Creating an Auction object, registering and placing bids, checking that a lower bid cannot replace a higher bid already made
   */
  @Test
  public void testRegisterandPlaceBid() {
    AuctionHouse ahouse1 = new AuctionHouse();
    Product product = new Product(1, "teddy", 10.00);
    User user = new User("Stella");
    assertEquals(false, ahouse1.checkExistence(product));
    assertEquals(true, ahouse1.register(product, user));
    // can't register twice
    assertEquals(false, ahouse1.register(product, user));
    // check place bid
    assertEquals(true, ahouse1.placeBid(product, user, 10.00));
    assertEquals(false, ahouse1.placeBid(product, user, 5.00));
    assertEquals(true, ahouse1.placeBid(product, user, 20.00));
  }

  /**
   * Creating a valid auction and registering product. Don't need to test for registering in this (already done). Check that all
   * unsold products stored and displayed correctly. Because Maps don't preserve order we need to be able to check that the output
   * of the map is preserved in any order, see https://examples.javacodegeeks.com/core-java/junit/junit-assertthat-example/
   */
  @Test
  public void testEndAuctionWithNoBids() {
    AuctionHouse ahouse1 = new AuctionHouse();
    Product product1 = new Product(1, "teddy", 10.00);
    Product product2 = new Product(2, "doll", 20.00);
    User user = new User("Stella");
    ahouse1.register(product1, user);
    ahouse1.register(product2, user);
    ahouse1.endAuction(product1);
    ahouse1.endAuction(product2);
    // System.out.println("display unsold auction with no bids \n");
    // System.out.println(ahouse1.displayUnsoldProducts());
    assertThat(ahouse1.displayUnsoldProducts(),
        anyOf(is("1 - teddy" + "\n" + "2 - doll" + "\n"), is("2 - doll" + "\n" + "1 - teddy" + "\n")));
  }

  /**
   * Creating a valid auction and registering product. Don't need to test for registering and bidding in this (already done). Check
   * that all unsold products stored and displayed correctly. Scenario includes making bids that do not meet the reserve prices.
   * Because Maps don't preserve order we need to be able to check that the output of the map is preserved in any order, see
   * https://examples.javacodegeeks.com/core-java/junit/junit-assertthat-example/
   */
  @Test
  public void testEndAuctionWithBidsNotMeetingReserve() {
    AuctionHouse ahouse1 = new AuctionHouse();
    Product product1 = new Product(1, "teddy", 10.00);
    Product product2 = new Product(2, "doll", 20.00);
    User user = new User("Stella");
    ahouse1.register(product1, user);
    ahouse1.register(product2, user);
    ahouse1.placeBid(product1, user, 8.00);
    ahouse1.placeBid(product2, user, 18.00);
    ahouse1.endAuction(product1);
    // System.out.println("display unsold auction with no bids meeting reserve \n");
    // System.out.println(ahouse1.displayUnsoldProducts());
    ahouse1.endAuction(product2);
    // System.out.println(ahouse1.displayUnsoldProducts());
    assertThat(ahouse1.displayUnsoldProducts(),
        anyOf(is("1 - teddy" + "\n" + "2 - doll" + "\n"), is("2 - doll" + "\n" + "1 - teddy" + "\n")));

  }

  /**
   * Creating a valid auction and registering product. Don't need to test for registering and bidding in this (already done). Check
   * that all sold products stored and displayed correctly. Scenario includes making bids that meet the reserve prices. Because Maps
   * don't preserve order we need to be able to check that the output of the map is preserved in any order, see
   * https://examples.javacodegeeks.com/core-java/junit/junit-assertthat-example/
   */
  @Test
  public void testEndAuctionWithBidsMeetingReserve() {
    AuctionHouse ahouse1 = new AuctionHouse();
    Product product1 = new Product(1, "teddy", 10.00);
    Product product2 = new Product(2, "doll", 20.00);
    User user = new User("Stella");
    ahouse1.register(product1, user);
    ahouse1.register(product2, user);
    ahouse1.placeBid(product1, user, 12.00);
    ahouse1.placeBid(product2, user, 22.00);
    ahouse1.endAuction(product1);
    ahouse1.endAuction(product2);
    // System.out.println("display unsold auction with bids meeting reserve \n");
    // System.out.println(ahouse1.displaySoldProducts());
    assertThat(ahouse1.displaySoldProducts(), anyOf(is("1 - Stella bid £12.0" + "\n" + "2 - Stella bid £22.0" + "\n"),
        is("2 - Stella bid £22.0" + "\n" + "1 - Stella bid £12.0" + "\n")));
  }

}
