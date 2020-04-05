package cs3500.animator.view;

import cs3500.animator.controller.IController;
import javax.swing.JPanel;

public interface IEditorView extends IView {
  /**
   *
   * @param listener
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
}
