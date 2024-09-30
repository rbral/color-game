package com.example.dsii_pa_01_game;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;
import java.util.Random;



public class HelloController {

    @FXML
    private Button startButton;

    @FXML
    private Pane colorPane;

    @FXML
    private Label pointsTitleLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    private Label progressLabel;

    @FXML
    private GridPane buttonGridPane;

    private final String[] colors = {"rgb(220,20,60)", "rgb(0,191,255)", "rgb(0,139,139)", "rgb(255,255,51)", "rgb(255,165,0)"};
    private final Random random = new Random();
    private Timeline timeline;
    private String stripColor;

    @FXML
    protected void onStartButtonClick()
    {
        startButton.setVisible(false);
        startColorPane();
    }

    private void startColorPane()
    {
        colorPane.setVisible(true);
        pointsTitleLabel.setVisible(true);
        pointsLabel.setVisible(true);
        progressLabel.setText("Please wait...");

        // change color every 5 seconds:
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            stripColor = colors[random.nextInt(colors.length)];
            colorPane.setStyle("-fx-background-color: " + stripColor + ";");
            startColorButtons();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void startColorButtons()
    {
        int numButtons = random.nextInt(10, 18);

        for (int ix = 0; ix < numButtons; ix++)
        {
            createColorButton();
        }
    }

    private void createColorButton()
    {
        Button colorButton = new Button();

        String buttonColor = colors[random.nextInt(colors.length)];
        colorButton.setStyle("-fx-background-color: " + buttonColor + ";");
        colorButton.setPrefWidth(100);
        colorButton.setPrefHeight(40);

        int numColumns = 4;
        int numRows = 6;
        int col = random.nextInt(numColumns);
        int row = random.nextInt(numRows);
        buttonGridPane.add(colorButton, col, row);
        buttonGridPane.setHalignment(colorButton, HPos.CENTER);
        buttonGridPane.setValignment(colorButton, VPos.CENTER);

        // random position in buttonContainer
        /*double xPos = random.nextDouble() * (buttonPane.getWidth() - 100);
        double yPos = random.nextDouble() * (buttonPane.getHeight() - 100);
        colorButton.setLayoutX(xPos);
        colorButton.setLayoutY(yPos);*/

        // timer and points for each button:
        double displayTime = 1.5 + random.nextDouble() * 1.5;
        int points;

        // Assign points based on the displayTime range
        if (random.nextInt(5) == 0)
        {
            points = 5; // 20% chance bonus points
        }
        else if (displayTime <= 2.0)
        {
            points = 3;
        }
        else if (displayTime > 2.0 && displayTime <= 2.5)
        {
            points = 2;
        }
        else
        {
            points = 1;
        }
        colorButton.setText(String.valueOf(points));

        // if correct button is clicked, increment points and display message:
        colorButton.setOnAction(event -> {
            int currentScore = Integer.parseInt(pointsLabel.getText());
            if (buttonColor == stripColor)
            {
                currentScore += points;
                progressLabel.setText("Great job! :) ");
            }
            else
            {
                currentScore -= 1;
                progressLabel.setText("Wrong color :( -1 ");
            }
            pointsLabel.setText(String.valueOf(currentScore));
            // remove clicked button:
            buttonGridPane.getChildren().remove(colorButton);
        });


        // remove after displayTime:
        Timeline buttonTimer = new Timeline(new KeyFrame(Duration.seconds(displayTime), event -> {
            buttonGridPane.getChildren().remove(colorButton);
        }));
        buttonTimer.setCycleCount(1);
        buttonTimer.play();


    }
}











