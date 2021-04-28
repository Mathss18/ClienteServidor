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
import model.Chat;
import model.Cliente;
import model.Hospital;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServidorController extends Thread {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Socket> socks;
    private static ArrayList<Chat> chats;
    private Cliente c = new Cliente();
    
    private static ArrayList<Hospital> hospitais = new ArrayList<>();
    private Hospital h1 = new Hospital("nome","rua numero bairro",11);
    private Hospital h2 = new Hospital("nome2","rua numero bairro2",12);
   
    //Variaveis de Comunicação
    private PrintWriter output;
    private BufferedReader input;
    private String request;

    public ServidorController(Socket con) {
        this.clientSocket = con;

        try {
            input = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            System.out.println("[SERVER] Erro ao abrir conexão");
        }
    }

    public ServidorController() {

    }

    public void run() {
        try {
            output = new PrintWriter(clientSocket.getOutputStream());
            
            //O que o Servidor recebe do cliente
            while ((request = input.readLine()) != null) {
                System.out.println("[SERVER] Recebido do Cliente: " + request + "\n");

                //O que o Servidor responde para o  cliente
                output.println(tratarDados(request));
                output.flush();
            }

        } catch (Exception e) {
            //Logger.getLogger(ServidorController.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.println("[SERVER] Thread do cliente Fechada.");
            clientes.remove(c);
        }
    }

    public void abrirServidor(int porta, JTable tabelaIp) {
        hospitais.add(h1);
        hospitais.add(h2);
        try {
            //Cria os objetos necessário para instânciar o servidor
            serverSocket = new ServerSocket(porta);
            clientes = new ArrayList<>();
            socks = new ArrayList<>();
           
            while (true) {
                System.out.println("[SERVER] Aguardando conexão...\n");
                Socket con = serverSocket.accept(); //Linha que trava o servidor
                System.out.println("[SERVER] Cliente conectado\n");
                Thread t = new ServidorController(con);
                atualizarLista(con,tabelaIp);
                t.start();
                
            }

        } catch (Exception e) {
            System.err.println("[SERVER] Porta já utilizada, tente outra...");
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
            case "91":
                return enviarHospitais();
            case "92":
                return escolhaChat(jsonObj);
            default:
                break;

        }
        return null;
    }
    
    private String enviarHospitais(){
        JSONObject response = new JSONObject();
        response.put("cod", "10");
        
        for (int i = 0; i < hospitais.size(); i++) {
            JSONObject hospital = new JSONObject();
            hospital.put("nome", hospitais.get(i).getNome());
            hospital.put("endereco", hospitais.get(i).getEndereco());
            hospital.put("vagas", hospitais.get(i).getVagas());
            response.append("hospitais",hospital);
        }

        System.out.println("[SERVER] Enviado para o Cliente: " + response + "\n");
        
        return response.toString();
    }
    
    private String escolhaChat(JSONObject dados) {
        String respostaSaude;
        int aux = 0;
        
        JSONObject response = new JSONObject();
        response.put("cod", "72");
        response.put("sucesso", "false");
        response.put("usuario", "");
        
        respostaSaude = response.toString();
        
        for (int i = 0; i < clientes.size(); i++) {
            if("saude".equals(clientes.get(i).getTipo()) && aux == 0){
                respostaSaude = iniciarChatSaude(dados.getString("usuario"), clientes.get(i).getNome());
                System.out.println("[SERVER] Resposta iniciar chat: " + respostaSaude + "\n");
                aux++;
            }
        }
        if(clientes.size()<=0){
            System.out.println("[SERVER] Erro na lista de clientes");
        }  
        System.out.println("[SERVER] Enviado para o Cliente: " + respostaSaude + "\n");
        return respostaSaude;

    }
    private String iniciarChatSaude(String paciente, String saude) {
        PrintWriter outputTemp;
        BufferedReader inputTemp;
        int aux1 = 0, aux2 = 0;
        
        JSONObject response = new JSONObject();
        response.put("cod", "72");
        response.put("usuario", saude);
        response.put("sucesso", "false");
        
        JSONObject requestSaude = new JSONObject();
        requestSaude.put("cod", "70");
        requestSaude.put("usuario", paciente);
        
        for (int i = 0; i < clientes.size(); i++) {
            if(saude.equals(clientes.get(i).getNome()) && aux1 == 0){
                for (int j = 0; j < socks.size(); j++) {
                    if(clientes.get(i).getPorta() ==  socks.get(j).getPort() && aux2 == 0){
                        try {
                            outputTemp = new PrintWriter(socks.get(j).getOutputStream());
                            inputTemp = new BufferedReader(new InputStreamReader(socks.get(j).getInputStream()));
                            
                            outputTemp.println(requestSaude.toString());
                            outputTemp.flush();
                            
                            String reponseSaude = inputTemp.readLine();
                            JSONObject jsonSaude = new JSONObject(reponseSaude);
                            
                            if("71".equals(jsonSaude.getString("cod"))){
                                response.put("sucesso", jsonSaude.getString("sucesso"));
                            }else{
                                System.out.println("[SERVER] Erro ao receber confirmação saude=>servidor codigo da mensagem inválido");
                                response.put("sucesso", "false");
                            }
                            outputTemp.close();
                            inputTemp.close();
                            
                        } catch (IOException ex) {
                            System.out.println("[SERVER] Erro ao enviar requisição de chat para saúde");
                            response.put("sucesso", "false");
                        }
                        aux2++;
                    }
                }
                aux1++;
            }
        }
        if(clientes.size()<=0){
            System.out.println("[SERVER] Erro na lista de clientes");
            response.put("sucesso", "false");
        }
        
        System.out.println("[SERVER] Resposta saude: " + response + "\n");
        return response.toString();
    }
    
    

    private String confirmarLogin(JSONObject dados) {
        JSONObject response = new JSONObject();
        response.put("cod", "11");
        response.put("success", "true");
        
        c.setNome(dados.getString("usuario"));
        c.setIpcliente(clientSocket.getInetAddress().toString());
        c.setPorta(clientSocket.getPort());
        
        if(null == dados.getString("usuario")){
            response.put("tipo", "");
            response.put("success", "false");
            c.setTipo("admin");
        }else switch (dados.getString("usuario")) {
            case "usuario":
                response.put("tipo", "usuario");
                c.setTipo("usuario");
                break;
            case "saude":
                response.put("tipo", "saude");
                c.setTipo("saude");
                break;
            default:
                response.put("tipo", "admin");
                c.setTipo("admin");
                break;
        }
        
        clientes.add(c);
        socks.add(clientSocket);
        System.out.println("[SERVER] Lista de Clientes: "+clientes.toString()+"\n");
        System.out.println("[SERVER] Enviado para o Cliente: " + response + "\n");
        
        return response.toString();

    }

    private String probabilidadeCovid(JSONObject dados) {
        JSONObject request = dados;
        JSONObject response = new JSONObject();
        int soma = 0;

        JSONArray arr = request.getJSONArray("respostas");

        for (int i = 0; i < arr.length(); i++) {
            if(Integer.parseInt(arr.getJSONObject(i).getString("id")) != 4)
                soma += Integer.parseInt(arr.getJSONObject(i).getString("resposta"));
        }

        response.put("cod", "8");
        if(soma > 3){
            response.put("covid", "true");
        }
        else{
            response.put("covid", "false");
        }

        System.out.println("[SERVER] Enviado para o Cliente: " + response + "\n");
        
        return response.toString();
    }

    private void logout(JSONObject dados) {
        JSONObject request = dados;
        clientes.remove(c);
        try {
            input.close();
            output.close();
            clientSocket.close();
            
        } catch (IOException ex) {
            System.err.println("[SERVER] Impossivel Desconectar Cliente\n");
        }
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
