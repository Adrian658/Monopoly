package Monopoly;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.TextArea;

/**
 * Represents a deck of Historic Sites card
 */
public class HistoricSites extends DeckSpot {
  
  /** Stores the number of cards in the Historic Sites deck */
  private int numberCards = this.getClass().getDeclaredMethods().length - 4;
  
  /** Stores the funcionality of each card in a flexible Linked List */
  private LinkedList<Method> historicSitesCards = new LinkedList<>();
  
  /** 
   * Constructor which initializes and shuffles the deck of cards
   * @throws NoSuchMethodException
   */
  public HistoricSites() throws NoSuchMethodException {
    Method[] methods = this.getClass().getDeclaredMethods(); //stores the methods that represent cards
    Method[] randomizedMethods = new Method[getNumberCards()]; //stores the methods that represent cards in a random order
    int j = 0;
    for (int i = 0; i < methods.length; i++) {
      /* ensures we only add methods that are representative of Historic Sites Cards to historicSitesCards */
      if (!methods[i].equals(this.getClass().getMethod("getNumberCards")) && !methods[i].equals(this.getClass().getMethod("getHistoricSitesCards")) 
            && !methods[i].equals(this.getClass().getMethod("removeCard")) && !methods[i].equals(this.getClass().getMethod("drawCard", Player.class, TextArea.class))) {
        /* to provide randomization for the deck of cards whenever a new deck is created, essentially a digital shuffle */
        int index = (int)(Math.random() * getNumberCards());
        while (randomizedMethods[index] != null) {
          index = (int)(Math.random() * getNumberCards());
        }
        randomizedMethods[index] = methods[i];
        j++;
      }
    }
    /* adding the randomized methods to the deck of Historic Sites cards */
    for (int k = 0; k < getNumberCards(); k++) {
      getHistoricSitesCards().add(randomizedMethods[k]);
    }
  }
  
  /**
   * Represents a Historic Sites card that moves the player back 3 spaces
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void parkClosedDueToFloods(Player player, TextArea dialogueBox) {
      player.moveBackSpaces(3, dialogueBox);
      dialogueBox.appendText("\nHistoricSites: Park closed due to floods. Go back 3 spaces");
  }
  
  /**
   * Represents a Historic Sites card that subtracts $15 from the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void donateToYourFavoritePark(Player player, TextArea dialogueBox) {
      player.subtractBalance(15);
      dialogueBox.appendText("\nHistoricSites: Donate to your favorite park. Pay $15");
  }
  
  /**
   * Represents a Historic Sites card that adds $50 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void historicWaterRoutes(Player player, TextArea dialogueBox) {
      player.addBalance(50);
      dialogueBox.appendText("\nHistoricSites: Travel voyageurs national park's historic water routes. Recieve $50");
  }
  
  /**
   * Represents a Historic Sites card that advances the player to Yellowstone
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void aroundOldFaithful(Player player, TextArea dialogueBox) {
      player.setPosition(3, 9, dialogueBox);
      dialogueBox.appendText("\nHistoricSites: Around Old Faithful. Advance to Yellowstone.");
  }
  
  /**
   * Represents a Historic Sites card that advances the player to GO
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void shuttleToGo(Player player, TextArea dialogueBox) {
      player.setPosition(0, 0, dialogueBox);
      dialogueBox.appendText("\nHistoricSites: Catch park shuttle to GO.");
  }
  
  /**
   * Represents a Historic Sites card that advances the player to Glacier
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void kayakToGlacier(Player player, TextArea dialogueBox) {
      if (player.getPosition()[0] == 3 && player.getPosition()[1] > 1)
        player.addBalance(200);
      player.setPosition(3, 1, dialogueBox);
      dialogueBox.appendText("\nHistoricSites: Kayak to Glacier. If you pass GO, collect 200 dollars.");
  }
  
  /**
   * Represents a Historic Sites card that advances the player to Bryce Canyon
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void takeACanyonTrip(Player player, TextArea dialogueBox) {
      player.setPosition(2, 2, dialogueBox);
      dialogueBox.appendText("\nHistoricSites: Take a canyon trip. Advance to Bryce Canyon.");
  }
  
  /**
   * Represents a Historic Sites card that advances the player to the nearest Activity spot
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void advanceToActivity(Player player, TextArea dialogueBox) {
    boolean onActivity = false;
    while (!onActivity) {
      player.moveForwardSpaces(1, dialogueBox);
      if (player.getCurrentBoardSpot() instanceof Property && ((Property)player.getCurrentBoardSpot()).getType() == "Activities")
        onActivity = true;
    }
    dialogueBox.appendText("\nHistoricSites: Advance to the nearest activity. If the activity is unowned, you may buy it from the bank.");
  }
  
  /**
   * Represents a Historic Sites card that sends the player to jail
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void goToJail(Player player, TextArea dialogueBox) {
    player.setPosition(1, 0, dialogueBox);
    player.setInJail(true);
    dialogueBox.appendText("\nHistoricSites: Leave bear box open. Go directly to jail. Do not collect $200 if you pass GO.");
  }
  
  /**
   * Represents a Historic Sites card that adds $50 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void monumentPreservation(Player player, TextArea dialogueBox) {
      player.addBalance(150);
      dialogueBox.appendText("\nHistoricSites: Ancient ruins at Tuzigoot National Monument need preservation. Collect $150");
  }
  
  /**
   * Represents a Historic Sites card that advances the player to the nearest Activity spot
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void advanceToActivityTwo(Player player, TextArea dialogueBox) {
    boolean onActivity = false;
    while (!onActivity) {
      player.moveForwardSpaces(1, dialogueBox);
      if (player.getCurrentBoardSpot() instanceof Property && ((Property)player.getCurrentBoardSpot()).getType() == "Activities")
        onActivity = true;
    }
    dialogueBox.appendText("\nHistoricSites: Advance to the nearest activity. If the activity is unowned, you may buy it from the bank.");
  }
  
  /**
   * Represents a Historic Sites card that gives the player a get out of jail free card
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void getOutOfJailFree(Player player, TextArea dialogueBox) {
      player.addGetOutOfJailFree();
      dialogueBox.appendText("\nHistoricSites: Escape from Alcatraz. Get out of jail free card.");
  }
  
  /**
   * Represents a Historic Sites card that advances the player to the River Rafting Activity spot
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void goRiverRafting(Player player, TextArea dialogueBox) {
      player.setPosition(3, 5, dialogueBox);
      if (player.getPosition()[0] == 3 && player.getPosition()[1] > 5)
        player.addBalance(200);
      dialogueBox.appendText("\nHistoricSites: Go river rafting. If you pass GO, collect $200");
  }
  
  /*
   * Returns the number of cards in the deck
   * @return numberCards number of cards in the deck
   */
  public int getNumberCards() {
    return numberCards;
  }
  
  /**
   * Returns deck of cards in LinkedList structure
   * @return historicSitesCards the deck Historic Sites cards
   */
  public LinkedList<Method> getHistoricSitesCards() {
    return historicSitesCards;
  }
  
  /*
   * Removes card at top of the deck and adds to bottom
   */
  public void removeCard() {
    getHistoricSitesCards().add(getHistoricSitesCards().getFirst());
    getHistoricSitesCards().remove();
  }
  
  /**
   * Invokes the method (draws the card) at the top of the deck and places the played card at the bottom of the deck
   * @param player player who will be drawing a card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public void drawCard(Player player, TextArea dialogueBox) throws IllegalAccessException, InvocationTargetException {
    getHistoricSitesCards().getFirst().invoke(this, player, dialogueBox);
    this.removeCard();
  }
  
}