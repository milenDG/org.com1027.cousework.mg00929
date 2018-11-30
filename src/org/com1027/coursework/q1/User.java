package org.com1027.coursework.q1;

public class User {
  private String name = null;
  
  public User(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must not be null!");
    } 
    if(name.length() == 0) {
      throw new IllegalArgumentException("Name must not be an empty string!");
    }
    
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
  
}
