package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.InstantState;
import cs3500.animator.model.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Timer;
import cs3500.animator.view.HW7EditView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

/**
 * Controller for Animations, allows user to determine which view they want and speed given a model,
 * as well as passes the data from the model to the view.
 */
public class AnimationControls {

  private AnimationModel model;
  private IView view;
  private int speed;
  private boolean pause = false;
  private boolean restart = false;
  private Timer time;
  private boolean loop = true;
  private int ticktracker = 0;
  private ArrayList<Shape> sortedlist;

  /**
   * Constructor for the controller.
   *
   * @param m        Model.AnimationModel
   * @param viewType type of the view, it can be either visual, text or svg
   * @param speed    tick in real time (How long in millisecond per tick)
   * @param fileName name of the output file if applicable
   */
  public AnimationControls(AnimationModel m, String viewType, int speed, String fileName) {
    this.model = m;
    for (int i = 0; i < model.getShapes().size(); i++) {
      m.getShapes().get(i).handleWithGap();
    }
    this.speed = speed;
    //generate the correct view based on the input.
    if (viewType.equalsIgnoreCase("svg")) {
      view = new SVGView(m.getCanvasWidth(), m.getCanvasHeight(), m.getCanvasX(), m.getCanvasY(),
          30,
          fileName);
    } else if (viewType.equalsIgnoreCase("text")) {
      view = new TextView(m.getCanvasWidth(), m.getCanvasHeight(), m.getCanvasX(), m.getCanvasY(),
          fileName);
    } else if (viewType.equalsIgnoreCase("edit")) {
      view = new HW7EditView(m.getCanvasWidth(), m.getCanvasHeight(), m.getCanvasX(),
          m.getCanvasY());
    } else {
      view = new VisualView(m.getCanvasWidth(), m.getCanvasHeight(),
          m.getCanvasX(), m.getCanvasY());
    }

    //sort shape by its layer value here!!!!!
    ArrayList<Shape> sortedList = new ArrayList<>();

    //select the index with smallest value;

    ArrayList<Shape> templist = new ArrayList<>();
    //making a copy
    for (int i = 0; i < this.model.getShapes().size(); i++) {
      templist.add(this.model.getShapes().get(i));
    }

    //sorting the new list.
    this.sortedlist = templist;
    this.reorderSortedList();

    this.view.addShape(sortedlist);

    //Responsible for the pause button
    view.pauseListener(e -> {
      pause = !pause;
    });

    //Setting speed
    view.setSpeedListener(e -> {
      if (view.getSpeedText().equalsIgnoreCase("")) {
        return;
      }
      try {
        setSpeed(Integer.parseInt(view.getSpeedText()));
        time.setDelay(Integer.parseInt(view.getSpeedText()));
      } catch (NumberFormatException a) {
        //do nothing if input is invalid (unable to be parsed into string)
        return;
      }
    });

    //restart
    view.restartListener(e -> restart = true);
    //looping
    view.loopListener(e -> loop = !loop);

    view.mouseEditingListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        time.stop();
        view.sliderListener(a -> {
          int value = view.getSliderValue();
          if (value != -1) {
            int globalMax = 0;
            for (Shape s : sortedlist) {
              if (s.getStateBySecond().size() > globalMax) {
                globalMax = s.getStateBySecond().size();
              }
            }

            model.setAllTick(value);
            for (Shape s : sortedlist) {
              s.updateFieldsReplay(loop, globalMax);
            }

            view.addShape(sortedlist);
            view.repaint();
          }
        });
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        ticktracker = view.getSliderValue();
        time.start();
      }
    });

    //show all the keyframe available
    view.showAllKeyFrameListener(e -> {
      for (int i = 0; i < model.getShapes().size(); i++) {
        System.out.println(
            model.getShapes().get(i).getType() + " " + model.getShapes().get(i).getName()
                + "'s KeyFrames\n" +
                model.getShapes().get(i).toStringKeyFrames());
      }
    });

    //keyframe editing listener
    view.keyframeListener(e -> {
      ArrayList<String> values = view.getKeyFrameInputs();
      //adding keyframe
      if (view.getKeyFrameModifyOption().equalsIgnoreCase("Add KeyFrame")) {
        System.out.println("Add Keyframe command received");
        model.convertMotionToKeyFrame();
        if (!(values.get(3).equalsIgnoreCase("rectangle") ||
            values.get(3).equalsIgnoreCase("ellipse"))) {
          System.out.println("Current view only support drawing rectangle and ellipse!");
          return;
        }
        try {
          if (Integer.parseInt(values.get(1)) < 1) {
            System.out.println("Time must be greater than or equal to 1. Try again!");
            return;
          }
          model.addKeyframe(values.get(2), values.get(3), Integer.parseInt(values.get(1)),
              Integer.parseInt(values.get(4)), Integer.parseInt(values.get(5)),
              Integer.parseInt(values.get(6)), Integer.parseInt(values.get(7)),
              Integer.parseInt(values.get(8)), Integer.parseInt(values.get(9)),
              Integer.parseInt(values.get(10)), Integer.parseInt(values.get(11)));

        } catch (Exception a) {
          System.out.println("Invalid Input! Please try again.");
          return;
        }
        model.keyFrameToInstantStateLog();
        //restart and sync shapes
        for (Shape s : model.getShapes()) {
          s.setTick(0);
        }
        restart = false;

        //deleting keyframe
      } else if (view.getKeyFrameModifyOption().equalsIgnoreCase("Delete KeyFrame")) {
        System.out.println("Delete Keyframe command received");
        model.convertMotionToKeyFrame();

        try {
          int index = model.getShapeNameIndex(values.get(2));
          if (model.getShapes().get(index).getKeyFrame().size() - 1 < 2) {
            System.out.println("There will be less than 2 keyframes after deleting, therefore "
                + "not allowed.\nPlease ensure there are at least 2 keyframes available "
                + "after deleting or delete "
                + "the entire shape if necessary.");
            return;
          }
          model.deleteKeyFrame(values.get(2), Integer.parseInt(values.get(1)));
        } catch (Exception a) {
          System.out.println("Invalid Input! Try Again!");
          return;
        }
        model.keyFrameToInstantStateLog();
        System.out.println(
            model.getShapes().get(model.getShapeNameIndex(values.get(2))).toStringKeyFrames());
        //editing keyframe
      } else if (view.getKeyFrameModifyOption().equalsIgnoreCase("Edit KeyFrame")) {
        System.out.println("Edit Keyframe command received");
        model.convertMotionToKeyFrame();
        if (!(values.get(3).equalsIgnoreCase("rectangle") ||
            values.get(3).equalsIgnoreCase("ellipse"))) {
          System.out.println("Current view only support drawing rectangle and ellipse!");
          return;
        }
        try {
          int index = model.getShapeNameIndex(values.get(2));
          if (model.getShapes().get(index).getKeyFrame().size() < 2) {
            System.out.println("There will be less than 2 keyframes after editing, therefore "
                + "not allowed.\nPlease ensure there are at least 2 keyframes available "
                + "after editing or delete "
                + "the entire shape if necessary.");
            return;
          }
          InstantState value = new InstantState(Integer.parseInt(values.get(1)), values.get(2),
              Integer.parseInt(values.get(6)), Integer.parseInt(values.get(7)),
              Integer.parseInt(values.get(8)), Integer.parseInt(values.get(9)),
              Integer.parseInt(values.get(10)), Integer.parseInt(values.get(4)),
              Integer.parseInt(values.get(5)), Integer.parseInt(values.get(11)));
          model.editKeyFrame(values.get(2), value);
          System.out.println(value.toStringKeyFrame());
          //support adding shape with customized layer
          if (!(values.get(12).equals(""))) {
            model.setLayerForAShape(values.get(2), Integer.parseInt(values.get(12)));
          }

        } catch (Exception a) {
          System.out.println("Invalid Input! Try again!");
          return;
        }
        model.keyFrameToInstantStateLog();
        //adding shape
      } else if (view.getKeyFrameModifyOption().equalsIgnoreCase("Add Shape")) {

        if (!(values.get(3).equalsIgnoreCase("rectangle") ||
            values.get(3).equalsIgnoreCase("ellipse"))) {
          System.out.println("Current view only support drawing rectangle and ellipse!");
          return;
        }

        try {
          Shape newshape = new Shape(values.get(2), values.get(3), 0, 0, 255,
              255, 255, 0, 0, 0);
          model.addShape(newshape);
          model.addKeyframe(values.get(2), values.get(3), 1, 0, 0, 0, 0,
              255, 255, 255, 0);
          System.out.println("Shape created.");
          if (!(values.get(12).equals("layer"))) {
            model.setLayerForAShape(values.get(2), Integer.parseInt(values.get(12)));
            newshape.setLayer(Integer.parseInt(values.get(12)));
          } else {
            model.setLayerForAShape(values.get(2), 1);
            newshape.setLayer(1);
            System.out.println(
                "Since there is no layer specification, the layer has been set to 1 by default.");
          }
          sortedlist.add(newshape);
          this.reorderSortedList();
        } catch (Exception a) {
          System.out.println("Invalid Input! Try Again!");
          return;
        }
        //deleting keyframe
      } else if (view.getKeyFrameModifyOption().equalsIgnoreCase("Delete Shape")) {
        model.removeShape(values.get(2));
      } else if (view.getKeyFrameModifyOption().equalsIgnoreCase("Delete Layer")) {
        try {
          //syncing
          model.removeAllShapeWithinLayer(Integer.parseInt(values.get(12)));
          System.out.println("Size before removing layer is: " + sortedlist.size());
          System.out.println("the layer input is " + Integer.parseInt(values.get(12)));

          //making a copy
          ArrayList<Shape> copylist = new ArrayList<>();
          for (int i = 0; i < sortedlist.size(); i++) {
            copylist.add(sortedlist.get(i));
          }

          int currentSize = sortedlist.size();
          for (int i = 0; i < currentSize; i++) {
            if (copylist.get(i).getLayer() == Integer.parseInt(values.get(12))) {
              copylist.remove(i);
              i--;
              currentSize--;
            }
          }
          this.sortedlist = copylist;
          System.out.println("Size after removing layer is: " + sortedlist.size());
          this.reorderSortedList();
        } catch (NumberFormatException a) {
          System.out.println("Invalid Layer Input!");
        }
      } else if (view.getKeyFrameModifyOption().equalsIgnoreCase("Edit Layer")) {

        try {
          model.setLayerForAShape(values.get(2), Integer.parseInt(values.get(12)));
          for (int i = 0; i < this.sortedlist.size(); i++) {
            if (this.sortedlist.get(i).getName().equalsIgnoreCase(values.get(2))) {
              this.sortedlist.get(i).setLayer(Integer.parseInt(values.get(12)));
              this.reorderSortedList();
              return;
            }
          }
        } catch (NumberFormatException a) {
          System.out.println("Invalid Input!");
        }

      }
    });


  }

  /**
   * Start the animation, and assigns the view to start outputting. Must be called in order to start
   * the animation.
   */
  public void startAnimating() {
    view.makeVisible();
    model.convertMotionToKeyFrame();

    time = new Timer(this.speed, e -> {
      int globalMax = 0;
      for (Shape s : this.sortedlist) {
        if (s.getStateBySecond().size() > globalMax) {
          globalMax = s.getStateBySecond().size();
        }
      }

      view.setSliderMax(globalMax);

      if (restart) {
        for (Shape s : this.sortedlist) {
          s.setTick(0);

        }
        ticktracker = 0;
        restart = false;
      }
      if (!pause) {
        for (Shape s : this.sortedlist) {
          view.setCurrentSliderVal(ticktracker);
          s.updateFieldsReplay(loop, globalMax);
        }
        if (ticktracker == globalMax && loop) {
          ticktracker = 1;
        } else {
          if (!(ticktracker == globalMax && !loop)) {
            ticktracker++;
          }
        }
      }

      view.addShape(this.sortedlist);
      view.repaint();
    });
    time.setInitialDelay(0);
    time.start();
  }

  private void setSpeed(int speed) {
    this.speed = speed;
  }

  //private method reordering sorted list
  private void reorderSortedList() {
    ArrayList<Shape> templist = new ArrayList<>();
    ArrayList<Shape> result = new ArrayList<>();
    //making a copy
    for (int i = 0; i < this.model.getShapes().size(); i++) {
      templist.add(this.model.getShapes().get(i));
    }

    ArrayList<Integer> layerlist = new ArrayList<>();
    for (int j = 0; j < templist.size(); j++) {
      layerlist.add(templist.get(j).getLayer());
    }

    while (templist.size() > 0) {
      int smallestindex = -1;
      int location = 0;

      if (templist.size() == 1) {

        result.add(templist.get(0));
        templist.remove(0);
        layerlist.remove(0);
        break;
      }

      for (int i = 0; i < layerlist.size(); i++) {
        if (i == 0) {
          smallestindex = layerlist.get(i);
          location = i;
        } else {
          if (layerlist.get(i) < smallestindex) {
            smallestindex = layerlist.get(i);
            location = i;
          } else {
            continue;
          }
        }
      }

      result.add(templist.get(location));
      templist.remove(location);
      layerlist.remove(location);
    }
    this.sortedlist = result;
  }


}
