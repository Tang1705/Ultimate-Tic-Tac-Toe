/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.windows;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import tactictoe.client_side.component.GameButton;

public class LocalGameGUI extends Application {
    private static ArrayList<GameButton> buttonArrayList = new ArrayList();
    private static GridPane gridPane;
    private static boolean win = true;

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Ultimate Tic-Tac-Toe");
        gridPane = new GridPane();
        gridPane.setVgap(30.0D);
        gridPane.setHgap(155.0D);
        Scene scene = new Scene(gridPane, 1280.0D, 720.0D);
        ColumnConstraints[] columnConstraints = new ColumnConstraints[3];

        int area;
        for (area = 0; area < 3; ++area) {
            columnConstraints[area] = new ColumnConstraints(50.0D);
            gridPane.getColumnConstraints().add(columnConstraints[area]);
        }

        area = 0;
        GameButton[][] gameButtons = new GameButton[3][3];

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                gameButtons[i][j] = new GameButton(area++);
                buttonArrayList.add(gameButtons[i][j]);
            }
        }

        gridPane.add(gameButtons[0][0], 0, 0);
        gridPane.add(gameButtons[0][1], 1, 0);
        gridPane.add(gameButtons[0][2], 2, 0);
        gridPane.add(gameButtons[1][0], 0, 1);
        gridPane.add(gameButtons[1][1], 1, 1);
        gridPane.add(gameButtons[1][2], 2, 1);
        gridPane.add(gameButtons[2][0], 0, 2);
        gridPane.add(gameButtons[2][1], 1, 2);
        gridPane.add(gameButtons[2][2], 2, 2);


        String url = this.getClass().getResource("/tactictoe/client_side/style/child.wav").toString();
        Media media = new Media(url);
        MediaPlayer player = new MediaPlayer(media);
        player.setAutoPlay(true);
        player.setCycleCount(20);
        player.play();



        primaryStage.getIcons().add(new Image("http://static.zybuluo.com/TangWill/du4235tohjaap56iakth6e32/icon.jpg"));
        gridPane.setPadding(new Insets(70.0D, 200.0D, 200.0D, 350.0D));
        primaryStage.setScene(scene);
        scene.getStylesheets().add(BeginGUI.class.getResource("operationCSS.css").toExternalForm());
        primaryStage.show();


    }


    public static GridPane getGridPane() {
        return gridPane;
    }

    public static ArrayList<GameButton> getButtonArrayList() {
        return buttonArrayList;
    }

    public static boolean getWin() {
        return win;
    }

    public static void setWin() {
        win = false;
    }

}
