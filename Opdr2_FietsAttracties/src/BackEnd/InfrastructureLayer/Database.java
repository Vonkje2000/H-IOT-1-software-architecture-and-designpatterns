package BackEnd.InfrastructureLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
        private static Database Object; // Singleton, niet thread-save
        protected Database ( ) { }    
        public static Database object ( ) {
            if ( Object == null ) Object = new Database ( );         
            return Object;
        }
        
        public Connection connect(String url) {
        Connection C = null;
        try {
            C = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return C;
    }
}
