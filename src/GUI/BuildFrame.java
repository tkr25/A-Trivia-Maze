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

    Button startButton;

    Button doorAButton;

    Button doorBButton;

    Button doorCButton;

    Button doorDButton;
    Button DifficultyButton;

    Button themeButton;

    Button aboutButton;

    Button loadButton; //rgba(173, 165, 191, 1)
    Color baseColor = new Color(173.0/255, 165.0/255, 191.0/255, 1);

    Circle me;

    Point myDirection;
    Group gameFrame;
    Position myPosition;
    Stage myStage;
    ImageView rainImage = new ImageView(new Image("file:83196.jpg"));
    Image brick = new Image("file:brick.jpg");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("THE AMAZING MAZE");
        primaryStage.getIcons().add(new Image("file:logo.png"));
        myStage = primaryStage;
        setStartMenu();
        primaryStage.show();
    }

    private void setStartMenu() {
        rainImage.setFitWidth(680);
        rainImage.setFitHeight(680);
        rainImage.setLayoutX(10);
        rainImage.setLayoutY(10);
        rainImage.setOpacity(0.5);

        Label title = new Label("Amazing Maze");
        title.setScaleX(7);
        title.setScaleY(15);
        title.setLayoutX(300);
        title.setLayoutY(200);
        title.setTextFill(Color.BLACK);

        startButton = new Button("     START     ");
        DifficultyButton = new Button(" DIFFICULTY ");
        aboutButton = new Button("ABOUT");
        loadButton = new Button("LOAD GAME");
        themeButton = new Button("THEME");
        buttonMaker(startButton, new Point(300, 350));
        buttonMaker(loadButton, new Point(300, 425));
        buttonMaker(aboutButton, new Point(150, 500));
        buttonMaker(DifficultyButton, new Point(300, 500));
        buttonMaker(themeButton, new Point(475, 500));
        Group root = new Group();
        root.getChildren().addAll(rainImage, DifficultyButton, startButton,
                loadButton, themeButton, aboutButton, title);

        Scene scene = new Scene(root, 700, 700, Color.GREY);
        myStage.setScene(scene);
    }

    private void buildGameDisplay() {

    }

    private void startGame() {
        myDirection = Position.LEFT;
        myPosition = new Position();

        // Create you are here marker
        me = new Circle(10);
        me.setLayoutX(475);
        me.setLayoutY(475);

        // Create frame for gameplay
        gameFrame = new Group();

        // Create the 3 doors
        Group doors = new Group();
        Polygon doorA = new Polygon(250, 100, 250, 250, 350, 250, 350, 100);
        doorA.setFill(new ImagePattern(new Image("file:doorA.jpg")));

        doorAButton = new Button("A");
        doorAButton.setPrefSize(100,150);
        //doorAButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
        doorAButton.setLayoutX(50);
        doorAButton.setLayoutY(150);
        doorAButton.setOnAction(this);

        doorBButton = new Button("B");
        doorBButton.setPrefSize(100,150);
        doorBButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
        doorBButton.setLayoutX(250); //50
        doorBButton.setLayoutY(100); //150
        doorBButton.setOnAction(this);

        doorCButton = new Button("C");
        doorCButton.setPrefSize(100,150);
        //doorCButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
        doorCButton.setLayoutX(500);
        doorCButton.setLayoutY(150);
        doorCButton.setOnAction(this);

        doorDButton = new Button("D");
        doorDButton.setPrefSize(50,50);
        //doorDButton.setStyle("-fx-background-color: rgba(1, 10, 50, 0)");
        doorDButton.setLayoutX(350);
        doorDButton.setLayoutY(550);
        doorDButton.setOnAction(this);

        doors.getChildren().addAll(doorA,doorBButton,doorCButton,doorAButton, doorDButton);

        // Create Walls
        Polygon wallA = new Polygon(0 ,0, 0, 500, 200, 200, 200, 0);
        wallA.setFill(new ImagePattern(new Image("file:sideBrick.jpg"), 0, 0, 1.7, 1, true));
        Rectangle wallB = new Rectangle(168, 0,364,250);
        wallB.setFill(new ImagePattern(brick, -.1, 0, 100, 80, false));
        Polygon wallC = new Polygon(532,0, 700, 0, 700, 500, 532, 250);
        wallC.setFill(baseColor.brighter());
        wallC.setFill(new ImagePattern(new Image("file:sideBrick.jpg"), -0.8, -0.1, 1.7, 1, true));

        // Create Floor
        Rectangle floor = new Rectangle(0,0,750, 500);
        floor.setFill(new ImagePattern( new Image("file:floor.jpg"), 0,0,0,0,true));

        // Create button panel
        Group buttonPanel = new Group();
        Rectangle bottom = new Rectangle(0, 500,300,300);
        bottom.setFill(Color.TAN);
        Button rulesButton = new Button("RULES");
        Button saveButton = new Button("SAVE");
        Button shortcutButton = new Button("SHORTCUTS");
        buttonMaker(saveButton, new Point(125, 530));
        buttonMaker(rulesButton, new Point(125, 600));
        buttonMaker(shortcutButton, new Point(110, 670));
        buttonPanel.getChildren().addAll(bottom, shortcutButton, rulesButton, saveButton);

        // Create grid
        Group grid = new Group();
        Rectangle base = new Rectangle(450, 450, 250, 250);
        base.setFill(Color.WHITE);
        Line line = new Line(500,450, 500, 800);
        grid.getChildren().add(base);
        for (int i = 450; i <= 700; i+=50) {
            grid.getChildren().add(new Line(i, 450, i, 700));
            grid.getChildren().add(new Line(450, i, 700, i));
        }

        gameFrame.getChildren().addAll(floor, wallA, wallC, wallB,  buttonPanel, grid, doors, me);

        Scene scene = new Scene(gameFrame, 800, 750);
        myStage.setScene(scene);
    }

//    private void about() {
//        Popup pop = PopupBuilder.create().width(50).height(100).autoFix(true).build();
//        pop.show(myStage);
//    }

    private void changePositions(Point theChange) {
        myPosition.changePositions(theChange);
        me.setLayoutX(myPosition.myPosition.x);
        me.setLayoutY(myPosition.myPosition.y);
        myStage.setScene(new Scene(new Group(gameFrame)));
    }
    private void buttonMaker(Button theButton, Point theCoordinate) {
        buttonMaker(theButton,theCoordinate, new Point(2,2), false);
    }
    private void buttonMaker(Button theButton, Point theCoordinate, Point theScale, boolean theBlendIn) {
        theButton.setOnAction(this);
        theButton.setLayoutX(theCoordinate.x);
        theButton.setLayoutY(theCoordinate.y);
        theButton.setScaleX(2);
        theButton.setScaleY(2);
        if (theBlendIn) {
            theButton.setStyle("-fx-background-color: rgba(84, 72, 113, 0.46)");
        }
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == startButton) {
            startGame();
        } else if (source == doorAButton) {
            changePositions(Position.LEFT);
        } else if (source == doorBButton) {
            changePositions(Position.UP);
        } else if (source == doorCButton) {
            changePositions(Position.RIGHT);
        } else if (source == doorDButton) {
            changePositions(Position.DOWN);
        }
    }
}
