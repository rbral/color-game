package com.example.dsii_pa_01_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import java.util.Random;

import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.rgb;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button startButton;

    @FXML
    private Pane colorPane;

    //private final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK};

    private final String[] colors = {"rgb(220,20,60)", "rgb(0,191,255)", "rgb(0,139,139)", "rgb(255,255,51)", "rgb(255,165,0)"};
    private final Random random = new Random();
    private Timeline timeline;

    @FXML
    protected void onStartButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!\nAre we having fun yet?");
        startButton.setVisible(false);
        startColorPane();
    }

    private void startColorPane()
    {
        colorPane.setVisible(true);
        // Set an initial random color immediately
        String initialColor = colors[random.nextInt(colors.length)];
        colorPane.setStyle("-fx-background-color: " + initialColor + ";");

        // change color every 5 seconds:
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
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

        int points = random.nextInt(1, 3);
        colorButton.setText(String.valueOf(points));

        // random position: make sure does not go out of bounds:
        double xPos = random.nextDouble() * colorPane.getScene().getWidth() - 50;
        // make underneath color pane:
        double yPos = colorPane.getHeight() + random.nextDouble() *
                (colorPane.getScene().getHeight() - colorPane.getHeight() - 50);

        colorButton.setLayoutX(xPos);
        colorButton.setLayoutY(yPos);

        colorButton.setVisible(true);
    }
}











