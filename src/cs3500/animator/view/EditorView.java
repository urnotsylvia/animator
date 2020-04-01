package cs3500.animator.view;

import cs3500.animator.model.IShape;
import java.util.List;
import javax.swing.JFrame;

/**
 * A class that represent the editor view that allow the animation to play more than once, and add,
 * remove, and modify items from the animation.
 */
public class EditorView extends JFrame implements IEditorView {

  private boolean loop;
  private VisualView visual;
  private int speed;

  /**
   * constructs the view given speed to output.
   *
   * @param speed
   */
  public EditorView(/*VisualView visual, boolean loop*/int speed) {
    this.loop = false; //??????????????????????????????
    this.speed = speed;

    this.makeVisible();
  }


  @Override
  public void showAnimation(List<IShape> shapes, List<Integer> bounds) {
    this.visual.showAnimation(shapes, bounds);
  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void startAnimation() {

  }

  @Override
  public void pauseAnimation() {

  }

  @Override
  public void resumeAnimation() {

  }

  @Override
  public void restartAnimation() {

  }

  @Override
  public void changeSpeed(int speed) {

  }
}
