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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private Label titlePomaranc;
    @FXML
    private Label textPomaranc;
    @FXML
    private ImageView imgPomaranc;
    @FXML
    private Label likeMessage;

    public static String product = null;

    private String alreadyLiked = null;

    public void onLikeClick(ActionEvent event) {
        System.out.println("You clicked on onLikeClick BUTTON.");

        if ((ProfilController.profil != null) && (ProductController.product != null)) {

            if (likedDoesNotExist()) {
                try {
                    Connection connection = DBConnector.getConnection();

                    ResultSet rs = connection.createStatement().executeQuery("SELECT fruit_id FROM mp_vitamins.users_fruits WHERE user_id = '" + ProfilController.profil + "' AND "+ "fruit_id = '"+ ProductController.product +"'");

                    while (rs.next()) {
                        alreadyLiked = rs.getString("fruit_id");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (ProductController.product.contentEquals(alreadyLiked)) {
                    System.out.println("product is already in liked!");
                    likeMessage.setText("You have already liked it.");
                } else {

                    try {
                        Connection connection = DBConnector.getConnection();

                        PreparedStatement preparedStatement =
                                connection.prepareStatement(
                                        "INSERT INTO users_fruits " +
                                                "(user_id, fruit_id) " +
                                                "VALUES " +
                                                "(?, ?)");

                        preparedStatement.setString(1, ProfilController.profil);
                        preparedStatement.setString(2, ProductController.product);

                        int result = preparedStatement.executeUpdate();

                        if (result == 1) {
                            System.out.println("INSERT INTO users_fruit.");
                            likeMessage.setText("Added to liked.");
                        } else {
                            System.out.println("INSERT INTO failed.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                try {
                    Connection connection = DBConnector.getConnection();

                    PreparedStatement preparedStatement =
                            connection.prepareStatement(
                                    "INSERT INTO users_fruits " +
                                            "(user_id, fruit_id) " +
                                            "VALUES " +
                                            "(?, ?)");

                    preparedStatement.setString(1, ProfilController.profil);
                    preparedStatement.setString(2, ProductController.product);

                    int result = preparedStatement.executeUpdate();

                    if (result == 1) {
                        System.out.println("INSERT INTO users_fruit.");
                        likeMessage.setText("Added to liked.");
                    } else {
                        System.out.println("INSERT INTO failed.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            System.out.println("user_id is empty... You have to log in.");
            likeMessage.setText("You are not logged in!");
        }
    }

    public void backtoMenuButton(ActionEvent event) {
        System.out.println("You clicked on the backtoMenuButton BUTTON");
        try {

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("fruitsStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean likedDoesNotExist() {
        String user = ProfilController.profil;
        String product = ProductController.product;

        try {
            Connection connection = DBConnector.getConnection();
            String query = "SELECT * FROM mp_vitamins.users_fruits WHERE user_id = ? AND fruit_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, product);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DBConnector.getConnection();

            ResultSet rs = connection.createStatement().executeQuery("SELECT title_fruit, text_fruit, img_fruit FROM mp_vitamins.fruits WHERE id = " + product);

            while (rs.next()) {
                titlePomaranc.setText(rs.getString("title_fruit"));
                textPomaranc.setText(rs.getString("text_fruit"));
                Image imgFruit = new Image(rs.getString("img_fruit"));
                imgPomaranc.setImage(imgFruit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
