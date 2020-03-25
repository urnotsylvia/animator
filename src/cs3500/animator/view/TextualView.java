package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;
import cs3500.animator.Model.IShape;
import cs3500.animator.Model.KeyFrame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFrame;

/**
 * The View of the Animation to produce a textual description of the animation that is compliant
 * with the popular SVG (Scalable Vector Graphics) file format.
 */
public class TextualView extends AView {

  /**
   * Represent the constructor of the textual view.
   * @param model the initial model that the textual view takes in
   * @param speed the
   * @param output
   */
  public TextualView(IAnimationOperations model, int speed, Appendable output) {
    super(model, speed, output);
    this.speed = speed;
    this.output = output;

    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new AnimationPanel(model);
    this.add(panel);
  }

  @Override
  public ViewType getViewType() {
    return ViewType.TEXTUAL;
  }

  @Override
  public void showAnimation(IAnimationOperations m) {
    String curMotionToString = "";
    String result = "";
    List<String> unSortedMotions = new ArrayList<>();
    for (int i = 0; i < m.getShapes().size(); i++) {
      IShape curShape = m.getShapes().get(i);
      for (int k = 0; k < curShape.getKeyFrames().size(); k++) {
        KeyFrame curKey = curShape.getKeyFrames().get(k);
        KeyFrame nextKey = curShape.getKeyFrames().get(k + 1);
        //motion disk1 1 190 180 20 30 0 49 90  1 190 180 20 30 0 49 90
        curMotionToString = "motion " + curShape.getName() + " "
            + curKey.keyToString() + "  " + nextKey.keyToString() + "\n";
        unSortedMotions.add(curMotionToString);
      }
    }
    result = sortMotions(unSortedMotions);
    try {
      output.append(result);
    } catch (IOException ioe) {
      throw new IllegalArgumentException("failed to append:("); //what is IOE, should throw IAE?
    }
  }

  /**
   *
   * @param unSortedMotions
   * @return
   */
  private String sortMotions(List<String> unSortedMotions) {
    String result = "";
    unSortedMotions.sort(new Comparator<String>() {
      public int compare(String s1, String s2) {
        String[] arr1 = s1.split(" ");
        String[] arr2 = s2.split(" ");
        return Integer.parseInt(arr1[2]) - Integer.parseInt(arr2[2]);
      }
    });
    for (String s: unSortedMotions) {
      result = result + s;
    }
    return result;
  }
}

