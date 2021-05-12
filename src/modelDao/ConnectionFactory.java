
package modelDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static modelDao.ConnectionFactory.closeConnection;

/**
 *
 * @author gilberto
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/equipe4";  //para flex-mol com hamachi​
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
    
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL,USER,PASS);
                    
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao Conectar");
            throw new RuntimeException("Erro na conexão: ",ex);
            
        }
    
    }
    
    public static void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    }
    
        public static void closeConnection(Connection con, PreparedStatement stmt){
        
            closeConnection(con);
            
            try {
                
                if(stmt != null){
                stmt.close();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
    }
        
            public static void closeConnection(Connection con, PreparedStatement stmt,ResultSet rs){
        
            closeConnection(con);
                closeConnection(con, stmt);
            
            try {
                
                if(rs != null){
                rs.close();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
    }
    
}
