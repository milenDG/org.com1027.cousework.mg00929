package org.com1027.coursework.q3;


public abstract class Product {
  private int productId = 0;
  private String productName = null;
  
  public Product(int productId, String productName) throws IllegalArgumentException {
    super();
    
    if(productId < 0) {
      throw new IllegalArgumentException("ProductId must be non-negative!");
    }
    if(productName == null || productName.length() == 0) {
      throw new IllegalArgumentException("ProductName must not be an empty String!");
    }
    
    this.productId = productId;
    this.productName = productName;
  }
  
  public abstract String displayHistory();
  
  public abstract String displayUserInfoForProduct();
  
  public abstract double getCurrentPrice();
  
  public int getProductId() {
    return this.productId;
  }
  
  public String getProductName() {
    return this.productName;
  }
  
  public abstract boolean isProductSold();
  
  @Override
  public String toString() {
    return this.productId + " - " + this.productName;
  }
}
