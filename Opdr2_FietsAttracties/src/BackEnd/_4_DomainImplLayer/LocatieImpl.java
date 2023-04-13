package BackEnd._4_DomainImplLayer;

import BackEnd._5_DomainLayer.Locatie;
import java.time.LocalDateTime;

public class LocatieImpl implements Locatie {
    private int           Tid;
    private LocalDateTime Tijd;  // van Fietstocht
    private LocalDateTime Tijdstip;
    private int           X;
    private int           Y;
    
    public LocatieImpl (int tid, LocalDateTime ft, LocalDateTime t, int x, int y) { Tid=tid; Tijd=ft; Tijdstip=t; X=x; Y=y; }
    
    public int           tid      () { return Tid;      }
    public LocalDateTime tijd     () { return Tijd;     }
    public LocalDateTime tijdStip () { return Tijdstip; } 
    public int           x        () { return X;        }
    public int           y        () { return Y;        }
}

/*
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class GetCurrentDateTime {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) {

        Date date = new Date();
        System.out.println(sdf.format(date));

        Calendar cal = Calendar.getInstance();
        System.out.println(sdf.format(cal.getTime()));

        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        LocalDate localDate = LocalDate.now();
        System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));

    }

}
*/