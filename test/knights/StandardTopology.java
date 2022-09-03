/**
 * A3-Springer
 * 2022-09-02
 * VP
 * Aufgabe 4
 * Realisieren Sie die konkrete Unterklasse StandardTopology von Topology (Achten Sie auf einen
 * geeignete Konstruktor!). Sie soll die übliche Topologie umsetzen, d.h. die map-Methode liefert
 * für alle Felder außerhalb des Schachbretts den Wert null zurück
 * AUSZUG!
 * Aufgabe3
 * • public abstract Field map(Field f) ist eine abstrakte Methode, die von konkreten
 * Unterklassen realisiert werden muss und die für die Realisierung der konkreten Topologie
 * verantwortlich ist. Die jeweilige Implementierung muss die folgenden Eigenschaften
 * aufweisen:
 * 1. Wird ein Feld f übergeben, das innerhalb des Schachbretts liegt (d.h. falls 0 ≤
 * f.getRow() < rows und 0 ≤ f.getColumn() < columns, wobei rows und columns die
 * Größe des Schachbretts angeben), soll f, also dasselbe Feld zurückgegeben werden.
 * 2. In allen anderen Fällen soll entweder null zurückgegeben werden oder ein Feld, das
 * innerhalb des Schachbretts liegt.
 * Diese map-Methode kann verwendet werden, um die Springerzüge unter der jeweiligen Topologie
 * zu berechnen: Befinde sich ein Springer auf einem Feld f1=new Field(0,1). Dann
 * wäre ein potenzieller Springerzug der nach f2=new Field(-1,-1), wenn man nämlich versucht,
 * eine Zeile nach oben und zwei Spalten nach links zu ziehen. Natürlich liegt f2
 * außerhalb des Schachbretts. Ob der Zug bei der gegebenen Topologie (sei das hier t) möglich
 * ist, sieht man am Ergebnis f3=t.map(f2). Der Zug ist nicht erlaubt, wenn f3 gleich
 * null ist. Andernfalls ist f3 das Feld innerhalb des Schachbretts (siehe die zweite der oberen
 * beiden Bedingungen), wohin der Springer nun tatsächlich gezogen wird.
 */

package knights;

/**
 * Konstruktor
 * Erbt von Topology, Konstruktor via super()
 */
public class StandardTopology extends Topology {
    public StandardTopology(int rows, int columns) throws IllegalArgumentException {
        super(rows, columns);
    }

    /**
     * Auswertung Feld
     *
     * @param f wird zurückgegeben, wenn innerhalb des Schachbretts
     * @return null wird zurückgegeben, wenn außerhalb des Schachbretts
     */
    @Override
    public Field map(Field f) {
        if (0 <= f.getRow() && 0 <= f.getColumn()) if (f.getRow() < rows && f.getColumn() < columns) return (f);
        return null;
    }
}