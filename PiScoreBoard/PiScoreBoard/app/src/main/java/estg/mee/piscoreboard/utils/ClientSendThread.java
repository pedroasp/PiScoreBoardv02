package estg.mee.piscoreboard.utils;

import android.app.Fragment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Fernando Henriques on 26/04/2015.
 */
public class ClientSendThread implements Runnable {
    private String message;
    private final String IP = "192.168.132.76";
    private final int PORT = 9999;

    public ClientSendThread(String message) {
        this.message=message;
    }

    public void run() {
        try {
            Socket socket = new Socket(IP, PORT);   // Abertura Socket
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                        .getOutputStream())), true);        // Construção Stream Saída
                // WHERE YOU ISSUE THE COMMANDS
                out.println(message);                       // Print mensagem no socket
            } catch (Exception e) {
                Log.e("ClientActivity", "C: Error", e);
            }
            //socket.close();                                 // Fecho do Socket...O socket é aberto e fechado todas as vezes que se efetua escrita ou leitura
        } catch (Exception e) {
            Log.e("ClientActivity", "C: Error", e);
        }
    }



}
