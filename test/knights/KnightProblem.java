/**
 * A3-Springer
 * 2022-09-02
 * VP
 * Aufgabe 6
 * Schreiben Sie die Klasse KnightProblem, mit der alle offenen Springer-Routen für ein gegebenes
 * Schachbrett mit seiner Topologie ermittelt werden sollen. Die Klasse soll die folgenden Methoden
 * bzw. Konstruktor aufweisen:
 * • public KnightProblem(Topology topology) erzeugt ein Objekt für die angegebene Topologie.
 * Beachte, dass der Parameter damit auch die Größe des Schachbretts vorgibt.
 * • public List<Solution> solve() gibt eine Liste aller Springer-Routen für die gegebene
 * Topologie zurück. Die Reihenfolge der Listenelemente ist unerheblich, es darf aber keine
 * Springer-Route mehrfach vorkommen. Wird die Methode mehrfach aufgerufen, soll sie immer
 * das gleiche Ergebnis zurückliefern.
 * Hinweis: Verwenden Sie Backtracking, um alle Lösungen zu finden. Nutzen Sie dabei die
 * map-Methode der vorgegebenen Topologie, wie es Aufgabe 3 erläutert hat, wenn Sie für
 * Felder die vom Springer erreichbaren Folgefelder berechnen wollen.
 * Probieren Sie Ihre Lösung mit den beiden in den Aufgaben 4 und 5 realisierten Topologien
 * aus. Beachten Sie, dass es für die Schachbretter der Größe n × n mit normaler Topologie und
 * n ∈ {2, 3, 4} keine Lösung gibt und dass die Zahl der Lösungen für n > 5 zu groß ist, als dass
 * man sie vernünftig berechnen könnte. Bei Verwendung der Torus-Topologie gibt es aber für viele
 * kleinere Schachbretter ebenfalls Lösungen, z.B. genau zwei für das Schachbrett der Größe 2×1,
 * wie man sich leicht vergegenwärtigen kann.
 */

package knights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnightProblem {
    Topology topology;

    public KnightProblem(Topology topology) {
        this.topology = topology;
    }

    public List<Solution> solve() {
        List<Solution> solutionList = new ArrayList<>(); List<Field[]> anwaerterListe = new ArrayList<>();

        // Trivialer Fall Schachbrett 1x1 ist auch eine Springer-Route?!
        if (topology.numberOfFields() == 1) {
            Field[] trivial = new Field[1]; trivial[0] = new Field(0, 0); solutionList.add(new Solution(trivial));
            return solutionList;
        }

        for (int i = 0; i < topology.rows; i++) {
            for (int j = 0; j < topology.columns; j++) {
                // Startfeld
                Field f = new Field(i, j);
                // Pfad der bei dem Startfeld beginnt
                Field[] fa = new Field[topology.numberOfFields()]; fa[0] = f; anwaerterListe.add(fa);

                // Schleife über die maximale Pfadlänge. Füge in jeder Iteration den Pfaden ein neues Feld hinzu, bis alle Felder besucht wurden
                for (int k = 0; k < topology.numberOfFields(); k++) {

                    List<Field[]> neueAnwaerter = new ArrayList<>();

                    for (Field[] aktuellerArr : anwaerterListe) {
                        // Füge 8 potenziell neue Wege hinzu
                        solutionsHelper(2, 1, aktuellerArr, k, solutionList, neueAnwaerter);
                        solutionsHelper(2, -1, aktuellerArr, k, solutionList, neueAnwaerter);
                        solutionsHelper(-2, 1, aktuellerArr, k, solutionList, neueAnwaerter);
                        solutionsHelper(-2, -1, aktuellerArr, k, solutionList, neueAnwaerter);
                        solutionsHelper(1, 2, aktuellerArr, k, solutionList, neueAnwaerter);
                        solutionsHelper(1, -2, aktuellerArr, k, solutionList, neueAnwaerter);
                        solutionsHelper(-1, 2, aktuellerArr, k, solutionList, neueAnwaerter);
                        solutionsHelper(-1, -2, aktuellerArr, k, solutionList, neueAnwaerter);
                    } anwaerterListe.clear(); anwaerterListe.addAll(neueAnwaerter);
                }
            }
        }

        return solutionList;
    }

    /**
     * Hilfsfunktion die überprüfen soll welcher der 8 möglichen Sprünge zu einer neuen erlaubten Springer-Route pfad führt
     *
     * @param r             Reihenänderung-Parameter (-2,-1,+1,+2)
     * @param c             Spaltenänderung-Parameter ...
     * @param aktuellerARR  Aktueller zu untersuchender Springer-Routen-Pfad
     * @param k             Anzahl an bereits besuchten Felder in jedem Pfad
     * @param solutionList  Liste mit allen gültigen Springer-Routen
     * @param neueAnwaerter Liste mit allen neuen auffächernden Pfaden
     */
    public void solutionsHelper(int r, int c, Field[] aktuellerARR, int k, List<Solution> solutionList, List<Field[]> neueAnwaerter) {
        Field[] aktuellerA = Arrays.copyOf(aktuellerARR, topology.numberOfFields());

        // Neues Sprungfeld
        Field sprungFeld = new Field(aktuellerA[k].row + r, aktuellerA[k].column + c);

        // Überprüfe, ob der Sprung erlaubt ist (Standard-Topology)
        if (!((topology.map(sprungFeld)) == null)) {
            boolean besucht = false;
            // überprüfe, ob alle bereits besuchten Knoten dem neuen entsprächen
            for (Field field : aktuellerA) {
                if (field != null) {
                    if (field.equals(topology.map(sprungFeld))) {
                        besucht = true;
                    }
                }
            }

            if (!besucht) {
                aktuellerA[k + 1] = topology.map(sprungFeld);
                // Abbruchbedingung!
                if (aktuellerA[topology.numberOfFields() - 1] != null) {
                    if (!solutionList.contains(new Solution(aktuellerA)))
                        // ABBRUCHBEDINGUNG Pfad hat alle Knoten besucht
                        solutionList.add(new Solution(aktuellerA));
                }
                else {
                    // Pfad wird für die nächste k++ Schleife hinzugefügt
                    neueAnwaerter.add(aktuellerA);
                }
            }
        }
    }
}
