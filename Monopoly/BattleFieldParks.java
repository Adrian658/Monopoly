package Monopoly;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.TextArea;

/* Represents a deck of Battlefield Parks cards */
public class BattleFieldParks extends DeckSpot {
  
  /** Stores the number of cards in the Battlefield Parks deck */
  private int numberCards = this.getClass().getDeclaredMethods().length - 4;
  
  /** Stores the funcionality of each card in a flexible Linked List */
  private LinkedList<Method> battlefieldCards = new LinkedList<Method>();
  
  /** 
   * Constructor which initializes and shuffles the deck of cards
   * @throws NoSuchMethodException
   */
  public BattleFieldParks() throws NoSuchMethodException {
    Method[] methods = this.getClass().getDeclaredMethods(); //stores the methods that represent cards
    Method[] randomizedMethods = new Method[getNumberCards()]; //stores the methods that represent cards in a random order
    int j = 0;
    for (int i = 0; i < methods.length; i++) {
      /* ensures we only add methods that are representative of Battlefield Parks Cards to battlefieldCards */
      if (!methods[i].equals(this.getClass().getMethod("getNumberCards")) && !methods[i].equals(this.getClass().getMethod("getBattlefieldCards")) 
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
    /* adding the randomized methods to the deck of battlefield cards */
    for (int k = 0; k < getNumberCards(); k++) {
      getBattlefieldCards().add(randomizedMethods[k]);
    }
  }
  
  /**
   * Represents a Battlefield Parks card that adds $100 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void restorationProject(Player player, TextArea dialogueBox) {
      player.addBalance(100);
      dialogueBox.appendText("\nBattlefield Parks: Big Cypress national preserve wetlands restoration project. Collect $100");
  }
  
  /**
   * Represents a Battlefield Parks card that subtracts $50 from the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void stepOnCactus(Player player, TextArea dialogueBox) {
      player.subtractBalance(50);
      dialogueBox.appendText("\nBattlefield Parks: Step on a cactus at Saguaro national park. Pay $50");
  }
  
  /**
   * Represents a Battlefield Parks card that adds $50 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void parkVolunteerOfTheYear(Player player, TextArea dialogueBox) {
      player.addBalance(200);
      dialogueBox.appendText("\nBattlefield Parks: Selected as national park volunteer of the year. Collect $200");
  }
  
  /**
   * Represents a Battlefield Parks card that adds $100 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void helpStrandedVisitor(Player player, TextArea dialogueBox) {
      player.addBalance(100);
      dialogueBox.appendText("\nBattlefield Parks: Helped stranded park visitor. Collect $100");
  }
  
  /**
   * Represents a Battlefield Parks card that adds $45 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void weedWarriors(Player player, TextArea dialogueBox) {
      player.addBalance(45);
      dialogueBox.appendText("\nBattlefield Parks: Weed Warriors' at Acadia national park needed tools");
  }
  
  /**
   * Represents a Battlefield Parks card that adds $100 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void volunteerAtPointReyes(Player player, TextArea dialogueBox) {
      player.addBalance(100);
      dialogueBox.appendText("\nBattlefield Parks: Volunteer at Point Reyes national seashore. Collect $100");
  }
  
  /**
   * Represents a Battlefield Parks card that sends the player to jail
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void goToJail(Player player, TextArea dialogueBox) {
      player.setPosition(1, 0, dialogueBox);
      player.setInJail(true);
      dialogueBox.appendText("\nBattlefield Parks: Caught looting archeological dig. Go directly to jail. Do not collect $200 if you pass GO.");
  }
  
  /**
   * Represents a Battlefield Parks card that adds $20 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void spatterConesTrail(Player player, TextArea dialogueBox) {
      player.addBalance(20);
      dialogueBox.appendText("\nBattlefield Parks: Spatter Cones trail at Craters of the Moon national monument needs restoration. Collect $20");
  }
  
  /**
   * Represents a Battlefield Parks card that subtracts $100 from the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void losePaddle(Player player, TextArea dialogueBox) {
      player.subtractBalance(100);
      dialogueBox.appendText("\nBattlefield Parks: Lose paddle canoeing St. Croix national scenic riverway. Pay $100");
  }
  
  /**
   * Represents a Battlefield Parks card that subtracts $150 from the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void loseHeadlamp(Player player, TextArea dialogueBox) {
      player.subtractBalance(150);
      dialogueBox.appendText("\nBattlefield Parks: Lose headlamp exploring Mammoth Cave national park. Pay $150");
  }
  
  /**
   * Represents a Battlefield Parks card that adds $200 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void hikeToGo(Player player, TextArea dialogueBox) {
      player.setPosition(0, 0, dialogueBox);
      player.addBalance(200);
      dialogueBox.appendText("\nBattlefield Parks: Hike over to GO. Collect $200");
  }
  
  /**
   * Represents a Battlefield Parks card that adds $10 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void wonWinterBirdCount(Player player, TextArea dialogueBox) {
      player.addBalance(10);
      dialogueBox.appendText("\nBattlefield Parks: You have won second place in the Rocky Mountain national park winter bird contest. Collect $10");
  }
  
  /**
   * Represents a Battlefield Parks card that adds $25 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void buildingBoardwalk(Player player, TextArea dialogueBox) {
      player.addBalance(25);
      dialogueBox.appendText("\nBattlefield Parks: Building a boardwalk at Congaree Swamp national monument. Collect $25");
  }
  
  /**
   * Represents a Battlefield Parks card that adds $50 to the players balance
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void musicPerformance(Player player, TextArea dialogueBox) {
      player.addBalance(50);
      dialogueBox.appendText("\nBattlefield Parks: Perform at Chamizal national memorial music festival. Collect $50");
  }
  
  /**
   * Represents a Battlefield Parks card that gives the player a get out of jail free card
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void getOutOfJailFree(Player player, TextArea dialogueBox) {
      player.addGetOutOfJailFree();
      dialogueBox.appendText("\nBattlefield Parks: Save an endangered species. Get out of jail free card.");
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
   * @return battlefieldCards the deck battlefield parks cards
   */
  public LinkedList<Method> getBattlefieldCards() {
    return battlefieldCards;
  }
  
  /*
   * Removes card at top of the deck and adds to bottom
   */
  public void removeCard() {
    getBattlefieldCards().add(getBattlefieldCards().getFirst());
    getBattlefieldCards().remove();
  }
  
  /**
   * Invokes the method (draws the card) at the top of the deck and places the played card at the bottom of the deck
   * @param player the player who drew the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public void drawCard(Player player, TextArea dialogueBox) throws IllegalAccessException, InvocationTargetException {
    getBattlefieldCards().getFirst().invoke(this, player, dialogueBox);
    this.removeCard();
  }
  
}