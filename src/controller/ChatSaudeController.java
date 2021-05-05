/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JTextField;
import org.json.JSONObject;
import view.ChatSaudeView;

/**
 *
 * @author rafae
 */
public class ChatSaudeController extends Thread {
    
    // parte que controla a recepção de mensagens do cliente
    private ClienteController conexao;
    JTextField campoNome;
    String saudeUser;
    public String pacienteUser;
    String retorno;
    String cod = "";
    // construtor que recebe o socket do cliente
    public ChatSaudeController(ClienteController cc, String saude) {
        this.conexao = cc;
        this.saudeUser = saude;
    }

    public ChatSaudeController() {
        
    }
    
    public void principal()
    {
        Thread thread = new ChatSaudeController(conexao, saudeUser);
        thread.start();
    }
    // execução da thread
    @Override
    public void run()
    {
        String msg;
        while (true){
            retorno = tratarDados(conexao.escutar());
            if(!"fn".equals(retorno)){
             conexao.enviarSemEscuta(retorno);
            }
         }
    }
    
    private String tratarDados(String dados) {

        JSONObject jsonObj = new JSONObject(dados);

        //Armazena o codigo da requisicao
        cod = jsonObj.getString("cod");

        switch (cod) {
            case "70":
                return confirmarChat(jsonObj);
            case "74":
                recebeMensagem(jsonObj);
                return "fn";
            case "76":
                confirmaEncerramento(jsonObj);
                return "fn";
            default:
                break;

        }
        return null;
    }
    
    public String confirmarChat(JSONObject dados){
        JSONObject response = new JSONObject();
        response.put("cod", "71");
        response.put("sucesso", "false");
        //recebe nome paciente aqui
        if("70".equals(dados.getString("cod"))){
            pacienteUser = dados.getString("usuario");
            System.out.println("[SAUDE] Nome paciente: "+pacienteUser);
            response.put("sucesso", "true");
        }else{
            System.out.println("[SAUDE] Codigo diferente de 70 ao iniciar chat");
        }
        
        return response.toString();
    }
    
    public void recebeMensagem(JSONObject dados){
                
        if("74".equals(dados.getString("cod"))){
            //view.chat.append(dados.getString("origem")+": " + dados.getString("msg") + "\n");
            System.out.println("[RECEBEU] "+dados.getString("origem")+": "+dados.getString("msg"));
        }else{
            System.out.println("[SAUDE] Codigo diferente de 74 ao receber mensagem");
        }
        
    }
    public void confirmaEncerramento(JSONObject dados){
        
        if("76".equals(dados.getString("cod"))){
            if("true".equals(dados.getString("sucesso"))){
                System.out.println("[SAUDE] Chat encerrado com sucesso");
            }else{
                System.out.println("[SAUDE] A requisicao de encerramento nao foi aceita pelo servidor");
            }
        }else{
            System.out.println("[SAUDE] Codigo diferente de 76 ao encerrar chat");
        }
    }
    
    
    
}
