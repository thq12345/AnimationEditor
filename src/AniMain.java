import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ExcellenceModel;
import cs3500.animator.controller.AnimationControls;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Main class for animation. Place where we run the arguments and generate either text (console)
 * output, visual output(java swing), SVG output (SVG file) or edit output (Editable animation).
 * Arguments must be modified via run configurations.
 */
public class AniMain {

  /**
   * Main method for running arguments. Arguments can be either -in, -out, -view, -speed, which then
   * take in arguments to configure the animation.
   *
   * @param args arguments
   * @throws FileNotFoundException when the program cannot find the file specified in the command
   *                               line.
   */
  public static void main(String[] args) throws FileNotFoundException {
    FileReader fr;
    String filename = "";
    AnimationBuilder<AnimationModel> builder = new ExcellenceModel.Builder();
    AnimationControls control;
    int speed = -1;
    String viewtype = "";
    String output = "";
    AnimationReader reader = new AnimationReader();
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          filename = args[i + 1];
          i++;
          break;
        case "-view":
          viewtype = args[i + 1];
          if (!(viewtype.equalsIgnoreCase("svg") ||
              viewtype.equalsIgnoreCase("text") ||
              viewtype.equalsIgnoreCase("visual") ||
              viewtype.equalsIgnoreCase("edit"))) {
            throw new IllegalArgumentException("Viewtype must be one of the following: "
                + "svg, text, visual or edit.");
          }
          i++;
          break;
        case "-speed":
          try {
            speed = Integer.parseInt(args[i + 1]);
            if (speed < 1) {
              throw new IllegalArgumentException("Speed must be positive integer!");
            }
          } catch (NumberFormatException e) {
            e.printStackTrace();
          }
          i++;
          break;
        case "-out":
          output = args[i + 1];
          i++;
          break;
        default:
          break;
      }
    }

    AnimationControls play;
    fr = new FileReader(filename);
    AnimationModel model = reader.parseFile(fr, builder);
    if (viewtype.equalsIgnoreCase("text")) {
      play = new AnimationControls(model, "text", speed, output);
    } else if (viewtype.equalsIgnoreCase("visual")) {
      if (speed == -1) {
        play = new AnimationControls(model, "visual", 1000, output);
      } else {
        play = new AnimationControls(model, "visual", speed, output);
      }
    } else if (viewtype.equalsIgnoreCase("svg")) {
      if (!(output.equals(""))) {
        play = new AnimationControls(model, "svg", speed, output);
      } else {
        play = new AnimationControls(model, "svg", speed, "consoleOutput");
      }
    } else if (viewtype.equalsIgnoreCase("edit")) {
      if (speed == -1) {
        play = new AnimationControls(model, "edit", 1000, output);
      } else {
        play = new AnimationControls(model, "edit", speed, output);
      }

    } else {
      throw new IllegalStateException("Unable to play.");
    }

    play.startAnimating();
  }
}
