import static org.junit.Assert.assertEquals;

import cs3500.animator.model.ExcellenceModel;
import cs3500.animator.model.InstantState;
import cs3500.animator.model.Shape;
import org.junit.Test;

/**
 * Testing class for shape.
 */
public class ShapeTest {

  @Test
  public void checkOverlapTrue() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 5, 40, 20);
    m.changePosn("abc", 1, 5, 100, 100);
    m.changeSize("abc", 5, 10, 40, 90);
    assertEquals(false, m.getShapes().get(0).
        checkOverlap(11, 15, m.getShapes().get(0).getSizeDuration()));
  }

  @Test
  public void checkOverlapFalse() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changeSize("abc", 1, 5, 40, 20);
    m.changePosn("abc", 1, 5, 100, 100);
    m.changeSize("abc", 5, 10, 40, 90);
    assertEquals(true, m.getShapes().get(0).
        checkOverlap(6, 10, m.getShapes().get(0).getSizeDuration()));
  }


  @Test
  public void getType() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    assertEquals("Rectangle", abc.getType());
  }

  @Test
  public void changeMoving() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    abc.changeMoving(200, 200);
    assertEquals(200, abc.getXposn(), 0.01);
    assertEquals(200, abc.getYposn(), 0.01);
  }

  @Test
  public void changeColor() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    abc.changeColor(1, 1, 1);
    assertEquals(1, abc.getR(), 0.01);
    assertEquals(1, abc.getG(), 0.01);
    assertEquals(1, abc.getB(), 0.01);
  }

  @Test
  public void changeSize() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    abc.changeSize(200, 200);
    assertEquals(200, abc.getWidth(), 0.01);
    assertEquals(200, abc.getHeight(), 0.01);
  }

  @Test
  public void updateInstantState() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    abc.updateInstantState(1, 5, "move", "abc", 60, 60,
        69, 60, 60, 60, 60,0);
    assertEquals("[1 0 0 10 10 100 100 100 0, 2 15 15 60 60 69 60 60 0, "
        + "3 30 30 60 60 69 60 60 0, 4 45 45 60 60 69 60 60 0, 5 60 60 60 60 69 6"
        + "0 60 0]", abc.stateBySecond.toString());
  }

  @Test
  public void updateInstantState2() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    abc.updateInstantState(1, 5, "size", "abc", 60, 60,
        69, 60, 60, 60, 60,0);
    assertEquals("[1 0 0 10 10 100 100 100 0, 2 60 60 23 23 69 60 60 0, "
        + "3 60 60 35 35 69 60 60 0, 4 60 60 48 48 69 60 60 0, 5 60 60 60"
        + " 60 69 60 60 0]", abc.stateBySecond.toString());
  }

  @Test
  public void updateInstantState3() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    abc.updateInstantState(1, 5, "color", "abc", 60, 60,
        69, 60, 60, 60, 60,0);
    assertEquals("[1 0 0 10 10 100 100 100 0, 2 60 60 60 60 92 90 90 0,"
        + " 3 60 60 60 60 85 80 80 0, 4 60 60 60 60 77 70 70 0, 5 60 60 "
        + "60 60 69 60 60 0]", abc.stateBySecond.toString());
  }

  //donothing
  @Test
  public void updateInstantState4() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    abc.updateInstantState(1, 5, "nothing", "abc", 60, 60,
        69, 60, 60, 60, 60,0);
    assertEquals("[1 0 0 10 10 100 100 100 0, 2 60 60 60 60 69 60 60 0, "
        + "3 60 60 60 60 69 60 60 0, 4 60 60 60 60 69 60 60 0, 5 60 "
        + "60 60 60 69 60 60 0]", abc.stateBySecond.toString());
  }

  //invalid
  @Test(expected = IllegalArgumentException.class)
  public void updateInstantState5() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    abc.updateInstantState(1, 5, "sddsa", "abc"
        , 60, 60,
        69, 60, 60, 60, 60,0);
  }

  @Test
  public void checkGapsTrue() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 10, 15, 40, 20);
    m.changePosn("abc", 1, 5, 40, 90);
    assertEquals(true, abc.checkGaps());
  }

  @Test
  public void checkGapsFalse() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 10, 15, 40, 20);
    m.changePosn("abc", 1, 10, 40, 90);
    assertEquals(false, abc.checkGaps());
  }

  @Test
  public void checkImportantPointsTrue() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 10, 15, 40, 20);
    m.changePosn("abc", 1, 10, 40, 90);
    assertEquals(false, abc.checkImportantPoints(2));
    assertEquals(true, abc.checkImportantPoints(10));
  }

  @Test
  public void checkHandleWithGap() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.changePosn("abc", 10, 15, 40, 20);
    abc.handleWithGap();
    assertEquals(
        "[1 0 0 0 0 0 0 0 0, 2 0 0 0 0 0 0 0 0, 3 0 0 0 0 0 0 0 0,"
            + " 4 0 0 0 0 0 0 0 0, 5 0 0 0 0 0 0 0 0, 6 0 0 0 0 0 0 0 0, "
            + "7 0 0 0 0 0 0 0 0, 8 0 0 0 0 0 0 0 0, 9 0 0 0 0 0 0 0 0, 10 0 "
            + "0 10 10 100 100 100 0, 11 8 4 10 10 100 100 100 0, 12 16 8 10 10 1"
            + "00 100 100 0, 13 24 12 10 10 100 100 100 0, 14 32 16 10 10 100 10"
            + "0 100 0, 15 40 20 10 10 100 100 100 0]",
        m.getShapes().get(0).getStateBySecond().toString());
  }


  @Test
  public void checkToStringKeyFrames() {
    ExcellenceModel m = new ExcellenceModel();
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    m.addShape(abc);
    m.addKeyframe("abc", "rectangle", 10, 20, 20, 20, 20, 20, 20, 20,0);
    m.addKeyframe("abc", "rectangle", 20, 20, 20, 20, 20, 20, 20, 20,0);
    assertEquals(
        "10 20 20 20 20 20 20 20 0\n"
            + "20 20 20 20 20 20 20 20 0\n", abc.toStringKeyFrames());
  }


  @Test
  public void checkAddKeyFramePiece() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    InstantState newpiece = new InstantState(20, "abc", 20, 20, 20, 20, 20
        , 20, 20,0);
    abc.addKeyFramePiece(newpiece);
    assertEquals("20 20 20 20 20 20 20 20 0\n", abc.toStringKeyFrames());
  }

  @Test
  public void checkAddKeyFramePiece2() {
    Shape abc = new Shape("abc", "Rectangle", 10,
        10, 100, 100, 100, 0, 0,0);
    InstantState newpiece = new InstantState(20, "abc", 20, 20, 20, 20, 20
        , 20, 20,0);
    InstantState newpiece2 = new InstantState(30, "abc", 20, 20, 20, 20, 20
        , 20, 20,0);
    abc.addKeyFramePiece(newpiece);
    abc.addKeyFramePiece(newpiece2);
    assertEquals("20 20 20 20 20 20 20 20 0\n"
        + "30 20 20 20 20 20 20 20 0\n", abc.toStringKeyFrames());
  }


}