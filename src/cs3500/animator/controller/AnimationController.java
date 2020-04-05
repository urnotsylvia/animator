package cs3500.animator.controller;

import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.view.IView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

  /**
   * constructs the controller.
   *
   * @param model the model that stores all the data of the animation
   * @param view  the view that shows the animation
   */
  public AnimationController(IAnimationOperations model, IView view, int speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;

    this.timer = new Timer(1000 / speed, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        curTime++;
        view.refresh();
      }
    });
  }

  @Override
  public int getTime() {
    return curTime;
  }

  @Override
  public void changeLoop() {
    loop = !loop;
    this.timer.setRepeats(loop);
  }

  @Override
  public void playAnimation() {
    this.view.addActionListener(this);
    this.view.showAnimation();
    curTime = 1;
  }

  private void startAnimation() {
    this.timer.start();
  }

  @Override
  public void addKeyFrame() {

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
    timer.setDelay(this.speed);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    switch (actionEvent.getActionCommand()) {
      case "start":
        this.startAnimation();
        break;
      case "resume":
        this.resumeAnimation();
        break;
      case "changeSpeed":

        //??????????????????????????????????????????????????
        this.changeSpeed(1);
        break;
      case "loop":
        this.changeLoop();
        break;
      case "add":
        this.addKeyFrame();//get the new keyFrame information
        break;
      case "pause":
        this.pauseAnimation();
        break;
      default:
    }
  }
}
