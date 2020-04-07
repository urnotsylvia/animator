package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IReadonlyAnimationOperations;

/**
 * the interface IEditorView that can let the user interacts with the animation and mutates the
 * model by adding new keyFrames to the selected shape.
 */
public interface IEditorView extends IVisualView {

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
