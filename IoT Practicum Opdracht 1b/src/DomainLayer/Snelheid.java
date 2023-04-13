package DomainLayer;

import InfrastructureLayer.Subject;

public class Snelheid implements Subject {
    private int Waarde;
    
    public Snelheid     () { Waarde=0;      }
    public int  waarde  () { return Waarde; }
    public void verhoog () { Waarde++;      } // Simulatie
    public void verlaag () { Waarde--;      } // Simulatie
}
