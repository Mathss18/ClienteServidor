/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClienteController;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class FormularioView extends javax.swing.JFrame {

    ClienteController cc;

    public FormularioView(ClienteController cliente) {
        cc = cliente;
        initComponents();
    }

    private FormularioView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inputIdade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboFebre = new javax.swing.JComboBox<>();
        comboTosse = new javax.swing.JComboBox<>();
        comboRespirar = new javax.swing.JComboBox<>();
        comboIsolamento = new javax.swing.JComboBox<>();
        comboContato = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnEnviarRespostas = new javax.swing.JButton();
        btnDeslogar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Formulário para teste de COVID-19");

        jLabel2.setText("Apresentou Febre?");

        jLabel3.setText("Apresentou tosse?");

        jLabel4.setText("Apresentou dificuldade para respirar?");

        jLabel5.setText("Qual é sua Idade?");

        jLabel6.setText("Você está em isolamento social?");

        jLabel7.setText("Esteve em contato com alguém");

        comboFebre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sim", "Não" }));

        comboTosse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sim", "Não" }));

        comboRespirar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sim", "Não" }));

        comboIsolamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sim", "Não" }));

        comboContato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sim", "Não" }));

        jLabel8.setText("que apresentou sintomas?");

        btnEnviarRespostas.setText("Enviar Respostas");
        btnEnviarRespostas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarRespostasActionPerformed(evt);
            }
        });

        btnDeslogar.setText("Deslogar e Fechar");
        btnDeslogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeslogarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboTosse, javax.swing.GroupLayout.Alignment.LEADING, 0, 149, Short.MAX_VALUE)
                                .addComponent(comboFebre, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(comboRespirar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(comboContato, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(inputIdade)
                            .addComponent(comboIsolamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEnviarRespostas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeslogar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboFebre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTosse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboIsolamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboRespirar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addComponent(comboContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEnviarRespostas)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeslogar)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarRespostasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarRespostasActionPerformed
        // TODO add your handling code here:
        JSONObject requestJson = new JSONObject();
        JSONObject resposta1 = new JSONObject();
        JSONObject resposta2 = new JSONObject();
        JSONObject resposta3 = new JSONObject();
        JSONObject resposta4 = new JSONObject();
        JSONObject resposta5 = new JSONObject();
        JSONObject resposta6 = new JSONObject();
        JSONObject responseJson;
        String response;

        String febre = comboFebre.getSelectedItem().toString();
        String tosse = comboTosse.getSelectedItem().toString();
        String respirar = comboRespirar.getSelectedItem().toString();
        String contato = comboContato.getSelectedItem().toString();
        String idade = inputIdade.getText();
        String isolamento = comboIsolamento.getSelectedItem().toString();

        resposta1.put("id", "1");
        resposta1.put("resposta", (febre == "Sim") ? "1" : "0");

        resposta2.put("id", "2");
        resposta2.put("resposta", (tosse == "Sim") ? "1" : "0");

        resposta3.put("id", "3");
        resposta3.put("resposta", (respirar == "Sim") ? "1" : "0");

        resposta4.put("id", "4");
        resposta4.put("resposta", (contato == "Sim") ? "1" : "0");

        resposta5.put("id", "5");
        resposta5.put("resposta", idade);

        resposta6.put("id", "6");
        resposta6.put("resposta", (isolamento == "Sim") ? "1" : "0");

        requestJson.put("cod", "6");
        requestJson.append("respostas", resposta1);
        requestJson.append("respostas", resposta2);
        requestJson.append("respostas", resposta3);
        requestJson.append("respostas", resposta4);
        requestJson.append("respostas", resposta5);
        requestJson.append("respostas", resposta6);

        try {
            response = cc.enviarMensagem(requestJson.toString());
            responseJson = new JSONObject(response);    

            if (responseJson.getString("covid").equals("true")) {
                String[] options = {"Chat com Medico", "Ver lista de hospitais"};

                int option = JOptionPane.showOptionDialog(null, "A probabilidade de você estar com COVID é ALTA, então:",
                        "O que deseja fazer?",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                System.out.println(option);
            } else {
                String[] options = {"Refazer Formulario", "Cancelar"};

                int option = JOptionPane.showOptionDialog(null, "A probabilidade de você estar com COVID é BAIXA, então:",
                        "O que deseja fazer?",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                System.out.println(option);
            }

        } catch (IOException ex) {
            System.err.println("[CLIENTE] Falha ao enviar ou receber resposta do servidor");
        }

    }//GEN-LAST:event_btnEnviarRespostasActionPerformed

    private void btnDeslogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeslogarActionPerformed
        // TODO add your handling code here:
        JSONObject requestJson = new JSONObject();
        JSONObject responseJson;
        String response; //Não ha resposta do servido para este metodo

        requestJson.put("cod", "5");
        try {
            cc.enviarMensagem(requestJson.toString());
            cc.desconectar();
            this.dispose();
        } catch (IOException ex) {
            System.err.println("[CLIENTE] Falha ao enviar ou receber resposta do servidor");
        }
    }//GEN-LAST:event_btnDeslogarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeslogar;
    private javax.swing.JButton btnEnviarRespostas;
    private javax.swing.JComboBox<String> comboContato;
    private javax.swing.JComboBox<String> comboFebre;
    private javax.swing.JComboBox<String> comboIsolamento;
    private javax.swing.JComboBox<String> comboRespirar;
    private javax.swing.JComboBox<String> comboTosse;
    private javax.swing.JTextField inputIdade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
