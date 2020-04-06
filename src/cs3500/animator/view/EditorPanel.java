package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IReadonlyAnimationOperations;
import cs3500.animator.model.IShape;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditorPanel extends JPanel {
  private IReadonlyAnimationOperations model;
  private JTextField addKeyInput;
  private JTextField changeSpeedInput;
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton addKeyFrameButton;
  private JButton loopButton;
  private JButton changeSpeedButton;
  private JComboBox whichShape;


  public EditorPanel(IReadonlyAnimationOperations model) {

    this.model = model;

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

    changeSpeedInput = new JTextField(3);
    this.add(changeSpeedInput);

    addKeyInput = new JTextField(30);
    this.add(addKeyInput);

    addKeyFrameButton = new JButton("ADD");
    addKeyFrameButton.setActionCommand("add");
    this.add(addKeyFrameButton);

    List<String> names = new ArrayList<>();
    for (IShape s: model.getShapes()) {
      names.add(s.getName());
    }

    whichShape = new JComboBox(names.toArray());
    whichShape.setActionCommand("whichShapeToAdd");
    this.add(whichShape);
    System.out.println((String) whichShape.getSelectedItem());
  }

  public String getInputString() {
    return changeSpeedInput.getText();
  }

  public List<Integer> getKeyFrameAsList() {
    List<Integer> keyValues = new ArrayList<>();
    String[] keyValuesAsString = addKeyInput.getText().split(" ");
    for (String str: keyValuesAsString) {
      keyValues.add(Integer.parseInt(str));
    }
    return keyValues;
  }

  public void addActionListener(ActionListener listener) {
    startButton.addActionListener(listener);

    pauseButton.addActionListener(listener);

    resumeButton.addActionListener(listener);

    addKeyFrameButton.addActionListener(listener);

    loopButton.addActionListener(listener);

    changeSpeedButton.addActionListener(listener);

    whichShape.addActionListener(listener);

  }

  public JButton getStartButton() {
    return this.startButton;
  }

  public JButton getLoopButton() {
    return this.loopButton;
  }

  public JComboBox getComboBox() {
    return this.whichShape;
  }
}
