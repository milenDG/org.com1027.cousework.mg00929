package org.com1027.coursework.q2optional;

public class User {
  private String name = null;
  
  public User(String name) throws IllegalArgumentException {
    super();
    
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
    return Character.toString(this.name.charAt(0)) + 
        "***" + 
        Character.toString(this.name.charAt(this.name.length() - 1));
  }
  
}
