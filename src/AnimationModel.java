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
      if (s.getName().equals(shape.getName())) {
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
    List<IShape> allKeysAtThisTick = new ArrayList<>();
    for (IShape s : shapes) {
      for (KeyFrame k : s.getKeyFrames()) {
        if (k.getTime() == when) {
          IShape cur = s.getShape();
          System.out.println(cur.getWOrH("h"));
          cur.setName(s.getName());
          cur.changeColor(k.getColor());
          cur.changeLength("w", k.getW());
          cur.changeLength("h", k.getH());
          cur.getPos().setX(k.getX());
          cur.getPos().setX(k.getY());
          System.out.println(cur.getWOrH("w"));
          allKeysAtThisTick.add(cur);
        }
      }
    }
    return allKeysAtThisTick;
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
