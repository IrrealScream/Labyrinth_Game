package entity.pnjs;
import entity.pnjs.questions.*;
import entity.pnjs.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

public class AskerTest{

  private Asker Sphinx;

  @Before
  public void before(){
    Sphinx = new Asker(0,"Sphinx");
  }

  @Test
  public void TestAssertImmortal(){
    assertTrue(Sphinx.isImmortal());
  }

  @Test
  public void TestAssertCanGetAndAnswerQuestion(){
    Question akshan = new Question("Akshan est il un champion de League Of Legends?","Oui");
    assertTrue(Sphinx.getSizeListQuestion()==1);
    Sphinx.addQuestion(akshan);
    assertTrue(Sphinx.getSizeListQuestion()==1);
    assertEquals(Sphinx.getRandomQuestion(),akshan);
    assertTrue(Sphinx.getCurrentQuestion().checkAnswer("Oui"));
  }

// ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(entity.pnjs.AskerTest.class);
  }

}
