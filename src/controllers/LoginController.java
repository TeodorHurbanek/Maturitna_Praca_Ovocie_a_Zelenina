package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField nameFieldLOG;
    @FXML
    private PasswordField passwordFieldLOG;
    @FXML
    private Label messageLogin;

    public static int numberLogin = 0;

    public void onSwitchToRegistrationClick(ActionEvent event) {
        System.out.println("You clicked on the onSwitchToRegistrationClick BUTTON");
        try {

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("registrationStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logInButton(ActionEvent event){
        System.out.println("You clicked on the logInButton BUTTON.");
        if (!nameFieldLOG.getText().isEmpty() && !passwordFieldLOG.getText().isEmpty()) {
            if (isUserLoggedIn() == false) {
                messageLogin.setText("Name does not exist!");
                System.out.println("Wrong name!");
            } else if (isUserLoggedInPass() == false){
                passwordFieldLOG.setText("");
                messageLogin.setText("Wrong password!");
                System.out.println("Wrong password!");
            } else {
                System.out.println("Successfully logged in.");

                try {
                    Connection connection = DBConnector.getConnection();

                    ResultSet rs = connection.createStatement().executeQuery("SELECT id FROM mp_vitamins.users WHERE name = "+ "'"+ nameFieldLOG.getText().trim() +"'");

                    while (rs.next()) {
                        ProfilController.profil = rs.getString("id");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                numberLogin = 1;
                //stayLoggedIn(numberLogin);

                try {

                    FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("profilStage");

                    Parent parent = fxmlLoader.load();

                    MainController.getMainController().mainStage.setCenter(parent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Empty fields!");
            messageLogin.setText("One of these fields is empty!");
        }
    }

    private boolean isUserLoggedIn () {
        String name = nameFieldLOG.getText().trim();

        try {
            Connection connection = DBConnector.getConnection();
            String query = "SELECT * FROM mp_vitamins.users WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery() ;
            if(rs.next()) {
                return true ;
            }else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isUserLoggedInPass () {
        String pass = passwordFieldLOG.getText().trim();

        try {
            Connection connection = DBConnector.getConnection();
            String query = "SELECT * FROM mp_vitamins.users WHERE password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pass);

            ResultSet rs = preparedStatement.executeQuery() ;
            if(rs.next()) {
                return true ;
            } else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean stayLoggedIn(int num) {
        boolean truth = false;
        if (num == 1) {
            truth = true;
        } else {
            truth = false;
        }
        return truth;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
