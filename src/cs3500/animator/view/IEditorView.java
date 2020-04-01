package cs3500.animator.view;

public interface IEditorView extends IView {

  /**
   * starts the animation.
   */
  void startAnimation();

  /**
   * pauses the animation.
   */
  void pauseAnimation();

  /**
   * resumes the animation.
   */
  void resumeAnimation();

  /**
   * restarts the animation.
   */
  void restartAnimation();

  /**
   * changes the speed during the animation.
   * @param speed the speed to change to
   */
  void changeSpeed(int speed);
}
