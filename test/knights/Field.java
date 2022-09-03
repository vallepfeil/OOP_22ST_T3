/**
 * A3-Springer
 * 2022-09-02
 * VP
 * Aufgabe 1
 * Realisieren Sie im Paket knights die Klasse Field. Jede Instanz entspreche einem Paar
 * (row, column) ganzer Zahlen, mit dem man ein Feld innerhalb, aber auch außerhalb eines
 * Schachbretts adressiert. Die Zeilen eines Schachbretts der Größe n × m werden von oben nach
 * unten mit row = 0, 1, 2 . . . und die Spalten von links nach rechts mit column = 0, 1, 2, . . .
 * durchnummeriert. Welche Felder innerhalb eines Schachbretts liegen, hängt von dessen Größe
 * ab. Grundsätzlich sind aber alle Paare erlaubt, auch solche mit negativen Werten. Sie werden
 * v.a. im Zusammenhang mit den verschiedenen Topologien benötigt.
 * Die Klasse soll die folgenden Methoden bzw. Konstruktor aufweisen:
 * • public Field(int row, int column) erstellt ein neues Field-Objekt für das angegebene
 * Zahlenpaar.
 * • Getter-Methoden für row und column.
 * • public String toString() gibt das Paar im Format Field(row,column) zurück.
 * • equals- und hashCode-Methoden mit der üblichen Semantik.
 */

package knights;

import java.util.Objects;

public class Field {
    int row;
    int column;

    /**
     * Konstruktor
     *
     * @param row    Zeile neg-pos, ganzzahlig
     * @param column Spalte neg-pos, ganzzahlig
     */
    public Field(int row, int column) {
        this.row = row; this.column = column;
    }

    /**
     * Getter-Methoden
     *
     * @return return(row), return(column)
     */
    public int getRow() {return (row);}

    public int getColumn() {return (column);}

    /**
     * @return gibt das Paar im Format Field(row,column) zurück
     */
    public String toString() {return "Field(" + getRow() + "," + getColumn() + ")";}

    /**
     * Vergleichs-Methoden equals, hashCode
     * Generierung, weil Override notwendig
     * Field-Objekte zum Vgl.; Parameter Row und Column; zur Gleichbehandlung
     *
     * @param o Objekt
     * @return row == field.row && column == field.column, Objects.hash(row, column)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; if (o == null || getClass() != o.getClass()) return false; Field field = (Field) o;
        return row == field.row && column == field.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
