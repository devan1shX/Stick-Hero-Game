package com.example.stickhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Random;

class Pillar extends Game{
    private Main mainInstance;
    public Pillar(Main mainInstance){ this.mainInstance = mainInstance; }

    Random rand= new Random();
    ImageView imageView;
    public Boolean flag = false;

//    public void moveImageViewLeft(ImageView imageView, double distance, double durationInSeconds) {
//        // Create a translate transition
//        TranslateTransition transition = new TranslateTransition(Duration.seconds(durationInSeconds), imageView);
//
//        // Set the initial and final positions
//        transition.setFromX(imageView.getTranslateX());
//        transition.setToX(imageView.getTranslateX() - distance);
//
//        // Play the transition
//        transition.play();
//    }
//    public void imageSet(double platformHeight, Object playScreen) {
//        InputStream imageStream = getClass().getResourceAsStream("/com/example/stickhero/apple.png");
//        Image image = new Image(imageStream);
//        imageView = new ImageView(image);
//        double appledist = 300;
//        imageView.setLayoutX(appledist);
//        imageView.setLayoutY(platformHeight + 55);
//        imageView.setFitHeight(40);
//        imageView.setFitWidth(40);
//        // Assuming playScreen is of type Object, you may need to replace it with the actual type
//        mainInstance.getPlayScreen().getChildren().add(imageView);
//    }
    @Override
    public void generatePillars() {

//        double platformHeight = 350;
//        Object playScreen = new Object();
//
//
//        imageSet(platformHeight, playScreen);
////        moveImageViewLeft(imageView, 50,2);
        mainInstance.getRectanglesArray().add(new Rectangle(100,mainInstance.getPlatformHeight(), Color.BLACK));
        for(int i=1;i<4;i++){
            int wid = rand.nextInt(25,150);
            Rectangle rect=new Rectangle(wid,mainInstance.getPlatformHeight(),Color.BLACK);
            mainInstance.getRectanglesArray().add(rect);
        }
        for(int i=0;i<4;i++){
            int gap= rand.nextInt(80,110);
            mainInstance.getGapArray().add(gap);
        }
        mainInstance.getRectanglesArray().get(0).setX(100);
        mainInstance.getRectanglesArray().get(0).setLayoutY(350);
        mainInstance.getPlayScreen().getChildren().add(mainInstance.getRectanglesArray().get(0));
        Iterator<Rectangle> iterator = mainInstance.getRectanglesArray().iterator();
        if (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasNext()) {
            Rectangle currentRect = iterator.next();
            Rectangle prevRect = mainInstance.getRectanglesArray().get(mainInstance.getRectanglesArray().indexOf(currentRect) - 1);

            double newLayoutX = prevRect.getLayoutX() + prevRect.getWidth() + 150 + mainInstance.getGapArray().get(mainInstance.getRectanglesArray().indexOf(prevRect));
//            System.out.println(mainInstance.getGapArray().get(mainInstance.getRectanglesArray().indexOf(prevRect)) + prevRect.getX());

            currentRect.setLayoutX(newLayoutX);
            currentRect.setLayoutY(350);

            mainInstance.getPlayScreen().getChildren().add(currentRect);
        }



        mainInstance.getPlayScreen().setOnMousePressed(event->mainInstance.getPlayer().handleMousePressed(event));
        mainInstance.getPlayScreen().setOnMouseReleased(event -> mainInstance.getPlayer().handleMouseReleased(event));
    }
}