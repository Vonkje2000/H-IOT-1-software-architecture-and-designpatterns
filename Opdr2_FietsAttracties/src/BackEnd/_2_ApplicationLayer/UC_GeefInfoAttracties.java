package BackEnd._2_ApplicationLayer;

import BackEnd._3_DAOLayer.AttractieDao;
import BackEnd._3_DAOLayer.FietstochtDao;
import BackEnd._3_DAOLayer.LocatieDao;
import BackEnd._4_DomainImplLayer.FietstochtImpl;
import BackEnd._5_DomainLayer.Attractie;
import BackEnd._5_DomainLayer.Fietstocht;
import java.time.LocalDateTime;

public class UC_GeefInfoAttracties {

    GeefInfoAttractiesServer Server;
    Fietstocht          Tocht;
    
    public UC_GeefInfoAttracties(GeefInfoAttractiesServer s) {
        Server = s;
        laadAttracties();
    }
    
    public void laadAttracties() {
        UC_RegistreerAttracties.all().clear();
        AttractieDao adao = new AttractieDao();
        for (Attractie a : adao.all()) {
            UC_RegistreerAttracties.all().add(a);
        }
    }
    
    public void start (int tid) {
        Tocht = new FietstochtImpl(LocalDateTime.now(),tid);
        FietstochtDao fdao = new FietstochtDao();
        fdao.insert(Tocht);
    }
    
    public String getInfo (int tid, String tabletMessage) {
        
        String[] bericht = tabletMessage.split(",");
        int x       = Integer.parseInt(bericht[0]);
        int y       = Integer.parseInt(bericht[1]);
        int Radius  = Integer.parseInt(bericht[2]);
        int Horeca  = Integer.parseInt(bericht[3]);
        
        Tocht.voegLocatieToe(LocalDateTime.now(),x,y);
        
        String infoString = "";
        for (Attractie a : UC_RegistreerAttracties.all()) {
            if (   (x-a.x())*(x-a.x())+(y-a.y())*(y-a.y())<Radius*Radius &&
                   ( !a.isHoreca()  ||  (a.isHoreca() && Horeca==1 ) )
               )
                infoString += a.naam() + ": " + a.info() + "\n";
        }
        if (infoString=="") infoString = "Helaas geen attractie in de omgeving.\n";
        
        Server.setId(tid);        
        Server.getInfo(infoString);  // Dit is een beetje raar: in de callback meteen publish....
        return infoString;
    }
    public void stop (int tid) {
        LocatieDao    ldao = new LocatieDao();
        /*for (Locatie l : FietstochtImpl.locaties()) {
           ldao.insert(l);
        }*/
        
    }
}
