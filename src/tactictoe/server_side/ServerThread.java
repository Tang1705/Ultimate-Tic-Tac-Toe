/*
 * Ultimate Tac-Tic-Toe by Tang
 * 2018.12.25
 * */

package tactictoe.server_side;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private static Socket socket;
    private  BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String message = "";
    private ServerOperation serverOperation;
    private ArrayList<ServerThread> arrayList = TestServer.getArrayList();

    public ServerThread(Socket socketH) {
        try {
            socket = socketH;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socketH.getOutputStream(), true);
            serverOperation = new ServerOperation();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                message = bufferedReader.readLine();
            } catch (IOException event) {

            }

            if (message != null && message.length() != 0) {

                String result = serverOperation.operation(message);
                printWriter.println(result);
            }
        }
    }

    public BufferedReader getBufferedReader(){
        return bufferedReader;
    }


}
