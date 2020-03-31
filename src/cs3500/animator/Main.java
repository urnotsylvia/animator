package cs3500.animator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel.Builder;
import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.ViewType;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import cs3500.animator.view.IView;

/**
 * Run a Animation interactively.
 */
public class Main {

  /**
   * Run a Tic Tac Toe game interactively.
   */
  public static void main(String[] args) {

    IAnimationOperations model;

    Readable fileReader;
    FileWriter output = null;
    String fileName = "";
    String viewType = "";
    int speed = 0;
    String out = "";

    //parse the file and look for the file Name (the argument next to "in")
    for (int i = 0; i < args.length - 1; i++) {
      switch (args[i]) {
        case "-in":
          fileName = args[i + 1];
          break;
        case "-view":
          viewType = args[i + 1];
          break;
        case "-speed":
          speed = Integer.parseInt(args[i + 1]);
          break;
        case "-out":
          out = args[i + 1];
          break;
        default:
          break;
      }
    }

    if (speed <= 0) {
      speed = 1;
    }

    try {
      fileReader = new FileReader(fileName);
    } catch (FileNotFoundException fne) {
      throw new IllegalArgumentException("bad file name");
    }
    //-in "name-of-animation-file (file name)" -view "type-of-view" -out "where-output-show-go"
    // -speed "integer-ticks-per-second"

    if (out.equals("")) {
      output = null;
    } else {
      try {
        output = new FileWriter(out);

      } catch (IOException ioe) {
        throw new IllegalArgumentException("no such file"); //should this be IAE?
      }
    }
    AnimationBuilder<IAnimationOperations> builder = new Builder();
    model = AnimationReader.parseFile(fileReader, builder);

    IView view = null;
    switch (viewType) {
      case "textual":
      case "Textual":
        view = AnimationCreator.create(ViewType.TEXTUAL, model, speed, output);
        break;
      case "Display":
      case "display":
        view = AnimationCreator.create(ViewType.VISUAL, model, speed, output);
        break;
      case "SVG":
      case "svg":
        view = AnimationCreator.create(ViewType.SVG, model, speed, output);
        break;
      default:
        throw new IllegalArgumentException("invalid view:(");
    }

    IController controller = new AnimationController(model, view);

    controller.playAnimation(model);

    if (output != null) {
      try {
        output.close();
      } catch (IOException ioe) {
        throw new IllegalArgumentException("invalid close:(");
      }
    }
  }
}
