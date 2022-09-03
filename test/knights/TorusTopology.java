/**
 * A3-Springer
 * 2022-09-02
 * VP
 * Aufgabe 5
 * Realisieren Sie die konkrete Unterklasse TorusTopology von Topology (Achten Sie auch hier auf
 * einen geeignete Konstruktor!). Sie soll die Topologie eines torusförmigen Schachbretts umsetzen,
 * bei dem man sich die gegenüberliegenden Kanten des Schachbretts miteinander verklebt vorstellt.
 * Damit entspricht die Zeile −1 tatsächlich der Zeile n − 1, die Zeile −2 der Zeile n − 2 bzw.
 * die Zeile n tatsächlich der Zeile 0, die Zeile n + 1 der Zeile 1 etc. Für Spalten verhält es sich
 * ähnlich. Die Methode map liefert also für jedes Feld ein von null verschiedenes Ergebnis zurück.
 */

package knights;

public class TorusTopology extends Topology {
    /**
     * Konstruktor
     *
     * @param rows    Zeilen
     * @param columns Spalten
     * @throws IllegalArgumentException Exception-Call im undefinierten Bereich (Negativ)
     */
    protected TorusTopology(int rows, int columns) throws IllegalArgumentException {
        super(rows, columns);
    }

    // Map muss noch geschrieben werden!
    @Override
    public Field map(Field f) {
        // Feld liegt in Topologie
        if (0 <= f.getRow() && 0 <= f.getColumn()) if (f.getRow() < rows && f.getColumn() < columns) return (f);
        // Feld liegt außerhalb der Topologie: f.getRow/Column NEGATIV, f.getRow/Column GRÖßER als rows/columns
        int m = Math.floorMod(f.getRow(), rows); int n = Math.floorMod(f.getColumn(), columns);
        return (new Field(m, n));
    }
}
