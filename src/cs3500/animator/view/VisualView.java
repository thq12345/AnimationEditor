package cs3500.animator.view;

import cs3500.animator.model.Shape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeListener;

/**
 * view.VisualView class, representing the view using Java Swing package.
 */
public class VisualView extends JPanel implements IView {

  private List<Shape> shapeList;
  //  private JPanel mainpanel;
  private JFrame frame = new JFrame();
  private int x;
  private int y;
  //one method take in a list of shape.

  /**
   * Constructor for view.VisualView class.
   *
   * @param width  width of the canvas
   * @param height height of the canvas
   * @param x      The leftmost x value
   * @param y      The topmost y value
   */
  public VisualView(int width, int height, int x, int y) {
    shapeList = new ArrayList<Shape>();
    frame.setTitle("Animation");
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(width, height));
    JScrollPane scrollPane1 = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    frame.add(scrollPane1);
    this.x = x;
    this.y = y;
    frame.pack();

  }


  /**
   * Make the view visible.
   */
  @Override
  public void makeVisible() {
    frame.setVisible(true);
  }

  /**
   * Adding a list of shape to the view.
   *
   * @param given list of shape
   */
  @Override
  public void addShape(List<Shape> given) {
    this.shapeList = given;
  }

  /**
   * Refresh the screen per tick.
   */
  @Override
  public void repaint() {
    super.repaint();
  }

  //UNSUPPORTED FUNCTIONALITY!!!!!!!

  /**
   * Output the file into SVG (Not applicable in this view).
   *
   * @throws IOException when there is an error writing to the file.
   */
  @Override
  public void printFile() throws IOException {
    return;
  }

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
   * UNSUPPORTED SETKEYFRAME OPERATION.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void keyframeListener(ActionListener action) {
    return;
  }

  /**
   * LISTENER FOR SLIDER.
   *
   * @param action action when there is a manual change in the slider.
   */
  @Override
  public void sliderListener(ChangeListener action) {
    return;
  }

  /**
   * NOT APPLICABLE IN THIS CLASS.
   *
   * @return -1, invalid value
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
   * UNSUPPORTED GETKEYFRAME OPERATION.
   *
   * @return empty string ""
   */
  @Override
  public String getKeyFrameModifyOption() {
    return "";
  }

  /**
   * UNSUPPORTED GETKEYFRAMEINPUT OPERATION.
   *
   * @return null
   */
  @Override
  public ArrayList<String> getKeyFrameInputs() {
    return null;
  }

  /**
   * NOT APPLICABLE IN THIS CLASS.
   *
   * @param value max value for a slider
   */
  @Override
  public void setSliderMax(int value) {
    return;
  }

  /**
   * NOT APPLICABLE IN THIS CLASS.
   *
   * @param value current slider value
   */
  @Override
  public void setCurrentSliderVal(int value) {
    return;
  }

  /**
   * NOT APPLICABLE IN THIS CLASS.
   *
   * @param listener listener for the mouse
   */
  @Override
  public void mouseEditingListener(MouseListener listener) {
    return;
  }


  /**
   * Important part painting the shapes per tick.
   *
   * @param g Graphic, Java swing will use this to draw
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    for (int i = 0; i < shapeList.size(); i++) {
      Shape currentShape = shapeList.get(i);

      int x = (int) Math.round(currentShape.getXposn());
      int y = (int) Math.round(currentShape.getYposn());
      int w = (int) Math.round(currentShape.getWidth());
      int h = (int) Math.round(currentShape.getHeight());
      int r = (int) Math.round(currentShape.getR());
      int gr = (int) Math.round(currentShape.getG());
      int b = (int) Math.round(currentShape.getB());
      int degree = (int) Math.round(currentShape.getDegree());
      switch (currentShape.getType()) {
        case "rectangle":
          AffineTransform transform = new AffineTransform();
          transform.rotate(Math.toRadians(degree),
              x - this.x + w / 2, y - this.y + h / 2);
          g2d.transform(transform);
          g2d.setColor(new Color(r, gr, b));
          g2d.fillRect(x - this.x, y - this.y, w, h);
          g2d.drawRect(x - this.x, y - this.y, w, h);
          try {
            g2d.transform(transform.createInverse());
          } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
          }
          break;
        case "ellipse":
          g2d.setColor(new Color(r, gr, b));
          g2d.fillOval(x - this.x, y - this.y, w, h);
          g2d.drawOval(x - this.x, y - this.y, w, h);
          AffineTransform transform2 = new AffineTransform();
          transform2.rotate(Math.toRadians(degree),
              x - this.x + w / 2, y - this.y + h / 2);
          g2d.transform(transform2);
          try {
            g2d.transform(transform2.createInverse());
          } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
          }
          break;
        default:
          g2d.setColor(new Color(255, 0, 0));
          g2d.drawRect(x - this.x, y - this.y, 10, 10);
          break;
      }


    }
  }


}
