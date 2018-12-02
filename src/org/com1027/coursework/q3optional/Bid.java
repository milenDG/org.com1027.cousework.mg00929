package org.com1027.coursework.q3optional;


public class Bid {
  private User buyer = null;
  private double bidValue = 0d;
  
  public Bid(User buyer, double bidValue) throws IllegalArgumentException {
    super();
    
    if(buyer == null) {
      throw new IllegalArgumentException("Buyer must not be null!");
    }
    if(bidValue <= 0) {
      throw new IllegalArgumentException("BidValue must be positive!");
    }
    
    this.bidValue = bidValue;
    this.buyer = buyer;
  }
  
  
  public double getBidValue() {
    return this.bidValue;
  }
  
  public User getBuyer() {
    return this.buyer;
  }
  
  @Override
  public String toString() {
    return this.buyer.toString() + " bid £" + this.bidValue;
  }
}
