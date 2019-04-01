/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.windows;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class LanGUI extends Application {
    String userName;
    String record;

    public LanGUI(String name, String win) {
        userName = name;
        record = win;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane labelPane = new GridPane();
        Scene scene = new Scene(labelPane, 1280.0D, 720.0D);
        labelPane.setHgap(20.0D);
        labelPane.setVgap(20.0D);
        RowConstraints rowConstraints1 = new RowConstraints();
        RowConstraints rowConstraints2 = new RowConstraints();
        RowConstraints rowConstraints3 = new RowConstraints();
        RowConstraints rowConstraints4 = new RowConstraints();

        labelPane.getRowConstraints().addAll(rowConstraints1, rowConstraints2, rowConstraints3, rowConstraints4);

        Label nameLabel = new Label("User: " + userName);
        nameLabel.setId("infoLabel");
        Label lanLabel = new Label("Creat Room");
        lanLabel.setId("infoLabel");
        Label joinLabel = new Label("Join Room");
        joinLabel.setId("infoLabel");
        Label winLabel = new Label();
        if (record.equals("-1") || record == "-1") {
            winLabel.setText("Record: 0");
        } else {
            winLabel.setText("Record: " + record);
        }
        winLabel.setId("infoLabel");

        lanLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RoomGUI roomGUI = new RoomGUI(userName);
                try {
                    roomGUI.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        joinLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RoomGUI roomGUI = new RoomGUI(userName);
                try {
                    roomGUI.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        labelPane.add(nameLabel, 0, 0);
        labelPane.add(winLabel, 0, 1);
        labelPane.add(lanLabel, 0, 2);
        labelPane.add(joinLabel, 0, 3);


        labelPane.setPadding(new Insets(200, 100, 200, 100));
        scene.getStylesheets().add(BeginGUI.class.getResource("beginCSS.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
