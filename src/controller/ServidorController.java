/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author Maths
 */
public class ServidorController extends Thread {
    
    public ServidorController(){
        start();
    }
    
    public void run(){
        try {
            carregar(4949);
        } catch (IOException ex) {
            Logger.getLogger(ServidorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregar(int porta) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(porta);
        } catch (IOException e) {
            System.err.println("Nao pode ouvir na porta " + porta);
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println("Esperando conexao.....");

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Falha ao aceitar conexao.");
            System.exit(1);
        }

        System.out.println("Conexao bem sucedida");

        InputStreamReader inPut = new InputStreamReader(clientSocket.getInputStream());
        BufferedReader bf = new BufferedReader(inPut);
        PrintWriter outPut = new PrintWriter(clientSocket.getOutputStream(), true);
        String str;
        
        while((str = bf.readLine()) != "exit"){
            System.out.println("Cliente Enviou: "+str);
            outPut.println(str.toUpperCase());
        }
        
        System.out.println("Cliente: "+str);
    }

}

