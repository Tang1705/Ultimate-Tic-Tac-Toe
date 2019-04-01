/*
* Ultimate Tac-Tic-Toe by Tang
* 2018.12.25
* */

package tactictoe.client_side.windows;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tactictoe.client_side.dao.PlayerNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BeginGUI extends Application {
    public BeginGUI() {
    }

    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Ultimate Tic-Tac-Toe");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1280.0D, 720.0D);
        GridPane gridpane = new GridPane();
        gridpane.setHgap(20.0D);
        gridpane.setVgap(20.0D);
        ColumnConstraints column1 = new ColumnConstraints(100.0D);
        ColumnConstraints column2 = new ColumnConstraints(50.0D, 300.0D, 400.0D);
        column2.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(new ColumnConstraints[]{column1, column2});
        Label usernameLabel = new Label("username");
        usernameLabel.getStylesheets().add(BeginGUI.class.getResource("beginCSS.css").toExternalForm());
        final TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password");
        final PasswordField passwordField = new PasswordField();
        Button logInButton = new Button("Log In");
        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                LogInGUI logInGUI = new LogInGUI();

                try {
                    logInGUI.start(primaryStage);
                } catch (Exception var4) {
                    var4.printStackTrace();
                }

            }
        });

        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getText());
                String result = "";
                boolean sign = false;
                if (!username.equals("") && username != null) {
                    if (!password.equals("") && password != null) {
                        result = "3";
                    } else {
                        result = "2";
                    }
                } else {
                    result = "1";
                }

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Look, some errors you should find");
                alert.setContentText("Here are some errors!");
                String rule = "";
                Label label = new Label("The errors you should find are:");

                switch (result) {
                    case "1":
                        rule = "The name is empty.";
                        break;
                    case "2":
                        rule = "The password is empty.";
                        break;
                    case "3":
                        PlayerNet playerNet = new PlayerNet();
                        BufferedReader in = playerNet.getIn();
                        PrintWriter out = playerNet.getOut();
                        String success = "";
                        String record = "";
                        out.println("5-1~" + username + "~" + password);
                        try {
                            success = in.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (!success.equals("0") && success != "0") {
                            if (!success.equals("-1") && success != "-1") {
                                if (success.equals("-2") || success == "-2") {
                                    rule = "The password is wrong.";
                                }
                            } else {
                                rule = "The account doesn't exist.";
                            }
                        } else {
                            out.println("5-3~" + username);
                            try {
                                record=in.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            LanGUI lanGUI = new LanGUI(username, record);
                            try {
                                in.close();
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                lanGUI.start(primaryStage);
                            } catch (Exception var15) {
                                var15.printStackTrace();
                            }

                            sign = true;
                        }
                        break;

                }

                if (!sign) {
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

            }
        });

        GridPane.setHalignment(usernameLabel, HPos.RIGHT);
        gridpane.add(usernameLabel, 0, 0);
        GridPane.setHalignment(passwordLabel, HPos.RIGHT);
        gridpane.add(passwordLabel, 0, 1);
        GridPane.setHalignment(usernameField, HPos.LEFT);
        gridpane.add(usernameField, 1, 0);
        GridPane.setHalignment(passwordField, HPos.LEFT);
        gridpane.add(passwordField, 1, 1);
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(40.0D);
        flowPane.getChildren().add(logInButton);
        flowPane.getChildren().add(signUpButton);
        flowPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(signUpButton, HPos.RIGHT);
        gridpane.add(flowPane, 1, 2);
        Text title = new Text("Welcome to Tic-Tac-Toe");
        title.setId("titleText");
        GridPane mainPane = new GridPane();
        mainPane.setVgap(100.0D);
        ColumnConstraints columnMain = new ColumnConstraints();
        mainPane.getColumnConstraints().add(columnMain);
        mainPane.add(title, 1, 1);
        mainPane.add(gridpane, 1, 2);
        mainPane.setPadding(new Insets(80.0D, 200.0D, 120.0D, 350.0D));
        root.setCenter(mainPane);
        primaryStage.getIcons().add(new Image("http://static.zybuluo.com/TangWill/du4235tohjaap56iakth6e32/icon.jpg"));
        scene.getStylesheets().add(BeginGUI.class.getResource("beginCSS.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
