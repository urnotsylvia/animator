import java.util.ArrayList;
import java.util.List;

/**
 * The class AnimationModel that operates every motion of the shape, and store the motion of the
 * shape.
 */
public class AnimationModel implements IAnimationOperations {

  private final List<IShape> shapes;

  /**
   * to represent the constructor of the model.
   *
   * @param shapes the list of the shapes that the model takes in
   */
  public AnimationModel(List<IShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void add(IShape shape) {
    for (IShape s : this.shapes) {
      if (s.getName().equalsIgnoreCase(shape.getName())) {
        throw new IllegalArgumentException("This shape is already exists, cannot add this shape.");
      }
    }
    this.shapes.add(shape);
  }

  @Override
  public void remove(String name) {
    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i).getName().equals(name)) {
        shapes.remove(i);
      }
    }
  }

  @Override
  public List<IShape> getState(int when) {
    List<IShape> allKeysAtThisTick = null;
    for (IShape s : shapes) {
      for (KeyFrame k : s.getKeyFrames()) {
        if (k.getTime() == when) {
          IShape cur = s.getShape();
          cur.changeColor(k.getColor());
          cur.changeLength("w", k.getW());
          cur.changeLength("h", k.getH());
          cur.getPos().setX(k.getX());
          cur.getPos().setX(k.getY());
          allKeysAtThisTick.add(cur);
        }
      }
    }
    return allKeysAtThisTick;
  }

  @Override
  public String motionToString(String which, int when) {
    String result = null;
    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i).getName().equals(which)) {
        for (int j = 0; j < shapes.get(i).getKeyFrames().size(); j++) {
          if (shapes.get(i).getKeyFrames().get(j).getTime() == when) {
            result += "motion " + shapes.get(i).getName()
                + shapes.get(i).getKeyFrames().get(j).keyToString() + " " + shapes.get(i)
                .getKeyFrames().get(j + 1).keyToString();
          }
        }
      }
    }
    return result;
  }
}
