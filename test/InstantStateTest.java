import static org.junit.Assert.assertEquals;

import cs3500.animator.model.InstantState;
import org.junit.Test;

/**
 * Testing for instantState class.
 */
public class InstantStateTest {

  @Test
  public void testToString() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    assertEquals("3 50 60 101 10 100 100 105 0", newstate.toString());
  }

  @Test
  public void toStringTest() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    assertEquals("3 dsa 101.0 10.0 100.0 100.0 105.0 50.0 60.0 0.0\n",
        newstate.toStringTest());
  }

  @Test
  public void getWidth() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    assertEquals(101, newstate.getWidth(), 0.0001);
  }

  @Test
  public void setWidth() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    newstate.setWidth(10);
    assertEquals(10, newstate.getWidth(), 0.001);
  }

  @Test
  public void getHeight() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    assertEquals(10, newstate.getHeight(), 0.0001);
  }

  @Test
  public void setHeight() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    newstate.setHeight(15);
    assertEquals(15, newstate.getHeight(), 0.01);
  }

  @Test
  public void getR() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    assertEquals(100, newstate.getR(), 0.01);
  }

  @Test
  public void setR() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    newstate.setR(105);
    assertEquals(105, newstate.getR(), 0.01);
  }

  @Test
  public void getG() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    assertEquals(100, newstate.getG(), 0.01);
  }

  @Test
  public void setG() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    newstate.setG(105);
    assertEquals(105, newstate.getG(), 0.01);
  }

  @Test
  public void getB() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    assertEquals(105, newstate.getB(), 0.01);
  }

  @Test
  public void setB() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    newstate.setB(106);
    assertEquals(106, newstate.getB(), 0.01);
  }

  @Test
  public void getXposn() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    assertEquals(50, newstate.getXposn(), 0.01);
  }

  @Test
  public void setXposn() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    newstate.setXposn(105);
    assertEquals(105, newstate.getXposn(), 0.01);
  }

  @Test
  public void getYposn() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    assertEquals(60, newstate.getYposn(), 0.01);
  }

  @Test
  public void setYposn() {
    InstantState newstate = new InstantState(2, "dsa",
        101, 10, 100, 100, 105, 50, 60,0);
    newstate.setYposn(50);
    assertEquals(50, newstate.getYposn(), 0.01);
  }


}