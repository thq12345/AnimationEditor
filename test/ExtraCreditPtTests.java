import cs3500.animator.controller.AnimationControls;
import cs3500.animator.model.ExcellenceModel;
import cs3500.animator.model.Shape;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing part for the assignment 9. Testing functionalities of rotation and layers.
 */
public class ExtraCreditPtTests {

  @Test
  public void rotateMotionStandard() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    m.addShape(shape1);
    m.changePosn("abc", 1, 10, 100, 100);
    m.rotateShape("abc", 1, 10, 100);
    assertEquals(
        "[1 10 10 10 10 10 10 10 10, 2 20 20 10 10 10 10 10 20, 3 30 30 10 10 10 10 10 30,"
            + " 4 40 40 10 10 10 10 10 40, 5 50 50 10 10 10 10 10 50, 6 60 60 10 10 10 10 10 60, "
            + "7 70 70 10 10 10 10 10 70, 8 80 80 10 10 10 10 10 80, 9 90 90 10 10 10 10 10 90, "
            + "10 100 100 10 10 10 10 10 100]",
        m.getShapes().get(0).stateBySecond.toString());
  }

  @Test
  public void rotateMotionStandard2() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    m.addShape(shape1);
    m.changePosn("abc", 1, 10, 100, 100);
    m.rotateShape("abc", 7, 15, 100);
    assertEquals(
        "[1 10 10 10 10 10 10 10 10, 2 20 20 10 10 10 10 10 10, "
            + "3 30 30 10 10 10 10 10 10, 4 40 40 10 10 10 10 10 10, 5 50 50 10 10 10 10 10 10, "
            + "6 60 60 10 10 10 10 10 10, 7 70 70 10 10 10 10 10 10, 8 80 80 10 10 10 10 10 21, "
            + "9 90 90 10 10 10 10 10 33, 10 100 100 10 10 10 10 10 44, 11 100 100 10 "
            + "10 10 10 10 55, 12 100 100 10 10 10 10 10 66, 13 100 100 10 10 10 10 "
            + "10 78, 14 100 100 10 10 10 10 10 89, 15 100 100 10 10 10 10 10 100]",
        m.getShapes().get(0).stateBySecond.toString());
  }

  @Test
  public void rotateMotionStandard3() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    m.addShape(shape1);
    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    assertEquals(
        "[1 10 10 10 10 10 10 10 10, 2 33 33 10 10 10 10 10 10, "
            + "3 55 55 10 10 10 10 10 10, 4 78 78 10 10 10 10 10 10, 5 100 100 10 10 10 10 10 10, "
            + "6 100 100 10 10 10 10 10 28, 7 100 100 10 10 10 10 10 46, 8 100 100 10 "
            + "10 10 10 10 64, 9 100 100 10 10 10 10 10 82, 10 100 100 10 10 10 10 10 100]",
        m.getShapes().get(0).getStateBySecond().toString());
  }

  @Test
  public void rotateMotionStandard4() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    m.addShape(shape1);
    m.addShape(shape2);
    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    assertEquals(
        "[1 10 10 10 10 10 10 10 10, 2 33 33 10 10 10 10 10 10, "
            + "3 55 55 10 10 10 10 10 10, 4 78 78 10 10 10 10 10 10, "
            + "5 100 100 10 10 10 10 10 10, "
            + "6 100 100 10 10 10 10 10 28, 7 100 100 10 10 10 10"
            + " 10 46, 8 100 100 10 "
            + "10 10 10 10 64, 9 100 100 10 10 10 10 10 82, 10 1"
            + "00 100 10 10 10 10 10 100]",
        m.getShapes().get(0).getStateBySecond().toString());
    assertEquals("[1 10 10 10 10 10 10 10 10, 2 33 33 10 1"
            + "0 10 10 10 10, "
            + "3 55 55 10 10 10 10 10 10, 4 78 78 10 10 10 10 10 10"
            + ", 5 100 100 10 10 10 10 10 10,"
            + " 6 100 100 10 10 10 10 10 28, 7 100 100 10 10 10 10 10 46,"
            + " 8 100 100 10 10 10 10 10 64,"
            + " 9 100 100 10 10 10 10 10 82, 10 100 100 10 10 10 10 10 100]"
        , m.getShapes().get(1).getStateBySecond().toString());
  }


  @Test
  public void testSimulateInputSVG() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    m.addShape(shape1);
    m.addShape(shape2);
    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    AnimationControls play = new AnimationControls(m, "svg", 20, "");
    play.startAnimating();

    assertEquals(
        "<svg width=\"0\" height=\"0\" viewBox=\"0 0 0 0\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"abc\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" "
            + "fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "<rect id=\"def\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" fill="
            + "\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "</svg>\r\n",
        outContent.toString());
  }

  @Test
  public void testShapeLayedInOrder() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);

    m.addShape(shape1);
    m.addShape(shape2);

    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    AnimationControls play = new AnimationControls(m, "text", 20, "");
    play.startAnimating();

    assertEquals("canvas 0 0 0 0\n"
            + "shape abc rectangle\n"
            + "motion abc 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion abc 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\n"
            + "shape def rectangle\n"
            + "motion def 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion def 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\r\n",
        outContent.toString());
  }

  @Test
  public void testShapeLayedNotInOrder() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);

    //the order is flipped.
    m.addShape(shape2);
    m.addShape(shape1);

    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);

    AnimationControls play = new AnimationControls(m, "text", 20, "");
    play.startAnimating();

    assertEquals("canvas 0 0 0 0\n"
            + "shape abc rectangle\n"
            + "motion abc 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion abc 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\n"
            + "shape def rectangle\n"
            + "motion def 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion def 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\r\n",
        outContent.toString());
  }


  @Test
  public void testShapeLayedInOrderSVG() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);

    m.addShape(shape1);
    m.addShape(shape2);

    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    AnimationControls play = new AnimationControls(m, "svg", 20, "");
    play.startAnimating();

    assertEquals("<svg width=\"0\" height=\"0\" viewBox=\"0 0 0 0\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"abc\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" "
            + "fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "<rect id=\"def\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" "
            + "fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "</svg>\r\n",
        outContent.toString());
  }


  @Test
  public void testShapeLayedNotInOrderSVG() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);

    //the order is being flipped
    m.addShape(shape2);
    m.addShape(shape1);

    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    AnimationControls play = new AnimationControls(m, "svg", 20, "");
    play.startAnimating();

    assertEquals("<svg width=\"0\" height=\"0\" viewBox=\"0 0 0 0\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"abc\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" "
            + "fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "<rect id=\"def\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" "
            + "fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "</svg>\r\n",
        outContent.toString());
  }


  @Test
  public void testSimulateInput() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape3 = new Shape("shape3", "rectangle", 10, 10
        , 10, 10, 10, 10, 10, 0);
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);
    shape3.setLayer(3);
    m.addShape(shape3);
    m.addShape(shape1);
    m.addShape(shape2);

    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    m.changePosn("shape3", 1, 5, 100, 100);
    m.rotateShape("shape3", 5, 10, 100);
    AnimationControls play = new AnimationControls(m, "text", 20, "");
    play.startAnimating();

    assertEquals("canvas 0 0 0 0\n"
            + "shape abc rectangle\n"
            + "motion abc 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion abc 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\n"
            + "shape def rectangle\n"
            + "motion def 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion def 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\n"
            + "shape shape3 rectangle\n"
            + "motion shape3 1 10 10 10 10 10 10 10 0 5 100 100 10 10 10 10 10 0\n"
            + "motion shape3 5 100 100 10 10 10 10 10 0 10 100 100 10 10 10 10 10 100\r\n",
        outContent.toString());
  }

  @Test
  public void testMultipleShapeLayedInOrder() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);
    Shape shape3 = new Shape("def2", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape3.setLayer(1);

    //the order is flipped.
    m.addShape(shape2);
    m.addShape(shape1);
    m.addShape(shape3);

    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    m.changePosn("def2", 1, 5, 100, 100);
    m.rotateShape("def2", 5, 10, 100);
    AnimationControls play = new AnimationControls(m, "text", 20, "");
    play.startAnimating();

    assertEquals("canvas 0 0 0 0\n"
            + "shape abc rectangle\n"
            + "motion abc 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion abc 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\n"
            + "shape def2 rectangle\n"
            + "motion def2 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion def2 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\n"
            + "shape def rectangle\n"
            + "motion def 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion def 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\r\n",
        outContent.toString());
  }

  @Test
  public void testMultipleShapeLayedNotInOrder() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);
    Shape shape3 = new Shape("def2", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape3.setLayer(1);

    //the order is flipped.
    m.addShape(shape2);
    m.addShape(shape3);
    m.addShape(shape1);

    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    m.changePosn("def2", 1, 5, 100, 100);
    m.rotateShape("def2", 5, 10, 100);
    AnimationControls play = new AnimationControls(m, "text", 20, "");
    play.startAnimating();

    assertEquals("canvas 0 0 0 0\n"
            + "shape def2 rectangle\n"
            + "motion def2 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion def2 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\n"
            + "shape abc rectangle\n"
            + "motion abc 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion abc 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\n"
            + "shape def rectangle\n"
            + "motion def 1 10 10 10 10 10 10 10 10 5 100 100 10 10 10 10 10 10\n"
            + "motion def 5 100 100 10 10 10 10 10 10 10 100 100 10 10 10 10 10 100\r\n",
        outContent.toString());
  }


  @Test
  public void testMultipleShapeLayedInOrderSVG() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);
    Shape shape3 = new Shape("shape3", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape3.setLayer(1);

    m.addShape(shape2);
    m.addShape(shape1);
    m.addShape(shape3);

    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    m.changePosn("shape3", 1, 5, 100, 100);
    m.rotateShape("shape3", 5, 10, 100);
    AnimationControls play = new AnimationControls(m, "svg", 20, "");
    play.startAnimating();

    assertEquals(
        "<svg width=\"0\" height=\"0\" viewBox=\"0 0 0 0\" version=\"1.1\" xmlns=\""
            + "http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"abc\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" fill=\""
            + "rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" attribut"
            + "eName=\"x\" "
            + "from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" attribu"
            + "teName=\"y\" "
            + "from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "<rect id=\"shape3\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" "
            + "fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" attributeName=\""
            + "x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" attributeName="
            + "\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "<rect id=\"def\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" "
            + "fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "</svg>\r\n",
        outContent.toString());
  }

  @Test
  public void testMultipleShapeLayedNotInOrderSVG() {
    final OutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);
    Shape shape3 = new Shape("shape3", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape3.setLayer(1);

    m.addShape(shape2);
    m.addShape(shape3);
    m.addShape(shape1);

    m.changePosn("abc", 1, 5, 100, 100);
    m.rotateShape("abc", 5, 10, 100);
    m.changePosn("def", 1, 5, 100, 100);
    m.rotateShape("def", 5, 10, 100);
    m.changePosn("shape3", 1, 5, 100, 100);
    m.rotateShape("shape3", 5, 10, 100);
    AnimationControls play = new AnimationControls(m, "svg", 20, "");
    play.startAnimating();

    assertEquals(
        "<svg width=\"0\" height=\"0\" viewBox=\"0 0 0 0\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"shape3\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" "
            + "fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" attribute"
            + "Name=\"x\" "
            + "from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" attributeN"
            + "ame=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "<rect id=\"abc\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\""
            + "10.0\" fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "<rect id=\"def\" x=\"10.0\" y=\"10.0\" width=\"10.0\" height=\"10.0\" "
            + "fill=\"rgb(10.0,10.0,10.0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"x\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"30ms\" dur=\"120.0ms\" "
            + "attributeName=\"y\" from=\"33\" to=\"100\" fill=\"freeze\" />\n"
            + "   <animateTransform attributeName=\"transform\"\n"
            + "                    type=\"rotate\"\n"
            + "                    from=\"28 105 105\" to=\"100 105 105\"\n"
            + "                    begin=\"150ms\" dur=\"150ms\"\n"
            + "                    repeatCount=\"freeze\"\n"
            + "   />\n"
            + "</rect>\n"
            + "</svg>\r\n",
        outContent.toString());
  }

  @Test
  public void testsetAllTick() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    shape1.setTick(2);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);
    shape2.setTick(1);
    Shape shape3 = new Shape("shape3", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape3.setLayer(1);
    shape3.setTick(5);

    m.addShape(shape2);
    m.addShape(shape3);
    m.addShape(shape1);
    m.setAllTick(6);
    assertEquals(6, shape1.getTick());
    assertEquals(6, shape2.getTick());
    assertEquals(6, shape3.getTick());
  }

  @Test
  public void testsetLayerForAShape() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    shape1.setTick(2);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);
    shape2.setTick(1);
    Shape shape3 = new Shape("shape3", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape3.setLayer(1);
    shape3.setTick(5);

    m.addShape(shape2);
    m.addShape(shape3);
    m.addShape(shape1);
    m.setLayerForAShape("def", 5);
    assertEquals(5, shape2.getLayer());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testsetLayerForAShapeInvalid() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    shape1.setTick(2);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);
    shape2.setTick(1);
    Shape shape3 = new Shape("shape3", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape3.setLayer(1);
    shape3.setTick(5);

    m.addShape(shape2);
    m.addShape(shape3);
    m.addShape(shape1);
    m.setLayerForAShape("invalidName", 5);
  }

  @Test
  public void testremoveAllShapeWithinLayer() {
    ExcellenceModel m = new ExcellenceModel();
    Shape shape1 = new Shape("abc", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape1.setLayer(1);
    shape1.setTick(2);
    Shape shape2 = new Shape("def", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape2.setLayer(2);
    shape2.setTick(1);
    Shape shape3 = new Shape("shape3", "rectangle", 10,
        10, 10, 10, 10, 10, 10, 10);
    shape3.setLayer(1);
    shape3.setTick(5);

    m.addShape(shape2);
    m.addShape(shape3);
    m.addShape(shape1);
    m.removeAllShapeWithinLayer(1);
    assertEquals(1, m.getShapes().size());
    assertEquals("def", m.getShapes().get(0).getName());
  }


}
