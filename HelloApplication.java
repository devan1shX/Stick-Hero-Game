package com.example.stickhero;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HelloApplication extends Application {
    @FXML
    public Button play;         // button to play
    public int timer = 0;      // timer to know when to play music and when to not
    Clip clip;               // clip used for music things

    @FXML
    public Button loadButton;

    public int trueScore;
    Stage primaryStage;
    public static Serialisng_object serialisingObject = new Serialisng_object();

    @Override
    public void start(Stage stage) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (timer == 0){
            playMusic(0);
            timer++;
        }
        primaryStage = stage;
        writeZeroToScoreFile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        loader.setController(this);

        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Stick Hero");
        stage.setResizable(false);
        stage.show();

    }


    // adding music to the game
    public boolean playMusic(int timer) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        if (timer == 0){
            File file = new File("C:\\Users\\anish\\OneDrive - iiitd.ac.in\\StickHeron\\StickHero\\src\\main\\resources/com/example/stickhero/feel my love.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            return true;
        }
        return false;
    }

    public static void writeZeroToScoreFile() {
        // Specify the file path
        String filePath = "ScoreCounter.txt";

        try {
            // Create or open the file
            FileWriter writer = new FileWriter(filePath);

            // Write "0" to the file
            writer.write("0");

            // Close the writer
            writer.close();

            System.out.println("Successfully wrote '0' to score.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // play button to play the game
    public void handlePlayButton() {
        Main main = new Main();
        main.start(new Stage());
        Stage stage = (Stage) play.getScene().getWindow();
        stage.close();
    }

    // if volume button is pressed, then volume is set to 0 and music stops
    public void changeVolume() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
                System.out.println("Clip stopped");
            } else {
                System.out.println("Clip is not running");
            }
        } else {
            File file = new File("@feel my love.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("Clip initialized and started");
        }
    }

    public void loadPreviousGame() throws IOException {
        System.out.println("loading previous game");
        serialisingObject.serialise();
        Main main = new Main();
        main.start(primaryStage);
        trueScore = 5;
        System.out.println(trueScore);
    }







































































































    public static void main(String[] args) {
        launch();
    }
}