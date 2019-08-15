package Monopoly;

/**
 * Represents the Free Parking spot on the Monopoly board
 * @author Adrian Guzman
 */
public class FreeParking extends BoardSpot {
  
  /** Represents amount stored in free parking */
  private static int balance = 500;
  
  /**
   * Returns amount stored in free parking 
   * @return balance balance of free parking
   */
  public static int getBalance() {
    return balance;
  }
  
  /**
   * Sets the balance to the specified amount
   * @param amount amount to set the balance to
   */
  public static void setBalance(int amount) {
    balance = amount;
  }
  
  /**
   * Adds the specified balance to free parking
   * @param amount amount to add to free parking
   */
  public static void addBalance(int amount) {
    balance += amount;
  }
  
  /**
   * Rewards the specified player with the current free parking balance and resets the free parking balance to 500
   * @param player player whos turn it is
   */
  public static void rewardPlayer(Player player) {
    player.addBalance(FreeParking.getBalance());
    FreeParking.setBalance(500);
  }
  
}