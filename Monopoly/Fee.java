package Monopoly;

/** 
 * Represents a spot on the Monopoly board where a Fee must be payed
 * @author Adrian Guzman
 */
public class Fee extends BoardSpot {
  
  /** Stores the name of the Fee */
  private String name;
  
  /** Stores the amount of the Fee */
  private int fee;
  
  /** Constructor initializing name and amount of Fee */
  public Fee(String name, int fee) {
    this.name = name;
    this.fee = fee;
  }
  
  /**
   * Returns the name of the Fee
   * @return name name of the Fee
   */
  public String getName() {
    return name;
  }
  
  /** 
   * Returns the amount of the Fee
   * @return fee the amount of the fee
   */
  public int getFee() {
    return fee;
  }
  
}