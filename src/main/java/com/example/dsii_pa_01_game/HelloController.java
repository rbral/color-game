package com.example.dsii_pa_01_game;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;


import java.util.Random;

import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.rgb;


public class HelloController {
    /*@FXML
    private Label welcomeText;*/

    @FXML
    private Button startButton;

    @FXML
    private Pane colorPane;

    @FXML
    private Label pointsTitleLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    private Pane buttonPane;

    //private final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK};

    private final String[] colors = {"rgb(220,20,60)", "rgb(0,191,255)", "rgb(0,139,139)", "rgb(255,255,51)", "rgb(255,165,0)"};
    private final Random random = new Random();
    private Timeline timeline;

    @FXML
    protected void onStartButtonClick()
    {
        //welcomeText.setText("Welcome to JavaFX Application!\nAre we having fun yet?");
        startButton.setVisible(false);
        startColorPane();
    }

    private void startColorPane()
    {
        colorPane.setVisible(true);
        pointsTitleLabel.setVisible(true);
        pointsLabel.setVisible(true);
        // Set an initial random color immediately
        String initialColor = colors[random.nextInt(colors.length)];
        colorPane.setStyle("-fx-background-color: " + initialColor + ";");

        // change color every 5 seconds:
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            String randomColor = colors[random.nextInt(colors.length)];
            colorPane.setStyle("-fx-background-color: " + randomColor + ";");
            startColorButtons();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void startColorButtons()
    {
        int numButtons = random.nextInt(1, 7);

        for (int ix = 0; ix < numButtons; ix++)
        {
            createColorButton();
        }
    }

    private void createColorButton()
    {
        Button colorButton = new Button();

        String randomColor = colors[random.nextInt(colors.length)];
        colorButton.setStyle("-fx-background-color: " + randomColor + ";");
        colorButton.setPrefWidth(100);
        colorButton.setPrefHeight(40);
        //int points = random.nextInt(1, 3);
        //colorButton.setText(String.valueOf(points));

        // random position in buttonContainer
        double xPos = random.nextDouble() * (buttonPane.getWidth() - 50);
        double yPos = random.nextDouble() * (buttonPane.getHeight() - 50);
        colorButton.setLayoutX(xPos);
        colorButton.setLayoutY(yPos);

        buttonPane.getChildren().add(colorButton);

        // timer and points for each button:
        double displayTime = random.nextDouble(1.5, 3.0);
        int points = (int) Math.floor(displayTime);
        colorButton.setText(String.valueOf(points));

        Timeline buttonTimer = new Timeline(new KeyFrame(Duration.seconds(displayTime), event -> {
            buttonPane.getChildren().remove(colorButton);
        }));
        buttonTimer.setCycleCount(1);
        buttonTimer.play();



        //colorButton.setVisible(true);
    }
}











