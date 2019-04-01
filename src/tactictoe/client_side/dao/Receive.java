/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.dao;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tactictoe.client_side.component.MyButton;
import tactictoe.client_side.component.NetGameButton;
import tactictoe.client_side.windows.NetGameGUI;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Receive implements Runnable {
    private BufferedReader in;
    private PrintWriter out;
    private static String result = "";
    private boolean can = true;
    private ArrayList<int[]> arrayListOfNetGameButton = new ArrayList<>();
    private int[] judge = new int[9];
    private int winner = -1;
    private static ArrayList<NetGameButton> arrayListOfBlock = NetGameGUI.getButtonArrayList();
    private GridPane gridPane = NetGameGUI.getGridPane();
    private int[] winArea = new int[9];
    private Button turnNameLabel = NetGameGUI.getTurnNameLabel();
    private String name_first = "";
    private String name_second = "";
    private Label turnLabel = NetGameGUI.getTurnLabel();
    private int won;

    public Receive(BufferedReader bufferedReader, PrintWriter printWriter, GridPane gridPane) {
        this.gridPane = gridPane;
        in = bufferedReader;
        out = printWriter;
        for (int i = 0; i < 9; i++) {
            judge[i] = -1;
        }

        for (int i = 0; i < 9; i++) {
            int[] buttons = new int[9];
            for (int j = 0; j < 9; j++) {
                buttons[j] = -1;
            }
            arrayListOfNetGameButton.add(buttons);
        }

        for (int i = 0; i < 9; i++) {
            winArea[i] = -1;
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                out.println("4-0");
                result = in.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(result, "~", false);
                String firstJudge = stringTokenizer.nextToken();
                if (!firstJudge.equals("wait")) {
                    String area = firstJudge;
                    String button = stringTokenizer.nextToken();
                    String user = stringTokenizer.nextToken();

                    int intArea = Integer.parseInt(area);
                    int intButton = Integer.valueOf(button);
                    int intUser = Integer.parseInt(user);

                    if (can) {
                        if (judge[intArea] == -1 && arrayListOfNetGameButton.get(intArea)[intButton] == -1) {
                            MyButton myButton = (MyButton) arrayListOfBlock.get(intArea).getButtons().get(intButton);
                            if (intUser == 1) {
                                arrayListOfNetGameButton.get(intArea)[intButton] = 1;
                                myButton.setId("obutton");

                            } else {
                                arrayListOfNetGameButton.get(intArea)[intButton] = 0;
                                myButton.setId("xbutton");
                            }
                        }

                        boolean win = false;

                        int[] curInt = arrayListOfNetGameButton.get(intArea);

                        if (curInt[0] == curInt[1] && curInt[0] == curInt[2] && curInt[0] != -1) {
                            winner = curInt[0];
                            win = true;
                        } else if (curInt[3] == curInt[4] && curInt[3] == curInt[5] && curInt[3] != -1) {
                            winner = curInt[3];
                            win = true;
                        } else if (curInt[6] == curInt[7] && curInt[6] == curInt[8] && curInt[6] != -1) {
                            winner = curInt[6];
                            win = true;
                        } else if (curInt[0] == curInt[3] && curInt[0] == curInt[6] && curInt[0] != -1) {
                            winner = curInt[0];
                            win = true;
                        } else if (curInt[1] == curInt[4] && curInt[1] == curInt[7] && curInt[1] != -1) {
                            winner = curInt[1];
                            win = true;
                        } else if (curInt[2] == curInt[5] && curInt[2] == curInt[8] && curInt[2] != -1) {
                            winner = curInt[2];
                            win = true;
                        } else if (curInt[0] == curInt[4] && curInt[0] == curInt[8] && curInt[0] != -1) {
                            winner = curInt[0];
                            win = true;
                        } else if (curInt[2] == curInt[4] && curInt[2] == curInt[6] && curInt[2] != -1) {
                            winner = curInt[2];
                            win = true;
                        }

                        if (win) {
                            try {
                                winArea[intArea] = winner;
                                Label turnLabel = NetGameGUI.getTurnLabel();
                                if (winner == 1) {
                                    arrayListOfBlock.get(intArea).setId("oPane");
                                } else {
                                    arrayListOfBlock.get(intArea).setId("xPane");
                                }
                            } catch (ConcurrentModificationException e) {

                            }

                        }

                        if (winArea[0] == winArea[1] && winArea[0] == winArea[2] && winArea[0] != -1) {
                            gridPane.setId("5Pane");
                            this.winSet();
                            won = winArea[0];
                            can = false;
                        } else if (winArea[3] == winArea[4] && winArea[3] == winArea[5] && winArea[3] != -1) {
                            gridPane.setId("0Pane");
                            this.winSet();
                            won = winArea[3];
                            can = false;
                        } else if (winArea[6] == winArea[7] && winArea[6] == winArea[8] && winArea[6] != -1) {
                            gridPane.setId("6Pane");
                            this.winSet();
                            won = winArea[6];
                            can = false;
                        } else if (winArea[0] == winArea[3] && winArea[0] == winArea[6] && winArea[0] != -1) {
                            gridPane.setId("7Pane");
                            this.winSet();
                            won = winArea[0];
                            can = false;
                        } else if (winArea[1] == winArea[4] && winArea[1] == winArea[7] && winArea[1] != -1) {
                            gridPane.setId("1Pane");
                            this.winSet();
                            won = winArea[1];
                            can = false;
                        } else if (winArea[2] == winArea[5] && winArea[2] == winArea[8] && winArea[2] != -1) {
                            gridPane.setId("8Pane");
                            this.winSet();
                            won = winArea[2];
                            can = false;
                        } else if (winArea[0] == winArea[4] && winArea[0] == winArea[8] && winArea[0] != -1) {
                            gridPane.setId("3Pane");
                            this.winSet();
                            won = winArea[0];
                            can = false;
                        } else if (winArea[2] == winArea[4] && winArea[2] == winArea[6] && winArea[2] != -1) {
                            gridPane.setId("4Pane");
                            this.winSet();
                            won = winArea[2];
                            can = false;
                        } else {
                            if (intUser == 1) {
                                turnNameLabel.setStyle("-fx-background-image: url('/tactictoe/client_side/style/xturn.png');");
                            } else {
                                turnNameLabel.setStyle("-fx-background-image: url('/tactictoe/client_side/style/oturn.png');");
                            }


                            Iterator<NetGameButton> netGameButtonIterator = arrayListOfBlock.iterator();
                            int i = 0;
                            while (netGameButtonIterator.hasNext()) {
                                NetGameButton curNetGameButton = (NetGameButton) netGameButtonIterator.next();
                                if (winArea[intButton] == -1) {
                                    boolean full = false;
                                    int[] mark = (int[]) arrayListOfNetGameButton.get(intButton);
                                    for (int a : mark) {
                                        if (a != -1) {
                                            winArea[intButton]=-2;
                                            full = true;
                                        }else {
                                            winArea[intButton]=-1;
                                            full=false;
                                            break;
                                        }
                                    }

                                    if (!full) {
                                        if (i == intButton) {
                                            curNetGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: #e295c0;");
                                        } else {
                                            curNetGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: rgba(226,149,192,0);");
                                        }
                                    }else {
                                        Iterator<NetGameButton> anotherNetGameButtonIterator = arrayListOfBlock.iterator();
                                        int j = 0;
                                        while (anotherNetGameButtonIterator.hasNext()) {
                                            NetGameButton anotherCurNetGameButton = (NetGameButton) anotherNetGameButtonIterator.next();
                                            if (winArea[j] == -1) {
                                                anotherCurNetGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: #e295c0;");
                                            } else {
                                                anotherCurNetGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: rgba(226,149,192,0);");
                                            }
                                            j++;
                                        }
                                        break;
                                    }
                                } else {
                                    Iterator<NetGameButton> anotherNetGameButtonIterator = arrayListOfBlock.iterator();
                                    int j = 0;
                                    while (anotherNetGameButtonIterator.hasNext()) {
                                        NetGameButton anotherCurNetGameButton = (NetGameButton) anotherNetGameButtonIterator.next();
                                        if (winArea[j] == -1) {
                                            anotherCurNetGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: #e295c0;");
                                        } else {
                                            anotherCurNetGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: rgba(226,149,192,0);");
                                        }
                                        j++;
                                    }
                                    break;
                                }
                                i++;
                            }

                        }

                        if (!can) {
                            Platform.runLater(() -> {
                                if (won == 1) {
                                    turnLabel.setText(name_first);
                                    turnLabel.setStyle("-fx-font-size: 18px;\n" +
                                            "    -fx-font-family: \"Snap ITC\";\n" +
                                            "    -fx-fill: #818181;\n" +
                                            "    -fx-effect: innershadow(three-pass-box, rgba(0, 0, 0, 0.7), 6, 0.0, 0, 2);");
                                } else {
                                    turnLabel.setText(name_second);
                                    turnLabel.setStyle("-fx-font-size: 18px;\n" +
                                            "    -fx-font-family: \"Snap ITC\";\n" +
                                            "    -fx-fill: #818181;\n" +
                                            "    -fx-effect: innershadow(three-pass-box, rgba(0, 0, 0, 0.7), 6, 0.0, 0, 2);");
                                }
                            });
                        }
                    }

                } else {
                    name_first = stringTokenizer.nextToken();
                    name_second = stringTokenizer.nextToken();
                    turnNameLabel.setStyle("-fx-background-image: url('/tactictoe/client_side/style/oturn.png');");
                }
            } catch (Exception e) {

            }
        }
    }

    public void winSet() {
        Iterator<NetGameButton> netGameButtonIterator = arrayListOfBlock.iterator();
        while (netGameButtonIterator.hasNext()) {
            NetGameButton curNetGameButton = (NetGameButton) netGameButtonIterator.next();
            curNetGameButton.setStyle("-fx-border-style: solid inside;-fx-border-width:3;-fx-border-color: rgba(226,149,192,0);");
        }
        turnNameLabel.setStyle("-fx-background-image: url('/tactictoe/client_side/style/null.png');");

    }

}
