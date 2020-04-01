package cs3500.animator.view;

import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.model.IShape;
import cs3500.animator.model.KeyFrame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The View of the Animation to produce a textual description of the animation that is compliant
 * with the popular SVG (Scalable Vector Graphics) file format.
 */
public class TextualView implements IView {
  private Appendable output;

  /**
   * constructs the view given model, speed and the appendable to output.
   *
   * @param output the output file
   */
  public TextualView(Appendable output) {

    this.output = output;
  }

  @Override
  public void showAnimation(List<IShape> shapes, List<Integer> bounds) {
    String curMotionToString = "";
    String result =
        "canvas " + getBoundsAsString(bounds) + "\n"; //need to finish setBound in model first

    List<String> unSortedMotions = new ArrayList<>();
    for (int i = 0; i < shapes.size(); i++) {
      IShape curShape = shapes.get(i);
      result = result + "shape " + curShape.getName() + " " + curShape.getShapeAsString() + "\n";

      for (int k = 0; k < curShape.getKeyFrames().size() - 1; k++) {
        KeyFrame curKey = curShape.getKeyFrames().get(k);
        KeyFrame nextKey = curShape.getKeyFrames().get(k + 1);
        //motion disk1 1 190 180 20 30 0 49 90  1 190 180 20 30 0 49 90
        curMotionToString = "motion " + curShape.getName() + " "
            + curKey.keyToString() + "  " + nextKey.keyToString() + "\n";
        unSortedMotions.add(curMotionToString);
      }
    }
    result = result + sortMotions(unSortedMotions);
    if (output == null) {
      System.out.println(result);
    } else {
      try {
        output.append(result);
      } catch (IOException ioe) {
        throw new IllegalArgumentException("failed to append:("); //what is IOE, should throw IAE?
      }
    }
  }

  /**
   *
   * @param bounds
   * @return
   */
  private String getBoundsAsString(List<Integer> bounds) {
    String result = "";
    for (Integer i: bounds) {
      result += i + " ";
    }
    result = result.substring(0, result.length() - 1);
    return result;
  }

  @Override
  public void makeVisible() {
    return;
  }

  /**
   * sort the list of motions based on the start time, from small to big.
   *
   * @param unSortedMotions list of motion that need to be sorted
   * @return the sorted Motions as a String
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
    for (String s : unSortedMotions) {
      result = result + s;
    }
    return result;
  }
}

