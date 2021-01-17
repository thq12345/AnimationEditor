package cs3500.animator.view;


import cs3500.animator.model.Shape;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;

/**
 * The interface for view, that processes model data passed from the controller and outputs it as a
 * visual representation.
 */
public interface IView {

  /**
   * Make the view visible.
   */
  void makeVisible();

  /**
   * Add a list of shapes to the view.
   *
   * @param given list of shape
   */
  void addShape(List<Shape> given);

  /**
   * Repaint the shape when applicable.
   */
  void repaint();

  /**
   * Output the file in SVG when applicable.
   *
   * @throws IOException when it is unable to read/write the file
   */
  void printFile() throws IOException;

  /**
   * Listener method when user pressed the pause button.
   *
   * @param action action that need to be passed to the controller
   */
  void pauseListener(ActionListener action);

  /**
   * Listener method when user pressed the restart button.
   *
   * @param action action that need to be passed to the controller
   */
  void restartListener(ActionListener action);

  /**
   * Listener method when user pressed the setspeed button.
   *
   * @param action action that need to be passed to the controller
   */
  void setSpeedListener(ActionListener action);

  /**
   * Listener method when user pressed the loop button.
   *
   * @param action action that need to be passed to the controller
   */
  void loopListener(ActionListener action);

  /**
   * Listener method when user pressed the set button for modifying keyframe.
   *
   * @param action action that need to be passed to the controller
   */
  void keyframeListener(ActionListener action);

  void sliderListener(ChangeListener change);

  int getSliderValue();

  /**
   * Listener method when user pressed the showallkeyframe button.
   *
   * @param action action that need to be passed to the controller
   */
  void showAllKeyFrameListener(ActionListener action);

  /**
   * Retrieve the speed information.
   *
   * @return user input in the speed field.
   */
  String getSpeedText();

  /**
   * Retrieve the keyframe/shape modify option user selected from the combo box.
   *
   * @return user input in keyframe modify fields.
   */
  String getKeyFrameModifyOption();


  /**
   * Return data user inputted from text fields about keyframe editing information.
   *
   * @return Arraylist containing user's input data
   */
  ArrayList<String> getKeyFrameInputs();

  /**
   * Set the max value available for slider.
   * @param value max value for a slider
   */
  void setSliderMax(int value);

  /**
   * Set the current value for the slider.
   * @param value current slider value
   */
  void setCurrentSliderVal(int value);

  void mouseEditingListener(MouseListener listener);

}
