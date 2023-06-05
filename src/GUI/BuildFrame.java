package GUI;


import Model.Maze;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.awt.*;
import java.util.HashMap;


public class BuildFrame extends Application implements EventHandler<ActionEvent> {

    Maze myMaze;
    Circle myIAmHereMarker;
    Group myGameFrame;

    Group myThemePanel;
    Group myStartMenu;

    Group myQuestionPanel;

    Label myQuestion;
    Label myQuestion2;

    Label myQuestion3;

    TextField myResponse;
    Point myDesiredDirection;

    Position myPosition;

    HashMap<String, Button> myButtons;

    Rectangle hideDoorA;
    Rectangle hideDoorB;
    Rectangle hideDoorC;

    Stage myStage;

    String myTheme = GUIConstants.JAVA;

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

        GUIConstants.RAIN_IMAGE.setFitWidth(GUIConstants.SCREEN_SIZE.x - GUIConstants.BORDER * 2);
        GUIConstants.RAIN_IMAGE.setFitHeight(GUIConstants.SCREEN_SIZE.y - GUIConstants.BORDER * 2);
        GUIConstants.RAIN_IMAGE.relocate(GUIConstants.BORDER,GUIConstants.BORDER);
        GUIConstants.RAIN_IMAGE.setOpacity(0.25);

        // create title
        Label title = new Label(GUIConstants.TITLE);
        title.setFont(GUIConstants.TITLE_FONT);
        title.relocate(GUIConstants.TITLE_COORDINATES.x, GUIConstants.TITLE_COORDINATES.y);

        // create start menu buttons
        Button startButton = buttonMaker(GUIConstants.START, GUIConstants.START_COORDINATES);
        Button loadButton = buttonMaker(GUIConstants.LOAD, GUIConstants.DIFFICULTY_COORDINATES);
        startButton.setPrefSize(GUIConstants.BUTTON_SIZE.x * 2 + 10, GUIConstants.BUTTON_SIZE.y);
        Button aboutButton = buttonMaker(GUIConstants.ABOUT, GUIConstants.ABOUT_COORDINATES);
        Button themeButton = buttonMaker(GUIConstants.THEME, GUIConstants.THEME_COORDINATES);

        startButton.setOnAction(theEvent -> {
            try {
                startGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        aboutButton.setOnAction(theEvent -> GUIConstants.ABOUT_DESCRIPTION.setVisible(!GUIConstants.ABOUT_DESCRIPTION.isVisible()));
        themeButton.setOnAction(theEvent -> setVisibility(myThemePanel));

        myStartMenu.getChildren().addAll(GUIConstants.RAIN_IMAGE, startButton, //difficultyButton,
                loadButton, themeButton, aboutButton, title);

        createThemedButtonsPanel();
        createLabel(myStartMenu, GUIConstants.ABOUT_DESCRIPTION, GUIConstants.ABOUT_LABEL_COORDINATES);
        Scene scene = new Scene(myStartMenu, GUIConstants.SCREEN_SIZE.x, GUIConstants.SCREEN_SIZE.y, Color.GREY);
        myStage.setScene(scene);
    }

    private void startGame() throws Exception {
        myPosition = new Position();
        myMaze = new Maze(myTheme);

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

        Button doorDButton = buttonMaker(GUIConstants.DOOR_D, GUIConstants.DOOR_D_COORDINATES);
        doorDButton.setOnAction(theEvent -> doorClicked(Position.DOWN));

        Rectangle smallBrickSquare = new Rectangle(200, 500, 200,300);
        smallBrickSquare.setFill(GUIConstants.BRICK_ONLY);

        makeHidePanels();
        makeQuestionPanel();
        room.getChildren().addAll(smallBrickSquare, backroundDoor,
                doorAButton, hideDoorA, doorBButton, hideDoorB,
                doorCButton, hideDoorC, doorDButton, myQuestionPanel);
        //checkForWindows();
        checkForWalls();

        // Create button panel
        Group buttonPanel = new Group();
        Rectangle bottom = new Rectangle(0, 500,200,300);
        bottom.setFill(GUIConstants.BRICK_ONLY);
        Button saveButton = buttonMaker(GUIConstants.SAVE, new Point(50, 510));
        Button rulesButton = buttonMaker(GUIConstants.RULES,new Point(50, 580));
        Button shortcutButton = buttonMaker(GUIConstants.SHORTCUT, new Point(50, 650));

        shortcutButton.setOnAction(theEvent -> GUIConstants.SHORTCUT_DESCRIPTION.setVisible(!GUIConstants.SHORTCUT_DESCRIPTION.isVisible()));
        rulesButton.setOnAction(theEvent -> GUIConstants.RULES_DESCRIPTION.setVisible(!GUIConstants.RULES_DESCRIPTION.isVisible()));
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
        createLabel(myGameFrame, GUIConstants.SHORTCUT_DESCRIPTION, GUIConstants.SHORTCUT_LABEL_COORDINATES);
        createLabel(myGameFrame,GUIConstants.RULES_DESCRIPTION, GUIConstants.RULES_LABEL_COORDINATES);

        Scene scene = new Scene(myGameFrame, GUIConstants.SCREEN_SIZE.x, GUIConstants.SCREEN_SIZE.y);
        myStage.setScene(scene);
    }

    private void makeHidePanels(){
        hideDoorC = new Rectangle(483,0,117, GUIConstants.ROOM_SIZE.y);
        hideDoorC.setFill(GUIConstants.DOOR_C_LOCKED_IMAGE);
        hideDoorB = new Rectangle(114,0,370, GUIConstants.ROOM_SIZE.y);
        hideDoorB.setFill(GUIConstants.DOOR_B_LOCKED_IMAGE);

        hideDoorA = new Rectangle(0,0,116, GUIConstants.ROOM_SIZE.y);
        hideDoorA.setFill(GUIConstants.DOOR_A_LOCKED_IMAGE);
    }

    private int centerQuestion(String theQuestion) {
        return 300 - theQuestion.length() * 4;
    }

    private void checkAnswer() {
        boolean isCorrect = myMaze.checkAnswer(myResponse.getText());

        if (isCorrect) {
            changePositions(myDesiredDirection);
        } else {
            checkForWalls();
        }
        if (!Maze.isThereWayOut(myMaze.toString())) {
            myButtons.get(GUIConstants.RULES).setVisible(false);
            myButtons.get(GUIConstants.SHORTCUT).setVisible(false);
            gameOver(false);
        }
        myButtons.get(GUIConstants.SAVE).setVisible(true);
        myQuestionPanel.setVisible(false);
    }

    private void gameOver(boolean youWon){
        Group gameOverPanel = new Group();

        Rectangle blur = new Rectangle(0,0, GUIConstants.SCREEN_SIZE.x, GUIConstants.SCREEN_SIZE.y);
        blur.setOpacity(0.2);
        blur.setFill(Color.WHITE);
        Rectangle scroll = GUIConstants.SCROLL_DIMENTIONS_AND_COORDINATES;
        scroll.setFill(GUIConstants.SCROLL_IMAGE);
        Label endingMessage = new Label();
        if (youWon) {
            endingMessage.setText("YOU WON \n GAME OVER");
        } else {
            endingMessage.setText("NO WAY OUT \n GAME OVER");
        }
        endingMessage.setFont(GUIConstants.GAME_OVER_FONT);
        endingMessage.relocate(120, 190);
        gameOverPanel.getChildren().addAll(blur, scroll, endingMessage);
        gameOverPanel.setVisible(true);
        myGameFrame.getChildren().add(gameOverPanel);
    }

    private void makeQuestionPanel(){
        myQuestionPanel = new Group();

        Rectangle blur = new Rectangle(0,0, GUIConstants.ROOM_SIZE.x, GUIConstants.ROOM_SIZE.y);
        blur.setOpacity(0.2);
        Rectangle scroll = GUIConstants.SCROLL_DIMENTIONS_AND_COORDINATES;
        scroll.setFill(GUIConstants.SCROLL_IMAGE);

        myQuestion = new Label();
        myQuestion.setFont(GUIConstants.GENERAL_FONT);
        myQuestion2 = new Label();
        myQuestion2.setFont(GUIConstants.GENERAL_FONT);
        myQuestion3 = new Label();
        myQuestion3.setFont(GUIConstants.GENERAL_FONT);

        myResponse = new TextField();
        myResponse.relocate(220, 280);
        myResponse.setOnAction(theEvent -> checkAnswer());
        myQuestionPanel.getChildren().addAll(blur, scroll, myQuestion, myQuestion2, myQuestion3, myResponse);
        myQuestionPanel.setVisible(false);
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
        myTheme = theSetting;
        flipStartOptionsVisible();
    }

    private void createLabel(Group thePanel, Label theLabel, Point theCoordinates) {
        // create About label
        theLabel.relocate(theCoordinates.x, theCoordinates.y);
        theLabel.setFont(GUIConstants.GENERAL_FONT);
        theLabel.setBackground(Background.fill(Color.LIGHTGRAY));
        thePanel.getChildren().add(theLabel);
        theLabel.setVisible(false);
    }

    private Button doorMaker(final String theName, final Point theCoordinate, final Point theDimenstion, final Point theDirection) {
        Button button = buttonMaker(theName, theCoordinate);
        button.setPrefSize(theDimenstion.x, theDimenstion.y);
        button.setStyle(GUIConstants.TRANSPARENT);
        button.setOnAction(theEvent -> doorClicked(theDirection));
        return button;
    }

    private Button buttonMaker(final String theName, final Point theCoordinate) {
        Button button = new Button(theName);
        button.setPrefSize(GUIConstants.BUTTON_SIZE.x, GUIConstants.BUTTON_SIZE.y);
        button.relocate(theCoordinate.x, theCoordinate.y);
        myButtons.put(theName, button);
        return button;
    }


    private void createThemedButtonsPanel() {
        myThemePanel = new Group();

        Button themeOne = buttonMaker(GUIConstants.JAVA, GUIConstants.THEME_ONE_COORDINATES);
        Button themeTwo = buttonMaker(GUIConstants.ONE_PIECE, GUIConstants.THEME_TWO_COORDINATES);
        Button themeThree = buttonMaker(GUIConstants.HARRY_POTTER, GUIConstants.THEME_THREE_COORDINATES);

        themeOne.setOnAction(theEvent -> setButtonSetting(GUIConstants.THEME, GUIConstants.JAVA, myThemePanel));
        themeTwo.setOnAction(theEvent -> setButtonSetting(GUIConstants.THEME, GUIConstants.ONE_PIECE, myThemePanel));
        themeThree.setOnAction(theEvent -> setButtonSetting(GUIConstants.THEME, GUIConstants.HARRY_POTTER, myThemePanel));

        myThemePanel.getChildren().addAll(themeOne, themeTwo, themeThree);
        myStartMenu.getChildren().add(myThemePanel);
        myThemePanel.setVisible(false);
    }
    private void doorClicked(Point theDirection) {
        myResponse.clear();
        myDesiredDirection = theDirection;
        myButtons.get(GUIConstants.SAVE).setVisible(false);
        myMaze.setIntendedDirection(theDirection);

        String q = myMaze.getCurrentQ();
        if (q.length() == 1) {
            changePositions(myDesiredDirection);
        } else {
            myQuestion.setText(" ");
            myQuestion2.setText(" ");
            myQuestion3.setText(" ");
            int placement = 240;
            if (q.length() >= GUIConstants.MAX_LETTERS_ON_LINE) {
                String temp = q.substring(GUIConstants.MAX_LETTERS_ON_LINE);
                int cutAtIndex = GUIConstants.MAX_LETTERS_ON_LINE + 1 + temp.indexOf(" ");

                myQuestion.setText(q.substring(0, cutAtIndex));
                myQuestion.relocate(centerQuestion(myQuestion.getText()), placement - 20);
                q = q.substring(cutAtIndex);
                if (q.length() >= GUIConstants.MAX_LETTERS_ON_LINE) {
                    temp = q.substring(GUIConstants.MAX_LETTERS_ON_LINE);
                    cutAtIndex = GUIConstants.MAX_LETTERS_ON_LINE + 1 + temp.indexOf(" ");
                    myQuestion2.setText(q.substring(0, cutAtIndex));
                    q = q.substring(cutAtIndex);
                    myQuestion2.relocate(centerQuestion(myQuestion2.getText()), placement);
                    placement += 20;
                }
            }
            myQuestion3.setText(q);
            myQuestion3.relocate(centerQuestion(myQuestion3.getText()), placement);

            //myMaze.getDoor();
            myQuestionPanel.setVisible(true);
        }
    }
    private void changePositions(Point theChange) {
        myPosition.changePositions(theChange);
        myMaze.setMyPosition(myPosition.convertPoisition());
        myIAmHereMarker.relocate(myPosition.myPosition.x, myPosition.myPosition.y);
        if (myPosition.convertPoisition().equals(GUIConstants.WINNING_POSITION)) {
            gameOver(true);
        } else {
            System.out.println(myPosition.convertPoisition());
            checkForWalls();
        }
    }


    @Override
    public void handle(ActionEvent actionEvent) {
    }
    private void checkForWalls() {
        Point modelPos = myPosition.convertPoisition();

        boolean isDoorAVisible = myMaze.myLeftRightDoors[modelPos.x][modelPos.y].getDoorState() < 2;
        boolean isDoorBVisible = myMaze.myUpDownDoors[modelPos.x][modelPos.y].getDoorState() < 2;
        boolean isDoorCVisible = myMaze.myLeftRightDoors[modelPos.x][modelPos.y + 1].getDoorState() < 2;
        boolean isDoorDVisible = myMaze.myUpDownDoors[modelPos.x + 1][modelPos.y].getDoorState() < 2;

        myButtons.get(GUIConstants.DOOR_A).setVisible(isDoorAVisible);
        hideDoorA.setVisible(!isDoorAVisible);

        myButtons.get(GUIConstants.DOOR_B).setVisible(isDoorBVisible);
        hideDoorB.setVisible(!isDoorBVisible);

        myButtons.get(GUIConstants.DOOR_C).setVisible(isDoorCVisible);
        hideDoorC.setVisible(!isDoorCVisible);

        myButtons.get(GUIConstants.DOOR_D).setVisible(isDoorDVisible);
    }
}