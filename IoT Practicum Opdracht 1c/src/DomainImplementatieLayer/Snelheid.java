package DomainImplementatieLayer;

import DomainLayer.SnelheidInt;
import InfrastructureLayer.Subject;

public class Snelheid implements Subject, SnelheidInt {
    private int Waarde;
    
    public Snelheid     () { Waarde=0;      }
    public int  waarde  () { return Waarde; }
    public void verhoog () { Waarde++;      } // Simulatie
    public void verlaag () { Waarde--;      } // Simulatie
}
