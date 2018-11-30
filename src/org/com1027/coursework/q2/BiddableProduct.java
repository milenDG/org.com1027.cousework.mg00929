package org.com1027.coursework.q2;

import java.util.ArrayList;
import java.util.List;

public class BiddableProduct extends Product{
  private double reservedPrice = 0d;
  private List<Bid> bids = null;
  
  public BiddableProduct(int productId, String productName, double reservedPrice) {
    super(productId, productName);
    this.reservedPrice = reservedPrice;
    this.bids = new ArrayList<Bid>();
  }

  
  public boolean attemptToPurchase(User user, double bidValue) {
    if(user == null) {
      throw new IllegalArgumentException("User must not be null!");
    }
    if(bidValue <= 0d) {
      throw new IllegalArgumentException("BidValue must be positive!");
    }
    
    boolean wasAttemptSuccessful = false;
    
    if (bidValue > this.getCurrentPrice()) {
      this.bids.add(new Bid(user, bidValue));
      wasAttemptSuccessful = true;
    }
    
    return wasAttemptSuccessful;
  }
  

  @Override
  public String displayHistory() {
    StringBuffer history = new StringBuffer();
    history.append(this.getProductId() + ": " + this.getProductName() + " = ");
    if(this.bids.size() > 0) {
      history.append("\n");
      for (int i = bids.size() - 1; i >= 0; i--) {
        history.append(bids.get(i).toString() + "\n");
      }
    } else {
      history.append("no bids");
    }
    
    return history.toString();
  }

  @Override
  public String displayUserInfoForProduct() {
    String userInfo = "";
    
    if(this.bids.size() > 0) {
      userInfo = this.bids.get(this.bids.size() - 1).toString();
    }
    
    return userInfo;
  }

  @Override
  public double getCurrentPrice() {
    double currentPrice = 0;
    if(!bids.isEmpty()) {
      currentPrice = bids.get(0).getBidValue();
      for (Bid bid : bids) {
        if (bid.getBidValue() > currentPrice) {
          currentPrice = bid.getBidValue();
        }
      }
    }
    
    return currentPrice;
  }

  @Override
  public boolean isProductSold() {
    boolean isSold = false;
    
    if(this.getCurrentPrice() > this.reservedPrice) {
      isSold = true;
    }
    
    return isSold;
  }

}
