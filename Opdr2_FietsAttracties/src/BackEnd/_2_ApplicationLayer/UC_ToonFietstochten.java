package BackEnd._2_ApplicationLayer;

import BackEnd._3_DAOLayer.FietstochtDao;
import BackEnd._3_DAOLayer.LocatieDao;
import BackEnd._5_DomainLayer.Fietstocht;
import BackEnd._5_DomainLayer.Locatie;

public class UC_ToonFietstochten {
    
    private ToonFietstochtenServer  S;
    
    public UC_ToonFietstochten (ToonFietstochtenServer  s) {
        S=s;
    }

    public void toon () {
        int tid=S.tid();
        FietstochtDao fdao = new FietstochtDao();
        LocatieDao    ldao = new LocatieDao();
        for (Fietstocht f : fdao.all()) {
            if (f.tid()==tid) {
                S.toon (f);
            }
            S.toon("\n");
            for (Locatie l : ldao.all()) {
                String s1=l.tijd().toString();
                String s2=l.tijdStip().toString();
                if (l.tid()==tid && f.tijd().toString().equals(l.tijd().toString())) {
                    S.toon (l);
                }
            }
        }
    }
}
