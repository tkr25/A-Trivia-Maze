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
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;


public class BuildFrame extends Application implements EventHandler<ActionEvent> {

    Button startButton;

    Button DifficultyButton;

    Button themeButton;

    Button aboutButton;

    Button loadButton; //rgba(173, 165, 191, 1)
    Color baseColor = new Color(173.0/255, 165.0/255, 191.0/255, 1);
    Stage myStage;
    ImageView rainImage = new ImageView(new Image("file:83196.jpg"));
    Image maze = new Image("file:maze.jpg");
    ImageView mazeImage = new ImageView();
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
        buttonMaker(startButton, new Point(300, 350), new Point(2,2));
        buttonMaker(loadButton, new Point(300, 425), new Point(2,2));
        buttonMaker(aboutButton, new Point(150, 500), new Point(2,2));
        buttonMaker(DifficultyButton, new Point(300, 500), new Point(2, 2));
        buttonMaker(themeButton, new Point(475, 500), new Point(2,2));
        Group root = new Group();
        root.getChildren().addAll(rainImage, DifficultyButton, startButton,
                loadButton, themeButton, aboutButton, title);

        Scene scene = new Scene(root, 700, 700, Color.GREY);
        myStage.setScene(scene);
    }

    private void startGame() {
        Group root = new Group();
        ImageView doorA = doorMaker(new Point(400, 100));
        ImageView doorB = doorMaker(new Point(100, 300));
        ImageView doorC = doorMaker(new Point(700, 300));
        Polygon wallA = new Polygon(0,0, 0, 500, 300, 200, 300, 0);
        wallA.setFill(baseColor.brighter());
        Rectangle wallB = new Rectangle(250, 0,400,250);
        wallB.setFill(baseColor);
        Polygon wallC = new Polygon(600,0, 900, 0, 900, 500, 600, 200);
        wallC.setFill(baseColor.brighter());

        Rectangle bottom = new Rectangle(0, 500,400,400);
        bottom.setFill(Color.TAN);

        root.getChildren().addAll(wallA, wallC, wallB, doorA, doorB, doorC, bottom);

        Scene scene = new Scene(root, 900, 900, Color.WHITE);
        myStage.setScene(scene);
    }

    private ImageView doorMaker(Point theCoordinates) {
        ImageView door = new ImageView(new Image("file:door.jpg"));
        door.setFitWidth(100);
        door.setFitHeight(150);
        door.setLayoutX(theCoordinates.x);
        door.setLayoutY(theCoordinates.y);
        return door;
    }
    private void buttonMaker(Button theButton, Point theCoordinate, Point theScale) {
        theButton.setOnAction(this);
        theButton.setLayoutX(theCoordinate.x);
        theButton.setLayoutY(theCoordinate.y);
        theButton.setScaleX(theScale.x);
        theButton.setScaleY(theScale.y);
        //startButton.setStyle("-fx-background-color: rgba(84, 72, 113, 0.46)");
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == startButton) {
            startGame();
        }
    }
}
//    Polygon p = new Polygon();
//
//        p.setLayoutX(10);
//                p.setLayoutY(10);
//                p.getPoints().add(0.0); // Point A: x coordinate
//                p.getPoints().add(0.0); // Point A: y coordinate
//                p.getPoints().add(300.0); // Point B: x coordinate
//                p.getPoints().add(0.0); // Point B: y coordinate
//                p.getPoints().add(300.0);  // Point C: x coordinate
//                p.getPoints().add(300.0);// Point C: y
//                p.getPoints().add(0.0);
//                p.getPoints().add(300.0);
//
//                p.setFill(new ImagePattern(maze, 0, 0, 150, 50, false));
