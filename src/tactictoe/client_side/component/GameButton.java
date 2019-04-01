/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.component;

import tactictoe.client_side.dao.LocalOperation;
import tactictoe.client_side.dao.Operation;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GameButton extends GridPane {
    private boolean white = true;
    private boolean over = false;
    private boolean won = true;
    private int area;
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

    public GameButton(final int area) {
        this.area = area;
        this.setVgap(8.0D);
        this.setHgap(8.0D);
        final Operation operation = LocalOperation.getLocalOperation();
        RowConstraints[] rowConstraints = new RowConstraints[3];

        for(int i = 0; i < 3; i++) {
            rowConstraints[i] = new RowConstraints(50.0D);
            this.getRowConstraints().add(rowConstraints[i]);
        }

        this.button1 = new MyButton(1);
        this.buttonArrayList.add(this.button1);
        this.button1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String result = operation.paint(area+"-1");
                if (!result.equals("0") && result != "0") {
                    if (result.equals("1") || result == "1") {
                         button1.setId("obutton");
                         button1.setWhite();
                    }
                } else {
                     button1.setId("xbutton");
                     button1.setWhite();
                }

                operation.rule();
                operation.setSymbol(result+"-1");
            }
        });

        this.button2 = new MyButton(2);
        this.buttonArrayList.add(this.button2);
        this.button2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String result = operation.paint(area+"-2");
                if (!result.equals("0") && result != "0") {
                    if (result.equals("1") || result == "1") {
                         button2.setId("obutton");
                         button2.setWhite();
                    }
                } else {
                     button2.setId("xbutton");
                     button2.setWhite();
                }

                operation.rule();
                operation.setSymbol(result+"-2");
            }
        });
        this.button3 = new MyButton(3);
        this.buttonArrayList.add(this.button3);
        this.button3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String result = operation.paint(area+"-3");
                if (!result.equals("0") && result != "0") {
                    if (result.equals("1") || result == "1") {
                         button3.setId("obutton");
                         button3.setWhite();
                    }
                } else {
                     button3.setId("xbutton");
                     button3.setWhite();
                }

                operation.rule();
                operation.setSymbol(result+"-3");
            }
        });

        this.button4 = new MyButton(4);
        this.buttonArrayList.add(this.button4);
        this.button4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String result = operation.paint(area+"-4");
                if (!result.equals("0") && result != "0") {
                    if (result.equals("1") || result == "1") {
                         button4.setId("obutton");
                         button4.setWhite();
                    }
                } else {
                     button4.setId("xbutton");
                     button4.setWhite();
                }

                operation.rule();
                operation.setSymbol(result+"-4");
            }
        });

        this.button5 = new MyButton(5);
        this.buttonArrayList.add(this.button5);
        this.button5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String result = operation.paint(area+"-5");
                if (!result.equals("0") && result != "0") {
                    if (result.equals("1") || result == "1") {
                         button5.setId("obutton");
                         button5.setWhite();
                    }
                } else {
                     button5.setId("xbutton");
                     button5.setWhite();
                }

                operation.rule();
                operation.setSymbol(result+"-5");
            }
        });
        this.button6 = new MyButton(6);
        this.buttonArrayList.add(this.button6);
        this.button6.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String result = operation.paint(area+"-6");
                if (!result.equals("0") && result != "0") {
                    if (result.equals("1") || result == "1") {
                         button6.setId("obutton");
                         button6.setWhite();
                    }
                } else {
                     button6.setId("xbutton");
                     button6.setWhite();
                }

                operation.rule();
                operation.setSymbol(result+"-6");
            }
        });
        this.button7 = new MyButton(7);
        this.buttonArrayList.add(this.button7);
        this.button7.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String result = operation.paint(area+"-7");
                if (!result.equals("0") && result != "0") {
                    if (result.equals("1") || result == "1") {
                         button7.setId("obutton");
                         button7.setWhite();
                    }
                } else {
                     button7.setId("xbutton");
                     button7.setWhite();
                }

                operation.rule();
                operation.setSymbol(result+"-7");
            }
        });
        this.button8 = new MyButton(8);
        this.buttonArrayList.add(this.button8);
        this.button8.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String result = operation.paint(area+"-8");
                if (!result.equals("0") && result != "0") {
                    if (result.equals("1") || result == "1") {
                         button8.setId("obutton");
                         button8.setWhite();
                    }
                } else {
                     button8.setId("xbutton");
                     button8.setWhite();
                }

                operation.rule();
                operation.setSymbol(result+"-8");
            }
        });
        this.button9 = new MyButton(9);
        this.buttonArrayList.add(this.button9);
        this.button9.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String result = operation.paint(area+"-9");
                if (!result.equals("0") && result != "0") {
                    if (result.equals("1") || result == "1") {
                         button9.setId("obutton");
                         button9.setWhite();
                    }
                } else {
                     button9.setId("xbutton");
                     button9.setWhite();
                }

                operation.rule();
                operation.setSymbol(result+"-9");
            }
        });
        this.add(this.button1, 0, 0);
        this.add(this.button2, 1, 0);
        this.add(this.button3, 2, 0);
        this.add(this.button4, 0, 1);
        this.add(this.button5, 1, 1);
        this.add(this.button6, 2, 1);
        this.add(this.button7, 0, 2);
        this.add(this.button8, 1, 2);
        this.add(this.button9, 2, 2);
    }

    public static int[] getWinAreaNum() {
        return winAreaNum;
    }

    public ArrayList getButtons() {
        return this.buttonArrayList;
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
