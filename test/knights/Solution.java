/**
 * A3-Springer
 * 2022-09-02
 * VP
 * Aufgabe 2
 * Realisieren Sie im Paket knights die Klasse Solution. Jede Instanz entspreche einer Folge von
 * Field-Instanzen, womit man eine Springer-Route darstellen kann. Die Klasse soll die folgenden
 * Methoden bzw. Konstruktor aufweisen:
 * • public Solution(Field... array) erstellt ein neues Solution-Objekt für die übergebene
 * Folge von Field-Instanzen.
 * Beachte, dass man das übergebene Array geeignet kopieren muss, damit man es später
 * verändern kann, ohne damit die Lösung zu verändern!
 * • public String toString() gibt die Folge der Felder geeignet in Zeichenkettendarstellung
 * zurück.
 * • Außerdem sind equals und hashCode mit der üblichen Semantik zu realisieren.
 */

package knights;

import java.util.Arrays;

public class Solution {
    Field[] fieldArray;

    /**
     * Konstruktor
     * es gab zwei Zeiger auf dasselbe Objekt, somit war der Vgl. immer true
     * jetzt wird beim Erstellen des Objekts immer ein neuer Zeiger mit anderer Speicheradresse erzeugt
     * damit wird sichergestellt, dass nun nur noch inhaltliche Vgl. stattfinden
     *
     * @param fieldArray [] variable Größe, Initialisierung nicht notwendig
     */
    public Solution(Field... fieldArray) {
        this.fieldArray = Arrays.copyOf(fieldArray, fieldArray.length);
    }

    /**
     * toString-Methode
     *
     * @return Kann sein, dass er nur den Namen vom Objekt im Speicher gibt, erlegt, von fieldArray zu Arrays geändert
     */
    public String toString() {return (Arrays.toString(fieldArray));}

    /**
     * @param o Objekt
     * @return Vgl.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o; return Arrays.equals(fieldArray, solution.fieldArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(fieldArray);
    }
}
