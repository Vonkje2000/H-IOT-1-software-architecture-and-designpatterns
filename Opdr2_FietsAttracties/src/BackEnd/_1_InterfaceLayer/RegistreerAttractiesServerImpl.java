package BackEnd._1_InterfaceLayer;

import java.util.Scanner;
import BackEnd._2_ApplicationLayer.RegistreerAttractiesServer;
import BackEnd._2_ApplicationLayer.UC_RegistreerAttracties;
import BackEnd._5_DomainLayer.Attractie;

public class RegistreerAttractiesServerImpl implements RegistreerAttractiesServer {
    
    public String getString (String vraag) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(vraag);
        return scanner.nextLine();
    }
    public int    getInteger (String vraag) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(vraag);
        return scanner.nextInt();        
    }
    public String getNaam   () {
        return getString ("Geef de naam van de attractie: ");
    }
    public int    getX      () {
        return getInteger ("Geef de x-locatie: ");
    }
    public int    getY      () {
        return getInteger ("Geef de y-locatie: ");
    }
    public String getHoreca () {
        return getString ("Is het een horecagelegenheid? [J,N]: ");
    }
    public String getInfo   () {
        return getString ("Geef de informatie: ");
    }
    public boolean next     () {
        return getString ("Nog een attractie? [J,N]: ").equals("J");
    }
    public static void toonOverzicht () {
        System.out.println("####################################################");
        for( Attractie a : UC_RegistreerAttracties.all()) {
            System.out.println(a.naam() + "\t" + a.x() + "\t" + a.y() + "\t" + a.info());
        }
        System.out.println("####################################################");
    }
}
