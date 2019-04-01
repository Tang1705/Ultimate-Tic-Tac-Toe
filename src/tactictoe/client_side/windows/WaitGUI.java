/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.windows;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import tactictoe.client_side.dao.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tactictoe.client_side.dao.PlayerNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class WaitGUI extends Application {
    private Stage stage = null;
    private String roomNum = "";
    private String username = "";
    private PlayerNet playerNet;

    public WaitGUI(PlayerNet playerNet) {
        this.playerNet = playerNet;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        GridPane gridPane = new GridPane();


        gridPane.setId("waitPane");

        Listen listen = new Listen();
        Thread thread = new Thread(listen);
        thread.start();

        Scene scene = new Scene(gridPane, 1280, 720);
        scene.getStylesheets().add(BeginGUI.class.getResource("beginCSS.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Look, some info Tang want to tell you:");
        Label label = new Label();
        String rule = "Waiting for your friend.";
        label.setText("The information you should know:");

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


        thread.join();
        Player player = new Player(1, roomNum, username);
        player.play(stage);
    }

    private class Listen implements Runnable {
        @Override
        public void run() {
            boolean listen = true;
            PrintWriter out = playerNet.getOut();
            BufferedReader in = playerNet.getIn();

            while (listen) {
                out.println("3-" + roomNum);
                String result = "";
                try {
                    result = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(result.equals("2")||result=="2"){
                    try {
                        in.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

            }
        }
    }


    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String num) {
        roomNum = num;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
