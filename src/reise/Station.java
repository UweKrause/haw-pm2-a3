package reise;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Eine Station der Reise
 *
 * @author Mieke Narjes
 * @author Uwe Krause
 */
public class Station implements Comparable<Station> {

    private LocalDateTime ankunft;
    private LocalDateTime abreise;

    private String ort;

    // cached den hashCode, damit er nicht jedes Mal neu berechnet werden muss
    private int hashCode;

    /**
     *
     * @param ort Der Name der Station z.B. "Hamburg"
     * @param startziel Zeitpunkt des Aufenthaltes
     */
    public Station(String ort, LocalDateTime startziel) {
        this.ort = ort;
        this.ankunft = startziel;
        this.abreise = startziel;
    }

    /**
     *
     * @param ort Der Name der Station z.B. "Hamburg"
     * @param ankunft Zeitpunkt der Ankunft an diesem Ort
     * @param abreise Zeitpunkt der Abreise von diesem Ort
     */
    public Station(String ort, LocalDateTime ankunft, LocalDateTime abreise) {
        this.ort = ort;
        this.ankunft = ankunft;
        this.abreise = abreise;
    }

    /**
     * Gibt die Ankunftszeit zurueck
     *
     * @return ankunft
     */
    public LocalDateTime getAnkunft() {
        return ankunft;
    }

    /**
     * Gibt die Abreisezeit zurueck
     *
     * @return abreise
     */
    public LocalDateTime getAbreise() {
        return abreise;
    }

    /**
     * Gibt den Ort zurueck
     *
     * @return ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * Setzt die Ankunftszeit
     *
     * @param ankunft ankunft
     */
    public void setAnkunft(LocalDateTime ankunft) {
        this.ankunft = ankunft;
        this.hashCode = 0;
    }

    /**
     * Setzt den Abreisezeitpunkt
     *
     * @param abreise the abreise to set
     */
    public void setAbreise(LocalDateTime abreise) {
        this.abreise = abreise;
        this.hashCode = 0;
    }

    /**
     * Setzt den Ort der Station
     *
     * @param ort the ort to set
     */
    public void setOrt(String ort) {
        this.ort = ort;
        this.hashCode = 0;
    }

    /**
     * Gibt die Aufenthaltsdauer an diesem Ort zurueck. Aufenthaltsdauer ist die
     * Differenz zwischen Ankunft und Abreise.
     *
     * @return Duration
     */
    public Duration getAufenthaltsdauer() {

        return Duration.between(this.getAnkunft(), this.getAbreise());
    }

    /**
     * Berechnet den hashCode
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            int result = 17;

            result = 37 * result + ankunft.hashCode();
            result = 37 * result + abreise.hashCode();
            result = 37 * result + ort.hashCode();

            hashCode = result;
        }
        return hashCode;
    }

    /**
     * Vergleichsmethode
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Station other = (Station) obj;

        if (!Objects.equals(this.ankunft, other.ankunft)) {
            return false;
        }
        if (!Objects.equals(this.abreise, other.abreise)) {
            return false;
        }
        if (!Objects.equals(this.ort, other.ort)) {
            return false;
        }
        return true;
    }

    /**
     * Vergleicht in abhaengigkeit der natuerlichen Ordnung. Diese Methode
     * erlaubt es, die Sortierung einer Collectionklasse zu ueberlassen.
     * Reihenfolge: Ankunft - Abreise - Name
     *
     * @param cmp Mit dieser Station wird verglichen
     */
    @Override
    public int compareTo(Station cmp) {
        int ret;

        // -1, wenn kleiner,
        // 0 wenn gleich,
        //+1 wenn groesser
        ret = this.getAnkunft().compareTo(cmp.getAnkunft());

        if (ret == 0) {
            ret = this.getAbreise().compareTo(cmp.getAbreise());
        }

        if (ret == 0) {
            ret = this.getOrt().compareTo(cmp.getOrt());
        }

        return ret;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        boolean is_zwischenstation = (this.ankunft != this.abreise);

        sb.append(this.ort);

        if (is_zwischenstation) {
            sb.append(" (");
            sb.append(this.getAufenthaltsdauer());
            sb.append(")");
        }

        sb.append("\n");

        if (is_zwischenstation) {

            sb.append("\tAn: ").append(this.ankunft).append("\n");
            sb.append("\tAb: ").append(this.abreise);

        } else {

            sb.append("\t").append(this.abreise);
        }

        return sb.toString();

    }

}
