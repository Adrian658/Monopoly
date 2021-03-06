import Monopoly.BattleFieldParks;
import Monopoly.BoardSpot;
import Monopoly.DeckSpot;
import Monopoly.Fee;
import Monopoly.FreeParking;
import Monopoly.HistoricSites;
import Monopoly.Player;
import Monopoly.Property;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import java.net.URL;
import java.net.MalformedURLException;
import java.awt.Desktop;
import java.net.URISyntaxException;
import java.io.IOException;
import javafx.scene.input.MouseEvent;

/**
 * Represents an instance of a monopoly game
 */
public class Monopoly extends Application {
  
  /** The monopoly board represented by a 2D array of objects representing the funcionality of each spot on the board */
  private BoardSpot[][] board = {{null, null, null, null, null, null, null, null, null}, {null, null, null, null, null, null, null, null, null, null, null}, 
    {null, null, null, null, null, null, null, null, null}, {null, null, null, null, null, null, null, null, null, null, null}};
  
  /** The names of all the porperties in this monopoly game */
  private String[] propertyNames = {"Cuyahoga Valley", "Joshua Tree", "Nature Hiking", "Death Valley", "Arches", "Redwood", "Big Bend", "Volcano", "Olympic",
    "Great Smoky Mountain", "Rock Climbing", "Everglades", "Carlsbad Caverns", "Grand Teton", "Acadia", "Bryce Canyon", "Zion", "Bicycling", "Grand Canyon",
    "Denali", "Geyser", "Rocky Mountains", "Glacier", "Mount Rainier", "Sequoia", "River Rafting", "Yosemite", "YellowStone"};
  
  /** Keeps track of how many turns there have been in the current game */
  private int turnCount = 1;
  
  /** Represents a digitial roll of two dice */
  private int diceRoll;
  
  /** Stores if the current player has rolled on their turn */
  private boolean rolled = false;
  
  /** The box that stores the buttons */
  private VBox buttonBox = new VBox();
  
  /** Keeps track of the current spot on the board the player is on as an object */
  private BoardSpot currentBoardSpot;
  
  /** The number of players in the game */
  private int numPlayers = 2;
  
  /** The first player */
  private Player player1 = new Player();
  
  /** The second player */
  private Player player2 = new Player();
  
  /** Represents a deck of Historic Sites cards */
  private HistoricSites historicSites;
  
  /** Represents a deck of Battlefield Parks cards */
  private BattleFieldParks battlefieldParks;
  
  /** The box where the game outputs messages to the players about what is happening in the game */
  private TextArea dialogueBox = new TextArea("Welcome to Monopoly - National Parks");
  
  /** A scroll pane to hold the dialogue box */
  private ScrollPane scrollPane = new ScrollPane();
  
  /** A border pane containing the buttons and the dialogue box */
  private BorderPane tertiaryPane = new BorderPane();
  
  /**
   * Launches the monopoly game when the program is run
   * @param primaryStage the stage do dsplay the game on
   * @throws NoSuchMethodException
   */
  @Override
  public void start(Stage primaryStage) throws NoSuchMethodException {
    
    /* initializing the screen that retrieves both players names which then initializes the board image and all the buttons */
    getPlayer1Name(primaryStage);
    /* initializing the board functionality */
    initializeBoardProperties();
    
  }
  
  /**
   * Creates the images of the monopoly board and displays them in the primary stage
   * @param primaryStage the main stage of the monopoly game
   */
  public void initializeBoardImage(Stage primaryStage) {
    
    /* Creating and resizing images on left of monopoly board */
    ImageView cuyahogaValley = new ImageView(new Image("Images/cuyahogaValley.png")); cuyahogaValley.setFitHeight(58); cuyahogaValley.setFitWidth(100);
    ImageView battlefieldParksLeft = new ImageView(new Image("Images/battleFieldParksLeft.png")); battlefieldParksLeft.setFitHeight(58);battlefieldParksLeft.setFitWidth(100);
    ImageView joshuaTree = new ImageView(new Image("Images/joshuaTree.png")); joshuaTree.setFitHeight(58); joshuaTree.setFitWidth(100);
    ImageView campfireViolation = new ImageView(new Image("Images/campfireViolation.png")); campfireViolation.setFitHeight(58); campfireViolation.setFitWidth(100);
    ImageView natureHiking = new ImageView(new Image("Images/natureHiking.png")); natureHiking.setFitHeight(58); natureHiking.setFitWidth(100);
    ImageView deathValley = new ImageView(new Image("Images/deathValley.png")); deathValley.setFitHeight(58); deathValley.setFitWidth(100);
    ImageView historicSitesLeft = new ImageView(new Image("Images/historicSitesLeft.png")); historicSitesLeft.setFitHeight(58); historicSitesLeft.setFitWidth(100);
    ImageView arches = new ImageView(new Image("Images/arches.png")); arches.setFitHeight(58); arches.setFitWidth(100);
    ImageView redwood = new ImageView(new Image("Images/redwood.png")); redwood.setFitHeight(58); redwood.setFitWidth(100);
    /* Creating and resizing images on top of monopoly board */
    ImageView jail = new ImageView(new Image("Images/jail.png")); jail.setFitHeight(100); jail.setFitWidth(100);
    ImageView bigBend = new ImageView(new Image("Images/bigBend.png")); bigBend.setFitHeight(100); bigBend.setFitWidth(58);
    ImageView volcano = new ImageView(new Image("Images/volcano.png")); volcano.setFitHeight(100); volcano.setFitWidth(58);
    ImageView olympic = new ImageView(new Image("Images/olympic.png")); olympic.setFitHeight(100); olympic.setFitWidth(58);
    ImageView greatSmokyMountains = new ImageView(new Image("Images/greatSmokyMountains.png")); greatSmokyMountains.setFitHeight(100); greatSmokyMountains.setFitWidth(58);
    ImageView rockClimbing = new ImageView(new Image("Images/rockClimbing.png")); rockClimbing.setFitHeight(100); rockClimbing.setFitWidth(58);
    ImageView everglades = new ImageView(new Image("Images/everglades.png")); everglades.setFitHeight(100); everglades.setFitWidth(58);
    ImageView battlefieldParksTop = new ImageView(new Image("Images/battleFieldParksTop.png")); battlefieldParksTop.setFitHeight(100); battlefieldParksTop.setFitWidth(58);
    ImageView carlsbadCaverns = new ImageView(new Image("Images/carlsbadCaverns.png")); carlsbadCaverns.setFitHeight(100); carlsbadCaverns.setFitWidth(58);
    ImageView grandTeton = new ImageView(new Image("Images/grandTeton.png")); grandTeton.setFitHeight(100); grandTeton.setFitWidth(58);
    ImageView freeParking = new ImageView(new Image("Images/freeParking.png")); freeParking.setFitHeight(100); freeParking.setFitWidth(100);
    /* Creating and resizing images on right of monopoly board */
    ImageView acadia = new ImageView(new Image("Images/acadia.png")); acadia.setFitHeight(58); acadia.setFitWidth(100);
    ImageView historicSitesRight = new ImageView(new Image("Images/historicSitesRight.png")); historicSitesRight.setFitHeight(58); historicSitesRight.setFitWidth(100);
    ImageView bryceCanyon = new ImageView(new Image("Images/bryceCanyon.png")); bryceCanyon.setFitHeight(58); bryceCanyon.setFitWidth(100);
    ImageView zion = new ImageView(new Image("Images/zion.png")); zion.setFitHeight(58); zion.setFitWidth(100);
    ImageView bicycling = new ImageView(new Image("Images/bicycling.png")); bicycling.setFitHeight(58); bicycling.setFitWidth(100);
    ImageView grandCanyon = new ImageView(new Image("Images/grandCanyon.png")); grandCanyon.setFitHeight(58); grandCanyon.setFitWidth(100);
    ImageView denali = new ImageView(new Image("Images/denali.png")); denali.setFitHeight(58); denali.setFitWidth(100);
    ImageView geyser = new ImageView(new Image("Images/geyser.png")); geyser.setFitHeight(58); geyser.setFitWidth(100);
    ImageView rockyMountains = new ImageView(new Image("Images/rockyMountains.png")); rockyMountains.setFitHeight(58); rockyMountains.setFitWidth(100);
    /* Creating and resizing images on bottom of monopoly board */
    ImageView goToJail = new ImageView(new Image("Images/goToJail.png")); goToJail.setFitHeight(100); goToJail.setFitWidth(100);
    ImageView glacier = new ImageView(new Image("Images/glacier.png")); glacier.setFitHeight(100); glacier.setFitWidth(58);
    ImageView mountRainier = new ImageView(new Image("Images/mountRainier.png")); mountRainier.setFitHeight(100); mountRainier.setFitWidth(58);
    ImageView battlefieldParksBottom = new ImageView(new Image("Images/battleFieldParksBot.png")); battlefieldParksBottom.setFitHeight(100); battlefieldParksBottom.setFitWidth(58);
    ImageView sequoia = new ImageView(new Image("Images/sequoia.png")); sequoia.setFitHeight(100); sequoia.setFitWidth(58);
    ImageView riverRafting = new ImageView(new Image("Images/riverRafting.png")); riverRafting.setFitHeight(100); riverRafting.setFitWidth(58);
    ImageView historicSitesBottom = new ImageView(new Image("Images/historicSitesBot.png")); historicSitesBottom.setFitHeight(100); historicSitesBottom.setFitWidth(58);
    ImageView yosemite = new ImageView(new Image("Images/yosemite.png")); yosemite.setFitHeight(100); yosemite.setFitWidth(58);
    ImageView purchaseAnnualPass = new ImageView(new Image("Images/purchaseAnnualPass.png")); purchaseAnnualPass.setFitHeight(100); purchaseAnnualPass.setFitWidth(58);
    ImageView yellowstone = new ImageView(new Image("Images/yellowstone.png")); yellowstone.setFitHeight(100); yellowstone.setFitWidth(58);
    ImageView go = new ImageView(new Image("Images/GO.png")); go.setFitHeight(100); go.setFitWidth(100);
    /* Creating and resizing image in the center of monopoly board */
    ImageView monopolyCenter = new ImageView(new Image("Images/Monopoly.png")); monopolyCenter.setFitHeight(522); monopolyCenter.setFitWidth(522);
    
    /** Array of monopoly board images corresponding to their position on the board */
    final ImageView[][] boardImages = {{cuyahogaValley, battlefieldParksLeft, joshuaTree, campfireViolation, natureHiking, deathValley, historicSitesLeft, arches, 
      redwood}, {jail, bigBend, volcano, olympic, greatSmokyMountains, rockClimbing, everglades, battlefieldParksTop, carlsbadCaverns, 
      grandTeton, freeParking}, {acadia, historicSitesRight, bryceCanyon, zion, bicycling, grandCanyon, denali, geyser, rockyMountains}, 
      {go, yellowstone, purchaseAnnualPass, yosemite, historicSitesBottom, riverRafting, sequoia, battlefieldParksBottom, mountRainier, glacier, goToJail}};
    
    /* Creating the HBox's and VBox's to store each board piece image, Groups to store the boxes, and the border pane to store the groups */
    Group rootTop = new Group(); Group rootLeft = new Group(); Group rootBottom = new Group(); Group rootRight = new Group();
    HBox topBox = new HBox(); HBox bottomBox = new HBox();
    VBox leftBox = new VBox(); VBox rightBox = new VBox();
    VBox rulesAndDialogueBox = new VBox(); rulesAndDialogueBox.getChildren().add(getDialogueBox()); rulesAndDialogueBox.getChildren().add(linkToRules());
    BorderPane pane = new BorderPane(); BorderPane secondaryPane = new BorderPane(); buttonBox.setMinWidth(100);
    Scene scene = new Scene(pane);
    
    /* Assigning each board piece image to its respective HBox or VBox */
    leftBox.getChildren().add(redwood); leftBox.getChildren().add(arches); leftBox.getChildren().add(historicSitesLeft); 
    leftBox.getChildren().add(deathValley); leftBox.getChildren().add(natureHiking); leftBox.getChildren().add(campfireViolation); 
    leftBox.getChildren().add(joshuaTree); leftBox.getChildren().add(battlefieldParksLeft); leftBox.getChildren().add(cuyahogaValley);
    topBox.getChildren().add(jail); topBox.getChildren().add(bigBend); topBox.getChildren().add(volcano);
    topBox.getChildren().add(olympic); topBox.getChildren().add(greatSmokyMountains); topBox.getChildren().add(rockClimbing);
    topBox.getChildren().add(everglades); topBox.getChildren().add(battlefieldParksTop); topBox.getChildren().add(carlsbadCaverns);
    topBox.getChildren().add(grandTeton); topBox.getChildren().add(freeParking);
    rightBox.getChildren().add(acadia); rightBox.getChildren().add(historicSitesRight); rightBox.getChildren().add(bryceCanyon);
    rightBox.getChildren().add(zion); rightBox.getChildren().add(bicycling); rightBox.getChildren().add(grandCanyon);
    rightBox.getChildren().add(denali); rightBox.getChildren().add(geyser); rightBox.getChildren().add(rockyMountains);
    bottomBox.getChildren().add(player1And2Image(3, 0)); bottomBox.getChildren().add(yellowstone); bottomBox.getChildren().add(purchaseAnnualPass);
    bottomBox.getChildren().add(yosemite); bottomBox.getChildren().add(historicSitesBottom); bottomBox.getChildren().add(riverRafting);
    bottomBox.getChildren().add(sequoia); bottomBox.getChildren().add(battlefieldParksBottom); bottomBox.getChildren().add(mountRainier);
    bottomBox.getChildren().add(glacier); bottomBox.getChildren().add(goToJail);
    
    /* Initializes each button */
    initializeButtons(leftBox, topBox, rightBox, bottomBox, boardImages, primaryStage);
    
    /* assigning each HBox and VBox to a group */
    rootTop.getChildren().add(topBox);
    rootLeft.getChildren().add(leftBox);
    rootBottom.getChildren().add(bottomBox);
    rootRight.getChildren().add(rightBox);
    
    /* setting the border pane bottom, left, top, right, and center nodes to respective rows of monopoly board pieces or other panes consisting of the appropriate nodes */
    initializeScrollPane();
    getTertiaryPane().setTop(buttonBox); getTertiaryPane().setBottom(rulesAndDialogueBox);
    secondaryPane.setLeft(rootRight); secondaryPane.setCenter(getTertiaryPane());
    pane.setTop(rootTop); pane.setLeft(rootLeft); pane.setRight(secondaryPane); pane.setBottom(rootBottom); pane.setCenter(monopolyCenter);
    
    /* displaying the primary stage set to scene with border pane */
    primaryStage.setScene(scene);
    primaryStage.setX(0);
    primaryStage.setTitle("Monopoly - National Parks");
    primaryStage.show();
    
    /* telling the first player it is their turn */
    getDialogueBox().appendText("\n" + getPlayer().getName() + "'s turn");
    
  }
  
  /**
   * Creates the images of the board with the player 1 icon on them and creates an array corresponding to the images position on the Monopoly board.
   * Returns the image in the created array corresponding to the specified row and column.
   * @param row row of the specified P1 image
   * @param column column of the specified P1 image
   * @return the P1 image at the specified row and column
   */
  public ImageView player1Image(int row, int column) {
    /* Creating and resizing images on left of monopoly board */
    ImageView cuyahogaValleyP1 = new ImageView(new Image("Images/cuyahogaValleyP1.png")); cuyahogaValleyP1.setFitHeight(58); cuyahogaValleyP1.setFitWidth(100);
    ImageView battlefieldParksLeftP1 = new ImageView(new Image("Images/battleFieldParksLeftP1.png")); battlefieldParksLeftP1.setFitHeight(58);battlefieldParksLeftP1.setFitWidth(100);
    ImageView joshuaTreeP1 = new ImageView(new Image("Images/joshuaTreeP1.png")); joshuaTreeP1.setFitHeight(58); joshuaTreeP1.setFitWidth(100);
    ImageView campfireViolationP1 = new ImageView(new Image("Images/campfireViolationP1.png")); campfireViolationP1.setFitHeight(58); campfireViolationP1.setFitWidth(100);
    ImageView natureHikingP1 = new ImageView(new Image("Images/natureHikingP1.png")); natureHikingP1.setFitHeight(58); natureHikingP1.setFitWidth(100);
    ImageView deathValleyP1 = new ImageView(new Image("Images/deathValleyP1.png")); deathValleyP1.setFitHeight(58); deathValleyP1.setFitWidth(100);
    ImageView historicSitesLeftP1 = new ImageView(new Image("Images/historicSitesLeftP1.png")); historicSitesLeftP1.setFitHeight(58); historicSitesLeftP1.setFitWidth(100);
    ImageView archesP1 = new ImageView(new Image("Images/archesP1.png")); archesP1.setFitHeight(58); archesP1.setFitWidth(100);
    ImageView redwoodP1 = new ImageView(new Image("Images/redwoodP1.png")); redwoodP1.setFitHeight(58); redwoodP1.setFitWidth(100);
    /* Creating and resizing images on top of monopoly board */
    ImageView jailP1 = new ImageView(new Image("Images/jailP1.png")); jailP1.setFitHeight(100); jailP1.setFitWidth(100);
    ImageView bigBendP1 = new ImageView(new Image("Images/bigBendP1.png")); bigBendP1.setFitHeight(100); bigBendP1.setFitWidth(58);
    ImageView volcanoP1 = new ImageView(new Image("Images/volcanoP1.png")); volcanoP1.setFitHeight(100); volcanoP1.setFitWidth(58);
    ImageView olympicP1 = new ImageView(new Image("Images/olympicP1.png")); olympicP1.setFitHeight(100); olympicP1.setFitWidth(58);
    ImageView greatSmokyMountainsP1 = new ImageView(new Image("Images/greatSmokyMountainsP1.png")); greatSmokyMountainsP1.setFitHeight(100); greatSmokyMountainsP1.setFitWidth(58);
    ImageView rockClimbingP1 = new ImageView(new Image("Images/rockClimbingP1.png")); rockClimbingP1.setFitHeight(100); rockClimbingP1.setFitWidth(58);
    ImageView evergladesP1 = new ImageView(new Image("Images/evergladesP1.png")); evergladesP1.setFitHeight(100); evergladesP1.setFitWidth(58);
    ImageView battlefieldParksTopP1 = new ImageView(new Image("Images/battleFieldParksTopP1.png")); battlefieldParksTopP1.setFitHeight(100); battlefieldParksTopP1.setFitWidth(58);
    ImageView carlsbadCavernsP1 = new ImageView(new Image("Images/carlsbadCavernsP1.png")); carlsbadCavernsP1.setFitHeight(100); carlsbadCavernsP1.setFitWidth(58);
    ImageView grandTetonP1 = new ImageView(new Image("Images/grandTetonP1.png")); grandTetonP1.setFitHeight(100); grandTetonP1.setFitWidth(58);
    ImageView freeParkingP1 = new ImageView(new Image("Images/freeParkingP1.png")); freeParkingP1.setFitHeight(100); freeParkingP1.setFitWidth(100);
    /* Creating and resizing images on right of monopoly board */
    ImageView acadiaP1 = new ImageView(new Image("Images/acadiaP1.png")); acadiaP1.setFitHeight(58); acadiaP1.setFitWidth(100);
    ImageView historicSitesRightP1 = new ImageView(new Image("Images/historicSitesRightP1.png")); historicSitesRightP1.setFitHeight(58); historicSitesRightP1.setFitWidth(100);
    ImageView bryceCanyonP1 = new ImageView(new Image("Images/bryceCanyonP1.png")); bryceCanyonP1.setFitHeight(58); bryceCanyonP1.setFitWidth(100);
    ImageView zionP1 = new ImageView(new Image("Images/zionP1.png")); zionP1.setFitHeight(58); zionP1.setFitWidth(100);
    ImageView bicyclingP1 = new ImageView(new Image("Images/bicyclingP1.png")); bicyclingP1.setFitHeight(58); bicyclingP1.setFitWidth(100);
    ImageView grandCanyonP1 = new ImageView(new Image("Images/grandCanyonP1.png")); grandCanyonP1.setFitHeight(58); grandCanyonP1.setFitWidth(100);
    ImageView denaliP1 = new ImageView(new Image("Images/denaliP1.png")); denaliP1.setFitHeight(58); denaliP1.setFitWidth(100);
    ImageView geyserP1 = new ImageView(new Image("Images/geyserP1.png")); geyserP1.setFitHeight(58); geyserP1.setFitWidth(100);
    ImageView rockyMountainsP1 = new ImageView(new Image("Images/rockyMountainsP1.png")); rockyMountainsP1.setFitHeight(58); rockyMountainsP1.setFitWidth(100);
    /* Creating and resizing images on bottom of monopoly board */
    ImageView goToJailP1 = new ImageView(new Image("Images/goToJailP1.png")); goToJailP1.setFitHeight(100); goToJailP1.setFitWidth(100);
    ImageView glacierP1 = new ImageView(new Image("Images/glacierP1.png")); glacierP1.setFitHeight(100); glacierP1.setFitWidth(58);
    ImageView mountRainierP1 = new ImageView(new Image("Images/mountRainierP1.png")); mountRainierP1.setFitHeight(100); mountRainierP1.setFitWidth(58);
    ImageView battlefieldParksBotP1 = new ImageView(new Image("Images/battleFieldParksBotP1.png")); battlefieldParksBotP1.setFitHeight(100); battlefieldParksBotP1.setFitWidth(58);
    ImageView sequoiaP1 = new ImageView(new Image("Images/sequoiaP1.png")); sequoiaP1.setFitHeight(100); sequoiaP1.setFitWidth(58);
    ImageView riverRaftingP1 = new ImageView(new Image("Images/riverRaftingP1.png")); riverRaftingP1.setFitHeight(100); riverRaftingP1.setFitWidth(58);
    ImageView historicSitesBottomP1 = new ImageView(new Image("Images/historicSitesBotP1.png")); historicSitesBottomP1.setFitHeight(100); historicSitesBottomP1.setFitWidth(58);
    ImageView yosemiteP1 = new ImageView(new Image("Images/yosemiteP1.png")); yosemiteP1.setFitHeight(100); yosemiteP1.setFitWidth(58);
    ImageView purchaseAnnualPassP1 = new ImageView(new Image("Images/purchaseAnnualPassP1.png")); purchaseAnnualPassP1.setFitHeight(100); purchaseAnnualPassP1.setFitWidth(58);
    ImageView yellowstoneP1 = new ImageView(new Image("Images/yellowstoneP1.png")); yellowstoneP1.setFitHeight(100); yellowstoneP1.setFitWidth(58);
    ImageView goP1 = new ImageView(new Image("Images/GOP1.png")); goP1.setFitHeight(100); goP1.setFitWidth(100);
    /* Creating array of Player 1 images */
    ImageView[][] p1Images = {{cuyahogaValleyP1, battlefieldParksLeftP1, joshuaTreeP1, campfireViolationP1, natureHikingP1, deathValleyP1, historicSitesLeftP1, archesP1, 
      redwoodP1}, {jailP1, bigBendP1, volcanoP1, olympicP1, greatSmokyMountainsP1, rockClimbingP1, evergladesP1, battlefieldParksTopP1, carlsbadCavernsP1, 
      grandTetonP1, freeParkingP1}, {acadiaP1, historicSitesRightP1, bryceCanyonP1, zionP1, bicyclingP1, grandCanyonP1, denaliP1, geyserP1, rockyMountainsP1}, 
      {goP1, yellowstoneP1, purchaseAnnualPassP1, yosemiteP1, historicSitesBottomP1, riverRaftingP1, sequoiaP1, battlefieldParksBotP1, mountRainierP1, glacierP1, goToJailP1}};
    return p1Images[row][column];
  }
  
  /**
   * Creates the images of the board with the player 2 icon on them and creates an array corresponding to the images position on the monopoly board.
   * Returns the image in the created array corresponding to the specified row and column.
   * @param row row of the specified P2 image
   * @param column column of the specified P2 image
   * @return the P2 image at the specified row and column
   */
  public ImageView player2Image(int row, int column) {
    /* Creating and resizing images on left of monopoly board */
    ImageView cuyahogaValleyP2 = new ImageView(new Image("Images/cuyahogaValleyP2.png")); cuyahogaValleyP2.setFitHeight(58); cuyahogaValleyP2.setFitWidth(100);
    ImageView battlefieldParksLeftP2 = new ImageView(new Image("Images/battleFieldParksLeftP2.png")); battlefieldParksLeftP2.setFitHeight(58);battlefieldParksLeftP2.setFitWidth(100);
    ImageView joshuaTreeP2 = new ImageView(new Image("Images/joshuaTreeP2.png")); joshuaTreeP2.setFitHeight(58); joshuaTreeP2.setFitWidth(100);
    ImageView campfireViolationP2 = new ImageView(new Image("Images/campfireViolationP2.png")); campfireViolationP2.setFitHeight(58); campfireViolationP2.setFitWidth(100);
    ImageView natureHikingP2 = new ImageView(new Image("Images/natureHikingP2.png")); natureHikingP2.setFitHeight(58); natureHikingP2.setFitWidth(100);
    ImageView deathValleyP2 = new ImageView(new Image("Images/deathValleyP2.png")); deathValleyP2.setFitHeight(58); deathValleyP2.setFitWidth(100);
    ImageView historicSitesLeftP2 = new ImageView(new Image("Images/historicSitesLeftP2.png")); historicSitesLeftP2.setFitHeight(58); historicSitesLeftP2.setFitWidth(100);
    ImageView archesP2 = new ImageView(new Image("Images/archesP2.png")); archesP2.setFitHeight(58); archesP2.setFitWidth(100);
    ImageView redwoodP2 = new ImageView(new Image("Images/redwoodP2.png")); redwoodP2.setFitHeight(58); redwoodP2.setFitWidth(100);
    /* Creating and resizing images on top of monopoly board */
    ImageView jailP2 = new ImageView(new Image("Images/jailP2.png")); jailP2.setFitHeight(100); jailP2.setFitWidth(100);
    ImageView bigBendP2 = new ImageView(new Image("Images/bigBendP2.png")); bigBendP2.setFitHeight(100); bigBendP2.setFitWidth(58);
    ImageView volcanoP2 = new ImageView(new Image("Images/volcanoP2.png")); volcanoP2.setFitHeight(100); volcanoP2.setFitWidth(58);
    ImageView olympicP2 = new ImageView(new Image("Images/olympicP2.png")); olympicP2.setFitHeight(100); olympicP2.setFitWidth(58);
    ImageView greatSmokyMountainsP2 = new ImageView(new Image("Images/greatSmokyMountainsP2.png")); greatSmokyMountainsP2.setFitHeight(100); greatSmokyMountainsP2.setFitWidth(58);
    ImageView rockClimbingP2 = new ImageView(new Image("Images/rockClimbingP2.png")); rockClimbingP2.setFitHeight(100); rockClimbingP2.setFitWidth(58);
    ImageView evergladesP2 = new ImageView(new Image("Images/evergladesP2.png")); evergladesP2.setFitHeight(100); evergladesP2.setFitWidth(58);
    ImageView battlefieldParksTopP2 = new ImageView(new Image("Images/battleFieldParksTopP2.png")); battlefieldParksTopP2.setFitHeight(100); battlefieldParksTopP2.setFitWidth(58);
    ImageView carlsbadCavernsP2 = new ImageView(new Image("Images/carlsbadCavernsP2.png")); carlsbadCavernsP2.setFitHeight(100); carlsbadCavernsP2.setFitWidth(58);
    ImageView grandTetonP2 = new ImageView(new Image("Images/grandTetonP2.png")); grandTetonP2.setFitHeight(100); grandTetonP2.setFitWidth(58);
    ImageView freeParkingP2 = new ImageView(new Image("Images/freeParkingP2.png")); freeParkingP2.setFitHeight(100); freeParkingP2.setFitWidth(100);
    /* Creating and resizing images on right of monopoly board */
    ImageView acadiaP2 = new ImageView(new Image("Images/acadiaP2.png")); acadiaP2.setFitHeight(58); acadiaP2.setFitWidth(100);
    ImageView historicSitesRightP2 = new ImageView(new Image("Images/historicSitesRightP2.png")); historicSitesRightP2.setFitHeight(58); historicSitesRightP2.setFitWidth(100);
    ImageView bryceCanyonP2 = new ImageView(new Image("Images/bryceCanyonP2.png")); bryceCanyonP2.setFitHeight(58); bryceCanyonP2.setFitWidth(100);
    ImageView zionP2 = new ImageView(new Image("Images/zionP2.png")); zionP2.setFitHeight(58); zionP2.setFitWidth(100);
    ImageView bicyclingP2 = new ImageView(new Image("Images/bicyclingP2.png")); bicyclingP2.setFitHeight(58); bicyclingP2.setFitWidth(100);
    ImageView grandCanyonP2 = new ImageView(new Image("Images/grandCanyonP2.png")); grandCanyonP2.setFitHeight(58); grandCanyonP2.setFitWidth(100);
    ImageView denaliP2 = new ImageView(new Image("Images/denaliP2.png")); denaliP2.setFitHeight(58); denaliP2.setFitWidth(100);
    ImageView geyserP2 = new ImageView(new Image("Images/geyserP2.png")); geyserP2.setFitHeight(58); geyserP2.setFitWidth(100);
    ImageView rockyMountainsP2 = new ImageView(new Image("Images/rockyMountainsP2.png")); rockyMountainsP2.setFitHeight(58); rockyMountainsP2.setFitWidth(100);
    /* Creating and resizing images on bottom of monopoly board */
    ImageView goToJailP2 = new ImageView(new Image("Images/goToJailP2.png")); goToJailP2.setFitHeight(100); goToJailP2.setFitWidth(100);
    ImageView glacierP2 = new ImageView(new Image("Images/glacierP2.png")); glacierP2.setFitHeight(100); glacierP2.setFitWidth(58);
    ImageView mountRainierP2 = new ImageView(new Image("Images/mountRainierP2.png")); mountRainierP2.setFitHeight(100); mountRainierP2.setFitWidth(58);
    ImageView battlefieldParksBotP2 = new ImageView(new Image("Images/battleFieldParksBotP2.png")); battlefieldParksBotP2.setFitHeight(100); battlefieldParksBotP2.setFitWidth(58);
    ImageView sequoiaP2 = new ImageView(new Image("Images/sequoiaP2.png")); sequoiaP2.setFitHeight(100); sequoiaP2.setFitWidth(58);
    ImageView riverRaftingP2 = new ImageView(new Image("Images/riverRaftingP2.png")); riverRaftingP2.setFitHeight(100); riverRaftingP2.setFitWidth(58);
    ImageView historicSitesBottomP2 = new ImageView(new Image("Images/historicSitesBotP2.png")); historicSitesBottomP2.setFitHeight(100); historicSitesBottomP2.setFitWidth(58);
    ImageView yosemiteP2 = new ImageView(new Image("Images/yosemiteP2.png")); yosemiteP2.setFitHeight(100); yosemiteP2.setFitWidth(58);
    ImageView purchaseAnnualPassP2 = new ImageView(new Image("Images/purchaseAnnualPassP2.png")); purchaseAnnualPassP2.setFitHeight(100); purchaseAnnualPassP2.setFitWidth(58);
    ImageView yellowstoneP2 = new ImageView(new Image("Images/yellowstoneP2.png")); yellowstoneP2.setFitHeight(100); yellowstoneP2.setFitWidth(58);
    ImageView goP2 = new ImageView(new Image("Images/GOP2.png")); goP2.setFitHeight(100); goP2.setFitWidth(100);
    /* Creating array of Player 2 images */
    ImageView[][] p2Images = {{cuyahogaValleyP2, battlefieldParksLeftP2, joshuaTreeP2, campfireViolationP2, natureHikingP2, deathValleyP2, historicSitesLeftP2, archesP2, 
      redwoodP2}, {jailP2, bigBendP2, volcanoP2, olympicP2, greatSmokyMountainsP2, rockClimbingP2, evergladesP2, battlefieldParksTopP2, carlsbadCavernsP2, 
      grandTetonP2, freeParkingP2}, {acadiaP2, historicSitesRightP2, bryceCanyonP2, zionP2, bicyclingP2, grandCanyonP2, denaliP2, geyserP2, rockyMountainsP2}, 
      {goP2, yellowstoneP2, purchaseAnnualPassP2, yosemiteP2, historicSitesBottomP2, riverRaftingP2, sequoiaP2, battlefieldParksBotP2, mountRainierP2, glacierP2, goToJailP2}};
    return p2Images[row][column];
  }
  
  /**
   * Creates the images of the board with the player 1 and 2 icons on them and creates an array corresponding to the images position on the Monopoly board.
   * Returns the image in the created array corresponding to the specified row and column.
   * @param row row of the specified P12 image
   * @param column column of the specified P12 image
   * @return the P12 image at the specified row and column
   */
  public ImageView player1And2Image(int row, int column) {
    /* Creating and resizing images on left of monopoly board */
    ImageView cuyahogaValleyP12 = new ImageView(new Image("Images/cuyahogaValleyP12.png")); cuyahogaValleyP12.setFitHeight(58); cuyahogaValleyP12.setFitWidth(100);
    ImageView battlefieldParksLeftP12=new ImageView(new Image("Images/battleFieldParksLeftP12.png"));battlefieldParksLeftP12.setFitHeight(58);battlefieldParksLeftP12.setFitWidth(100);
    ImageView joshuaTreeP12 = new ImageView(new Image("Images/joshuaTreeP12.png")); joshuaTreeP12.setFitHeight(58); joshuaTreeP12.setFitWidth(100);
    ImageView campfireViolationP12 = new ImageView(new Image("Images/campfireViolationP12.png")); campfireViolationP12.setFitHeight(58); campfireViolationP12.setFitWidth(100);
    ImageView natureHikingP12 = new ImageView(new Image("Images/natureHikingP12.png")); natureHikingP12.setFitHeight(58); natureHikingP12.setFitWidth(100);
    ImageView deathValleyP12 = new ImageView(new Image("Images/deathValleyP12.png")); deathValleyP12.setFitHeight(58); deathValleyP12.setFitWidth(100);
    ImageView historicSitesLeftP12 = new ImageView(new Image("Images/historicSitesLeftP12.png")); historicSitesLeftP12.setFitHeight(58); historicSitesLeftP12.setFitWidth(100);
    ImageView archesP12 = new ImageView(new Image("Images/archesP12.png")); archesP12.setFitHeight(58); archesP12.setFitWidth(100);
    ImageView redwoodP12 = new ImageView(new Image("Images/redwoodP12.png")); redwoodP12.setFitHeight(58); redwoodP12.setFitWidth(100);
    /* Creating and resizing images on top of monopoly board */
    ImageView jailP12 = new ImageView(new Image("Images/jailP12.png")); jailP12.setFitHeight(100); jailP12.setFitWidth(100);
    ImageView bigBendP12 = new ImageView(new Image("Images/bigBendP12.png")); bigBendP12.setFitHeight(100); bigBendP12.setFitWidth(58);
    ImageView volcanoP12 = new ImageView(new Image("Images/volcanoP12.png")); volcanoP12.setFitHeight(100); volcanoP12.setFitWidth(58);
    ImageView olympicP12 = new ImageView(new Image("Images/olympicP12.png")); olympicP12.setFitHeight(100); olympicP12.setFitWidth(58);
    ImageView greatSmokyMountainsP12 = new ImageView(new Image("Images/greatSmokyMountainsP12.png")); greatSmokyMountainsP12.setFitHeight(100); greatSmokyMountainsP12.setFitWidth(58);
    ImageView rockClimbingP12 = new ImageView(new Image("Images/rockClimbingP12.png")); rockClimbingP12.setFitHeight(100); rockClimbingP12.setFitWidth(58);
    ImageView evergladesP12 = new ImageView(new Image("Images/evergladesP12.png")); evergladesP12.setFitHeight(100); evergladesP12.setFitWidth(58);
    ImageView battlefieldParksTopP12 = new ImageView(new Image("Images/battleFieldParksTopP12.png")); battlefieldParksTopP12.setFitHeight(100); battlefieldParksTopP12.setFitWidth(58);
    ImageView carlsbadCavernsP12 = new ImageView(new Image("Images/carlsbadCavernsP12.png")); carlsbadCavernsP12.setFitHeight(100); carlsbadCavernsP12.setFitWidth(58);
    ImageView grandTetonP12 = new ImageView(new Image("Images/grandTetonP12.png")); grandTetonP12.setFitHeight(100); grandTetonP12.setFitWidth(58);
    ImageView freeParkingP12 = new ImageView(new Image("Images/freeParkingP12.png")); freeParkingP12.setFitHeight(100); freeParkingP12.setFitWidth(100);
    /* Creating and resizing images on right of monopoly board */
    ImageView acadiaP12 = new ImageView(new Image("Images/acadiaP12.png")); acadiaP12.setFitHeight(58); acadiaP12.setFitWidth(100);
    ImageView historicSitesRightP12 = new ImageView(new Image("Images/historicSitesRightP12.png")); historicSitesRightP12.setFitHeight(58); historicSitesRightP12.setFitWidth(100);
    ImageView bryceCanyonP12 = new ImageView(new Image("Images/bryceCanyonP12.png")); bryceCanyonP12.setFitHeight(58); bryceCanyonP12.setFitWidth(100);
    ImageView zionP12 = new ImageView(new Image("Images/zionP12.png")); zionP12.setFitHeight(58); zionP12.setFitWidth(100);
    ImageView bicyclingP12 = new ImageView(new Image("Images/bicyclingP12.png")); bicyclingP12.setFitHeight(58); bicyclingP12.setFitWidth(100);
    ImageView grandCanyonP12 = new ImageView(new Image("Images/grandCanyonP12.png")); grandCanyonP12.setFitHeight(58); grandCanyonP12.setFitWidth(100);
    ImageView denaliP12 = new ImageView(new Image("Images/denaliP12.png")); denaliP12.setFitHeight(58); denaliP12.setFitWidth(100);
    ImageView geyserP12 = new ImageView(new Image("Images/geyserP12.png")); geyserP12.setFitHeight(58); geyserP12.setFitWidth(100);
    ImageView rockyMountainsP12 = new ImageView(new Image("Images/rockyMountainsP12.png")); rockyMountainsP12.setFitHeight(58); rockyMountainsP12.setFitWidth(100);
    /* Creating and resizing images on bottom of monopoly board */
    ImageView goToJailP12 = new ImageView(new Image("Images/goToJailP12.png")); goToJailP12.setFitHeight(100); goToJailP12.setFitWidth(100);
    ImageView glacierP12 = new ImageView(new Image("Images/glacierP12.png")); glacierP12.setFitHeight(100); glacierP12.setFitWidth(58);
    ImageView mountRainierP12 = new ImageView(new Image("Images/mountRainierP12.png")); mountRainierP12.setFitHeight(100); mountRainierP12.setFitWidth(58);
    ImageView battlefieldParksBotP12 =new ImageView(new Image("Images/battleFieldParksBotP12.png"));battlefieldParksBotP12.setFitHeight(100);battlefieldParksBotP12.setFitWidth(58);
    ImageView sequoiaP12 = new ImageView(new Image("Images/sequoiaP12.png")); sequoiaP12.setFitHeight(100); sequoiaP12.setFitWidth(58);
    ImageView riverRaftingP12 = new ImageView(new Image("Images/riverRaftingP12.png")); riverRaftingP12.setFitHeight(100); riverRaftingP12.setFitWidth(58);
    ImageView historicSitesBottomP12 = new ImageView(new Image("Images/historicSitesBotP12.png")); historicSitesBottomP12.setFitHeight(100); historicSitesBottomP12.setFitWidth(58);
    ImageView yosemiteP12 = new ImageView(new Image("Images/yosemiteP12.png")); yosemiteP12.setFitHeight(100); yosemiteP12.setFitWidth(58);
    ImageView purchaseAnnualPassP12 = new ImageView(new Image("Images/purchaseAnnualPassP12.png")); purchaseAnnualPassP12.setFitHeight(100); purchaseAnnualPassP12.setFitWidth(58);
    ImageView yellowstoneP12 = new ImageView(new Image("Images/yellowstoneP12.png")); yellowstoneP12.setFitHeight(100); yellowstoneP12.setFitWidth(58);
    ImageView goP12 = new ImageView(new Image("Images/GOP12.png")); goP12.setFitHeight(100); goP12.setFitWidth(100);
    /* Creating array of Player 2 images */
    ImageView[][] p12Images = {{cuyahogaValleyP12, battlefieldParksLeftP12, joshuaTreeP12, campfireViolationP12, natureHikingP12, deathValleyP12, historicSitesLeftP12, 
      archesP12, redwoodP12}, {jailP12, bigBendP12, volcanoP12, olympicP12, greatSmokyMountainsP12, rockClimbingP12, evergladesP12, battlefieldParksTopP12, carlsbadCavernsP12, 
      grandTetonP12, freeParkingP12}, {acadiaP12, historicSitesRightP12, bryceCanyonP12, zionP12, bicyclingP12, grandCanyonP12, denaliP12, geyserP12, rockyMountainsP12}, 
      {goP12, yellowstoneP12, purchaseAnnualPassP12, yosemiteP12, historicSitesBottomP12, riverRaftingP12, sequoiaP12, battlefieldParksBotP12, mountRainierP12, glacierP12, 
        goToJailP12}};
    return p12Images[row][column];
  }
  
  /**
   * Creates an array of images containing the possible combinations of each player in jail / visiting jail
   * @param index the index of the array at which to return the image
   */
  public ImageView inJailImage(int index) {
    ImageView inJailP1 = new ImageView(new Image("inJailP1.png")); inJailP1.setFitHeight(100); inJailP1.setFitWidth(100);
    ImageView inJailP2 = new ImageView(new Image("inJailP2.png")); inJailP2.setFitHeight(100); inJailP2.setFitWidth(100);
    ImageView inJailP12 = new ImageView(new Image("inJailP12.png")); inJailP12.setFitHeight(100); inJailP12.setFitWidth(100);
    ImageView jailP1InJailP2 = new ImageView(new Image("jailP1InJailP2.png")); jailP1InJailP2.setFitHeight(100); jailP1InJailP2.setFitWidth(100);
    ImageView jailP2InJailP1 = new ImageView(new Image("jailP2InJailP1.png")); jailP2InJailP1.setFitHeight(100); jailP2InJailP1.setFitWidth(100);
    ImageView[] inJailImages = {inJailP1, inJailP2, inJailP12, jailP1InJailP2, jailP2InJailP1};
    return inJailImages[index];
  }
  
  /**
   * Changes the image at the players current position to the corresponding image on the monopoly board with no player tokens on it
   * @param leftBox the VBox containing monopoly board images on the left side of the board
   * @param topBox the HBox containing monopoly board images on the top of the board
   * @param rightBox the VBox containing monopoly board images on the right side of the board
   * @param bottomBox the HBox containing monopoly board images on the bottom of the board
   * @param boardImages the array of original monopoly board images with no player tokens on them
   */
  public void changeInitialImage(VBox leftBox, HBox topBox, VBox rightBox, HBox bottomBox, ImageView[][] boardImages) {
    if (getPlayer().getPosition()[0] == 0) { //if player is in the first row of positions change the image to the corresponding image of the monopoly board
      leftBox.getChildren().set(8 - getPlayer().getPosition()[1], boardImages[getPlayer().getPosition()[0]][getPlayer().getPosition()[1]]);
    }
    else if (getPlayer().getPosition()[0] == 1) { //if player is in the second row of positions change the image to the corresponding image of the monopoly board
      topBox.getChildren().set(getPlayer().getPosition()[1], boardImages[getPlayer().getPosition()[0]][getPlayer().getPosition()[1]]);
    }
    else if (getPlayer().getPosition()[0] == 2) { //if player is in the third row of positions change the image to the corresponding image of the monopoly board
      rightBox.getChildren().set(getPlayer().getPosition()[1], boardImages[getPlayer().getPosition()[0]][getPlayer().getPosition()[1]]);
    }
    else if (getPlayer().getPosition()[0] == 3) { //if player is in the fourth row of positions change the image to the corresponding image of the monopoly board
      bottomBox.getChildren().set(10 - getPlayer().getPosition()[1], boardImages[getPlayer().getPosition()[0]][10 - getPlayer().getPosition()[1]]);
    }
  }
  
  /**
   * Changes the image at the players current position to the corresponding image on the monopoly board with the player 1 token on it
   * @param leftBox the VBox containing monopoly board images on the left side of the board
   * @param topBox the HBox containing monopoly board images on the top of the board
   * @param rightBox the VBox containing monopoly board images on the right side of the board
   * @param bottomBox the HBox containing monopoly board images on the bottom of the board
   */
  public void changePlayer1Image(VBox leftBox, HBox topBox, VBox rightBox, HBox bottomBox) {
    if (getPlayer().getPosition()[0] == 0) { //if player is in the first row of positions change the image to the corresponding image of the monopoly board
      leftBox.getChildren().set(8 - getPlayer().getPosition()[1], player1Image(getPlayer().getPosition()[0], getPlayer().getPosition()[1]));
    }
    else if (getPlayer().getPosition()[0] == 1) { //if player is in the second row of positions change the image to the corresponding image of the monopoly board
      topBox.getChildren().set(getPlayer().getPosition()[1], player1Image(getPlayer().getPosition()[0], getPlayer().getPosition()[1]));
    }
    else if (getPlayer().getPosition()[0] == 2) { //if player is in the third row of positions change the image to the corresponding image of the monopoly board
      rightBox.getChildren().set(getPlayer().getPosition()[1], player1Image(getPlayer().getPosition()[0], getPlayer().getPosition()[1]));
    }
    else if (getPlayer().getPosition()[0] == 3) { //if player is in the fourth row of positions change the image to the corresponding image of the monopoly board
      bottomBox.getChildren().set(10 - getPlayer().getPosition()[1], player1Image(getPlayer().getPosition()[0], 10 - getPlayer().getPosition()[1]));
    }
  }
  
  /**
   * Changes the image at the players current position to the corresponding image on the monopoly board with the player 2 token on it
   * @param leftBox the VBox containing monopoly board images on the left side of the board
   * @param topBox the HBox containing monopoly board images on the top of the board
   * @param rightBox the VBox containing monopoly board images on the right side of the board
   * @param bottomBox the HBox containing monopoly board images on the bottom of the board
   */
  public void changePlayer2Image(VBox leftBox, HBox topBox, VBox rightBox, HBox bottomBox) {
    if (getPlayer().getPosition()[0] == 0) { //if player is in the first row of positions change the image to the corresponding image of the monopoly board
      leftBox.getChildren().set(8 - getPlayer().getPosition()[1], player2Image(getPlayer().getPosition()[0], getPlayer().getPosition()[1]));
    }
    else if (getPlayer().getPosition()[0] == 1) { //if player is in the second row of positions change the image to the corresponding image of the monopoly board
      topBox.getChildren().set(getPlayer().getPosition()[1], player2Image(getPlayer().getPosition()[0], getPlayer().getPosition()[1]));
    }
    else if (getPlayer().getPosition()[0] == 2) { //if player is in the third row of positions change the image to the corresponding image of the monopoly board
      rightBox.getChildren().set(getPlayer().getPosition()[1], player2Image(getPlayer().getPosition()[0], getPlayer().getPosition()[1]));
    }
    else if (getPlayer().getPosition()[0] == 3) { //if player is in the fourth row of positions change the image to the corresponding image of the monopoly board
      bottomBox.getChildren().set(10 - getPlayer().getPosition()[1], player2Image(getPlayer().getPosition()[0], 10 - getPlayer().getPosition()[1]));
    }
  }
  
  /**
   * Changes the image at the players current position to the corresponding image on the monopoly board with the player 1 and 2 tokens on it
   * @param leftBox the VBox containing monopoly board images on the left side of the board
   * @param topBox the HBox containing monopoly board images on the top of the board
   * @param rightBox the VBox containing monopoly board images on the right side of the board
   * @param bottomBox the HBox containing monopoly board images on the bottom of the board
   */
  public void changePlayer1And2Image(VBox leftBox, HBox topBox, VBox rightBox, HBox bottomBox) {
    if (getPlayer().getPosition()[0] == 0) { //if player is in the first row of positions change the image to the corresponding image of the monopoly board
      leftBox.getChildren().set(8 - getPlayer().getPosition()[1], player1And2Image(getPlayer().getPosition()[0], getPlayer().getPosition()[1]));
    }
    else if (getPlayer().getPosition()[0] == 1) { //if player is in the second row of positions change the image to the corresponding image of the monopoly board
      topBox.getChildren().set(getPlayer().getPosition()[1], player1And2Image(getPlayer().getPosition()[0], getPlayer().getPosition()[1]));
    }
    else if (getPlayer().getPosition()[0] == 2) { //if player is in the third row of positions change the image to the corresponding image of the monopoly board
      rightBox.getChildren().set(getPlayer().getPosition()[1], player1And2Image(getPlayer().getPosition()[0], getPlayer().getPosition()[1]));
    }
    else if (getPlayer().getPosition()[0] == 3) { //if player is in the fourth row of positions change the image to the corresponding image of the monopoly board
      bottomBox.getChildren().set(10 - getPlayer().getPosition()[1], player1And2Image(getPlayer().getPosition()[0], 10 - getPlayer().getPosition()[1]));
    }
  }
  
  /**
   * If the current player is in or visiting jail, removes their token from the jail board image
   * @param leftBox the VBox containing monopoly board images on the left side of the board
   * @param topBox the HBox containing monopoly board images on the top of the board
   * @param rightBox the VBox containing monopoly board images on the right side of the board
   * @param bottomBox the HBox containing monopoly board images on the bottom of the board
   * @param boardImages array of original monopoly board images
   */
  public void removePlayerImageFromJail(VBox leftBox, HBox topBox, VBox rightBox, HBox bottomBox, ImageView[][] boardImages) {
    /* if the next player is in jail */
    if (getNextPlayer().getInJail()) {
      if (getPlayer().equals(player1)) //if the current player is player 1
        topBox.getChildren().set(0, inJailImage(1));
      else //if the current player is player 2
        topBox.getChildren().set(0, inJailImage(0));
    }
    /* if the next player is visiting jail */
    else if (getNextPlayer().visitingJail()) {
      if (getPlayer().equals(player1)) //if the current player is player 1
        changePlayer2Image(leftBox, topBox, rightBox, bottomBox);
      else //if the current player is player 2
        changePlayer1Image(leftBox, topBox, rightBox, bottomBox);
    }
    /* if the next player is not in or visiting jail */
    else {
      changeInitialImage(leftBox, topBox, rightBox, bottomBox, boardImages);
    }
  }
  
  /**
   * If the current player is in or visiting jail, adds their token to the jail board image
   * @param leftBox the VBox containing monopoly board images on the left side of the board
   * @param topBox the HBox containing monopoly board images on the top of the board
   * @param rightBox the VBox containing monopoly board images on the right side of the board
   * @param bottomBox the HBox containing monopoly board images on the bottom of the board
   */
  public void addPlayerImageToJail(VBox leftBox, HBox topBox, VBox rightBox, HBox bottomBox) {
    /* if the current player is visiting jail and the next player is in jail */
    if (getPlayer().visitingJail() && getNextPlayer().getInJail()) {
      if (getPlayer().equals(player1)) //if the current player is player 1
        topBox.getChildren().set(0, inJailImage(3));
     else //if the current player is player 2
        topBox.getChildren().set(0, inJailImage(4));;
    }
    /* if the current player is visiting jail and the next player is visiting jail */
    else if (getPlayer().visitingJail() && getNextPlayer().visitingJail()) {
      changePlayer1And2Image(leftBox, topBox, rightBox, bottomBox);
    }
    /* if the current player is visiting jail and the next player is not visiting or in jail */
    else if (getPlayer().visitingJail()) {
      if (getPlayer().equals(player1)) //if the current player is player 1
        changePlayer1Image(leftBox, topBox, rightBox, bottomBox);
      else //if the current player is player 2
        changePlayer2Image(leftBox, topBox, rightBox, bottomBox);
    }
    /* if the current player is in jail and the next player is in jail */
    else if (getPlayer().getInJail() && getNextPlayer().getInJail()) {
      topBox.getChildren().set(0, inJailImage(2));
    }
    /* if the current player is in jail and the next player is visiting jail */
    else if (getPlayer().getInJail() && getNextPlayer().visitingJail()) {
      if (getPlayer().equals(player1)) //if the current player is player 1
        topBox.getChildren().set(0, inJailImage(4));
      else //if the current player is player 2
        topBox.getChildren().set(0, inJailImage(3));
    }
    /* if the current player is in jail and the next player is not in or visiting jail */
    else if (getPlayer().getInJail()) {
      if (getPlayer().equals(player1)) //if the current player is player 1
        topBox.getChildren().set(0, inJailImage(0));
      else //if the current player is player 2
        topBox.getChildren().set(0, inJailImage(1));
    }
  }
  
  /**
   * Creates the button that rolls the dice for the player, handles their position change, and moves their player piece on the board
   * @param leftBox the VBox containing monopoly board images on the left side of the board
   * @param topBox the HBox containing monopoly board images on the top of the board
   * @param rightBox the VBox containing monopoly board images on the right side of the board
   * @param bottomBox the HBox containing monopoly board images on the bottom of the board
   * @param boardImages array of original monopoly board images
   */
  public void rollButton(VBox leftBox, HBox topBox, VBox rightBox, HBox bottomBox, ImageView[][] boardImages) {
      
      /* Creating button that rolls dice and moves the player whos turn it is */
    final Button rollButton = new Button("Roll!");
    rollButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e)  {
        /* if the player has not already rolled this turn */
        if (getRolled() == false) {
          getTertiaryPane().setCenter(null);
          int diceOne = rollDice();
          int diceTwo = rollDice();
          setDiceRoll(diceOne + diceTwo); //sets the value for the number of spots for the player to move
          boolean jailImageChange = false; //indicates whether or not the current player has undergone the initial image change in or visiting jail
          
          /* 
           * The player position change is handled as such:
           * Step 1: the current players token is removed from the board by replacing the current image where the player is located by the same image without the players token
           * Step 2: if the player is in jail, it is handled according to the game rules
           * Step 3: if the player is not in jail, they move forward the given number of spaces and their position is handled according to what spot they landed on
           * Step 4: the current players token is added to the board at their new location by replacing the current image where the player is by the same image with the players
           *         token on it
           */
          
          /* Step 1: if the current player is visiting or in jail remove their token from the board */
          if (getPlayer().visitingJail() || getPlayer().getInJail()) {
            removePlayerImageFromJail(leftBox, topBox, rightBox, bottomBox, boardImages);
            jailImageChange = true;
          }
          /* Step 2: if the current player is in jail proceed according to the rules */
          if (getPlayer().getInJail()) {
            handleJail(diceOne, diceTwo);
            setRolled(true);
          }
          /* Step 3: if the player is not in jail */
          if (!getPlayer().getInJail()) {
            /* if the players image has not already undergone the initial image change */
            if (!jailImageChange) {
              /* if the players are on the same spot */
              if ((player1.getPosition()[0]) == ((player2.getPosition()[0])) && (player1.getPosition()[1]) == ((player2.getPosition()[1]))) {
                /* if current player is player 1 remove their token from the spot */
                if (getPlayer().equals(player1))
                    changePlayer2Image(leftBox, topBox, rightBox, bottomBox);
                /* if the current player is player 2 remove their token from the spot */
                else if (getPlayer().equals(player2))
                    changePlayer1Image(leftBox, topBox, rightBox, bottomBox);
              }
              else {
                /* if current player is player 1 remove their token from the spot */
                if (getPlayer().equals(player1))
                  changeInitialImage(leftBox, topBox, rightBox, bottomBox, boardImages);
                /* if current player is player 2 remove their token from the spot */
                if (getPlayer().equals(player2))
                  changeInitialImage(leftBox, topBox, rightBox, bottomBox, boardImages);
              }
            }
            getPlayer().moveForwardSpaces(getDiceRoll(), getDialogueBox()); //moves the players position
            setCurrentBoardSpot(getBoard()[getPlayer().getPosition()[0]][getPlayer().getPosition()[1]]); //sets the current board spot occupied by the player
            getPlayer().setCurrentBoardSpot(getCurrentBoardSpot()); //sets the current board spot occupied by the player
            getDialogueBox().appendText("\nYou rolled a " + getDiceRoll());
            try {
              /* determines what to do depending on where the player is */
              handlePosition();
            }
            catch (IllegalAccessException exception1) { }
            catch (InvocationTargetException exception2) { }
            /* Step 4: if the current player is not visiting or in jail */
            if (!getPlayer().getInJail() && !getPlayer().visitingJail()) {
              /* if the players are on the same spot on the board change to the corresponding image */
              if ((player1.getPosition()[0]) == ((player2.getPosition()[0])) && (player1.getPosition()[1]) == ((player2.getPosition()[1])))
                changePlayer1And2Image(leftBox, topBox, rightBox, bottomBox);
              else {
                /* if the current player is player 1 add their token to the board */
                if (getPlayer().equals(player1))
                  changePlayer1Image(leftBox, topBox, rightBox, bottomBox);
                /* if the current player is player 1 add their token to the board */
                else if (getPlayer().equals(player2))
                  changePlayer2Image(leftBox, topBox, rightBox, bottomBox);
              }
            }
            setRolled(true);
          }
          /* if the playeris visiting or in jail change the image accordingly */
          if (getPlayer().visitingJail() || getPlayer().getInJail()) {
            addPlayerImageToJail(leftBox, topBox, rightBox, bottomBox);
          }
        }
        else
          getDialogueBox().appendText("\nYou already rolled this turn");
      }
    });
    buttonBox.getChildren().add(rollButton); //displaying rollButton next to the monopoly board
  }
  
  /*
   * Creates a button that allows the player to buy the property they are currently on
   */
  public void buyPropertyButton() {
    Button buyPropertyButton = new Button("Buy property");
    buyPropertyButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) { //when the button is clicked
        if (getCurrentBoardSpot() instanceof Property) { //if the spot they are on is a property
          Property property = (Property)(getCurrentBoardSpot());
          if (!property.getOwnership() && getPlayer().getBalance() >= property.getPrice()) { //if the property is unowned and the player has enough money to buy it
            getPlayer().addProperty(property);
            getPlayer().subtractBalance(property.getPrice());
            property.setOwner(getPlayer());
            property.setOwnership(true);
            getDialogueBox().appendText("\nYou bought " + property.getName() + " for $" + property.getPrice());
          }
          else if (property.getOwnership()) { //if the property is already owned
            if (property.getOwner().equals(getPlayer())) //if the current player owns the property
              getDialogueBox().appendText("\nYou own this property");
            else //if someone else owns the property
              getDialogueBox().appendText("\n" + property.getOwner().getName() + " owns this property");
          }
          else if (getPlayer().getBalance() < property.getPrice()) //if the player does not have enough money to buy the property
            getDialogueBox().appendText("\nYou do not have enough money to buy this property");
        }
        else //if the spot the player is on is not a property
          getDialogueBox().appendText("\nThis is not a property");
      }
    });
    getButtonBox().getChildren().add(buyPropertyButton); //add this button to the box of buttons
  }
  
  /*
   * Creates a button to show the current player's properties
   */
  public void getPropertiesOwned() {
    Button getPropertiesOwnedButton = new Button("My Properties");
    getPropertiesOwnedButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) { //when the button is clicked
        getDialogueBox().appendText("\n" + getPlayer().getName() + ": " + getPlayer().getPropertiesOwnedNamesAndTents()); //shows the players current properties
      }
    });
    buttonBox.getChildren().add(getPropertiesOwnedButton); //add this button to the box of buttons
  }
  
  /*
   * Creates a button to show the current players balance
   */
  public void getMyBalance() {
    Button getMyBalanceButton = new Button("My Balance");
    getMyBalanceButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) { //when the button is clicked
        getDialogueBox().appendText("\n" + getPlayer().getName() + ": " + getPlayer().getBalance()); //shows the players current balance
      }
    });
    buttonBox.getChildren().add(getMyBalanceButton); //add this button to the box of buttons
  }
  
  /**
   * Creates a button to buy a tent/ranger station for a property
   */
  public void buyTent() {
    Button buyTentButton = new Button("Buy tents or ranger stations");
    buyTentButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) { //when the button is clicked
        TextField text = new TextField();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Property you would like to buy a tent for: "), 0, 0);
        grid.add(text, 1, 0);
        getTertiaryPane().setCenter(grid);
        /* specifies what to do when the player eneters the name of a property they would like to buy a tent or ranger station for */
        text.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent e) {
            boolean owned = false; //if the player owns the property
            boolean isProperty = false; //if the name entered is not a property
            for (Property property : getPlayer().getPropertiesOwned()) {
              if (property.getName().equals(text.getText())) { //if the player owns the property
                owned = true;
                isProperty = true;
                /* if the property entered is a Natural Features or Activities property */
                if (property.getType().equals("Natural Features") || property.getType().equals("Activities")) {
                  getDialogueBox().appendText("\nYou can not buy tents for this type of property");
                  getTertiaryPane().setCenter(null);
                }
                /* if the property entered is a regular property */
                else {
                  /* if the player owns all the other properties with the same type as the current property */
                  if (getPlayer().ownsAllTypes(property.getType())) {
                    TextField textTwo = new TextField();
                    GridPane gridTwo = new GridPane();
                    gridTwo.setPadding(new Insets(5, 5, 5, 5));
                    /* if the property does not have a ranger station on it */
                    if (!property.getRangerStationOwned()) {
                      gridTwo.add(new Label(property.getName() + " currently has " + property.getNumTents() + 
                                            " tents.\nEach tent costs $" + property.getTentPrice() + ". How many tents would you like to buy?"), 0, 0);
                      gridTwo.add(textTwo, 1, 0);
                      getTertiaryPane().setCenter(gridTwo);
                      /* specifies what to do when the player specifies how many tents they want to buy */
                      textTwo.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e) {
                          try {
                            if (getPlayer().getBalance() >= (Integer.valueOf(textTwo.getText())) * property.getTentPrice()) {
                              boolean tentsAdded = property.addTents(Integer.valueOf(textTwo.getText()), getDialogueBox()); //adds tents to the property
                              getTertiaryPane().setCenter(null);
                              if (property.getRangerStationOwned() && tentsAdded) //if the property now has a ranger station on it
                                getDialogueBox().appendText("\nYou bought a ranger station for " + property.getName());
                              else if (tentsAdded) //if the property now only has tents on it
                                getDialogueBox().appendText("\nYou bought " + textTwo.getText() + " tents for " + property.getName());
                            }
                            else {
                              getDialogueBox().appendText("You do not have enough money to buy " + Integer.valueOf(textTwo.getText()) + " tents for " + property.getName());
                            }
                          }
                          catch (NumberFormatException exception) {
                            getDialogueBox().appendText("\nPlease enter an integer value");
                          }
                        }
                      });
                    }
                    /* if the property already has a ranger station on it */
                    else {
                      gridTwo.add(new Label(property.getName() + " currently has a ranger station on it."), 0, 0);
                    }
                  }
                  /* if the player does not own all the other properties with the same type as the current property */
                  else {
                    getDialogueBox().appendText("\nYou must own all " + property.getType() + " type properties to buy houses for this property");
                    getTertiaryPane().setCenter(null);
                  }
                }
              }
            }
            /* if the player specified a property they do not own */
            for (String name : getPropertyNames()) {
              if (text.getText().equals(name) && !owned) {
                getDialogueBox().appendText("\nYou do not own this property");
                isProperty = true;
                getTertiaryPane().setCenter(null);
              }
            }
            /* if the player did not enter a property name */
            if (!isProperty) {
              getDialogueBox().appendText("\nPlease enter the name of a property");
              getTertiaryPane().setCenter(null);
            }
          }
        });
      }
    });
    getButtonBox().getChildren().add(buyTentButton); //add this button to the box of buttons
  }
  
  /**
   * creates a button to sell a tent/ranger station for a property
   */
  public void sellTent() {
    Button buyTentButton = new Button("Sell tents or ranger stations");
    buyTentButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) { //when the button is clicked
        TextField text = new TextField();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Property you would like to sell a tent for: "), 0, 0);
        grid.add(text, 1, 0);
        getTertiaryPane().setCenter(grid);
        /* specifies what to do when the player eneters the name of a property they would like to sell a tent or ranger station for */
        text.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent e) {
            boolean owned = false; //if the player owns the property
            boolean isProperty = false; //if the name entered is a property
            for (Property property : getPlayer().getPropertiesOwned()) {
              if (property.getName().equals(text.getText())) { //if the player owns the property
                owned = true;
                isProperty = true;
                /* if the property entered is a Natural Features or Activities property */
                if (property.getType().equals("Natural Features") || property.getType().equals("Activities")) {
                  getDialogueBox().appendText("\nYou can not buy/sell tents for this property");
                  getTertiaryPane().setCenter(null);
                }
                /* if the property entered is a regular property */
                else {
                  TextField textTwo = new TextField();
                  GridPane gridTwo = new GridPane();
                  gridTwo.setPadding(new Insets(5, 5, 5, 5));
                  /* if the property specified has between 1 and 4 tents on it */
                  if (!property.getRangerStationOwned() && property.getNumTents() > 0) {
                    gridTwo.add(new Label(property.getName() + " currently has " + property.getNumTents() + 
                                        " tents.\nEach tent sells for $" + property.getTentPrice() + ". How many tents would you like to sell?"), 0, 0);
                    gridTwo.add(textTwo, 1, 0);
                    getTertiaryPane().setCenter(gridTwo);
                    /* specifies what to do when the player specifies how many tents they want to sell */
                    textTwo.setOnAction(new EventHandler<ActionEvent>() {
                      public void handle(ActionEvent e) {
                        try {
                          boolean tentsSold = property.sellTents(Integer.valueOf(textTwo.getText()), getDialogueBox()); //sell the specified number of tents
                          getTertiaryPane().setCenter(null);
                          if (tentsSold) //if the player was able to sell any tents
                            getDialogueBox().appendText("\nYou sold " + textTwo.getText() + " tents for " + property.getName());
                        }
                        catch (NumberFormatException exception) {
                          getDialogueBox().appendText("\nPlease enter an integer value");
                        }
                      }
                    });
                  }
                  /* if the property has a ranger station on it */
                  else if (property.getRangerStationOwned()) {
                    gridTwo.add(new Label(property.getName() + " currently has a ranger station on it, which is the equivalent of 5 tents.\nEach tent sells for $" 
                                            + property.getTentPrice() + ". How many tents would you like to sell?"), 0, 0);
                    gridTwo.add(textTwo, 1, 0);
                    getTertiaryPane().setCenter(gridTwo);
                    /* specifies what to do when the player specifies how many tents they want to sell */
                    textTwo.setOnAction(new EventHandler<ActionEvent>() {
                      public void handle(ActionEvent e) {
                        try {
                          boolean tentsSold = property.sellTents(Integer.valueOf(textTwo.getText()), getDialogueBox()); //sells the tents
                          getTertiaryPane().setCenter(null);
                          if (tentsSold) //if the player was able to sell any tents
                            getDialogueBox().appendText("\nYou sold a ranger station and " + (Integer.valueOf(textTwo.getText()) - 1) + " tents for " + property.getName());
                        }
                        catch (NumberFormatException exception) {
                          getDialogueBox().appendText("\nPlease enter an integer value");
                        }
                      }
                    });
                  }
                  /* if the property has no tents and no ranger station on it */
                  else {
                    getDialogueBox().appendText("\n" + property.getName() + " does not have any tents or a ranger station on it");
                    getTertiaryPane().setCenter(null);
                  }
                }
              }
            }
            /* if the player specified a property they do not own */
            for (String name : getPropertyNames()) {
              if (text.getText().equals(name) && !owned) {
                getDialogueBox().appendText("\nYou do not own this property");
                isProperty = true;
                getTertiaryPane().setCenter(null);
              }
            }
            /* if the player did not enter a property name */
            if (!isProperty) {
              getDialogueBox().appendText("\nPlease enter the name of a property");
              getTertiaryPane().setCenter(null);
            }
          }
        });
      }
    });
    getButtonBox().getChildren().add(buyTentButton); //add this button to the box of buttons
  }
  
  /**
   * Creates a button to mortgage a property
   */
  public void mortgageProperty() {
    Button mortgagePropertyButton = new Button("Mortgage a Property");
    mortgagePropertyButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) { //when the button is clicked prompt for a property name
        TextField text = new TextField();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Property you would like to mortgage: "), 0, 0);
        grid.add(text, 1, 0);
        /* specifies what to do when the player eneters the name of the property they would like to mortgage */
        text.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent e) {
            boolean owned = false; //if the player owns the property
            boolean isProperty = false; //if the name entered is a property
            /* searches the players owned properties to see if the player owns the specified property */
            for (Property property : getPlayer().getPropertiesOwned()) {
              if (property.getName().equals(text.getText())) { //if the player owns the property
                isProperty = true;
                owned = true;
                TextField option = new TextField();
                GridPane gridOne = new GridPane();
                gridOne.setPadding(new Insets(5, 5, 5, 5));
                if (!property.getRangerStationOwned()) { //if the property has tents on it
                  gridOne.add(new Label(property.getName() + " mortgages for $" + property.getMortgage() + " and has " + property.getNumTents() + 
                                        " tents on it which sell for $" + property.getTentPrice() + " each.\nWould you like to mortgage it?"), 0, 0);
                }
                else { //if the property has a ranger station on it
                  gridOne.add(new Label(property.getName() + " mortgages for $" + property.getMortgage() + " and has a ranger station on it which sells for $" 
                                          + (property.getTentPrice() * 5) + ".\nWould you like to mortgage it?"), 0, 0);
                }
                gridOne.add(option, 1, 0);
                getTertiaryPane().setCenter(gridOne);
                /* specifies what to do when the player confirms they want to mortgage the property */
                option.setOnAction(new EventHandler<ActionEvent>() {
                  public void handle(ActionEvent e) {
                    /* if the user confirms they want to mortgage the property */
                    if (option.getText().equals("yes") || option.getText().equals("Yes") || option.getText().equals("YES")) {
                      getPlayer().mortgageProperty(property, getDialogueBox());
                      getTertiaryPane().setCenter(null);
                      getDialogueBox().appendText("\nYou mortgaged " + property.getName());
                    }
                    else {
                      getTertiaryPane().setCenter(null);
                    }
                  }
                });
              }
            }
            /* if the player does not own the property specified */
            for (String name : getPropertyNames()) {
              if (text.getText().equals(name) && !owned) {
                getDialogueBox().appendText("\nYou do not own this property");
                isProperty = true;
                getTertiaryPane().setCenter(null);
              }
            }
            /* if the name specified is not a property */
            if (!isProperty) {
              getDialogueBox().appendText("\nPlease enter the name of a property");
              getTertiaryPane().setCenter(null);
            }
          }
        });
        getTertiaryPane().setCenter(grid);
      }
    });
    getButtonBox().getChildren().add(mortgagePropertyButton); //add this button to the box of buttons
  }
  
  /**
   * Button to end the current player's turn
   * @param primaryStage stage to change scene for when a player loses
   */
  public void endTurn(Stage primaryStage) {
    Button endTurnButton = new Button("End Turn");
    endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        /* if the player has not rolled yet */
        if (!getRolled())
          getDialogueBox().appendText("\nYou have not rolled yet");
        /* if the player has already rolled and they have money or assets */
        else if (getRolled() && !checkIfInDebtAndOwnsAssets()) {
          addTurnCount();
          setRolled(false);
          getDialogueBox().appendText("\n" + getPlayer().getName() + "'s turn!");
          checkLostCondition(primaryStage);
          /* The following only applies if the next player is in jail 
          /* ask the next player if they want to use their get out of jail free card if they have one */
          if (getPlayer().getInJail() && getPlayer().getJailCount() != 2 && getPlayer().getGetOutOfJailFree() > 0) {
            promptForGetOutOfJailCardUse();
          }
          /* ask the next player if they want to pay to get out of jail */
          else if (getPlayer().getInJail() && getPlayer().getJailCount() != 2) {
            promptForPayToGetOutOfJail();
          }
          /* tell the next player they must roll doubles or pay the fee to get out of jail */
          else if (getPlayer().getInJail() && getPlayer().getJailCount() == 2) {
            getDialogueBox().appendText("\nIf you do not roll doubles on this turn, you will have to pay $50 to get out of jail");
          }
        }
      }
    });
    getButtonBox().getChildren().add(endTurnButton); //add this button to the box of buttons
  }
  
  /**
   * Checks if the current player has less than 0 balance and if they own properties or not
   * @return boolean if the player is in debt but owns properties
   */
  public boolean checkIfInDebtAndOwnsAssets() {
    boolean loosing = false;
    if (getPlayer().getBalance() < 0 && getPlayer().getPropertiesOwned().size() > 0) {
      getDialogueBox().appendText("\nYou must sell tents or properties that you own to increase your balance to at least $0, or you will lose when you end your turn");
      loosing = true;
    }
    return loosing;
  }
  
  /**
   * If the current player is in debt, displays a win screen for the next player
   */
  public void checkLostCondition(Stage primaryStage) {
    if (getPlayer().getBalance() < 0) {
      Label won = new Label(getPlayer().getName() + " has run out of Assets. Congratulations " + getNextPlayer().getName() + ", you crushed it!");
      Scene scene = new Scene(won);
      primaryStage.setScene(scene);
      primaryStage.sizeToScene();
      primaryStage.show();
    }
  }
  
  /*
   * Determines what type of spot the player has landed on and performs the action specified by that spot
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public void handlePosition() throws IllegalAccessException, InvocationTargetException {
    /* if player landed on a Historic Sites spot */
    if (getCurrentBoardSpot() instanceof HistoricSites) {
      getHistoricSites().drawCard(getPlayer(), getDialogueBox()); //draw a historic sites card
    }
    /* if player landed on a Battlefield Parks spot */
    if (getCurrentBoardSpot() instanceof BattleFieldParks) {
      getBattlefieldParks().drawCard(getPlayer(), getDialogueBox()); //draw a Battlefield Parks card
    }
    setCurrentBoardSpot(getBoard()[getPlayer().getPosition()[0]][getPlayer().getPosition()[1]]); //sets current board spot to where the player is currently located
    /* if player landed on a spot that requires them to pay a fee */
    if (getCurrentBoardSpot() instanceof Fee) {
      Fee fee = ((Fee)(getCurrentBoardSpot()));
      getPlayer().subtractBalance(fee.getFee());
      FreeParking.addBalance(fee.getFee());
      getDialogueBox().appendText("\n" + fee.getName() + ". Pay " + fee.getFee());
    }
    /* if player landed on Free Parking */
    if (getPlayer().getPosition()[0] == 1 && getPlayer().getPosition()[1] == 10)
      FreeParking.rewardPlayer(getPlayer());
    /* if player landed on a Property */
    if (getCurrentBoardSpot() instanceof Property) {
      Property property = (Property)getCurrentBoardSpot();
      getDialogueBox().appendText("\nYou landed on " + property.getName());
      /* if the property the player landed on is owned by another player */
      if (property.getOwnership() == true && !getPlayer().ownsProperty(property.getName()) && !property.getOwner().equals(getPlayer())) {
        /* if the property landed on is a natural feature property */
        if (property.equals(getBoard()[1][2]) || property.equals(getBoard()[2][7])) {
          /* if the same person owns both natural featues properies, rent is 10 times the dice roll */
          if (property.getOwner().equals(((Property)(getBoard()[1][2])).getOwner()) && property.getOwner().equals(((Property)(getBoard()[2][7])).getOwner())) {
            getPlayer().subtractBalance(10 * getDiceRoll());
            property.getOwner().addBalance(10 * getDiceRoll());
            getDialogueBox().appendText("\n" + property.getName() + " is owned by " + property.getOwner().getName() + " and the rent is $" + (10 * getDiceRoll()));
          }
          else {
            getPlayer().subtractBalance(4 * getDiceRoll());
            property.getOwner().addBalance(4 * getDiceRoll());
            getDialogueBox().appendText("\n" + property.getName() + " is owned by " + property.getOwner().getName() + " and the rent is $" + (4 * getDiceRoll()));
          }
        }
        /* if the property the player landed on is an Activities property */
        else if (property.equals(getBoard()[0][4]) || property.equals(getBoard()[1][5]) || property.equals(getBoard()[2][4]) || property.equals(getBoard()[3][5])) {
          /* if the owner of this property owns 1 Activities property rent is $25 */
          if ((property.getOwner()).getActivities() == 1) {
            getPlayer().subtractBalance(25);
            property.getOwner().addBalance(25);
            getDialogueBox().appendText("\n" + property.getName() + " is owned by " + property.getOwner().getName() + " and the rent is $25");
          }
          /* if the owner of this property owns 2 Activities properties rent is $50 */
          else if ((property.getOwner()).getActivities() == 2) {
            getPlayer().subtractBalance(50);
            property.getOwner().addBalance(50);
            getDialogueBox().appendText("\n" + property.getName() + " is owned by " + property.getOwner().getName() + " and the rent is $50");
          }
          /* if the owner of this property owns 3 Activities properties rent is $100 */
          else if ((property.getOwner()).getActivities() == 3) {
            getPlayer().subtractBalance(100);
            property.getOwner().addBalance(100);
            getDialogueBox().appendText("\n" + property.getName() + " is owned by " + property.getOwner().getName() + " and the rent is $100");
          }
          /* if the owner of this property owns 4 Activities properties rent is $200 */
          else if ((property.getOwner()).getActivities() == 4) {
            getPlayer().subtractBalance(200);
            property.getOwner().addBalance(200);
            getDialogueBox().appendText("\n" + property.getName() + " is owned by " + property.getOwner().getName() + " and the rent is $200");
          }
        }
        /* if the player landed on a regular property owned by another player */
        else {
          getPlayer().subtractBalance(property.getRent());
          property.getOwner().addBalance(property.getRent());
          getDialogueBox().appendText("\n" + property.getName() + " is owned by " + property.getOwner().getName() + " and the rent is $" + property.getRent());
        }
      }
    }
    /* if player landed on Go to Jail */
    if (getPlayer().getPosition()[0] == 3 && getPlayer().getPosition()[1] == 0) {
      getPlayer().setPosition(1, 0, getDialogueBox()); //set their position to the jail spot
      getPlayer().setInJail(true); //set them in jail
      getDialogueBox().appendText("\nYou failed to return your library book on time and were thrown in jail");
    }
  }
  
  /**
   * Called when the player is in jail
   * Determines what the player's next action is depending on number of turns spent in jail and if doubles were rolled
   * @param diceOne the number rolled by the first dice
   * @param diceTwo the number rolled by the second dice
   */
  public void handleJail(int diceOne, int diceTwo) {
    /* if the player has not been in jail for less than 3 turns */
    if (getPlayer().getJailCount() != 2) {
      /* if the player rolled doubles they escape from prison */
      if (diceOne == diceTwo) {
        getPlayer().setInJail(false);
        getPlayer().setJailCount(0);
        getDialogueBox().appendText("\nYou rolled doubles and escaped from Jail!");
      }
      /* else they stay in prison */
      else {
        getPlayer().addJailCount();
        getDialogueBox().appendText("\nYou are stuck in Jail!");
      }
    }
    /* else they have been in jail for 3 turns and automatically get out this turn, whatever they roll */
    else {
      /* if the player rolled doubles they escape from prison */
      if (diceOne == diceTwo) {
        getPlayer().setInJail(false);
        getPlayer().setJailCount(0);
        getDialogueBox().appendText("\nYou rolled doubles and escaped from Jail!");
      }
      /* else they must pay $50 to get out of jail */
      else {
        getPlayer().setInJail(false);
        getPlayer().setJailCount(0);
        getPlayer().subtractBalance(50);
        getDialogueBox().appendText("\nYou bribe the jail guard with $50 and he helps you escape");
      }
    }
  }
  
  /**
   * Prompts the player if they want to use their get out of jail free card to get out of jail, or if not, if they want to pay the fee to get out of jail
   */
  public void promptForGetOutOfJailCardUse() {
    TextField text = new TextField(); //to store the users response
    GridPane grid = new GridPane(); //to store the prompt and the area for user input
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("You are currently in Jail. You have been in Jail for " + getPlayer().getJailCount() + 
                               " turns. \nWould you like to use your get out of jail free card?"), 0, 0);
    grid.add(text, 1, 0);
    getTertiaryPane().setCenter(grid);
    text.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        /* if the user answers yes, remove a get out of jail free card from their inventory and remove them from jail */
        if (text.getText().equals("yes") || text.getText().equals("Yes") || text.getText().equals("YES")) {
          getPlayer().subtractGetOutOfJailFree();
          getPlayer().setInJail(false);
          getPlayer().setJailCount(0);
          getTertiaryPane().setCenter(null);
          getDialogueBox().appendText("\nYou use your get out of jail free card. You may now roll");
        }
        /* if the user answers no, ask if they want to pay the fee to get out of jail */
        else
          promptForPayToGetOutOfJail();
      }
    });
  }
  
  /**
   * Prompts the player if they want to pay the fee to get out of jail
   */
  public void promptForPayToGetOutOfJail() {
    TextField text = new TextField(); //to store the users response
    GridPane grid = new GridPane(); //to store the prompt and the area for user response
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("You are currently in Jail. You have been in Jail for " + getPlayer().getJailCount() + 
                               " turns. \nWould you like to pay $50 to get out now?"), 0, 0);
    grid.add(text, 1, 0);
    getTertiaryPane().setCenter(grid);
    text.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        /* if the user answers yes, subtract $50 from their balance, add it to Free Parking, and remove them from jail */
        if (text.getText().equals("yes") || text.getText().equals("Yes") || text.getText().equals("YES")) {
          getPlayer().subtractBalance(50);
          FreeParking.addBalance(50);
          getPlayer().setInJail(false);
          getPlayer().setJailCount(0);
          getTertiaryPane().setCenter(null);
          getDialogueBox().appendText("\nYour mom pays your $50 bail but demands that you pay her back. You may now roll");
        }
        /* if the user answers anything besides yes, clears the space and does nothing */
        else
          getTertiaryPane().setCenter(null);
      }
    });
  }
  
  /*
   * Initializes each spot of the Monopoly board with the proper type of board spot
   * @throws NoSuchMethodException
   */
  public void initializeBoardProperties() throws NoSuchMethodException {
    setBattlefieldParks(new BattleFieldParks());
    setHistoricSites(new HistoricSites());
    getBoard()[0][0] = new Property("Cuyahoga Valley", 60, "Brown", new int[]{2, 10, 30, 90, 160, 250}, 50);
    getBoard()[0][1] = getBattlefieldParks();
    getBoard()[0][2] = new Property("Joshua Tree", 60, "Brown", new int[]{4, 20, 60, 180, 320, 450}, 50);
    getBoard()[0][3] = new Fee("Campfire Violation", 200);
    getBoard()[0][4] = new Property("Nature Hiking", 200, "Activities");
    getBoard()[0][5] = new Property("Death Valley", 100, "Light Blue", new int[]{6, 30, 90, 270, 400, 550}, 50);
    getBoard()[0][6] = getHistoricSites();
    getBoard()[0][7] = new Property("Arches", 100, "Light Blue", new int[]{6, 30, 90, 270, 400, 550}, 50);
    getBoard()[0][8] = new Property("Redwood", 120, "Light Blue", new int[]{8, 40, 100, 300, 450, 600}, 50);
    /* getBoard()[1][0] - (Jail) - is handled in the handle jail method */
    getBoard()[1][1] = new Property("Big Bend", 140, "Pink", new int[]{10, 50, 150, 450, 625, 750}, 100);
    getBoard()[1][2] = new Property("Volcano", 150, "Natural Features");
    getBoard()[1][3] = new Property("Olympic", 140, "Pink", new int[]{10, 50, 150, 450, 625, 750}, 100);
    getBoard()[1][4] = new Property("Great Smoky Mountain", 160, "Pink", new int[]{12, 60, 180, 500, 700, 900}, 100);
    getBoard()[1][5] = new Property("Rock Climbing", 200, "Activities");
    getBoard()[1][6] = new Property("Everglades", 180, "Orange", new int[]{14, 70, 200, 550, 750, 950}, 100);
    getBoard()[1][7] = getBattlefieldParks();
    getBoard()[1][8] = new Property("Carlsbad Caverns", 180, "Orange", new int[]{14, 70, 200, 550, 750, 950}, 100);
    getBoard()[1][9] = new Property("Grand Teton", 200, "Orange", new int[]{16, 80, 220, 600, 800, 1000}, 100);
    /* getBoard()[1][10] - (Free Parking) - is handled in the handle position method */
    getBoard()[2][0] = new Property("Acadia", 220, "Red", new int[]{18, 90, 250, 700, 875, 1050}, 150);
    getBoard()[2][1] = getHistoricSites();
    getBoard()[2][2] = new Property("Bryce Cannon", 220, "Red", new int[]{18, 90, 250, 700, 875, 1050}, 150);
    getBoard()[2][3] = new Property("Zion", 240, "Red", new int[]{20, 100, 300, 750, 925, 1100}, 150);
    getBoard()[2][4] = new Property("Bicycling", 200, "Activities");
    getBoard()[2][5] = new Property("Grand Canyon", 260, "Yellow", new int[]{22, 110, 330, 800, 975, 1150}, 150);
    getBoard()[2][6] = new Property("Denali", 260, "Yellow", new int[]{22, 110, 330, 800, 975, 1150}, 150);
    getBoard()[2][7] = new Property("Geyser", 150, "Natural Features");
    getBoard()[2][8] = new Property("Rocky Mountains", 280, "Yellow", new int[]{24, 120, 380, 850, 1025, 1200}, 150);
    /* getBoard()[3][0] - (Go To Jail) - is handled in the handle position method */
    getBoard()[3][1] = new Property("Glacier", 300, "Green", new int[]{26, 130, 390, 900, 1100, 1275}, 200);
    getBoard()[3][2] = new Property("Mount Rainier", 300, "Green", new int[]{26, 130, 390, 900, 1100, 1275}, 200);
    getBoard()[3][3] = getBattlefieldParks();
    getBoard()[3][4] = new Property("Sequoia", 320, "Green", new int[]{28, 150, 450, 1000, 1200, 1400}, 200);
    getBoard()[3][5] = new Property("River Rafting", 200, "Activities");
    getBoard()[3][6] = getHistoricSites();
    getBoard()[3][7] = new Property("Yosemite", 350, "Blue", new int[]{35, 175, 500, 1100, 1300, 1500}, 200);
    getBoard()[3][8] = new Fee("Purchase Annual Pass", 100);
    getBoard()[3][9] = new Property("Yellowstone", 400, "Blue", new int[]{40, 200, 600, 1400, 1700, 2000}, 200);
    /* getBoard()[3][10] - (GO) - is handled in the player classes setPosition method */
  }
  
  /*
   * Creates an input box for the first player to enter their name
   * @param primaryStage stage to display current scene on
   */
  public void getPlayer1Name(Stage primaryStage) {
    TextField text = new TextField();
    text.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        player1.setName(text.getText()); //sets player 1 name to name entered
        getPlayer2Name(primaryStage); //call to retrieve name of player 2
      }
    });
    
    GridPane grid = new GridPane(); //grid to store the prompt and answer area
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("Player 1 Name: "), 0, 0); //the prompt for question
    grid.add(text, 1, 0);
    
    Scene scene = new Scene(grid);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /*
   * Creates an input box for the second player to enter their name
   * @param primaryStage stage to display current scene on
   */
  public void getPlayer2Name(Stage primaryStage) {
    TextField text = new TextField();
    text.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        player2.setName(text.getText()); //sets player 2 name to text entered
        initializeBoardImage(primaryStage); // initializes board images
      }
    });
    
    GridPane grid = new GridPane(); //grid to store the propmt and answer area
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("Player 2 Name: "), 0, 0); //the propmt for question
    grid.add(text, 1, 0);
    
    Scene scene = new Scene(grid);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * Creates a link to the rules for monopoly
   * @return grid the gridpane displaying the statement that contains the link to the monopoly rules
   */
  public GridPane linkToRules() {
    try {
      URL url = new URL("https://www.hasbro.com/common/instruct/monins.pdf"); //link to monopoly rules
      Desktop desktop = Desktop.getDesktop(); //establishes the users desktop
      Label label = new Label("here"); //label that, when clicked, will take the user to the webpage for monopoly rules
      label.setTextFill(Color.BLUE);
      label.setUnderline(true);
      label.setOnMouseClicked(new EventHandler<MouseEvent>() { //set on mouse clicked
        public void handle(MouseEvent e) {
          try {
            if (desktop.isSupported(Desktop.Action.BROWSE)) //if the desktop supports the browse function
              desktop.browse(url.toURI()); //open the link specified earlier
          }
          catch (IOException a) {  }
          catch (URISyntaxException b) {  }
        }
      });
      GridPane grid = new GridPane();
      grid.add(new Label("Click "), 0, 0);
      grid.add(label, 1, 0);
      grid.add(new Label(" to view the rules of monopoly."), 2, 0);
      return grid;
    }
    catch (MalformedURLException exception) {  }
    return null;
  }
  
  /**
   * Initializes each button and adds to the monopoly board image
   * @param leftBox the VBox containing monopoly board images on the left side of the board
   * @param topBox the HBox containing monopoly board images on the top of the board
   * @param rightBox the VBox containing monopoly board images on the right side of the board
   * @param bottomBox the HBox containing monopoly board images on the bottom of the board
   * @param boardImages the array of original monopoly board images with no player tokens on them
   * @param primaryStage the main stage of the monopoly game
   */
  public void initializeButtons(VBox leftBox, HBox topBox, VBox rightBox, HBox bottomBox, ImageView[][] boardImages, Stage primaryStage) {
    /* initializing the button that allows the user to roll the dice and move their token */
    rollButton(leftBox, topBox, rightBox, bottomBox, boardImages);
    /* initializing the button that allows the user to buy a property they are currently on */
    buyPropertyButton();
    /* initializing the button that allows the user to see a list of properties they own */
    getPropertiesOwned();
    /* initializing the button that allows the user to see their balance */
    getMyBalance();
    /* initializing the button that allows the user to mortgage a property */
    mortgageProperty();
    /* initializing the button that allows the user to buy tents for their properties */
    buyTent();
    /* initializing the button that allows the user to sell tents for their properties */
    sellTent();
    /* initializing the button that allows the user to end their turn */
    endTurn(primaryStage);
  }
  
  /*
   * Initializes the properties of the scroll pane to display the dialogue box
   */
  public void initializeScrollPane() {
    getDialogueBox().setPrefSize(642, 240);
    getScrollPane().setPrefSize(644, 242);   
    getScrollPane().setContent(getDialogueBox()); //sets content to the text area
    getScrollPane().setVvalue(getScrollPane().getHeight()); //sets the scrollpane to programatically scroll 
    getScrollPane().setHvalue(0);
  }
  
  /**
   * Simulates the roll of a dice and returns the number rolled by the die
   * @return dice the number rolled by the dice
   */
  public int rollDice() {
    int dice = (int)(Math.random() * 7);
    if (dice == 0) //ensures the dice does not roll a zero
      dice = rollDice();
    return dice;
  }
  
  /**
   * Main method that launches the game
   * @param args list of arguments passed in
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
  
  /**
   * Returns the number rolled by both dice
   * @return diceRoll the number rolled by both dice
   */
  public int getDiceRoll() {
    return diceRoll;
  }
  
  /**
   * Sets the number rolled by the dice
   * @param diceRoll the number to set the dice value to
   */
  public void setDiceRoll(int diceRoll) {
    this.diceRoll = diceRoll;
  }
  
  /**
   * Returns the array of individual BoardSpots on the monopoly board
   * @return board the array of individual BoardSpots on the board
   */
  public BoardSpot[][] getBoard() {
    return this.board;
  }
  
  /**
   * Returns the array of property names on the board
   * @return propertyNames an array of property names 
   */
  public String[] getPropertyNames() {
    return propertyNames;
  }
  
  /**
   * Returns the spot on the board at which the current player is located
   * @return currentBoardSpot the spot on the board at which the current player is located
   */
  public BoardSpot getCurrentBoardSpot() {
    return currentBoardSpot;
  }
  
  /**
   * Sets the spot on the board at which the player is currently located
   * @param currentBoardSpot the spot on the board at which the current player is located
   */
  public void setCurrentBoardSpot(BoardSpot currentBoardSpot) {
    this.currentBoardSpot = currentBoardSpot;
  }
  
  /**
   * Returns the turn count of the game
   * @return turnCount the amount of turns taken so far in the game
   */
  public int getTurnCount() {
    return turnCount;
  }
  
  /**
   * Returns if the current player has rolled the dice this turn
   * @return rolled whether the current player has rolled the dice this turn
   */
  public boolean getRolled() {
    return rolled;
  }
  
  /**
   * Sets the value for whether the current player has rolled the dice this turn
   * @param rolled whether the current player has rolled the dice this turn
   */
  public void setRolled(boolean rolled) {
    this.rolled = rolled;
  }
  
  /**
   * Returns the player whos turn it is now
   * @return player1 if it is their turn
   * @return player2 if it is their turn
   */
  public Player getPlayer() {
    if (getTurnCount() % numPlayers() == 1)
      return player1;
    else
      return player2;
  }
  
  /**
   * Returns the number of players in the game
   * @return numPlayers the number of players in the game
   */
  public int numPlayers() {
    return this.numPlayers;
  }
  
  /**
   * Returns the player whos turn it is next
   * @return player2 if it is player 1's turn
   * @return player1 if it is player 2's turn
   */
  public Player getNextPlayer() {
    if (getPlayer().equals(player1))
      return player2;
    else
      return player1;
  }
  
  /**
   * Increases the turn count by 1
   */
  public void addTurnCount() {
    turnCount++;
  }
  
  /**
   * Returns the deck of Historic Sites cards
   * @return historicSites the deck of Historic Sites cards
   */
  public HistoricSites getHistoricSites() {
    return historicSites;
  }
  
  /**
   * Sets Historic Sites to a new deck of Historic Sites cards, randomly shuffled
   * @param historicSites the deck of Historic Sites cards
   */
  public void setHistoricSites(HistoricSites historicSites) {
    this.historicSites = historicSites;
  }
  
  /**
   * Returns the deck of Battlefield Parks cards
   * @return battlefieldParks the deck of Battlefield Parks cards
   */
  public BattleFieldParks getBattlefieldParks() {
    return battlefieldParks;
  }
  
  /**
   * Sets Battlefield Parks to a new deck of Historic Sites Cards,  randomly shuffled
   * @param  battlefieldParks the deck of Battlefield Parks cards
   */
  public void setBattlefieldParks(BattleFieldParks battleFieldPark) {
    this.battlefieldParks = battleFieldPark;
  }
  
  /**
   * Returns the pane where buttons are to be added
   * @return tertiaryPane the pane to add buttons to
   */
  public BorderPane getTertiaryPane() {
    return tertiaryPane;
  }
  
  /**
   * Returns the scroll pane which contains the dialogue box
   * @return scrollPane the scroll pane containing the dialogue box
   */
  public ScrollPane getScrollPane() {
    return scrollPane;
  }
  
  /** 
   * Returns the Text Area that displays messages to the user about what is happening in the game
   * @return dialogueBox the Text Area displaying messages about the current state of the game
   */
  public TextArea getDialogueBox() {
    return dialogueBox;
  }
  
  /**
   * Returns the VBox that holds the buttons
   * @return buttonBox the VBox containing the buttons for the game
   */
  public VBox getButtonBox() {
    return buttonBox;
  }
}