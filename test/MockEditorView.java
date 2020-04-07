import cs3500.animator.controller.IController;
import cs3500.animator.model.IReadonlyAnimationOperations;
import cs3500.animator.view.EditorPanel;
import cs3500.animator.view.IEditorView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Representing a new Mock EditorView that logs everything to the appendable as the readable.
 */
public class MockEditorView implements IEditorView {
  public Appendable log = new StringBuilder();
  List<IController> listener = new ArrayList<>();

  private void add(String str){
    try{
      log.append(str).append("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public EditorPanel getEditorPanel() {
    this.add("GET EDITOR PANEL");
    return null;
  }

  @Override
  public void updateROModel(IReadonlyAnimationOperations readonlyModel) {
    this.add("UPDATE READONLY MODEL");
  }

  @Override
  public void addActionListener(IController listener) {
    this.add("ADD ACTION LISTENER");
  }

  @Override
  public void makeVisible() {
    this.add("MAKE VISIBLE");

  }

  @Override
  public void refresh() {
    this.add("REFRESH");

  }

  @Override
  public void showAnimation() {
    this.add("SHOW ANIMATION");

  }
}
