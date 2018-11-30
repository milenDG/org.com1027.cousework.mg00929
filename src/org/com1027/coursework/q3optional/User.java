package org.com1027.coursework.q3optional;

import java.util.HashMap;
import java.util.Map;

public class User {
  private String name = null;
  private Map<Integer, Integer> purchases = null;
  private Map<Integer, Double> successfulBids = null;
  
  public User(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must not be null!");
    } 
    if(name.length() == 0) {
      throw new IllegalArgumentException("Name must not be an empty string!");
    }
    
    this.name = name;
    this.purchases = new HashMap<Integer, Integer>();
    this.successfulBids = new HashMap<Integer, Double>();
  }

  
  public void buy(int productId, int quantity) {
    if(productId < 0) {
      throw new IllegalArgumentException("ProductId must be greater than 0!");
    }
    if(quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be positive!");
    }
    this.purchases.put(productId, quantity);
  }
  
  public String displayAllPurchases() {
    return "All Purchased Products: \n" + this.displayPurchases() + this.displaySuccessfulBids();
  }
  
  public String displayPurchases() {
    StringBuffer purchasedProducts = new StringBuffer("Purchases: \n");
    if(this.purchases.size() > 0) {
      for (Map.Entry<Integer, Integer> purchase : this.purchases.entrySet()) {
        purchasedProducts.append(purchase.getKey() + " with quantity " + purchase.getValue() + "\n");
      }
    }
    return purchasedProducts.toString();
  }
  
  public String displaySuccessfulBids() {
    StringBuffer successfulBidProducts = new StringBuffer("Successful Bids: \n");
    if(this.successfulBids.size() > 0) {
      for (Map.Entry<Integer, Double> successfulBid : this.successfulBids.entrySet()) {
        successfulBidProducts.append(successfulBid.getKey() + " at a cost of " + successfulBid.getValue() + "\n");
      }
    }
    return successfulBidProducts.toString();
  }
  
  @Override
  public String toString() {
    return Character.toString(this.name.charAt(0)) + 
        "***" + 
        Character.toString(this.name.charAt(this.name.length() - 1));
  }
  
  public void wonAuction(int productId, double winningPrice) {
    if(productId < 0) {
      throw new IllegalArgumentException("ProductId must be greater than 0!");
    }
    if(winningPrice <= 0) {
      throw new IllegalArgumentException("WinningPrice must be positive!");
    }
    this.successfulBids.put(productId, winningPrice);
  }
  
}
