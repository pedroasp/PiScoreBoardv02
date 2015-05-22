package estg.mee.piscoreboard.utils;

import java.io.PrintWriter;

import android.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;



/**
 * Created by Fernando Henriques on 26/04/2015.
 */
public class ClientSendThread implements Runnable {
    private String message;
    private final String IP = "192.168.132.45";
    private final int PORT = 9999;
    private PrintWriter out;
    private Socket socket;

    Context c;

    public ClientSendThread(String message,Context c) {
        this.message=message; this.c = c;
    }



    public void run() {

        try {
            socket = new Socket(IP, PORT);   // Abertura Socket
//            Boolean a = socket.isConnected();
//            if (socket == null){ Toast.makeText(c,"Erro de ligação", Toast.LENGTH_SHORT).show();}
            try {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                        .getOutputStream())), true);        // Construção Stream Saída
                // WHERE YOU ISSUE THE COMMANDS
                                   // Print mensagem no socket

                out.println(message + "\r");

            } catch (Exception e) {
                Log.e("ClientActivity", "C: Error", e);
                Toast.makeText(c,"Erro de ligação", Toast.LENGTH_SHORT).show();
            }finally {
                out.close();
            }
            //socket.close();                                 // Fecho do Socket...O socket é aberto e fechado todas as vezes que se efetua escrita ou leitura
        } catch (Exception e) {
            Log.e("ClientActivity", "C: Error", e);
            Toast.makeText(c,"Erro de ligação", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
