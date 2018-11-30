package org.com1027.coursework.q1;

import java.util.ArrayList;
import java.util.List;


public class Product {
  private int productId = 0;
  private String productName = null;
  private double reservedPrice = 0d;
  private List<Bid> bids = null;
  
  public Product(int productId, String productName, double reservedPrice) {
    if(productId < 0) {
      throw new IllegalArgumentException("ProductId must be non-negative!");
    }
    if(productName == null || productName.length() == 0) {
      throw new IllegalArgumentException("ProductName must not be an empty String!");
    }
    
    this.productId = productId;
    this.productName = productName;
    this.reservedPrice = reservedPrice;
    this.bids = new ArrayList<Bid>();
  }
  
  
  public Bid getHighestBid() {
    if(bids.isEmpty()) {
      return null;
    }
    
    Bid highestBid = bids.get(0);
    
    for (int i = 1; i < this.bids.size(); i++) {
      if(bids.get(i).getBidValue() > highestBid.getBidValue()) {
        highestBid = bids.get(i);
      }
    }
    
    return highestBid;
  }
  
  
  public int getProductId() {
    return this.productId;
  }
  
  
  public String getProductName() {
    return this.productName;
  }
  
  
  public double getReservedPrice() {
    return this.reservedPrice;
  }
  
  public boolean placeBid(User user, double bidValue) {
    boolean wasBidAdded = false;
    
    if(user == null) {
      throw new IllegalArgumentException("User must not be null!");
    }
    if(bidValue <= 0d) {
      throw new IllegalArgumentException("Bid must not be negative or 0!");
    }
    
    if(this.getHighestBid() == null || bidValue > this.getHighestBid().getBidValue()) {
      this.bids.add(new Bid(user, bidValue));
      wasBidAdded = true;
    }
    
    return wasBidAdded;
  }
}
