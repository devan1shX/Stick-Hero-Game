package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.HLineTo;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class GameOverScreen {
    @FXML
    private ImageView myImageView;
    @FXML
    private Button homeButton;
    @FXML
    private Button restartButton;
    @FXML
    private Text currentScore;

    Stage primaryStage;
    Stick stick = new Stick(new Main());

    @FXML
    private void onHelloButtonClick(ActionEvent event) {
        System.out.println("Button Clicked!");
    }

    @FXML
    private void restartFunction() throws IOException {
        System.out.println("Restarting...");
        Main main = new Main();
        primaryStage.setResizable(false);
        main.start(this.primaryStage);
    }

    @FXML
    private void goToHome() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        HelloApplication main = new HelloApplication();
        main.timer ++;
        main.start(this.primaryStage);
        System.out.println("Going to home...");
    }

    public void showGameOverScreen(Stage stage, int score) throws IOException {
        this.primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stickhero/GameOver.fxml"));
        loader.setController(this);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        for (int i = 0 ; i < stick.maxScore.size(); i++ ){
            System.out.println(stick.maxScore.get(i));
        }

//        String s = String.valueOf(stick.trueScore);
//        System.out.println("in game over: "+stick.trueScore);
//        this.currentScore.setText(s);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
