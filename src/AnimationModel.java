import java.util.ArrayList;
import java.util.List;

/**
 * The class AnimationModel and store the status of the Animation.
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
      if (s.getName().equals(shape.getName())) {
        throw new IllegalArgumentException("This shape is already exists, cannot add this shape.");
      }
    }
    this.shapes.add(shape);
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
    return shapes;
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
