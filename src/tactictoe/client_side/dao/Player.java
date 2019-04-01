/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.dao;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tactictoe.client_side.windows.NetGameGUI;
import java.io.*;
import java.net.Socket;

public class Player {
    private String roomNum;
    private String username = "";
    private Socket socket;
    private int no;
    private static BufferedReader in;
    private static PrintWriter out;


    public Player(int number, String num, String name) {
        roomNum = num;
        username = name;
        no = number;

        try {
            socket = new Socket("192.168.43.62", 5618);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(Stage primaryStage) {
        NetGameGUI netGameGUI = new NetGameGUI(in, out,username);
        netGameGUI.setNumOfClient(no);
        GridPane gridPane = NetGameGUI.getGridPane();
        Receive receive = new Receive(in, out, gridPane);
        Thread thread = new Thread(receive);
        thread.start();

        try {
            netGameGUI.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static BufferedReader getIn() {
        return in;
    }

    public static PrintWriter getOut() {
        return out;
    }

    public int getNo() {
        return no;
    }


    public String getRoomNum() {
        return roomNum;
    }

    public String getUsername() {
        return username;
    }

}
