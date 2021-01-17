package cs3500.animator.view;

import cs3500.animator.model.Shape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

/**
 * EditView class, representing the view using Java Swing package. This view also gives user the
 * ability to edit keyframe of the animation, as well as playback control (pause, resume, loop,
 * restart, change speed). In other words, this is an upgraded version view.VisualView.
 */
public class HW7EditView extends VisualView implements IView {

  private List<Shape> shapeList;
  private JFrame frame = new JFrame();
  private int x;
  private int y;

  int sliderlength = 300;
  JButton pausebutton;
  JTextField speedInput;
  JButton restartButton;
  JButton setSpeed;
  JButton loop;
  JButton setKeyFrame;
  JComboBox keyFrameEdit;


  //KeyFrame convenience GUI components
  JTextField namefield;
  JTextField timefield;
  JTextField widthfield;
  JTextField heightfield;
  JTextField red;
  JTextField green;
  JTextField blue;
  JTextField xposn;
  JTextField yposn;
  JTextField typefield;
  JTextField degreefield;
  JTextField layerfield;
  JSlider slider;
  //one method take in a list of shape.

  JButton showAllKeyFrame;

  /**
   * Constructor for EditView class.
   *
   * @param width  width of the canvas
   * @param height height of the canvas
   * @param x      The leftmost x value
   * @param y      The topmost y value
   */
  public HW7EditView(int width, int height, int x, int y) {
    super(width, height, x, y);
    shapeList = new ArrayList<Shape>();
    frame.setTitle("Animation");
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    JLabel label = new JLabel("Label");
    label.setPreferredSize(new Dimension(1000, 1000));

    JScrollPane scrollPane1 = new JScrollPane(label);

    this.setLayout(new FlowLayout());
    this.setPreferredSize(new Dimension(width, height));
    this.setMaximumSize(new Dimension(400, 400));
    scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane1.getViewport().add(this, null);
    scrollPane1.setSize(400, 400);
    frame.add(scrollPane1, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());

    slider = new JSlider(1, 100, 1);

    bottomPanel.add(slider);

    //pause/resume button
    pausebutton = new JButton("pause/resume");
    pausebutton.setActionCommand("pause");
    pausebutton.setLayout(new GridBagLayout());
    bottomPanel.add(pausebutton);

    //Restart Button
    restartButton = new JButton("restart");
    restartButton.setActionCommand("restart");
    bottomPanel.add(restartButton);

    //loop button
    loop = new JButton("loop");
    loop.setActionCommand("loop");
    bottomPanel.add(loop);

    speedInput = new JTextField(4);
    speedInput.addFocusListener(new JTextFieldUpgradedWithFocus(speedInput, "speed"));
    bottomPanel.add(speedInput);

    setSpeed = new JButton("setSpeed");
    setSpeed.setActionCommand("setspeed");
    bottomPanel.add(setSpeed);

    keyFrameEdit = new JComboBox();
    keyFrameEdit.addItem("Add KeyFrame");
    keyFrameEdit.addItem("Delete KeyFrame");
    keyFrameEdit.addItem("Edit KeyFrame");
    keyFrameEdit.addItem("Add Shape");
    keyFrameEdit.addItem("Delete Shape");
    keyFrameEdit.addItem("Delete Layer");
    keyFrameEdit.addItem("Edit Layer");
    bottomPanel.add(keyFrameEdit);

    //name field
    namefield = new JTextField(4);
    namefield.addFocusListener(new JTextFieldUpgradedWithFocus(namefield, "Name"));
    bottomPanel.add(namefield);

    typefield = new JTextField(4);
    typefield.addFocusListener(new JTextFieldUpgradedWithFocus(typefield, "Type"));
    bottomPanel.add(typefield);

    //time field
    timefield = new JTextField(3);
    timefield.addFocusListener(new JTextFieldUpgradedWithFocus(timefield, "Time"));
    bottomPanel.add(timefield);

    //x position input
    xposn = new JTextField(2);
    xposn.addFocusListener(new JTextFieldUpgradedWithFocus(xposn, "X"));
    bottomPanel.add(xposn);

    //y position input
    yposn = new JTextField(2);
    yposn.addFocusListener(new JTextFieldUpgradedWithFocus(yposn, "Y"));
    bottomPanel.add(yposn);

    //R
    red = new JTextField(2);
    red.addFocusListener(new JTextFieldUpgradedWithFocus(red, "R"));
    bottomPanel.add(red);

    //G
    green = new JTextField(2);
    green.addFocusListener(new JTextFieldUpgradedWithFocus(green, "G"));
    bottomPanel.add(green);

    //B
    blue = new JTextField(2);
    blue.addFocusListener(new JTextFieldUpgradedWithFocus(blue, "B"));
    bottomPanel.add(blue);

    //Width
    widthfield = new JTextField(2);
    widthfield.addFocusListener(new JTextFieldUpgradedWithFocus(widthfield, "W"));
    bottomPanel.add(widthfield);

    //Height
    heightfield = new JTextField(2);
    heightfield.addFocusListener(new JTextFieldUpgradedWithFocus(heightfield, "H"));
    bottomPanel.add(heightfield);

    //degree
    degreefield = new JTextField(4);
    degreefield.addFocusListener(new JTextFieldUpgradedWithFocus(degreefield, "Degree"));
    bottomPanel.add(degreefield);

    //layer
    layerfield = new JTextField(4);
    layerfield.addFocusListener(new JTextFieldUpgradedWithFocus(layerfield, "layer"));
    bottomPanel.add(layerfield);

    setKeyFrame = new JButton("Set");
    setKeyFrame.setActionCommand("setKeyFrame");
    bottomPanel.add(setKeyFrame);

    showAllKeyFrame = new JButton("ShowAllKeyFrame");
    showAllKeyFrame.setActionCommand("showkeyframe");
    bottomPanel.add(showAllKeyFrame);

    frame.add(bottomPanel, BorderLayout.PAGE_END);

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

  /**
   * Output the file into SVG (Not applicable in this view).
   *
   * @throws IOException when there is an error writing to the file.
   */
  @Override
  public void printFile() {
    return;
  }

  /**
   * Listener when the user paused the pause button.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void pauseListener(ActionListener action) {
    pausebutton.addActionListener(action);
  }

  /**
   * Listener when the user pressed the restart button.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void restartListener(ActionListener action) {
    restartButton.addActionListener(action);
  }

  /**
   * Listener when the user pressed the setspeed button.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void setSpeedListener(ActionListener action) {
    setSpeed.addActionListener(action);
  }

  /**
   * Listener when the user pressed the loop button.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void loopListener(ActionListener action) {
    loop.addActionListener(action);
  }

  /**
   * Listerner when the user pressed the "set" button for keyframe.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void keyframeListener(ActionListener action) {
    setKeyFrame.addActionListener(action);
  }

  @Override
  public void sliderListener(ChangeListener change) {
    slider.addChangeListener(change);
  }

  @Override
  public int getSliderValue() {
    return slider.getValue();
  }

  /**
   * Listener when the user pressed pressed the showallkeyframe button.
   *
   * @param action action that need to be passed to the controller
   */
  @Override
  public void showAllKeyFrameListener(ActionListener action) {
    showAllKeyFrame.addActionListener(action);
  }

  /**
   * Retrieve the speed information.
   *
   * @return user input in the speed field.
   */
  @Override
  public String getSpeedText() {
    return speedInput.getText();
  }

  /**
   * Retrieve the keyframe/shape modify option user selected from the combo box.
   *
   * @return user input in keyframe modify fields.
   */
  @Override
  public String getKeyFrameModifyOption() {
    return keyFrameEdit.getSelectedItem().toString();
  }

  @Override
  public void setSliderMax(int value) {
    slider.setMaximum(value);
  }


  @Override
  public void setCurrentSliderVal(int val) {
    slider.setValue(val);
  }


  /**
   * Return data user inputted from text fields about keyframe editing information.
   *
   * @return Arraylist containing user's input data
   */
  @Override
  public ArrayList<String> getKeyFrameInputs() {
    ArrayList<String> toReturn = new ArrayList<String>();
    toReturn.add(keyFrameEdit.getSelectedItem().toString());
    toReturn.add(timefield.getText());
    toReturn.add(namefield.getText());
    toReturn.add(typefield.getText());
    toReturn.add(xposn.getText());
    toReturn.add(yposn.getText());
    toReturn.add(widthfield.getText());
    toReturn.add(heightfield.getText());
    toReturn.add(red.getText());
    toReturn.add(green.getText());
    toReturn.add(blue.getText());
    toReturn.add(degreefield.getText());
    toReturn.add(layerfield.getText());
    return toReturn;
  }


  @Override
  public void mouseEditingListener(MouseListener listener) {
    slider.addMouseListener(listener);
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
    for (Shape currentShape : shapeList) {
      int x = (int) Math.round(currentShape.getXposn());
      int y = (int) Math.round(currentShape.getYposn());
      int w = (int) Math.round(currentShape.getWidth());
      int h = (int) Math.round(currentShape.getHeight());
      int r = (int) Math.round(currentShape.getR());
      int gr = (int) Math.round(currentShape.getG());
      int b = (int) Math.round(currentShape.getB());
      int degree = (int) Math.round(currentShape.getDegree());
      AffineTransform transform = new AffineTransform();

      switch (currentShape.getType()) {
        case "rectangle":
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
          transform.rotate(Math.toRadians(degree),
              x - this.x + w / 2, y - this.y + h / 2);
          g2d.transform(transform);
          g2d.setColor(new Color(r, gr, b));
          g2d.setColor(new Color(r, gr, b));
          g2d.fillOval(x - this.x, y - this.y, w, h);
          g2d.drawOval(x - this.x, y - this.y, w, h);
          try {
            g2d.transform(transform.createInverse());
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

