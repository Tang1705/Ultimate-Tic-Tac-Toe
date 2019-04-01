/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.component;

import tactictoe.client_side.dao.NetOperation;
import tactictoe.client_side.dao.Operation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NetGameButton extends GridPane {
    private boolean white = true;
    private boolean over = false;
    private boolean won = true;
    private int area;
    private int userOfClient;
    private static int[] winAreaNum = new int[9];
    private ArrayList<MyButton> buttonArrayList = new ArrayList();
    private MyButton button1;
    private MyButton button2;
    private MyButton button3;
    private MyButton button4;
    private MyButton button5;
    private MyButton button6;
    private MyButton button7;
    private MyButton button8;
    private MyButton button9;
    private BufferedReader in;
    private PrintWriter out;

    public NetGameButton(final int user, final int area, BufferedReader bufferedReader, PrintWriter printWriter) {
        this.userOfClient = user;
        this.area = area;
        this.setVgap(8.0D);
        this.setHgap(8.0D);
        RowConstraints[] rowConstraints = new RowConstraints[3];
        in = bufferedReader;
        out = printWriter;
        Operation operation = new NetOperation(in, out);


        for (int i = 0; i < 3; i++) {
            rowConstraints[i] = new RowConstraints(50.0D);
            this.getRowConstraints().add(rowConstraints[i]);
        }


        button1 = new MyButton(1);
        buttonArrayList.add(button1);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                operation.paint("2-" + userOfClient + "-" + area + "-1");
            }
        });

        button2 = new MyButton(2);
        buttonArrayList.add(button2);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                operation.paint("2-" + userOfClient + "-" + area + "-2");
            }
        });

        button3 = new MyButton(3);
        buttonArrayList.add(button3);
        button3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                operation.paint("2-" + userOfClient + "-" + area + "-3");

            }
        });

        button4 = new MyButton(4);
        buttonArrayList.add(button4);
        button4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                operation.paint("2-" + userOfClient + "-" + area + "-4");
            }
        });

        button5 = new MyButton(5);
        buttonArrayList.add(button5);
        button5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                operation.paint("2-" + userOfClient + "-" + area + "-5");
            }
        });

        button6 = new MyButton(6);
        buttonArrayList.add(button6);
        button6.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                operation.paint("2-" + userOfClient + "-" + area + "-6");
            }
        });

        button7 = new MyButton(7);
        buttonArrayList.add(button7);
        button7.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                operation.paint("2-" + userOfClient + "-" + area + "-7");
            }
        });

        button8 = new MyButton(8);
        buttonArrayList.add(button8);
        button8.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                operation.paint("2-" + userOfClient + "-" + area + "-8");
            }
        });

        button9 = new MyButton(9);
        buttonArrayList.add(button9);
        button9.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                operation.paint("2-" + userOfClient + "-" + area + "-9");
            }
        });

        this.add(button1, 0, 0);
        this.add(button2, 1, 0);
        this.add(button3, 2, 0);
        this.add(button4, 0, 1);
        this.add(button5, 1, 1);
        this.add(button6, 2, 1);
        this.add(button7, 0, 2);
        this.add(button8, 1, 2);
        this.add(button9, 2, 2);
    }


    public static int[] getWinAreaNum() {
        return winAreaNum;
    }

    public ArrayList getButtons() {
        return buttonArrayList;
    }

    public boolean getWhite() {
        return this.white;
    }

    public void setWhite(boolean decision) {
        this.white = decision;
    }

    public void setOver() {
        this.over = true;
    }

    public boolean getOver() {
        return this.over;
    }

    public int getArea() {
        return this.area;
    }

    public void setWon() {
        this.won = false;
    }

    public boolean getWon() {
        return this.won;
    }

    public Node getStyleableNode() {
        return null;
    }
}
