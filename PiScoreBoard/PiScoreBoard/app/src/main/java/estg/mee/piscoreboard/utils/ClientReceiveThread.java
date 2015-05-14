package estg.mee.piscoreboard.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Fernando Henriques on 26/04/2015.
 */
public class ClientReceiveThread implements Runnable {
    private String message = null;
    private final String IP = "192.168.132.76";
    private final int PORT = 9999;

    public void run() {

            try {
                Socket socket = new Socket(IP, PORT);  //abertura socket

                try {
                    BufferedReader inData = new BufferedReader(new InputStreamReader(socket.getInputStream()));  //abertura buffer de entrada do socket

                    // WHERE YOU ISSUE THE COMMANDS

                    this.message = inData.readLine();   //le o buffer ate ao próximo /n

                } catch (Exception e) {
                    Log.e("ClientActivity", "S: Error", e);
                }
                socket.close();                         //Fecha o socket
                Log.d("ClientActivity", "C: Closed.");
            } catch (Exception e) {
                Log.e("ClientActivity", "C: Error", e);
            }

    }

    public String getMessage(){
        while(this.message==null); //espera que haja dados na string de receção antes de a devolver
        //TO DO .. este bocado prende código e é possivel que o programa encrave aqui por alguma falha
        // tem de se encontrar alguma forma melhor de fazer isto... ou adicionar um timeout...
        // outra forma é usar um handles da thread e voltar a coloca-la activa ate que os dados sejam válidos
            return this.message;

    }

}




//        https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html