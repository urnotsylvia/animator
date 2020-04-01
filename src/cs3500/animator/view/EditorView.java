package cs3500.animator.view;

import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.model.IShape;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class EditorView extends JFrame implements IEditorView{
  private boolean loop;
  private VisualView visual;
  private int speed;

  public EditorView (/*VisualView visual, boolean loop*/int speed) {
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
