/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.json.JSONObject;
import view.ChatPacienteView;

/**
 *
 * @author Maths
 */
public class AdminController extends Thread{

    
    private ClienteController conexao;
    public String adminUser;
    String retorno;
    String cod = "";
    
    public AdminController() {
    }
    
    public AdminController(ClienteController conexao) {
        this.conexao = conexao;
    }
    
    public void principal()
    {
        Thread thread = new AdminController(conexao);
        thread.start();
    }
    // execução da thread
    @Override
    public void run()
    {
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
                
                return "fn";
            case "77":
                
                return "fn";
                
            default:
                break;

        }
        return null;
    }
    
    public void filtrar(String query, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(modelo);
        tabela.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
}
