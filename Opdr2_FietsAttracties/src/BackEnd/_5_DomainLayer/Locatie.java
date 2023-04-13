package BackEnd._5_DomainLayer;

import java.time.LocalDateTime;

public interface Locatie {
    public int           tid      ();    
    public LocalDateTime tijd     ();  // van de fietstocht !!!! NIEUW!!!!
    public LocalDateTime tijdStip ();
    public int           x        ();
    public int           y        ();
}