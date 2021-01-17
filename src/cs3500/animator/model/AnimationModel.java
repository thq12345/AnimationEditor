package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;

/**
 * The interface of the animationModel. This is where data representing the shapes and the canvas
 * should be stored and modified.
 */
public interface AnimationModel {

  /**
   * Sets new dimensions of the model.
   *
   * @param width  the width of the model.
   * @param height the height of the model.
   * @param x      the x coordinate the model starts.
   * @param y      the y coordinate the model starts.
   */
  void setCanvasDimensions(int width, int height, int x, int y);

  /**
   * Returns the width of the canvas.
   *
   * @return the width of the canvas.
   */
  int getCanvasWidth();

  /**
   * Returns the height of the canvas.
   *
   * @return the height of the canvas.
   */
  int getCanvasHeight();

  /**
   * Returns the x position of the canvas.
   *
   * @return the x position of the canvas.
   */
  int getCanvasX();

  /**
   * Returns the y position of the canvas.
   *
   * @return the y position of the canvas.
   */
  int getCanvasY();

  /**
   * Adds a predefined shape to the Model, instead of creating a new shape with parameters taken
   * in.
   *
   * @param s shape.
   */
  void addShape(Shape s);

  /**
   * Add a shape to the animation.
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
  void addShape(String name, String type, int width, int height, int r, int g, int b, int xposn,
      int yposn, int degree);

  /**
   * Change the position of a shape.
   *
   * @param n         the name of the shape
   * @param startTime start time of the change position
   * @param endTime   end time of the change position
   * @param movetoX   X position user want to move to in this duration
   * @param movetoY   Y position user want to move to in this duration
   */
  void changePosn(String n, int startTime, int endTime, int movetoX, int movetoY);

  /**
   * Change the color of a shape at a duration.
   *
   * @param n         the name of the shape
   * @param startTime start time of the change position
   * @param endTime   end time of the change position
   * @param givenr    R value user given to change color
   * @param giveng    G value user given to change color
   * @param givenb    B value user given to change color
   */
  void changeColor(String n, int startTime, int endTime, int givenr, int giveng, int givenb);

  /**
   * Change the size at a given duration.
   *
   * @param n         the name of the shape
   * @param startTime start time of the change position
   * @param endTime   end time of the change position
   * @param gwidth    width user want to change to in this duration
   * @param gheight   height user want to change to in this duration
   */
  void changeSize(String n, int startTime, int endTime, int gwidth, int gheight);


  /**
   * Rotate the shape to a specified degree.
   * @param n name of the shape
   * @param startTime start time of the rotating motion
   * @param endTime end time of the rotating motion
   * @param degree degree of rotation into
   */
  void rotateShape(String n, int startTime, int endTime, int degree);

  /**
   * Do nothing in this duration.
   *
   * @param n         the name of the shape
   * @param startTime start time of doing nothing
   * @param endTime   end time of doing nothing
   */
  void doNothing(String n, int startTime, int endTime);

  /**
   * Get the log of all the shapes in this animation.
   *
   * @return the log of this entire animation
   */
  String getLog();

  /**
   * Get all the shapes inside a animationModel.
   *
   * @return list of shapes contained in a model
   */
  ArrayList<Shape> getShapes();

  /**
   * Removes a specific shape from the model. If there is no corresponding shape, nothing will be
   * done.
   *
   * @param name the name of the shape to be removed.
   */
  void removeShape(String name);

  /**
   * Removes a specific motion within a shape with a timeframe. If there is no shape and no motion
   * that exactly matches the timeframe, do nothing.
   *
   * @param name  the name of the shape with the motion.
   * @param type  the type of motion to be removed.
   * @param start the start time of the motion.
   * @param end   the end time of the motion.
   */
  void removeMotion(String name, String type, int start, int end);

  /**
   * Given a name of a shape, get its properties.
   *
   * @param name the name of the shape.
   * @return the type of shape, coordinates, size and color as a String, or null.
   */
  String getShapeProperties(String name);

  /**
   * Given a shape name, and a type of motion, and an interval, get properties of the motion.
   *
   * @param shapeName the name of the shape.
   * @param type      the type of motion.
   * @param start     the start time of the motion.
   * @param end       the end time of the motion.
   * @Return description of the motion's value changes, or null if there is no shape with the name.
   */
  String getMotionProperties(String shapeName, String type, int start, int end);

  /**
   * Given a shape name, return its index in the list inside the model.
   *
   * @param name name of the shape
   * @return the index of the shape in the list
   */
  int getShapeNameIndex(String name);

  /**
   * Convert a list of motions into list of keyframes. Very useful when converting list of
   * instantstate to keyframe.
   */
  void convertMotionToKeyFrame();

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
  void addKeyframe(String name, String type, int t, int x, int y, int w,
      int h, int r, int g, int b, int degree);

  /**
   * Delete a keyframe from the list given the shape name and time.
   *
   * @param name name of the shape given
   * @param t    time of the keyframe to be deleted
   */
  void deleteKeyFrame(String name, int t);

  /**
   * Translate a list of keyframe to instantStateLog. Super helpful whe refresh the instantstate
   * list from keyframe.
   */
  void keyFrameToInstantStateLog();

  /**
   * Edit a specific keyframe in the keyframe list.
   *
   * @param name     name of the shape
   * @param changeTo Model.InstantState to change into.
   */
  void editKeyFrame(String name, InstantState changeTo);

  /**
   * Set all the shape's tick to a given value.
   * @param giventime given tick value to be set to.
   */
  void setAllTick(int giventime);

  /**
   * Find the shape with the given name and set a layer value.
   * @param name name of the shape.
   * @param layer layer value to be set to
   */
  void setLayerForAShape(String name, int layer);

  /**
   * Remove all the shape available within the given layer.
   * @param layer given layer value
   */
  void removeAllShapeWithinLayer(int layer);

}
