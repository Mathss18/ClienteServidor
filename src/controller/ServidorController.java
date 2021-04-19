package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServidorController extends Thread {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static ArrayList<Socket> clientes;

    //Variaveis de Comunicação
    private PrintWriter output;
    private BufferedReader input;
    private String request;

    public ServidorController(Socket con) {
        this.clientSocket = con;

        try {
            input = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServidorController() {

    }

    public void run() {
        try {
            output = new PrintWriter(clientSocket.getOutputStream());

            //Adiciona no array de clientes
            clientes.add(clientSocket);
            System.out.println("[SERVER] Lista de Clientes: "+clientes+"\n");

            //O que o Servidor recebe do cliente
            while ((request = input.readLine()) != null) {
                System.out.println("[SERVER] Recebido do Cliente: " + request + "\n");

                //O que o Servidor responde para o  cliente
                output.println(tratarDados(request));
                output.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void abrirServidor(int porta, JTable tabelaIp) {
        
        try {
            //Cria os objetos necessário para instânciar o servidor
            serverSocket = new ServerSocket(porta);
            clientes = new ArrayList<>();
           
            while (true) {
                System.out.println("[SERVER] Aguardando conexão...\n");
                Socket con = serverSocket.accept(); //Linha que trava o servidor
                System.out.println("[SERVER] Cliente conectado\n");
                Thread t = new ServidorController(con);
                atualizarLista(con,tabelaIp);
                t.start();
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String tratarDados(String dados) {
        String cod = null;
        
        JSONObject jsonObj = new JSONObject(dados);

        //Armazena o codigo da requisicao
        cod = jsonObj.getString("cod");

        switch (cod) {
            case "1":
                return confirmarLogin(jsonObj);
            case "3":
                break;
            case "5":
                logout(jsonObj);
                break;
            case "6":
                return probabilidadeCovid(jsonObj);

        }
        return null;
    }

    private String confirmarLogin(JSONObject dados) {
        JSONObject request = dados;
        JSONObject response = new JSONObject();

        response.put("cod", "11");
        response.put("tipo", "usuario");
        response.put("success", "true");

        return response.toString();

    }

    private String probabilidadeCovid(JSONObject dados) {
        JSONObject request = dados;
        JSONObject response = new JSONObject();
        int soma = 0;

        JSONArray arr = request.getJSONArray("respostas");

        for (int i = 0; i < arr.length(); i++) {
            if(Integer.parseInt(arr.getJSONObject(i).getString("id")) != 5)
                soma += Integer.parseInt(arr.getJSONObject(i).getString("resposta"));
        }

        response.put("cod", "8");
        if(soma > 3){
            response.put("covid", "true");
        }
        else{
            response.put("covid", "false");
        }

        return response.toString();
    }

    private void logout(JSONObject dados) {
        JSONObject request = dados;
        try {
            clientSocket.close();
        } catch (IOException ex) {
            System.err.println("[SERVER] Impossivel Desconectar Cliente\n");
        }
        clientes.remove(clientSocket);
        System.out.println("[SERVER] Lista de Clientes: "+clientes+"\n");
        System.out.println("[SERVER] Cliente Desconectado\n");
    }

    private void atualizarLista(Socket con,JTable tabelaIp) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaIp.getModel();
        Object[] dados  = {
            con.getInetAddress(),
            con.getPort()
        };
        modelo.addRow(dados);
    }
}
