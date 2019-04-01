/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.windows;

import tactictoe.client_side.dao.Player;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import tactictoe.client_side.dao.PlayerNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class RoomGUI extends Application {
    private String username = "";

    public RoomGUI(String username){
        this.username=username;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 1280, 720);
        gridPane.setVgap(20.0D);
        gridPane.setHgap(20.0D);

        RowConstraints rowConstraints1 = new RowConstraints();
        RowConstraints rowConstraints2 = new RowConstraints();

        gridPane.getRowConstraints().addAll(rowConstraints1, rowConstraints2);

        Label numLabel = new Label("Room No.");
        numLabel.setId("infoLabel");
        TextField roomNum = new TextField();
        roomNum.setId("roomField");
        Label button = new Label("Join");
        button.setId("infoLabel");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String num = roomNum.getText();
                PlayerNet playerNet = new PlayerNet();
                PrintWriter out = playerNet.getOut();
                BufferedReader in = playerNet.getIn();
                out.println("1-" + num+"-"+username);


                String result = "";
                try {
                    result = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                switch (result) {
                    case "-1": {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Look, some errors occur");
                        Label label = new Label();
                        String rule = "The room doesn't exist";
                        label.setText("The thing you should know:");
                        TextArea textArea = new TextArea(rule);
                        textArea.setEditable(false);
                        textArea.setWrapText(true);
                        textArea.setMaxWidth(1.7976931348623157E308D);
                        textArea.setMaxHeight(1.7976931348623157E308D);
                        GridPane.setVgrow(textArea, Priority.ALWAYS);
                        GridPane.setHgrow(textArea, Priority.ALWAYS);
                        GridPane expContent = new GridPane();
                        expContent.setMaxWidth(1.7976931348623157E308D);
                        expContent.add(label, 0, 0);
                        expContent.add(textArea, 0, 1);
                        alert.getDialogPane().setExpandableContent(expContent);
                        alert.initOwner(primaryStage);
                        alert.showAndWait();
                        break;
                    }

                    case "0": {
                        WaitGUI waitGUI = new WaitGUI(playerNet);
                        try {
                            waitGUI.setUsername(username);
                            waitGUI.setRoomNum(num);
                            waitGUI.start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                    case "1": {
                        try {
                            in.close();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Player player = new Player(2, num,username);
                        player.play(primaryStage);
                        break;
                    }
                    case "2": {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Look, some errors occur");
                        Label label = new Label();
                        String rule = "The room is full.";
                        label.setText("The thing you should know:");
                        TextArea textArea = new TextArea(rule);
                        textArea.setEditable(false);
                        textArea.setWrapText(true);
                        textArea.setMaxWidth(1.7976931348623157E308D);
                        textArea.setMaxHeight(1.7976931348623157E308D);
                        GridPane.setVgrow(textArea, Priority.ALWAYS);
                        GridPane.setHgrow(textArea, Priority.ALWAYS);
                        GridPane expContent = new GridPane();
                        expContent.setMaxWidth(1.7976931348623157E308D);
                        expContent.add(label, 0, 0);
                        expContent.add(textArea, 0, 1);
                        alert.getDialogPane().setExpandableContent(expContent);
                        alert.initOwner(primaryStage);
                        alert.showAndWait();
                        break;
                    }


                }
            }
        });

        gridPane.add(numLabel, 0, 0);
        gridPane.add(roomNum, 1, 0);
        gridPane.add(button, 0, 1);
        button.setPadding(new

                Insets(0, 0, 0, 100));
        gridPane.setPadding(new

                Insets(300, 300, 200, 450));

        scene.getStylesheets().

                add(BeginGUI.class.getResource("beginCSS.css").

                        toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
