
package org.com1027.coursework.q1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Tests for the Product class.
 * 
 * @author Stella Kazamia
 */
public class ProductTest {

  /**
   * Creating a Product object with valid input parameter for the product. Test that initial values of getters can be retrieved
   * correctly.
   */
  @Test
  public void testProductConstruction() {
    Product product = new Product(1, "teddy", 10.00);
    assertEquals(1, product.getProductId());
    assertEquals("teddy", product.getProductName());
    assertEquals(10.00, product.getReservedPrice(), 0);

  }

  /**
   * Creating a Product object with invalid input parameter for product name
   * 
   */
  @Test(expected = IllegalArgumentException.class)
  public void testProductInvalidConstruction() {
    Product product = new Product(1, null, 10.00);
  }

  /**
   * Creating a Product object with valid input parameter for the product. Test bids can be entered and that highest bid works
   * correctly.
   * 
   * 
   */
  @Test
  public void testProductBid() {
    Product product1 = new Product(1, "teddy", 10.00);
    User user1 = new User("Stella");
    User user2 = new User("Joe");

    // no bid initially placed for the teddy
    assertNull("no bid returned", product1.getHighestBid());
    // Stella enters a bid of 5.00
    assertEquals(true, product1.placeBid(user1, 5.00));
    assertEquals(5.00, product1.getHighestBid().getBidValue(), 00);
    // Joe enters a bid of 2.00
    assertEquals(false, product1.placeBid(user2, 2.00));
    assertEquals(5.00, product1.getHighestBid().getBidValue(), 00);
    // Joe enters a bid of 7.00
    assertEquals(true, product1.placeBid(user2, 7.00));
    assertEquals(7.00, product1.getHighestBid().getBidValue(), 00);
    assertEquals("Joe", product1.getHighestBid().getBuyer().toString());

  }

  /**
   * Creating a Product object with valid input parameter for the product. Test that invalid bids throws exception
   * 
   * 
   */
  @Test(expected = IllegalArgumentException.class)
  public void testProductInvalidBid() {
    Product product1 = new Product(1, "teddy", 10.00);
    assertEquals(true, product1.placeBid(null, 5.00));
  }

  /**
   * Creating a Product object with valid input parameter for the bid value. Test that invalid bids throws exception
   * 
   * 
   */
  @Test(expected = IllegalArgumentException.class)
  public void testProductInvalidBid2() {
    Product product1 = new Product(1, "teddy", 10.00);
    User user1 = new User("Stella");
    assertEquals(true, product1.placeBid(user1, 0.00));
  }
}