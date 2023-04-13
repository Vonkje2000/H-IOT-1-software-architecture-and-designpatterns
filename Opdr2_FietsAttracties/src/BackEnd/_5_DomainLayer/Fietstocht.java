package BackEnd._5_DomainLayer;

import Tablet._4_DomainLayer.*;
import java.time.LocalDateTime;

public interface Fietstocht {
    public int           tid ();
    public LocalDateTime tijd();
    public void          voegLocatieToe (LocalDateTime t, int x, int y);
}
