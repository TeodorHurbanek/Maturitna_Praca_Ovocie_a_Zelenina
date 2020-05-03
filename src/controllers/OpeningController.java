package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class OpeningController implements Initializable {

    @FXML
    private Label text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text.setText("Welcome to Demo Version of app called Vitamins\nwhere you can find information about different\nkinds of fruit or vegetables from all around the world.\nLog in for browsing in app.");
    }
}
