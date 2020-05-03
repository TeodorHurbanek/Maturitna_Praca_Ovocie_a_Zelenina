package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FavoritesController implements Initializable {

    @FXML
    private TableView<ModelFavorites> favoriteTable;
    @FXML
    private TableColumn<ModelFavorites, String> col_id;
    @FXML
    private TableColumn<ModelFavorites, String> col_favorites;

    @FXML
    private TextField deleteField;
    @FXML
    private Label messageDelete;

    ObservableList<ModelFavorites> observableList = FXCollections.observableArrayList();

    public void onDeleteClick(ActionEvent event) {
        System.out.println("You clicked on onDeleteClick BUTTON.");

        if (!deleteField.getText().isEmpty()) {

            if (deleteField.getText().matches("[0-9]+")) {

                if (existID()) {

                    try {
                        Connection connection = DBConnector.getConnection();

                        PreparedStatement st = connection.prepareStatement("DELETE FROM mp_vitamins.users_fruits WHERE id = '"+ deleteField.getText().trim() +"';");
                        st.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    messageDelete.setText("");
                    deleteField.setText("");

                    favoriteTable.getItems().clear();

                    try {
                        Connection connection = DBConnector.getConnection();

                        ResultSet rs = connection.createStatement().executeQuery("SELECT users_fruits.id as user_fruit_id, fruits.title_fruit as title_fruit FROM mp_vitamins.users INNER JOIN users_fruits" +
                                " ON users_fruits.user_id = users.id INNER JOIN fruits ON users_fruits.fruit_id = fruits.id WHERE users.id = '"+ ProfilController.profil +"'");

                        while (rs.next()) {
                            observableList.add(new ModelFavorites(
                                    rs.getString("user_fruit_id"), rs.getString("title_fruit")));
                        }

                    } catch (SQLException e) {
                        Logger.getLogger(FavoritesController.class.getName()).log(Level.SEVERE, null, e);
                    }
                } else {
                    System.out.println("users_fruits.id does not exist!");
                    messageDelete.setText("The ID does not exist!");
                    deleteField.setText("");
                }
            } else {
                System.out.println("delete field does not contain numbers!");
                messageDelete.setText("You can insert only numbers!");
                deleteField.setText("");
            }
        } else {
            System.out.println("delete field is empty!");
            messageDelete.setText("The ID field is empty!");
        }

    }

    public void backtoProfilButton(ActionEvent event) {
        System.out.println("You clicked on the backtoProfilButton BUTTON");
        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("profilStage");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainStage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean existID() {
        String delete = deleteField.getText().trim();
        String user = ProfilController.profil;

        try {
            Connection connection = DBConnector.getConnection();
            String query = "SELECT * FROM mp_vitamins.users_fruits WHERE id = ? AND user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, delete);
            preparedStatement.setString(2, user);

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

    /*private boolean existIDInProfil() {
        //String delete = deleteField.getText().trim();

        try {
            Connection connection = DBConnector.getConnection();
            String query = "SELECT fruit_id FROM mp_vitamins.users_fruits WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ProfilController.profil);

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
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DBConnector.getConnection();

            ResultSet rs = connection.createStatement().executeQuery("SELECT users_fruits.id as user_fruit_id, fruits.title_fruit as title_fruit FROM mp_vitamins.users INNER JOIN users_fruits" +
                    " ON users_fruits.user_id = users.id INNER JOIN fruits ON users_fruits.fruit_id = fruits.id WHERE users.id = '"+ ProfilController.profil +"'");

            while (rs.next()){
                observableList.add(new ModelFavorites(
                        rs.getString("user_fruit_id"), rs.getString("title_fruit")));
            }

        } catch (SQLException e) {
            Logger.getLogger(FavoritesController.class.getName()).log(Level.SEVERE, null, e);
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_favorites.setCellValueFactory(new PropertyValueFactory<>("title"));

        favoriteTable.setItems(observableList);
    }
}
