
package org.com1027.coursework.q2optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * Tests for the BuyNow class.
 * 
 * @author Stella Kazamia
 */
public class BuyNowProductTest {
  
  
  /**
   * Creating a BiddableProduct object with valid input parameter for the product. 
   * Test that initial values of getters can be retrieved correctly. 
   * 
   * 
   */
 @Test
 public void testProductConstruction() {
   BuyNowProduct product = new BuyNowProduct(1,"teddy",10.00,5);
   assertEquals(1, product.getProductId());
   assertEquals("teddy", product.getProductName());
   assertEquals(10.0, product.getCurrentPrice(),0);
   assertFalse(product.isProductSold());   
   //System.out.println(product.displayHistory());
   assertEquals("1: teddy quantity: 5\n"+"no purchases", product.displayHistory());
   //System.out.println(product.displayUserInfoForProduct());
   assertEquals("", product.displayUserInfoForProduct());
  
 }
 

 /**
  * Creating a BuyNow object with invalid input parameter for the user. 
  * 
  */
 @Test(expected=IllegalArgumentException.class)
 public void testProductInvalidConstruction1() {
   BuyNowProduct product = new BuyNowProduct(1,null,10.00,5);
 }

 /**
  * Creating a BuyNow object with invalid input parameter for the price. 
 * 
  */
 @Test(expected=IllegalArgumentException.class)
 public void testProductInvalidConstruction2() {
   BuyNowProduct product = new BuyNowProduct(1,"teddy",0.00,5);
 }
 
 
 /**
  * Creating a BuyNow object with invalid input parameter for the quantity. 
 * 
  */
 @Test(expected=IllegalArgumentException.class)
 public void testProductInvalidConstruction3() {
   BuyNowProduct product = new BuyNowProduct(1,"teddy",10.00,0);
 }
 /**
  * Creating a BuyNow object with valid input parameters for the product. 
  * Test purchases can be made and displays work
  * 
  * System.out can be used during debugging of code
  * 
  */
 @Test
 public void testAttemptToPurchaseAndQueryAndDisplayPurchases() {
   BuyNowProduct product1 = new BuyNowProduct(1,"teddy",10.00,9);
   User user1 = new User("Stella");
   User user2 = new User("Joe");
   // no bid initially placed for the teddy so display product will be empty string
   //System.out.println(product1.displayUserInfoForProduct());
   assertEquals("",product1.displayUserInfoForProduct());   
   //System.out.println(product1.displayHistory());
   assertEquals("1: teddy quantity: 9\n"+"no purchases", product1.displayHistory());
      
   //Stella buys 5 teddy
   assertEquals(true, product1.attemptToPurchase(user1, 5));
   //check that the display methods are as intended
  // System.out.println(product1.displayUserInfoForProduct());
   assertEquals("S***a bought 5",product1.displayUserInfoForProduct());
   //System.out.println(product1.displayHistory());
   assertEquals("1: teddy quantity: 9\n"+"buy now history: \n" + "S***a bought 5\n", 
       product1.displayHistory());
    
   //Joe buys 2
   assertEquals(true, product1.attemptToPurchase(user2, 2));
   assertEquals("J***e bought 2",product1.displayUserInfoForProduct());
   //System.out.println(product1.displayHistory());
   assertEquals("1: teddy quantity: 9\n"+"buy now history: \n" + "S***a bought 5\n" + "J***e bought 2\n",  
       product1.displayHistory());
 }
 
 /**
  * Creating a BuyNow object with valid input parameter for the product. 
  * Test purchases can be made when there are buy now items available 
  * and that is product sold demonstrates all quantity sold
  * 
  * 
  */
 
 
 @Test
 public void testAttemptToPurchaseAndIsSold() {
   BuyNowProduct product1 = new BuyNowProduct(1,"teddy",10.00,10);
   User user1 = new User("Stella");
   User user2 = new User("Joe");
   
   //Stella buys 5 teddy
   assertEquals(true, product1.attemptToPurchase(user1, 5));
   assertFalse(product1.isProductSold());
  
   //Joe buys 2 teddy
   assertEquals(true, product1.attemptToPurchase(user2, 2));
   assertFalse(product1.isProductSold());
   
   //Stella buys another 3
   assertEquals(true, product1.attemptToPurchase(user1, 3));
   assertTrue(product1.isProductSold());

   //Stella attempts to buys another 3
   assertEquals(false, product1.attemptToPurchase(user1, 3));
 
 }
 
}