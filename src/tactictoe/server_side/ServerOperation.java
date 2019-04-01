/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.server_side;

import tactictoe.server_side.Database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class ServerOperation {
    private static int time = 1;
    private static ArrayList<String[]> arrayListOfRoom = RoomManager.getRoomArrayList();
    private static ArrayList<String[]> arrayListOfNetGameButton = new ArrayList<>();
    private static int[] judge = new int[9];
    private static String send = "wait~";
    private static int[] winArea = new int[9];
    private static int current = -1;
    private static String name_first = "";
    private static String name_second = "";

    public ServerOperation() {
        for (int i = 0; i < 9; i++) {
            judge[i] = 0;
        }

        for (int i = 0; i < 9; i++) {
            String[] buttons = new String[9];
            for (int j = 0; j < 9; j++) {
                buttons[j] = "-1";
            }
            arrayListOfNetGameButton.add(buttons);
        }

        for (int i = 0; i < 9; i++) {
            winArea[i] = -1;
        }

    }

    public String operation(String info) {
        StringTokenizer stringTokenizer = new StringTokenizer(info, "-", false);
        switch (stringTokenizer.nextToken()) {
            case "1": {
                if (arrayListOfRoom.isEmpty()) {
                    String[] numOfRoom = new String[2];
                    numOfRoom[0] = stringTokenizer.nextToken();
                    name_first = stringTokenizer.nextToken();
                    numOfRoom[1] = "1";
                    arrayListOfRoom.add(numOfRoom);
                    return "0";
                } else {
                    // boolean thereBe = false;
                    Iterator anIterator = arrayListOfRoom.iterator();
                    String numOfRoom = stringTokenizer.nextToken();
                    while (anIterator.hasNext()) {
                        String[] curStringArray = (String[]) anIterator.next();
                        if (curStringArray[0].equals(numOfRoom)
                                || curStringArray[0] == numOfRoom) {
                            if (curStringArray[1].equals("2") || curStringArray[1] == "2") {
                                return "2";
                            } else {
                                curStringArray[1] = "2";
                                name_second = stringTokenizer.nextToken();
                                return "1";
                            }
                        }
                    }

                    return "-1";
                }

            }

            case "2": {
                int side = Integer.parseInt(stringTokenizer.nextToken());
                int area = Integer.parseInt(stringTokenizer.nextToken());
                int button = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                boolean permit = false;
                boolean turn = false;
                boolean blank = true;
                boolean same = false;

                if (time % 2 == 0) {
                    if (side == 2) {
                        permit = true;
                        turn = true;
                    }
                } else {
                    if (side == 1) {
                        permit = true;
                        turn = true;
                    }
                }

                if (time == 1 && side == 1) {
                    same = true;
                } else {
                    if (area != current) {
                        permit = false;
                        same = false;
                    } else {
                        same = true;
                    }
                }


                if (time != 1) {
                    String[] curString = (String[]) arrayListOfNetGameButton.get(current);

                    if (curString[0].equals(curString[1]) && curString[0].equals(curString[2]) && !curString[0].equals("-1")) {
                        winArea[current] = Integer.parseInt(curString[0]);
                        permit = true;
                        blank = false;
                    } else if (curString[3].equals(curString[4]) && curString[3].equals(curString[5]) && !curString[3].equals("-1")) {
                        winArea[current] = Integer.parseInt(curString[3]);
                        permit = true;
                        blank = false;
                    } else if (curString[6].equals(curString[7]) && curString[6].equals(curString[8]) && !curString[6].equals("-1")) {
                        winArea[current] = Integer.parseInt(curString[6]);
                        permit = true;
                        blank = false;
                    } else if (curString[0].equals(curString[3]) && curString[0].equals(curString[6]) && !curString[0].equals("-1")) {
                        winArea[current] = Integer.parseInt(curString[0]);
                        permit = true;
                        blank = false;
                    } else if (curString[1].equals(curString[4]) && curString[1].equals(curString[7]) && !curString[1].equals("-1")) {
                        winArea[current] = Integer.parseInt(curString[1]);
                        permit = true;
                        blank = false;
                    } else if (curString[2].equals(curString[5]) && curString[2].equals(curString[8]) && !curString[2].equals("-1")) {
                        winArea[current] = Integer.parseInt(curString[2]);
                        permit = true;
                        blank = false;
                    } else if (curString[0].equals(curString[4]) && curString[0].equals(curString[8]) && !curString[0].equals("-1")) {
                        winArea[current] = Integer.parseInt(curString[0]);
                        permit = true;
                        blank = false;
                    } else if (curString[2].equals(curString[4]) && curString[2].equals(curString[8]) && !curString[2].equals("-1")) {
                        winArea[current] = Integer.parseInt(curString[2]);
                        permit = true;
                        blank = false;
                    } else {
                        for (String a : curString) {
                            if (!(a.equals("-1") || a == "-1")) {
                                blank = false;
                                same = false;
                            } else {
                                blank = true;
                                same = true;
                                break;
                            }
                        }

                    }

                }

                boolean win = false;
                int guy = -1;
                if (winArea[0] == winArea[1] && winArea[0] == winArea[2] && winArea[0] != -1) {
                    win = true;
                    guy = winArea[0];
                } else if (winArea[3] == winArea[4] && winArea[3] == winArea[5] && winArea[3] != -1) {
                    win = true;
                    guy = winArea[3];
                } else if (winArea[6] == winArea[7] && winArea[6] == winArea[8] && winArea[6] != -1) {
                    win = true;
                    guy = winArea[6];
                } else if (winArea[0] == winArea[3] && winArea[0] == winArea[6] && winArea[0] != -1) {
                    win = true;
                    guy = winArea[0];
                } else if (winArea[1] == winArea[4] && winArea[1] == winArea[7] && winArea[1] != -1) {
                    win = true;
                    guy = winArea[1];
                } else if (winArea[2] == winArea[5] && winArea[2] == winArea[8] && winArea[2] != -1) {
                    win = true;
                    guy = winArea[2];
                } else if (winArea[0] == winArea[4] && winArea[0] == winArea[8] && winArea[0] != -1) {
                    win = true;
                    guy = winArea[0];
                } else if (winArea[2] == winArea[4] && winArea[2] == winArea[6] && winArea[2] != -1) {

                    win = true;
                    guy = winArea[2];
                }

                if (win) {
                    Database database = new Database();
                    if (guy == 1) {
                        database.operate("2~" + name_first);
                    } else {
                        database.operate("2~" + name_second);
                    }
                }

                if (permit && turn) {

                    if (blank == true && same == true) {
                        if (judge[area] == 0) {
                            String[] curStrings = (String[]) arrayListOfNetGameButton.get(area);
                            if (curStrings[button].equals("-1") || curStrings[button] == "-1") {
                                curStrings[button] = side + "";
                                send = area + "~" + button + "~" + side;
                            }
                        }
                        time++;
                        current = button;

                    } else if (blank == false && same == false) {
                        if (judge[area] == 0) {
                            String[] curStrings = (String[]) arrayListOfNetGameButton.get(area);
                            if (curStrings[button].equals("-1") || curStrings[button] == "-1") {
                                curStrings[button] = side + "";
                                send = area + "~" + button + "~" + side;
                            }
                        }
                        time++;
                        current = button;
                    }
                }
                return send;

            }


            case "3": {
                String num = stringTokenizer.nextToken();
                Iterator anIterator = arrayListOfRoom.iterator();
                while (anIterator.hasNext()) {
                    String[] curStringArray = (String[]) anIterator.next();
                    if (curStringArray[0].equals(num)
                            || curStringArray[0] == num) {
                        if (curStringArray[1].equals("2") || curStringArray[1] == "2") {
                            return "2";
                        } else {
                            return "1";
                        }
                    }
                }


            }

            case "4": {
                if (send.equals("wait~") && (name_first != null && name_first.length() != 0)
                        && (name_second != null) && name_second.length() != 0) {
                    send += name_first + "~";
                    send += name_second;
                }
                return send;
            }

            case "5": {
                Database database = new Database();
                String cur = stringTokenizer.nextToken();
                String result = database.operate(cur);
                return result;
            }

            case "": {
                break;
            }

        }

        return "wait";
    }

    public static int getTime() {
        return time;
    }

}
