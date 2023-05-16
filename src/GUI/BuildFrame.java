package GUI;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.awt.*;


public class BuildFrame extends Application implements EventHandler<ActionEvent> {

    final static Point DOOR_SIZE = new Point(100,150);

    String myDifficulty = "EASY";
    Button myAboutButton;
    Button myDifficultyButton;
    Button myDoorAButton;
    Button myDoorBButton;
    Button myDoorCButton;
    Button myDoorDButton;
    Button myLoadButton; //rgba(173, 165, 191, 1)
    Button myRulesButton;
    Button mySaveButton;
    Button myShortcutButton;
    Button myStartButton;
    Button myThemeButton;

    //Color baseColor = new Color(173.0/255, 165.0/255, 191.0/255, 1);
    Circle myIAmHereMarker;
    Group myGameFrame;

    Group easyMediumHardPanel;
    Group myStartMenu;
    Image brick = new Image("file:brick.jpg");
    ImageView rainImage = new ImageView(new Image("file:83196.jpg"));
    Point myDirection;

    Position myPosition;
    Stage myStage;
    public static void main(final String[] theArgs) {
        launch(theArgs);
    }

    @Override
    public void start(final Stage thePrimaryStage) {
        thePrimaryStage.setTitle("THE AMAZING MAZE");
        thePrimaryStage.getIcons().add(new Image("file:logo.png"));
        myStage = thePrimaryStage;
        setStartMenu();
        thePrimaryStage.show();
    }

    private void setStartMenu() {
        myStartMenu = new Group();

        rainImage.setFitWidth(680);
        rainImage.setFitHeight(680);
        rainImage.setLayoutX(10);
        rainImage.setLayoutY(10);
        rainImage.setOpacity(0.5);

        Label title = new Label("Amazing Maze");
        //title.setPrefSize();
        title.setScaleX(7);
        title.setScaleY(15);
        title.setLayoutX(300);
        title.setLayoutY(200);
        title.setTextFill(Color.BLACK);

        myStartButton = buttonMaker("START", new Point(300, 350));
        myDifficultyButton = buttonMaker("DIFFICULTY", new Point(300, 500));
        myDifficultyButton.setOnAction(theEvent -> easyMediumHardButtons());
        myAboutButton = buttonMaker("ABOUT", new Point(150, 500));
        myLoadButton = buttonMaker("LOAD", new Point(300, 425));
        myThemeButton = buttonMaker("THEME", new Point(475, 500));

        myStartMenu.getChildren().addAll(rainImage, myDifficultyButton, myStartButton,
                myLoadButton, myThemeButton, myAboutButton, title);

        Scene scene = new Scene(myStartMenu, 700, 700, Color.GREY);
        myStage.setScene(scene);
    }

    private void easyMediumHardButtons() {
        easyMediumHardPanel = new Group();
        flipStartOptionsVisible();

        Button easy = buttonMaker("EASY", new Point(300,500));
        easy.setOnAction(theEvent -> setDifficulty(easy.getText()));
        Button medium = buttonMaker("MEDIUM", new Point(300,570));
        medium.setOnAction(theEvent -> setDifficulty(medium.getText()));
        Button hard = buttonMaker("HARD", new Point(300, 640));
        hard.setOnAction(theEvent -> setDifficulty(hard.getText()));

        easyMediumHardPanel.getChildren().addAll(easy, medium, hard);
        myStartMenu.getChildren().add(easyMediumHardPanel);
    }

    private void flipStartOptionsVisible() {
        myStartButton.setVisible(!myStartButton.isVisible());
        myLoadButton.setVisible(!myLoadButton.isVisible());
        myDifficultyButton.setVisible(!myDifficultyButton.isVisible());
    }

    private void setDifficulty(String theDifficulty) {
        myDifficulty = theDifficulty;
        myDifficultyButton.setText(theDifficulty);
        myStartMenu.getChildren().removeAll(easyMediumHardPanel);
        flipStartOptionsVisible();
    }

    private void startGame() {
        myDirection = Position.LEFT;
        myPosition = new Position();

        // Create you are here marker
        myIAmHereMarker = new Circle(10);
        myIAmHereMarker.setLayoutX(475);
        myIAmHereMarker.setLayoutY(475);

        // Create frame for gameplay
        myGameFrame = new Group();

        // Create the doors
        Group room = new Group();
        Polygon doorA = new Polygon(250, 100, 250, 250, 350, 250, 350, 100);
        doorA.setFill(new ImagePattern(new Image("file:doorA.jpg")));
        myDoorAButton = buttonMaker("A", new Point(50,150), DOOR_SIZE,false);
        myDoorBButton = buttonMaker("B", new Point(250,100), DOOR_SIZE, true);
        myDoorCButton = buttonMaker("C", new Point(500,150), DOOR_SIZE, false);
        myDoorDButton = buttonMaker("D", new Point(325,550), DOOR_SIZE, false);
        checkForWindows();

        // Create Walls
        Polygon wallA = new Polygon(0 ,0, 0, 500, 200, 200, 200, 0);
        wallA.setFill(new ImagePattern(new Image("file:sideBrick.jpg"), 0, 0, 1.7, 1, true));
        Rectangle wallB = new Rectangle(168, 0,364,250);
        wallB.setFill(new ImagePattern(brick, -.1, 0, 100, 80, false));
        Polygon wallC = new Polygon(532,0, 700, 0, 700, 500, 532, 250);
        wallC.setFill(new ImagePattern(new Image("file:sideBrick.jpg"), -0.8, -0.1, 1.7, 1, true));

        // Create Floor
        Rectangle floor = new Rectangle(0,0,750, 500);
        floor.setFill(new ImagePattern( new Image("file:floor.jpg"), 0,0,0,0,true));

        room.getChildren().addAll(floor, wallA, wallC, wallB, doorA,
                myDoorBButton, myDoorCButton, myDoorAButton, myDoorDButton);

        // Create button panel
        Group buttonPanel = new Group();
        Rectangle bottom = new Rectangle(0, 500,300,300);
        bottom.setFill(Color.TAN);
        mySaveButton = buttonMaker("SAVE", new Point(100, 510));
        myRulesButton = buttonMaker("RULES",new Point(100, 580));
        myShortcutButton = buttonMaker("SHORTCUTS", new Point(100, 650));
        buttonPanel.getChildren().addAll(bottom, myShortcutButton, myRulesButton, mySaveButton);

        // Create grid
        Group grid = new Group();
        Rectangle base = new Rectangle(450, 450, 250, 250);
        base.setFill(Color.WHITE);
        grid.getChildren().add(base);
        for (int i = 450; i <= 700; i+=50) {
            grid.getChildren().add(new Line(i, 450, i, 700));
            grid.getChildren().add(new Line(450, i, 700, i));
        }

        myGameFrame.getChildren().addAll(room, buttonPanel, grid, myIAmHereMarker);

        Scene scene = new Scene(myGameFrame, 800, 750);
        myStage.setScene(scene);
    }

//    private void about() {
//        Popup pop = PopupBuilder.create().width(50).height(100).autoFix(true).build();
//        pop.show(myStage);
//    }

    private void changePositions(Point theChange) {
        myPosition.changePositions(theChange);
        myIAmHereMarker.setLayoutX(myPosition.myPosition.x);
        myIAmHereMarker.setLayoutY(myPosition.myPosition.y);
        checkForWindows();
    }
    private Button buttonMaker(final String theName, final Point theCoordinate) {
        return buttonMaker(theName, theCoordinate, new Point(100,50), false);
    }
    private Button buttonMaker(final String theName, final Point theCoordinate,
                             final Point theSize, final boolean theBlendIn) {
        Button button = new Button(theName);
        button.setPrefSize(theSize.x, theSize.y);
        button.setLayoutX(theCoordinate.x);
        button.setLayoutY(theCoordinate.y);
        button.setOnAction(this);
        if (theBlendIn) {
            button.setStyle("-fx-background-color: rgba(84, 72, 113, 0.46)");
        }
        return button;
    }

    private void checkForWindows() {
        int xCoordinate = myPosition.myPosition.x;
        int yCoordinate = myPosition.myPosition.y;
        myDoorAButton.setVisible(xCoordinate > 475);
        myDoorBButton.setVisible(yCoordinate > 475);
        myDoorCButton.setVisible(xCoordinate < 675);
        myDoorDButton.setVisible(yCoordinate < 675);
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == myStartButton) {
            startGame();
        } else if (source == myDoorAButton) {
            changePositions(Position.LEFT);
        } else if (source == myDoorBButton) {
            changePositions(Position.UP);
        } else if (source == myDoorCButton) {
            changePositions(Position.RIGHT);
        } else if (source == myDoorDButton) {
            changePositions(Position.DOWN);
        }
    }
}

//        myDoorAButton = new Button("A");
//        myDoorAButton.setPrefSize(100,150);
//        //doorAButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
//        myDoorAButton.setLayoutX(50);
//        myDoorAButton.setLayoutY(150);
//        myDoorAButton.setOnAction(this);
//        myDoorBButton = new Button("B");
//        myDoorBButton.setPrefSize(100,150);
//        myDoorBButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
//        myDoorBButton.setLayoutX(250); //50
//        myDoorBButton.setLayoutY(100); //150
//        myDoorBButton.setOnAction(this);
//        myDoorCButton = new Button("C");
//        myDoorCButton.setPrefSize(100,150);
//        //doorCButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
//        myDoorCButton.setLayoutX(500);
//        myDoorCButton.setLayoutY(150);
//        myDoorCButton.setOnAction(this);
//        myDoorDButton = new Button("D");
//        myDoorDButton.setPrefSize(50,50);
//        //doorDButton.setStyle("-fx-background-color: rgba(1, 10, 50, 0)");
//        myDoorDButton.setLayoutX(350);
//        myDoorDButton.setLayoutY(550);
//        myDoorDButton.setOnAction(this);

//myRulesButton = new Button("RULES");
//mySaveButton = new Button("SAVE");
//myShortcutButton = new Button("SHORTCUTS");
//buttonMaker(mySaveButton, new Point(125, 530));
//buttonMaker(myRulesButton, new Point(125, 600));
//buttonMaker(myShortcutButton, new Point(110, 670));

//        myDifficultyButton = new Button(" DIFFICULTY ");
//        myAboutButton = new Button("ABOUT");
//        myLoadButton = new Button("LOAD GAME");
//        myThemeButton = new Button("THEME");
//        buttonMaker(myLoadButton, new Point(300, 425));
//        buttonMaker(myAboutButton, new Point(150, 500));
//        buttonMaker(myDifficultyButton, new Point(300, 500));
//        buttonMaker(myThemeButton, new Point(475, 500));

//        if (xCoordinate <= 475) {
//            myDoorAButton.setVisible(false);
//        } else if (xCoordinate >= 675) {
//            myDoorCButton.setVisible(false);
//        } else {
//            myDoorAButton.setVisible(true);
//            myDoorCButton.setVisible(true);
//        }
//        if (yCoordinate <= 475) {
//            myDoorBButton.setVisible(false);
//        } else if (yCoordinate >= 675) {
//            myDoorDButton.setVisible(false);
//        } else {
//            myDoorBButton.setVisible(true);
//            myDoorDButton.setVisible(true);
//        }