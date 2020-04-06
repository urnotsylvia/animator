package cs3500.animator.view;

import cs3500.animator.model.IReadonlyAnimationOperations;
import cs3500.animator.model.IShape;
import cs3500.animator.model.KeyFrame;
import java.io.IOException;

/**
 * shows the animation in the format that is an XML-based format that can be used to describe images
 * and animations.
 */
public class SVGView implements ISVGView {
  private IReadonlyAnimationOperations model;
  private Appendable output;
  private int speed;

  /**
   * constructs the view given speed and the appendable to output.
   *
   * @param speed  the speed
   * @param output the output file
   */
  public SVGView(IReadonlyAnimationOperations model, int speed, Appendable output) {
    this.model = model;
    this.speed = speed;
    this.output = output;
  }

  @Override
  public void showAnimation() {
    StringBuilder result = new StringBuilder(
        "<svg viewBox=\"" + model.getBound("x") + " " + model.getBound("y")
            + " " + model.getBound("w") + " "
            + model.getBound("h")
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n");
    String shapeType;
    for (int i = 0; i < model.getShapes().size(); i++) {
      IShape curShape = model.getShapes().get(i);

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
 /*     if((curShape.getKeyFrames().size() == 2) && curShape.getKeyFrames().get(0).allValuesAreEqualButTime(curShape.getKeyFrames().get(1))) {
        result.append("\t\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\""
            + ((curShape.getKeyFrames().get(curShape.getKeyFrames().size() - 1).getTime() + 0.0) * 1000 / speed)
            + "ms\" attributeName=\"visibility\" from=\"visible\" to=\"hidden\" repeatCount=\"1\" />\n");
          result.append("\t\t<animate attributeType=\"xml\" begin=\""
              + (curShape.getKeyFrames().get(0).getTime() * 1000 / speed) + "ms\" dur=\""
              + (((curShape.getKeyFrames().get(curShape.getKeyFrames().size() - 1).getTime() - curShape.getKeyFrames().get(0).getTime()) + 0.0) * 1000 / speed)
              + "ms\" attributeName=\"visibility\" from=\"hidden\" to=\"visible\" repeatCount=\"1\" />\n");
      }*/
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

  /**
   * return the current motion to the string.
   *
   * @param curKey  the current keyFrame
   * @param nextKey the keyFrame next to the current KeyFrame
   * @return the current motion to the string
   */
  private String curAnimation(KeyFrame curKey, KeyFrame nextKey, String shapeType) {
    StringBuilder result = new StringBuilder();
    String[] arr1 = curKey.keyToString().split(" ");
    String[] arr2 = nextKey.keyToString().split(" ");
    double startTime = (curKey.getTime() + 0.0) * 1000 / speed;
    double duration = ((nextKey.getTime() - curKey.getTime()) + 0.0) * 1000 / speed;
    String changeAttribute = "";
    int arr2Index = 1;
    boolean changedColor = false;
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
                break;
            default:
              throw new IllegalArgumentException("not a valid attribute");
          }
          if (changeAttribute.equals("fill")) {
            if (!changedColor) {
              result.append("\t\t<animate attributeType=\"xml\" begin=\"").append(startTime)
                  .append("ms\" dur=\"").append(duration).append("ms\" attributeName=\"")
                  .append(changeAttribute).append("\" from=\"")
                  .append(curKey.getColor().asRGBString()).append("\" to=\"")
                  .append(nextKey.getColor().asRGBString()).append("\" fill=\"freeze\" />\n");
              changedColor = true;
            }
          } else {
            result.append("\t\t<animate attributeType=\"xml\" ").append("begin=\"")
                .append(startTime).append("ms\" ").append("dur=\"").append(duration).append("ms\" ")
                .append("attributeName=\"").append(changeAttribute).append("\" from=\"")
                .append(arr1[i]).append("\" to=\"").append(arr2[arr2Index])
                .append("\" fill=\"freeze\" />\n");
          }
        }
        arr2Index++;
      }
    }
    return result.toString();
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