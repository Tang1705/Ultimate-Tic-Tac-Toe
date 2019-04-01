/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.dao;

import javafx.scene.layout.GridPane;
import tactictoe.client_side.component.GameButton;
import tactictoe.client_side.windows.LocalGameGUI;
import tactictoe.client_side.component.NetGameButton;
import tactictoe.client_side.windows.NetGameGUI;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class NetOperation implements Operation {
    private ArrayList<NetGameButton> arrayList = NetGameGUI.getButtonArrayList();
    private static ArrayList<Integer[]> judge = new ArrayList();
    private static int[] very = NetGameButton.getWinAreaNum();
    private BufferedReader in;
    private PrintWriter out;

    public NetOperation(BufferedReader bufferedReader, PrintWriter printWriter) {
        in = bufferedReader;
        out = printWriter;

    }

    @Override
    public String paint(String code) {
        out.println(code);
        return "";
    }

    @Override
    public void setSymbol(String code) {
        StringTokenizer stringTokenizer = new StringTokenizer(code, "-", false);
        String result = stringTokenizer.nextToken();
        String num = stringTokenizer.nextToken();
        if (!result.equals("999") && result != "999") {
            Iterator iterator = this.arrayList.iterator();
            int i = 0;

            while (iterator.hasNext()) {
                NetGameButton curGameButton = (NetGameButton) iterator.next();
                if (LocalGameGUI.getWin()) {
                    if (i == Integer.valueOf(num) - 1) {
                        if (!curGameButton.getWon()) {
                            Iterator anotherIterator = this.arrayList.iterator();

                            while (anotherIterator.hasNext()) {
                                GameButton aCurGameButton = (GameButton) anotherIterator.next();
                                if (aCurGameButton.getWon()) {
                                    aCurGameButton.setWhite(true);
                                    aCurGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: #e295c0;");
                                } else {
                                    aCurGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: rgba(226,149,192,0);");
                                }
                            }

                            return;
                        }

                        curGameButton.setWhite(true);
                        curGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: #e295c0;");
                    } else {
                        curGameButton.setWhite(false);
                        curGameButton.setStyle("-fx-border-width:3;-fx-border-color:rgba(226,149,192,0); ");
                    }

                    ++i;
                } else {
                    curGameButton.setWhite(false);
                    curGameButton.setStyle("-fx-border-width:3;-fx-border-color:rgba(226,149,192,0); ");
                }
            }
        }
    }

    @Override
    public void rule() {
        int i = 0;
        int winner = 66;

        for (Iterator iterator = judge.iterator(); iterator.hasNext(); ++i) {
            Integer[] curInteger = (Integer[]) iterator.next();
            if (curInteger[0] == curInteger[1] && curInteger[0] == curInteger[2] && curInteger[0] != -1) {
                winner = curInteger[0];
                ((NetGameButton) this.arrayList.get(i)).setOver();
                if (((NetGameButton) this.arrayList.get(i)).getWon() && ((NetGameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[3] == curInteger[4] && curInteger[3] == curInteger[5] && curInteger[3] != -1) {
                winner = curInteger[3];
                ((NetGameButton) this.arrayList.get(i)).setOver();
                if (((NetGameButton) this.arrayList.get(i)).getWon() && ((NetGameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[6] == curInteger[7] && curInteger[6] == curInteger[8] && curInteger[6] != -1) {
                winner = curInteger[6];
                ((NetGameButton) this.arrayList.get(i)).setOver();
                if (((NetGameButton) this.arrayList.get(i)).getWon() && ((NetGameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[0] == curInteger[3] && curInteger[0] == curInteger[6] && curInteger[0] != -1) {
                winner = curInteger[0];
                ((NetGameButton) this.arrayList.get(i)).setOver();
                if (((NetGameButton) this.arrayList.get(i)).getWon() && ((NetGameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[1] == curInteger[4] && curInteger[1] == curInteger[7] && curInteger[1] != -1) {
                winner = curInteger[1];
                ((NetGameButton) this.arrayList.get(i)).setOver();
                if (((NetGameButton) this.arrayList.get(i)).getWon() && ((NetGameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[2] == curInteger[5] && curInteger[2] == curInteger[8] && curInteger[2] != -1) {
                winner = curInteger[2];
                ((NetGameButton) this.arrayList.get(i)).setOver();
                if (((NetGameButton) this.arrayList.get(i)).getWon() && ((NetGameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[0] == curInteger[4] && curInteger[0] == curInteger[8] && curInteger[0] != -1) {
                winner = curInteger[0];
                ((NetGameButton) this.arrayList.get(i)).setOver();
                if (((NetGameButton) this.arrayList.get(i)).getWon() && ((NetGameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[2] == curInteger[4] && curInteger[2] == curInteger[6] && curInteger[2] != -1) {
                winner = curInteger[2];
                ((NetGameButton) this.arrayList.get(i)).setOver();
                if (((NetGameButton) this.arrayList.get(i)).getWon() && ((NetGameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            }
        }

        if (winner != 66 && i != 9) {
            if (winner == 0) {
                very[i] = 0;
                ((NetGameButton) this.arrayList.get(i)).setId("xPane");
            } else {
                very[i] = 1;
                ((NetGameButton) this.arrayList.get(i)).setId("oPane");
            }

            ((NetGameButton) this.arrayList.get(i)).setWhite(false);
            ((NetGameButton) this.arrayList.get(i)).setWon();
        }

        winner = -1;

        if (very[0] == very[1] && very[0] == very[2] && very[0] != -1) {
            winner = 5;
        } else if (very[3] == very[4] && very[3] == very[5] && very[3] != -1) {
            winner = 2;
        } else if (very[6] == very[7] && very[6] == very[8] && very[6] != -1) {
            winner = 6;
        } else if (very[0] == very[3] && very[0] == very[6] && very[0] != -1) {
            winner = 7;
        } else if (very[1] == very[4] && very[1] == very[7] && very[1] != -1) {
            winner = 1;
        } else if (very[2] == very[5] && very[2] == very[8] && very[2] != -1) {
            winner = 8;
        } else if (very[0] == very[4] && very[0] == very[8] && very[0] != -1) {
            winner = 3;
        } else if (very[2] == very[4] && very[2] == very[6] && very[2] != -1) {
            winner = 4;
        }

        GridPane gridPane = NetGameGUI.getGridPane();
        if (winner != -1) {
            LocalGameGUI.setWin();
            this.setSymbol("0-0");
            switch (winner) {
                case 0:
                    gridPane.setId("0Pane");
                    break;
                case 1:
                    gridPane.setId("1Pane");
                case 2:
                default:
                    break;
                case 3:
                    gridPane.setId("3Pane");
                    break;
                case 4:
                    gridPane.setId("4Pane");
                    break;
                case 5:
                    gridPane.setId("5Pane");
                    break;
                case 6:
                    gridPane.setId("6Pane");
                    break;
                case 7:
                    gridPane.setId("7Pane");
                    break;
                case 8:
                    gridPane.setId("8Pane");
            }
        }


    }
}
