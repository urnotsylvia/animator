import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.junit.Before;
import org.junit.Test;

public class AnimationControllerTest {

  JButton startButton;
  JButton pauseButton;
  JButton resumeButton;
  JButton addButton;
  JButton changeSpeedButton;
  JButton loopButton;
  JComboBox whichShapeToAdd;

  MockController controller;
  MockEditorView view;


  @Before
  public void initialize() {
    this.startButton = new JButton("START");
    this.pauseButton = new JButton("PAUSE");
    this.resumeButton = new JButton("RESUME");
    this.addButton = new JButton("ADD");
    this.changeSpeedButton = new JButton("CHANGE SPEED TO:");
    this.loopButton = new JButton("LOOP");
    this.whichShapeToAdd = new JComboBox();

    this.controller = new MockController();
    this.view = new MockEditorView();

  }

  @Test
  public void testActionPerformed() {
    ActionEvent ae1 = new ActionEvent(startButton, 1, "START");
    ActionEvent ae2 = new ActionEvent(pauseButton, 2, "PAUSE");
    ActionEvent ae3 = new ActionEvent(resumeButton, 3, "RESUME");
    ActionEvent ae4 = new ActionEvent(addButton, 4, "ADD");
    ActionEvent ae5 = new ActionEvent(changeSpeedButton, 5, "CHANGE SPEED TO:");
    ActionEvent ae6 = new ActionEvent(loopButton, 6, "LOOP");
    ActionEvent ae7 = new ActionEvent(whichShapeToAdd, 7, "");

    this.controller.actionPerformed(ae1);
    assertEquals(0, this.controller.getTime());
    assertEquals("ACTION PERFORMED\n", this.controller.log.toString());
    controller.actionPerformed(ae2);
    assertEquals("ACTION PERFORMED\n"
        + "ACTION PERFORMED\n", this.controller.log.toString());
    controller.actionPerformed(ae3);
    assertEquals("ACTION PERFORMED\n"
        + "ACTION PERFORMED\n" + "ACTION PERFORMED\n", this.controller.log.toString());
    controller.actionPerformed(ae4);
    assertEquals("ACTION PERFORMED\n"
            + "ACTION PERFORMED\n" + "ACTION PERFORMED\n" + "ACTION PERFORMED\n",
        this.controller.log.toString());
    controller.actionPerformed(ae5);
    assertEquals("ACTION PERFORMED\n"
            + "ACTION PERFORMED\n" + "ACTION PERFORMED\n" + "ACTION PERFORMED\n" + "ACTION PERFORMED\n",
        this.controller.log.toString());
    controller.actionPerformed(ae6);
    assertEquals("ACTION PERFORMED\n"
        + "ACTION PERFORMED\n" + "ACTION PERFORMED\n" + "ACTION PERFORMED\n" + "ACTION PERFORMED\n"
        + "ACTION PERFORMED\n", this.controller.log.toString());
    controller.actionPerformed(ae7);
    assertEquals("ACTION PERFORMED\n"
        + "ACTION PERFORMED\n" + "ACTION PERFORMED\n" + "ACTION PERFORMED\n" + "ACTION PERFORMED\n"
        + "ACTION PERFORMED\n" + "ACTION PERFORMED\n", this.controller.log.toString());


  }

  @Test
  public void testPlayAnimation() {
    controller.playAnimation();
    assertEquals("PLAY ANIMATION\n", controller.log.toString());
  }

  @Test
  public void testChangeLoop() {
    controller.changeLoop();
    assertEquals("CHANGE LOOP\n", controller.log.toString());
  }

  @Test
  public void testPauseAnimation() {
    controller.pauseAnimation();
    assertEquals("PAUSE ANIMATION\n", controller.log.toString());
  }

  @Test
  public void testResumeAnimation() {
    controller.resumeAnimation();
    assertEquals("RESUME ANIMATION\n", controller.log.toString());
  }

  @Test
  public void testChangeSpeed() {
    controller.changeSpeed(15);
    assertEquals("CHANGE SPEED\n", controller.log.toString());
  }

  @Test
  public void testAddKeyFrame() {
    controller.addKeyFrame("r1",
        new ArrayList<Integer>(10)); // array list ????????????????????????????????????
    assertEquals("ADD KEYFRAME\n", controller.log.toString());
  }
}
