/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.windows;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class MainGUI extends Application {
    public MainGUI() {
    }

    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ultimate Tic-Tac-Toe");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1280.0D, 720.0D);
        GridPane buttonPane = new GridPane();
        buttonPane.setVgap(30.0D);
        ColumnConstraints column = new ColumnConstraints(700.0D);
        buttonPane.getColumnConstraints().addAll(new ColumnConstraints[]{column});
        Label singleButton = new Label("Single");
        singleButton.setId("choiceLabel");
        singleButton.setWrapText(true);
        singleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                BeginGUI beginGUI = new BeginGUI();
                beginGUI.start(primaryStage);
            }
        });

        Label doubleButton = new Label("Double");
        doubleButton.setId("choiceLabel");
        doubleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LocalGameGUI singleGameGUI = new LocalGameGUI();

                try {
                    singleGameGUI.start(primaryStage);
                } catch (Exception var4) {
                    var4.printStackTrace();
                }

            }
        });

        Label ruleButton = new Label("Rule");
        ruleButton.setId("choiceLabel");
        ruleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Rule and Tip");
                alert.setHeaderText("Look, some rules you should know");
                alert.setContentText("Here are some tips from Tang!");
                String rule = "On the Tac-Tic-Toe game, each square of the 3 * 3 game board contains another smaller 3 * 3 game board.\n" +
                        "Where the player makes his move in a square of any small board,the opponent is sent in the respective square of the big board.\n"+
                        "If the player is sent to an already won or draw board, then he can go wherever he likes.\n"+
                        "3 squares in-a-row in a small board wins the small board and the big square .\n"+
                        "3 squares in-a-row in the big board wins the game.\n"+
                        "A draw board will not count for either players.\n"+
                        "Circle(o) always starts first.";
                Label label = new Label("The rules you should know are:");
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
            }
        });

        Label recordButton = new Label("Record");
        recordButton.setId("choiceLabel");
        recordButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                RankGUI rankGUI =new RankGUI();
                try {
                    rankGUI.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Label quitButton = new Label("Quit");
        quitButton.setId("choiceLabel");
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        Label title = new Label("Welcome to Tic-Tac-Toe");
        title.setId("titleText");
        buttonPane.add(title, 0, 0);
        buttonPane.add(singleButton, 0, 1);
        buttonPane.add(doubleButton, 0, 2);
        buttonPane.add(ruleButton, 0, 3);
        buttonPane.add(recordButton, 0, 4);
        buttonPane.add(quitButton, 0, 5);
        buttonPane.setPadding(new Insets(100.0D, 500.0D, 800.0D, 200.0D));
        root.setCenter(buttonPane);
        primaryStage.getIcons().add(new Image("http://static.zybuluo.com/TangWill/cinm0c9yyyhzuf1d1wvj3mt1/icon.png"));
        scene.getStylesheets().add(BeginGUI.class.getResource("beginCSS.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
