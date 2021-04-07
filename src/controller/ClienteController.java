package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteController {

    private Socket clientSocket;
    
    //Variaveis de Comunicação
    private PrintWriter output;
    private BufferedReader input;
    private String response;

    public boolean conectar(String ip, int porta) {
        System.out.println(ip + ' ' + porta);

        try {
            //CONECTA CLIENTE AO SERVIDOR - setando as streams
            clientSocket = new Socket(ip, porta);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream());
            
        } catch (IOException ex) {
            System.err.println("Falhou Ao conectar o cliente");
            return false;
        }

        return true;

    }

    public String enviarMensagem(String msg) throws IOException {
        output.println(msg);
        output.flush();
        
        return escutar();
    }

    public String escutar() throws IOException {
        response = input.readLine();
        System.out.println("Recebido do Servidor: " + response);
        return response;
        
    }

    public boolean desconectar() {
        try {
            output.close();
            input.close();
            clientSocket.close();

        } catch (IOException e) {
            System.out.println("Nao foi possivel desconectar\n");
            return false;
        }
        System.out.println("Cliente desconectado\n");
        return true;

    }
}
