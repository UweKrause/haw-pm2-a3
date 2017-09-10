package reise;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Mieke Narjes
 * @author Uwe Krause
 */
public class ReiseTest {

    Station station1;

    Station station2;

    Station station3;
    Station station3gleich;
    Station station35;

    Station station4;

    Reise reise;

    LinkedList<Station> vergleich;

    public ReiseTest() {
    }

    @Before
    public void setUp() {

        station1 = new Station(
                "Hamburg",
                LocalDateTime.of(2016, Month.FEBRUARY, 13, 13, 00));

        station2 = new Station(
                "Bremen",
                LocalDateTime.of(2016, Month.MARCH, 13, 14, 20),
                LocalDateTime.of(2016, Month.APRIL, 15, 14, 00));

        station3 = new Station(
                "Hannover",
                LocalDateTime.of(2016, Month.MAY, 15, 16, 00),
                LocalDateTime.of(2016, Month.JUNE, 16, 12, 00));

        station3gleich = new Station(
                "Hannover",
                LocalDateTime.of(2016, Month.MAY, 15, 16, 00),
                LocalDateTime.of(2016, Month.JUNE, 16, 12, 00));

        station4 = new Station(
                "Berlin",
                LocalDateTime.of(2016, Month.SEPTEMBER, 16, 23, 00));

        station35 = new Station("Magdeburg",
                LocalDateTime.of(2016, Month.JULY, 16, 16, 00),
                LocalDateTime.of(2016, Month.AUGUST, 16, 18, 00));

        reise = new Reise("Weltreise");

        vergleich = new LinkedList<>();

    }

    /**
     * Test of getStartzeit method, of class Reise.
     */
    @Test
    public void testGetStartzeit() {

        System.out.println("getStartzeit");

        Reise instance = reise;
        instance.addStation(station1);
        LocalDateTime expResult = station1.getAbreise();
        LocalDateTime result = instance.getStartzeit();
        assertEquals(expResult, result);

    }

    /**
     * Test of getEndzeit method, of class Reise.
     */
    @Test
    public void testGetEndzeit() {
        System.out.println("getEndzeit");
        Reise instance = reise;
        instance.addStation(station1);
        instance.addStation(station2);
        instance.addStation(station3);
        LocalDateTime expResult = station3.getAnkunft();
        LocalDateTime result = instance.getEndzeit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartort method, of class Reise.
     */
    @Test
    public void testGetStartort() {
        System.out.println("getStartort");
        Reise instance = reise;

        instance.addStation(station4);
        instance.addStation(station3);
        instance.addStation(station2);
        instance.addStation(station1);

        Station expResult = station1;

        Station result = instance.getStartort();
        assertEquals(expResult, result);
    }

    /**
     * Test of getReiseziel method, of class Reise.
     */
    @Test
    public void testGetReiseziel() {
        System.out.println("getReiseziel");
        Reise instance = reise;

        instance.addStation(station4);
        instance.addStation(station3);
        instance.addStation(station2);
        instance.addStation(station1);

        Station expResult = station4;

        Station result = instance.getReiseziel();
        assertEquals(expResult, result);

    }

    /**
     * Test of getStationen method, of class Reise.
     */
    @Test
    public void testGetStationen() {
        System.out.println("getStationen");
        Reise instance = reise;

        reise.addStation(station1);
        reise.addStation(station2);
        reise.addStation(station4);

        vergleich.add(station1);
        vergleich.add(station2);
        vergleich.add(station4);

        assertEquals(reise.getStationen(), vergleich);

    }

    /**
     * Test of addStation method, of class Reise.
     */
    @Test
    public void testAddStation() {
        System.out.println("addStation");

        Reise instance = reise;

        reise.addStation(station1);
        reise.addStation(station2);
        reise.addStation(station4);

        vergleich.add(station1);
        vergleich.add(station2);
        vergleich.add(station4);

        assertEquals(reise.getStationen(), vergleich);

    }

    /**
     * Test of removeStation method, of class Reise.
     */
    @Test
    public void testRemoveStation() {
        System.out.println("removeStation");
        Reise instance = reise;

        reise.addStation(station1);
        reise.addStation(station2);
        reise.addStation(station4);

        reise.removeStation(station2);

        vergleich.add(station1);

        vergleich.add(station4);

        assertEquals(reise.getStationen(), vergleich);
    }

    /**
     * Test of changeStation method, of class Reise.
     */
    @Test
    public void testChangeStation() {
        System.out.println("changeStation");
        Station alt = station2;
        Station neu = station3;
        Reise instance = reise;

        reise.addStation(station1);
        reise.addStation(station2);
        reise.addStation(station4);

        vergleich.add(station1);
        vergleich.add(station3);
        vergleich.add(station4);

        instance.changeStation(station2, station3);

        assertEquals(reise.getStationen(), vergleich);

    }

    /**
     * Test of getGesamtdauer method, of class Reise.
     */
    @Test
    public void testGetGesamtdauer() {
        System.out.println("getGesamtdauer");
        Reise instance = reise;

        reise.addStation(station1);
        reise.addStation(station4);

        Duration expResult = Duration.between(station1.getAbreise(), station4.getAnkunft());
        Duration result = instance.getGesamtdauer();
        assertEquals(expResult, result);

    }

}
