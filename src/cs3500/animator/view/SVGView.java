package cs3500.animator.view;

import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.model.IShape;
import cs3500.animator.model.KeyFrame;
import java.io.IOException;
import java.util.List;

/**
 * shows the animation in the format that is an XML-based format that can be used to describe images
 * and animations.
 */
public class SVGView implements IView {

  private Appendable output;
  private int speed;

  /**
   * constructs the view given speed and the appendable to output.
   *
   * @param speed  the speed
   * @param output the output file
   */
  public SVGView(int speed, Appendable output) {
    this.speed = speed;
    this.output = output;
  }

  @Override
  public void showAnimation(List<IShape> shapes, List<Integer> bounds) {
    StringBuilder result = new StringBuilder(
        "<svg viewBox=\"" + bounds.get(0) + " " + bounds.get(1)
            + " " + bounds.get(2) + " "
            + bounds.get(3)
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n");
    String shapeType;
    for (int i = 0; i < shapes.size(); i++) {
      IShape curShape = shapes.get(i);

      switch (curShape.getShapeAsString()) {
        case "rectangle":
          shapeType = "rect";
          break;
        case "oval":
          shapeType = "ellipse";
          break;
        default:
          throw new IllegalArgumentException("no such shape type");
      }
      result.append("\t").append(getShapeAsSVGString(shapeType, curShape));
      for (int k = 0; k < curShape.getKeyFrames().size() - 1; k++) {
        KeyFrame curKey = curShape.getKeyFrames().get(k);
        KeyFrame nextKey = curShape.getKeyFrames().get(k + 1);
        result.append(curAnimation(curKey, nextKey, shapeType));
      }
      result.append("\t</").append(shapeType).append(">\n\n");
    }
    result.append("</svg>");
    if (output == null) {
      System.out.println(result);
    } else {
      try {
        output.append(result.toString());
      } catch (IOException ioe) {
        throw new IllegalArgumentException("failed to append:("); //what is IOE, should throw IAE?
      }
    }
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("don't need this method");
  }

  /**
   * return the current motion to the string.
   *
   * @param curKey  the current keyFrame
   * @param nextKey the keyFrame next to the current KeyFrame
   * @return the current motion to the string
   */
  private String curAnimation(KeyFrame curKey, KeyFrame nextKey, String shapeType) {
    String result = "";
    String[] arr1 = curKey.keyToString().split(" ");
    String[] arr2 = nextKey.keyToString().split(" ");
    double startTime = (curKey.getTime() + 0.0) * 1000 / speed;
    double duration = ((nextKey.getTime() - curKey.getTime()) + 0.0) * 1000 / speed;
    String changeAttribute = "";
    int arr2Index = 1;
    boolean changedColorAt5 = false;
    boolean changedColorAt6 = false;
    while (arr2Index < arr2.length) {
      for (int i = 1; i < arr1.length; i++) {
        if (!arr1[i].equals(arr2[arr2Index])) {
          switch (i) {
            case 1:
              switch (shapeType) {
                case "rect":
                  changeAttribute = "x";
                  break;
                case "ellipse":
                  changeAttribute = "cx";
                  break;
                default:
              }
              break;
            case 2:
              switch (shapeType) {
                case "rect":
                  changeAttribute = "y";
                  break;
                case "ellipse":
                  changeAttribute = "cy";
                  break;
                default:
              }
              break;
            case 3:
              switch (shapeType) {
                case "rect":
                  changeAttribute = "width";
                  break;
                case "ellipse":
                  changeAttribute = "rx";
                  break;
                default:
              }
              break;
            case 4:
              switch (shapeType) {
                case "rect":
                  changeAttribute = "height";
                  break;
                case "ellipse":
                  changeAttribute = "ry";
                  break;
                default:
              }
              break;
            case 5:
            case 6:
            case 7:
              changeAttribute = "fill";
              if (changedColorAt5 || changedColorAt6) {
                changedColorAt5 = false;
                changedColorAt6 = false;
                break;
              }
              if (i == 5) {
                changedColorAt5 = true;
              }
              if (i == 6) {
                changedColorAt6 = true;
              }
              break;
            default:
              throw new IllegalArgumentException("not a valid attribute");
          }
          if (changeAttribute.equals("fill")) {
            if (changedColorAt5) {
              result = result + "\t\t<animate attributeType=\"xml\" begin=\""
                  + startTime
                  + "ms\" dur=\"" + duration
                  + "ms\" attributeName=\"" + changeAttribute
                  + "\" from=\"" + curKey.getColor().asRGBString() + "\" to=\""
                  + nextKey.getColor().asRGBString() + "\" fill=\"freeze\" />\n";
            }
          } else {
            result = result + "\t\t<animate attributeType=\"xml\" "
                + "begin=\"" + startTime + "ms\" "
                + "dur=\"" + duration + "ms\" "
                + "attributeName=\"" + changeAttribute
                + "\" from=\"" + arr1[i] + "\" to=\"" + arr2[arr2Index]
                + "\" fill=\"freeze\" />\n";
          }
        }
        arr2Index++;
      }
    }
    return result;
  }

  /**
   * return the shapes in the model as a String using SVG format.
   *
   * @param shapeType shape type
   * @param curShape  the current shape
   * @return the shapes in the model as a String
   */
  private String getShapeAsSVGString(String shapeType, IShape curShape) {
    String dimension;
    switch (shapeType) {
      case "rect":
        dimension = "x=\"" + curShape.getKeyFrames().get(0).getX() + "\" " + "y=\"" + curShape
            .getKeyFrames().get(0).getY() + "\" "
            + "width=\"" + curShape.getKeyFrames().get(0).getW() + "\" "
            + "height=\"" + curShape.getKeyFrames().get(0).getH() + "\" ";
        break;
      case "ellipse":
        dimension = "cx=\"" + curShape.getKeyFrames().get(0).getX() + "\" " + "cy=\""
            + curShape.getKeyFrames().get(0).getY() + "\" "
            + "rx=\"" + (curShape.getKeyFrames().get(0).getW() / 2) + "\" "
            + "ry=\"" + (curShape.getKeyFrames().get(0).getH() / 2) + "\" ";
        break;
      default:
        throw new IllegalStateException("invalid shape type");
    }
    return "<" + shapeType + " id=\"" + curShape.getName() + "\" "
        + dimension
        + "fill=\"" + curShape.getKeyFrames().get(0).getColor().asRGBString() + "\" "
        + "visibility=\"visible\" >\n";
  }
}