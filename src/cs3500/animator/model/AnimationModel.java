package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Model.AnimationModel and store the status of the Animation.
 */
public class AnimationModel implements IAnimationOperations {

  private final List<IShape> shapes;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * constructs the model and initialize the values.
   *
   * @param shapes the list of the shapes that the model takes in
   */
  public AnimationModel(List<IShape> shapes) {
    this.shapes = shapes;
    this.x = 0;
    this.y = 0;
    this.width = 0;
    this.height = 0;
  }


  /**
   * the builder that build the model according to the operations after the reader parse the file.
   */
  public static final class Builder implements AnimationBuilder<IAnimationOperations> {

    IAnimationOperations model;

    /**
     * constructs the builder that takes in the model.
     */
    public Builder() {
      this.model = new AnimationModel(new ArrayList<IShape>());
    }

    @Override
    public IAnimationOperations build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<IAnimationOperations> setBounds(int x, int y, int width, int height) {
      this.model.setBounds(x, y, width, height);
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
      this.model.addKeyframe(name, t1, x1, y1, w1, h1, r1, g1, b1);
      this.model.addKeyframe(name, t2, x2, y2, w2, h2, r2, g2, b2);
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
  public void setBounds(int x, int y, int width, int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("bound can not be negative number");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
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
      case "oval":
      case "ellipse":
        this.shapes.add(new Oval(name));
        break;
      default:
        throw new IllegalArgumentException("invalid shape type");
    }
  }

  @Override
  public String getBoundsAsString() {
    return this.x + " " + this.y + " " + this.width + " " + this.height;
  }

  @Override
  public int getBound(String i) {
    switch (i) {
      case "x":
        return this.x;
      case "y":
        return this.y;
      case "w":
        return this.width;
      case "h":
        return this.height;
      default:
        throw new IllegalArgumentException("invalid attribute");
    }
  }

  @Override
  public List<Integer> getBoundAsList() {
    List<Integer> bounds = new ArrayList<>();
    bounds.add(x);
    bounds.add(y);
    bounds.add(width);
    bounds.add(height);
    return bounds;
  }

  @Override
  public void addKeyframe(String name, int t, int x, int y,
      int w, int h, int r, int g, int b) {
    boolean exist = false;
    for (IShape s : shapes) {
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

  /**
   * make a copy of the shape and add it to the given list.
   *
   * @param list the list of shapes
   * @param s    the shase need to be copied
   * @param k    the keyFrame of the shape
   */
  private void copyAllTheValue(List<IShape> list, IShape s, KeyFrame k) {
    IShape cur = s.getShape();
    cur.setName(s.getName());
    cur.changeColor(k.getColor());
    cur.setLength("w", k.getW());
    cur.setLength("h", k.getH());
    cur.getPos().setX(k.getX());
    cur.getPos().setY(k.getY());
    cur.getKeyFrames().add(k);
    list.add(cur);
  }

  @Override
  public List<IShape> getShapes() {
    List<IShape> copy = new ArrayList<>();
    for (IShape s : shapes) {
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
