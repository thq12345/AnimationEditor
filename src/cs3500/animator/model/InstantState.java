package cs3500.animator.model;

/**
 * Class representing Instantaneous state of the shape, and contains its properties.
 */
public class InstantState {

  //name of the shape
  private String name;
  //width of the shape
  private double width;
  //height of the shape
  private double height;
  //red
  private double r;
  //green
  private double g;
  //blue
  private double b;
  //x position (on the frame)
  private double xposn;
  //y position (on the frame)
  private double yposn;
  //moves to be made, with the integer to be done at the start time
  private int time;

  private double degree;


  public String getName() {
    return name;
  }

  /**
   * The constructor for the Model.InstantState class. Representing instant state of an shape at a
   * given tick.
   *
   * @param time   the tick which the shape has that property
   * @param name   the name of the shape
   * @param width  the width of the shape
   * @param height the height of the shape
   * @param r      the redness of the shape
   * @param g      the greenness of the shape
   * @param b      the blueness of the shape
   * @param xposn  the x position of the shape
   * @param yposn  the y position of the shape
   */
  public InstantState(int time, String name, double width, double height, double r, double g,
      double b,
      double xposn, double yposn, double degree) {

    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Invalid color!");
    }

    this.name = name;
    this.width = width;
    this.height = height;
    this.r = r;
    this.g = g;
    this.b = b;
    this.xposn = xposn;
    this.yposn = yposn;
    this.time = time;
    this.degree = degree;

  }

  /**
   * Convert the current state of a shape into String.
   *
   * @return a string representing current state
   */
  public String toString() {
    return (this.time + 1) + " " + Math.round(this.xposn) + " "
        + Math.round(this.yposn) + " " + Math.round(this.width) + " " + Math.round(this.height)
        + " " + Math.round(this.r) + " " + Math.round(this.g) + " " + Math.round(this.b)
        + " " + Math.round(this.degree);
  }

  /**
   * To String method but for keyframe.
   * @return a string representing keyframe information
   */
  public String toStringKeyFrame() {
    return (this.time) + " " + Math.round(this.xposn) + " "
        + Math.round(this.yposn) + " " + Math.round(this.width) + " " + Math.round(this.height)
        + " " + Math.round(this.r) + " " + Math.round(this.g) + " " + Math.round(this.b) + " " +
        Math.round(this.degree) + "\n";
  }

  /**
   * Convert the current state of a shape into String(For testing purposes).
   *
   * @return a string representing current state
   */
  public String toStringTest() {
    return (this.time + 1) + " " + this.name + " " + this.width + " " + this.height
        + " " + this.r + " " + this.g + " " + this.b + " " + this.xposn + " " + this.yposn +
        " " + this.degree + "\n";
  }


  /**
   * Getter method for getting the width.
   *
   * @return the width of the shape
   */
  public double getWidth() {
    return width;
  }

  /**
   * Setter method for width.
   *
   * @param width the width we want to set it to
   */
  public void setWidth(double width) {
    this.width = width;
  }

  /**
   * Getter method for height.
   *
   * @return height of the shape
   */
  public double getHeight() {
    return height;
  }

  /**
   * Setter method for height.
   *
   * @param height height we want to set it to
   */
  public void setHeight(double height) {
    this.height = height;
  }

  /**
   * Getter method for redness(color).
   *
   * @return R color for the shape
   */
  public double getR() {
    return r;
  }

  /**
   * setter methods for R.
   *
   * @param r color of the shape
   */
  public void setR(double r) {
    this.r = r;
  }

  /**
   * getter method for G.
   *
   * @return greeness of the color
   */
  public double getG() {
    return g;
  }

  /**
   * Setter method for Greenness.
   *
   * @param g the value we want to set greenness
   */
  public void setG(double g) {
    this.g = g;
  }

  /**
   * Getter method for Blue.
   *
   * @return return blue value
   */
  public double getB() {
    return b;
  }

  /**
   * Setter method for blue.
   *
   * @param b value for blue
   */
  public void setB(double b) {
    this.b = b;
  }


  /**
   * Getter method for xposition.
   *
   * @return x position of the shape
   */
  public double getXposn() {
    return xposn;
  }

  /**
   * Setter method for x position.
   *
   * @param xposn x position of the shape
   */
  public void setXposn(double xposn) {
    this.xposn = xposn;
  }

  /**
   * Getter method for Y.
   *
   * @return Y position
   */
  public double getYposn() {
    return yposn;
  }

  /**
   * Setter method for y.
   *
   * @param yposn y position we want to set
   */
  public void setYposn(double yposn) {
    this.yposn = yposn;
  }


  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public double getDegree() {
    return degree;
  }

  public void setDegree(double degree) {
    this.degree = degree;
  }
}
