package cs3500.animator.controller;

import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.view.EditorPanel;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.IEditorView;
import cs3500.animator.view.IView;
import cs3500.animator.view.IVisualView;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;
import javax.swing.Timer;

/**
 * the Controller that start the animation.
 */
public class AnimationController implements IController {

  private IAnimationOperations model;
  private IView view;
  private boolean loop;
  private int speed;
  private Timer timer;
  private int curTime;

  private String name = "";

  /**
   * constructs the controller.
   *
   * @param model the model that stores all the data of the animation
   * @param view  the view that shows the animation
   * @param speed the speed of the animation
   */
  public AnimationController(IAnimationOperations model, IView view, int speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    this.loop = false;

    if (view instanceof IEditorView) {
      this.timer = new Timer(1000 / speed, e -> {
        if (curTime == model.maxTick()) {
          if (!loop) {
            timer.stop();
          } else {
            curTime = 0;
          }
        } else {
          curTime++;
          ((IEditorView) view).refresh();
        }
      });
    }

    if (view instanceof IVisualView) {
      this.timer = new Timer(1000 / speed, e -> {
        curTime++;
        ((IVisualView) view).refresh();
      });
    }
  }

  @Override
  public int getTime() {
    return curTime;
  }

  @Override
  public void changeLoop() {
    loop = !loop;
    String loopToChange = "LOOP";
    if (loop) {
      loopToChange = "NonLOOP";
    }
    ((IEditorView) view).getEditorPanel().getLoopButton().setText(loopToChange);
  }

  @Override
  public void playAnimation() {
    if (view instanceof IEditorView) {
      ((IEditorView) this.view).addActionListener(this);
    }
    if (view instanceof IVisualView) {
      ((IVisualView) this.view).addActionListener(this);
      this.timer.start();
    }
    this.view.showAnimation();
    curTime = 1;
  }

  private void startAnimation() {
    this.timer.start();
    curTime = 1;
  }

  @Override
  public void addKeyFrame(String name, List<Integer> values) {
    if (values.size() != 8) {
      ((EditorView) view).getEditorPanel().getHint()
          .setText("the format should be 8 integers separated by blank!");
    } else {
      int t = values.get(0);
      int x = values.get(1);
      int y = values.get(2);
      int w = values.get(3);
      int h = values.get(4);
      int r = values.get(5);
      int g = values.get(6);
      int b = values.get(7);

      if (name.equals("")) {
        name = model.getShapes().get(0).getName();
      }

      model.addKeyframe(name, t, x, y, w, h, r, g, b);
      ((EditorView) view).getEditorPanel().getHint().setText("");
      ((EditorView) view).updateROModel(model);
    }
  }


  @Override
  public void pauseAnimation() {
    timer.stop();
  }

  @Override
  public void resumeAnimation() {
    timer.start();
  }

  @Override
  public void changeSpeed(int speed) {
    this.speed = speed;
    timer.setDelay(1000 / this.speed);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    EditorPanel editorPanel = ((IEditorView) view).getEditorPanel();

    switch (actionEvent.getActionCommand()) {
      case "start":
        this.startAnimation();
        editorPanel.getStartButton().setText("RESTART");
        break;
      case "resume":
        this.resumeAnimation();
        break;
      case "whichShapeToAdd":
        name = Objects.requireNonNull(editorPanel.getComboBox().getSelectedItem()).toString();
        break;
      case "changeSpeed":
        int s;
        try {
          s = Integer.parseInt(editorPanel.getInputString());
          if (s <= 0) {
            this.changeSpeed(this.speed);
          } else {
            this.changeSpeed(s);
          }
        } catch (NumberFormatException nfe) {
          this.changeSpeed(this.speed);
        }
        break;
      case "loop":
        this.changeLoop();
        break;
      case "add":
        this.addKeyFrame(name, ((IEditorView) view).getEditorPanel().getKeyFrameAsList());
        break;
      case "pause":
        this.pauseAnimation();
        break;
      default:
    }
  }
}
