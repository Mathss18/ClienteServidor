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
<<<<<<< HEAD
        pr = new PrintWriter(clientSocket.getOutputStream(), true);
        inPut = new InputStreamReader(clientSocket.getInputStream());
        bf = new BufferedReader(inPut);

=======
        
        //String resp = bf.readLine();
>>>>>>> 6d833c2ae6a5f8515f05b8b0ed5b7f363570980d
        pr.println(msg);
        pr.flush();
        
        
        return bf.readLine();
    }
    
<<<<<<< HEAD
    public void desconectar() throws IOException{
        pr.close();
        bf.close();
        clientSocket.close();
=======
    public boolean desconectar(){
		try {
			pr.close();
			bf.close();
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Não foi possível desconectar\n");
			return false;
		}
		System.out.println("Cliente desconectado\n");
		return true;
		
>>>>>>> 6d833c2ae6a5f8515f05b8b0ed5b7f363570980d
    }
}
