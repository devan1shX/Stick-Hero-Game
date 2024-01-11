package com.example.stickhero;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.InputStream;

class Player extends Game {
    private Character player = null;
    private Main mainInstance;
    private Stick stick;

    public Player(Main mainInstance, Stick stick){
        this.mainInstance = mainInstance;
        this.stick = stick;
    }
    public void placeCharacter(double widthOf1stPillar, Stage primaryStage, double widthOf2ndPillar, double gap) {
        InputStream characterStream = getClass().getResourceAsStream("/com/example/stickhero/realSprites.png");
        if (characterStream != null) {
            Image characterImage = new Image(characterStream);
            player = new Character(new ImageView(characterImage).getImage());
            player.setTranslateY(mainInstance.getPlayScreen().getPrefHeight() - mainInstance.getPlatformHeight() - 60);
            player.setTranslateX(mainInstance.getRectanglesArray().get(0).getX() + mainInstance.getRectanglesArray().get(0).getWidth()/2-10);

            mainInstance.getRoot().getChildren().add(player);

            Scene scene = new Scene(mainInstance.getRoot(), 800, 600);
            scene.setOnKeyPressed(event -> mainInstance.getKeys().put(event.getCode(), true));
            scene.setOnKeyReleased(event -> {
                mainInstance.getKeys().put(event.getCode(), false);
                player.animation.stop();
            });

            mainInstance.getPlayScreen().setOnMousePressed(this::handleMousePressed);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Stick Hero");
            primaryStage.show();
        } else {
            System.out.println("Character image file not found");
        }
    }

    public Character getInstance(){
        if ( player == null ){
            InputStream characterStream = Main.class.getResourceAsStream("/com/example/stickhero/realSprites.png");

            Image characterImage = new Image(characterStream);
            player = new Character(new ImageView(characterImage).getImage());
        }
        return player;
    }
    @Override
    public void handleMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            mainInstance.setLeftButtonPressed(true) ;
            mainInstance.setCounter(0);
            stick.startGeneratingStick(mainInstance.getWidthOf1stPillar());
        }
    }
    @Override
    public void handleMouseReleased(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            mainInstance.setLeftButtonReleased(true);
            stick.rotateStick();
            stick.doTransition(mainInstance.getPillarNumber());
        }
    }

    public double getTranslateX(){
        return player.getTranslateX();
    }


}