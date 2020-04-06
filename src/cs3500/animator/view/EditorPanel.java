package cs3500.animator.view;

import cs3500.animator.model.IReadonlyAnimationOperations;
import cs3500.animator.model.IShape;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * the editor panel that have Swing components for user to click on to mutate the animation or input
 * the information to change speed and add new keyFrame.
 */
public class EditorPanel extends JPanel {

  private JTextField addKeyInput;
  private JTextField changeSpeedInput;
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton addKeyFrameButton;
  private JButton loopButton;
  private JButton changeSpeedButton;
  private JComboBox whichShape;
  private JLabel hint;

  /**
   * constructs the layout of the panel and initializes the components.
   *
   * @param model the readonly model of the animation
   */
  public EditorPanel(IReadonlyAnimationOperations model) {

    this.setBackground(Color.green);

    startButton = new JButton("START");
    startButton.setActionCommand("start");
    this.add(startButton);

    pauseButton = new JButton("PAUSE");
    pauseButton.setActionCommand("pause");
    this.add(pauseButton);

    resumeButton = new JButton("RESUME");
    resumeButton.setActionCommand("resume");
    this.add(resumeButton);

    loopButton = new JButton("LOOP");
    loopButton.setActionCommand("loop");
    this.add(loopButton);

    changeSpeedButton = new JButton("Change Speed To:");
    changeSpeedButton.setActionCommand("changeSpeed");
    this.add(changeSpeedButton);

    changeSpeedInput = new JTextField(5);
    this.add(changeSpeedInput);

    addKeyInput = new JTextField(35);
    this.add(addKeyInput);

    addKeyFrameButton = new JButton("ADD");
    addKeyFrameButton.setActionCommand("add");
    this.add(addKeyFrameButton);

    List<String> names = new ArrayList<>();
    for (IShape s : model.getShapes()) {
      names.add(s.getName());
    }

    whichShape = new JComboBox(names.toArray());
    whichShape.setActionCommand("whichShapeToAdd");
    this.add(whichShape);

    JLabel guidance = new JLabel(
        "types values for time x y width height red green blue to add a new keyFrame");
    this.add(guidance);

    hint = new JLabel("");
    hint.setForeground(Color.red);
    this.add(hint);
  }

  /**
   * gets the input speed to change as a string.
   *
   * @return the new speed as a String
   */
  public String getInputString() {
    return changeSpeedInput.getText();
  }

  /**
   * breaks the input down as a list of integers.
   *
   * @return a list of integers
   */
  public List<Integer> getKeyFrameAsList() {
    List<Integer> keyValues = new ArrayList<>();
    String[] keyValuesAsString = addKeyInput.getText().split(" ");
    for (String str : keyValuesAsString) {
      try {
        keyValues.add(Integer.parseInt(str));
      } catch (NumberFormatException nfe) {
        hint.setText("need to be integer numbers!");
      }
    }
    return keyValues;
  }

  /**
   * adds the actionListener to the components.
   *
   * @param listener controller
   */
  public void addActionListener(ActionListener listener) {
    startButton.addActionListener(listener);

    pauseButton.addActionListener(listener);

    resumeButton.addActionListener(listener);

    addKeyFrameButton.addActionListener(listener);

    loopButton.addActionListener(listener);

    changeSpeedButton.addActionListener(listener);

    whichShape.addActionListener(listener);
  }

  /**
   * gets the startButton.
   *
   * @return startButton
   */
  public JButton getStartButton() {
    return this.startButton;
  }

  /**
   * gets the loopButton.
   *
   * @return loopButton
   */
  public JButton getLoopButton() {
    return this.loopButton;
  }

  /**
   * gets the combo box.
   *
   * @return whichShape comboBox
   */
  public JComboBox getComboBox() {
    return this.whichShape;
  }

  /**
   * gets the hint JLabel.
   *
   * @return hint JLabel
   */
  public JLabel getHint() {
    return this.hint;
  }
}
