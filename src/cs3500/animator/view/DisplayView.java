package cs3500.animator.view;
import cs3500.animator.Model.IAnimationOperations;
import javax.swing.Timer;

public class DisplayView extends AView {


  public DisplayView(IAnimationOperations model) {
    super(model);
  }

  //Timer //swing
  @Override
  public ViewType getViewType() {
    return ViewType.DISPLAY;
  }

  @Override
  public void playAnimation(IAnimationOperations m) {

  }
}
