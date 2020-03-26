package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;
import cs3500.animator.Model.IShape;
import cs3500.animator.Model.KeyFrame;
import java.io.IOException;

public class SVGView extends AView {

  /**
   * constructs the view given model, speed and the appendable to output
   *
   * @param model  the animation model
   * @param speed  the speed
   * @param output the output file
   */
  public SVGView(IAnimationOperations model, int speed, Appendable output) {
    super(model, speed, output);
    this.speed = speed;
    this.output = output;
  }

  @Override
  public void showAnimation() {
    String result = "";
    String shapeType = "";
    for (int i = 0; i < this.model.getShapes().size(); i++) {
      IShape curShape = this.model.getShapes().get(i);

      switch (curShape.getShapeAsString()) {
        case "Rectangle":
          shapeType = "rect";
          break;
        case "Oval":
          shapeType = "ellipse";
          break;
        default:
          throw new IllegalArgumentException("no such shape type");
      }
      result = result + "\t" + getShapeAsSVGString(shapeType, curShape);
      for (int k = 0; k < curShape.getKeyFrames().size() - 1; k++) {
        KeyFrame curKey = curShape.getKeyFrames().get(k);
        KeyFrame nextKey = curShape.getKeyFrames().get(k + 1);
        //get the animations for curShape
        //<animate attributeType="xml" begin="base.begin+1000ms" dur="4000ms" attributeName="x" from="200" to="300" fill="freeze" />
        result = result + curAnimation(curKey, nextKey);
      }
    }
    try {
      output.append(result);
    } catch (IOException ioe) {
      throw new IllegalArgumentException("failed to append:("); //what is IOE, should throw IAE?
    }
  }

  private String curAnimation(KeyFrame curKey, KeyFrame nextKey) {
    String result = "";
    String[] arr1 = curKey.keyToString().split(" ");
    String[] arr2 = nextKey.keyToString().split(" ");
    String changeAttribute = "";
    int arr2Index = 1;
    while (arr2Index < arr2.length) {
      for (int i = 1; i < arr1.length; i++) {
        if (!arr1[i].equals(arr2[arr2Index])) {
          switch (i) {
            case 1:
              changeAttribute = "x";
              break;
            case 2:
              changeAttribute = "y";
              break;
            case 3:
              changeAttribute = "width";
              break;
            case 4:
              changeAttribute = "height";
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
            if (i == 5) {
              result = result + "\t\t<animate attributeName=\"fill\" values=\""
                  + arr2[5] + ";" + arr2[6] + ";" + arr2[7]
                  + "\" dur=\"" + (Integer.parseInt(arr2[0]) - Integer.parseInt(arr1[0]))
                  + "s\" repeatCount=\"indefinite\" />\n";
            }
          }
          else {
            result = result + "\t\t<animation attributeType=\"xml\" "
                + "begin=\"base.begin+" + (speed * curKey.getTime()) + "ms\" "
                + "dur=\"" + (speed * (nextKey.getTime() - curKey.getTime())) + "ms\" "
                + "attributeName=" + changeAttribute
                + "\" from=\"" + arr1[i] + "\" to=" + arr2[arr2Index]
                + "\" fill=\"freeze\" />\n";
          }
        }
        arr2Index++;
      }
    }
    return result;
  }

  //<rect id="P" x="200" y="200" width="50" height="100" fill="rgb(128,0,128)" visibility="visible" >
  private String getShapeAsSVGString(String shapeType, IShape curShape) {
    return "<" + shapeType + " id=\"" + curShape.getName() + "\" "
        + "x=\"" + curShape.getPos().getX() + "\" " + "y=\"" + curShape.getPos().getY() + "\" "
        + "width=\"" + curShape.getWOrH("w") + "\" "
        + "height=\"" + curShape.getWOrH("h") + "\" "
        + "fill=\"rgb(" + curShape.getColor().asString() + ")\" "
        + "visibility=\"visible\" />\n";
  }
}