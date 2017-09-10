package reise;

import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author Mieke Narjes
 * @author Uwe Krause
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /**
         * 5. Schreiben Sie bitte Code, mit dem solche Reisen zusammengestellt
         * werden und bearbeitet werden können. Hier können Sie Controls
         * einsetzen und den Umgang mit ihnen üben, die Sie auch im Mediaplayer
         * verwenden wollen.
         */
        Station station1 = new Station(
                "Hamburg",
                LocalDateTime.of(2016, Month.FEBRUARY, 13, 13, 00));

        Station station2 = new Station(
                "Bremen",
                LocalDateTime.of(2016, Month.MARCH, 13, 14, 20),
                LocalDateTime.of(2016, Month.APRIL, 15, 14, 00));

        Station station3 = new Station(
                "Hannover",
                LocalDateTime.of(2016, Month.MAY, 15, 16, 00),
                LocalDateTime.of(2016, Month.JUNE, 16, 12, 00));

        Station station3gleich = new Station(
                "Hannover",
                LocalDateTime.of(2016, Month.MAY, 15, 16, 00),
                LocalDateTime.of(2016, Month.JUNE, 16, 12, 00));

        Station station4 = new Station(
                "Berlin",
                LocalDateTime.of(2016, Month.SEPTEMBER, 16, 23, 00));

        Station station35 = new Station("Magdeburg",
                LocalDateTime.of(2016, Month.JULY, 16, 16, 00),
                LocalDateTime.of(2016, Month.AUGUST, 16, 18, 00));

        Reise reise = new Reise("Weltreise");

        //System.out.println(reise.getGesamtdauer());

        reise.addStation(station1);
        reise.addStation(station2);
        reise.addStation(station3);

        // versucht eine gleiche Station einzufuegen
        reise.addStation(station3gleich);

        reise.addStation(station4);

        /**
         * fuegt nachtraeglich eine Zwischenstation ausserhalb der zeitlichen
         * Reihenfolge ein
         */
        reise.addStation(station35);

        // System.out.println(reise);
    }
}
