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
import java.net.Socket;

/**
 *
 * @author Maths
 */
public class ClienteController {
    private Socket clientSocket;
    private PrintWriter pr;
    private InputStreamReader inPut;
    private BufferedReader bf;
    
    public boolean conectar(String ip, String porta) throws IOException{
        System.out.println(ip+' '+porta);
        clientSocket = new Socket("localhost", 4949);
        
        return true;
        
    }
    
    public String enviarMensagem(String msg) throws IOException{
        pr = new PrintWriter(clientSocket.getOutputStream(), true);
        inPut = new InputStreamReader(clientSocket.getInputStream());
        bf = new BufferedReader(inPut);
        //String resp = bf.readLine();
        pr.println(msg);
        pr.flush();
        //System.out.println("Servidor Respondeu: "+resp);
        
        //reqResp[0] = msg; //Request
        //reqResp[1] = bf.readLine(); //Response
        
        return bf.readLine();
    }
    
    public void desconectar() throws IOException{
        pr.close();
	bf.close();
	clientSocket.close();
    }
}
