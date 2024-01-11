package com.example.stickhero;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.control.IndexRange;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;

class Stick extends Game{
    protected Rectangle rectangle2;
    private SequentialTransition sequentialTransition;

    private Main mainInstance;
    public Stick(Main main){ this.mainInstance = main; }
    ArrayList<Integer> maxScore = new ArrayList<>();

    HelloApplication helloApplication = new HelloApplication();
    Pillar pillar = new Pillar(this.mainInstance);
    @Override
    public void initializeStick() {
        rectangle2 = new Rectangle(5, 0, Color.BLACK);
        mainInstance.getPlayScreen().getChildren().add(rectangle2);
        rectangle2.setLayoutX(-50);
        rectangle2.setY(350);
    }
    @Override
    public void startGeneratingStick(double characterXPosition) {
        rectangle2.setHeight(0);
        rectangle2.setX(245);

        mainInstance.timeline = new Timeline(
                new KeyFrame(Duration.millis(50), actionEvent -> {
                    rectangle2.setHeight(rectangle2.getHeight() + 10);
                    rectangle2.setY(rectangle2.getY() - 10);
                }
                ));

        mainInstance.timeline.setCycleCount(Animation.INDEFINITE);
        mainInstance.timeline.play();
    }

    @Override
    public void rotateStick() {
        mainInstance.timeline.stop();

        rectangle2.setRotate(90);
        rectangle2.setX(rectangle2.getX()+rectangle2.getHeight()/2);
        rectangle2.setTranslateY(rectangle2.getHeight()/2);

    }

    @Override
    public void doTransition(int i) {
        double gapFor2nd = mainInstance.getRectanglesArray().get(i + 1).getX() + mainInstance.getRectanglesArray().get(i + 1).getWidth() + mainInstance.getPlayer().getTranslateX();
        double gapFor1st = mainInstance.getRectanglesArray().get(i).getX() + mainInstance.getRectanglesArray().get(i).getWidth() + mainInstance.getRectanglesArray().get(i + 1).getX() - 20;

        double checkDistI = mainInstance.getRectanglesArray().get(0).getX() + mainInstance.getRectanglesArray().get(i).getWidth() / 2 + mainInstance.getRectanglesArray().get(i + 1).getX() - mainInstance.getRectanglesArray().get(i + 1).getWidth() / 2;
        double checkDistF = mainInstance.getRectanglesArray().get(0).getX() + mainInstance.getRectanglesArray().get(i).getWidth() / 2 + mainInstance.getRectanglesArray().get(i + 1).getX() + mainInstance.getRectanglesArray().get(i + 1).getWidth() / 2;

        if (rectangle2.getHeight() >= checkDistI && rectangle2.getHeight() <= checkDistF) {
            // If the stick is in the correct position
            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(2), mainInstance.getRectanglesArray().get(i));
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(2), mainInstance.getRectanglesArray().get(i + 1));
            TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(2), rectangle2);
            TranslateTransition translateTransition4 = new TranslateTransition(Duration.seconds(2), pillar.imageView);
            translateTransition3.setToX(-gapFor1st);
            translateTransition4.setToX(-150);
            translateTransition1.setToX(-gapFor1st);
            translateTransition2.setToX(-gapFor2nd);

            translateTransition1.play();
            translateTransition3.play();
            translateTransition4.play();
            translateTransition2.play();
            translateTransition2.setOnFinished(actionEvent -> {
                mainInstance.setScore(mainInstance.getScore() + 1);
                mainInstance.setScoreLabel(String.valueOf(mainInstance.getScore()));
//                pillar.moveImageViewLeft(pillar.imageView, 50,2);
                Platform.runLater(() -> {
                    try {
//                        File scoreFile = new File("ScoreCounter.txt");
//
//                        if (!scoreFile.exists()) {
//                            scoreFile.createNewFile();
//                        }
//                        try (FileInputStream in = new FileInputStream(scoreFile)) {
//                            int readValue = in.read();
//                            helloApplication.trueScore = readValue - '0';
//                        }

//                        helloApplication.trueScore++;
//                        String sc = String.valueOf(helloApplication.trueScore);
//                        mainInstance.setScore(helloApplication.trueScore);
//                        mainInstance.setScoreLabel(sc);
////                        try (FileWriter writer = new FileWriter(scoreFile)) {
////                            writer.write(String.valueOf(helloApplication.trueScore));
////                        }
                        System.out.println("Updated score: " + helloApplication.trueScore);
//                        maxScore.add(helloApplication.trueScore);
////                        pillar.moveImageViewLeft(pillar.imageView, 50,2);
                        Main newMain = new Main();
                        newMain.start(this.mainInstance.getPrimaryStage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        } else {
            // If the stick is not in the correct position
            mainInstance.notOut = false;
            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(2), mainInstance.getRectanglesArray().get(i));
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(2), mainInstance.getRectanglesArray().get(i + 1));
            TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(2), rectangle2);
            TranslateTransition translateTransition4 = new TranslateTransition(Duration.seconds(2), this.mainInstance.getPlayer().getInstance());
            translateTransition3.setToX(-(rectangle2.getHeight()) - mainInstance.getRectanglesArray().get(i).getWidth() / 2 + 20);
            translateTransition1.setToX(-(mainInstance.getRectanglesArray().get(i).getX() + rectangle2.getHeight() / 2) + 30);
            translateTransition2.setToX(-(mainInstance.getRectanglesArray().get(i).getX() + rectangle2.getHeight() / 2));
            translateTransition4.setToY(1000);

            translateTransition1.play();
            translateTransition3.play();
            translateTransition2.play();
            translateTransition2.setOnFinished(actionEvent -> {
                translateTransition4.play();
                try {
                    mainInstance.gameOver(helloApplication.trueScore);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
