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
import java.net.UnknownHostException;

/**
 *
 * @author Maths
 */
public class ClienteController {
    private Socket clientSocket;
    private PrintWriter pr;
    private InputStreamReader inPut;
    private BufferedReader bf;
    
    public boolean conectar(String ip, int porta){
        System.out.println(ip+' '+porta);
        
        try {
			clientSocket = new Socket(ip, porta);
			pr = new PrintWriter(clientSocket.getOutputStream(), true);
	        inPut = new InputStreamReader(clientSocket.getInputStream());
	        bf = new BufferedReader(inPut);
		} catch (UnknownHostException e) {
			System.out.println("Host desconhecido\n");
			return false;
		} catch (IOException e) {
			System.out.println("Nao possivel se conectar ao servidor\n");
			return false;
		}
        
        return true;
        
    }
    
    public String enviarMensagem(String msg) throws IOException{
        
        //String resp = bf.readLine();
        pr.println(msg);
        pr.flush();
        //System.out.println("Servidor Respondeu: "+resp);
        
        //reqResp[0] = msg; //Request
        //reqResp[1] = bf.readLine(); //Response
        
        return bf.readLine();
    }
    
    public boolean desconectar(){
		try {
			pr.close();
			bf.close();
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("N�o foi poss�vel desconectar\n");
			return false;
		}
		System.out.println("Cliente desconectado\n");
		return true;
		
    }
}
