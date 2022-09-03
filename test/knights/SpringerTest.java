package knights;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SpringerTest {
    private static final int[][] dom = {
            {-2, -2}, {-2, -1}, {-2, 0}, {-2, 1}, {-2, 2}, {-2, 3}, {-2, 4}, {-2, 5}, {-1, -2}, {-1, -1}, {-1, 0},
            {-1, 1}, {-1, 2}, {-1, 3}, {-1, 4}, {-1, 5}, {0, -2}, {0, -1}, {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
            {0, 5}, {1, -2}, {1, -1}, {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, -2}, {2, -1}, {2, 0}, {2, 1},
            {2, 2}, {2, 3}, {2, 4}, {2, 5}, {3, -2}, {3, -1}, {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5}, {4, -2},
            {4, -1}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5}, {5, -2}, {5, -1}, {5, 0}, {5, 1}, {5, 2}, {5, 3},
            {5, 4}, {5, 5}, {6, -2}, {6, -1}, {6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5}
    };
    private static final int[][] cod = {
            {1, 0}, {1, 1}, {1, 0}, {1, 1}, {1, 0}, {1, 1}, {1, 0}, {1, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 0},
            {2, 1}, {2, 0}, {2, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {1, 0}, {1, 1},
            {1, 0}, {1, 1}, {1, 0}, {1, 1}, {1, 0}, {1, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 0},
            {2, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {1, 0}, {1, 1}, {1, 0}, {1, 1},
            {1, 0}, {1, 1}, {1, 0}, {1, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {0, 0},
            {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}
    };

    @Test
    public void testField() {
        final Field f1 = field(-1, 3);
        assertEquals(-1, f1.getRow());
        assertEquals(3, f1.getColumn());
        final Field f2 = field(-1, 3);
        assertEquals(f1, f1);
        assertEquals(f1, f2);
        assertEquals(f2, f1);
        assertEquals(f2, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    public void testSolution() {
        final Field[] array = new Field[]{field(0, 0), field(1, 0), field(2, 0)};
        final Solution s1 = new Solution(array);
        array[1] = field(0, 1);
        final Solution s2 = new Solution(array);
        final Solution s3 = new Solution(field(0, 0), field(1, 0), field(2, 0));
        final Solution s4 = new Solution(field(0, 0), field(0, 1), field(2, 0));
        assertEquals(s1, s1);
        assertNotEquals(s1, s2);
        assertEquals(s1, s3);
        assertNotEquals(s1, s4);
        assertNotEquals(s2, s1);
        assertEquals(s2, s2);
        assertNotEquals(s2, s3);
        assertEquals(s2, s4);
        assertEquals(s3, s1);
        assertNotEquals(s3, s2);
        assertEquals(s3, s3);
        assertNotEquals(s3, s4);
        assertNotEquals(s4, s1);
        assertEquals(s4, s2);
        assertNotEquals(s4, s3);
        assertEquals(s4, s4);

        assertEquals(s1.hashCode(), s1.hashCode());
        assertNotEquals(s1.hashCode(), s2.hashCode());
        assertEquals(s1.hashCode(), s3.hashCode());
        assertNotEquals(s1.hashCode(), s4.hashCode());
        assertEquals(s2.hashCode(), s2.hashCode());
        assertNotEquals(s2.hashCode(), s3.hashCode());
        assertEquals(s2.hashCode(), s4.hashCode());
        assertEquals(s3.hashCode(), s3.hashCode());
        assertNotEquals(s3.hashCode(), s4.hashCode());
        assertEquals(s4.hashCode(), s4.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTopology() {
        new StandardTopology(2, 0);
    }

    @Test
    public void testStandardTopology() {
        final Topology topology = new StandardTopology(3, 2);
        assertEquals(3, topology.getRows());
        assertEquals(2, topology.getColumns());
        assertEquals(6, topology.numberOfFields());
        for (int row = -2; row < 7; row++)
            for (int col = -2; col < 6; col++) {
                final Field f = topology.map(field(row, col));
                if (row >= 0 && row < 3 && col >= 0 && col < 2)
                    assertEquals(field(row, col), f);
                else
                    assertNull(f);
            }
    }

    @Test
    public void testTorusTopology() {
        final Topology topology = new TorusTopology(3, 2);
        assertEquals(3, topology.getRows());
        assertEquals(2, topology.getColumns());
        assertEquals(6, topology.numberOfFields());
        for (int i = 0; i < dom.length; i++)
            assertEquals(field(cod[i][0], cod[i][1]), topology.map(field(dom[i][0], dom[i][1])));
    }

    @Test
    public void testStandardTopology1() {
        final Topology topology = new StandardTopology(1, 1);
        final KnightProblem problem = new KnightProblem(topology);
        final List<Solution> solutions = problem.solve();
        assertEquals(List.of(new Solution(field(0, 0))), solutions);
    }

    @Test
    public void testStandardTopology5() {
        final Topology topology = new StandardTopology(5, 5);
        final KnightProblem problem = new KnightProblem(topology);
        final List<Solution> solutions = problem.solve();
        final Solution s1 = new Solution(field(0, 0), field(2, 1), field(4, 0),
                                         field(3, 2), field(1, 3), field(3, 4),
                                         field(4, 2), field(3, 0), field(1, 1),
                                         field(0, 3), field(2, 4), field(4, 3),
                                         field(3, 1), field(1, 0), field(0, 2),
                                         field(1, 4), field(3, 3), field(4, 1),
                                         field(2, 2), field(0, 1), field(2, 0),
                                         field(1, 2), field(0, 4), field(2, 3),
                                         field(4, 4));
        final Solution s2 = new Solution(field(4, 4), field(3, 2), field(4, 0),
                                         field(2, 1), field(0, 0), field(1, 2),
                                         field(2, 0), field(0, 1), field(1, 3),
                                         field(3, 4), field(4, 2), field(3, 0),
                                         field(1, 1), field(0, 3), field(2, 4),
                                         field(4, 3), field(2, 2), field(4, 1),
                                         field(3, 3), field(1, 4), field(0, 2),
                                         field(1, 0), field(3, 1), field(2, 3),
                                         field(0, 4));
        assertEquals(1728, solutions.size());
        assertTrue(solutions.contains(s1));
        assertTrue(solutions.contains(s2));
    }

    @Test
    public void testTorusTopology2() {
        final Topology topology = new TorusTopology(2, 1);
        final KnightProblem problem = new KnightProblem(topology);
        final List<Solution> solutions = problem.solve();
        assertEquals(2, solutions.size());
        assertEquals(Set.of(new Solution(field(0, 0), field(1, 0)),
                            new Solution(field(1, 0), field(0, 0))),
                     new HashSet<>(solutions));
    }

    @Test
    public void testTorusTopology2by2() {
        final Topology topology = new TorusTopology(2, 2);
        final KnightProblem problem = new KnightProblem(topology);
        final List<Solution> solutions = problem.solve();
        for(Solution s : solutions){
            System.out.println(s.toString());
        }
        assertEquals(8, solutions.size());
        assertEquals(Set.of(new Solution(field(0, 0), field(0, 1), field(1, 1), field(1, 0)),
                            new Solution(field(0, 0), field(1, 0), field(1, 1), field(0, 1)),
                            new Solution(field(0, 1), field(0, 0), field(1, 0), field(1, 1)),
                            new Solution(field(0, 1), field(1, 1), field(1, 0), field(0, 0)),
                            new Solution(field(1, 0), field(1, 1), field(0, 1), field(0, 0)),
                            new Solution(field(1, 0), field(0, 0), field(0, 1), field(1, 1)),
                            new Solution(field(1, 1), field(1, 0), field(0, 0), field(0, 1)),
                            new Solution(field(1, 1), field(0, 1), field(0, 0), field(1, 0))),
                     new HashSet<>(solutions));
    }

    @Test
    public void testMirroredTorusTopology2by2() {
        final Topology topology = new MirroredTorusTopology(2, 3);
        final KnightProblem problem = new KnightProblem(topology);
        final List<Solution> solutions = problem.solve();
        assertEquals(16, solutions.size());
        assertEquals(Set.of(new Solution(field(0, 0), field(0, 1), field(0, 2), field(1, 0), field(1, 1), field(1, 2)),
                            new Solution(field(0, 0), field(1, 2), field(1, 1), field(1, 0), field(0, 2), field(0, 1)),
                            new Solution(field(0, 0), field(1, 2), field(1, 1), field(0, 1), field(0, 2), field(1, 0)),
                            new Solution(field(0, 1), field(0, 2), field(1, 0), field(1, 1), field(1, 2), field(0, 0)),
                            new Solution(field(0, 1), field(0, 0), field(1, 2), field(1, 1), field(1, 0), field(0, 2)),
                            new Solution(field(0, 2), field(0, 1), field(0, 0), field(1, 2), field(1, 1), field(1, 0)),
                            new Solution(field(0, 2), field(1, 0), field(1, 1), field(1, 2), field(0, 0), field(0, 1)),
                            new Solution(field(0, 2), field(1, 0), field(1, 1), field(0, 1), field(0, 0), field(1, 2)),
                            new Solution(field(1, 0), field(1, 1), field(1, 2), field(0, 0), field(0, 1), field(0, 2)),
                            new Solution(field(1, 0), field(0, 2), field(0, 1), field(0, 0), field(1, 2), field(1, 1)),
                            new Solution(field(1, 0), field(0, 2), field(0, 1), field(1, 1), field(1, 2), field(0, 0)),
                            new Solution(field(1, 1), field(1, 2), field(0, 0), field(0, 1), field(0, 2), field(1, 0)),
                            new Solution(field(1, 1), field(1, 0), field(0, 2), field(0, 1), field(0, 0), field(1, 2)),
                            new Solution(field(1, 2), field(1, 1), field(1, 0), field(0, 2), field(0, 1), field(0, 0)),
                            new Solution(field(1, 2), field(0, 0), field(0, 1), field(0, 2), field(1, 0), field(1, 1)),
                            new Solution(field(1, 2), field(0, 0), field(0, 1), field(1, 1), field(1, 0), field(0, 2))),
                     new HashSet<>(solutions));
    }

    static Field field(int row, int column) {
        return new Field(row, column);
    }
}

