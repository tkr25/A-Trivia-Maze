package GUI;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;


public class GUIConstants {

    final static String testQuestion = "What Color is Robins hair?";


    final static Image LOGO = new Image("file:logo.png");

    final static ImagePattern BRICK_ONLY = new ImagePattern( new Image("file:brick.jpg"));
    final static ImageView RAIN_IMAGE = new ImageView(new Image("file:83196.jpg"));
    final static ImagePattern ROOM_DOORS_IMAGE = new ImagePattern(new Image("file:brickWallDoor.jpg"));

    final static ImagePattern SCROLL_IMAGE = new ImagePattern(new Image("file:paper.JPG"));

    final static ImagePattern DOOR_A_LOCKED_IMAGE = new ImagePattern(new Image("file:doorAEmpty2.jpg"));

    final static ImagePattern DOOR_B_LOCKED_IMAGE = new ImagePattern(new Image("file:doorBEmpty.jpg"));

    final static ImagePattern DOOR_C_LOCKED_IMAGE = new ImagePattern(new Image("file:doorCEmpty2.jpg"));


    final static int BORDER = 10;

    final static int MAX_LETTERS_ON_LINE = 38;
    final static Font GENERAL_FONT = new Font("Bodoni MT Black", 15);
    final static Font GAME_OVER_FONT = new Font("Bodoni MT Black", 50);
    final static Font TITLE_FONT = new Font("Bodoni MT Black", 70);

    final public static Point GUI_ORGIN = new Point(new Point (365, 465));
    final static Point DOOR_A_DIMENSIONS = new Point(80,220);

    final static Point DOOR_A_COORDINATES = new Point(15,175);

    final static Point DOOR_B_DIMENSIONS = new Point(100,160);

    final static Point DOOR_B_COORDINATES = new Point(255,175);

    final static Point DOOR_C_DIMENSIONS = new Point(70,220);

    final static Point DOOR_C_COORDINATES = new Point(500,180);

    final static Point DOOR_D_COORDINATES = new Point(200,510);

    final static Point DOOR_D_COORDINATES_2 = new Point(225,450);

    final static Point BUTTON_SIZE = new Point(100,50);

    final static Point SCREEN_SIZE = new Point(600, 700);

    final static Point ROOM_SIZE = new Point(600, 500);

    final static Point ABOUT_LABEL_COORDINATES = new Point(60,485);

    final static Point RULES_LABEL_COORDINATES = new Point(155, 525);

    final static Point SHORTCUT_LABEL_COORDINATES = new Point(160, 575);

    final static Point START_COORDINATES = new Point(175, 350);
    final static Point LOAD_COORDINATES = new Point(300, 350);

    final static Point DIFFICULTY_COORDINATES = new Point(250, 425);

    final static Point ABOUT_COORDINATES = new Point(100, 425);

    final static Point TITLE_COORDINATES =  new Point(125, 175);

    final static Point THEME_ONE_COORDINATES = new Point(400,425);

    final static Point THEME_TWO_COORDINATES = new Point(400,495);

    final static Point THEME_THREE_COORDINATES = new Point(400, 565);

    final static Point THEME_COORDINATES = new Point(400, 425);

    final static Point WINNING_POSITION = new Point(4,4);

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

    final static String DOOR_D = "GO _DOWN?";

    final static Label RULES_DESCRIPTION =  new Label("FIND A PATH BY \n SELECTING DOORS\n " +
            " AND A QUESTION \n WILL APPEAR.\n" +
            " ANSWER CORRECTLY \n-> DOOR UNLOCKED\n " +
            " ANSWER WRONG \n-> DOOR LOCKED \n EXIT-> BOTTOM RIGHT");

    final static Label SHORTCUT_DESCRIPTION =  new Label("ALT + A -> GO LEFT\n" +
            "ALT + B -> GO UP \nALT + C -> GO RIGHT\nALT + D -> GO DOWN");

    final static Label ABOUT_DESCRIPTION =  new Label("  AMAZING MAZE IS A TRIVIA MAZE GAME WHERE\n " +
            " YOU ARE LOST IN AN ABANDONED BUILDING\n " +
            " THAT HAS A BUNCH DOORS THAT HAVE BEEN CURSED\n " +
            " THE ONLY WAY TO PASS THROUGH ONE IS TO GET THE \n" +
            "  ANSWER THE QUESTION CORRECTLY. GUESS WRONG \n " +
            "    AND THE DOORWAY WILL BE CLOSED FOREVER!!\n\n " +
            "               GOOD LUCK ESCAPING... \n\n\n\n");
    final static String THEME = "THEME";


    final static ArrayList<String> myStartMenuButtons =
            new ArrayList<> (Arrays.asList(START, LOAD, ABOUT, THEME));

    final static ArrayList<String> myDoorButtons =
            new ArrayList<> (Arrays.asList(DOOR_A, DOOR_B, DOOR_C, DOOR_D));

    final static public Rectangle SCROLL_DIMENTIONS_AND_COORDINATES = new Rectangle(100,150, 400,200);

    final static public String JAVA = "Java";
    final static public String ONE_PIECE = "OnePiece";// PIECE";
    final static public String HARRY_POTTER = "HarryPotter";


}
