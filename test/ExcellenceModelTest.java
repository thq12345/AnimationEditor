import static org.junit.Assert.assertEquals;

import cs3500.animator.model.ExcellenceModel;
import cs3500.animator.model.InstantState;
import cs3500.animator.model.Shape;
import org.junit.Test;

/**
 * Testing class for model.
 */
public class ExcellenceModelTest {

  @Test
  public void testAddShape1() {
    ExcellenceModel givenModel = new ExcellenceModel();
    givenModel.addShape("abc", "Rectangle", 10, 10,
        100, 100, 100, 0, 0, 0);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   ", givenModel.getLog());
  }

  @Test
  public void testAddShape2() {
    ExcellenceModel givenModel = new ExcellenceModel();
    Shape givenshape2 = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    givenModel.addShape(givenshape2);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   ", givenModel.getLog());
  }


  @Test
  public void testSameThingInModel() {
    ExcellenceModel givenModel = new ExcellenceModel();
    givenModel.addShape("abc", "Rectangle", 10, 10,
        100, 100, 100, 0, 0,0);

    givenModel.changePosn("abc", 1, 10, 100, 100);
    givenModel.changePosn("abc", 10, 20, 300, 300);
    givenModel.changeColor("abc", 5, 15, 100, 100, 100);
    givenModel.changePosn("abc", 20, 30, 400, 300);
    givenModel.changeSize("abc", 4, 8, 444, 444);

    assertEquals(
        "Shape abc Rectangle\n"
            + "Motion abc 1 0 0 10 10 100 100 100 0   4 33 33 10 10 100 100 100 0\n"
            + "Motion abc 4 33 33 10 10 100 100 100 0   5 44 44 119 119 100 100 100 0\n"
            + "Motion abc 5 44 44 119 119 100 100 100 0   8 78 78 444 444 100 100 100 0\n"
            + "Motion abc 8 78 78 444 444 100 100 100 0   10 100 100 444 444 100 100 100 0\n"
            + "Motion abc 10 100 100 444 444 100 100 100 0   15 200 200 444 444 100 100 100 0\n"
            + "Motion abc 15 200 200 444 444 100 100 100 0   20 300 300 444 444 100 100 100 0\n"
            + "Motion abc 20 300 300 444 444 100 100 100 0   30 400 300 444 444 100 100 100 0",
        givenModel.getLog());
  }


  @Test
  public void justTestGapInOrder() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);

    m.changePosn("abc", 18, 30, 40, 20);
    m.changePosn("abc", 5, 10, 50, 50);
    m.changePosn("abc", 10, 18, 20, 110);
    m.changePosn("abc", 1, 5, 20, 20);

    assertEquals("Shape abc Rectangle\n"
            + "Motion abc 1 0 0 10 10 100 100 100 0   5 20 20 10 10 100 100 100 0\n"
            + "Motion abc 5 20 20 10 10 100 100 100 0   10 50 50 10 10 100 100 100 0\n"
            + "Motion abc 10 50 50 10 10 100 100 100 0   18 20 110 10 10 100 100 100 0\n"
            + "Motion abc 18 20 110 10 10 100 100 100 0   30 40 20 10 10 100 100 100 0",
        m.getLog());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingSameMoves() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 0, 10, 40, 20);
    m.changePosn("abc", 5, 15, 40, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingSameSizeMove() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 0, 10, 40, 20);
    m.changeSize("abc", 5, 15, 40, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingSameSizeColor() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeColor("abc", 0, 10, 40, 20, 20);
    m.changeColor("abc", 5, 15, 40, 20, 50);
  }

  @Test
  public void testAddingToMiddle() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 10, 15, 40, 20);
    m.changePosn("abc", 1, 5, 40, 140);
    m.changePosn("abc", 5, 10, 40, 90);

    assertEquals("Shape abc Rectangle\n"
            + "Motion abc 1 0 0 10 10 100 100 100 0   5 40 140 10 10 100 100 100 0\n"
            + "Motion abc 5 40 140 10 10 100 100 100 0   10 40 90 10 10 100 100 100 0\n"
            + "Motion abc 10 40 90 10 10 100 100 100 0   15 40 20 10 10 100 100 100 0",
        m.getLog());

  }

  @Test
  public void testReverseAdding() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 1, 5, 40, 90);

    m.changePosn("abc", 10, 15, 40, 20);
    m.changePosn("abc", 5, 10, 40, 140);

    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   5 40 90 10 10 100 100 100 0\n"
        + "Motion abc 5 40 90 10 10 100 100 100 0   10 40 140 10 10 100 100 100 0\n"
        + "Motion abc 10 40 140 10 10 100 100 100 0   15 40 20 10 10 100 100 100 0", m.getLog());
  }

  @Test
  public void testOneShapeOverlap() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 10, 15, 40, 20);
    m.changePosn("abc", 1, 5, 40, 140);
    m.changePosn("abc", 5, 10, 40, 90);
    Shape def = new Shape("def", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(def);
    m.changePosn("def", 13, 15, 40, 20);
    m.changePosn("def", 1, 7, 40, 140);
    m.changeColor("def", 1, 11, 20, 140, 95);
    m.changePosn("def", 7, 13, 40, 90);

    assertEquals("Shape abc Rectangle\n"
            + "Motion abc 1 0 0 10 10 100 100 100 0   5 40 140 10 10 100 100 100 0\n"
            + "Motion abc 5 40 140 10 10 100 100 100 0   10 40 90 10 10 100 100 100 0\n"
            + "Motion abc 10 40 90 10 10 100 100 100 0   15 40 20 10 10 100 100 100 0\n"
            + "Shape def Rectangle\n"
            + "Motion def 1 0 0 10 10 100 100 100 0   7 40 140 10 10 52 124 97 0\n"
            + "Motion def 7 40 140 10 10 52 124 97 0   11 40 67 10 10 20 140 95 0\n"
            + "Motion def 11 40 67 10 10 20 140 95 0   13 40 90 10 10 20 140 95 0\n"
            + "Motion def 13 40 90 10 10 20 140 95 0   15 40 20 10 10 20 140 95 0",
        m.getLog());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoGaps() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 10, 15, 40, 20);
    m.changePosn("abc", 1, 5, 40, 90);
    String log = m.getLog();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoValidShape() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("def", 1, 15, 40, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoRepeatOverlapMove() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 1, 15, 40, 20);
    m.changePosn("abc", 4, 16, 40, 90);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoRepeatOverlapColor() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeColor("abc", 1, 15, 40, 20, 20);
    m.changeColor("abc", 4, 16, 40, 90, 11);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNoRepeatOverlapSize() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 15, 40, 20);
    m.changeSize("abc", 4, 16, 40, 90);
  }

  // ---------------------------------------------------------------------

  @Test
  public void testRegularCase() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 5, 40, 20);
    m.changeSize("abc", 5, 10, 40, 90);
    m.changeSize("abc", 10, 15, 60, 40);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   5 0 0 40 20 100 100 100 0\n"
        + "Motion abc 5 0 0 40 20 100 100 100 0   10 0 0 40 90 100 100 100 0\n"
        + "Motion abc 10 0 0 40 90 100 100 100 0   15 0 0 60 40 100 100 100 0", m.getLog());
  }

  @Test
  public void testPerfectOverlap() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 5, 40, 20);
    m.changePosn("abc", 1, 5, 100, 100);
    m.changeSize("abc", 5, 10, 40, 90);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   5 100 100 40 20 100 100 100 0\n"
        + "Motion abc 5 100 100 40 20 100 100 100 0   10 100 100 40 90 100 100 100 0", m.getLog());
  }

  @Test
  public void testPerfectOverlap2() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 5, 40, 20);
    m.changePosn("abc", 1, 5, 100, 100);
    assertEquals("Shape abc Rectangle\n"
            + "Motion abc 1 0 0 10 10 100 100 100 0   5 100 100 40 20 100 100 100 0"
        , m.getLog());
  }

  @Test
  public void testPosnCase() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 1, 5, 40, 20);
    m.changePosn("abc", 5, 10, 40, 90);
    m.changePosn("abc", 10, 15, 60, 40);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   5 40 20 10 10 100 100 100 0\n"
        + "Motion abc 5 40 20 10 10 100 100 100 0   10 40 90 10 10 100 100 100 0\n"
        + "Motion abc 10 40 90 10 10 100 100 100 0   15 60 40 10 10 100 100 100 0", m.getLog());
  }

  @Test
  public void testRGBCase() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeColor("abc", 1, 5, 100, 20, 120);
    m.changeColor("abc", 5, 10, 30, 90, 75);
    m.changeColor("abc", 10, 15, 60, 40, 60);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   5 0 0 10 10 100 20 120 0\n"
        + "Motion abc 5 0 0 10 10 100 20 120 0   10 0 0 10 10 30 90 75 0\n"
        + "Motion abc 10 0 0 10 10 30 90 75 0   15 0 0 10 10 60 40 60 0", m.getLog());
  }

  @Test
  public void testOutOfOrdercase() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 10, 15, 60, 40);
    m.changeSize("abc", 1, 5, 40, 20);
    m.changeSize("abc", 5, 10, 40, 90);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   5 0 0 40 20 100 100 100 0\n"
        + "Motion abc 5 0 0 40 20 100 100 100 0   10 0 0 40 90 100 100 100 0\n"
        + "Motion abc 10 0 0 40 90 100 100 100 0   15 0 0 60 40 100 100 100 0", m.getLog());
  }

  @Test
  public void testOverLapSize() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 1, 5, 40, 20);
    m.changeSize("abc", 3, 8, 40, 90);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   3 20 10 10 10 100 100 100 0\n"
        + "Motion abc 3 20 10 10 10 100 100 100 0   5 40 20 22 42 100 100 100 0\n"
        + "Motion abc 5 40 20 22 42 100 100 100 0   8 40 20 40 90 100 100 100 0", m.getLog());
  }

  @Test
  public void testOverLapColor() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 1, 5, 40, 20);
    m.changeColor("abc", 3, 8, 50, 10, 200);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   3 20 10 10 10 100 100 100 0\n"
        + "Motion abc 3 20 10 10 10 100 100 100 0   5 40 20 10 10 80 64 140 0\n"
        + "Motion abc 5 40 20 10 10 80 64 140 0   8 40 20 10 10 50 10 200 0", m.getLog());
  }

  @Test
  public void testOverLapPosn() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 5, 50, 70);
    m.changePosn("abc", 3, 8, 40, 90);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   3 0 0 30 40 100 100 100 0\n"
        + "Motion abc 3 0 0 30 40 100 100 100 0   5 16 36 50 70 100 100 100 0\n"
        + "Motion abc 5 16 36 50 70 100 100 100 0   8 40 90 50 70 100 100 100 0", m.getLog());
  }

  @Test
  public void testOverlapSizeOutOfOrder() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 3, 8, 40, 90);
    m.changeSize("abc", 1, 5, 50, 30);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   3 0 0 30 20 100 100 100 0\n"
        + "Motion abc 3 0 0 30 20 100 100 100 0   5 16 36 50 30 100 100 100 0\n"
        + "Motion abc 5 16 36 50 30 100 100 100 0   8 40 90 50 30 100 100 100 0", m.getLog());
  }

  @Test
  public void testOverLapColorOutOfOrder() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeColor("abc", 3, 8, 50, 10, 200);
    m.changePosn("abc", 1, 5, 40, 20);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   3 20 10 10 10 100 100 100 0\n"
        + "Motion abc 3 20 10 10 10 100 100 100 0   5 40 20 10 10 80 64 140 0\n"
        + "Motion abc 5 40 20 10 10 80 64 140 0   8 40 20 10 10 50 10 200 0", m.getLog());
  }

  @Test
  public void testOverLapSizeOutOfOrder() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 3, 8, 40, 90);
    m.changePosn("abc", 1, 5, 40, 20);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   3 20 10 10 10 100 100 100 0\n"
        + "Motion abc 3 20 10 10 10 100 100 100 0   5 40 20 22 42 100 100 100 0\n"
        + "Motion abc 5 40 20 22 42 100 100 100 0   8 40 20 40 90 100 100 100 0", m.getLog());
  }

  @Test
  public void testOutrightOverlapSizeOutOfOrder() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 1, 8, 40, 90);
    m.changeSize("abc", 2, 6, 50, 30);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   2 6 13 10 10 100 100 100 0\n"
        + "Motion abc 2 6 13 10 10 100 100 100 0   6 29 64 50 30 100 100 100 0\n"
        + "Motion abc 6 29 64 50 30 100 100 100 0   8 40 90 50 30 100 100 100 0", m.getLog());
  }

  @Test
  public void testOutrightOverLapColorOutOfOrder() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeColor("abc", 1, 8, 50, 10, 200);
    m.changePosn("abc", 2, 6, 40, 20);
    assertEquals("Shape abc Rectangle\n"
            + "Motion abc 1 0 0 10 10 100 100 100 0   2 0 0 10 10 93 87 114 0\n"
            + "Motion abc 2 0 0 10 10 93 87 114 0   6 40 20 10 10 64 36 171 0\n"
            + "Motion abc 6 40 20 10 10 64 36 171 0   8 40 20 10 10 50 10 200 0"
        , m.getLog());
  }


  @Test
  public void testPerfectOverLapSizeOutOfOrder() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 9, 40, 80);
    m.changePosn("abc", 2, 9, 40, 80);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   2 0 0 14 19 100 100 100 0\n"
        + "Motion abc 2 0 0 14 19 100 100 100 0   9 40 80 40 80 100 100 100 0", m.getLog());
  }

  @Test
  public void testDoNothing() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 8, 50, 80);
    m.doNothing("abc", 8, 12);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   8 0 0 50 80 100 100 100 0\n"
        + "Motion abc 8 0 0 50 80 100 100 100 0   12 0 0 50 80 100 100 100 0", m.getLog());
  }

  @Test
  public void testDoNothingOutOfOrder() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 8, 40, 80);
    m.doNothing("abc", 8, 12);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   8 0 0 40 80 100 100 100 0\n"
        + "Motion abc 8 0 0 40 80 100 100 100 0   12 0 0 40 80 100 100 100 0", m.getLog());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoNothingSizeOverlap() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);

    m.changeSize("abc", 1, 10, 40, 80);
    m.doNothing("abc", 8, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoNothingColorOverlap() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);

    m.changeColor("abc", 1, 10, 40, 80, 110);
    m.doNothing("abc", 8, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoNothingPosnOverlap() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);

    m.changePosn("abc", 1, 10, 40, 80);
    m.doNothing("abc", 8, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlapSize() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changeSize("abc", 7, 15, 40, 80);
    assertEquals("", m.getLog());
  }

  @Test
  public void testRemovalSize() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   7 0 0 30 57 100 100 100 0\n"
        + "Motion abc 7 0 0 30 57 100 100 100 0   10 15 30 40 80 100 100 100 0\n"
        + "Motion abc 10 15 30 40 80 100 100 100 0   15 40 80 40 80 100 100 100 0", m.getLog());
    m.removeMotion("abc", "size", 1, 10);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   7 0 0 10 10 100 100 100 0\n"
        + "Motion abc 7 0 0 10 10 100 100 100 0   10 15 30 10 10 100 100 100 0\n"
        + "Motion abc 10 15 30 10 10 100 100 100 0   15 40 80 10 10 100 100 100 0", m.getLog());
  }

  @Test
  public void testRemovalPosn() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   7 0 0 30 57 100 100 100 0\n"
        + "Motion abc 7 0 0 30 57 100 100 100 0   10 15 30 40 80 100 100 100 0\n"
        + "Motion abc 10 15 30 40 80 100 100 100 0   15 40 80 40 80 100 100 100 0", m.getLog());
    m.removeMotion("abc", "move", 7, 15);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   7 0 0 30 57 100 100 100 0\n"
        + "Motion abc 7 0 0 30 57 100 100 100 0   10 0 0 40 80 100 100 100 0\n"
        + "Motion abc 10 0 0 40 80 100 100 100 0   15 0 0 40 80 100 100 100 0", m.getLog());
  }

  @Test
  public void testRemovalColor() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changeColor("abc", 10, 15, 40, 80, 10);
    m.changePosn("abc", 10, 15, 40, 80);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   10 0 0 40 80 100 100 100 0\n"
        + "Motion abc 10 0 0 40 80 100 100 100 0   15 40 80 40 80 40 80 10 0", m.getLog());
    m.removeMotion("abc", "color", 10, 15);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   10 0 0 40 80 100 100 100 0\n"
        + "Motion abc 10 0 0 40 80 100 100 100 0   15 40 80 40 80 100 100 100 0", m.getLog());
  }

  @Test
  public void testNoChangeFollowingInvalidCoordinates() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changeColor("abc", 10, 15, 40, 80, 10);
    m.changePosn("abc", 10, 15, 40, 80);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   10 0 0 40 80 100 100 100 0\n"
        + "Motion abc 10 0 0 40 80 100 100 100 0   15 40 80 40 80 40 80 10 0", m.getLog());
    m.removeMotion("abc", "color", 6, 13);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   10 0 0 40 80 100 100 100 0\n"
        + "Motion abc 10 0 0 40 80 100 100 100 0   15 40 80 40 80 40 80 10 0", m.getLog());
  }

  @Test
  public void testNoChangeFollowingInvalidType() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changeColor("abc", 10, 15, 40, 80, 10);
    m.changePosn("abc", 10, 15, 40, 80);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   10 0 0 40 80 100 100 100 0\n"
        + "Motion abc 10 0 0 40 80 100 100 100 0   15 40 80 40 80 40 80 10 0", m.getLog());
    m.removeMotion("abc", "size", 10, 15);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   10 0 0 40 80 100 100 100 0\n"
        + "Motion abc 10 0 0 40 80 100 100 100 0   15 40 80 40 80 40 80 10 0", m.getLog());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyAfterRemoval() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   7 0 0 30 57 100 100 100 0\n"
        + "Motion abc 7 0 0 30 57 100 100 100 0   10 15 30 40 80 100 100 100 0\n"
        + "Motion abc 10 15 30 40 80 100 100 100 0   15 40 80 40 80 100 100 100 0", m.getLog());
    m.removeMotion("abc", "move", 7, 15);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   7 0 0 30 57 100 100 100 0\n"
        + "Motion abc 7 0 0 30 57 100 100 100 0   10 0 0 40 80 100 100 100 0\n"
        + "Motion abc 10 0 0 40 80 100 100 100 0   15 0 0 40 80 100 100 100 0", m.getLog());
    m.changeSize("abc", 10, 15, 30, 80);
  }

  @Test
  public void testRemoveAShape() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    Shape def = new Shape("def", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(def);
    m.changeSize("def", 1, 10, 40, 80);
    m.changePosn("def", 7, 15, 40, 80);
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   7 0 0 30 57 100 100 100 0\n"
        + "Motion abc 7 0 0 30 57 100 100 100 0   10 15 30 40 80 100 100 100 0\n"
        + "Motion abc 10 15 30 40 80 100 100 100 0   15 40 80 40 80 100 100 100 0\n"
        + "Shape def Rectangle\n"
        + "Motion def 1 0 0 10 10 100 100 100 0   7 0 0 30 57 100 100 100 0\n"
        + "Motion def 7 0 0 30 57 100 100 100 0   10 15 30 40 80 100 100 100 0\n"
        + "Motion def 10 15 30 40 80 100 100 100 0   15 40 80 40 80 100 100 100 0", m.getLog());
    m.removeShape("def");
    assertEquals("Shape abc Rectangle\n"
        + "Motion abc 1 0 0 10 10 100 100 100 0   7 0 0 30 57 100 100 100 0\n"
        + "Motion abc 7 0 0 30 57 100 100 100 0   10 15 30 40 80 100 100 100 0\n"
        + "Motion abc 10 15 30 40 80 100 100 100 0   15 40 80 40 80 100 100 100 0", m.getLog());
  }

  @Test
  public void testShapeProperties() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals("Rectangle\n"
            + "Motion abc 1 0 0 10 10 100 100 100 0   7 0 0 30 57 100 100 100 0\n"
            + "Motion abc 7 0 0 30 57 100 100 100 0   10 15 30 40 80 100 100 100 0\n"
            + "Motion abc 10 15 30 40 80 100 100 100 0   15 40 80 40 80 100 100 100 0",
        m.getShapeProperties("abc"));
  }

  @Test
  public void testNoShapeProperties() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals(null, m.getShapeProperties("def"));
  }

  @Test
  public void testGetMove() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals("abc 0 0 40 80",
        m.getMotionProperties("abc", "move", 7, 15));
  }

  @Test
  public void testGetSize() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals("abc 10 10 40 80",
        m.getMotionProperties("abc", "size", 1, 10));
  }

  @Test
  public void testGetColor() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals("abc 10 10 40 80",
        m.getMotionProperties("abc", "size", 1, 10));
  }

  @Test
  public void testGetWrongPosition() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals(null,
        m.getMotionProperties("abc", "size", 7, 15));
  }

  @Test
  public void testGetNonexistentShape() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    assertEquals(null,
        m.getMotionProperties("def", "size", 7, 15));
  }

  @Test
  public void testGetKeyFrame() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    m.convertMotionToKeyFrame();
    assertEquals("1 0 0 10 10 100 100 100 0\n"
            + "7 0 0 30 57 100 100 100 0\n"
            + "10 15 30 40 80 100 100 100 0\n"
            + "15 40 80 40 80 100 100 100 0\n",
        m.getShapes().get(0).toStringKeyFrames());
  }

  @Test
  public void testKeyFrameToInstantLogTranslation() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.addKeyframe("abc", "rectangle", 1, 0, 0, 10, 10, 100, 100, 100,0);
    m.addKeyframe("abc", "rectangle", 7, 0, 0, 30, 57, 100, 100, 100,0);
    m.addKeyframe("abc", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);
    m.addKeyframe("abc", "rectangle", 15, 40, 80, 40, 80, 100, 100, 100,0);
    m.keyFrameToInstantStateLog();
    assertEquals("[1 0 0 10 10 100 100 100 0, 2 0 0 13 18 100 100 100 0, "
        + "3 0 0 17 26 100 100 100 0, 4 0 0 20 34 100 100 100 0, 5 0 0 23 41 100 100 100 0, "
        + "6 0 0 27 49 100 100 100 0, 7 0 0 30 57 100 100 100 0, 8 5 10 33 65 100 100 100 0, "
        + "9 10 20 37 72 100 100 100 0, 10 15 30 40 80 100 100 100 0, 11 20 40 40 80"
        + " 100 100 100 0, "
        + "12 25 50 40 80 100 100 100 0, 13 30 60 40 80 100 100 100 0, 14 35 70 40 80 100 100 100 "
        + "0, 15 40 80 40 80 100 100 100 0]", m.getShapes().get(0).getStateBySecond().toString());
  }

  @Test
  public void testKeyFrameToInstantLogTranslation2() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.addKeyframe("abc", "rectangle", 1, 0, 0, 10, 10, 100, 100, 100,0);
    m.addKeyframe("abc", "rectangle", 7, 0, 0, 30, 57, 200, 200, 200,0);
    m.addKeyframe("abc", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);

    m.keyFrameToInstantStateLog();
    m.convertMotionToKeyFrame();
    assertEquals("1 0 0 10 10 100 100 100 0\n"
        + "7 0 0 30 57 200 200 200 0\n"
        + "10 15 30 40 80 100 100 100 0\n", m.getShapes().get(0).toStringKeyFrames());
    assertEquals("[1 0 0 10 10 100 100 100 0, 2 0 0 13 18 117 117 117 0, "
        + "3 0 0 17 26 133 133 133 0, 4 0 0 20 34 150 150 150 0, 5 0 0 23 41 167 167 167 0, "
        + "6 0 0 27 49 183 183 183 0, 7 0 0 30 57 200 200 200 0, 8 5 10 33 65 167 167 167 0, "
        + "9 10 20 37 72 133 133 133 0, 10 15 30 40 80 100 100 100 0]",
        m.getShapes().get(0).getStateBySecond().toString());

    m.addKeyframe("abc", "rectangle", 5, 40, 80, 40, 80, 50, 50, 50,0);
    m.keyFrameToInstantStateLog();
    m.convertMotionToKeyFrame();

    assertEquals("1 0 0 10 10 100 100 100 0\n"
        + "5 40 80 40 80 50 50 50 0\n"
        + "7 0 0 29 57 200 200 200 0\n"
        + "10 15 30 40 80 100 100 100 0\n", m.getShapes().get(0).toStringKeyFrames());
    assertEquals("[1 0 0 10 10 100 100 100 0, 2 10 20 18 28 88 88 88 0, "
            + "3 20 40 25 45 75 75 75 0, 4 30 60 33 63 63 63 63 0, 5 40 80 40 80 50 50 50 0, "
            + "6 20 40 35 69 125 125 125 0, 7 0 0 29 57 200 200 200 0, 8 5 10 33 65 167 167 167 "
            + "0, 9 10 20 36 72 133 133 133 0, 10 15 30 40 80 100 100 100 0]",
        m.getShapes().get(0).getStateBySecond().toString());
  }


  @Test
  public void testKeyFrameToInstantLogTranslation3() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("def", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.addKeyframe("def", "rectangle", 1, 0, 0, 10, 10, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", 7, 0, 0, 10, 17, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", 15, 40, 80, 40, 80, 50, 50, 50,0);
    m.addKeyframe("def", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);

    m.keyFrameToInstantStateLog();
    assertEquals("[1 0 0 10 10 177 188 166 0, 2 0 0 10 11 177 188 166 0, "
        + "3 0 0 10 12 177 188 166 0, 4 0 0 10 13 177 188 166 0, 5 0 0 10 15 177 188 166 0, "
        + "6 0 0 10 16 177 188 166 0, 7 0 0 10 17 177 188 166 0, 8 5 10 20 38 151 159 144 0, "
        + "9 10 20 30 59 126 129 122 0, 10 15 30 40 80 100 100 100 0, 11 20 40 40 80 90 90 90 0"
        + ", 12 25 50 40 80 80 80 80 0, 13 30 60 40 80 70 70 70 0, 14 35 70 40 80 60 60 60 0, "
        + "15 40 80 40 80 50 50 50 0]", m.getShapes().get(0).stateBySecond.toString());
    assertEquals("1 0 0 10 10 177 188 166 0\n"
        + "7 0 0 10 17 177 188 166 0\n"
        + "10 15 30 40 80 100 100 100 0\n"
        + "15 40 80 40 80 50 50 50 0\n", m.getShapes().get(0).toStringKeyFrames());
  }

  @Test
  public void RegularToKeyFrameTranslate() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 7, 15, 40, 80);
    m.changeSize("abc", 1, 10, 40, 80);
    m.convertMotionToKeyFrame();
    assertEquals("1 0 0 10 10 100 100 100 0\n"
            + "7 0 0 30 57 100 100 100 0\n"
            + "10 15 30 40 80 100 100 100 0\n"
            + "15 40 80 40 80 100 100 100 0\n",
        m.getShapes().get(0).toStringKeyFrames());
  }

  @Test
  public void PerfectKeyFrameTranslate() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 7, 15, 40, 80);
    m.changeSize("abc", 1, 7, 40, 80);
    m.convertMotionToKeyFrame();
    assertEquals("1 0 0 10 10 100 100 100 0\n"
            + "7 0 0 40 80 100 100 100 0\n"
            + "15 40 80 40 80 100 100 100 0\n",
        m.getShapes().get(0).toStringKeyFrames());
  }


  @Test
  public void backwardCompatibleCheck() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 7, 15, 40, 80);
    m.convertMotionToKeyFrame();
    m.keyFrameToInstantStateLog();
    assertEquals("[1 0 0 10 10 100 100 100 0, 2 0 0 13 18 100 100 100 0, "
            + "3 0 0 16 25 100 100 100 0, 4 0 0 20 33 100 100 100 0, 5 0 0 23 41 100 100 100 0, "
            + "6 0 0 26 48 100 100 100 0, 7 0 0 29 56 100 100 100 0, 8 5 10 33 64 100 100 100 0, "
            + "9 10 20 36 71 100 100 100 0, 10 15 30 40 79 100 100 100 0, 11 20 40 40 79 "
            + "100 100 100 0, 12 25 50 40 79 100 100 100 0, 13 30 60 40 80 100 100 100 "
            + "0, 14 35 70 40 80 100 100 100 0, 15 40 80 40 80 100 100 100 0]"
        , m.getShapes().get(0).stateBySecond.toString());
  }

  @Test
  public void backwardCompatibleCheck2() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.addKeyframe("abc", "rectangle", 1, 0, 0, 10, 10, 100, 100, 100,0);
    m.addKeyframe("abc", "rectangle", 7, 0, 0, 30, 57, 100, 100, 100,0);
    m.addKeyframe("abc", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);
    m.addKeyframe("abc", "rectangle", 15, 40, 80, 40, 80, 100, 100, 100,0);
    m.keyFrameToInstantStateLog();
    m.convertMotionToKeyFrame();
    assertEquals("1 0 0 10 10 100 100 100 0\n"
        + "7 0 0 30 57 100 100 100 0\n"
        + "10 15 30 40 80 100 100 100 0\n"
        + "15 40 80 40 80 100 100 100 0\n", m.getShapes().get(0).toStringKeyFrames());
    assertEquals("[1 0 0 10 10 100 100 100 0, 2 0 0 13 18 100 100 100 0, "
            + "3 0 0 17 26 100 100 100 0, 4 0 0 20 34 100 100 100 0, 5 0 0 23 41 100 100 100 0, "
            + "6 0 0 27 49 100 100 100 0, 7 0 0 30 57 100 100 100 0, 8 5 10 33 65 100 100 100 0,"
            + " 9 10 20 37 72 100 100 100 0, 10 15 30 40 80 100 100 100 0, 11 20 40 40 80 100 10"
            + "0 100 0, 12 25 50 40 80 100 100 100 0, 13 30 60 40 80 100 100 100 0, 14 35 70 40"
            + " 80 100 100 100 0, 15 40 80 40 80 100 100 100 0]"
        , m.getShapes().get(0).stateBySecond.toString());
  }


  @Test
  public void backwardCompatibleCheck3() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 10, 40, 80);
    m.changePosn("abc", 10, 15, 40, 80);
    m.convertMotionToKeyFrame();

    m.keyFrameToInstantStateLog();
    assertEquals("1 0 0 10 10 100 100 100 0\n"
        + "10 0 0 40 80 100 100 100 0\n"
        + "15 40 80 40 80 100 100 100 0\n", m.getShapes().get(0).toStringKeyFrames());
  }


  @Test
  public void testKeyFrameToInstantLogSpecialCase1() {
    ExcellenceModel m = new ExcellenceModel();
    Shape def = new Shape("def", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(def);
    m.addKeyframe("def", "rectangle", 7, 0, 0, 10, 17, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", 15, 40, 80, 40, 80, 50, 50, 50,0);
    m.addKeyframe("def", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);

    m.keyFrameToInstantStateLog();
    assertEquals("[1 0 0 0 0 0 0 0 0, 2 0 0 0 0 0 0 0 0, 3 0 0 0 0 0 0 0 0, "
            + "4 0 0 0 0 0 0 0 0, 5 0 0 0 0 0 0 0 0, 6 0 0 0 0 0 0 0 0, "
            + "7 0 0 10 17 177 188 166 0, 8 5 10 20 38 151 159 144 0, 9 10 20"
            + " 30 59 126 129 122 0, 10 15 30 40 80 100 100 100 0, 11 20 40 40 "
            + "80 90 90 90 0, 12 25 50 40 80 80 80 80 0, 13 30 60 40 80 70 70 70"
            + " 0, 14 35 70 40 80 60 60 60 0, 15 40 80 40 80 50 50 50 0]",
        m.getShapes().get(0).stateBySecond.toString());
    assertEquals("7 0 0 10 17 177 188 166 0\n"
        + "10 15 30 40 80 100 100 100 0\n"
        + "15 40 80 40 80 50 50 50 0\n", m.getShapes().get(0).toStringKeyFrames());

    //add 1 later
    m.addKeyframe("def", "rectangle", 5, 0, 0, 10, 10, 100, 100, 100,0);
    m.keyFrameToInstantStateLog();
    m.convertMotionToKeyFrame();
    assertEquals("[1 0 0 0 0 0 0 0 0, 2 0 0 0 0 0 0 0 0, 3 0 0 0 0 0 0 0 0, "
            + "4 0 0 0 0 0 0 0 0, 5 0 0 10 10 100 100 100 0, 6 0 0 10 14 139 144 133 0, "
            + "7 0 0 10 17 177 188 166 0, 8 5 10 20 38 151 159 144 0, 9 10 20 30 59 126"
            + " 129 122 0, 10 15 30 40 80 100 100 100 0, 11 20 40 40 80 90 90 90 0, 12 2"
            + "5 50 40 80 80 80 80 0, 13 30 60 40 80 70 70 70 0, 14 35 70 40 80 60 60 60"
            + " 0, 15 40 80 40 80 50 50 50 0]"
        , m.getShapes().get(0).stateBySecond.toString());
    assertEquals("5 0 0 10 10 100 100 100 0\n"
        + "7 0 0 10 17 177 188 166 0\n"
        + "10 15 30 40 80 100 100 100 0\n"
        + "15 40 80 40 80 50 50 50 0\n", m.getShapes().get(0).toStringKeyFrames());
  }


  @Test(expected = IllegalArgumentException.class)
  public void addNegativeKeyFrame() {
    ExcellenceModel m = new ExcellenceModel();
    m.addKeyframe("def", "rectangle", -7, 0, 0, 10, 17, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", -15, 40, 80, 40, 80, 50, 50, 50,0);
    m.addKeyframe("def", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void NegativeKeyFrameToInstantState() {
    ExcellenceModel m = new ExcellenceModel();
    m.addShape("def", "rectangle", 10, 10, 10, 10, 10, 10, 10,0);
    m.addKeyframe("def", "rectangle", 1, 0, 0, 10, 17, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);
    m.addKeyframe("def", "rectangle", -7, 0, 0, 10, 10, 177, 188, 166,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ZeroKeyFrameToInstantState() {
    ExcellenceModel m = new ExcellenceModel();
    m.addShape("def", "rectangle", 10, 10, 10, 10, 10, 10, 10,0);
    m.addKeyframe("def", "rectangle", 1, 0, 0, 10, 17, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);
    m.addKeyframe("def", "rectangle", 0, 0, 0, 10, 10, 177, 188, 166,0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void randomEditTest() {
    ExcellenceModel m = new ExcellenceModel();
    m.addKeyframe("def", "rectangle", -1, 0, 0, 10, 17, 177, 188, 166,0);
  }


  @Test
  public void anotherRealisticTest() {
    ExcellenceModel m = new ExcellenceModel();
    m.addShape("sq", "rectangle", 30, 30, 0, 255, 0, 100, 100,0);

    m.changePosn("sq", 10, 20, 200, 200);

    m.getShapes().get(0).handleWithGap();

    m.convertMotionToKeyFrame();
    assertEquals("10 100 100 30 30 0 255 0 0\n"
        + "20 200 200 30 30 0 255 0 0\n", m.getShapes().get(0).toStringKeyFrames());
  }

  @Test
  public void testEditKeyFrame() {
    ExcellenceModel m = new ExcellenceModel();
    m.addShape("def", "rectangle", 10, 10, 10, 10, 10, 10, 10,0);
    m.addKeyframe("def", "rectangle", 1, 0, 0, 10, 17, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);
    assertEquals("1 0 0 10 17 177 188 166 0\n"
        + "10 15 30 40 80 100 100 100 0\n", m.getShapes().get(0).toStringKeyFrames());
    m.editKeyFrame("def", new InstantState(10, "def", 10,
        10, 10, 10, 10, 10, 10,0));
    assertEquals("1 0 0 10 17 177 188 166 0\n"
        + "10 10 10 10 10 10 10 10 0\n", m.getShapes().get(0).toStringKeyFrames());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyFrameInvalidTime() {
    ExcellenceModel m = new ExcellenceModel();
    m.addShape("def", "rectangle", 10, 10, 10, 10, 10, 10, 10,0);
    m.addKeyframe("def", "rectangle", 1, 0, 0, 10, 17, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);
    assertEquals("1 0 0 10 17 177 188 166 0\n"
        + "10 15 30 40 80 100 100 100 0\n", m.getShapes().get(0).toStringKeyFrames());
    m.editKeyFrame("def", new InstantState(100, "def", 10,
        10, 1000, 1000, 1000, 10, 10,0));
    assertEquals("1 0 0 10 17 177 188 166\n"
        + "10 15 30 40 80 100 100 100\n", m.getShapes().get(0).toStringKeyFrames());
  }


  @Test
  public void testDeleteKeyframe() {
    ExcellenceModel m = new ExcellenceModel();
    m.addShape("def", "rectangle", 10, 10, 10, 10, 10, 10, 10,0);
    m.addKeyframe("def", "rectangle", 1, 0, 0, 10, 17, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);
    assertEquals("1 0 0 10 17 177 188 166 0\n"
        + "10 15 30 40 80 100 100 100 0\n", m.getShapes().get(0).toStringKeyFrames());
    m.deleteKeyFrame("def", 10);
    assertEquals("1 0 0 10 17 177 188 166 0\n", m.getShapes().get(0).toStringKeyFrames());
  }


  @Test
  public void testDeleteKeyframeInvalid() {
    ExcellenceModel m = new ExcellenceModel();
    m.addShape("def", "rectangle", 10, 10, 10, 10, 10, 10, 10,0);
    m.addKeyframe("def", "rectangle", 1, 0, 0, 10, 17, 177, 188, 166,0);
    m.addKeyframe("def", "rectangle", 10, 15, 30, 40, 80, 100, 100, 100,0);
    assertEquals("1 0 0 10 17 177 188 166 0\n"
        + "10 15 30 40 80 100 100 100 0\n", m.getShapes().get(0).toStringKeyFrames());
    m.deleteKeyFrame("def", 100);
    assertEquals("1 0 0 10 17 177 188 166 0\n"
        + "10 15 30 40 80 100 100 100 0\n", m.getShapes().get(0).toStringKeyFrames());
  }

  @Test
  public void testGetShapeNameIndex() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10,0);
    Shape shape2 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10,0);
    m.addShape(shape1);
    m.addShape(shape2);

    assertEquals(1, m.getShapeNameIndex("abc"));
    assertEquals(-1, m.getShapeNameIndex("deas"));
  }

  @Test
  public void testgetMotionProperties() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10,0);
    Shape shape2 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10,0);
    m.addShape(shape1);
    m.addShape(shape2);
    m.changePosn("abc", 1, 10, 100, 100);
    m.changeSize("abc", 10, 20, 200, 200);
    m.changeColor("abc", 20, 30, 255, 255, 255);
    assertEquals("abc 10 10 100 100",
        m.getMotionProperties("abc", "move", 1, 10));
    assertEquals("abc 10 10 200 200",
        m.getMotionProperties("abc", "size", 10, 20));
    assertEquals(null,
        m.getMotionProperties("abc", "color", 10, 20));
    assertEquals("abc 10 10 10 255 255 255",
        m.getMotionProperties("abc", "color", 20, 30));
  }

  @Test
  public void testgetShapeProperties() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10,0);
    Shape shape2 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10,0);
    m.addShape(shape1);
    m.addShape(shape2);
    m.changePosn("abc", 1, 10, 100, 100);
    m.changeSize("abc", 10, 20, 200, 200);
    m.changeColor("abc", 20, 30, 255, 255, 255);
    assertEquals("rectangle\n"
            + "Motion abc 1 10 10 10 10 10 10 10 0   10 100 100 10 10 10 10 10 0\n"
            + "Motion abc 10 100 100 10 10 10 10 10 0   20 100 100 200 200 10 10 10 0\n"
            + "Motion abc 20 100 100 200 200 10 10 10 0   30 100 100 200 200 255 255 255 0",
        m.getShapeProperties("abc"));
    assertEquals(null, m.getShapeProperties("dsad"));
  }

  @Test
  public void testRemoveMotion() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10,0);
    Shape shape2 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10,0);
    m.addShape(shape1);
    m.addShape(shape2);
    m.changePosn("abc", 1, 10, 100, 100);
    m.changeSize("abc", 10, 20, 200, 200);
    m.changeColor("abc", 20, 30, 255, 255, 255);
    m.removeMotion("abc", "size", 10, 20);
    assertEquals("[1 10 10 10 10 10 10 10 0, 2 20 20 10 10 10 10 10 0, "
            + "3 30 30 10 10 10 10 10 0, 4 40 40 10 10 10 10 10 0, 5 50 50 10 10 10 10 10 0, "
            + "6 60 60 10 10 10 10 10 0, 7 70 70 10 10 10 10 10 0, 8 80 80 10 10 10 10 10 0,"
            + " 9 90 90 10 10 10 10 10 0, 10 100 100 10 10 10 10 10 0, 11 100 100 10 10 10 10 "
            + "10 0, 12 100 100 10 10 10 10 10 0, 13 100 100 10 10 10 10 10 0, 14 100 100 10 1"
            + "0 10 10 10 0, 15 100 100 10 10 10 10 10 0, 16 100 100 10 10 10 10 10 0, 17 100 "
            + "100 10 10 10 10 10 0, 18 100 100 10 10 10 10 10 0, 19 100 100 10 10 10 10 10 0, "
            + "20 100 100 10 10 10 10 10 0, 21 100 100 10 10 35 35 35 0, 22 100 100 10 10 59 59"
            + " 59 0, 23 100 100 10 10 84 84 84 0, 24 100 100 10 10 108 108 108 0, 25 100 100 1"
            + "0 10 133 133 133 0, 26 100 100 10 10 157 157 157 0, 27 100 100 10 10 182 182 182 "
            + "0, 28 100 100 10 10 206 206 206 0, 29 100 100 10 10 231 231 231 0, 30 100 100 10 "
            + "10 255 255 255 0]"
        , shape2.getStateBySecond().toString());
  }



}
