/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.client_side.dao;

public interface Operation {
    String paint(String code);
    void setSymbol(String code);
    void rule();
}
