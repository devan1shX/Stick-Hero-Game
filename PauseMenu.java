package com.example.stickhero;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class PauseMenu {
    HelloApplication helloApplication;
    Stage primaryStage;
    Main main;
    public PauseMenu() {
        // Default constructor code (if needed)
    }
    Stage stage;
    public PauseMenu(Main main) {
        this.main = main;
    }
    public void pause(Stage stage) throws IOException {
        this.primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stickhero/pauseMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void playAgain(ActionEvent event) {
        System.out.println("playing again");
    }


    public void restartFunction(ActionEvent event) {
        System.out.println("Restart button clicked");
    }
    public void goToHomeFunction(ActionEvent event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Create a new instance of HelloApplication only if needed
        if (primaryStage == null) {
            primaryStage = new Stage();
        }

        HelloApplication helloApplication = new HelloApplication();

        helloApplication.timer++;

        helloApplication.start(this.primaryStage);

        System.out.println("Going to home...");
        System.out.println("Go to home button clicked");
    }




}
