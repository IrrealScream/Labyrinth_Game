package entity.pnjs.questions;

import entity.pnjs.questions.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

public class QuestionTest{

    private Question ques1;
    private Question ques2;

    @Before
    public void before(){
        ques1 = new Question("Quel est le résultat de !3 ?","6");
        ques2 = new Question("Que se passe t-il en 1453?","Chute de Constantinople");
    }

    @Test
    public void CheckAnswerTestQ1(){
        assertTrue(ques1.checkAnswer("6"));
    }

    @Test
    public void CheckAnswerTestQ2(){
        assertTrue(ques2.checkAnswer("Chute de CONSTANTINOPLE"));
    }


// ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(entity.pnjs.questions.QuestionTest.class);
  }
}