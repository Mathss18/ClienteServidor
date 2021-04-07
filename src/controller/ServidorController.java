/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author Maths
 */
public class ServidorController extends Thread {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private InputStreamReader inPut;
    private InputStream in;
    private BufferedReader bf;
    private PrintWriter outPut;
    private String str;
    private static ArrayList<BufferedWriter>clientes;
    
    public ServidorController(Socket con) {
        this.clientSocket = con;
        try {
            in  = con.getInputStream();
            inPut = new InputStreamReader(in);
            bf = new BufferedReader(inPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ServidorController() {
        //start();
    }

    public void run() {
        try{

            String msg;
            //OutputStream ou =  this.clientSocket.getOutputStream();
            //Writer ouw = new OutputStreamWriter(ou);
            //BufferedWriter bfw = new BufferedWriter(ouw);
            outPut = new PrintWriter(clientSocket.getOutputStream(), true);
            
            
            //clientes.add(bfw);
            msg = bf.readLine();

            while ((str = bf.readLine()) != null) {
                System.out.println("Cliente Enviou: " + str);
                outPut.println(str.toUpperCase());
            }

        }catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public void abrirServidor(int porta){
        // CRIAR O SERVER 
        /*
        try {
            serverSocket = new ServerSocket(porta);
        } catch (IOException e) {
            System.err.println("Nao pode ouvir na porta " + porta);
            System.exit(1);
        }
        start();
*/
        try{
            //Cria os objetos necessário para instânciar o servidor

            serverSocket = new ServerSocket(porta);
            clientes = new ArrayList<BufferedWriter>();
  

            while(true){
                System.out.println("Aguardando conexão...");
                Socket con = serverSocket.accept();
                System.out.println("Cliente conectado...");
                Thread t = new ServidorController(con);
                t.start();
            }

        }catch (Exception e) {

            e.printStackTrace();
        }
        
    }

    public void carregar() {


        clientSocket = null;
        System.out.println("Esperando conexao.....");

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Falha ao aceitar conexao.");
            System.exit(1);
        }

        System.out.println("Conexao bem sucedida");

        
        try {
            inPut = new InputStreamReader(clientSocket.getInputStream());
            bf = new BufferedReader(inPut);
            outPut = new PrintWriter(clientSocket.getOutputStream(), true);

            while ((str = bf.readLine()) != null) {
                System.out.println("Cliente Enviou: " + str);
                outPut.println(str.toUpperCase());
            }
            System.out.println("Cliente: " + str);
            System.out.println("Conexao encerrada pelo cliente\n");
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Conexao encerrada pelo cliente\n");
            try {
                serverSocket.close();
            } catch (IOException e1) {
                System.out.println("Nao foi poss�vel reiniciar o servidor, abra novamente\n");
                System.exit(1);
            }
        }

    }

}
