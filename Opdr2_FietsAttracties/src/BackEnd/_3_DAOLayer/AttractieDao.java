package BackEnd._3_DAOLayer;

import BackEnd.InfrastructureLayer.Database;
import BackEnd._4_DomainImplLayer.AttractieImpl;
import BackEnd._5_DomainLayer.Attractie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AttractieDao {
    
    private static final Database DB=Database.object();
    
    public List<Attractie> all()       {
        String sql = "SELECT naam, X, Y, Horeca, info FROM Attractie";
        List<Attractie> L = new ArrayList<Attractie>();        
        try { Connection conn = DB.connect("jdbc:sqlite:.//SQLite//Attractie.db");
              Statement stmt  = conn.createStatement();
              ResultSet rs    = stmt.executeQuery(sql);
              while (rs.next())  {
                    boolean Horeca = rs.getString("horeca").equals("J");
                    L.add(new AttractieImpl(rs.getString("naam"),rs.getInt("X"),rs.getInt("Y"),Horeca,rs.getString("info")));
              }
            }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return L;
    }

    public void insert(Attractie a)    {
        String sql = "INSERT INTO Attractie(naam,X,Y,horeca,info) VALUES(?,?,?,?,?)";
        try { Connection conn = DB.connect("jdbc:sqlite:.//SQLite//Attractie.db");
              String horeca;
              if (a.isHoreca()) horeca="J"; else horeca="N";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setString(1, a.naam());
              stmt.setInt   (2, a.x()   );
              stmt.setInt   (3, a.y()   );
              stmt.setString(4, horeca  );
              stmt.setString(5, a.info());
              stmt.executeUpdate();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
    
    public void update(Attractie a)    {
        delete(a);
        insert(a);
    }

    public void delete(Attractie a)    {
        String sql = "DELETE FROM Attractie WHERE naam = ?";
        try { Connection conn = DB.connect("jdbc:sqlite:.//SQLite//Attractie.db");
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setString(1, a.naam());
              stmt.executeUpdate();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }    
}
