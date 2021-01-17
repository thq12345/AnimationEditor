package cs3500.animator.view;

import cs3500.animator.model.Duration;
import cs3500.animator.model.Shape;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;

/**
 * Class representing view in SVG format. Requires a valid Model.AnimationModel.
 */
public class SVGView implements IView {

  private String fileContents;
  private List<Shape> current;
  private int tickSecondFactor;
  private String svgname;

  /**
   * Constructor for view.SVGView class.
   *
   * @param width            width of the SVG view
   * @param height           height of the SVG view
   * @param x                x position of the SVG view
   * @param y                y position of the SVG view
   * @param tickSecondFactor one tick equals how many millisecond
   * @param svgname          name of the output SVG file (must include the .svg).
   */
  public SVGView(int width, int height, int x, int y, int tickSecondFactor, String svgname) {
    this.tickSecondFactor = tickSecondFactor;
    this.svgname = svgname;
    fileContents =
        "<svg width=\"" + width + "\" height=\"" + height + "\" viewBox=\"" + x + " " + y
            + " " + width + " " + height + "\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n";
  }

  /**
   * Make the entire SVG file visible. (Note "visible" have different definition in different
   * models, in this case it refers to generate SVG data that makes sense).
   */
  @Override
  public void makeVisible() {
    for (Shape s : current) {
      fileContents += this.makeDeclarationOfShape(s);
      for (Duration dur : s.getMoveDuration()) {
        int netDiffTime = dur.endTime - dur.startTime;

        long startX = Math.round(s.getStateBySecond().get(dur.startTime).getXposn());
        long endX = Math.round(s.getStateBySecond().get(dur.endTime - 1).getXposn());
        long startY = Math.round(s.getStateBySecond().get(dur.startTime).getYposn());
        long endY = Math.round(s.getStateBySecond().get(dur.endTime - 1).getYposn());
        fileContents =
            fileContents + this.makeMoveWithConsiderationOfShape(s, startX, startY, endX, endY,
                dur.startTime, netDiffTime);
      }
      for (Duration dur : s.getColorDuration()) {
        int netDiffTime = dur.endTime - dur.startTime;
        long startR = Math.round(s.getStateBySecond().get(dur.startTime).getR());
        long endR = Math.round(s.getStateBySecond().get(dur.endTime - 1).getR());
        long startG = Math.round(s.getStateBySecond().get(dur.startTime).getG());
        long endG = Math.round(s.getStateBySecond().get(dur.endTime - 1).getG());
        long startB = Math.round(s.getStateBySecond().get(dur.startTime).getB());
        long endB = Math.round(s.getStateBySecond().get(dur.endTime - 1).getB());
        fileContents = fileContents +
            "    <animate attributeType=\"xml\" begin=\"" + dur.startTime * tickSecondFactor +
            "ms\" dur=\"" + netDiffTime * tickSecondFactor + ".0ms\" attributeName=\"fill\" "
            + "from=\"rgb(" + startR + "," + startG + "," + startB + ")\" "
            + "to=\"rgb(" + endR + "," + endG + "," + endB + ")\" fill=\"freeze\" />\n";
      }
      for (Duration dur : s.getSizeDuration()) {
        int netDiffTime = dur.endTime - dur.startTime;
        long startW = Math.round(s.getStateBySecond().get(dur.startTime).getWidth());
        long endW = Math.round(s.getStateBySecond().get(dur.endTime - 1).getWidth());
        long startH = Math.round(s.getStateBySecond().get(dur.startTime).getHeight());
        long endH = Math.round(s.getStateBySecond().get(dur.endTime - 1).getHeight());
        fileContents =
            fileContents + this.makeSizeWithConsiderationOfShape(s, startW, startH, endW, endH,
                dur.startTime, netDiffTime);
      }
      for (Duration dur : s.getRotateDuration()) {
        int netDiffTime = dur.endTime - dur.startTime;
        long startDeg = Math.round(s.getStateBySecond().get(dur.startTime).getDegree());
        long endDeg = Math.round(s.getStateBySecond().get(dur.endTime - 1).getDegree());
        long startCenterX = Math.round(s.getStateBySecond().get(dur.startTime).getXposn()
            + s.getStateBySecond().get(dur.startTime).getWidth() / 2);
        long startCenterY = Math.round(s.getStateBySecond().get(dur.startTime).getYposn()
            + s.getStateBySecond().get(dur.startTime).getHeight() / 2);
        long endCenterX = Math.round(s.getStateBySecond().get(dur.endTime - 1).getXposn()
            + s.getStateBySecond().get(dur.endTime - 1).getWidth() / 2);
        long endCenterY = Math.round(s.getStateBySecond().get(dur.endTime - 1).getYposn()
            + s.getStateBySecond().get(dur.endTime - 1).getHeight() / 2);
        fileContents += "   <animateTransform attributeName=\"transform\"\n" +
            "                    type=\"rotate\"\n" +
            "                    from=\"" + startDeg + " " + startCenterX + " " + startCenterY
            + "\" " +
            "to=\"" + endDeg + " " + endCenterX + " " + endCenterY + "\"\n" +
            "                    begin=\"" + dur.startTime * tickSecondFactor + "ms\" dur=\""
            + netDiffTime * tickSecondFactor + "ms\"\n" +
            "                    repeatCount=\"freeze\"\n" +
            "   />\n";
      }
      fileContents += this.makeEndingDeclaration(s);
    }
    fileContents += "</svg>";
    System.out.println(fileContents);
    // make a file?
    try {
      this.printFile();
    } catch (IOException e) {
      return;
    }
  }

  /**
   * Add a list of shape into the view.
   *
   * @param given list of shape
   */
  @Override
  public void addShape(List<Shape> given) {
    this.current = given;
  }

  /**
   * Refresh the shape (Not applicable in this view).
   */
  @Override
  public void repaint() {
    return;
  }

  private String makeDeclarationOfShape(Shape s) {
    switch (s.getType()) {
      case "rectangle":
        return "<rect id=\"" + s.getName() + "\" x=\"" + s.stateBySecond.get(0).getXposn()
            + "\" y=\"" +
            s.stateBySecond.get(0).getYposn() + "\" "
            + "width=\"" + s.stateBySecond.get(0).getWidth() + "\" height=\"" + s.stateBySecond
            .get(0).
                getHeight() +
            "\" fill=\"rgb(" + s.stateBySecond.get(0).getR() + "," + s.stateBySecond.get(0).getG() +
            "," + s.stateBySecond.get(0).getB() +
            ")\" visibility=\"visible\" >\n";
      case "ellipse":
        return "<ellipse id=\"" + s.getName() + "\" cx=\"" + s.stateBySecond.get(0).getXposn()
            + "\" cy=\"" +
            s.stateBySecond.get(0).getYposn() + "\" rx=\"" + s.stateBySecond.get(0).getWidth() / 2
            + "\" ry=\"" + s.getHeight() / 2 +
            "\" fill=\"rgb(" + s.stateBySecond.get(0).getR() + "," + s.stateBySecond.get(0).getG()
            + "," + s.stateBySecond.get(0).getB() +
            ")\" visibility=\"visible\" >\n";
      default:
        return "<rect id=\"" + s.getName() + "\" x=\"" + s.stateBySecond.get(0).getXposn()
            + "\" y=\"" +
            s.stateBySecond.get(0).getYposn() + "\" width=\"10\" "
            + "height=\"10\" fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n";
    }
  }

  private String makeEndingDeclaration(Shape s) {
    switch (s.getType()) {
      case "rectangle":
        return "</rect>\n";
      case "ellipse":
        return "</ellipse>\n";
      default:
        return "</rect>\n";
    }
  }

  private String makeMoveWithConsiderationOfShape(Shape s, long startX, long startY, long endX,
      long endY, long
      startTime, int netTime) {
    String toReturn = "";
    if (s.getType().equals("ellipse")) {
      toReturn += "    <animate attributeType=\"xml\" begin=\"" + startTime * tickSecondFactor +
          "ms\" dur=\"" + netTime * tickSecondFactor + ".0ms\" attributeName=\"cx\" from=\""
          + startX + "\" "
          + "to=\"" + endX + "\" fill=\"freeze\" />\n";
      toReturn += "    <animate attributeType=\"xml\" begin=\"" + startTime * tickSecondFactor +
          "ms\" dur=\"" + netTime * tickSecondFactor + ".0ms\" attributeName=\"cy\" from=\""
          + startY + "\" "
          + "to=\"" + endY + "\" fill=\"freeze\" />\n";
      return toReturn;
    } else {
      toReturn += "    <animate attributeType=\"xml\" begin=\"" + startTime * tickSecondFactor +
          "ms\" dur=\"" + netTime * tickSecondFactor + ".0ms\" attributeName=\"x\" from=\"" + startX
          + "\" "
          + "to=\"" + endX + "\" fill=\"freeze\" />\n";
      toReturn += "    <animate attributeType=\"xml\" begin=\"" + startTime * tickSecondFactor +
          "ms\" dur=\"" + netTime * tickSecondFactor + ".0ms\" attributeName=\"y\" from=\"" + startY
          + "\" "
          + "to=\"" + endY + "\" fill=\"freeze\" />\n";
      return toReturn;
    }
  }

  private String makeSizeWithConsiderationOfShape(Shape s, long startW, long startH, long endW,
      long endH, long
      startTime, int netTime) {
    String toReturn = "";
    if (s.getType().equals("ellipse")) {
      toReturn += "    <animate attributeType=\"xml\" begin=\"" + startTime * tickSecondFactor +
          "ms\" dur=\"" + netTime * tickSecondFactor + ".0ms\" attributeName=\"rx\" from=\""
          + startW / 2 + "\" "
          + "to=\"" + endW / 2 + "\" fill=\"freeze\" />\n";
      toReturn += "    <animate attributeType=\"xml\" begin=\"" + startTime * tickSecondFactor +
          "ms\" dur=\"" + netTime * tickSecondFactor + ".0ms\" attributeName=\"ry\" from=\""
          + startH / 2 + "\" "
          + "to=\"" + endH / 2 + "\" fill=\"freeze\" />\n";
      return toReturn;
    } else {
      toReturn += "    <animate attributeType=\"xml\" begin=\"" + startTime * tickSecondFactor +
          "ms\" dur=\"" + netTime * tickSecondFactor + ".0ms\" attributeName=\"width\" from=\""
          + startW + "\" "
          + "to=\"" + endW + "\" fill=\"freeze\" />\n";
      toReturn += "    <animate attributeType=\"xml\" begin=\"" + startTime * tickSecondFactor +
          "ms\" dur=\"" + netTime * tickSecondFactor + ".0ms\" attributeName=\"height\" from=\""
          + startH
          + "\" "
          + "to=\"" + endH + "\" fill=\"freeze\" />\n";
      return toReturn;
    }
  }

  /**
   * Output the file in SVG format.
   *
   * @throws IOException when it is unable to write a file.
   */
  @Override
  public void printFile() throws IOException {
    if (svgname.equalsIgnoreCase("consoleOutput")) {
      System.out.println(fileContents);
      System.out.println("Console output successfully created.");
      return;
    }
    FileOutputStream fos = new FileOutputStream(svgname, true);
    byte[] b = fileContents.getBytes();
    fos.write(b);
    fos.close();
    System.out.println("File " + svgname + " successfully created and saved.");
  }

  //UNSUPPORTED FUNCTIONALITY FROM HERE!!!!!


  /**
   * UNSUPPORTED PAUSE OPERATION.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void pauseListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED RESTART OPERATION. DO NOTHING WHEN CALLED.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void restartListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED SETSPEED OPERATION. DO NOTHING WHEN CALLED.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void setSpeedListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED LOOP OPERATION. DO NOTHING WHEN CALLED.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void loopListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED KEYFRAME OPERATION. DO NOTHING WHEN CALLED.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void keyframeListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED SLIDERLISTENING OPERATION.
   *
   * @param action changelistener
   */
  @Override
  public void sliderListener(ChangeListener action) {
    return;
  }

  /**
   * UNSUPPORTED GETSLIDERVALUE OPERATION.
   *
   * @return -1, INVALID VALUE
   */
  @Override
  public int getSliderValue() {
    return -1;
  }

  /**
   * UNSUPPORTED SHOWALLKEYFRAME OPERATION. DO NOTHING WHEN CALLED.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void showAllKeyFrameListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED OPERATION. RETURN NULL WHEN BEING CALLED.
   *
   * @return null
   */
  @Override
  public String getSpeedText() {
    return null;
  }

  /**
   * UNSUPPORTED OPERATION. RETURN EMPTY STRING WHEN BEING CALLED.
   *
   * @return ""
   */
  @Override
  public String getKeyFrameModifyOption() {
    return "";
  }


  /**
   * UNSUPPORTED OPERATION. RETURN NULL WHEN BEING CALLED.
   *
   * @return null
   */
  @Override
  public ArrayList<String> getKeyFrameInputs() {
    return null;
  }

  /**
   * UNSUPPORTED SETSLIDER OPERATION.
   *
   * @param value max value for a slider
   */
  @Override
  public void setSliderMax(int value) {
    return;
  }

  /**
   * UNSUPPORTED SETSLIDER OPERATION.
   *
   * @param value current slider value
   */
  @Override
  public void setCurrentSliderVal(int value) {
    return;
  }

  /**
   * UNSUPPORTED MOUSEEDIT OPERATION.
   *
   * @param listener mouse listener
   */
  @Override
  public void mouseEditingListener(MouseListener listener) {
    return;
  }


}
