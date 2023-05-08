package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.EventListener;


public class BuildFrame extends Application implements EventHandler<ActionEvent> {

    Button startButton;
    Stage myStage;
    ImageView rainImage = new ImageView(new Image("file:83196.jpg"));
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
        Image maze = new Image("file:maze.jpg");
        ImageView mazeMV = new ImageView(maze);
        rainImage.setFitWidth(480);
        rainImage.setFitHeight(480);
        rainImage.setLayoutX(10);
        rainImage.setLayoutY(10);
        mazeMV.setFitWidth(100);
        mazeMV.setFitHeight(50);

        startButton = new Button("START", mazeMV);
        startButton.setOnAction(this);
        startButton.setLayoutX(200);
        startButton.setLayoutY(200);
        Group root = new Group();
        root.getChildren().addAll(rainImage, startButton);

        Scene scene = new Scene(root, 500, 500, Color.DARKSLATEBLUE);
        myStage.setScene(scene);
    }

    private void startGame() {
        Group root = new Group();
        root.getChildren().addAll(rainImage);

        Scene scene = new Scene(root, 500, 500, Color.GOLD);
        myStage.setScene(scene);
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
