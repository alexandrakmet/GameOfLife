package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/main.fxml"));

        Parent fxmlMain = fxmlLoader.load();
        Controller mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("Notes");
        primaryStage.getIcons().add(new Image("file:resources/icon.png"));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(600);
        primaryStage.setScene(new Scene(fxmlMain, 850, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
