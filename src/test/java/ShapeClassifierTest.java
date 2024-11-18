
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShapeClassifierTest {

    private ShapeClassifier shapeClassifier;

    @BeforeEach
    void setUp() {
        shapeClassifier = new ShapeClassifier();
    }

    @Test
    void testLineSmallEven() {
        assertEquals("Yes", shapeClassifier.evaluateGuess("Line,Small,Yes,4"));
    }

    @Test
    void testRectangleLargeEven() {
        assertEquals("Yes", shapeClassifier.evaluateGuess("Rectangle,Large,Yes,50,50,50,50"));
    }

    @Test
    void testCircleSmallOdd() {
        assertEquals("No: Wrong Even/Odd", shapeClassifier.evaluateGuess("Circle,Small,Yes,3,3"));
    }

    @Test
    void testEllipseSmallEven() {
        assertEquals("Yes", shapeClassifier.evaluateGuess("Ellipse,Small,Yes,3,4"));
    }

    @Test
    void testInvalidShape() {
        assertEquals("No: Suggestion=Line", shapeClassifier.evaluateGuess("Invalid,Small,Yes,10"));
    }

    @Test
    void testTriangleEquilateral() {
        assertEquals("Yes", shapeClassifier.evaluateGuess("Equilateral,Small,Yes,3,3,3"));
    }

    @Test
    void testTriangleNotATriangle() {
        assertEquals("No: Suggestion=Not A Triangle", shapeClassifier.evaluateGuess("Scalene,Large,No,2,2,5"));
    }

    @Test
    void testSquareLargeEven() {
        assertEquals("Yes", shapeClassifier.evaluateGuess("Square,Large,Yes,4,4,4,4"));
    }

    @Test
    void testBadGuessLimitExceeded() {
        shapeClassifier.evaluateGuess("Line,Small,Yes,10");
        shapeClassifier.evaluateGuess("Circle,Small,Yes,5,5");
        Exception exception = assertThrows(Exception.class, () -> {
            shapeClassifier.evaluateGuess("Rectangle,Large,Yes,5,5,5,5");
        });
        assertTrue(exception.getMessage().contains("Bad guess limit Exceeded"));
    }

    @Test
    void testRectangleLargeOdd() {
        assertEquals("No: Wrong Even/Odd", shapeClassifier.evaluateGuess("Rectangle,Large,No,3,5,3,5"));
    }

    @Test
    void testBoundarySmallSize() {
        assertEquals("Yes", shapeClassifier.evaluateGuess("Line,Small,Yes,9"));
    }

    @Test
    void testBoundaryLargeSize() {
        assertEquals("Yes", shapeClassifier.evaluateGuess("Rectangle,Large,Yes,50,50,50,50"));
    }

    @Test
    void testBoundaryInvalidSize() {
        assertEquals("No: Wrong Size", shapeClassifier.evaluateGuess("Circle,Small,No,10,10"));
    }
}
