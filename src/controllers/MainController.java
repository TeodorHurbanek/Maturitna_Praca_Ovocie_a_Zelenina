package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static MainController mainController;

    public static MainController getMainController() {
        return mainController;
    }

    public BorderPane mainStage;

    @FXML
    private ImageView logo;

    public void onFruitsClick(ActionEvent event) {
        System.out.println("You clicked on the onFruitsClick BUTTON.");
        try {

            FXMLLoader fxmlLoader = loadFXML("fruitsStage");

            Parent parent = fxmlLoader.load();
            mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onOpeningClick(ActionEvent event) {
        System.out.println("You clicked on OnOpenClick BUTTON.");

        try {

            FXMLLoader fxmlLoader = loadFXML("openingStage");

            Parent parent = fxmlLoader.load();
            mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onProfilClick(ActionEvent event){
        System.out.println("You clikced on the onProfilClick BUTTON.");

        if (LoginController.stayLoggedIn(LoginController.numberLogin)) {
            try {

                FXMLLoader fxmlLoader = loadFXML("profilStage");

                Parent parent = fxmlLoader.load();
                mainStage.setCenter(parent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {

                FXMLLoader fxmlLoader = loadFXML("loginStage");

                Parent parent = fxmlLoader.load();
                mainStage.setCenter(parent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void onLoginClick(ActionEvent event) {
        System.out.println("You clicked on the onLoginClick BUTTON.");
         //podmienka, kt bude pracovat s databazou. ked bude uzivatel prihlaseny, tak ak klikne na login da ho to opat na PROFIL nie na LOGIN
        if (LoginController.stayLoggedIn(LoginController.numberLogin)) {
            try {

                FXMLLoader fxmlLoader = loadFXML("profilStage");

                Parent parent = fxmlLoader.load();
                mainStage.setCenter(parent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {

                FXMLLoader fxmlLoader = loadFXML("loginStage");

                Parent parent = fxmlLoader.load();
                mainStage.setCenter(parent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public FXMLLoader loadFXML(String name) {

        String pathToMain = "../sample/" + name + ".fxml";

        URL mainURL = getClass().getResource(pathToMain);

        FXMLLoader fxmlLoader = new FXMLLoader(mainURL);

        return fxmlLoader;
    }

    @FXML
    private ImageView logoIMG;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mainController = this;
            FXMLLoader fxmlLoader = loadFXML("openingStage");

            Parent parent = fxmlLoader.load();

            mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Image logoimage = new Image("/images/logo5.png");
        logoIMG.setImage(logoimage);
    }
}
