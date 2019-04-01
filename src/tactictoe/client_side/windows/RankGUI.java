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
import tactictoe.client_side.dao.Rank;

public class RankGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(30.0D);
        gridPane.setHgap(30.0D);
        Scene scene = new Scene(gridPane, 1280, 720);

        RowConstraints rowConstraints1 = new RowConstraints();
        RowConstraints rowConstraints2 = new RowConstraints();
        RowConstraints rowConstraints3 = new RowConstraints();
        RowConstraints rowConstraints4 = new RowConstraints();

        gridPane.getRowConstraints().addAll(rowConstraints1, rowConstraints2, rowConstraints3, rowConstraints4);

        Label[] numLabel = new Label[3];
        Label[] nameLabel = new Label[3];
        Label[] scoreLabel = new Label[3];

        int j=0;
        for (int i = 0; i < 3; i++) {
            numLabel[i] = new Label(j++ + "");
            numLabel[i].setId("choiceLabel");
            nameLabel[i] = new Label();
            scoreLabel[i] = new Label();
        }

        Rank rank = new Rank();
        rank.readRank();
        String[] name = rank.getName();
        String[] score = rank.getScore();


        Label button = new Label("Back");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainGUI mainGUI = new MainGUI();
                try {
                    mainGUI.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        for (int i = 0; i < 3; i++) {
            nameLabel[i].setText(name[i+1]);
            nameLabel[i].setId("choiceLabel");
            scoreLabel[i].setText(score[i+1]);
            scoreLabel[i].setId("choiceLabel");
        }


        gridPane.add(numLabel[0],0,0);
        gridPane.add(numLabel[1],0,1);
        gridPane.add(numLabel[2],0,2);
        gridPane.add(nameLabel[0], 1, 0);
        gridPane.add(nameLabel[1], 1, 1);
        gridPane.add(nameLabel[2], 1, 2);
        gridPane.add(scoreLabel[0], 2, 0);
        gridPane.add(scoreLabel[1], 2, 1);
        gridPane.add(scoreLabel[2], 2, 2);

        gridPane.add(button, 5, 5);
        button.setId("infoLabel");

        gridPane.setPadding(new Insets(200, 400, 200, 450));

        scene.getStylesheets().add(BeginGUI.class.getResource("beginCSS.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
