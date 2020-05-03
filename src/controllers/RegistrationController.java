package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private TextField nameFieldREG;
    @FXML
    private PasswordField passwordFieldREG;
    @FXML
    private PasswordField confirmPassFieldREG;
    @FXML
    private Label messageRegistration;

    public void onSignUpClick(ActionEvent event) {
        System.out.println("You clicked on the onSignUpClick BUTTON.");
        String password1 = passwordFieldREG.getText();
        String password2 = confirmPassFieldREG.getText();

        if (!nameFieldREG.getText().isEmpty() && !passwordFieldREG.getText().isEmpty() && !confirmPassFieldREG.getText().isEmpty()) {

            if (nameDoesntExist() == false) {

                if ((password1.contentEquals(password2)) == true) {
                    String nameREG = nameFieldREG.getText().trim();
                    String passwordREG = passwordFieldREG.getText();

                    try {
                        Connection connection = DBConnector.getConnection();

                        PreparedStatement preparedStatement =
                                connection.prepareStatement(
                                        "INSERT INTO users " +
                                                "(name, password) " +
                                                "VALUES " +
                                                "(?, ?)");

                        preparedStatement.setString(1, nameREG);
                        preparedStatement.setString(2, passwordREG);

                        int result = preparedStatement.executeUpdate();

                        if (result == 1) {
                            try {

                                FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("loginStage");

                                Parent parent = fxmlLoader.load();

                                MainController.getMainController().mainStage.setCenter(parent);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("INSERT INTO users.");
                        /*messageRegistration.setText("Succesfully signed up.");
                        nameFieldREG.setText("");
                        passwordFieldREG.setText("");
                        confirmPassFieldREG.setText("");*/
                        } else {
                            System.out.println("INSERT INTO error!");
                            nameFieldREG.setText("");
                            passwordFieldREG.setText("");
                            confirmPassFieldREG.setText("");
                            messageRegistration.setText("Sign up failed. Try it later.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("passwords are not equal!");
                    messageRegistration.setText("Password and Confirm password are not equal!");
                    passwordFieldREG.setText("");
                    confirmPassFieldREG.setText("");
                }
            } else {
                System.out.println("Name alredy exist!");
                messageRegistration.setText("Name is already taken! Use another one.");
                nameFieldREG.setText("");
            }

        } else {
            System.out.println("empty fields!");
            messageRegistration.setText("One of these fields is empty!");
        }
    }

    public void backtoLoginButton(ActionEvent event) {
        System.out.println("You clicked on the backtoLoginButton BUTTON");
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("loginStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean nameDoesntExist () {
        String name = nameFieldREG.getText().trim();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
