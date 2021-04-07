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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maths
 */
public class ClienteController {

    private Socket clientSocket;
    private BufferedWriter bw;
    private OutputStream ou;
    private OutputStreamWriter ouw;

    public boolean conectar(String ip, int porta) {
        System.out.println(ip + ' ' + porta);

        try {
            //CONECTA CLIENTE AO SERVIDOR
            clientSocket = new Socket(ip, porta);
            ou = clientSocket.getOutputStream();
            ouw = new OutputStreamWriter(ou);
            bw = new BufferedWriter(ouw);
            bw.write("Cliente Conectado\r\n");
            bw.flush();

        } catch (IOException ex) {
            System.err.println("Falhou Ao conectar o cliente");
            return false;
        }

        return true;

    }

    public String escutar() {
        //ESCUTA A RESPOSTA DO SERVIDOR
        InputStream in;
        try {
            in = clientSocket.getInputStream();
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader bfr = new BufferedReader(inr);
            String msg = "";

            while (!"Sair".equalsIgnoreCase(msg)) {
                if (bfr.ready()) {
                    msg = bfr.readLine();
                    System.out.println(msg);
                    if (msg.equals("Sair")) {
                        System.out.println("Servidor Caiu");
                        return "Servidor Caiu";
                    } else {
                        System.out.println(msg.toUpperCase());
                        return msg.toUpperCase();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Erro ao escutar Clientec");
        }

        return null;
    }

    public String enviarMensagem(String msg) throws IOException {

        if (msg.equals("Sair")) {
            bw.write("Desconectado \r\n");
            bw.flush();
            return "Desconectado";

        } else {
            bw.write(msg + "\r\n");
            bw.flush();
            return msg;

        }

    }

    public boolean desconectar() {
        try {
            //pr.close();
            bw.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("N�o foi poss�vel desconectar\n");
            return false;
        }
        System.out.println("Cliente desconectado\n");
        return true;

    }
}
