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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import tactictoe.client_side.dao.PlayerNet;

import java.io.PrintWriter;

public class LogInGUI extends Application {
    public LogInGUI() {
    }

    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ultimate Tic-Tac-Toe Log In");
        BorderPane logInWindow = new BorderPane();
        Scene logInScene = new Scene(logInWindow, 1280.0D, 720.0D);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20.0D);
        gridPane.setVgap(20.0D);
        ColumnConstraints column1 = new ColumnConstraints(150.0D);
        ColumnConstraints column2 = new ColumnConstraints(50.0D, 150.0D, 300.0D);
        column2.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(new ColumnConstraints[]{column1, column2});
        Label usernameLabel = new Label("Username");
        final TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password");
        final PasswordField passwordField = new PasswordField();
        Label againPasswordLabel = new Label("Password again");
        final PasswordField againPasswordField = new PasswordField();
        Label telephoneLabel = new Label("Telephone");
        final TextField telephoneField = new TextField();
        Button logButton = new Button("OK");
        usernameLabel.getStylesheets().add(BeginGUI.class.getResource("beginCSS.css").toExternalForm());
        GridPane.setHalignment(usernameLabel, HPos.RIGHT);
        gridPane.add(usernameLabel, 0, 0);
        GridPane.setHalignment(passwordLabel, HPos.RIGHT);
        gridPane.add(passwordLabel, 0, 1);
        GridPane.setHalignment(againPasswordLabel, HPos.RIGHT);
        gridPane.add(againPasswordLabel, 0, 2);
        GridPane.setHalignment(telephoneLabel, HPos.RIGHT);
        gridPane.add(telephoneLabel, 0, 3);
        GridPane.setHalignment(usernameField, HPos.LEFT);
        gridPane.add(usernameField, 1, 0);
        GridPane.setHalignment(passwordField, HPos.LEFT);
        gridPane.add(passwordField, 1, 1);
        GridPane.setHalignment(againPasswordField, HPos.LEFT);
        gridPane.add(againPasswordField, 1, 2);
        GridPane.setHalignment(telephoneField, HPos.LEFT);
        gridPane.add(telephoneField, 1, 3);
        GridPane.setHalignment(logButton, HPos.CENTER);
        gridPane.add(logButton, 1, 4);
        logButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String userName = usernameField.getText();
                String password = String.valueOf(passwordField.getText());
                String againPass = String.valueOf(againPasswordField.getText());
                String telephone = telephoneField.getText();
                String result = "";
                if (!userName.equals("") && userName != null) {
                    if (!password.equals("") && password != null) {
                        if (!againPass.equals("") && againPass != null) {
                            if (!password.equals(againPass)) {
                                result = "4";
                            } else if (!telephone.equals("") && telephone != null) {
                                result = "6";
                            } else {
                                result = "5";
                            }
                        } else {
                            result = "3";
                        }
                    } else {
                        result = "2";
                    }
                } else {
                    result = "1";
                }

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Look, some errors occur");
                Label label = new Label();
                String rule = "";
                byte var11 = -1;
                switch (result.hashCode()) {
                    case 49:
                        if (result.equals("1")) {
                            var11 = 0;
                        }
                        break;
                    case 50:
                        if (result.equals("2")) {
                            var11 = 1;
                        }
                        break;
                    case 51:
                        if (result.equals("3")) {
                            var11 = 2;
                        }
                        break;
                    case 52:
                        if (result.equals("4")) {
                            var11 = 3;
                        }
                        break;
                    case 53:
                        if (result.equals("5")) {
                            var11 = 4;
                        }
                        break;
                    case 54:
                        if (result.equals("6")) {
                            var11 = 5;
                        }
                }

                switch (var11) {
                    case 0:
                        rule = "The name is empty.";
                        label.setText("The error you should notice:");
                        break;
                    case 1:
                        rule = "The first password is empty.";
                        label.setText("The error you should notice:");
                        break;
                    case 2:
                        rule = "The second password is empty";
                        label.setText("The error you should notice:");
                        break;
                    case 3:
                        rule = "The password are different in two fields.";
                        label.setText("The error you should notice:");
                        break;
                    case 4:
                        rule = "The telephone number is empty.";
                        label.setText("The error you should notice:");
                        break;
                    case 5:
                        alert.setAlertType(AlertType.INFORMATION);
                        label.setText("The information you should know:");
                        alert.setTitle("Congratulation");
                        alert.setHeaderText("Successful!");
                        rule = "You have logged in successfully.";
                        PlayerNet playerNet = new PlayerNet();
                        //BufferedReader in=playerNet.getIn();
                        PrintWriter out = playerNet.getOut();
                        out.println("5-0~" + userName + "~" + password + "~" + telephone);
                }

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
                if (result == "6" || result.equals("6")) {
                    BeginGUI beginGUI = new BeginGUI();
                    beginGUI.start(primaryStage);
                }

            }
        });

        logInWindow.setCenter(gridPane);
        primaryStage.getIcons().add(new Image("http://static.zybuluo.com/TangWill/du4235tohjaap56iakth6e32/icon.jpg"));
        gridPane.setPadding(new Insets(250.0D, 200.0D, 120.0D, 350.0D));
        logInScene.getStylesheets().add(BeginGUI.class.getResource("beginCSS.css").toExternalForm());
        primaryStage.setScene(logInScene);
        primaryStage.show();
    }
}
