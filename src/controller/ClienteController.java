package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

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
    
    public void enviarSemEscuta(String msg){
        System.out.println("[CLIENTE] Enviado para o Servidor: " +msg+"\n");
        output.println(msg);
        output.flush();   
    }
    
    
    public String enviarMensagem(String msg){
        System.out.println("[CLIENTE] Enviado para o Servidor: " +msg+"\n");
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
        
        System.out.println("[CLIENTE] Recebido do Servidor: " +response+"\n");
        return response;
    }
    
    public void logout() {
        
        JSONObject request = new JSONObject();
        request.put("cod", "5");
        JSONObject response = new JSONObject(this.enviarMensagem(request.toString()));
        if("true".equals(response.getString("success"))){
            try {
            output.close();
            input.close();
            clientSocket.close();

        } catch (IOException e) {
            System.out.println("[CLIENTE] Nao foi possivel desconectar\n");
        }
        }
        
        System.out.println("[CLIENTE] Cliente desconectado\n");

    }
}
