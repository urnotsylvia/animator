package cs3500.animator;

import cs3500.animator.view.DisplayView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.ViewType;
import view.DisplayView;
import view.IView;
import view.SVGView;
import view.TextView;

/**
 * The factory class for AnimationView, provides a static method to give a instance of a
 * Animation IView.
 */
public class AnimationViewCreator {

  /**
   * creates a corresponding View of the Animation based on the type given.
   *
   * @param t the type of model: either a single move model or multi cards move model.
   * @return a Model that can only move a card at once or can move either one or a pile of cards
   */
  public static IView create(ViewType t) {
    switch (t) {
      case DISPLAY:
        return new DisplayView();
      case TEXT:
        return new TextView();
      case SVG:
        return new SVGView();
      default:
        return null;
    }
  }
}
