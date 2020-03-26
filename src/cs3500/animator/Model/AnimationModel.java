package cs3500.animator.Model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Model.AnimationModel and store the status of the Animation.
 */
public class AnimationModel implements IAnimationOperations {

  private final List<IShape> shapes;
  private final int width;
  private final int height;

  /**
   * to represent the constructor of the model.
   *
   * @param shapes the list of the shapes that the model takes in
   */
  public AnimationModel(List<IShape> shapes) {
    this.shapes = shapes;
    this.width = 0;
    this.height = 0;
  }

  public static final class Builder implements AnimationBuilder<IAnimationOperations> {
    IAnimationOperations model;

    public Builder () {
      this.model = new AnimationModel(new ArrayList<IShape>());
    }

    @Override
    public IAnimationOperations build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<IAnimationOperations> setBounds(int x, int y, int width, int height) {
      return this;
    }

    @Override
    public AnimationBuilder<IAnimationOperations> declareShape(String name, String type) {
      this.model.add(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimationOperations> addMotion(String name, int t1, int x1, int y1,
        int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2,
        int g2, int b2) {
      return this;
    }

    @Override
    public AnimationBuilder<IAnimationOperations> addKeyframe(String name, int t, int x, int y,
        int w, int h, int r, int g, int b) {
      this.model.addKeyframe(name, t, x, y, w, h, r, g, b);
      return this;
    }
  }

  @Override
  public void add(String name, String type) {
    for (IShape s : this.shapes) {
      if (s.getName().equals(name)) {
        throw new IllegalArgumentException("This shape is already exists, cannot add this shape.");
      }
    }

    switch (type) {
      case "Rect":
      case "rectangle":
      case "Rectangle":
        this.shapes.add(new Rectangle(name));
        break;
      case "Oval":
      case"oval":
        this.shapes.add(new Oval(name));
        break;
      default:
        throw new IllegalArgumentException("invalid shape type");
    }
  }

  @Override
  public void addKeyframe(String name, int t, int x, int y,
      int w, int h, int r, int g, int b) {
    boolean exist = false;
    for (IShape s: shapes) {
      if (s.getName().equals(name)) {
        exist = true;
        s.addKeyFrame(new KeyFrame(t, x, y, w, h, new RGBColor(r, g, b)));
      }
    }
    if (!exist) {
      throw new IllegalArgumentException("there is no such shape");
    }
  }

  @Override
  public void remove(String name) {
    boolean isThereTheSame = false;
    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i).getName().equals(name)) {
        isThereTheSame = true;
        shapes.remove(i);
      }
    }
    if (!isThereTheSame) {
      throw new IllegalArgumentException("there is no such shape");
    }
  }

  @Override
  public List<IShape> getState(int when) {
    List<IShape> allKeysAtThisTick = new ArrayList<>();
    for (IShape s : shapes) {
      for (int i = 0; i < s.getKeyFrames().size(); i++) {
        KeyFrame k = s.getKeyFrames().get(i);
        if (k.getTime() == when) {
          copyAllTheValue(allKeysAtThisTick, s, k);
        }
      }
    }
    return allKeysAtThisTick;
  }

  private void copyAllTheValue(List<IShape> allKeysAtThisTick, IShape s, KeyFrame k) {
    IShape cur = s.getShape();
    cur.setName(s.getName());
    cur.changeColor(k.getColor());
    cur.setLength("w", k.getW());
    cur.setLength("h", k.getH());
    cur.getPos().setX(k.getX());
    cur.getPos().setY(k.getY());
    cur.getKeyFrames().add(k);
    allKeysAtThisTick.add(cur);
  }

  @Override
  public List<IShape> getShapes() {
    List<IShape> copy = new ArrayList<>();
    for (IShape s: shapes) {
      copy.add(s);
    }
    return copy;
  }

  @Override
  public String motionToString(String which, int when) {
    String result = "";
    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i).getName().equals(which)) {
        for (int j = 0; j < shapes.get(i).getKeyFrames().size(); j++) {
          if (shapes.get(i).getKeyFrames().get(j).getTime() == when) {
            result = result + "motion " + shapes.get(i).getName() + " start:"
                + shapes.get(i).getKeyFrames().get(j).keyToString() + " end:" + shapes.get(i)
                .getKeyFrames().get(j + 1).keyToString();
          }
        }
      }
    }
    return result;
  }
}
