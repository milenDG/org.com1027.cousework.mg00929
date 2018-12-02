package org.com1027.coursework.q2optional;

import java.util.ArrayList;
import java.util.List;

public class BuyNowProduct extends Product{
  private double price = 0d;
  private int quantity = 0;
  private List<Purchase> purchases = null;
  
  
  public BuyNowProduct(int productId, String productName, double price, int quantity) throws IllegalArgumentException {
    super(productId, productName);
    
    if (price <= 0d) {
      throw new IllegalArgumentException("Price must be positive!");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be positive!");
    }
    
    this.price = price;
    this.quantity = quantity;
    this.purchases = new ArrayList<Purchase>();
  }

  
  public boolean attemptToPurchase(User user, int quantity) throws IllegalArgumentException {
    if(user == null) {
      throw new IllegalArgumentException("User must not be null!");
    }
    if(quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be positive!");
    }
    
    boolean wasAttemptSuccessful = false;
    
    if (quantity <= this.getQuantity()) {
      this.purchases.add(new Purchase(user, quantity));
      wasAttemptSuccessful = true;
    }
    
    return wasAttemptSuccessful;
  }
  
  @Override
  public String displayHistory() {
    StringBuffer history = new StringBuffer();
    history.append(this.getProductId() + ": " + this.getProductName() +
        " quantity: " + this.quantity + "\n");
    if(this.purchases.size() > 0) {
      history.append("buy now history: \n");
      for (Purchase purchase : purchases) {
        history.append(purchase.toString() + "\n");
      }
    } else {
      history.append("no purchases");
    }
    
    return history.toString();
  }

  @Override
  public String displayUserInfoForProduct() {
    String userInfo = "";
    if(this.purchases.size() > 0) {
      userInfo = this.purchases.get(this.purchases.size() - 1).toString();
    }
    
    return userInfo;
  }

  @Override
  public double getCurrentPrice() {
    return this.price;
  }
  
  
  public int getQuantity() {
    int soldQuantity = 0;
    for (Purchase purchase : purchases) {
      soldQuantity += purchase.getQuantityPurchased();
    }
    return this.quantity - soldQuantity;
  }

  public int howManyPurchases() {
    return this.purchases.size();
  }
  
  @Override
  public boolean isProductSold() {
    return this.getQuantity() == 0;
  }

}
