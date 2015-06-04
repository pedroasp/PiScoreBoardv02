package estg.mee.piscoreboard.utils;

import java.io.PrintWriter;

import android.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.os.MessageQueue;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import estg.mee.piscoreboard.controller.MainActivity;


/**
 * Created by Fernando Henriques on 26/04/2015.
 */
public class ClientSendThread implements Runnable {
    private String message;
    //private final String IP = "192.168.132.45";
    private final String IP = "10.5.5.10";
    private final int PORT = 9999;
    private PrintWriter out;
    private Socket socket;

    Context context;

    public ClientSendThread(String message,Context context) {
        this.message=message; this.context = context;
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

            }finally {
                out.close();
            }

            //socket.close();                                 // Fecho do Socket...O socket é aberto e fechado todas as vezes que se efetua escrita ou leitura
        } catch (Exception e) {
            Log.e("ClientActivity", "C: Error", e);
            showToastInThread(context,"Erro de ligação");
            Intent intent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
            context.startActivity(intent);
        } finally {
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void showToastInThread(final Context context,final String str){
        Looper.prepare();
        MessageQueue queue = Looper.myQueue();
        queue.addIdleHandler(new MessageQueue.IdleHandler() {
            int mReqCount = 0;

            @Override
            public boolean queueIdle() {
                if (++mReqCount == 2) {
                    Looper.myLooper().quit();
                    return false;
                } else
                    return true;
            }
        });
        Toast.makeText(context, str,Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

}
