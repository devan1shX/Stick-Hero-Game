package com.example.stickhero;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main extends Application {
    private Pane root;
    public Pane getRoot(){ return this.root; }
    private AnchorPane playScreen;
    private static final int PLATFORM_HEIGHT = 250;
    public int getPlatformHeight(){ return this.PLATFORM_HEIGHT; }

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();     // keys used for animation and transition

    private boolean leftButtonPressed = false;
    public boolean getLeftButtonPresssed(){ return this.leftButtonPressed; }
    public void setLeftButtonPressed(boolean val){ this.leftButtonPressed = val;}
    private boolean leftButtonReleased = false;
    public boolean getLeftButtonReleased(){ return this.leftButtonReleased; }
    public void setLeftButtonReleased(boolean val){ this.leftButtonReleased = val;}
    protected int counter = 0;         // counter to know number of units we have to make the stick
    public int getCounter(){ return this.counter;}
    public void setCounter(int val){ this.counter = val;}
    protected Text scoreText;        // for score
    public Text getScoreText(){ return this.scoreText;}
    public void setScoreText(Text txt){ this.scoreText = txt; }

    private HashMap<Integer, String> backgrounds = new HashMap<>();
    public HashMap<Integer, String> getBackgrounds(){ return this.backgrounds; }

    private final double speedFactor = 1.5;      // speed factor for animating the character
    private AnimationTimer counterTimer;
    private boolean characterMoving = false;
    private double targetX = 0;
    private int widthOf1stPillar;
    public int getWidthOf1stPillar(){ return this.widthOf1stPillar;}
    private static final double IMAGE_WIDTH = 800;
    private static final double MOVE_DISTANCE = 3;
    private static final Duration ANIMATION_DURATION = Duration.millis(30);
    protected Timeline timeline;      // used for transition
    public Timeline getTimeLine(){ return this.timeline; }
    public void setTimeLine(Timeline timeline){ this.timeline = timeline; }
    private ImageView imageView;
    public ImageView getImageView(){ return this.imageView; }
    public void setImageView(ImageView imageView){ this.imageView = imageView; }
    private Pane imagePane;
    private double width1;
    private double width2;
    private double gap;
    private int pillarNumber=0;
    public void setPillarNumber(int val){ this.pillarNumber = val; }
    public int getPillarNumber(){ return this.pillarNumber; }
    Label label = new Label();
    boolean notOut = true;
    private  Stage primaryStage;        // primary stage for doing all the things
    public Stage getPrimaryStage(){return this.primaryStage; }
    private int score = 0;
    public int getScore(){ return this.score; }
    public void setScore(int score){ this.score = score; }
    private Stick stick = new Stick(this);

    String scoreStr = String.valueOf(score);
    ArrayList<Rectangle> arr=new ArrayList<Rectangle>();
    public ArrayList<Rectangle> getRectanglesArray() { return this.arr; }
    ArrayList<Integer> gapArray=new ArrayList<Integer>();
    public ArrayList<Integer> getGapArray(){ return this.gapArray; }
    private Rectangle score_Rectangle = new Rectangle(80,20,Color.YELLOW);
    public Rectangle getScoreRectangle(){ return this.score_Rectangle;}

    private int currentScore = 0;
    Player player = new Player(this,stick);
    public Player getPlayer(){ return this.player; }

    public static void main(String[] args) {
        launch(args);
    }
    public AnchorPane getPlayScreen(){ return this.playScreen; }

    public Main(){}
    Pillar pillars = new Pillar(this);
    public HashMap<KeyCode, Boolean> getKeys(){ return this.keys; }
    public void setRectangleArray(ArrayList<Rectangle> arr){this.arr = arr;}
    public void setGapArray(ArrayList<Integer> arr){this.gapArray = arr;}



    public void setupPauseIcon(){
        InputStream imageStream = getClass().getResourceAsStream("/com/example/stickhero/pause-button.png");
        Image image = new Image(imageStream);
        imageView = new ImageView(image);
        imageView.setLayoutY(35);
        imageView.setLayoutX(40);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Button pauseButton = new Button();
        pauseButton.setOpacity(0);
        pauseButton.setLayoutX(55);
        pauseButton.setScaleX(3);
        pauseButton.setScaleY(2);
        pauseButton.setLayoutY(43);

        playScreen.getChildren().addAll(imageView, pauseButton);

//        pauseButton.setOnAction(e -> {
//            PauseMenu pauseMenu = new PauseMenu(this);
//
//            try {
//                pauseMenu.pause(primaryStage);
//
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//            System.out.println("pausing the screen");
//        });

        pauseButton.setOnAction(e->{
            HelloApplication helloApplication = new HelloApplication();
            try {
                helloApplication.start(this.primaryStage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);
        Background();                        // setting background

        pillars.generatePillars();          // generating pillars
        stick.initializeStick();            // initialising the stick
        player.placeCharacter(width1, primaryStage, width2, gap);
        setupPauseIcon();
        scoreText = new Text();
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        scoreText.setFill(Color.BLACK);
        setScoreLabel(scoreStr);
        System.out.println("new instance");

    }

    // generating random background
    public String generateRandomBackground() {
//        getBackgrounds().put(0, "/com/example/stickhero/background2.jpg");
//        getBackgrounds().put(1, "/com/example/stickhero/background.jpg");
//        getBackgrounds().put(2, "/com/example/stickhero/Play.jpg");
        getBackgrounds().put(0, "/com/example/stickhero/background3.jpg");
        int randomBackground = new Random().nextInt(getBackgrounds().size());
        return getBackgrounds().get(randomBackground);
    }

    public int Background() {
        InputStream backgroundStream = getClass().getResourceAsStream(generateRandomBackground());
        if (backgroundStream != null) {
            Image backgroundImage = new Image(backgroundStream);
            setImageView(new ImageView(backgroundImage)) ;
            getImageView().setFitHeight(600);
            getImageView().setFitWidth(800);

            root = new Pane();

            playScreen = new AnchorPane();
            getPlayScreen().setPrefSize(800, 600);
            getRoot().getChildren().addAll(getImageView(), getPlayScreen());
            return 1;
        } else {
            System.out.println("Background image file not found");
            return -1;
        }
    }


    protected int setScoreLabel(String score) {
        if (!getPlayScreen().getChildren().contains(score_Rectangle)) {
            setScoreText(new Text(score));
            currentScore = Integer.parseInt(score);
            scoreText.setLayoutY(getPlatformHeight() - 212);
            scoreText.setLayoutX(735);
            score_Rectangle.setLayoutX(700);
            score_Rectangle.setLayoutY(getPlatformHeight() - 230);
            getPlayScreen().getChildren().addAll(score_Rectangle, scoreText);
        } else {
            scoreText.setText(score);
        }
        return Integer.parseInt(score);
    }



    protected void gameOver(int score) throws IOException {
        GameOverScreen gameOverScreen = new GameOverScreen();
        gameOverScreen.showGameOverScreen(primaryStage,score);
    }

//    public void testing(){
//        Result result= JUnitCore.runClasses(MainTest.class);
//        for (Failure failure : result.getFailures()) {
//            System.out.println(failure.toString());
//        }
//        System.out.println(result.wasSuccessful());
//    }

}