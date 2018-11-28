package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Alexandra on 28.11.2018.
 */
public class Rules {
    @FXML
    private Label imageLabel;

    @FXML
    private Text text;

    @FXML
    private Button btnPlay;


    @FXML
    private void initialize(){
        Image image = new Image("file:sample.resources/name.png");
        imageLabel = new Label("Search");
        imageLabel.setGraphic(new ImageView(image));


        String string = "\tConway's Game of Life is a a cellular automaton invented by John Horton Conway in 1970. It is not a game in the conventional sense, but rather a simulation that runs on a grid of square cells, each of which can either be dead or alive.\n" +
                "\tWhen the simulation updates, living cells interact with their neighbors according to four rules. Any living cell with fewer than two live neighbors dies due to underpopulation. Living cells with four or more neighbors die through overpopulation. Living cells with two or three live neighbors continue to survive. Dead cells with three living neighbors come to life, as if via reproduction.\n";

        text = new Text(string);
        btnPlay.setDefaultButton(true);

    }

    @FXML
    private void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
