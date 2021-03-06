package org.com1027.coursework.q2optional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AuctionHouse {
  private Map<Product, User> forSaleProducts = null;
  private Map<Product, User> soldProducts = null;
  private Map<Product, User> unsoldProducts = null;
  
  public AuctionHouse() {
    super();
    
    this.forSaleProducts = new HashMap<Product, User>();
    this.soldProducts = new HashMap<Product, User>();
    this.unsoldProducts = new HashMap<Product, User>();
  }
  
  
  public boolean buyNow(BuyNowProduct product, User buyer, int quantity) throws IllegalArgumentException {
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    if (buyer == null) {
      throw new IllegalArgumentException("Buyer must not be null!");
    }
    
    return product.attemptToPurchase(buyer, quantity);
  }
  
  public boolean checkExistence(Product product) throws IllegalArgumentException {
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    
    boolean productExists = false;
    
    for (Map.Entry<Product, User> entry: this.forSaleProducts.entrySet()) {
      if(entry.getKey() == product) {
        productExists = true;
        break;
      }
    }
    
    if(productExists == false) {
      for (Map.Entry<Product, User> entry: this.soldProducts.entrySet()) {
        if(entry.getKey() == product) {
          productExists = true;
          break;
        }
      }
    }
    
    if(productExists == false) {
      for (Map.Entry<Product, User> entry: this.unsoldProducts.entrySet()) {
        if(entry.getKey() == product) {
          productExists = true;
          break;
        }
      }
    }
    
    return productExists;
  }
  
  public String displaySoldProducts() {
    StringBuffer result = new StringBuffer();
    Set<Product> soldProductsSet = this.soldProducts.keySet();
    
    for (Product product : soldProductsSet) {
      result.append(product.getProductId() + " - " + product.displayUserInfoForProduct() +  "\n");
    }
    
    return result.toString();
  }
  
  
  public String displayUnsoldProducts() {
    StringBuffer result = new StringBuffer();
    Set<Product> unsoldProductsSet = this.unsoldProducts.keySet();
    
    for (Product product : unsoldProductsSet) {
      result.append(product.getProductId() + " - " + product.getProductName() + "\n");
    }
    
    return result.toString();
  }
  
  
  public void endAuction(Product product) throws IllegalArgumentException {
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    
    if(!this.forSaleProducts.containsKey(product)) {
      throw new IllegalArgumentException("There is no such product!");
    }
    
    if(product.isProductSold()) {
      User user = this.forSaleProducts.get(product);
      this.forSaleProducts.remove(product);
      this.soldProducts.put(product, user);  
    } else {
      User user = this.forSaleProducts.get(product);
      this.forSaleProducts.remove(product);
      this.unsoldProducts.put(product, user); 
    }
    
  }
  
  public List<Product> getHighestValuedSoldProducts(){
    if(this.soldProducts.size() == 0) {
      return null;
    }
    
    List<Product> highestValuedProducts = new ArrayList<Product>();
    double maximumPrice = Double.MIN_VALUE;
    
    for (Product product : this.soldProducts.keySet()) {
      if (product.getCurrentPrice() > maximumPrice) {
        maximumPrice = product.getCurrentPrice();
        highestValuedProducts.clear();
        highestValuedProducts.add(product);
      } else if (product.getCurrentPrice() == maximumPrice){
        highestValuedProducts.add(product);
      }
    }
    
    return highestValuedProducts;
  }
  
  public boolean placeBid(BiddableProduct product, User user, double bidValue) throws IllegalArgumentException {
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    if(user == null) {
      throw new IllegalArgumentException("User must not be null!");
    }
    
    boolean wasSold = false;
    
    // Product.placeBid checks whether the bidValue is positive.
    if(this.forSaleProducts.containsKey(product)) {
      wasSold = product.attemptToPurchase(user, bidValue);
    }
    
    return wasSold;
  }
  
  
  public boolean register(Product product, User user) throws IllegalArgumentException {
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null");
    }
    if(user == null) {
      throw new IllegalArgumentException("User must not be null");
    }
    
    boolean wasRegistered = false;
    
    if(!this.forSaleProducts.containsKey(product)) {
      this.forSaleProducts.put(product, user);
      wasRegistered = true;
    }
    
    return wasRegistered;
  }
  
}
