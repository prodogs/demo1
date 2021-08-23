package com.investrics.Capability;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button myButton;

    @FXML
    private Label titleText;

    @FXML
    protected void onHelloClick() {
        titleText.setText("The 1st Title");
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onGoodbyeClick() {
        titleText.setText("The 2nd Title");

        welcomeText.setText("Goodbye to JavaFX Application!");
    }

}