package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfilController implements Initializable {

    @FXML
    private Label nameProfil;

    public static String profil = null;

    public void onFavoritesButtonClick(ActionEvent event) {

        System.out.println("You clicked on the onFavoritesButtonClick BUTTON");
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("favoritesStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLogOutClick(ActionEvent event) {
        System.out.println("You clicked on the onLogOutClick BUTTON.");

        ProductController.product = null;
        ProfilController.profil = null;
        LoginController.numberLogin = 0;
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("openingStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ImageView userIMG;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DBConnector.getConnection();

            ResultSet rs = connection.createStatement().executeQuery("SELECT name FROM mp_vitamins.users WHERE id = "+ profil);

            while (rs.next()) {
                nameProfil.setText(rs.getString("name"));
                /*Image pomaranc = new Image(rs.getString("img_fruit"));
                imgPomaranc.setImage(pomaranc);*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Image user = new Image("/images/user1.png");
        userIMG.setImage(user);
    }
}
