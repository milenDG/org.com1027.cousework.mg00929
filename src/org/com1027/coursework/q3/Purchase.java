package org.com1027.coursework.q3;


public class Purchase {
  private User buyer = null;
  private int quantityPurchased = 0;
  
  public Purchase(User buyer, int quantityPurchased) {
    if(buyer == null) {
      throw new IllegalArgumentException("Buyer must not be null!");
    }
    if(quantityPurchased <= 0) {
      throw new IllegalArgumentException("QuantityPurchased must be positive!");
    }
    
    this.quantityPurchased = quantityPurchased;
    this.buyer = buyer;
  }
  
  
  public int getQuantityPurchased() {
    return this.quantityPurchased;
  }
  
  public User getBuyer() {
    return this.buyer;
  }
  
  @Override
  public String toString() {
    return this.buyer.toString() + " bought " + this.quantityPurchased;
  }
}
