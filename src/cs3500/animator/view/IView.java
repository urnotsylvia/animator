package cs3500.animator.view;

public interface IView {

  /**
   * Make the view visible.
   */
  void makeVisible();

  /**
   * Return the viewType from the view.
   * @return the viewType
   */
  ViewType getViewType();


  void showAnimation();
}
