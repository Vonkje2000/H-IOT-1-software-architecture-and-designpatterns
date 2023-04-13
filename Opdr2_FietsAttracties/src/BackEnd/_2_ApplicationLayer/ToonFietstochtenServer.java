package BackEnd._2_ApplicationLayer;

import BackEnd._5_DomainLayer.Fietstocht;
import BackEnd._5_DomainLayer.Locatie;
import java.time.LocalDate;

public interface ToonFietstochtenServer {
        public int       tid   ();
        public LocalDate datum ();
        public void      toon  (Fietstocht f);
        public void      toon  (Locatie    l);
        public void      toon  (String     s);
}
