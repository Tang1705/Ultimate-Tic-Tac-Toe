/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Rank {
    public String[] name = new String[4];
    String[] score = new String[4];
    private File file;
    private FileReader fr;
    private FileWriter fw;
    private BufferedReader cout;
    private BufferedWriter cin;
    public int num;

    public void readRank() {
        file = new File("rank.txt");
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        try {
            fr = new FileReader(file);
            cout = new BufferedReader(fr);
            int num = 1;
            int row = 1;
            while (true) {
                String str = cout.readLine();
                if (str == null) {
                    break;
                }
                if (row % 2 == 0) {
                    score[num] = str;
                   // System.out.println(score[num]);
                    num++;
                    row++;
                } else {
                    name[num] = str;
                  //  System.out.println(name[num]);
                    row++;
                }

            }

            cout.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public boolean isInRank(int scoreNow) {
        for (num = 1; num < 4; num++) {
            String score0 = score[num];
            if (scoreNow >= Integer.parseInt(score0)) {
                if (num == 1) {
                    score[num + 2] = score[num + 1];
                    score[num + 1] = score0;
                    score[num] = String.valueOf(scoreNow);
                    return true;
                } else if (num == 2) {
                    score[num + 1] = score[num];
                    score[num] = String.valueOf(scoreNow);
                    return true;
                } else if (num == 3) {
                    score[num] = String.valueOf(scoreNow);
                    return true;
                }

            }
        }
        return false;
    }

    public void writeInRank(int classes) {
        file = new File("rank.txt");
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        try {
            fw = new FileWriter(file);
            cin = new BufferedWriter(fw);
            for (int i = 1; i < 4; i++) {
                cin.write(name[i]);
                cin.newLine();
                cin.write(score[i]);
                cin.newLine();
            }
            cin.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getName() {
        return name;
    }

    public String[] getScore() {
        return score;
    }

}
