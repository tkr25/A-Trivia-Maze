package GUI;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class GUIConstants {

    final static String testQuestion = "What Color is Robins hair?";


    final static Image LOGO = new Image("file:logo.png");

    final static ImagePattern BRICK_ONLY = new ImagePattern( new Image("file:brick.jpg"));
    final static ImageView RAIN_IMAGE = new ImageView(new Image("file:83196.jpg"));
    final static ImagePattern ROOM_DOORS_IMAGE = new ImagePattern(new Image("file:brickWallDoor.jpg"));

    final static ImagePattern SCROLL_IMAGE = new ImagePattern(new Image("file:paper.JPG"));

    final static Font GENERAL_FONT = new Font("Bodoni MT Black", 15);

    final static Font TITLE_FONT = new Font("Bodoni MT Black", 70);
    final static Point DOOR_A_DIMENSIONS = new Point(80,220);

    final static Point DOOR_A_COORDINATES = new Point(15,175);

    final static Point DOOR_B_DIMENSIONS = new Point(100,160);

    final static Point DOOR_B_COORDINATES = new Point(255,175);

    final static Point DOOR_C_DIMENSIONS = new Point(70,220);

    final static Point DOOR_C_COORDINATES = new Point(500,180);

    final static Point BUTTON_SIZE = new Point(100,50);

    final static Point SCREEN_SIZE = new Point(600, 700);

    final static Point ROOM_SIZE = new Point(600, 500);

    final static Point START_COORDINATES = new Point(175, 350);
    final static Point LOAD_COORDINATES = new Point(300, 350);

    final static Point DIFFICULTY_COORDINATES = new Point(250, 425);

    final static Point ABOUT_COORDINATES = new Point(100, 425);
    final static Point EASY_COORDINATES = new Point(250,425);
    final static Point MEDIUM_COORDINATES = new Point(250,495);
    final static Point HARD_COORDINATES =  new Point(250, 565);

    final static Point THEME_ONE_COORDINATES = new Point(400,425);

    final static Point THEME_TWO_COORDINATES = new Point(400,495);

    final static Point THEME_THREE_COORDINATES = new Point(400, 565);

    final static Point THEME_COORDINATES = new Point(400, 425);

    final static String TITLE = "AMAZING\n  MAZE";

    final static String TRANSPARENT = "-fx-background-color: rgba(0, 0, 0, 0)";

    final static String START = "START";

    final static String LOAD = "LOAD";

    final static String SAVE = "SAVE";
    final static String RULES = "RULES";
    final static String SHORTCUT = "SHORTCUT";
    final static String DIFFICULTY = "DIFFICULTY";
    final static String ABOUT = "ABOUT";

    final static String DOOR_A = "DOOR_A";

    final static String DOOR_B = "DOOR_B";

    final static String DOOR_C = "DOOR_C";

    final static String DOOR_D = "DOOR_D";
    final static Label ABOUT_DESCRIPTION =  new Label("  AMAZING MAZE IS A TRIVIA MAZE GAME WHERE\n " +
            " YOU ARE STUCK LOST IN AN ABANDONED BUILDING\n " +
            " THAT HAS A BUNCH DOORS THAT HAVE BEEN CURSED\n " +
            " THE ONLY WAY TO PASS THROUGH ONE IS TO GET THE \n" +
            "  ANSWER THE QUESTION CORRECTLY. GUESS WRONG \n " +
            "    AND THE DOORWAY WILL BE CLOSED FOREVER!!\n\n " +
            "               GOOD LUCK ESCAPING... \n\n\n\n");
    final static String THEME = "THEME";

    final static String MEDIUM = "MEDIUM";
    final static String EASY = "EASY";

    final static String HARD = "HARD";

    final static ArrayList<String> myStartMenuButtons =
            new ArrayList<> (Arrays.asList(START, LOAD, ABOUT, THEME, DIFFICULTY));

    final static ArrayList<String> myDoorButtons =
            new ArrayList<> (Arrays.asList(DOOR_A, DOOR_B, DOOR_C, DOOR_D));

    final static String OO = "OO";
    final static String ONE_PIECE = "ONE PIECE";
    final static String ANOTHER_THEME = "ANOTHER THEME";
}
