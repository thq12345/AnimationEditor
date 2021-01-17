package cs3500.animator.view;


import cs3500.animator.model.Shape;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.event.ChangeListener;

/**
 * Text view that outputs the animation in text format. REQUIRE A VALID ANIMATION MODEL!
 */
public class TextView implements IView {

  private List<Shape> current;
  private HashMap<Shape, String> toReturnLogs;
  private boolean isFirstAddShape;
  private String log;
  private String fileName;

  /**
   * Constructor for the view.TextView class.
   *
   * @param width    width of the canvas
   * @param height   height of the canvas
   * @param x        x of the canvas
   * @param y        y of the canvas
   * @param fileName name of the output file if needed .txt output
   */
  public TextView(int width, int height, int x, int y, String fileName) {
    current = new ArrayList<Shape>();
    toReturnLogs = new HashMap<Shape, String>();
    isFirstAddShape = true;
    this.log = "canvas " + width + " " + height + " " + x + " " + y;
    this.fileName = fileName;
  }

  /**
   * Generate actual readable textual view.
   */
  @Override
  public void makeVisible() {
    if (isFirstAddShape) {
      for (Shape s : current) {
        toReturnLogs.put(s, "\nshape " + s.getName() + " " + s.getType());
        for (int i = 0; i < s.getStateBySecond().size(); i++) {
          if (i == 0) {
            String current = toReturnLogs.get(s);
            String toAppend = "\nmotion " + s.getName() + " " + s.getStateBySecond().get(i);
            current += toAppend;
            toReturnLogs.put(s, current);
          } else if (s.checkImportantPoints(i + 1)) {
            String current = toReturnLogs.get(s);
            String toAppend = "";
            if (i == s.getStateBySecond().size() - 1) {
              toAppend = " " + s.getStateBySecond().get(i);
            } else {
              toAppend = " " + s.getStateBySecond().get(i) +
                  "\nmotion " + s.getName() + " " + s.getStateBySecond().get(i);
            }
            current += toAppend;
            toReturnLogs.put(s, current);
          }
        }
        log += toReturnLogs.get(s);
      }
      System.out.println(this.log);
      isFirstAddShape = false;
      try {
        this.printFile();
      } catch (IOException e) {
        return;
      }
    }
  }

  /**
   * Add a list of shapes to the view.
   *
   * @param given list of shape
   */
  @Override
  public void addShape(List<Shape> given) {
    this.current = given;
  }


  /**
   * Refresh the view(not applicable in textView).
   */
  @Override
  public void repaint() {
    return;
  }

  /**
   * Output the file in .txt.
   *
   * @throws IOException when the program is unable to output the .txt file
   */
  @Override
  public void printFile() throws IOException {
    FileOutputStream fos = new FileOutputStream(fileName, true);
    byte[] b = log.getBytes();
    fos.write(b);
    fos.close();
    System.out.println("file saved.");
  }

  //UNSUPPORTED OPERATION FROM HERE!!!!!!!!!

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
   * UNSUPPORTED RESTART OPERATION.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void restartListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED SETSPEED OPERATION.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void setSpeedListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED LOOP OPERATION.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void loopListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED KEYFRAME OPERATION.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void keyframeListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED TEXT OPERATION.
   *
   * @param action action for the slider(which does not exist)
   */
  @Override
  public void sliderListener(ChangeListener action) {
    return;
  }

  /**
   * UNSUPPORTED SLIDER OPERATION.
   *
   * @return slidervalue to be returned.
   */
  @Override
  public int getSliderValue() {
    return -1;
  }

  /**
   * UNSUPPORTED SHOWALLKEYFRAME OPERATION.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void showAllKeyFrameListener(ActionListener action) {
    return;
  }

  /**
   * UNSUPPORTED GETSPEED OPERATION.
   *
   * @return null
   */
  @Override
  public String getSpeedText() {
    return null;
  }

  /**
   * UNSUPPORTED GETKEYFRAME USER SELECTION OPERATION.
   *
   * @return empty string
   */
  @Override
  public String getKeyFrameModifyOption() {
    return "";
  }

  /**
   * UNSUPPORTED GET KEYFRAME USER INPUT OPERATION.
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
   * UNSUPPORTED SET CURRENT SLIDER VALUE OPERATION.
   *
   * @param value current slider value
   */
  @Override
  public void setCurrentSliderVal(int value) {
    return;
  }

  /**
   * UNSUPPORTED MOUSE CLICKING VALUE OPERATION.
   *
   * @param listener listener for mouse
   */
  @Override
  public void mouseEditingListener(MouseListener listener) {
    return;
  }


}