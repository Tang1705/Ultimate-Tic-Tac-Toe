/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.component;

import javafx.scene.control.Button;

public class MyButton extends Button {
    private boolean white = true;
    private int num = 0;

    public MyButton(int num) {
        this.num = num;
    }

    public boolean getWhite() {
        return this.white;
    }

    public void setWhite() {
        this.white = false;
    }

}