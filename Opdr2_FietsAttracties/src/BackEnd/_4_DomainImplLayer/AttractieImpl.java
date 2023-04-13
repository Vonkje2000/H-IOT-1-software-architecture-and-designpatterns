package BackEnd._4_DomainImplLayer;

import BackEnd._5_DomainLayer.Attractie;

public class AttractieImpl implements Attractie {

    final String  Naam;
    final int     X, Y;
    final boolean Horeca;
    final String  Info;
    
    public AttractieImpl(String n, int x, int y, boolean h, String info) {
        Naam   = n;
        X      = x;
        Y      = y;
        Horeca = h;
        Info   = info;
    }
    
    public String  naam () {
        return Naam;
    }
    public int x() {
        return X;
    }

    public int y() {
        return Y;
    }
    
    public boolean isHoreca () {
        return Horeca;
    }

    public String info() {
        return Info;
    }

}
