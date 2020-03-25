import Controller.AnimationController;
import Controller.IController;
import Model.AnimationModel;
import Model.IAnimationOperations;
import Model.IShape;
import cs3500.animator.Controller.AnimationController;
import cs3500.animator.Controller.IController;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import java.util.ArrayList;
import java.util.Timer;
import view.IView;
import view.TextView;

/**
 * Run a Animation
 */
public class Main {
  /**
   * Run a Tic Tac Toe game interactively.
   */
  public static void main(String[] args) {
    // Old News: console-based game:
    //new TicTacToeConsoleController(new InputStreamReader(System.in),
    //    System.out).playGame(new TicTacToeModel());

    // New Hotness: Graphical User Interface:
    // 1. Create an instance of the model.
    IAnimationOperations model = new AnimationModel(new ArrayList<IShape>());

    // 2. Create an instance of the view.
    IView view = new TextView();
    Timer timer = new Timer();


    AnimationReader reader = new AnimationReader();
    AnimationBuilder builder;
    reader.parseFile(readable, builder);
    //builder

    // 3. Create an instance of the controller, passing the view to its constructor.
    IController controller = new AnimationController(model, view);

    // 4. Call playGame() on the controller.
    controller.playAnimation(model);
  }
}
