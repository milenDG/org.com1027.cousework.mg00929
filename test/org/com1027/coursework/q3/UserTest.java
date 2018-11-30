
package org.com1027.coursework.q3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * Tests for the User class.
 * 
 * @author Stella Kazamia
 */
public class UserTest {
  

  
  
/**
  * Creating a User object with valid input parameter for the user. 
  * Test that the toString() can be retrieved correctly.
  * 
  * 
  */
 @Test
 public void testUserConstruction() {
   User user = new User("Helen");
   assertEquals("H***n", user.toString());
 }
 
 
 /**
  * Creating a User object with invalid input parameter for the user. 
  * Test that the exception thrown.
  * 
  * 
  */
 @Test(expected=IllegalArgumentException.class)
 public void testUserInvalidConstructions() {
   User user = new User(null);
 }
 
 @Test
 public void testDisplayingUserBidsAndPurchases() {
   User user = new User("Helen");
   user.buy(1, 3);
   user.buy(2, 7);
   user.wonAuction(3, 5);
   user.wonAuction(4, 10);
   assertEquals("Purchases: \n" +
       "1 with quantity 3\n" + 
       "2 with quantity 7\n", user.displayPurchases());
   assertEquals("Successful Bids: \n" +
       "3 at a cost of 5.0\n" +
       "4 at a cost of 10.0\n", user.displaySuccessfulBids());
   assertEquals("All Purchased Products: \n" + 
       "Purchases: \n"+
       "1 with quantity 3\n" + 
       "2 with quantity 7\n" +
       "Successful Bids: \n" +
       "3 at a cost of 5.0\n" +
       "4 at a cost of 10.0\n", user.displayAllPurchases());
 }
 
 @Test
 public void testDisplayingUserWithEmptyBidsAndPurchases() {
   User user = new User("Helen");
   assertEquals("Purchases: \n", user.displayPurchases());
   assertEquals("Successful Bids: \n", user.displaySuccessfulBids());
   assertEquals("All Purchased Products: \n" +
       "Purchases: \n" +
       "Successful Bids: \n", user.displayAllPurchases());
 }
 
}