/**
 * AuctionHouse.java
 */
package org.com1027.coursework.q1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The class is describing the properties and actions of an auction house.
 * @author Milen Georgiev
 *
 */
public class AuctionHouse {
  /** Contains the products which are currently for sale.*/
  private Map<Product, User> forSaleProducts = null;
  
  /** Contains the products whose auction has ended and were sold.*/
  private Map<Product, User> soldProducts = null;
  
  /** Contains the products whose auction has ended, but were not sold.*/
  private Map<Product, User> unsoldProducts = null;
  
  /**
   * Default constructor.
   * Initialises the Maps.
   */
  public AuctionHouse() {
    this.forSaleProducts = new HashMap<Product, User>();
    this.soldProducts = new HashMap<Product, User>();
    this.unsoldProducts = new HashMap<Product, User>();
  }
  
  /**
   * Checks whether a product is currently at auction or has been before.
   * @param product -> the product, whose existence we are checking
   * @return does the product exist
   */
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
    // The next if statements are placed to optimise the code and
    // leave the function with only one return statement.
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
  
  /**
   * Displays the sold products in the format [<ProductId> - <BuyerName> bid £<bidValue>].
   * @return string containing all sold products in the specific format
   */
  public String displaySoldProducts() {
    StringBuffer result = new StringBuffer();
    Set<Product> soldProductsSet = this.soldProducts.keySet();
    
    for (Product product : soldProductsSet) {
      result.append(product.getProductId() + " - " + product.getHighestBid().getBuyer().toString()
          + " bid £" + product.getHighestBid().getBidValue() + "\n");
    }
    
    return result.toString();
  }
  
  
  /**
   * Displays the not sold products in the format [<ProductId> - <ProductName>].
   * @return string containing all not sold products in the specific format
   */
  public String displayUnsoldProducts() {
    StringBuffer result = new StringBuffer();
    Set<Product> unsoldProductsSet = this.unsoldProducts.keySet();
    
    for (Product product : unsoldProductsSet) {
      result.append(product.getProductId() + " - " + product.getProductName() + "\n");
    }
    
    return result.toString();
  }
  
  
  /**
   * Ends the auction of a product and puts it either in map sold or not sold.
   * @param product - the product whose auction is to be ended
   */
  public void endAuction(Product product) {
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    
    if(!this.forSaleProducts.containsKey(product)) {
      throw new IllegalArgumentException("There is no such product!");
    }
    
    if(product.getHighestBid() != null && product.getHighestBid().getBidValue() >= product.getReservedPrice()) {
      // The highest bid is higher than the reserved price so the product is sold.
      User user = this.forSaleProducts.get(product);
      this.forSaleProducts.remove(product);
      this.soldProducts.put(product, user);  
    } else {
      // The highest bid is lower than the reserved price so the product is not sold.
      User user = this.forSaleProducts.get(product);
      this.forSaleProducts.remove(product);
      this.unsoldProducts.put(product, user); 
    }
    
  }
  
  
  /**
   * Placed a bid for a specific product.
   * @param product -> the product which is bid
   * @param user -> the user making the bid
   * @param bidValue -> the value of the bid
   * @return -> whether the bid was successful
   */
  public boolean placeBid(Product product, User user, double bidValue) {
    boolean wasSold = false;
    if(product == null) {
      throw new IllegalArgumentException("Product must not be null!");
    }
    if(user == null) {
      throw new IllegalArgumentException("User must not be null!");
    }
    // Product.placeBid checks whether the bidValue is positive.
    if(this.forSaleProducts.containsKey(product)) {
      wasSold = product.placeBid(user, bidValue);
    }
    
    return wasSold;
  }
  
  
  /**
   * Registers a product for bidding (in map forSale)
   * @param product -> the product to be registered
   * @param user -> the user selling the product
   * @return -> whether the product was registered
   */
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
