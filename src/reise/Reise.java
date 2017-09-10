package reise;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Eine Reise besteht aus Startzeitpunkt, beliebig vielen Zwischenstationen und
 * Endzeitpunkt
 *
 * @author Mieke Narjes
 * @author Uwe Krause
 */
public class Reise {

    /**
     * Der Titel der Reise
     */
    private String titel;

    /**
     * Die Liste der Stationen nach der Reihenfolge ihres Startzeitpunktes. Die
     * erste Station ist der Start, die letzte Station ist das Ziel. Somit
     * entfallen feste Zuweisungen fuer Start oder Ziel.
     */
    private LinkedList<Station> stationen = new LinkedList<>();

    /**
     * Eine leere Reise ohne Start oder Ziel. Start, Ziel und ggf.
     * Zwischenstationen lassen sich nachtraeglich hinzufuegen.
     *
     * @param titel Bezeichnung der Reise z.B. "Weltreise"
     */
    public Reise(String titel) {

        this.titel = titel;
    }

    /**
     * Eine Reise ohne Ziel. Ein Ziel und ggf. Zwischenstationen lassen sich
     * nachtraeglich hinzufuegen.
     *
     * @param titel Bezeichnung der Reise z.B. "Weltreise"
     * @param start Die erste Sation der Reise
     */
    public Reise(String titel, Station start) {

        this(titel);
        this.addStation(start);
    }

    /**
     * Eine Reise mit definiertem Start und Ziel. Zwischenstationen lassen sich
     * nachtraeglich hinzufuegen.
     *
     * @param titel Bezeichnung der Reise z.B. "Weltreise"
     * @param start Die erste Sation der Reise
     * @param ziel Die letzte Station der Reise
     */
    public Reise(String titel, Station start, Station ziel) {

        this(titel, start);
        this.addStation(ziel);
    }

    /**
     * Gibt die Startzeit der Reise zurueck. Startzeit ist die Ankunft an der
     * Startstation, welche sich ueber die erste Station der Reise definiert.
     * Bei der Startstation sollte Ankunft = Abfahrt sein.
     *
     * @return Startzeit der Reise oder null
     */
    public LocalDateTime getStartzeit() {
        try {

            return getStartort().getAnkunft();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Gibt die Endzeit der Reise zurueck. Endzeit ist die Ankunft an der
     * Zielstation, welche sich ueber die letzte Station der Reise definiert.
     * Bei der Zielstation sollte Ankunft = Abfahrt sein.
     *
     * @return Endzeit der Reise oder null
     */
    public LocalDateTime getEndzeit() {
        try {
            return getReiseziel().getAnkunft();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Gibt den Titel der Reise zurueck.
     *
     * @return titel Titel der Reise
     */
    public String getTitel() {

        return titel;
    }

    /**
     * Gibt den Startort der Reise zurueck.
     *
     * @return Startort der Reise
     */
    public Station getStartort() {

        this.sort();
        return this.stationen.peekFirst();
    }

    /**
     * Gibt das Reiseziel zurueck.
     *
     * @return the Reiseziel
     */
    public Station getReiseziel() {

        this.sort();

        return this.stationen.peekLast();
    }

    /**
     * Gibt die Stationen sortiert zurueck.
     *
     * @return Iterator der Stationen
     */
    public LinkedList<Station> getStationen() {

        this.sort();
        return this.stationen;
    }

    /**
     * Setzt den Titel der Reise.
     *
     * @param titel titel
     */
    public void setTitel(String titel) {

        this.titel = titel;
    }

    /**
     * Fuegt der Reise eine NEUE Station hinzu, unabhaengig von ihrer Position.
     * Eine bereits vorhandene Station wird nicht erneut hinzugefuegt. Es wird
     * niecht ueberprueft, ob es eine Zwischenstation wird oder aufgrund der
     * Zeitlichen Reihenfolge Anfang/Ende der Reise.
     *
     * @param station Die hinzuzufuegende Station
     */
    public void addStation(Station station) {

        if (!stationen.contains(station)) {

            this.stationen.add(station);
        }
    }

    /**
     * Entfernt eine Station der Reise.
     *
     * @param station Die zu entfernende Station
     */
    public void removeStation(Station station) {

        this.stationen.remove(station);
    }

    /**
     * Entfernt eine Station und fuegt eine neue hinzu. Die Position der neuen
     * Station definiert sich durch ihre natuerliche Ordnung.
     *
     * @param alt Die zu entfernende Station
     * @param neu Die hinzuzufuegende Station
     */
    public void changeStation(Station alt, Station neu) {

        this.removeStation(alt);

        this.addStation(neu);
    }

    /**
     * Sortiert die interne Liste gemaess ihrer "natuerlichen Ordnung". Wie in
     * compareTo() des Objekts definiert.
     */
    public void sort() {

        this.stationen.sort(null);
    }

    /**
     * Gibt die Gesamtdauer der Reise zurueck.
     *
     * @return gesamtdauer der Reise
     */
    public Duration getGesamtdauer() {
        try {
            return Duration.between(this.getStartzeit(), this.getEndzeit());
        } catch (NullPointerException e) {
            return Duration.ZERO;
        }
    }

    /**
     * Detaildarstellung: Etappen, Gesamte Reise, Dauer.
     *
     * @return Detaildarstellung der Reise
     */
    @Override
    public String toString() {

        this.sort();

        StringBuilder sb = new StringBuilder();

        sb.append("Beginn der Reise:\n");
        sb.append("\t").append(this.getStartort()).append("\n");

        Duration zeit_in_bewegung = Duration.ZERO;
        Duration trip;

        // initialisiert mit 1, um die Startstation zu ueberspringen!
        for (int i = 1; i < stationen.size(); i++) {
            // Was zwischen den Stationen passiert
            // Delta( Abreise(i-1), Ankunft(i)
            sb.append("\t\t| ");

            trip = Duration.between(stationen.get(i - 1).getAbreise(), stationen.get(i).getAnkunft());

            zeit_in_bewegung = zeit_in_bewegung.plus(trip);

            sb.append(trip);

            sb.append("\n");

            // Informationen zu den Stationen
            sb.append("\t");
            sb.append(stationen.get(i));
            sb.append("\n");
        }

        sb.append("\n");

        sb.append("Reisedauer: ").append(getGesamtdauer()).append("\n");
        sb.append("Zeit in Bewegung: ").append(zeit_in_bewegung);
        sb.append("\n");

        return sb.toString();
    }
}
