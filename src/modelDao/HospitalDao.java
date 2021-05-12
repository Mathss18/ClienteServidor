/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Hospital;

/**
 *
 * @author Matheus
 */
public class HospitalDao {
    public void create(Hospital h){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO hospitais (nome,endereco,vagas)VALUES(?,?)");
            stmt.setString(1,h.getNome());
            stmt.setString(2,h.getEndereco());
            stmt.setInt(3,h.getVagas());

           
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo Com Sucesso!"); 
            
            
                    } catch (SQLException ex) {
            Logger.getLogger(HospitalDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao Cadastrar Hospital " + ex,"ERRO",JOptionPane.ERROR_MESSAGE); 
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
        
    }
    
    public List<Hospital> find(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Hospital> hospitais = new ArrayList<Hospital>();
            
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hospitais ORDER BY id ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Hospital h = new Hospital();
                
                h.setId(rs.getInt("id"));
                h.setNome(rs.getString("nome"));
                h.setEndereco(rs.getString("endereco"));
                h.setVagas(rs.getInt("vagas"));

                hospitais.add(h);
                
            }
                    
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Falha Do Tipo:" + ex,"ERRO",JOptionPane.ERROR_MESSAGE); 
            Logger.getLogger(HospitalDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return hospitais;
    }
    
    public Hospital findById(int id){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Hospital h = new Hospital();
            
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hospitais where id = ?");
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                h.setId(rs.getInt("id"));
                h.setNome(rs.getString("nome"));
                h.setEndereco(rs.getString("endereco"));
                h.setVagas(rs.getInt("vagas"));

                
            }
                    
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Falha Do Tipo:" + ex,"ERRO",JOptionPane.ERROR_MESSAGE); 
            Logger.getLogger(HospitalDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return h;
    }
    
    public boolean update(Hospital h){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE hospitais SET nome=?,endereco=?,vagas=? WHERE id = ?");
            stmt.setString(1,h.getNome());
            stmt.setString(2,h.getEndereco());
            stmt.setInt(3,h.getVagas());
            
            
            
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado Com Sucesso!"); 
            
            
                    } catch (SQLException ex) {
            Logger.getLogger(HospitalDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar! " + ex); 
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
        return true;
    
    }
    
    public void delete(Hospital h){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM hospitais WHERE id=?");
            
            stmt.setInt(1,h.getId());
           
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Removido Com Sucesso!"); 
            
            
                    } catch (SQLException ex) {
            Logger.getLogger(HospitalDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Excluir! "); 
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
        
    }
}

