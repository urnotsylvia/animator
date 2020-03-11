import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class AnimationModelTest {
  RGBColor c1 = new RGBColor(100, 0, 20);
  RGBColor c2 = new RGBColor(10, 100, 100);
  Posn p1 = new Posn(10, 20);
  Posn p2 = new Posn(20, 30);
  KeyFrame k1 = new KeyFrame(1, 15, 40, 10, 20, c1);
  KeyFrame k2 = new KeyFrame(1, 20, 40, 10, 20, c1);
  KeyFrame k3 = new KeyFrame(4, 20, 40, 10, 20, c1);
  KeyFrame k4 = new KeyFrame(5, 20, 40, 10, 20, c2);
  KeyFrame k5 = new KeyFrame(5, 10, 5, 10, 20, c2);
  List<KeyFrame> lok1 = new ArrayList(Arrays.asList(k1));
  List<KeyFrame> lok4 = new ArrayList(Arrays.asList(k3));
  List<KeyFrame> lok5 = new ArrayList(Arrays.asList(k5));
  List<KeyFrame> lok2 = new ArrayList(Arrays.asList(k1, k3));
  List<KeyFrame> lok3 = new ArrayList(Arrays.asList(k1, k3, k4));
  IShape r1 = new Rectangle("r1", 15, 40, c1, p1, lok1);
  IShape r2 = new Rectangle("r1", 15, 40, c1, p1, lok2);
  IShape r3 = new Rectangle("r1", 15, 40, c1, p2, lok1);
  IShape r4 = new Rectangle("r1", 15.1 , 40, c1, p1, lok1);
  IShape r5 = new Rectangle("r1", 15, 50, c1, p1, lok1);
  IShape r6 = new Rectangle("r6", 15, 40, c1, p1, lok2);
  IShape o1 = new Oval("o1", 20, 40, c1, p1, lok2);
  IShape o1a = new Oval("o1", 20, 40, c1, p1, lok4);
  IShape o2 = new Oval("o2", 10, 5, c2, p2, lok3);
  IShape o2a = new Oval("o2", 10, 5, c2, p2, lok5);

  List<IShape> los1 = new ArrayList<>(Arrays.asList(r1, o1, o2));
  List<IShape> los2 = new ArrayList<>(Arrays.asList(r1, o1, o2, r6));
  List<IShape> los3 = new ArrayList<>(Arrays.asList(r1, o1a, o2a));

  IAnimationOperations model1 = new AnimationModel(los1);
  IAnimationOperations model2 = new AnimationModel(los2);

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

  @Test (expected = IllegalArgumentException.class)
  public void addKeyFrameAtSameTickFalseTest() {
    r1.addKeyFrame(k2);
  }

  @Test
  public void addKeyFrameTest() {
    r1.addKeyFrame(k3);
    assertEquals(r1, r2);
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
    r1.changeLength("w", 0.1);
    assertEquals(r1, r4);
  }

  @Test
  public void changeUpperWLengthTest() {
    r1.changeLength("W", 0.1);
    assertEquals(r1, r4);
  }

  @Test
  public void changeLowHLengthTest() {
    r1.changeLength("h", 10);
    assertEquals(r1, r5);
  }

  @Test
  public void changeUpperHLengthTest() {
    r1.changeLength("H", 10);
    assertEquals(r1, r5);
  }

  @Test
  public void changeColorTest() {
    r1.changeColor(c2);
    assertEquals(r1.getColor(), c2);
  }

  @Test
  public void createRectTest(){
    assertEquals(Rectangle.createRect("r1", 30, 60, c1, p1, lok1),
        new Rectangle("r1", 30, 60, c1, p1, lok1));
  }

  @Test (expected = IllegalArgumentException.class)
  public void createIllegalRectNegativeWTest() {
    Rectangle.createRect("invalidRect", -1, 60.2, c1, p1, lok1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void createIllegalRectNegativeHTest() {
    Rectangle.createRect("invalidRect", 100.002, -60.2, c1, p1, lok1);
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
  public void getKeyFrameY() {
  }

  @Test
  public void getKeyFrameW() {
  }

  @Test
  public void getKeyFrameH() {
  }

  @Test
  public void getTime() {
  }

  @Test
  public void keyToString() {
  }

  @Test
  public void createOval() {
  }

  @Test
  public void getOvalShape() {
  }

  @Test
  public void getPosnXTest() {
  }

  @Test
  public void getPosnYTest() {
  }

  @Test
  public void setPosnXTest() {
  }

  @Test
  public void posToStringTest() {
  }

  @Test
  public void setPosnYTest() {
  }

  @Test
  public void colorAsStringTest() {
  }

  @Test
  public void testEqualsTest() {
  }

  @Test
  public void add() {
    model1.add(r6);
    assertEquals(model1.getShapes(), los2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void invlidAdd() {
    model1.add(r2);
  }

  @Test
  public void remove() {
    model2.remove("r6");
    assertEquals(model2.getShapes(), los1);
  }

  @Test
  public void invalidRemove() {
    model2.remove("r100");
  }

  @Test
  public void getState() {
    List<IShape> los = model1.getState(1);
    assertEquals(los.get(0).getColor(), c1);
    assertEquals(los.get(0).getWOrH("w"), 15, 40);
  }

  @Test
  public void motionToStringNoSuchShapeTest() {
    assertEquals("", model1.motionToString("r2", 1));
  }

  @Test
  public void motionToStringTest() {
    assertEquals("motion o1 start:1 15 40 10.0 20.0 100 0 20 "
            + "end:4 20 40 10.0 20.0 100 0 20",
        model1.motionToString("o1", 1));
  }
  //all the tests for equals
  //do I have to test null??
  //what if there is no that tick in the keyFrames?
}