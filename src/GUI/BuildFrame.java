package GUI;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.awt.*;
import java.util.HashMap;


public class BuildFrame extends Application implements EventHandler<ActionEvent> {

    Circle myIAmHereMarker;
    Group myGameFrame;

    Group myDifficultyPanel;
    Group myThemePanel;
    Group myStartMenu;

    Group myQuestionPanel;

    Label myQuestion;
    TextField myResponse;
    Point myDirection;

    Position myPosition;

    HashMap<String, Button> myButtons;

    Rectangle hideDoorA;
    Rectangle hideDoorB;
    Rectangle hideDoorC;

    Stage myStage;

    public static void main(final String[] theArgs) {
        launch(theArgs);
    }

    @Override
    public void start(final Stage thePrimaryStage) {
        thePrimaryStage.setTitle(GUIConstants.TITLE);
        thePrimaryStage.getIcons().add(GUIConstants.LOGO);

        myStage = thePrimaryStage;
        myButtons = new HashMap();

        setStartMenu();
        thePrimaryStage.setResizable(false);
        thePrimaryStage.show();
    }

    private void setStartMenu() {
        myStartMenu = new Group();

        GUIConstants.RAIN_IMAGE.setFitWidth(GUIConstants.SCREEN_SIZE.x - 20);
        GUIConstants.RAIN_IMAGE.setFitHeight(GUIConstants.SCREEN_SIZE.y - 20);
        GUIConstants.RAIN_IMAGE.relocate(10,10);
        GUIConstants.RAIN_IMAGE.setOpacity(0.25);

        // create title
        Label title = new Label(GUIConstants.TITLE);
        title.setFont(GUIConstants.TITLE_FONT);
        title.relocate(125, 175);

        // create start menu buttons
        Button startButton = buttonMaker(GUIConstants.START, GUIConstants.START_COORDINATES);
        Button loadButton = buttonMaker(GUIConstants.LOAD, GUIConstants.LOAD_COORDINATES);
        Button difficultyButton = buttonMaker(GUIConstants.DIFFICULTY, GUIConstants.DIFFICULTY_COORDINATES);
        Button aboutButton = buttonMaker(GUIConstants.ABOUT, GUIConstants.ABOUT_COORDINATES);
        Button themeButton = buttonMaker(GUIConstants.THEME, GUIConstants.THEME_COORDINATES);

        startButton.setOnAction(theEvent -> startGame());
        difficultyButton.setOnAction(theEvent -> setVisibility(myDifficultyPanel));
        aboutButton.setOnAction(theEvent -> GUIConstants.ABOUT_DESCRIPTION.setVisible(!GUIConstants.ABOUT_DESCRIPTION.isVisible()));
        themeButton.setOnAction(theEvent -> setVisibility(myThemePanel));

        myStartMenu.getChildren().addAll(GUIConstants.RAIN_IMAGE, difficultyButton, startButton,
                loadButton, themeButton, aboutButton, title);

        createEasyMediumHardPanel();
        createThemedButtonsPanel();
        createPanels();
        Scene scene = new Scene(myStartMenu, GUIConstants.SCREEN_SIZE.x, GUIConstants.SCREEN_SIZE.y, Color.GREY);
        myStage.setScene(scene);
    }

    private void startGame() {
        myDirection = Position.LEFT;
        myPosition = new Position();

        // Create you are here marker
        myIAmHereMarker = new Circle(10);
        myIAmHereMarker.relocate(365,465);

        // Create frame for gameplay
        myGameFrame = new Group();

        // Create the room
        Group room = new Group();
        Rectangle backroundDoor = new Rectangle(0,0, GUIConstants.ROOM_SIZE.x, GUIConstants.ROOM_SIZE.y);
        backroundDoor.setFill(GUIConstants.ROOM_DOORS_IMAGE);

        Button doorAButton = doorMaker(GUIConstants.DOOR_A, GUIConstants.DOOR_A_COORDINATES,
                GUIConstants.DOOR_A_DIMENSIONS, Position.LEFT);

        Button doorBButton = doorMaker(GUIConstants.DOOR_B, GUIConstants.DOOR_B_COORDINATES,
                GUIConstants.DOOR_B_DIMENSIONS, Position.UP);

        Button doorCButton = doorMaker(GUIConstants.DOOR_C, GUIConstants.DOOR_C_COORDINATES,
                GUIConstants.DOOR_C_DIMENSIONS, Position.RIGHT);

        Button doorDButton = buttonMaker(GUIConstants.DOOR_D, new Point(225,530));
        doorDButton.setOnAction(theEvent -> changePositions(Position.DOWN));

        Rectangle smallBrickSquare = new Rectangle(200, 500, 200,300);
        smallBrickSquare.setFill(GUIConstants.BRICK_ONLY);

        makeHidePanels();
        makeQuestionPanel();
        room.getChildren().addAll(smallBrickSquare, backroundDoor,
                doorAButton, hideDoorA, doorBButton, hideDoorB,
                doorCButton, hideDoorC, doorDButton, myQuestionPanel);
        checkForWindows();

        // Create button panel
        Group buttonPanel = new Group();
        Rectangle bottom = new Rectangle(0, 500,200,300);
        bottom.setFill(GUIConstants.BRICK_ONLY);
        Button saveButton = buttonMaker(GUIConstants.SAVE, new Point(50, 510));
        Button rulesButton = buttonMaker(GUIConstants.RULES,new Point(50, 580));
        Button shortcutButton = buttonMaker(GUIConstants.SHORTCUT, new Point(50, 650));
        //myShortcutButton.setOnAction(theEvent -> shortcut());
        buttonPanel.getChildren().addAll(bottom, shortcutButton, rulesButton, saveButton);

        // Create grid
        Group grid = new Group();
        Rectangle base = new Rectangle(350, 450, 250, 250);
        base.setFill(Color.WHITE);
        grid.getChildren().add(base);
        for (int i = 350; i <= 600; i+=50) {
            grid.getChildren().add(new Line(i, 450, i, 700));
            grid.getChildren().add(new Line(350, i + 100, 600, i + 100));
        }
        myGameFrame.getChildren().addAll(room, buttonPanel, grid, myIAmHereMarker);


        Scene scene = new Scene(myGameFrame, GUIConstants.SCREEN_SIZE.x, GUIConstants.SCREEN_SIZE.y);
        myStage.setScene(scene);
    }

    private void makeHidePanels(){
        hideDoorC = new Rectangle(483,0,117, GUIConstants.ROOM_SIZE.y );
        hideDoorC.setFill(new ImagePattern(new Image("file:doorCEmpty2.jpg")));
        hideDoorB = new Rectangle(114,0,370, GUIConstants.ROOM_SIZE.y );
        hideDoorB.setFill(new ImagePattern(new Image("file:doorBEmpty.jpg")));

        //hideDoorA = new Rectangle(0,0,114, GUIConstants.ROOM_SIZE.y );
        hideDoorA = new Rectangle(0,0,116, GUIConstants.ROOM_SIZE.y );
        hideDoorA.setFill(new ImagePattern(new Image("file:doorAEmpty2.jpg")));
    }

    private int centerQuestion(String theQuestion) {
        return 300 - theQuestion.length() * 4;
    }
    private void makeQuestionPanel(){
        myQuestionPanel = new Group();

        Rectangle blur = new Rectangle(0,0, GUIConstants.ROOM_SIZE.x, GUIConstants.ROOM_SIZE.y + 85);
        blur.setOpacity(0.2);
        Rectangle scroll = new Rectangle(100,150, 400,200);
        scroll.setFill(GUIConstants.SCROLL_IMAGE);

        myQuestion = new Label(GUIConstants.testQuestion);
        myQuestion.setFont(GUIConstants.GENERAL_FONT);
        myQuestion.relocate(centerQuestion(GUIConstants.testQuestion) , 220);

        myResponse = new TextField();
        myResponse.relocate(220, 280);
        myResponse.setOnAction(theEvent -> myQuestionPanel.setVisible(false));
        myQuestionPanel.getChildren().addAll(blur, scroll, myQuestion, myResponse);
        myQuestionPanel.setVisible(false);
    }

    private void createEasyMediumHardPanel() {
        myDifficultyPanel = new Group();

        Button easy = buttonMaker(GUIConstants.EASY, GUIConstants.EASY_COORDINATES);
        Button medium = buttonMaker(GUIConstants.MEDIUM, GUIConstants.MEDIUM_COORDINATES);
        Button hard = buttonMaker(GUIConstants.HARD, GUIConstants.HARD_COORDINATES);
        easy.setOnAction(theEvent -> setButtonSetting(GUIConstants.DIFFICULTY, GUIConstants.EASY, myDifficultyPanel));
        medium.setOnAction(theEvent -> setButtonSetting(GUIConstants.DIFFICULTY, GUIConstants.MEDIUM, myDifficultyPanel));
        hard.setOnAction(theEvent -> setButtonSetting(GUIConstants.DIFFICULTY, GUIConstants.HARD, myDifficultyPanel));

        myDifficultyPanel.getChildren().addAll(easy, medium, hard);
        myStartMenu.getChildren().add(myDifficultyPanel);
        myDifficultyPanel.setVisible(false);
    }

    private void createThemedButtonsPanel() {
        myThemePanel = new Group();

        Button themeOne = buttonMaker(GUIConstants.OO, GUIConstants.THEME_ONE_COORDINATES);
        Button themeTwo = buttonMaker(GUIConstants.ONE_PIECE, GUIConstants.THEME_TWO_COORDINATES);
        Button themeThree = buttonMaker(GUIConstants.ANOTHER_THEME, GUIConstants.THEME_THREE_COORDINATES);

        themeOne.setOnAction(theEvent -> setButtonSetting(GUIConstants.THEME, GUIConstants.OO, myThemePanel));
        themeTwo.setOnAction(theEvent -> setButtonSetting(GUIConstants.THEME, GUIConstants.ONE_PIECE, myThemePanel));
        themeThree.setOnAction(theEvent -> setButtonSetting(GUIConstants.THEME, GUIConstants.ANOTHER_THEME, myThemePanel));

        myThemePanel.getChildren().addAll(themeOne, themeTwo, themeThree);
        myStartMenu.getChildren().add(myThemePanel);
        myThemePanel.setVisible(false);
    }

    private void setVisibility(Group thePanel) {
        GUIConstants.ABOUT_DESCRIPTION.setVisible(false);
        flipStartOptionsVisible();
        thePanel.setVisible(true);
    }

    private void flipStartOptionsVisible() {
        for (String theButtonName: GUIConstants.myStartMenuButtons) {
            Button button = myButtons.get(theButtonName);
            button.setVisible(!button.isVisible());
        }
    }

    private void setButtonSetting(String theButton, String theSetting, Group thePanel) {
        myButtons.get(theButton).setText(theSetting);
        thePanel.setVisible(false);
        flipStartOptionsVisible();
    }

    private void createPanels() {
        // create About label
        GUIConstants.ABOUT_DESCRIPTION.relocate(105,500);
        GUIConstants.ABOUT_DESCRIPTION.setFont(GUIConstants.GENERAL_FONT);
        GUIConstants.ABOUT_DESCRIPTION.setBackground(Background.fill(Color.LIGHTGRAY));
        myStartMenu.getChildren().add(GUIConstants.ABOUT_DESCRIPTION);
        GUIConstants.ABOUT_DESCRIPTION.setVisible(false);
    }

    private Button doorMaker(final String theName, final Point theCoordinate, final Point theDimenstion, final Point theDirection) {
        Button button = buttonMaker(theName, theCoordinate);
        button.setPrefSize(theDimenstion.x, theDimenstion.y);
        button.setStyle(GUIConstants.TRANSPARENT);
        button.setOnAction(theEvent -> changePositions(theDirection));
        return button;
    }

    private Button buttonMaker(final String theName, final Point theCoordinate) {
        Button button = new Button(theName);
        button.setPrefSize(GUIConstants.BUTTON_SIZE.x, GUIConstants.BUTTON_SIZE.y);
        button.relocate(theCoordinate.x, theCoordinate.y);
        myButtons.put(theName, button);
        return button;
    }
    private void changePositions(Point theChange) {
        myResponse.clear();
        myButtons.get(GUIConstants.SAVE).setVisible(false);

        myQuestionPanel.setVisible(true);
        myPosition.changePositions(theChange);
        myIAmHereMarker.relocate(myPosition.myPosition.x, myPosition.myPosition.y);
        checkForWindows();
    }

    private void checkForWindows() {
        int xCoordinate = myPosition.myPosition.x;
        int yCoordinate = myPosition.myPosition.y;

        myButtons.get(GUIConstants.DOOR_A).setVisible(xCoordinate > 365);
        hideDoorA.setVisible(!(xCoordinate > 365));

        myButtons.get(GUIConstants.DOOR_B).setVisible(yCoordinate > 465);
        hideDoorB.setVisible(!(yCoordinate > 465));

        myButtons.get(GUIConstants.DOOR_C).setVisible(xCoordinate < 565);
        hideDoorC.setVisible(!(xCoordinate < 565));

        myButtons.get(GUIConstants.DOOR_D).setVisible(yCoordinate < 665);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
    }
}
//        myDoorAButton.setPrefSize(GUIConstants.DOOR_A_DIMENSIONS.x, GUIConstants.DOOR_A_DIMENSIONS.y);
//        myDoorAButton.relocate(GUIConstants.DOOR_A_COORDINATES.x, GUIConstants.DOOR_A_COORDINATES.y);
//        myDoorAButton.setOnAction(theEvent -> changePositions(Position.LEFT));
//        myDoorAButton.setStyle(GUIConstants.TRANSPARENT);
//
//        //myDoorBButton = buttonMaker("B", new Point(250,100));
//        myDoorBButton = new Button("_B");
//        myDoorBButton.setPrefSize(GUIConstants.DOOR_B_DIMENSIONS.x, GUIConstants.DOOR_B_DIMENSIONS.y);
//        myDoorBButton.relocate(GUIConstants.DOOR_B_COORDINATES.x, GUIConstants.DOOR_B_COORDINATES.y);
//        myDoorBButton.setOnAction(theEvent -> changePositions(Position.UP));
//        myDoorBButton.setStyle(GUIConstants.TRANSPARENT);

//myDoorCButton = buttonMaker("C", new Point(500,150));
//        myDoorCButton = new Button("_C");
//        myDoorCButton.setPrefSize(GUIConstants.DOOR_C_DIMENSIONS.x, GUIConstants.DOOR_C_DIMENSIONS.y);
//        myDoorCButton.relocate(GUIConstants.DOOR_C_COORDINATES.x, GUIConstants.DOOR_C_COORDINATES.y);
//        myDoorCButton.setOnAction(theEvent -> changePositions(Position.RIGHT));
//        myDoorCButton.setStyle(GUIConstants.TRANSPARENT);