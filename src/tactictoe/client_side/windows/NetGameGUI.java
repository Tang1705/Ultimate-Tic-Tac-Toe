/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.windows;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import tactictoe.client_side.component.NetGameButton;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NetGameGUI extends Application {
    private int numOfClient;
    private static ArrayList<NetGameButton> buttonArrayList = new ArrayList();
    private static GridPane gridPane;
    private static boolean win = true;
    private BufferedReader in;
    private PrintWriter out;
    private String userName = "";
    private static Button turnNameLabel;
    private static Label turnLabel;

    public NetGameGUI(BufferedReader bufferedReader, PrintWriter printWriter, String name) {
        in = bufferedReader;
        out = printWriter;
        gridPane = new GridPane();
        userName = name;
        turnNameLabel=new Button();
        turnLabel=new Label();
    }

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Ultimate Tic-Tac-Toe");
        gridPane.setVgap(30.0D);
        gridPane.setHgap(155.0D);
        Scene scene = new Scene(gridPane, 1280.0D, 720.0D);
        ColumnConstraints[] columnConstraints = new ColumnConstraints[3];

        int area;
        for (area = 0; area < 3; ++area) {
            columnConstraints[area] = new ColumnConstraints(50.0D);
            gridPane.getColumnConstraints().add(columnConstraints[area]);
        }

        ColumnConstraints columnConstraints1=new ColumnConstraints();
        ColumnConstraints columnConstraints2=new ColumnConstraints(150.0D);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getColumnConstraints().add(columnConstraints2);

        area = 0;
        NetGameButton[][] gameButtons = new NetGameButton[3][3];

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                gameButtons[i][j] = new NetGameButton(numOfClient, area++, in, out);
                buttonArrayList.add(gameButtons[i][j]);
            }
        }

        Label nameLabel = new Label("   "+userName);
        nameLabel.setStyle("-fx-font-size: 32px;\n" +
                "    -fx-font-family: \"Snap ITC\";\n" +
                "    -fx-fill: #818181;\n" +
                "    -fx-effect: innershadow(three-pass-box, rgba(0, 0, 0, 0.7), 6, 0.0, 0, 2);");

        //Label turnLabel =new Label("Turn");
        turnLabel.setStyle("-fx-font-size: 18px;\n" +
                "    -fx-font-family: \"Snap ITC\";\n" +
                "    -fx-fill: #818181;\n" +
                "    -fx-effect: innershadow(three-pass-box, rgba(0, 0, 0, 0.7), 6, 0.0, 0, 2);");

        //turnNameLabel =new Label();

        Label turn =new Label("Turn");
        turn.setStyle("-fx-font-size: 18px;\n" +
                "    -fx-font-family: \"Snap ITC\";\n" +
                "    -fx-fill: #818181;\n" +
                "    -fx-effect: innershadow(three-pass-box, rgba(0, 0, 0, 0.7), 6, 0.0, 0, 2);");
        BorderPane borderPane =new BorderPane();
        borderPane.setLeft(turn);
        borderPane.setCenter(turnNameLabel);
        borderPane.setBottom(nameLabel);
        gridPane.add(gameButtons[0][0], 0, 0);
        gridPane.add(gameButtons[0][1], 1, 0);
        gridPane.add(gameButtons[0][2], 2, 0);
        gridPane.add(gameButtons[1][0], 0, 1);
        gridPane.add(gameButtons[1][1], 1, 1);
        gridPane.add(gameButtons[1][2], 2, 1);
        gridPane.add(gameButtons[2][0], 0, 2);
        gridPane.add(gameButtons[2][1], 1, 2);
        gridPane.add(gameButtons[2][2], 2, 2);
        gridPane.add(borderPane,4,2);
        gridPane.add(turnLabel,1,3);

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

    public static Button getTurnNameLabel(){
        return turnNameLabel;
    }

    public static Label getTurnLabel(){
        return turnLabel;
    }

    public void setNumOfClient(int num) {
        numOfClient = num;
    }

    public static GridPane getGridPane() {
        return gridPane;
    }

    public static ArrayList<NetGameButton> getButtonArrayList() {
        return buttonArrayList;
    }

    public static boolean getWin() {
        return win;
    }

    public static void setWin() {
        win = false;
    }
}
