package BackEnd._3_DAOLayer;

import BackEnd.InfrastructureLayer.Database;
import BackEnd._4_DomainImplLayer.FietstochtImpl;
import BackEnd._5_DomainLayer.Fietstocht;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FietstochtDao {
    
    private static final Database DB=Database.object();
    
    public List<Fietstocht> all()       {
        String sql = "SELECT Tid, Tijd FROM Fietstocht";
        List<Fietstocht> L = new ArrayList<Fietstocht>();        
        try { Connection conn = DB.connect("jdbc:sqlite:.//SQLite//Attractie.db");
              Statement stmt  = conn.createStatement();
              ResultSet rs    = stmt.executeQuery(sql);
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
              while (rs.next())  {
                    L.add(new FietstochtImpl(LocalDateTime.parse(rs.getString("Tijd"),formatter),rs.getInt("Tid")));
              }
            }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return L;
    }

    public void insert(Fietstocht t)    {
        String sql = "INSERT INTO Fietstocht(Tid,Tijd) VALUES(?,?)";
        try { Connection conn = DB.connect("jdbc:sqlite:.//SQLite//Attractie.db");
              String horeca;
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt   (1, t.tid()            );
              stmt.setString(2, t.tijd().toString());
              stmt.executeUpdate();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
    
    public void update(Fietstocht t)    {
        delete(t);
        insert(t);
    }

    public void delete(Fietstocht t)    {
        String sql = "DELETE FROM Fietstocht WHERE tid = ? and tijd = ?";
        try { Connection conn = DB.connect("jdbc:sqlite:.//SQLite//Attractie.db");
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt   (1, t.tid()            );
              stmt.setString(2, t.tijd().toString());
              stmt.executeUpdate();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }    
}
