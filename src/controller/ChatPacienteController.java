/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.json.JSONObject;
import view.ChatPacienteView;

/**
 *
 * @author rafae
 */
public class ChatPacienteController extends Thread {
    
    // parte que controla a recepção de mensagens do cliente
    private ClienteController conexao;
    public String saudeUser;
    String pacienteUser;
    String retorno;
    String cod = "";
    // construtor que recebe o socket do cliente
    public ChatPacienteController(ClienteController cc, String saude, String paciente) {
        this.conexao = cc;
        this.saudeUser = saude;
        this.pacienteUser = paciente;
    }

    public ChatPacienteController() {
        
    }
    
    public void principal()
    {
        Thread thread = new ChatPacienteController(conexao, saudeUser, pacienteUser);
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
            case "74":
                recebeMensagem(jsonObj);
                return "fn";
            case "77":
                confirmaEncerramento(jsonObj);
                return "fn";
                
            default:
                break;

        }
        return null;
    }
    
    public void recebeMensagem(JSONObject dados){
                
        if("74".equals(dados.getString("cod"))){
            //view.EscreveMensagem(dados.getString("origem"), dados.getString("msg"));
            System.out.println("[RECEBEU] "+dados.getString("origem")+": "+dados.getString("msg"));
        }else{
            System.out.println("[PACIENTE] Codigo diferente de 74 ao receber mensagem");
        }
        
    }
    public void confirmaEncerramento(JSONObject dados){
        JSONObject request = new JSONObject();
        request.put("cod", "78");
        request.put("sucesso", "false");
        
        if("77".equals(dados.getString("cod"))){
            request.put("sucesso", "true");
            conexao.enviarSemEscuta(request.toString());
            System.out.println("[PACIENTE] Chat encerrado com sucesso");
            
        }else{
            System.out.println("[PACIENTE] Codigo diferente de 77 ao encerrar chat");
        }
    }
    
    
    
}