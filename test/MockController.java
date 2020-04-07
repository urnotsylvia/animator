import cs3500.animator.controller.IController;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

/**
 * Representing a Mock Controller that logs everything to the appendable for testing the functionality.
 */
public class MockController implements IController {
  public Appendable log = new StringBuilder();

  public MockController() {
  }

  private void addLog(String str){
    try{
      log.append(str).append("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e){
    this.addLog("ACTION PERFORMED");

  }

  @Override
  public void playAnimation() {
    this.addLog("PLAY ANIMATION");

  }

  @Override
  public int getTime() {
    return 0;
  }

  @Override
  public void changeLoop() {
    this.addLog("CHANGE LOOP");

  }

  @Override
  public void pauseAnimation() {
    this.addLog("PAUSE ANIMATION");

  }

  @Override
  public void resumeAnimation() {
    this.addLog("RESUME ANIMATION");

  }

  @Override
  public void changeSpeed(int speed) {
    this.addLog("CHANGE SPEED");

  }

  @Override
  public void addKeyFrame(String name, List<Integer> values) {
    this.addLog("ADD KEYFRAME");

  }

}
