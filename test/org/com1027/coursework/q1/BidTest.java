package org.com1027.coursework.q1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BidTest {
  

  @Test
  public void testBidConstruction() {
    User user = new User("Helen");
    Bid bid = new Bid(user, 10d);
    assertEquals("Helen bid £10.0", bid.toString());
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testBidConstructorWithNegativeBidValue() {
    User user = new User("Helen");
    Bid bid = new Bid(user, -10d);
  }
  
  
  @Test (expected = IllegalArgumentException.class)
  public void testBidConstructorWithNullUser() {
    Bid bid = new Bid(null, 10d);
  }
  
  
  
  
  
}
