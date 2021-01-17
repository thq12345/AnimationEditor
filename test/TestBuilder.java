import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ExcellenceModel;
import cs3500.animator.controller.AnimationControls;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The test class that tests Builder.
 */
public class TestBuilder {

  @Test
  public void testTextual() {
    ExcellenceModel.Builder b = new ExcellenceModel.Builder();
    b.setBounds(2000, 2000, 50, 50);
    b.declareShape("abc", "rectangle",1);
    b.addMotion("abc", 1, 10, 10, 50, 50, 255, 100, 100,0,
        1, 10, 10, 50, 50, 255, 100, 100,0);
    b.declareShape("def", "oval",1);
    b.addMotion("def", 1, 100, 100, 50, 50, 255, 255, 0,0,
        1, 100, 100, 50, 50, 255, 255, 0,0);
    // the declarations of actual motions.
    b.addMotion("abc", 1, 10, 10, 50, 50, 255, 100, 100,0,
        500, 40, 40, 80, 80, 255, 100, 100,0);
    b.addMotion("abc", 500, 40, 40, 80, 80, 255, 100, 100,0,
        2000, 100, 300, 80, 80, 255, 100, 100,0);
    b.addMotion("def", 1, 100, 100, 50, 50, 255, 255, 0,0,
        500, 40, 40, 55, 55, 255, 230, 0,0);
    b.addMotion("def", 500, 40, 40, 55, 55, 255, 230, 0,0,
        2000, 200, 500, 70, 70, 255, 153, 0,0);
    b.addMotion("def", 2000, 200, 500, 70, 70, 255, 153, 0,0,
        5000, 200, 500, 100, 100, 255, 0, 0,0);
    AnimationModel model = b.build();
    assertEquals("Shape abc rectangle\n"
        + "Motion abc 1 10 10 50 50 255 100 100 0   500 40 40 80 80 255 100 100 0\n"
        + "Motion abc 500 40 40 80 80 255 100 100 0   2000 100 300 80 80 255 100 100 0\n"
        + "Shape def oval\n"
        + "Motion def 1 100 100 50 50 255 255 0 0   500 40 40 55 55 255 230 0 0\n"
        + "Motion def 500 40 40 55 55 255 230 0 0   2000 200 500 70 70 255 153 0 0\n"
        + "Motion def 2000 200 500 70 70 255 153 0 0   5000 200 500 100 100 255 0 0 0",
        model.getLog());
    AnimationControls cont2 = new AnimationControls(model, "text", 1, "random");
    cont2.startAnimating();
  }

  @Test
  public void testSVGView() {
    ExcellenceModel.Builder b = new ExcellenceModel.Builder();
    b.setBounds(2000, 2000, 50, 50);
    b.declareShape("abc", "rectangle",1);
    b.addMotion("abc", 1, 10, 10, 50, 50, 255, 100, 100,0,
        1, 10, 10, 50, 50, 255, 100, 100,0);
    b.declareShape("def", "oval",1);
    b.addMotion("def", 1, 100, 100, 50, 50, 255, 255, 0,0,
        1, 100, 100, 50, 50, 255, 255, 0,0);
    // the declarations of actual motions.
    b.addMotion("abc", 1, 10, 10, 50, 50, 255, 100, 100,0,
        500, 40, 40, 80, 80, 255, 100, 100,0);
    b.addMotion("abc", 500, 40, 40, 80, 80, 255, 100, 100,0,
        2000, 100, 300, 80, 80, 255, 100, 100,0);
    b.addMotion("def", 1, 100, 100, 50, 50, 255, 255, 0,0,
        500, 40, 40, 55, 55, 255, 230, 0,0);
    b.addMotion("def", 500, 40, 40, 55, 55, 255, 230, 0,0,
        2000, 200, 500, 70, 70, 255, 153, 0,0);
    b.addMotion("def", 2000, 200, 500, 70, 70, 255, 153, 0,0,
        5000, 200, 500, 100, 100, 255, 0, 0,0);
    AnimationModel model = b.build();
    assertEquals("Shape abc rectangle\n"
        + "Motion abc 1 10 10 50 50 255 100 100 0   500 40 40 80 80 255 100 100 0\n"
        + "Motion abc 500 40 40 80 80 255 100 100 0   2000 100 300 80 80 255 100 100 0\n"
        + "Shape def oval\n"
        + "Motion def 1 100 100 50 50 255 255 0 0   500 40 40 55 55 255 230 0 0\n"
        + "Motion def 500 40 40 55 55 255 230 0 0   2000 200 500 70 70 255 153 0 0\n"
        + "Motion def 2000 200 500 70 70 255 153 0 0   5000 200 500 100 100 255 0 0 0",
        model.getLog());
    AnimationControls cont2 = new AnimationControls(model, "svg", 1, "random");
    cont2.startAnimating();
  }


}
