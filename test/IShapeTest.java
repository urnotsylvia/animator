import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class IShapeTest {
  RGBColor c1 = new RGBColor(100, 0, 20);
  Posn p1 = new Posn(10, 20);
  Posn p2 = new Posn(20, 30);
  KeyFrame k1 = new KeyFrame(1, 15, 40, 10, 20, c1);
  KeyFrame k2 = new KeyFrame(1, 20, 40, 10, 20, c1);
  List<KeyFrame> lok1 = new ArrayList(Arrays.asList(k1));
  List<KeyFrame> lok2 = new ArrayList(Arrays.asList(k1, k2));
  IShape s1 = new Rectangle("s1", 15, 40, c1, p1, lok1);
  IShape s2 = new Rectangle("s2", 15, 40, c1, p1, lok2);
  IShape s3 = new Rectangle("s3", 15, 40, c1, p2, lok1);
  IShape s4 = new Rectangle("s4", 15.1 , 40, c1, p1, lok1);
  IShape s5 = new Rectangle("s5", 15, 50, c1, p1, lok1);





  @Test
  public void getColor() {
    assertEquals(s1.getColor(), c1);
  }

  @Test
  public void getName() {
    assertEquals(s1.getName(), "s1");
  }

  @Test
  public void getShape() {
    assertEquals(s1.getShape(), new Rectangle("", 0, 0,
        new RGBColor(0, 0, 0), new Posn(0, 0), new ArrayList()));
  }

  @Test
  public void getPos() {
  assertEquals(s1.getPos(), p1);
  }


  @Test
  public void addKeyFrame() {
    s1.addKeyFrame(k2);
    assertEquals(s1, s2);
  }

  @Test
  public void getKeyFrames() {
    assertEquals(s1.getKeyFrames(), lok1);
  }

  @Test
  public void getLowWTest() {
    assertEquals(s1.getWOrH("w"), 15, 0.001);
  }

  @Test
  public void getLowHTest() {
    assertEquals(s1.getWOrH("h"), 40, 0.001);
  }

  @Test
  public void getUpperWTest() {
    assertEquals(s1.getWOrH("W"), 15, 0.001);
  }

  @Test
  public void getUpperHTest() {
    assertEquals(s1.getWOrH("H"), 40, 0.001);
  }

  @Test
  public void changePos() {
    s1.changePos(p2);
    assertEquals(s1, s3);

  }

  @Test
  public void changeLowWLengthTest() {
    s1.changeLength("w", 0.1);
    assertEquals(s1, s4);
  }

  @Test
  public void changeUpperWLengthTest() {
    s1.changeLength("W", 0.1);
    assertEquals(s1, s4);
  }

  @Test
  public void changeLowHLengthTest() {
    s1.changeLength("h", 10);
    assertEquals(s1, s5);
  }

  @Test
  public void changeUpperHLengthTest() {
    s1.changeLength("H", 10);
    assertEquals(s1, s5);
  }

  @org.junit.Test
  public void changeColor() {
    
  }
}