package Monopoly;

import java.util.LinkedList;
import javafx.scene.control.TextArea;

/**
 * Represents a player of the Monopoly game
 */
public class Player {
  
  /** The name of the player */
  private String name;
  
  /** The current position of the player */
  private int[] position = {3, 10}; //set to GO
  
  /** The current balance of the player */
  private int balance = 1500;
  
  /** The current spot on the board the player is on */
  private BoardSpot currentBoardSpot;
  
  /** The list of properties the player owns */
  private LinkedList<Property> propertiesOwned = new LinkedList<Property>();
  
  /** Whether the player is in jail */
  private boolean inJail = false;
  
  /** The amount of turns the player has been in jail */
  private int jailCount = 0;
  
  /** The players get out of jail free cards */
  private int getOutOfJailFree = 0;
  
  /**
   * Returns the name of the player
   * @return name the name of the player
   */
  public String getName() {
    return name;
  }
  
  /**
   * Sets the name of the player
   * @param name the name of the player
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Returns the players position in array form
   * @return position the players position
   */
  public int[] getPosition() {
    return position;
  }
  
  /**
   * Sets the players position to the specified row and column indices
   * @param row the row to set the players position to
   * @param column the column to set the players position to
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void setPosition(int row, int column, TextArea dialogueBox) {
    position[0] = row;
    position[1] = column;
    if (position[0] == 3 && position[1] == 10) { //adding balance if player passes or lands on GO
      addBalance(200);
      dialogueBox.appendText("\nYou pass GO and collect $200");
    }
  }
  
  /**
   * Moves the player forward the given number of spaces
   * @param spaces the number of spaces to move the player forward
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void moveForwardSpaces(int spaces, TextArea dialogueBox) {
    /* loops the number of spaces specified in parameters */
    for (int i = 0; i < spaces; i++) {
      /* if the player is in the first or third row */
      if (getPosition()[0] == 0 || getPosition()[0] == 2) {
        if (getPosition()[1] == 8) //if the player is in the last column of the row
          setPosition(getPosition()[0] + 1, 0, dialogueBox);
        else
          setPosition(getPosition()[0], getPosition()[1] + 1, dialogueBox);
      }
      /* if the player is in the second or fourth row */
      else if (getPosition()[0] == 1 || getPosition()[0] == 3) {
        if (getPosition()[1] == 10) { //if player is in last column of row
          if (getPosition()[0] == 3) //if player is in last column and row
            setPosition(0, 0, dialogueBox);
          else //if player is only in last column not last row
            setPosition(getPosition()[0] + 1, 0, dialogueBox);
      }
      else //if player is not in last colum of row
        setPosition(getPosition()[0], getPosition()[1] + 1, dialogueBox);
      }
    }
  }
  
  /**
   * Moves the player back the given number of spaces
   * @param spaces the number of spaces to move the player back
   * @param dialogueBox the text area that informs the user of what is happening in the game
   */
  public void moveBackSpaces(int spaces, TextArea dialogueBox) {
    /* loops the number of spaces specified in parameters */
    for (int i = 0; i < spaces; i++) {
      /* if the player is in the second or fourth rows */
      if (getPosition()[0] == 1 || getPosition()[0] == 3) {
        if (getPosition()[1] == 0) { //if player is in first column of row
        setPosition(getPosition()[0] - 1, 8, dialogueBox);
      }
      else //if the player is not in the first column of row
        setPosition(getPosition()[0], getPosition()[1] - 1, dialogueBox);
      }
      /* if the player is in the first or third rows */
      else if (getPosition()[0] == 0 || getPosition()[0] == 2) {
        if (getPosition()[1] == 0) { //if player is in the first column of row
          if (getPosition()[0] == 0) //if player is in the first column and row
            setPosition(3, 10, dialogueBox);
          else //if the player is only in the first column not row
            setPosition(getPosition()[0] - 1, 10, dialogueBox);
      }
      else //if the player is not in the first column
        setPosition(getPosition()[0], getPosition()[1] - 1, dialogueBox);
      }
    }
  }
  
  /**
   * Returns the players current balance
   * @return balance the players current balance
   */
  public int getBalance() {
    return balance;
  }
  
  /**
   * Adds the specified balance to the players current balance
   * @param balance the balance to add
   */
  public void addBalance(int balance) {
    this.balance += balance; 
  }
  
  /**
   * Subtracts the specified balance from the plaers current balance
   * @param balance the balance to subtract
   */
  public void subtractBalance(int balance) {
    this.balance -= balance;
  }
  
  /**
   * Returns the current Board Spot at which the player is located
   * @return currentBoardSpot the board spot at which the player is located
   */
  public BoardSpot getCurrentBoardSpot() {
    return currentBoardSpot;
  }
  
  /**
   * Sets the current board spot at which the player is located
   * @param spot the spot at which the player is located
   */
  public void setCurrentBoardSpot(BoardSpot spot) {
    this.currentBoardSpot = spot;
  }
  
  /**
   * Returns the list of properties owned by the player
   * @return propertiesOwned the list of properties owned by the player
   */
  public LinkedList<Property> getPropertiesOwned() {
    return propertiesOwned;
  }
  
  /**
   * Returns the names of all properties owned by the player and the number of tents or ranger stations on the property
   * @return namesAndTents the names and number of tents of all properties owned by the player
   */
  public LinkedList<String> getPropertiesOwnedNamesAndTents() {
    LinkedList<String> namesAndTents = new LinkedList<String>();
    for (Property p : propertiesOwned) {
      if (!p.getRangerStationOwned())
        namesAndTents.add(p.getName() + " (" + p.getNumTents() + " tents)");
      else
        namesAndTents.add(p.getName() + " (Ranger Station)");
    }
    return namesAndTents;
  }
  
  /**
   * Adds the specified property to the list of properties the player owns
   * @param property the property to add to the players list of owned properties
   */
  public void addProperty(Property property) {
    propertiesOwned.add(property);
  }
  
  /**
   * Mortgages the specified property
   * @param property the property to specify
   */
  public void mortgageProperty(Property property, TextArea dialogueBox) {
    propertiesOwned.remove(property);
    addBalance(property.getMortgage());
    if (property.getRangerStationOwned()) //if the property has a ranger station on it
      property.sellTents(5, dialogueBox);
    else
      property.sellTents(property.getNumTents(), dialogueBox);
    property.setOwnership(false);
    property.setOwner(null);
  }
  
  /**
   * Returns whether the property entered is owned by this player
   * @param propertyName name of the property to search for
   * @return boolean whether the property entered is owned by this player
   */
  public boolean ownsProperty(String propertyName) {
    for (Property property : getPropertiesOwned()) {
      if ((property.getName()).equals(propertyName))
        return true;
    }
    return false;
  }
  
  /**
   * Returns whether the player owns all of the types of the specified type
   * @param type the type of property to search for
   * @return boolean whether the player owns all the types of the specified type
   */
  public boolean ownsAllTypes(String type) {
    int typesOwned = 0;
    /* counts the number of properties of the specified type the player owns */
    for (Property property : getPropertiesOwned()) {
      if (property.getType().equals(type))
        typesOwned++;
    }
    /* if the type is not brown or blue */
    if (!type.equals("Brown") && !type.equals("Blue")) {
      /* if the player owns 3 types then they own all the types and true is returned */
      if (typesOwned == 3)
        return true;
      else //false is returned
        return false;
    }
    /* if the type is not brown or blue */
    else {
      /* if the player owns 2 types then they own all the types and true is returned */
      if (typesOwned == 2)
        return true;
      else //false is returned
        return false;
    }
  }
  
  /**
   * Returns the number of Activity properties owned by this player
   * @return activities number of Activity properties owned by this player
   */
  public int getActivities() {
    int activities = 0; //stores number of activity property the player owns
    for (Property property : getPropertiesOwned()) {
      if ((property.getType()).equals("Activities"))
        activities++;
    }
    return activities;
  }
  
  /**
   * Returns whether the player is in jail
   * @return inJail whether the player is in jail
   */
  public boolean getInJail() {
    return inJail;
  }
  
  /**
   * Sets the value of whether the player is in jail or not
   * @param inJail if the player is in jail or not
   */
  public void setInJail(boolean inJail) {
    this.inJail = inJail;
  }
  
  /**
   * Returns whether the player is visiting jail
   * @return boolean whether the player is visiting jail
   */
  public boolean visitingJail() {
    if (getPosition()[0] == 1 && getPosition()[1] == 0 && !inJail) //if the player is on the jail spot and is not in jail, returns true
      return true;
    else
      return false;
  }
  
  /**
   * Returns the number of turns the player has been in jail
   * @return jailCount the number of turns the player has been in jail
   */
  public int getJailCount() {
    return jailCount;
  }
  
  /**
   * Sets the value of how many turns the player has been in jail
   * @param count the value to set how many turns the player has been in jail
   */
  public void setJailCount(int count) {
    this.jailCount = count;
  }
  
  /**
   * Adds a turn to the number of turns the player has been in jail
   */
  public void addJailCount() {
    jailCount++;
  }
  
  /**
   * Returns the number of get out of jail free cards the player owns
   * @return getOutOfJailFree the number of get out of jail free cards the player has
   */
  public int getGetOutOfJailFree() {
    return getOutOfJailFree;
  }
  
  /**
   * Adds a get out of jail free card to the player's inventory
   */
  public void addGetOutOfJailFree() {
    getOutOfJailFree++;
  }
  
  /**
   * Subtracts a get out of jail free card from the player's inventory
   */
  public void subtractGetOutOfJailFree() {
    getOutOfJailFree--;
  }
}