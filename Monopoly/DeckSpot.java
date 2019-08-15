package Monopoly;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.TextArea;

/**
 * Represents a spot on the monopoly board that is a deck of cards
 */
public abstract class DeckSpot extends BoardSpot {
  
  /*
   * Returns the number of cards in the deck
   * @return numberCards number of cards in the deck
   */
  public abstract int getNumberCards();
  
  /*
   * Removes card at top of the deck and adds to bottom
   */
  public abstract void removeCard();
  
  /*
   * Draws a card from the top of the deck and executes the action specified by the card
   * @param player the player who is going to draw the card
   * @param dialogueBox the text area that informs the user of what is happening in the game
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public abstract void drawCard(Player player, TextArea dialogueBox) throws IllegalAccessException, InvocationTargetException;
  
}