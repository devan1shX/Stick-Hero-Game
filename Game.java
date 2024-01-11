package com.example.stickhero;

import javafx.scene.input.MouseEvent;

public abstract class Game{
    public void handleMousePressed(MouseEvent event) {}

    public void handleMouseReleased(MouseEvent event){}
    public void generatePillars(){}
    public void initializeStick() {}
    public void startGeneratingStick(double characterXPosition) {}
    public void rotateStick() {}
    public void doTransition(int i) {}

}