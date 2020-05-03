package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FruitsController implements Initializable {

    public void onOrangeClick(ActionEvent event) {
        System.out.println("You clicked on onOrangeClick BUTTON");

        ProductController.product = "1";
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("productStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAppleClick(ActionEvent event) {
        System.out.println("You clicked on onAppleClick BUTTON");

        ProductController.product = "2";
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("productStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onKiwiClick(ActionEvent event) {
        System.out.println("You clicked on onKiwiClick BUTTON");

        ProductController.product = "4";
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("productStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCarrotClick(ActionEvent event) {
        System.out.println();

        ProductController.product = "3";
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("productStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTomatoClick(ActionEvent event) {
        System.out.println("You clicked on onTomatoClick BUTTON");

        ProductController.product = "5";
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("productStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCucumberClick(ActionEvent event) {
        System.out.println("You clicked on onCucumberClick BUTTON");

        ProductController.product = "6";
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("productStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private ImageView orangeIMG;
    @FXML
    private ImageView appleIMG;
    @FXML
    private ImageView kiwiIMG;
    @FXML
    private ImageView carrotIMG;
    @FXML
    private ImageView tomatoIMG;
    @FXML
    private ImageView cucumberIMG;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image orange = new Image("/images/orange2.png");
        orangeIMG.setImage(orange);
        Image apple = new Image("/images/apple.png");
        appleIMG.setImage(apple);
        Image kiwi = new Image("/images/kiwi.png");
        kiwiIMG.setImage(kiwi);
        Image carrot = new Image("/images/carrot3.png");
        carrotIMG.setImage(carrot);
        Image tomato = new Image("/images/tomato.png");
        tomatoIMG.setImage(tomato);
        Image cucumber = new Image("/images/cucumber.png");
        cucumberIMG.setImage(cucumber);
    }
}
