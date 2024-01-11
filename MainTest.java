package com.example.stickhero;

import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private static Main mainInstance;
    private static  Stick stick;
    private static HelloApplication applicationInstance;

    private static GameOverScreen gameOverInstance;

    @Test
    public void testScoreLabelUpdate() {
        int newScore = 5;
        assertEquals(newScore, mainInstance.setScoreLabel(String.valueOf(newScore)));
        assertEquals(String.valueOf(newScore), mainInstance.scoreText.getText());
    }

    @Test
    public void MusicPlayingOrNot() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Assertions.assertTrue(applicationInstance.playMusic(applicationInstance.timer));
    }

    @Test
    public void BackGroundGenerated(){
        Assertions.assertEquals(mainInstance.Background(),1);
        Assertions.assertNotEquals(mainInstance.Background(),1);
    }

    @Test
    public void testInitialization() {
        assertNotNull(mainInstance);
        assertNotNull(mainInstance.getPlayScreen());
        assertNotNull(mainInstance.getKeys());
    }

    @Test
    public void testStickRotation() {
        Platform.runLater(() -> stick.rotateStick());

        assertEquals(90, stick.rectangle2.getRotate());
    }

    @Test
    public void testGameOver() {
        Platform.runLater(() -> {
            try {
                mainInstance.gameOver(10);
            } catch (Exception e) {
            }
        });
    }
}

