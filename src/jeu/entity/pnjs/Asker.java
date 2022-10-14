package jeu.entity.pnjs;

import java.util.*;
import jeu.entity.pnjs.questions.*;

public class Asker extends PNJ{

  // List of Question
  private List<Question> listQuestion;
  // Actual Question
  private Question currentQuestion;
  // The clue from the Asker
  private String clue;

  /*
  * Constructor to build an Asker
  * @param iStamina the stamina
  * @param iName the name of the entity
  */
  public Asker(int iStamina,String iName){
    super(iStamina,iName,"\uD83D\uDC09");
    listQuestion = new ArrayList();
    this.currentQuestion=null;
    this.clue=null;
    }

    public void setClue(String clue2){
      this.clue=clue2;
    }
    public String getClue(){
      return this.clue;
    }

  /*
  *Needed to add a question to the possible question given
  * @param question Add a question of class Question to the list
  */
  public void addQuestion(Question question){
    listQuestion.add(question);
  }

  /*
  *Return the current question if needed to ask again
  * @return Question return currentQuestion
  */
  public Question getCurrentQuestion(){
    return this.currentQuestion;
  }

  /*
  *If needed to print the list to check some bug
  */
  public void listOfQuestionPrinting(){
    for(int i=0; i < listQuestion.size() ; i++){
      System.out.println("-"+listQuestion.get(i).getQuestion()+"\n");
    }
  }


  /*
  * This method will return a random Question in the list
  * @return Question return a Question the new currentQuestion
  */
  public Question getRandomQuestion(){ 
    int max= listQuestion.size();
    int min= 0;
    int i = (int)(Math.random() * (max - min )) + min;
    this.currentQuestion= listQuestion.get(i);
    return this.currentQuestion;
  }

}
