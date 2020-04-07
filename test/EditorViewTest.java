import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IReadonlyAnimationOperations;
import cs3500.animator.model.IShape;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class EditorViewTest {

  MockEditorView view;
  MockController controller;
  AnimationModel model;
  List<IShape> los;
  IReadonlyAnimationOperations roModel;

  @Before
  public void initialize() {
    this.model = new AnimationModel(los);
    this.view = new MockEditorView();
    this.controller = new MockController();
    this.roModel = new AnimationModel(los);

  }

  @Test
  public void testGetEditorPanel() {
    view.getEditorPanel();
    assertEquals("GET EDITOR PANEL\n", view.log.toString());
  }

  @Test
  public void testUpdateROModel() {
    view.updateROModel(roModel);
    assertEquals("UPDATE READONLY MODEL\n", view.log.toString());
  }

  @Test
  public void testAddActionListener() {
    view.addActionListener(controller);
    assertEquals("ADD ACTION LISTENER\n", view.log.toString());
  }

  @Test
  public void testMakeVisible(){
    view.makeVisible();
    assertEquals("MAKE VISIBLE\n", view.log.toString());
  }

  @Test
  public void testRefresh(){
    view.refresh();
    assertEquals("REFRESH\n", view.log.toString());
  }

  @Test
  public void testShowAnimation(){
    view.showAnimation();
    assertEquals("SHOW ANIMATION\n", view.log.toString());
  }

}
