package BackEnd._2_ApplicationLayer;

import BackEnd._3_DAOLayer.AttractieDao;
import BackEnd._4_DomainImplLayer.AttractieImpl;
import BackEnd._5_DomainLayer.Attractie;
import java.util.ArrayList;
import java.util.List;

public class UC_RegistreerAttracties {
    
    RegistreerAttractiesServer  S;
    
    static List<Attractie> AlleTransacties = new ArrayList<>();
    
    public static List<Attractie> all () {
        return AlleTransacties; 
    }
    
    public UC_RegistreerAttracties (RegistreerAttractiesServer  s) {
        S=s;
    }

    public void registreerAttractie() {
        AttractieDao dao = new AttractieDao();
        do {
              String naam   = S.getNaam();
              int    x      = S.getX();
              int    y      = S.getX();
              String horeca = S.getHoreca();
              String info   = S.getInfo();
              
              boolean horecaBool;
              if (horeca.equals("J")) horecaBool=true;
              else horecaBool=false;
        
              Attractie A = new AttractieImpl(naam, x, y, horecaBool, info);
              dao.insert(A);        
              all().add(A);
           }
        while (S.next()); 
    }

}
