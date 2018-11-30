
package org.com1027.coursework.q2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * Tests for the BiddableProduct class.
 * 
 * @author Stella Kazamia
 */
public class BiddableProductTest {
  
  
/**
  * Creating a BiddableProduct object with valid input parameter for the product. 
  * Test that initial values of getters can be retrieved correctly. 
  * 
  * 
  */
 @Test
 public void testProductConstruction() {
   BiddableProduct product = new BiddableProduct(1,"teddy",10.00);
   assertEquals(1, product.getProductId());
   assertEquals("teddy", product.getProductName());
   assertEquals("1: teddy = no bids", product.displayHistory());
   assertEquals("", product.displayUserInfoForProduct());
   assertEquals(0, product.getCurrentPrice(),0);
   assertFalse(product.isProductSold());
   
 }
 
 
 /**
  * Creating a BiddableProduct object with invalid input parameter for product name 
  * 
  */
 @Test(expected=IllegalArgumentException.class)
 public void testProductInvalidConstruction() {
   BiddableProduct product = new BiddableProduct(1,null,10.00);
 }

 
 /**
  * Creating a BiddableProduct object with valid input parameter for the product. 
  * Test bids can be entered and that highest bid works correctly.
  * 
  * System.out can be used during debugging of code
  * 
  */
 @Test
 public void testAttemptToPurchaseAndDisplayBids() {
   BiddableProduct product1 = new BiddableProduct(1,"teddy",10.00);
   User user1 = new User("Stella");
   User user2 = new User("Joe");
   // no bid initially placed for the teddy so display product will be empty string
   //System.out.println(product1.displayProduct());
   assertEquals("",product1.displayUserInfoForProduct());   
   //System.out.println(product1.displayHistory());
   assertEquals("1: teddy = no bids",product1.displayHistory());   
   
   //Stella enters a bid of 5.00
   assertEquals(true, product1.attemptToPurchase(user1, 5.00));
   assertEquals(5.00,product1.getCurrentPrice(),00);
   //check that the display methods are as intended
   //System.out.println(product1.displayUserInfoForProduct());
   assertEquals("S***a bid £5.0",product1.displayUserInfoForProduct());
   //System.out.println(product1.displayHistory());
   assertEquals("1: teddy = \n"+"S***a bid £5.0\n",product1.displayHistory());   
   
   //Joe enters a bid of 2.00
   assertEquals(false, product1.attemptToPurchase(user2, 2.00));
   //highest bid not changed
   assertEquals(5.00,product1.getCurrentPrice(),00);
   //check that the display methods are as intended
   //System.out.println(product1.displayUserInfoForProduct());
   assertEquals("S***a bid £5.0",product1.displayUserInfoForProduct());
   //System.out.println(product1.displayHistory());
   assertEquals("1: teddy = \n"+"S***a bid £5.0\n",product1.displayHistory());   
 
   //Joe enters a bid of 7.00
   assertEquals(true, product1.attemptToPurchase(user2, 7.00));
   assertEquals(7.00,product1.getCurrentPrice(),00);
   //System.out.println(product1.displayUserInfoForProduct());
   assertEquals("J***e bid £7.0",product1.displayUserInfoForProduct());
   //System.out.println(product1.displayHistory());
   assertEquals("1: teddy = \n"+"J***e bid £7.0\n"+"S***a bid £5.0\n",product1.displayHistory());   
 
 }
 
 /**
  * Creating a BiddableProduct object with valid input parameter for the product. 
  * Test bids can be entered and that is product sold demonstrates highest
  * bid meets reserved price
  * 
  * 
  */
 @Test
 public void testAttemptToPurchaseAndIsSold() {
   BiddableProduct product1 = new BiddableProduct(1,"teddy",10.00);
   User user1 = new User("Stella");
   User user2 = new User("Joe");
   
   //Stella enters a bid of 5.00
   assertEquals(true, product1.attemptToPurchase(user1, 5.00));
   assertFalse(product1.isProductSold());
  
   //Joe enters a bid of 2.00 so no change
   assertEquals(false, product1.attemptToPurchase(user2, 2.00));
   assertFalse(product1.isProductSold());
   
   //Joe enters a bid of 17.00 so over reserved price now
   assertEquals(true, product1.attemptToPurchase(user2, 17.00));
   assertTrue(product1.isProductSold());

 }
}