package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteController {

    private Socket clientSocket;
    
    //Variaveis de Comunicação
    private PrintWriter output;
    private BufferedReader input;
    private String response;

    public boolean conectar(String ip, int porta) {
        System.out.println("[CLIENTE] Conectando ao servidor...\n");

        try {
            //CONECTA CLIENTE AO SERVIDOR - setando as streams
            clientSocket = new Socket(ip, porta);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream());
            
        } catch (IOException ex) {
            System.err.println("[CLIENTE] Falha ao conectar com o servidor\n");
            return false;
        }

        return true;

    }
    
    public String enviarMensagem(String msg){
        output.println(msg);
        output.flush();
        
        return escutar();

    }

    public String escutar(){
        
        try {
            response = input.readLine();
        } catch (IOException ex) {
           System.err.println("[CLIENTE] Falha ao enviar ou receber resposta do servidor");
        }
        
        System.out.println("[CLIENTE] Recebido do Servidor: " + response+"\n");
        return response;
    }

    public boolean desconectar() {
        try {
            output.close();
            input.close();
            clientSocket.close();

        } catch (IOException e) {
            System.out.println("[CLIENTE] Nao foi possivel desconectar\n");
            return false;
        }
        System.out.println("[CLIENTE] Cliente desconectado\n");
        return true;

    }
}
