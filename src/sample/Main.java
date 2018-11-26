package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/main.fxml"));

        Parent fxmlMain = fxmlLoader.load();
        Controller mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);


        primaryStage.setTitle("GAME OF LIFE");
        primaryStage.getIcons().add(new Image("file:resources/icon.png"));
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(1100);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(fxmlMain, 850, 500));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
