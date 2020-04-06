package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IReadonlyAnimationOperations;

/**
 * the interface IEditorView that can let the user interacts with the animation and mutates the
 * model by adding new keyFrames to the selected shape.
 */
public interface IEditorView extends IView {

  /**
   * add a listener for the view.
   *
   * @param listener the controller as a listener
   */
  void addActionListener(IController listener);

  /**
   * makes the view visible.
   */
  void makeVisible();

  /**
   * refreshes the view.
   */
  void refresh();

  /**
   * gets the editor panel.
   */
  EditorPanel getEditorPanel();

  /**
   * updates the readonly model after the mutable model been mutated.
   *
   * @param readonlyModel the readonly model
   */
  void updateROModel(IReadonlyAnimationOperations readonlyModel);
}
