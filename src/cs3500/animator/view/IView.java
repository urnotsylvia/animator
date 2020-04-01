package cs3500.animator.view;

import cs3500.animator.model.IShape;
import java.util.List;

/**
 * the view interface that show the animation.
 */
public interface IView {

  /**
   * display the animation.
   * @param shapes the list of shapes got from the model
   * @param bounds the bounds (x, y, width, height)
   */
  void showAnimation(List<IShape> shapes, List<Integer> bounds);

  /**
   * makes the view visible.
   */
  void makeVisible();
}
