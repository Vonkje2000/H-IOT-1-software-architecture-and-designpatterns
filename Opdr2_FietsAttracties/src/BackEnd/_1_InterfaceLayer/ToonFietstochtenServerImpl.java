package BackEnd._1_InterfaceLayer;

import BackEnd._2_ApplicationLayer.ToonFietstochtenServer;
import BackEnd._5_DomainLayer.Fietstocht;
import BackEnd._5_DomainLayer.Locatie;
import java.time.LocalDate;
import java.util.Scanner;

public class ToonFietstochtenServerImpl implements ToonFietstochtenServer {
        public int tid   () {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Geef de Tablet ID: ");
            return scanner.nextInt();
        }
        public LocalDate datum () {
            return LocalDate.now();
        }
        public void toon  (Fietstocht f) {
            System.out.println("" + f.tid() + "\t"+ f.tijd());
        }
        public void toon  (Locatie l) {
            System.out.println("" + l.tid() + "\t"+ l.tijdStip() + l.x() + l.y());
        }
        public void toon  (String s) {
            System.out.print(s);
        }
}
