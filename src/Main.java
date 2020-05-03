import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        String pathToMain = "sample/mainStage.fxml";

        URL mainURL = getClass().getResource(pathToMain);

        Parent parent = FXMLLoader.load(mainURL);

        Scene scene = new Scene(parent);

        //metoda setMaximized, kt. zvacsuje okno po zapnuti
        //primaryStage.setMaximized(true);
        // metoda, kt. nezvacsi okno
        primaryStage.setResizable(false);
        primaryStage.setTitle("Vitamins");
        primaryStage.getIcons().add(new Image("images/logo5.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
