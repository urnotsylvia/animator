package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IReadonlyAnimationOperations;
import cs3500.animator.model.IShape;
import java.util.List;
import javax.swing.JFrame;

/**
 * A class that represent the editor view that allow the animation to play more than once, and add,
 * remove, and modify items from the animation.
 */
public class EditorView extends JFrame implements IView {
  private IReadonlyAnimationOperations model;
  private VisualView visual;
  private int speed;

  /**
   * constructs the view given speed to output.
   *
   * @param speed
   */
  public EditorView(/*VisualView visual, boolean loop*/IReadonlyAnimationOperations model, int speed) {
    this.model = model;
    this.speed = speed;

    this.makeVisible();
  }


  @Override
  public void showAnimation() {
    this.visual.showAnimation();
  }

  @Override
  public void addActionListener(IController listener) {

  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void refresh() {

  }
}
