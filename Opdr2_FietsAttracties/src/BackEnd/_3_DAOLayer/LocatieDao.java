package BackEnd._3_DAOLayer;

import BackEnd.InfrastructureLayer.Database;
import BackEnd._5_DomainLayer.Locatie;
import BackEnd._4_DomainImplLayer.LocatieImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LocatieDao {
    
    private static final Database DB=Database.object();
    
    public List<Locatie> all()       {
        String sql = "SELECT Tid, Tijd, Tijdstip, X, Y FROM Locatie";
        List<Locatie> L = new ArrayList<Locatie>();        
        try { Connection conn = DB.connect("jdbc:sqlite:.//SQLite//Attractie.db");
              Statement stmt  = conn.createStatement();
              ResultSet rs    = stmt.executeQuery(sql);
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
              while (rs.next())  {
                    ///String tijdFietstocht = LocalDateTime.parse(rs.getString("Tijd"),formatter);
                    // KAN NIET!  Locatie moet OOK de fietstocht tijd bevatten!!! En deze ook opslaan...
                    L.add(new LocatieImpl(rs.getInt("Tid"),LocalDateTime.parse(rs.getString("Tijd"),formatter),LocalDateTime.parse(rs.getString("Tijdstip"),formatter),rs.getInt("X"),rs.getInt("Y")));
              }
            }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return L;
    }

    public void insert(Locatie l)    {
        String sql = "INSERT INTO Locatie(Tid,Tijd,Tijdstip,X,Y) VALUES(?,?,?,?,?)";
        try { Connection conn = DB.connect("jdbc:sqlite:.//SQLite//Attractie.db");
              String horeca;
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt   (1, l.tid()            );
              stmt.setString(2, l.tijd().toString());
              stmt.setString(3, l.tijdStip().toString());
              stmt.setInt   (4, l.x()              );
              stmt.setInt   (5, l.y()              );
              stmt.executeUpdate();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
    
    public void update(Locatie l)    {
        delete(l);
        insert(l);
    }

    public void delete(Locatie l)    {
        String sql = "DELETE FROM Locatie WHERE Tid = ? and Tijd=? and Tijdstip = ?";
        try { Connection conn = DB.connect("jdbc:sqlite:.//SQLite//Attractie.db");
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt   (1, l.tid()            );
              stmt.setString(2, l.tijd().toString());
              stmt.setString(3, l.tijdStip().toString());
              stmt.executeUpdate();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }    
}
