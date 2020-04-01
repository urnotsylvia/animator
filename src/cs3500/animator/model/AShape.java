package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The shape takes part in the animation that need to be changed.
 */
public abstract class AShape implements IShape {

  protected String name;
  protected double w;
  protected double h;
  protected RGBColor color;
  protected Posn pos;
  protected List<KeyFrame> keyFrames;

  /**
   * to represent the constructor of the Model.AShape.
   *
   * @param name      the name of the shape
   * @param w         the width of the shape
   * @param h         the height of the shape
   * @param color     the color of the shape
   * @param pos       the position of the shape, if x or y is negative, the Shape is still exist but
   *                  off-screen
   * @param keyFrames the current status of the shape
   */
  public AShape(String name, double w, double h, RGBColor color, Posn pos,
      List<KeyFrame> keyFrames) {
    if (w < 0.0001 || h < 0.0001) {
      throw new IllegalArgumentException("the dimension of the shape cannot be negative");
    }
    this.name = name;
    this.color = color;
    this.w = w;
    this.h = h;
    this.pos = pos;
    this.keyFrames = keyFrames;
  }

  /**
   * constructs the shape.
   *
   * @param other the other copy shape
   */
  public AShape(AShape other) {
    this.name = other.name;
    this.color = other.color;
    this.w = other.w;
    this.h = other.h;
    this.pos = other.pos;
    this.keyFrames = other.keyFrames;
  }

  /**
   * constructs the shape with given name.
   *
   * @param name the name of the shape
   */
  public AShape(String name) {
    this.name = name;
  }

  @Override
  public void changePos(Posn newPos) {
    pos.setX(newPos.getX());
    pos.setY(newPos.getY());
  }

  @Override
  public List<IShape> getShapesWithAllKeys() {
    List<IShape> allKeys = new ArrayList<>();
    for (KeyFrame k : this.keyFrames) {
      for (int i = 0; i < this.keyFrames.size(); i++) {
        copyAllTheValue(allKeys, this, k);
      }
    }
    return allKeys;
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
  public void addKeyFrame(KeyFrame newKey) {
    for (KeyFrame keyFrame : keyFrames) {
      if (keyFrame.getTime() == newKey.getTime()) {
        if (!keyFrame.keyToString().equals(newKey.keyToString())) {
          throw new IllegalArgumentException("this tick has already been taken");
        } else {
          return;
        }
      }
    }
    keyFrames.add(newKey);
  }

  @Override
  public IShape getShape() {
    return new Rectangle("", 0.0001, 0.0001, new RGBColor(0, 0, 0),
        new Posn(0, 0),
        new ArrayList<>());
  }

  @Override
  public List<KeyFrame> getKeyFrames() {
    return keyFrames;
  }

  @Override
  public void setLength(String wH, double newLength) {
    switch (wH) {
      case "w":
      case "W":
        this.w = newLength;
        break;
      case "H":
      case "h":
        this.h = newLength;
        break;
      default:
    }
  }

  @Override
  public void dropKeyFrame(int time) {
    boolean flag = false;
    for (int i = 0; i < keyFrames.size(); i++) {
      if (keyFrames.get(i).getTime() == time) {
        flag = true;
        keyFrames.remove(i);
      }
    }
    if (!flag) {
      throw new IllegalArgumentException("no such keyFrame, cannot drop");
    }
  }

  @Override
  public RGBColor getColor() {
    return this.color;
  }

  @Override
  public void changeColor(RGBColor newColor) {
    this.color = newColor;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Posn getPos() {
    return this.pos;
  }

  @Override
  public double getWOrH(String wH) {
    switch (wH) {
      case "w":
      case "W":
        return w;
      case "h":
      case "H":
        return h;
      default:
        return 0;
    }
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }
}

