package cs3500.animator;

import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.VisualView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.ViewType;

/**
 * The factory class for AnimationView, provides a static method to give a instance of a Animation
 * IView.
 */
public class AnimationCreator {

  /**
   * creates a corresponding View of the Animation based on the type given.
   *
   * @param t the type of model: either a single move model or multi cards move model.
   * @return a Model that can only move a card at once or can move either one or a pile of cards
   */
  public static IView create(ViewType t, IAnimationOperations model, int speed, Appendable output) {
    switch (t) {
      case VISUAL:
        return new VisualView(model, speed);
      case TEXTUAL:
        return new TextualView(output);
      case SVG:
        return new SVGView(speed, output);
      case EDITOR:
        return new EditorView(speed);
      default:
        return null;
    }
  }
}