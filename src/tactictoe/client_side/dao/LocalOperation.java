/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javafx.scene.layout.GridPane;
import tactictoe.client_side.component.MyButton;
import tactictoe.client_side.component.GameButton;
import tactictoe.client_side.windows.LocalGameGUI;

public class LocalOperation implements Operation {
    static int time = 1;
    private ArrayList<GameButton> arrayList = LocalGameGUI.getButtonArrayList();
    private static ArrayList<Integer[]> judge = new ArrayList();
    private static int[] very = GameButton.getWinAreaNum();
    private static LocalOperation localOperation = null;

    private LocalOperation() {
        int i;
        for (i = 0; i < 9; ++i) {
            Integer[] integers = new Integer[9];

            for (int j = 0; j < 9; ++j) {
                integers[j] = -1;
            }

            judge.add(integers);
        }

        for (i = 0; i < 9; ++i) {
            very[i] = -1;
        }

    }

    public static LocalOperation getLocalOperation() {
        if (localOperation == null) {
            localOperation = new LocalOperation();
        }

        return localOperation;
    }

    public String paint(String code) {
        StringTokenizer stringTokenizer = new StringTokenizer(code, "-", false);
        int area = Integer.valueOf(stringTokenizer.nextToken());
        int num = Integer.valueOf(stringTokenizer.nextToken()) - 1;
        Iterator iterator = judge.iterator();

        for (int i = 0; iterator.hasNext(); ++i) {
            Integer[] curInteger = (Integer[]) iterator.next();
            if (i == area) {
                ArrayList<MyButton> arrayList1 = ((GameButton) this.arrayList.get(i)).getButtons();
                if (((GameButton) this.arrayList.get(i)).getWhite() && ((MyButton) arrayList1.get(num)).getWhite() && LocalGameGUI.getWin()) {
                    Iterator iterator1;
                    boolean full;
                    MyButton curMyButton;
                    if (time % 2 == 0) {
                        curInteger[num] = 0;
                        ((MyButton) arrayList1.get(num)).setWhite();
                        ++time;
                        iterator1 = arrayList1.iterator();

                        for (full = false; iterator1.hasNext(); full = true) {
                            curMyButton = (MyButton) iterator1.next();
                            if (curMyButton.getWhite()) {
                                full = false;
                                break;
                            }
                        }

                        if (full) {
                            ((GameButton) this.arrayList.get(i)).setWon();
                            ((GameButton) this.arrayList.get(i)).setWhite(false);
                            ((GameButton) this.arrayList.get(i)).setOver();
                        }

                        return "0";
                    }

                    curInteger[num] = 1;
                    ((MyButton) arrayList1.get(num)).setWhite();
                    iterator1 = arrayList1.iterator();

                    for (full = false; iterator1.hasNext(); full = true) {
                        curMyButton = (MyButton) iterator1.next();
                        if (curMyButton.getWhite()) {
                            full = false;
                            break;
                        }
                    }

                    if (full) {
                        ((GameButton) this.arrayList.get(i)).setWon();
                        ((GameButton) this.arrayList.get(i)).setWhite(false);
                        ((GameButton) this.arrayList.get(i)).setOver();
                    }

                    ++time;
                    return "1";
                }
            }
        }

        return "999";
    }

    public void setSymbol(String code) {
        StringTokenizer stringTokenizer = new StringTokenizer(code, "-", false);
        String result = stringTokenizer.nextToken();
        String num = stringTokenizer.nextToken();
        if (!result.equals("999") && result != "999") {
            Iterator iterator = this.arrayList.iterator();
            int i = 0;

            while (iterator.hasNext()) {
                GameButton curGameButton = (GameButton) iterator.next();
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

    public void rule() {
        int i = 0;
        int winner = 66;

        for (Iterator iterator = judge.iterator(); iterator.hasNext(); ++i) {
            Integer[] curInteger = (Integer[]) iterator.next();
            if (curInteger[0] == curInteger[1] && curInteger[0] == curInteger[2] && curInteger[0] != -1) {
                winner = curInteger[0];
                ((GameButton) this.arrayList.get(i)).setOver();
                if (((GameButton) this.arrayList.get(i)).getWon() && ((GameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[3] == curInteger[4] && curInteger[3] == curInteger[5] && curInteger[3] != -1) {
                winner = curInteger[3];
                ((GameButton) this.arrayList.get(i)).setOver();
                if (((GameButton) this.arrayList.get(i)).getWon() && ((GameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[6] == curInteger[7] && curInteger[6] == curInteger[8] && curInteger[6] != -1) {
                winner = curInteger[6];
                ((GameButton) this.arrayList.get(i)).setOver();
                if (((GameButton) this.arrayList.get(i)).getWon() && ((GameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[0] == curInteger[3] && curInteger[0] == curInteger[6] && curInteger[0] != -1) {
                winner = curInteger[0];
                ((GameButton) this.arrayList.get(i)).setOver();
                if (((GameButton) this.arrayList.get(i)).getWon() && ((GameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[1] == curInteger[4] && curInteger[1] == curInteger[7] && curInteger[1] != -1) {
                winner = curInteger[1];
                ((GameButton) this.arrayList.get(i)).setOver();
                if (((GameButton) this.arrayList.get(i)).getWon() && ((GameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[2] == curInteger[5] && curInteger[2] == curInteger[8] && curInteger[2] != -1) {
                winner = curInteger[2];
                ((GameButton) this.arrayList.get(i)).setOver();
                if (((GameButton) this.arrayList.get(i)).getWon() && ((GameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[0] == curInteger[4] && curInteger[0] == curInteger[8] && curInteger[0] != -1) {
                winner = curInteger[0];
                ((GameButton) this.arrayList.get(i)).setOver();
                if (((GameButton) this.arrayList.get(i)).getWon() && ((GameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            } else if (curInteger[2] == curInteger[4] && curInteger[2] == curInteger[6] && curInteger[2] != -1) {
                winner = curInteger[2];
                ((GameButton) this.arrayList.get(i)).setOver();
                if (((GameButton) this.arrayList.get(i)).getWon() && ((GameButton) this.arrayList.get(i)).getOver()) {
                    break;
                }
            }
        }

        if (winner != 66 && i != 9) {
            if (winner == 0) {
                very[i] = 0;
                ((GameButton) this.arrayList.get(i)).setId("xPane");
            } else {
                very[i] = 1;
                ((GameButton) this.arrayList.get(i)).setId("oPane");
            }

            ((GameButton) this.arrayList.get(i)).setWhite(false);
            ((GameButton) this.arrayList.get(i)).setWon();
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

        GridPane gridPane = LocalGameGUI.getGridPane();
        if (winner != -1) {
            LocalGameGUI.setWin();
            this.setSymbol("0-0");
            switch (winner) {
                case 1:
                    gridPane.setId("1Pane");
                case 2:
                    gridPane.setId("0Pane");
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
