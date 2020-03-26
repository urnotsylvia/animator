import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import cs3500.animator.Model.AnimationModel;
import cs3500.animator.Model.IAnimationOperations;
import cs3500.animator.Model.IShape;
import cs3500.animator.Model.KeyFrame;
import cs3500.animator.Model.Oval;
import cs3500.animator.Model.Posn;
import cs3500.animator.Model.RGBColor;
import cs3500.animator.Model.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * The tests and examples of the animation.
 */
public class AnimationModelTest {

  RGBColor c1 = new RGBColor(100, 0, 20);
  RGBColor c1Same = new RGBColor(100, 0, 20);
  RGBColor c1Diff = new RGBColor(250, 250, 250);
  RGBColor c2 = new RGBColor(10, 100, 100);
  RGBColor c3 = new RGBColor(10, 20, 100);
  Posn p1 = new Posn(10, 20);
  Posn sameP1 = new Posn(10, 20);
  Posn p2 = new Posn(20, 30);
  Posn p3 = new Posn(5, 20);
  Posn p4 = new Posn(10, 100);
  KeyFrame k1 = new KeyFrame(1, 15, 40, 10, 20, c1);
  KeyFrame sameK1 = new KeyFrame(1, 15, 40, 10, 20, c1);
  KeyFrame diffK1 = new KeyFrame(2, 13, 41, 15, 2, c2);
  KeyFrame k2 = new KeyFrame(1, 20, 40, 10, 20, c1);
  KeyFrame k3 = new KeyFrame(4, 20, 40, 10, 20, c1);
  KeyFrame k4 = new KeyFrame(9, 20, 40, 10, 20, c2);
  KeyFrame k5 = new KeyFrame(10, 20, 10,
      10, 10, c3);
  List<KeyFrame> lok1 = new ArrayList(Arrays.asList(k1));
  List<KeyFrame> lok1a = new ArrayList(Arrays.asList(k1, k5));
  List<KeyFrame> lok2 = new ArrayList(Arrays.asList(k1, k3));
  List<KeyFrame> lok3 = new ArrayList(Arrays.asList(k1, k3, k4));
  IShape r1 = new Rectangle("r1", 15, 40, c1, p1, lok1);
  IShape r1a = new Rectangle("r1", 15, 40, c1, p1, lok1a);
  IShape diffR1 = new Rectangle("r7", 20, 60, c2, p2, lok2);
  IShape r1Same = new Rectangle("r1", 15, 40, c1, p1, lok1);
  IShape r2 = new Rectangle("r1", 15, 40, c1, p1, lok2);
  IShape r3 = new Rectangle("r1", 15, 40, c1, p2, lok1);
  IShape r4 = new Rectangle("r1", 15.1, 40, c1, p1, lok1);
  IShape r5 = new Rectangle("r1", 15, 50, c1, p1, lok1);
  IShape r6 = new Rectangle("r6", 15, 40, c1, p1, lok2);
  IShape r7 = new Rectangle("r7");
  IShape o1 = new Oval("o1", 20, 40, c1, p1, lok2);
  IShape o2 = new Oval("o2", 10, 5, c2, p2, lok3);
  IShape o2Same = new Oval("o2", 10, 5, c2, p2, lok3);
  IShape o1WithDiffName = new Oval("o1NotSame", 20, 40, c1, p1, lok2);
  IShape o1WithDiffWidth = new Oval("o1", 30, 40, c1, p1, lok2);
  IShape o1WithDiffPos = new Oval("o1", 20, 40, c1, p2, lok2);
  IShape o1WithDiffColor = new Oval("o1", 20, 40, c2, p1, lok2);
  IShape o1WithDiffPosn = new Oval("o1", 20, 40, c1, p2, lok2);
  IShape o1WithDiffKeyFrames = new Oval("o1", 20, 40, c1, p1, lok3);

  List<IShape> los1 = new ArrayList<>(Arrays.asList(r1, o1, o2));
  List<IShape> los2 = new ArrayList<>(Arrays.asList(r1, o1, o2, r6));
  List<IShape> los3 = new ArrayList<>(Arrays.asList(r1, o1, o2, r7));
  List<IShape> los4 = new ArrayList<>(Arrays.asList(r1a, o1, o2));
  List<IShape> emptyList = new ArrayList<>();

  IAnimationOperations model1 = new AnimationModel(los1);
  IAnimationOperations model2 = new AnimationModel(los2);
  IAnimationOperations emptyModel = new AnimationModel(emptyList);

  @Test
  public void checkIfEmptyTest() {
    assertEquals(emptyModel.getShapes(), new ArrayList<>());
  }

  @Test
  public void getColorTest() {
    assertEquals(r1.getColor(), c1);
  }

  @Test
  public void getNameTest() {
    assertEquals(r1.getName(), "r1");
  }

  @Test
  public void getRectShapeTest() {
    assertEquals(r1.getShape(), new Rectangle("", 0.0001, 0.0001,
        new RGBColor(0, 0, 0), new Posn(0, 0), new ArrayList()));
  }

  @Test
  public void getPosTest() {
    assertEquals(r1.getPos(), p1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameAtSameTickFalseTest() {
    r1.addKeyFrame(k2);
  }

  @Test
  public void addKeyFrameTest() {
    r1.addKeyFrame(k3);
    assertEquals(r1, r2);
  }

  @Test
  public void addKeyFrameToModelTest() {
    model1.addKeyframe("r1", 10, 20, 10,
    10, 10, 10, 20, 100);
    assertEquals(model1.getShapes(), los4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addKeyFrameToModelFalseTest() {
    model1.addKeyframe("r10", 10, 20, 10,
        10, 10, 10, 20, 100);
  }

  @Test
  public void dropKeyFrameTest() {
    assertTrue(r2.getKeyFrames().contains(k3));
    r2.dropKeyFrame(4);
    assertFalse(r2.getKeyFrames().contains(k3));
  }

  @Test (expected = IllegalArgumentException.class)
  public void dropKeyFalseTest() {
    r2.dropKeyFrame(100);
  }

  @Test
  public void getKeyFramesTest() {
    assertEquals(r1.getKeyFrames(), lok1);
  }

  @Test
  public void getLowWTest() {
    assertEquals(r1.getWOrH("w"), 15, 0.0001);
  }

  @Test
  public void getLowHTest() {
    assertEquals(r1.getWOrH("h"), 40, 0.0001);
  }

  @Test
  public void getUpperWTest() {
    assertEquals(r1.getWOrH("W"), 15, 0.0001);
  }

  @Test
  public void getUpperHTest() {
    assertEquals(r1.getWOrH("H"), 40, 0.0001);
  }

  @Test
  public void changePosTest() {
    r1.changePos(p2);
    assertEquals(r1, r3);
  }

  @Test
  public void changeLowWLengthTest() {
    r1.setLength("w", 15.1);
    assertEquals(r1, r4);
  }

  @Test
  public void changeUpperWLengthTest() {
    r1.setLength("W", 15.1);
    assertEquals(r1, r4);
  }

  @Test
  public void changeLowHLengthTest() {
    r1.setLength("h", 50);
    assertEquals(r1, r5);
  }

  @Test
  public void getKeyFrameYTest() {
    assertEquals(k1.getY(), 40, 0.0001);
  }

  @Test
  public void getKeyFrameWTest() {
    assertEquals(k1.getW(), 10, 0.0001);
  }

  @Test
  public void getKeyFrameHTest() {
    assertEquals(k1.getH(), 20, 0.0001);
  }

  @Test
  public void getTimeTest() {
    assertEquals(k1.getTime(), 1);
  }

  @Test
  public void keyToStringTest() {
    assertEquals(k1.keyToString(),
        "1 15 40 10.0 20.0 100 0 20");
  }

  @Test
  public void getOvalShapeTest() {
    assertEquals(o1.getShape(),
        new Oval("", 0.0001, 0.0001, new RGBColor(0, 0, 0),
            new Posn(0, 0), new ArrayList()));

  }

  @Test
  public void getPosnXTest() {
    assertEquals(p1.getX(), 10, 0.0001);
  }

  @Test
  public void getPosnYTest() {
    assertEquals(p1.getY(), 20, 0.0001);
  }

  @Test
  public void setPosnXTest() {
    p1.setX(5);
    assertEquals(p1, p3);
  }

  @Test
  public void posToStringTest() {
    assertEquals(p1.posToString(), "10.0 20.0");
  }

  @Test
  public void setPosnYTest() {
    p1.setY(100);
    assertEquals(p1, p4);
  }

  @Test
  public void colorAsStringTest() {
    assertEquals(c1.asString(), "100 0 20");
  }

  @Test
  public void colorAsRGBStringTest() {
    assertEquals(c1.asRGBString(), "rgb(100,0,20)");
  }

  @Test
  public void changeUpperHLengthTest() {
    r1.setLength("H", 50);
    assertEquals(r1, r5);
  }

  @Test
  public void changeColorTest() {
    r1.changeColor(c2);
    assertEquals(r1.getColor(), c2);
  }

  @Test
  public void getKeyFrameColor() {
    assertEquals(k1.getColor(), c1);
  }

  @Test
  public void getKeyFrameX() {
    assertEquals(k1.getX(), 15, 0.0001);
  }

  @Test
  public void addShapeTest() {
    model1.add("r7", "Rect");
    assertTrue(model1.getShapes().contains(r7));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invlidAdd() {
    model1.add("r1", "Rect");
  }

  @Test
  public void remove() {
    model2.remove("r6");
    assertEquals(model2.getShapes(), los1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRemove() {
    model2.remove("r100");
  }

  @Test
  public void getStateInBetweenTest() {
    model1.getState(3);
    List<IShape> los = model1.getState(1);
    assertEquals(c1, los.get(0).getColor());
    assertEquals(10, los.get(0).getWOrH("w"), 0.0001);
    assertEquals(20, los.get(0).getWOrH("h"), 0.0001);
    assertEquals("r1", los.get(0).getName());
    assertEquals(15, los.get(0).getPos().getX(), 0.0001);
    assertEquals(40, los.get(0).getPos().getY(), 0.0001);
    assertEquals(lok1, los.get(0).getKeyFrames());
  }

  @Test
  public void getState() {
    List<IShape> los = model1.getState(1);
    assertEquals(c1, los.get(0).getColor());
    assertEquals(10, los.get(0).getWOrH("w"), 0.0001);
    assertEquals(20, los.get(0).getWOrH("h"), 0.0001);
    assertEquals("r1", los.get(0).getName());
    assertEquals(15, los.get(0).getPos().getX(), 0.0001);
    assertEquals(40, los.get(0).getPos().getY(), 0.0001);
    assertEquals(lok1, los.get(0).getKeyFrames());
  }

  @Test
  public void keyFrameEqualTest() {
    assertEquals(k1, sameK1);
  }

  @Test
  public void testEqualsOval() {
    assertEquals(o2, o2Same);
  }

  @Test
  public void keyFrameEqualFalseTest() {
    assertNotEquals(k1, diffK1);
  }

  @Test
  public void keyFrameEqualFalseDiffObjTest() {
    assertNotEquals(k1, c1);
  }

  @Test
  public void posnEqualTest() {
    assertEquals(p1, sameP1);
  }

  @Test
  public void posnEqualsYFalseTest() {
    assertNotEquals(p1, p4);
  }

  @Test
  public void posnEqualsXFalseTest() {
    assertNotEquals(p1, p3);
  }

  @Test
  public void posnEqualsNotSameObjectTest() {
    assertNotEquals(p1, r1);
  }

  @Test
  public void testNotEqualOvalName() {
    assertNotEquals(o1, o1WithDiffName);
  }

  @Test
  public void testNotEqualOvalWidth() {
    assertNotEquals(o1, o1WithDiffWidth);
  }

  @Test
  public void testNotEqualOvalPos() {
    assertNotEquals(o1, o1WithDiffPos);
  }

  @Test
  public void testNotEqualOvalColor() {
    assertNotEquals(o1, o1WithDiffColor);
  }

  @Test
  public void testNotEqualOvalPosn() {
    assertNotEquals(o1, o1WithDiffPosn);
  }

  @Test
  public void testNotEqualOvalKeyFrames() {
    assertNotEquals(o1, o1WithDiffKeyFrames);
  }

  @Test
  public void motionToStringNoSuchShapeTest() {
    assertEquals("",
        model1.motionToString("r2", 1));
  }

  @Test
  public void motionToStringTest() {
    assertEquals("motion o1 start:1 15 40 10.0 20.0 100 0 20 "
            + "end:4 20 40 10.0 20.0 100 0 20",
        model1.motionToString("o1", 1));
  }

  @Test
  public void testEqualsRectangle() {
    assertEquals(r1, r1Same);
  }

  @Test
  public void testEqualsNotSameObject() {
    assertNotEquals(r1, o1);
  }

  @Test
  public void testEqualsNotSameRectangle() {
    assertNotEquals(r1, diffR1);
  }

  @Test
  public void testEqualsSameColor() {
    assertEquals(c1, c1Same);
  }

  @Test
  public void testEqualsColorDiffObject() {
    assertNotEquals(c1, p1);
  }

  @Test
  public void testEqualsDiffColor() {
    assertNotEquals(c1, c1Diff);
  }
}