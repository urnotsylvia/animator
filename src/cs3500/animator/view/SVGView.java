package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;
import cs3500.animator.Model.IShape;
import cs3500.animator.Model.KeyFrame;
import javax.swing.JFrame;

public class SVGView extends AView {

  public SVGView(IAnimationOperations model, int speed, Appendable output) {
    super(model, speed, output);
    this.speed = speed;
    this.output = output;

    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


  @Override
  public ViewType getViewType() {
    return ViewType.SVG;
  }

  @Override
  public void showAnimation(IAnimationOperations m) {
    String result = "";
    String shapeType = "";
    for (int i = 0; i < m.getShapes().size(); i++) {
      IShape curShape = m.getShapes().get(i);

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
      result = result + getShapeAsSVGString(shapeType, curShape);
      for (int k = 0; k < curShape.getKeyFrames().size(); k++) {
        KeyFrame curKey = curShape.getKeyFrames().get(k);
        KeyFrame nextKey = curShape.getKeyFrames().get(k + 1);
        //get the animations for curShape
        //<animate attributeType="xml" begin="base.begin+1000ms" dur="4000ms" attributeName="x" from="200" to="300" fill="freeze" />
        result = result + "<animation attributeType=\"xml\" "
            + "begin=\"base.begin+" + (speed * curKey.getTime()) + "ms\" "
            + "dur=\"" + (speed * (nextKey.getTime() - curKey.getTime())) + "ms\" "
            + "attributeName="; //+ whichAttributeIsChange() +
      }
    }
  }


  //<rect id="P" x="200" y="200" width="50" height="100" fill="rgb(128,0,128)" visibility="visible" >
  private String getShapeAsSVGString(String shapeType, IShape curShape) {
    return "<" + shapeType + " id=\"" + curShape.getName() + "\" "
        + "x=\"" + curShape.getPos().getX() + "\" " + "y=\"" + curShape.getPos().getY() + "\" "
        + "width=\"" + curShape.getWOrH("w") + "\" "
        + "height=\"" + curShape.getWOrH("h") + "\" "
        + "fill=\"rgb(" + curShape.getColor().asString() + ")\" "
        + "visibility=\"visible\" >\n ";
  }
}