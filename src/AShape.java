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
   * to represent the constructor of the AShape.
   *
   * @param name      the name of the shape
   * @param w         the width of the shape
   * @param h         the height of the shape
   * @param color     the color of the shape
   * @param pos       the position of the shape,
   *                  if x or y is negative, the Shape is still exist but off-screen
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
   * to represent the copy of the constructor of the shape.
   *
   * @param other the other copy shape
   */
  public AShape(AShape other) {
    this.name = other.name;
    this.color = other.color;
    this.w = other.w;
    this.w = other.w;
    this.pos = other.pos;
  }

  @Override
  public void changePos(Posn newPos) {
    pos.setX(newPos.getX());
    pos.setY(newPos.getY());
  }

  @Override
  public void addKeyFrame(KeyFrame newKey) {
    for (int i = 0; i < keyFrames.size(); i++) {
      if (keyFrames.get(i).getTime() == newKey.getTime()) {
        throw new IllegalArgumentException("this tick has already been taken");
      }
    }
    keyFrames.add(newKey);
  }

  @Override
  public IShape getShape() {
    return new Rectangle("", 0.0001, 0.0001, new RGBColor(0, 0, 0), new Posn(0, 0), new ArrayList());
  }

  @Override
  public List<KeyFrame> getKeyFrames() {
    return keyFrames;
  }

  @Override
  public void changeLength(String wH, double rate) {
    switch (wH) {
      case "w":
      case "W":
        this.w += rate;
        break;
      case "H":
      case "h":
        this.h += rate;
        break;
      default:
        return;
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

