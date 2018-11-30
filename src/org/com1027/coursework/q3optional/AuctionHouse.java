package org.com1027.coursework.q3optional;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AuctionHouse {
  private Map<Product, User> forSaleProducts = null;
  private Map<Product, User> soldProducts = null;
  private Map<Product, User> unsoldProducts = null;
  
  public AuctionHouse() {
    this.forSaleProducts = new HashMap<Product, User>();
    this.soldProducts = new HashMap<Product, User>();
    this.unsoldProducts = new HashMap<Product, User>();
  }
  
  
  public boolean buyNow(BuyNowProduct product, User buyer, int quantity) {
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    if (buyer == null) {
      throw new IllegalArgumentException("Buyer must not be null!");
    }
    
    boolean wasSuccessful = product.attemptToPurchase(buyer, quantity);
    if(wasSuccessful) {
      buyer.buy(product.getProductId(), quantity);
      User owner = this.forSaleProducts.get(product);
      this.soldProducts.put(new BuyNowProduct((BuyNowProduct)product), owner);
      }
    
    return wasSuccessful;
  }
  
  public boolean checkExistence(Product product) {
    boolean productExists = false;
    
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    
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
    
    List<Product> sortedProducts = this.soldProducts.keySet()
        .stream()
        .collect(Collectors.toList());
    Collections.sort(sortedProducts, new Comparator<Product>() {
      @Override 
      public int compare(Product lhs,Product rhs){
        return lhs.getProductId() - rhs.getProductId();
      }
    });
    for (Product product : sortedProducts) {
        result.append(product.getProductId() + " - " + product.getProductName() +
            " - " + product.displayUserInfoForProduct() +  "\n");
    }
    
    return result.toString();
  }
  
  
  public String displayUnsoldProducts() {
    StringBuffer result = new StringBuffer();
    List<Product> sortedProducts = this.unsoldProducts.keySet()
        .stream()
        .collect(Collectors.toList());
    Collections.sort(sortedProducts, new Comparator<Product>() {
      @Override 
      public int compare(Product lhs,Product rhs){
        return lhs.getProductId() - rhs.getProductId();
      }
    });
    for (Product product : sortedProducts) {
      if (product instanceof BuyNowProduct) {
        result.append(product.getProductId() + " - " + product.getProductName() +
            " with " + ((BuyNowProduct)product).getQuantity() + " remaining\n");
      } else {
        result.append(product.getProductId() + " - " + product.getProductName() + "\n");
      }
    }
    
    return result.toString();
  }
  
  
  public void endAuction(Product product) {
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    
    if(!this.forSaleProducts.containsKey(product)) {
      throw new IllegalArgumentException("There is no such product!");
    }
    
    if(product.isProductSold() && product instanceof BiddableProduct) {
      User user = this.forSaleProducts.get(product);
      this.forSaleProducts.remove(product);
      this.soldProducts.put(product, user);  
    } else {
        if(!product.isProductSold()) {
          User user = this.forSaleProducts.get(product);
          this.forSaleProducts.remove(product);
          this.unsoldProducts.put(product, user); 
        }
     }
  }
  
  
  public boolean placeBid(BiddableProduct product, User user, double bidValue) {
    boolean wasSold = false;
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    if(user == null) {
      throw new IllegalArgumentException("User must not be null!");
    }
    // Product.placeBid checks whether the bidValue is positive.
    if(this.forSaleProducts.containsKey(product)) {
      wasSold = product.attemptToPurchase(user, bidValue);
      if (wasSold) {
        user.wonAuction(product.getProductId(), bidValue);
      }
    }
    
    return wasSold;
  }
  
  
  public boolean register(Product product, User user) {
    boolean wasRegistered = false;
    
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null");
    }
    if(user == null) {
      throw new IllegalArgumentException("User must not be null");
    }
    
    if(!this.forSaleProducts.containsKey(product)) {
      this.forSaleProducts.put(product, user);
      wasRegistered = true;
    }
    
    return wasRegistered;
  }
  
  
}
