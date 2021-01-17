package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The implementation of the Model interface, where data with shapes/its motions is stored as well
 * as the dimensions of the canvas.
 */
public class ExcellenceModel implements AnimationModel {

  //  private String log;
  private ArrayList<Shape> shapes;
  private int width;
  private int height;
  private int x;
  private int y;

  /**
   * Constructor for the Model with default dimensions for the model as 0, 0, 0 and 0.
   */
  public ExcellenceModel() {
    this.shapes = new ArrayList<>();
    this.width = 0;
    this.height = 0;
    this.x = 0;
    this.y = 0;
  }

  /**
   * Constructor but with specific dimensions for the model.
   *
   * @param width  the width of the model.
   * @param height the height of the model.
   * @param x      the x coordinate the model starts.
   * @param y      the y coordinate the model starts.
   */
  ExcellenceModel(int width, int height, int x, int y) {
    this.shapes = new ArrayList<>();
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  /**
   * Sets new dimensions of the model.
   *
   * @param width  the width of the model.
   * @param height the height of the model.
   * @param x      the x coordinate the model starts.
   * @param y      the y coordinate the model starts.
   */
  @Override
  public void setCanvasDimensions(int width, int height, int x, int y) {
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the width of the canvas.
   *
   * @return the width of the canvas.
   */
  @Override
  public int getCanvasWidth() {
    return this.width;
  }

  /**
   * Returns the height of the canvas.
   *
   * @return the height of the canvas.
   */
  @Override
  public int getCanvasHeight() {
    return this.height;
  }

  /**
   * Returns the x position of the canvas.
   *
   * @return the x position of the canvas.
   */
  @Override
  public int getCanvasX() {
    return this.x;
  }

  /**
   * Returns the y position of the canvas.
   *
   * @return the y position of the canvas.
   */
  @Override
  public int getCanvasY() {
    return this.y;
  }

  /**
   * Adding a predefined shape to the animation.
   *
   * @param s shape
   */
  @Override
  public void addShape(Shape s) {
    if (this.getShapeNameIndex(s.getName()) == -1) {
      shapes.add(s);
    }

  }

  /**
   * Another method for adding the shape, but creates a shape with custom parameters.
   *
   * @param name   name of the shape.
   * @param type   type of the shape
   * @param width  width of the shape
   * @param height height of the shape
   * @param r      Redness of the shape
   * @param g      Greenness of the shape
   * @param b      Blueness of the shape
   * @param xposn  XPosition of the shape
   * @param yposn  YPosition of the shape
   */
  @Override
  public void addShape(String name, String type, int width, int height, int r, int g, int b,
      int xposn,
      int yposn, int degree) {
    if (this.getShapeNameIndex(name) == -1) {
      shapes.add(new Shape(name, type, width, height, r, g, b, xposn, yposn, degree));
    }
  }

  /**
   * Change the position of a shape at given duration.
   *
   * @param n         the name of the shape
   * @param startTime start time of the change position
   * @param endTime   end time of the change position
   * @param movetoX   X position user want to move to in this duration
   * @param movetoY   Y position user want to move to in this duration
   */
  @Override
  public void changePosn(String n, int startTime, int endTime, int movetoX, int movetoY) {
    if (startTime <= 0 || endTime <= 0) {
      throw new IllegalArgumentException("Time cannot be equal or below 0!");
    }
    int index = this.findShape(n);
    if (index == -1) {
      throw new IllegalArgumentException("Cannot find the shape");
    }
    if (!this.preChecker(n, startTime, endTime, PreviousMove.move)) {
      throw new IllegalArgumentException("Prechecker did not pass");
    }

    Shape obj = shapes.get(index);
    obj.changeMoving(movetoX, movetoY);

    obj.updateInstantState(startTime, endTime, "move", obj.getName(), obj.getWidth(),
        obj.getHeight(),
        obj.getR(), obj.getG(), obj.getB(), movetoX, movetoY, obj.getDegree());
    obj.moveDuration.add(new Duration(startTime, endTime));
  }

  /**
   * Change the color of shape at a given duration.
   *
   * @param n         the name of the shape
   * @param startTime start time of the change position
   * @param endTime   end time of the change position
   * @param givenr    R value user given to change color
   * @param giveng    G value user given to change color
   * @param givenb    B value user given to change color
   */
  @Override
  public void changeColor(String n, int startTime, int endTime, int givenr, int giveng,
      int givenb) {

    if (givenr < 0 || givenr > 255 || giveng < 0 || giveng > 255 || givenb < 0 || givenb > 255) {
      throw new IllegalArgumentException("Invalid color!");
    }
    if (startTime <= 0 || endTime <= 0) {
      throw new IllegalArgumentException("Time cannot be equal or below 0!");
    }
    int index = this.findShape(n);
    if (index == -1) {
      throw new IllegalArgumentException("Cannot find the shape");
    }
    if (!this.preChecker(n, startTime, endTime, PreviousMove.color)) {
      throw new IllegalArgumentException("Prechecker did not pass");
    }
    Shape obj = shapes.get(index);

    obj.changeColor(givenr, giveng, givenb);

    //trying code
    obj.updateInstantState(startTime, endTime, "color", obj.getName(), obj.getWidth(),
        obj.getHeight(),
        givenr, giveng, givenb, obj.getXposn(), obj.getYposn(), obj.getDegree());
    obj.colorDuration.add(new Duration(startTime, endTime));
  }

  /**
   * Change the size of a shape at a given time.
   *
   * @param n         the name of the shape
   * @param startTime start time of the change position
   * @param endTime   end time of the change position
   * @param gwidth    width user want to change to in this duration
   * @param gheight   height user want to change to in this duration
   */
  @Override
  public void changeSize(String n, int startTime, int endTime, int gwidth, int gheight) {
    if (startTime <= 0 || endTime <= 0) {
      throw new IllegalArgumentException("Time cannot be equal or below 0!");
    }
    int index = this.findShape(n);
    if (index == -1) {
      throw new IllegalArgumentException("Cannot find the shape");
    }
    if (!this.preChecker(n, startTime, endTime, PreviousMove.size)) {
      throw new IllegalArgumentException("Prechecker did not pass");
    }
    Shape obj = shapes.get(index);

    obj.changeSize(gwidth, gheight);

    obj.updateInstantState(startTime, endTime, "size", obj.getName(), gwidth,
        gheight, obj.getR(), obj.getG(), obj.getB(), obj.getXposn(), obj.getYposn(),
        obj.getDegree());
    obj.sizeDuration.add(new Duration(startTime, endTime));
  }

  /**
   * Rotate the shape to a specified degree.
   *
   * @param n         name of the shape
   * @param startTime start time of the rotating motion
   * @param endTime   end time of the rotating motion
   * @param degree    degree of rotation into
   */
  @Override
  public void rotateShape(String n, int startTime, int endTime, int degree) {
    if (startTime <= 0 || endTime <= 0) {
      throw new IllegalArgumentException("Time cannot be equal or below 0!");
    }
    int index = this.findShape(n);
    if (index == -1) {
      throw new IllegalArgumentException("Cannot find the shape");
    }
    if (!this.preChecker(n, startTime, endTime, PreviousMove.size)) {
      throw new IllegalArgumentException("Prechecker did not pass");
    }
    Shape obj = shapes.get(index);

    obj.updateInstantState(startTime, endTime, "rotate", obj.getName(), obj.getWidth(),
        obj.getHeight(),
        obj.getR(), obj.getG(), obj.getB(), obj.getXposn(), obj.getYposn(), degree);
    obj.rotateDuration.add(new Duration(startTime, endTime));
  }

  /**
   * Do nothing in a given duration for a shape.
   *
   * @param n         the name of the shape
   * @param startTime start time of doing nothing
   * @param endTime   end time of doing nothing
   */
  @Override
  public void doNothing(String n, int startTime, int endTime) {
    if (startTime <= 0 || endTime <= 0) {
      throw new IllegalArgumentException("Time cannot be equal or below 0!");
    }
    int index = this.findShape(n);
    if (index == -1) {
      System.out.println(n);
      throw new IllegalArgumentException("Cannot find the shape");
    }
    if (!this.preChecker(n, startTime, endTime, PreviousMove.nothing)) {
      throw new IllegalArgumentException("Prechecker did not pass");
    }
    Shape obj = shapes.get(index);
    InstantState finalstate = obj.getStateBySecond().get(startTime - 1);
    double finaldegree = obj.getStateBySecond().get(startTime - 1).getDegree();

    obj.updateInstantState(startTime, endTime, "nothing",
        finalstate.getName(), finalstate.getWidth(),
        finalstate.getHeight(),
        finalstate.getR(), finalstate.getG(), finalstate.getB(), finalstate.getXposn(),
        finalstate.getYposn(),
        finaldegree);
    obj.nothingDuration.add(new Duration(startTime, endTime));

  }


  private int findShape(String given) {
    for (int i = 0; i < this.shapes.size(); i++) {
      if (this.shapes.get(i).getName().equals(given)) {
        return i;
      } else {
        continue;
      }
    }
    return -1;
  }


  private boolean preChecker(String n, int startTime, int endTime, PreviousMove current) {
    int index = this.findShape(n);
    if (index == -1) {
      throw new IllegalArgumentException("Cannot find the shape");

    }
    Shape obj = shapes.get(index);

    if (current.equals(PreviousMove.move)) {
      return !obj.checkOverlap(startTime, endTime, obj.moveDuration);
    } else if (current.equals(PreviousMove.color)) {
      return !obj.checkOverlap(startTime, endTime, obj.colorDuration);
    } else if (current.equals(PreviousMove.size)) {
      return !obj.checkOverlap(startTime, endTime, obj.sizeDuration);
    } else if (current.equals(PreviousMove.rotate)) {
      return !obj.checkOverlap(startTime, endTime, obj.rotateDuration);
    } else {
      return !obj.checkOverlap(startTime, endTime, obj.sizeDuration)
          && !obj.checkOverlap(startTime, endTime, obj.colorDuration)
          && !obj.checkOverlap(startTime, endTime, obj.moveDuration)
          && !obj.checkOverlap(startTime, endTime, obj.rotateDuration);
    }

  }

  /**
   * getting the log for all the shapes in this animation.
   *
   * @return the log for all the shapes in this animation.
   */
  @Override
  public String getLog() {
    String newLog = "";

    for (int i = 0; i < this.shapes.size(); i++) {
      ArrayList<InstantState> currentStatelog = this.shapes.get(i).stateBySecond;

      if (i != 0) {
        newLog = newLog + "\nShape " + this.shapes.get(i).getName() + " " +
            this.shapes.get(i).getType() + "\n";
      } else {
        newLog = newLog + "Shape " + this.shapes.get(i).getName() + " " +
            this.shapes.get(i).getType() + "\n";
      }

      if (this.shapes.get(i).checkGaps()) {
        throw new IllegalArgumentException("There is gap!");
      }

      for (int j = 0; j < currentStatelog.size(); j++) {

        if (j == 0) {
          newLog = newLog + "Motion " + this.shapes.get(i).getName() + " " + currentStatelog.get(j)
              .toString() + "   ";
          continue;
        } else if (this.shapes.get(i).checkImportantPoints(j + 1)) {
          if (j == currentStatelog.size() - 1) {
            newLog = newLog + currentStatelog.get(j).toString();
            break;
          } else {
            newLog =
                newLog + currentStatelog.get(j).toString() + "\n" + "Motion " + this.shapes.get(i)
                    .getName() + " "
                    + currentStatelog.get(j).toString() + "   ";
            continue;
          }
        } else {
          continue;
        }
      }

    }
    return newLog;
  }

  /**
   * Convert a list of motions into list of keyframes. Very useful when converting list of
   * instantstate to keyframe.
   */
  @Override
  public void convertMotionToKeyFrame() {
    for (int i = 0; i < this.shapes.size(); i++) {
      //added 6/13/2020 9pm
      this.shapes.get(i).setKeyFrame(new ArrayList<>());
      ArrayList<InstantState> currentStatelog = this.shapes.get(i).stateBySecond;
      ArrayList<InstantState> keyFrameLog = new ArrayList<>();
      for (int j = 0; j < currentStatelog.size(); j++) {
        InstantState adjustedObject = new InstantState(currentStatelog.get(j).getTime() + 1,
            currentStatelog.get(j).getName(), currentStatelog.get(j).getWidth(),
            currentStatelog.get(j).getHeight(), Math.round(currentStatelog.get(j).getR()),
            Math.round(currentStatelog.get(j).getG()),
            Math.round(currentStatelog.get(j).getB()), currentStatelog.get(j).getXposn(),
            currentStatelog.get(j).getYposn(), currentStatelog.get(j).getDegree());

        if (adjustedObject.getWidth() == 0 && adjustedObject.getHeight() == 0
            && adjustedObject.getR() == 0 && adjustedObject.getG() == 0
            && adjustedObject.getB() == 0 && adjustedObject.getXposn() == 0
            && adjustedObject.getYposn() == 0 && adjustedObject.getDegree() == 0) {
          continue;
        }
        if (j == 0) {
          keyFrameLog.add(adjustedObject);
          continue;
        } else if (this.shapes.get(i).checkImportantPoints(j + 1)) {
          keyFrameLog.add(adjustedObject);
        } else {
          continue;
        }
      }
      this.shapes.get(i).setKeyFrame(keyFrameLog);
    }
  }

  /**
   * getter method for the shape list of this model.
   *
   * @return list of shapes in the model
   */
  @Override
  public ArrayList<Shape> getShapes() {
    return shapes;
  }

  /**
   * Removes a shape from the list of shapes.
   *
   * @param name the name of the shape to be removed.
   */
  @Override
  public void removeShape(String name) {
    for (Shape s : this.shapes) {
      if (s.getName().equals(name)) {
        shapes.remove(s);
        break;
      }
    }
  }

  /**
   * Removes a motion of a specific type from a specific shape within the list of shapes, if there
   * is no shape with the name or no motion that is exactly within the interval and of the same type
   * within the shape, do nothing.
   *
   * @param name  the name of the shape with the motion.
   * @param type  the type of motion to be removed.
   * @param start the start time of the motion.
   * @param end   the end time of the motion.
   */
  @Override
  public void removeMotion(String name, String type, int start, int end) {
    for (Shape s : this.shapes) {
      if (s.getName().equals(name)) {
        s.removeMoveAtInterval(start, end, type);
        break;
      }
    }
  }

  /**
   * Returns the properties of a shape of a given name.
   *
   * @param name the name of the shape.
   * @return the type of shape, position, size and color as a String. Return null if no shape.
   */
  @Override
  public String getShapeProperties(String name) {
    for (Shape s : this.shapes) {
      if (s.getName().equals(name)) {
        String newLog = s.getType() + "\n";
        List<InstantState> currentStatelog = s.stateBySecond;
        for (int j = 0; j < currentStatelog.size(); j++) {

          if (j == 0) {
            newLog = newLog + "Motion " + s.getName() + " " + currentStatelog.get(j)
                .toString() + "   ";
            continue;
          } else if (s.checkImportantPoints(j + 1)) {
            if (j == currentStatelog.size() - 1) {
              newLog = newLog + currentStatelog.get(j).toString();
              break;
            } else {
              newLog =
                  newLog + currentStatelog.get(j).toString() + "\n" + "Motion "
                      + s.getName() + " " +
                      currentStatelog.get(j).toString() + "   ";
              continue;
            }

          } else {
            continue;
          }
        }
        return newLog;
      }
    }
    return null;
  }

  /**
   * Given a shape name, and a type of motion, and an interval, get properties of the motion.
   *
   * @param shapeName the name of the shape.
   * @param type      the type of motion.
   * @param start     the start time of the motion.
   * @param end       the end time of the motion.
   * @return a description of the motion's value changes, or null if there is no shape with name.
   */
  @Override
  public String getMotionProperties(String shapeName, String type, int start, int end) {
    for (Shape s : this.shapes) {
      if (s.getName().equals(shapeName)) {
        if (type.equalsIgnoreCase("move")) {
          for (Duration d : s.moveDuration) {
            if (d.sameTimeFrame(start, end)) {
              return s.getName() + " " + Math.round(s.stateBySecond.get(start - 1).getXposn())
                  + " " +
                  Math.round(s.stateBySecond.get(start - 1).getYposn()) + " " +
                  Math.round(s.stateBySecond.get(end - 1).getXposn()) +
                  " " + Math.round(s.stateBySecond.get(end - 1).getYposn());
            }
          }
        } else if (type.equalsIgnoreCase("color")) {
          for (Duration d : s.colorDuration) {
            if (d.sameTimeFrame(start, end)) {
              return s.getName() + " " + Math.round(s.stateBySecond.get(start - 1).getR()) + " " +
                  Math.round(s.stateBySecond.get(start - 1).getG()) + " " +
                  Math.round(s.stateBySecond.get(start - 1).getB()) + " " +
                  Math.round(s.stateBySecond.get(end - 1).getR()) + " " +
                  Math.round(s.stateBySecond.get(end - 1).getG()) +
                  " " + Math.round(s.stateBySecond.get(end - 1).getB());
            }
          }
        } else if (type.equalsIgnoreCase("size")) {
          for (Duration d : s.sizeDuration) {
            if (d.sameTimeFrame(start, end)) {
              return s.getName() + " " +
                  Math.round(s.stateBySecond.get(start - 1).getWidth()) + " " +
                  Math.round(s.stateBySecond.get(start - 1).getHeight()) + " " +
                  Math.round(s.stateBySecond.get(end - 1).getWidth()) +
                  " " + Math.round(s.stateBySecond.get(end - 1).getHeight());
            }
          }
        }
      }
    }
    return null;
  }

  /**
   * Given a shape name, return its index in the list inside the model. Return -1 when it is unable
   * to find the shape.
   *
   * @param name name of the shape
   * @return the index of the shape in the list
   */
  @Override
  public int getShapeNameIndex(String name) {
    for (int i = 0; i < this.shapes.size(); i++) {
      if (this.shapes.get(i).getName().equalsIgnoreCase(name)) {
        return i;
      } else {
        continue;
      }
    }
    return -1;
  }


  /**
   * Adds an individual keyframe to the growing document.
   *
   * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
   * @param type The type of the shape
   * @param t    The time for this keyframe
   * @param x    The x-position of the shape
   * @param y    The y-position of the shape
   * @param w    The width of the shape
   * @param h    The height of the shape
   * @param r    The red color-value of the shape
   * @param g    The green color-value of the shape
   * @param b    The blue color-value of the shape
   */
  @Override
  public void addKeyframe(String name, String type, int t, int x, int y, int w,
      int h, int r, int g, int b, int degree) {
    if (t <= 0) {
      throw new IllegalArgumentException("Invalid time!");
    }
    if (checkShapeName(name)) {
      System.out.println("Shape does not exist!");
      return;
    } else {
      for (int i = 0; i < this.getShapes().size(); i++) {
        if (this.getShapes().get(i).getName().equalsIgnoreCase(name)) {
          this.getShapes().get(i)
              .addKeyFramePiece(new InstantState(t, name, w, h, r, g, b, x, y, degree));
          break;
        } else {
          continue;
        }
      }
    }
  }

  /**
   * Delete a keyframe from the list given the shape name and time.
   *
   * @param name name of the shape given
   * @param t    time of the keyframe to be deleted
   */
  @Override
  public void deleteKeyFrame(String name, int t) {
    for (int i = 0; i < this.shapes.size(); i++) {
      if (this.shapes.get(i).getName().equalsIgnoreCase(name)) {
        for (int j = 0; j < this.shapes.get(i).keyFrame.size(); j++) {
          if (this.shapes.get(i).keyFrame.get(j).getTime() == t) {
            this.shapes.get(i).keyFrame.remove(j);
            return;
          }
        }
      } else {
        continue;
      }
    }
    //when the code cannot find the shape
    System.out.println("Cannot find the shape!");
  }


  /**
   * Check if the current model have shape with same name True if no collided name, False if the
   * name already exists.
   */
  private boolean checkShapeName(String shapename) {
    for (int i = 0; i < this.shapes.size(); i++) {
      if (this.shapes.get(i).getName().equalsIgnoreCase(shapename)) {
        return false;
      } else {
        continue;
      }
    }
    return true;
  }

  /**
   * Translate a list of keyframe to instantStateLog. Super helpful whe refresh the instantstate
   * list from keyframe.
   */
  @Override
  public void keyFrameToInstantStateLog() {
    for (int i = 0; i < this.shapes.size(); i++) {
      //try 6/14 2pm
      ArrayList<InstantState> keyFrameLog = this.shapes.get(i).getKeyFrame();

      if (keyFrameLog.size() == 1) {
        continue;
      }

      InstantState initialState;
      if (this.shapes.get(i).keyFrame.get(0).getTime() == 1) {

        InstantState previousState = this.shapes.get(i).keyFrame.get(0);
        initialState = new InstantState(previousState.getTime() - 1,
            previousState.getName(), previousState.getWidth(), previousState.getHeight(),
            previousState.getR(), previousState.getG(), previousState.getB(),
            previousState.getXposn(),
            previousState.getYposn(), previousState.getDegree());
      } else if (this.shapes.get(i).keyFrame.get(0).getTime() > 1) {
        initialState = new InstantState(0, this.shapes.get(i).getName(), 0, 0, 0, 0, 0,
            0, 0, 0);

        //try code 6/15
        this.shapes.get(i).setXposn(0);
        this.shapes.get(i).setYposn(0);
        this.shapes.get(i).setR(0);
        this.shapes.get(i).setG(0);
        this.shapes.get(i).setB(0);
        this.shapes.get(i).setWidth(0);
        this.shapes.get(i).setHeight(0);
        this.shapes.get(i).setDegree(0);
      } else {
        InstantState previousState = this.shapes.get(i).keyFrame.get(0);
        initialState = new InstantState(0,
            previousState.getName(), previousState.getWidth(), previousState.getHeight(),
            previousState.getR(), previousState.getG(), previousState.getB(),
            previousState.getXposn(),
            previousState.getYposn(), previousState.getDegree());
      }

      this.shapes.get(i).setStateBySecond(new ArrayList<>(
          Arrays.asList(initialState)
      ));
      this.shapes.get(i).setMoveDuration(new ArrayList<>());
      this.shapes.get(i).setSizeDuration(new ArrayList<>());
      this.shapes.get(i).setColorDuration(new ArrayList<>());
      this.shapes.get(i).setNothingDuration(new ArrayList<>());
      this.shapes.get(i).setRotateDuration(new ArrayList<>());

      if (keyFrameLog.get(0).getTime() > 1) {
        for (int k = 0; k < keyFrameLog.size(); k++) {
          if (k == 0) {
            int time = keyFrameLog.get(0).getTime();
            InstantState fakeInitialState = new InstantState(1, keyFrameLog.get(0).getName(),
                0, 0, 0, 0, 0, 0, 0, 0);
            InstantState fakeBeforeShapeAppear = new InstantState(time - 1,
                keyFrameLog.get(0).getName(),
                0, 0, 0, 0, 0, 0, 0, 0);
            keyFrameTranslateHelper(fakeInitialState, fakeBeforeShapeAppear);
            keyFrameTranslateHelper(fakeBeforeShapeAppear, keyFrameLog.get(0));
            keyFrameTranslateHelper(keyFrameLog.get(0), keyFrameLog.get(1));
            continue;
          } else {
            if (k + 1 == keyFrameLog.size()) {
              continue;
            } else {
              keyFrameTranslateHelper(keyFrameLog.get(k), keyFrameLog.get(k + 1));
            }
          }
        }
      } else if (keyFrameLog.get(0).getTime() < 1) {
        System.out.println("Invalid time!");
        return;
      } else {
        for (int j = 0; j < keyFrameLog.size(); j++) {
          if (j == 0) {
            InstantState currentstateBySec = this.shapes.get(i).stateBySecond.get(0);
            InstantState primaryState = new InstantState(1, currentstateBySec.getName(),
                currentstateBySec.getWidth(), currentstateBySec.getHeight(),
                currentstateBySec.getR(), currentstateBySec.getG(),
                currentstateBySec.getB(), currentstateBySec.getXposn(),
                currentstateBySec.getYposn(), currentstateBySec.getDegree());
            this.shapes.get(i).setXposn(currentstateBySec.getXposn());
            this.shapes.get(i).setYposn(currentstateBySec.getYposn());
            this.shapes.get(i).setR(currentstateBySec.getR());
            this.shapes.get(i).setG(currentstateBySec.getG());
            this.shapes.get(i).setB(currentstateBySec.getB());
            this.shapes.get(i).setWidth(currentstateBySec.getWidth());
            this.shapes.get(i).setHeight(currentstateBySec.getHeight());
            this.shapes.get(i).setDegree(currentstateBySec.getDegree());

            keyFrameTranslateHelper(primaryState, keyFrameLog.get(1));
            j++;

            if (j + 1 != keyFrameLog.size()) {
              keyFrameTranslateHelper(keyFrameLog.get(j), keyFrameLog.get(j + 1));
            }
            continue;
          }
          if (j + 1 == keyFrameLog.size()) {
            continue;
          } else {
            keyFrameTranslateHelper(keyFrameLog.get(j), keyFrameLog.get(j + 1));
          }
        }
      }
    }
  }


  private void keyFrameTranslateHelper(InstantState first, InstantState second) {
    if ((first.getXposn() - second.getXposn() != 0) ||
        (first.getYposn() - second.getYposn() != 0)) {
      this.changePosn(first.getName(), first.getTime(),
          second.getTime(), (int) second.getXposn(),
          (int) second.getYposn());
    }
    if ((first.getWidth() - second.getWidth() != 0) ||
        (first.getHeight() - second.getHeight() != 0)) {
      this.changeSize(first.getName(), first.getTime(),
          second.getTime(), (int) second.getWidth(),
          (int) second.getHeight());
    }
    if ((first.getR() - second.getR() != 0) ||
        (first.getG() - second.getG() != 0) ||
        (first.getB() - second.getB() != 0)) {
      this.changeColor(first.getName(), first.getTime(),
          second.getTime(), (int) second.getR(),
          (int) second.getG(), (int) second.getB());
    }

    if (first.getDegree() - second.getDegree() != 0) {
      this.rotateShape(first.getName(), first.getTime(),
          second.getTime(), (int) second.getDegree());
    }

    if (changeBetweenKeyFrame(first, second)) {
      this.doNothing(first.getName(), first.getTime(),
          second.getTime());
    }
  }


  private boolean changeBetweenKeyFrame(InstantState first, InstantState second) {
    return (first.getXposn() == second.getXposn() && first.getYposn() == second.getYposn()
        && first.getWidth() == second.getWidth() && first.getHeight() == second.getHeight()
        && first.getR() == second.getR() && first.getG() == second.getG() && first.getB() == second
        .getB() && first.getDegree() == second.getDegree());
  }


  /**
   * Edit a specific keyframe in the keyframe list.
   *
   * @param name     name of the shape
   * @param changeTo Model.InstantState to change into.
   */
  @Override
  public void editKeyFrame(String name, InstantState changeTo) {
    if (changeTo.getR() < 0 || changeTo.getR() > 255 || changeTo.getG() < 0
        || changeTo.getG() > 255 || changeTo.getB() < 0 || changeTo.getB() > 255
        || changeTo.getWidth() < 0 || changeTo.getHeight() < 0) {
      throw new IllegalArgumentException("Invalid input!");
    }
    for (int i = 0; i < this.shapes.size(); i++) {
      if (this.shapes.get(i).getName().equalsIgnoreCase(name)) {
        for (int j = 0; j < this.shapes.get(i).keyFrame.size(); j++) {
          if (changeTo.getTime() == this.shapes.get(i).keyFrame.get(j).getTime()) {
            this.shapes.get(i).keyFrame.set(j, changeTo);
            return;
          }
        }
      }
    }
    //if it cannot find the given shape.
    System.out.println("Cannot find the given shape, try again!");
  }

  /**
   * Set the tick info for all the shape within the model to a specified value.
   *
   * @param giventime given tick value to be set to.
   */
  @Override
  public void setAllTick(int giventime) {
    for (int i = 0; i < this.shapes.size(); i++) {
      this.shapes.get(i).setTick(giventime);
    }
  }

  /**
   * Setting the proper layer information for a shape.
   *
   * @param name  name of the shape
   * @param layer layer to set to.
   */
  @Override
  public void setLayerForAShape(String name, int layer) {
    int index = this.getShapeNameIndex(name);
    if (index == -1) {
      throw new IllegalArgumentException("Invalid Name!");
    }
    this.shapes.get(index).setLayer(layer);
  }

  /**
   * Remove all the shape available within the given layer.
   *
   * @param layer given layer value
   */
  @Override
  public void removeAllShapeWithinLayer(int layer) {
    if (layer < 1) {
      System.out.println("Invalid layer value!");
      return;
    } else {
      ArrayList<Shape> copylist = new ArrayList<>();
      for (int i = 0; i < this.shapes.size(); i++) {
        copylist.add(this.shapes.get(i));
      }
      int currentSize = this.shapes.size();
      for (int i = 0; i < currentSize; i++) {
        if (copylist.get(i).getLayer() == layer) {
          copylist.remove(i);
          i--;
          currentSize--;
        }
      }
      this.shapes = copylist;
    }
  }

  /**
   * Builder class, help the animationReader class read information from the file.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    private AnimationModel theModel = new ExcellenceModel();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> typeList = new ArrayList<>();
    private ArrayList<Integer> layerlist = new ArrayList<>();


    /**
     * Constructs a final document.
     *
     * @return the newly constructed document
     */
    @Override
    public AnimationModel build() {
      return theModel;
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      theModel.setCanvasDimensions(width, height, x, y);
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type, int layer) {
      nameList.add(name);
      typeList.add(type);
      layerlist.add(layer);
      return this;
    }


    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int d1, int t2, int x2, int y2, int w2, int h2, int r2,
        int g2,
        int b2, int d2) {
      if ((t2 < t1) || t1 <= 0 || t2 <= 0) {
        throw new IllegalArgumentException("Motion is invalid!");
      }

      if (t1 == t2 && t1 != 1 && t2 != 1) {
        int index = nameList.indexOf(name);
        String actualtype = typeList.get(index);
        Integer layerinfo = layerlist.get(index);
        Shape newshape = new Shape(name, actualtype, 0, 0, 0, 0, 0, 0, 0, 0);
        newshape.setLayer(layerinfo);
        theModel.addShape(newshape);

        theModel.doNothing(name, 1, t2 - 1);
        theModel.changePosn(name, t2 - 1, t2, x2, y2);
        theModel.changeColor(name, t2 - 1, t2, r2, g2, b2);
        theModel.changeSize(name, t2 - 1, t2, w2, h2);
        theModel.rotateShape(name, t2 - 1, t2, d2);
      }

      if (t1 == 1 && t2 == 1) {
        int index = nameList.indexOf(name);
        String actualtype = typeList.get(index);
        Integer layerinfo = layerlist.get(index);
        Shape newshape = new Shape(name, actualtype, w2, h2, r2, g2, b2, x2, y2, d2);
        newshape.setLayer(layerinfo);
        theModel.addShape(newshape);
        return this;
      } else {
        if (x2 - x1 != 0 || y2 - y1 != 0) {
          theModel.changePosn(name, t1, t2, x2, y2);
        }
        if (r2 - r1 != 0 || g2 - g1 != 0 || b2 - b1 != 0) {
          theModel.changeColor(name, t1, t2, r2, g2, b2);
        }
        if (w2 - w1 != 0 || h2 - h1 != 0) {
          theModel.changeSize(name, t1, t2, w2, h2);
        }
        if (d2 - d1 != 0) {
          theModel.rotateShape(name, t1, t2, d2);
        }
        if (x1 == x2 && y1 == y2 && r1 == r2 && g1 == g2 && b1 == b2 && w1 == w2 && h1 == h2
            && d1 == d2) {
          theModel.doNothing(name, t1, t2);
        }
        return this;
      }
    }

    /**
     * Adds an individual keyframe to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t    The time for this keyframe
     * @param x    The x-position of the shape
     * @param y    The y-position of the shape
     * @param w    The width of the shape
     * @param h    The height of the shape
     * @param r    The red color-value of the shape
     * @param g    The green color-value of the shape
     * @param b    The blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
        int h, int r, int g, int b, int d) {
      for (int i = 0; i < theModel.getShapes().size(); i++) {
        if (theModel.getShapes().get(i).getName().equalsIgnoreCase(name)) {
          if (t == 1) {
            int index = nameList.indexOf(name);
            Integer layerinfo = layerlist.get(index);
            String actualtype = typeList.get(index);
            Shape newshape = new Shape(name, actualtype, w, h, r, g, b, x, y, d);
            newshape.setLayer(layerinfo);
            theModel.addShape(newshape);
            return this;
          } else {
            theModel.getShapes().get(i)
                .addKeyFramePiece(new InstantState(t, name, w, h, r, g, b, x, y, d));
            return this;
          }
        } else {
          continue;
        }
      }
      return this;
    }

  }

}
