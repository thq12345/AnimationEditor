package cs3500.animator.model;

import java.util.ArrayList;


/**
 * The class for a Model.Shape and all its properties, color, size, type and location.
 */
public class Shape {

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
  private String type;
  //private counter
  private int tick;
  //degree of the shape
  private double degree;

  private int layer;

  public ArrayList<InstantState> stateBySecond = new ArrayList<>();
  ArrayList<InstantState> keyFrame = new ArrayList<>();
  ArrayList<Duration> moveDuration = new ArrayList<>();
  ArrayList<Duration> colorDuration = new ArrayList<>();
  ArrayList<Duration> sizeDuration = new ArrayList<>();
  ArrayList<Duration> nothingDuration = new ArrayList<>();
  ArrayList<Duration> rotateDuration = new ArrayList<>();

  /**
   * The constructor representing state of the shape.
   *
   * @param name   the type of shape.
   * @param width  the width of the shape.
   * @param height the height of the shape.
   * @param r      the R value of the shape.
   * @param g      the G value of the shape.
   * @param b      the B value of the shape.
   * @param xposn  the x coordinate of the shape.
   * @param yposn  the y coordinate oft he shape.
   */
  public Shape(String name, String type, int width, int height, int r, int g, int b, int xposn,
      int yposn, int degree) {
    this.name = name;
    this.type = type;
    this.width = width;
    this.height = height;
    this.r = r;
    this.g = g;
    this.b = b;
    this.xposn = xposn;
    this.yposn = yposn;
    this.stateBySecond.add(new InstantState(0, name, width, height, r, g, b, xposn, yposn, degree));
    this.tick = 0;
    this.degree = degree;
    this.layer = 1;
  }

  /**
   * check if there exists overlap between the given duration and duration on the list.
   *
   * @param startTime      start time of the duration we want to check
   * @param endTime        endtime of the duration we want to check
   * @param giventimeframe list of duration we want to check with
   * @return boolean indicating if given duration has any overlap with durations on the list
   */
  public boolean checkOverlap(int startTime, int endTime, ArrayList<Duration> giventimeframe) {
    for (int i = 0; i < giventimeframe.size(); i++) {
      if ((giventimeframe.get(i).startTime < startTime && giventimeframe.get(i).endTime > startTime)
          || (giventimeframe.get(i).startTime < endTime
          && giventimeframe.get(i).endTime > endTime)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Getter method for getting the type.
   *
   * @return type of the shape
   */
  public String getType() {
    return this.type;
  }

  /**
   * Move this object.
   *
   * @param movetoX X position it wants to move to
   * @param movetoY Y position it wants to move to
   */
  public void changeMoving(double movetoX, double movetoY) {
    /*
    append log later.
     */
    this.xposn = movetoX;
    this.yposn = movetoY;
  }

  /**
   * Change color of this shape.
   *
   * @param toR Red we want to change to
   * @param toG Green we want to change to
   * @param toB Blue we want to change to
   */
  public void changeColor(int toR, int toG, int toB) {
    this.r = toR;
    this.g = toG;
    this.b = toB;
  }

  /**
   * Size we want to change ths shape to.
   *
   * @param width  width we want to change to
   * @param height height we want to change to
   */
  public void changeSize(int width, int height) {
    this.width = width;
    this.height = height;
  }

  /**
   * Degree we want to change the shape to.
   *
   * @param degree degree of the shape we want to change to
   */
  public void rotate(int degree) {
    this.degree = degree;
  }


  /**
   * Update the instant state of the shape by tick (update the list) If duration does not exist, it
   * will add the duration into the instant state If the duration does exist, it will find the
   * duration and update.
   *
   * @param starttime start time of the duration
   * @param endtime   end time of the duration
   * @param command   which operation we want to do
   * @param name      name of the shape
   * @param width     width of the shape
   * @param height    height of the shape
   * @param r         red of the shape
   * @param g         green of the shape
   * @param b         blue of the shape
   * @param xposn     xposition of the shape
   * @param yposn     yposition of the shape
   */
  public void updateInstantState(int starttime, int endtime, String command, String name,
      double width, double height, double r, double g, double b,
      double xposn, double yposn, double degree) {
    switch (command) {
      case "move":
        int currentsize = this.stateBySecond.size();
        double oldx = this.stateBySecond.get(currentsize - 1).getXposn();
        double oldy = this.stateBySecond.get(currentsize - 1).getYposn();

        double incrementX = (xposn - oldx) / (endtime - starttime);
        double incrementY = (yposn - oldy) / (endtime - starttime);

        double incrementedX = this.stateBySecond.get(currentsize - 1).getXposn();
        double incrementedY = this.stateBySecond.get(currentsize - 1).getYposn();
        if (endtime < this.stateBySecond.size() && starttime < this.stateBySecond.size()) {
          for (int i = starttime; i < this.stateBySecond.size(); i++) {
            if (checkWithinDuration(i + 1, moveDuration)) {
              break;
            } else {
              if (i < endtime) {
                if (checkWithinEmpty(i)) {
                  throw new IllegalArgumentException("Can not modify empty.");
                }
                incrementedX += incrementX;
                incrementedY += incrementY;
              }

              this.stateBySecond.get(i).setXposn(incrementedX);
              this.stateBySecond.get(i).setYposn(incrementedY);
            }
          }
          break;
        } else if (starttime < this.stateBySecond.size()) {
          for (int i = starttime; i < endtime; i++) {
            if (i < this.stateBySecond.size()) {
              if (checkWithinDuration(i + 1, moveDuration)) {
                break;
              }
              if (checkWithinEmpty(i)) {
                throw new IllegalArgumentException("Can not modify empty.");
              }
              incrementedX += incrementX;
              incrementedY += incrementY;
              this.stateBySecond.get(i).setXposn(incrementedX);
              this.stateBySecond.get(i).setYposn(incrementedY);
            } else {
              incrementedX += incrementX;
              incrementedY += incrementY;
              this.stateBySecond.add(
                  new InstantState(i, name, width, height, r, g, b, incrementedX,
                      incrementedY, degree));
            }
          }
        } else {
          for (int i = this.stateBySecond.size(); i < endtime; i++) {
            if (i < starttime) {
              this.stateBySecond
                  .add(new InstantState(i, name, width, height, r, g, b, oldx, oldy, degree));
              continue;
            } else {
              incrementedX += incrementX;
              incrementedY += incrementY;
              this.stateBySecond.add(
                  new InstantState(i, name, width, height, r, g, b, incrementedX, incrementedY,
                      degree));
            }
          }
        }
        break;

      case "color":
        int currentsize2 = this.stateBySecond.size();
        double oldr = this.stateBySecond.get(currentsize2 - 1).getR();
        double oldg = this.stateBySecond.get(currentsize2 - 1).getG();
        double oldb = this.stateBySecond.get(currentsize2 - 1).getB();

        double incrementR = (r - oldr) / (endtime - starttime);
        double incrementG = (g - oldg) / (endtime - starttime);
        double incrementB = (b - oldb) / (endtime - starttime);

        double incrementedr = this.stateBySecond.get(currentsize2 - 1).getR();
        double incrementedg = this.stateBySecond.get(currentsize2 - 1).getG();
        double incrementedb = this.stateBySecond.get(currentsize2 - 1).getB();

        if (endtime < this.stateBySecond.size() && starttime < this.stateBySecond.size()) {
          for (int i = starttime; i < this.stateBySecond.size(); i++) {
            if (checkWithinDuration(i + 1, colorDuration)) {
              break;
            } else {
              if (i < endtime) {
                if (checkWithinEmpty(i)) {
                  throw new IllegalArgumentException("Can not modify empty.");
                }
                incrementedr += incrementR;
                incrementedg += incrementG;
                incrementedb += incrementB;
              }

              this.stateBySecond.get(i).setR(incrementedr);
              this.stateBySecond.get(i).setG(incrementedg);
              this.stateBySecond.get(i).setB(incrementedb);
            }
          }
          break;
        } else if (starttime < this.stateBySecond.size()) {
          for (int i = starttime; i < endtime; i++) {
            if (i < this.stateBySecond.size()) {
              if (checkWithinDuration(i + 1, colorDuration)) {
                break;
              }
              if (checkWithinEmpty(i)) {
                throw new IllegalArgumentException("Can not modify empty.");
              }
              incrementedr += incrementR;
              incrementedg += incrementG;
              incrementedb += incrementB;
              this.stateBySecond.get(i).setR(incrementedr);
              this.stateBySecond.get(i).setG(incrementedg);
              this.stateBySecond.get(i).setB(incrementedb);
            } else {
              incrementedr += incrementR;
              incrementedg += incrementG;
              incrementedb += incrementB;
              this.stateBySecond.add(
                  new InstantState(i, name, width, height, incrementedr, incrementedg, incrementedb,
                      xposn, yposn, degree));
            }
          }
        } else {
          for (int i = this.stateBySecond.size(); i < endtime; i++) {
            if (i < starttime) {
              //should I directly copy and paste previous state?
              this.stateBySecond
                  .add(new InstantState(i, name, width, height, oldr, oldg, oldb, xposn, yposn,
                      degree));
              continue;
            } else {
              incrementedr += incrementR;
              incrementedg += incrementG;
              incrementedb += incrementB;

              this.stateBySecond.add(
                  new InstantState(i, name, width, height, Math.round(incrementedr),
                      Math.round(incrementedg), Math.round(incrementedb),
                      xposn, yposn, degree));
            }
          }
        }
        break;
      case "size":
        int currentsize3 = this.stateBySecond.size();
        double oldwidth = this.stateBySecond.get(currentsize3 - 1).getWidth();
        double oldheight = this.stateBySecond.get(currentsize3 - 1).getHeight();

        double incrementWidth = (width - oldwidth) / (endtime - starttime);
        double incrementHeight = (height - oldheight) / (endtime - starttime);

        double incrementedwidth = this.stateBySecond.get(currentsize3 - 1).getWidth();
        double incrementedheight = this.stateBySecond.get(currentsize3 - 1).getHeight();

        if (endtime < this.stateBySecond.size() && starttime < this.stateBySecond.size()) {
          for (int i = starttime; i < this.stateBySecond.size(); i++) {
            if (checkWithinDuration(i + 1, sizeDuration)) {
              break;
            } else {
              if (i < endtime) {
                if (checkWithinEmpty(i)) {
                  throw new IllegalArgumentException("Can not modify empty.");
                }
                incrementedwidth += incrementWidth;
                incrementedheight += incrementHeight;
              }
              this.stateBySecond.get(i).setWidth(incrementedwidth);
              this.stateBySecond.get(i).setHeight(incrementedheight);
            }
          }
          break;
        } else if (starttime < this.stateBySecond.size()) {
          for (int i = starttime; i < endtime; i++) {
            if (i < this.stateBySecond.size()) {
              if (checkWithinDuration(i + 1, sizeDuration)) {
                break;
              }
              if (checkWithinEmpty(i)) {
                throw new IllegalArgumentException("Can not modify empty.");
              }
              incrementedwidth += incrementWidth;
              incrementedheight += incrementHeight;
              this.stateBySecond.get(i).setWidth(incrementedwidth);
              this.stateBySecond.get(i).setHeight(incrementedheight);
            } else {
              incrementedwidth += incrementWidth;
              incrementedheight += incrementHeight;
              this.stateBySecond.add(
                  new InstantState(i, name, incrementedwidth, incrementedheight, r, g, b, xposn,
                      yposn, degree));
            }
          }
        } else {
          for (int i = this.stateBySecond.size(); i < endtime; i++) {
            if (i < starttime) {
              //should I directly copy and paste previous state?
              this.stateBySecond
                  .add(new InstantState(i, name, oldwidth, oldheight, r, g, b, xposn, yposn,
                      degree));
              continue;
            } else {
              incrementedwidth += incrementWidth;
              incrementedheight += incrementHeight;
              this.stateBySecond.add(
                  new InstantState(i, name, incrementedwidth, incrementedheight, r, g, b, xposn,
                      yposn, degree));
            }
          }
        }
        break;
      case "nothing":
        if (starttime < this.stateBySecond.size()) {
          for (int i = starttime; i < endtime; i++) {
            if (i < this.stateBySecond.size()) {
              if (checkWithinEmpty(i)) {
                throw new IllegalArgumentException("Can not modify empty.");
              }
            } else {
              this.stateBySecond
                  .add(new InstantState(i, name, width, height, r, g, b, xposn, yposn, degree));
            }
          }
        }
        // if list size less than start time
        else {
          for (int i = this.stateBySecond.size(); i < endtime; i++) {
            this.stateBySecond
                .add(new InstantState(i, name, width, height, r, g, b, xposn, yposn, degree));
          }
        }
        break;
      case "rotate":
        int currentsize4 = this.stateBySecond.size();

        double olddegree = this.stateBySecond.get(starttime - 1).getDegree();

        double incrementdegree = (degree - olddegree) / (endtime - starttime);
        double incrementeddegree = this.stateBySecond.get(starttime - 1).getDegree();

        if (endtime < this.stateBySecond.size() && starttime < this.stateBySecond.size()) {

          for (int i = starttime; i < this.stateBySecond.size(); i++) {
            if (checkWithinDuration(i + 1, rotateDuration)) {
              break;
            } else {
              if (i < endtime) {
                if (checkWithinEmpty(i)) {
                  throw new IllegalArgumentException("Can not modify empty.");
                }
                incrementeddegree += incrementdegree;
              }
              this.stateBySecond.get(i).setDegree(incrementeddegree);
            }
          }
          break;
        } else if (starttime < this.stateBySecond.size()) {

          for (int i = starttime; i < endtime; i++) {
            if (i < this.stateBySecond.size()) {
              if (checkWithinDuration(i + 1, rotateDuration)) {
                break;
              }
              if (checkWithinEmpty(i)) {
                throw new IllegalArgumentException("Can not modify empty.");
              }
              incrementeddegree += incrementdegree;
              this.stateBySecond.get(i).setDegree(incrementeddegree);
            } else {
              incrementeddegree += incrementdegree;
              this.stateBySecond.add(
                  new InstantState(i, name, width, height, r, g, b, xposn,
                      yposn, incrementeddegree));
            }
          }
        } else {

          for (int i = this.stateBySecond.size(); i < endtime; i++) {
            if (i < starttime) {
              //should I directly copy and paste previous state?
              this.stateBySecond
                  .add(new InstantState(i, name, width, height, r, g, b, xposn, yposn,
                      olddegree));
              continue;
            } else {
              incrementeddegree += incrementdegree;
              this.stateBySecond.add(
                  new InstantState(i, name, width, height, r, g, b, xposn,
                      yposn, incrementeddegree));
            }
          }
        }
        break;
      default:
        throw new IllegalArgumentException("Error Command!!");
    }
  }

  /**
   * Getter method for the name.
   *
   * @return name of the shape
   */
  public String getName() {
    return name;
  }

  /**
   * Getter method for width.
   *
   * @return width of the shape
   */
  public double getWidth() {
    return width;
  }

  /**
   * Getter method for the Height.
   *
   * @return height of the shape
   */
  public double getHeight() {
    return height;
  }

  /**
   * Getter method for the x position.
   *
   * @return x position of the shape
   */
  public double getXposn() {
    return xposn;
  }

  /**
   * Getter method for the y position.
   *
   * @return
   */
  public double getYposn() {
    return yposn;
  }


  /**
   * Getter method for R color.
   *
   * @return R value of color
   */
  public double getR() {
    return r;
  }

  /**
   * Getter method for G color.
   *
   * @return G value of color
   */
  public double getG() {
    return g;
  }

  /**
   * Getter method for B color.
   *
   * @return B value of color
   */
  public double getB() {
    return b;
  }

  //should be private
  private String instantStateLog() {
    String result = "time, name, width, height, r, g, b, xposn, yposn, degree\n";
    for (InstantState i : this.stateBySecond) {
      result = result + i.toStringTest();
    }
    return result;
  }

  //helper tools, private
  private String durationLog(ArrayList<Duration> given) {
    String result = "";
    for (Duration i : given) {
      result = result + i.toString();
    }
    return result;
  }

  //check if the current index is in any duration
  private boolean checkWithinDuration(int time, ArrayList<Duration> given) {
    for (int i = 0; i < given.size(); i++) {
      if (time > given.get(i).startTime && time < given.get(i).endTime) {
        return true;
      }
    }
    return false;
  }

  private boolean checkWithinEmpty(int time) {
    for (int i = 0; i < nothingDuration.size(); i++) {
      if (time > nothingDuration.get(i).startTime && time < nothingDuration.get(i).endTime) {
        return true;
      }
    }
    return false;
  }

  //check if the current index is in any duration
  private boolean checkWithinDuration2(int time, ArrayList<Duration> given) {
    for (int i = 0; i < given.size(); i++) {
      if (time >= given.get(i).startTime && time <= given.get(i).endTime) {
        return true;
      } else {
        continue;
      }
    }
    return false;
  }

  /**
   * Checking if there is any gap in time in all animation of this shape.
   *
   * @return if there is any time gap.
   */
  public boolean checkGaps() {
    for (int i = 1; i < this.stateBySecond.size(); i++) {
      if (checkWithinDuration2(i, moveDuration) || checkWithinDuration2(i, colorDuration)
          || checkWithinDuration2(i, sizeDuration) || checkWithinDuration2(i, nothingDuration)
          || checkWithinDuration2(i, rotateDuration)) {
        continue;
      } else {
        return true;
      }
    }
    return false;
  }


  /**
   * Helper method for getLog method in model. Check if the current index is somewhere worth
   * recording in the log.
   *
   * @param index index on the instantStateLog.
   * @return if this point is somewhere worth written in the log (somewhere crucial change happens)
   */
  public boolean checkImportantPoints(int index) {
    if (index == 0) {
      return true;
    } else {
      return this.checkWithinSingleArrayListDuration(index, moveDuration)
          || this.checkWithinSingleArrayListDuration(index, sizeDuration) ||
          this.checkWithinSingleArrayListDuration(index, colorDuration)
          || this.checkWithinSingleArrayListDuration(index, nothingDuration)
          || this.checkWithinSingleArrayListDuration(index, rotateDuration);
    }

  }


  private boolean checkImportantPoints2(int index) {
    return this.checkWithinSingleArrayListDuration(index, moveDuration)
        || this.checkWithinSingleArrayListDuration(index, sizeDuration) ||
        this.checkWithinSingleArrayListDuration(index, colorDuration)
        || this.checkWithinSingleArrayListDuration(index, nothingDuration)
        || this.checkWithinSingleArrayListDuration(index, rotateDuration);
  }

  private boolean checkWithinSingleArrayListDuration(int given, ArrayList<Duration> list) {
    for (int i = 0; i < list.size(); i++) {
      if (given == list.get(i).startTime || given == list.get(i).endTime) {
        return true;
      } else {
        continue;
      }
    }
    return false;
  }

  /**
   * Getter method returning instantaneous state of the shape.
   *
   * @return List of instantaneous state of the shape.
   */
  public ArrayList<InstantState> getStateBySecond() {
    return stateBySecond;
  }

  /**
   * Setter method returning instantaneous state of the shape.
   *
   * @param stateBySecond giving a list of state of the shape per tick
   */
  public void setStateBySecond(ArrayList<InstantState> stateBySecond) {
    this.stateBySecond = stateBySecond;
  }

  /**
   * Getter method for getting all durations when shape is moving.
   *
   * @return list of durations when this shape is moving (position)
   */
  public ArrayList<Duration> getMoveDuration() {
    return moveDuration;
  }

  /**
   * Setter method for moveDuration.
   *
   * @param moveDuration list of duration where the shape moves.
   */
  public void setMoveDuration(ArrayList<Duration> moveDuration) {
    this.moveDuration = moveDuration;
  }

  /**
   * Getter method for color duration.
   *
   * @return list of durations when the color of this shape changes
   */
  public ArrayList<Duration> getColorDuration() {
    return colorDuration;
  }

  /**
   * Setter method for color duration.
   *
   * @param colorDuration list of durations where color changes
   */
  public void setColorDuration(ArrayList<Duration> colorDuration) {
    this.colorDuration = colorDuration;
  }

  /**
   * getter method for size duration.
   *
   * @return list of durations when this shape change its size
   */
  public ArrayList<Duration> getSizeDuration() {
    return sizeDuration;
  }

  /**
   * Setter method for size duration.
   *
   * @param sizeDuration list of durations when this shape change size
   */
  public void setSizeDuration(ArrayList<Duration> sizeDuration) {
    this.sizeDuration = sizeDuration;
  }

  /**
   * Setter method for nothing duration.
   *
   * @param nothingDuration list of duration when this shape do nothing
   */
  public void setNothingDuration(ArrayList<Duration> nothingDuration) {
    this.nothingDuration = nothingDuration;
  }


  /**
   * Method that will update the instant state of the shape(should be called each tick). NOTE:
   * REPLAY IN THE END!!!
   */
  public void updateFieldsReplay(boolean loop, int globalMax) {
    if (this.tick < stateBySecond.size()) {
      InstantState current = stateBySecond.get(tick);
      this.xposn = current.getXposn();
      this.yposn = current.getYposn();
      this.r = current.getR();
      this.g = current.getG();
      this.b = current.getB();
      this.width = current.getWidth();
      this.height = current.getHeight();
      this.degree = current.getDegree();
      this.tick++;
    }
    if (loop && this.tick >= globalMax) {
      this.tick = 0;
    }

    if (this.tick >= stateBySecond.size()) {
      this.width = -2;
      this.height = -2;
      this.tick++;
    }

  }

  /**
   * Removes a motion at a specific interval. If no motion exists that has this exact time frame, do
   * nothing.
   *
   * @param start the start time of the interval.
   * @param end   the end time of the interval.
   * @param type  the type of motion to be removed.
   */
  public void removeMoveAtInterval(int start, int end, String type) {
    // Check all the move durations.
    if (type.equalsIgnoreCase("move")) {
      for (Duration d : this.moveDuration) {
        if (d.sameTimeFrame(start, end)) {
          this.moveDuration.remove(d);
          double ogX;
          double ogY;
          double differenceX;
          double differenceY;
          ogX = this.stateBySecond.get(start - 1).getXposn();
          ogY = this.stateBySecond.get(start - 1).getYposn();
          differenceX = this.stateBySecond.get(end - 1).getXposn() -
              this.stateBySecond.get(start - 1).getXposn();
          differenceY = this.stateBySecond.get(end - 1).getYposn() -
              this.stateBySecond.get(start - 1).getYposn();
          for (int i = start; i < end; i++) {
            this.stateBySecond.get(i).setXposn(ogX);
            this.stateBySecond.get(i).setYposn(ogY);
          }
          for (int i = end; i < this.stateBySecond.size(); i++) {
            double currX = this.stateBySecond.get(i).getXposn();
            double currY = this.stateBySecond.get(i).getYposn();
            this.stateBySecond.get(i).setXposn(currX - differenceX);
            this.stateBySecond.get(i).setYposn(currY - differenceY);
          }
          this.nothingDuration.add(new Duration(start, end));
          break;
        }
      }
    }
    // Check all the color durations.
    else if (type.equalsIgnoreCase("color")) {
      for (Duration d : this.colorDuration) {
        if (d.sameTimeFrame(start, end)) {
          this.colorDuration.remove(d);
          double ogR;
          double ogG;
          double ogB;
          double diffR;
          double diffG;
          double diffB;
          ogR = this.stateBySecond.get(start - 1).getR();
          ogG = this.stateBySecond.get(start - 1).getG();
          ogB = this.stateBySecond.get(start - 1).getB();
          diffR = this.stateBySecond.get(end - 1).getR() - this.stateBySecond.get(start - 1).getR();
          diffG = this.stateBySecond.get(end - 1).getG() - this.stateBySecond.get(start - 1).getG();
          diffB = this.stateBySecond.get(end - 1).getB() - this.stateBySecond.get(start - 1).getB();
          for (int i = start; i < end; i++) {
            this.stateBySecond.get(i).setR(ogR);
            this.stateBySecond.get(i).setG(ogG);
            this.stateBySecond.get(i).setB(ogB);
          }
          for (int i = end; i < this.stateBySecond.size(); i++) {
            double currR = this.stateBySecond.get(i).getR();
            double currG = this.stateBySecond.get(i).getG();
            double currB = this.stateBySecond.get(i).getB();
            this.stateBySecond.get(i).setR(currR - diffR);
            this.stateBySecond.get(i).setG(currG - diffG);
            this.stateBySecond.get(i).setB(currB - diffB);
          }
          this.nothingDuration.add(new Duration(start, end));
          break;
        }
      }
    }
    // Check all the size durations.
    else if (type.equalsIgnoreCase("size")) {
      for (Duration d : this.sizeDuration) {
        if (d.sameTimeFrame(start, end)) {
          this.sizeDuration.remove(d);
          double ogWidth;
          double ogHeight;
          double diffW;
          double diffH;
          ogWidth = this.stateBySecond.get(start - 1).getWidth();
          ogHeight = this.stateBySecond.get(start - 1).getHeight();
          diffW = this.stateBySecond.get(end - 1).getWidth() -
              this.stateBySecond.get(start - 1).getWidth();
          diffH = this.stateBySecond.get(end - 1).getHeight() -
              this.stateBySecond.get(start - 1).getHeight();
          for (int i = start - 1; i < end; i++) {
            this.stateBySecond.get(i).setWidth(ogWidth);
            this.stateBySecond.get(i).setHeight(ogHeight);
          }
          for (int i = end; i < this.stateBySecond.size(); i++) {
            double currW = this.stateBySecond.get(i).getWidth();
            double currH = this.stateBySecond.get(i).getHeight();
            this.stateBySecond.get(i).setWidth(currW - diffW);
            this.stateBySecond.get(i).setHeight(currH - diffH);
          }
          this.nothingDuration.add(new Duration(start, end));
          break;
        }
      }
    }
  }

  /*
  DID NOT IMPLEMENT REMOVE REMOVE SHAPE FROM ROTATING
  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


   */
  public ArrayList<InstantState> getKeyFrame() {
    return keyFrame;
  }

  public void setKeyFrame(ArrayList<InstantState> keyFrame) {
    this.keyFrame = keyFrame;
  }

  /**
   * Add a piece of keyframe to the shape.
   *
   * @param data Piece of instantState
   */
  public void addKeyFramePiece(InstantState data) {
    if (this.keyFrame.size() == 0) {
      keyFrame.add(data);
    } else {
      //ensure that u cannot add two keyframe with same time.
      for (int j = 0; j < keyFrame.size(); j++) {
        if (keyFrame.get(j).getTime() == data.getTime()) {
          return;
        }
      }

      for (int i = 0; i < keyFrame.size(); i++) {
        if (i + 1 == keyFrame.size()) {
          keyFrame.add(data);
          break;
        }
        if (keyFrame.get(i + 1).getTime() > data.getTime()) {
          if (keyFrame.get(i).getTime() < data.getTime()) {
            keyFrame.add(i + 1, data);
          } else {
            keyFrame.add(i, data);
          }
          break;
        }
        if (keyFrame.get(i + 1).getTime() < data.getTime()) {
          continue;
        }
      }
    }
  }

  /**
   * Convert the list of keyframe available to a readable String format.
   *
   * @return String containing list of keyframes
   */
  public String toStringKeyFrames() {
    String result = "";
    for (int i = 0; i < this.keyFrame.size(); i++) {
      result += this.keyFrame.get(i).toStringKeyFrame();
    }
    return result;
  }


  public void setWidth(double width) {
    this.width = width;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public void setR(double r) {
    this.r = r;
  }

  public void setG(double g) {
    this.g = g;
  }

  public void setB(double b) {
    this.b = b;
  }

  public void setXposn(double xposn) {
    this.xposn = xposn;
  }

  public void setYposn(double yposn) {
    this.yposn = yposn;
  }

  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * A special method for fixing gapping issue in the instant state log.
   */
  public void handleWithGap() {
    for (int i = 0; i < this.stateBySecond.size(); i++) {
      if (this.checkImportantPoints2(i + 1)) {
        break;
      }
      this.stateBySecond.get(i).setWidth(0);
      this.stateBySecond.get(i).setHeight(0);
      this.stateBySecond.get(i).setR(0);
      this.stateBySecond.get(i).setG(0);
      this.stateBySecond.get(i).setB(0);
      this.stateBySecond.get(i).setXposn(0);
      this.stateBySecond.get(i).setYposn(0);
      this.stateBySecond.get(i).setDegree(0);
      continue;
    }
  }

  public int getTick() {
    return tick;
  }

  public ArrayList<Duration> getRotateDuration() {
    return rotateDuration;
  }

  public void setRotateDuration(ArrayList<Duration> rotateDuration) {
    this.rotateDuration = rotateDuration;
  }

  public double getDegree() {
    return degree;
  }

  public void setDegree(double degree) {
    this.degree = degree;
  }

  public ArrayList<Duration> getNothingDuration() {
    return nothingDuration;
  }

  public int getLayer() {
    return layer;
  }

  /**
   * Setter method for the layer.
   *
   * @param layer layer info given
   */
  public void setLayer(int layer) {
    if (layer <= 0) {
      throw new IllegalArgumentException("Layer must be positive integer!");
    } else {
      this.layer = layer;
    }
  }
}


