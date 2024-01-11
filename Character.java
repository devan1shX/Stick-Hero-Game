package com.example.stickhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Character extends Pane {
    ImageView imageView;
    Anim animation;
    private double positionX;
    private double positionY;

    public Character(Image image) {
        this.imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        animation = new Anim(imageView, javafx.util.Duration.millis(200), 3, 3, 0, 0, 280, 385);
        getChildren().add(imageView);
    }

    public void moveX(double x) {
        this.setTranslateX(this.getTranslateX() + x);
        // Update positionX
        this.positionX = this.getTranslateX();
    }

    public void moveY(double y) {
        this.setTranslateY(this.getTranslateY() + y);
        // Update positionY
        this.positionY = this.getTranslateY();
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setOffsetY(int y) {
        this.animation.setOffsetY(y);
    }
}
