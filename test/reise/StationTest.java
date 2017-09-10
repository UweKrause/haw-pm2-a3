package reise;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Mieke Narjes
 * @author Uwe Krause
 */
public class StationTest {

    LocalDateTime zeitpunkt0;
    LocalDateTime zeitpunkt1;
    LocalDateTime zeitpunkt2;
    LocalDateTime zeitpunkt3;

    LocalDateTime zeitpunkt4;
    LocalDateTime zeitpunkt4gleich;

    LocalDateTime zeitpunkt5;
    LocalDateTime zeitpunkt5gleich;

    LocalDateTime zeitpunkt6;
    LocalDateTime zeitpunkt7;
    LocalDateTime zeitpunkt8;
    LocalDateTime zeitpunkt8gleich;

    LocalDateTime zeitpunkt99;

    Station station1;
    Station station2;

    Station station3;
    Station station3gleich;
    Station station35;

    Station station4;

    public StationTest() {
    }

    @Before
    public void setUp() {
        zeitpunkt0 = LocalDateTime.MIN;

        zeitpunkt1 = LocalDateTime.of(2016, Month.FEBRUARY, 13, 13, 00);

        zeitpunkt2 = LocalDateTime.of(2016, Month.MARCH, 13, 14, 20);
        zeitpunkt3 = LocalDateTime.of(2016, Month.APRIL, 15, 14, 00);

        zeitpunkt4 = LocalDateTime.of(2016, Month.MAY, 15, 16, 00);
        zeitpunkt4gleich = LocalDateTime.of(2016, Month.MAY, 15, 16, 00);

        zeitpunkt5 = LocalDateTime.of(2016, Month.JUNE, 16, 12, 00);
        zeitpunkt5gleich = LocalDateTime.of(2016, Month.JUNE, 16, 12, 00);

        zeitpunkt6 = LocalDateTime.of(2016, Month.JULY, 16, 16, 00);
        zeitpunkt7 = LocalDateTime.of(2016, Month.AUGUST, 16, 18, 00);
        zeitpunkt8 = LocalDateTime.of(2016, Month.SEPTEMBER, 16, 23, 00);

        zeitpunkt99 = LocalDateTime.MAX;

        station1 = new Station("Hamburg", zeitpunkt1);

        station2 = new Station("Bremen", zeitpunkt2, zeitpunkt3);

        station3 = new Station("Hannover", zeitpunkt4, zeitpunkt5);
        station3gleich = new Station("Hannover", zeitpunkt4, zeitpunkt5);

        station35 = new Station("Magdeburg", zeitpunkt6, zeitpunkt7);

        station4 = new Station("Berlin", zeitpunkt8);

    }

    /**
     * Test of getAnkunft method, of class Station.
     */
    @Test
    public void testGetAnkunft() {
        System.out.println("getAnkunft");
        Station instance = station1;
        LocalDateTime expResult = zeitpunkt1;
        LocalDateTime result = instance.getAnkunft();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAbreise method, of class Station.
     */
    @Test
    public void testGetAbreise() {
        System.out.println("getAbreise");
        Station instance = station2;
        LocalDateTime expResult = zeitpunkt3;
        LocalDateTime result = instance.getAbreise();
        assertEquals(expResult, result);

    }

    /**
     * Test of getOrt method, of class Station.
     */
    @Test
    public void testGetOrt() {
        System.out.println("getOrt");
        Station instance = station1;
        String expResult = "Hamburg";
        String result = instance.getOrt();
        assertEquals(expResult, result);

    }

    /**
     * Test of setAnkunft method, of class Station.
     */
    @Test
    public void testSetAnkunft() {
        System.out.println("setAnkunft");
        LocalDateTime ankunft = zeitpunkt3;
        Station instance = station2;
        instance.setAnkunft(ankunft);

        assertEquals(instance.getAnkunft(), ankunft);
    }

    /**
     * Test of setAbreise method, of class Station.
     */
    @Test
    public void testSetAbreise() {
        System.out.println("setAbreise");
        LocalDateTime abreise = zeitpunkt5;
        Station instance = station2;
        instance.setAbreise(abreise);

        assertEquals(instance.getAbreise(), abreise);
    }

    /**
     * Test of setOrt method, of class Station.
     */
    @Test
    public void testSetOrt() {
        System.out.println("setOrt");
        String ort = "Bielefeld";
        Station instance = station2;
        instance.setOrt(ort);

        assertEquals(instance.getOrt(), ort);

    }

    /**
     * Test of getAufenthaltsdauer method, of class Station.
     */
    @Test
    public void testGetAufenthaltsdauer() {
        System.out.println("getAufenthaltsdauer");
        Station instance = station2;
        Duration expResult = Duration.between(zeitpunkt2, zeitpunkt3);
        Duration result = instance.getAufenthaltsdauer();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Station.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");

        assertEquals(station3.hashCode(), station3gleich.hashCode());

    }

    /**
     * Test of equals method, of class Station.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        assertEquals(station3, station3gleich);

        assertNotEquals(station3, station35);

    }

    /**
     * Test of compareTo method, of class Station.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");

        boolean result;

        int compared;

        result = false;
        compared = station2.compareTo(station3);
        if (compared < 0) {
            result = true;
        }
        assertTrue(result);

        result = false;
        compared = station3.compareTo(station2);
        if (compared > 0) {
            result = true;
        }
        assertTrue(result);

        result = false;
        compared = station3.compareTo(station3gleich);
        if (compared == 0) {
            result = true;
        }
        assertTrue(result);

    }

}
