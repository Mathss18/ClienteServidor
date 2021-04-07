package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServidorController extends Thread {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static ArrayList<PrintWriter> clientes;
    
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
            clientes.add(output);
            System.out.println(clientes);
            
            //O que o Servidor recebe do cliente
            while ((request = input.readLine()) != null) {
                System.out.println("Recebido do Cliente: " + request);
                //O que o Servidor responde para o  cliente
                output.println(request.toUpperCase());
                output.flush();
            }
            

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void abrirServidor(int porta) {

        try {
            //Cria os objetos necessário para instânciar o servidor
            serverSocket = new ServerSocket(porta);
            clientes = new ArrayList<PrintWriter>();
            

            while (true) {
                System.out.println("Aguardando conexão...");
                Socket con = serverSocket.accept();
                System.out.println("Cliente conectado...");
                Thread t = new ServidorController(con);
                t.start();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
